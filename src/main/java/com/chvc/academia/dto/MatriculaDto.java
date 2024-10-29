package com.chvc.academia.dto;

import com.chvc.academia.model.Curso;
import com.chvc.academia.model.Estudiante;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatriculaDto {

  private String id;
  @NotNull
  @NotEmpty
  private LocalDateTime fechaMatricula;

  private EstudianteDto estudiante;

  private List<CursoDto> cursos;
  @NotNull
  @NotEmpty
  private Boolean estado;
}
