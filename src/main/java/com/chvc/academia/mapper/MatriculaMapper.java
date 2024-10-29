package com.chvc.academia.mapper;

import com.chvc.academia.dto.MatriculaDto;
import com.chvc.academia.model.Matricula;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatriculaMapper {

  MatriculaDto toDTO(Matricula entity);

  Matricula toEntity(MatriculaDto dto);
}
