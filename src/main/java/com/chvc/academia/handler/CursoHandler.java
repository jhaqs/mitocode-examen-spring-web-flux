package com.chvc.academia.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import com.chvc.academia.dto.CursoDto;
import com.chvc.academia.mapper.CursoMapper;
import com.chvc.academia.model.Curso;
import com.chvc.academia.service.ICursoService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CursoHandler {

  private final ICursoService service;

  @Autowired
  private CursoMapper mapper;

  public Mono<ServerResponse> findAll(ServerRequest req) {
    return ServerResponse
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.findAll().map(this::convertToDto), CursoDto.class);
  }

  public Mono<ServerResponse> findById(ServerRequest req) {
    String id = req.pathVariable("id");

    return service.findById(id)
        .map(this::convertToDto)
        .flatMap(e -> ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fromValue(e))
        ).switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> create(ServerRequest req) {
    Mono<CursoDto> monoCursoDTO = req.bodyToMono(CursoDto.class);

    return monoCursoDTO
        .flatMap(e -> service.save(this.convertToEntity(e)))
        .map(this::convertToDto)
        .flatMap(e -> ServerResponse
            .created(URI.create(req.uri().toString().concat("/").concat(e.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(fromValue(e))
        );
  }

  public Mono<ServerResponse> update(ServerRequest req) {
    String id = req.pathVariable("id");

    return req.bodyToMono(CursoDto.class)
        .map(e -> {
          e.setId(id);
          return e;
        })
        .flatMap(e -> service.update(this.convertToEntity(e), id))
        .map(this::convertToDto)
        .flatMap(e -> ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fromValue(e))
        )
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> delete(ServerRequest req) {
    String id = req.pathVariable("id");

    return service.delete(id)
        .flatMap(result -> {
          if (result) {
            return ServerResponse.noContent().build();
          } else {
            return ServerResponse.notFound().build();
          }
        });
  }

  private CursoDto convertToDto(Curso model) {
    return mapper.toDTO(model);
  }

  private Curso convertToEntity(CursoDto dto) {
    return mapper.toEntity(dto);
  }
}
