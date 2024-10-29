package com.chvc.academia.controller;

import com.chvc.academia.dto.CursoDto;
import com.chvc.academia.mapper.CursoMapper;
import com.chvc.academia.model.Curso;
import com.chvc.academia.service.ICursoService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

  private final ICursoService service;

  @Autowired
  private CursoMapper mapper;

  @GetMapping
  public Mono<ResponseEntity<Flux<CursoDto>>> findAll() {
    Flux<CursoDto> fx = service.findAll()
            .map(this::convertToDto);
      return Mono.just(ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fx)
        )
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<CursoDto>> findById(@PathVariable("id") String id) {
    return service.findById(id)
        .map(this::convertToDto)
        .map(e -> ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(e)
        )
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<ResponseEntity<CursoDto>> save(@Valid @RequestBody CursoDto dto, final ServerHttpRequest req) {
    return service.save(convertToEntity(dto))
        .map(this::convertToDto)
        .map(e -> ResponseEntity.created(
                    URI.create(req.getURI().toString().concat("/").concat(e.getId()))
                )
                .contentType(MediaType.APPLICATION_JSON)
                .body(e)
        )
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<CursoDto>> update(@Valid @RequestBody CursoDto dto, @PathVariable("id") String id) {
    return Mono.just(dto)
        .map(e -> {
          e.setId(id);
          return e;
        })
        .flatMap(e -> service.update(convertToEntity(dto), id))
        .map(this::convertToDto)
        .map(e -> ResponseEntity
            .ok()
            .body(e)
        )
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Object>> delete(@PathVariable("id") String id) {
    return service.delete(id)
        .flatMap(result -> {
          if (result) {
            return Mono.just(ResponseEntity.noContent().build());
          } else {
            return Mono.just(ResponseEntity.notFound().build());
          }
        })
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  private CursoDto convertToDto(Curso model) {
    return mapper.toDTO(model);
  }

  private Curso convertToEntity(CursoDto dto) {
    return mapper.toEntity(dto);
  }
}
