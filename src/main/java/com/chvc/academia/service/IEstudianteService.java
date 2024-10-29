package com.chvc.academia.service;

import com.chvc.academia.model.Estudiante;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEstudianteService extends ICRUD<Estudiante, String> {
  Flux<Estudiante> listarOrdenDescEdad();

  Flux<Estudiante> listarOrdenAscEdad();

}
