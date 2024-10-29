package com.chvc.academia.repository;

import com.chvc.academia.model.Estudiante;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IEstudianteRepo extends IGenericRepo<Estudiante, String> {

  Flux<Estudiante> findByOrderByEdadDesc();

  Flux<Estudiante> findByOrderByEdadAsc();

}
