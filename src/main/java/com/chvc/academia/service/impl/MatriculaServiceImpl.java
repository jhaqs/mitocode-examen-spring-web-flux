package com.chvc.academia.service.impl;

import com.chvc.academia.model.Matricula;
import com.chvc.academia.repository.IGenericRepo;
import com.chvc.academia.repository.IMatriculaRepo;
import com.chvc.academia.service.IMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl extends CRUDImpl<Matricula, String> implements IMatriculaService {

  private final IMatriculaRepo repo;


  @Override
  protected IGenericRepo<Matricula, String> getRepo() {
    return repo;
  }
}
