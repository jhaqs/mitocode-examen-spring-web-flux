package com.chvc.academia.mapper;

import com.chvc.academia.dto.EstudianteDto;
import com.chvc.academia.model.Estudiante;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface EstudianteMapper {

  EstudianteDto toDTO(Estudiante entity);

  Estudiante toEntity(EstudianteDto dto);
}
