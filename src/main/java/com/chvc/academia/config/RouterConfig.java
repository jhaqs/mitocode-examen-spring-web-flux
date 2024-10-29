package com.chvc.academia.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.chvc.academia.handler.CursoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

  //Functional Endpoints

  @Bean
  public RouterFunction<ServerResponse> routesCurso(CursoHandler handler) {
    return route(GET("/v2/cursos"), handler::findAll)
        .andRoute(GET("/v2/cursos/{id}"), handler::findById)
        .andRoute(POST("/v2/cursos"), handler::create)
        .andRoute(PUT("/v2/cursos/{id}"), handler::update)
        .andRoute(DELETE("/v2/cursos/{id}"), handler::delete);
  }
}
