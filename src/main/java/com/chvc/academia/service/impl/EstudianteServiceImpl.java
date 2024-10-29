package com.chvc.academia.service.impl;

import com.chvc.academia.model.Estudiante;
import com.chvc.academia.repository.IEstudianteRepo;
import com.chvc.academia.repository.IGenericRepo;
import com.chvc.academia.service.IEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl extends CRUDImpl<Estudiante, String> implements IEstudianteService {

  private final IEstudianteRepo repo;

  @Override
  protected IGenericRepo<Estudiante, String> getRepo() {
    return repo;
  }

  @Override
  public Flux<Estudiante> listarOrdenDescEdad() {
    return repo.findByOrderByEdadDesc();
  }

  @Override
  public Flux<Estudiante> listarOrdenAscEdad() {
    return repo.findByOrderByEdadAsc();
  }
}
