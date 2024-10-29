package com.chvc.academia.service.impl;

import com.chvc.academia.model.Curso;
import com.chvc.academia.repository.ICursoRepo;
import com.chvc.academia.repository.IGenericRepo;
import com.chvc.academia.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl extends CRUDImpl<Curso, String> implements ICursoService {

  private final ICursoRepo repo;

  @Override
  protected IGenericRepo<Curso, String> getRepo() {
    return repo;
  }
}
