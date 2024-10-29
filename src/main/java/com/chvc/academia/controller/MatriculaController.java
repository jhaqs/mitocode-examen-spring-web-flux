package com.chvc.academia.controller;

import com.chvc.academia.dto.MatriculaDto;
import com.chvc.academia.mapper.MatriculaMapper;
import com.chvc.academia.model.Matricula;
import com.chvc.academia.service.IMatriculaService;
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
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

  private final IMatriculaService service;

  @Autowired
  private MatriculaMapper mapper;

  @GetMapping
  public Mono<ResponseEntity<Flux<MatriculaDto>>> findAll() {
    Flux<MatriculaDto> fx = service.findAll().map(this::convertToDto);

    return Mono.just(ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fx)
        )
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<MatriculaDto>> findById(@PathVariable("id") String id) {
    return service.findById(id)
        .map(this::convertToDto)
        .map(e -> ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(e)
        )
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<ResponseEntity<MatriculaDto>> save(@Valid @RequestBody MatriculaDto dto, final ServerHttpRequest req) {
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
  public Mono<ResponseEntity<MatriculaDto>> update(@Valid @RequestBody MatriculaDto dto, @PathVariable("id") String id) {
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

  private MatriculaDto convertToDto(Matricula model) {
    return mapper.toDTO(model);
  }

  private Matricula convertToEntity(MatriculaDto dto) {
    return mapper.toEntity(dto);
  }
}
