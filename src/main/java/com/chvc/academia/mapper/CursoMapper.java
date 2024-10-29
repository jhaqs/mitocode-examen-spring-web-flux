package com.chvc.academia.mapper;

import com.chvc.academia.dto.CursoDto;
import com.chvc.academia.model.Curso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CursoMapper {

  CursoDto toDTO(Curso entity);

  Curso toEntity(CursoDto dto);
}
