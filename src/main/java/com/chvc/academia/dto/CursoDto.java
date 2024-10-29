package com.chvc.academia.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoDto {
  private String id;
  @NotNull
  @NotEmpty
  private String nombre;
  @NotNull
  @NotEmpty
  private String siglas;
  private Boolean estado;
}
