package com.chvc.academia.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstudianteDto {

  private String id;
  @NotNull
  @NotEmpty
  private String nombres;
  @NotNull
  @NotEmpty
  private String apellidos;
  @Size(min = 8,max = 8,message = "Debe introducir un DNI valido(8 digitos)")
  @Pattern(regexp = "\\d{8}")
  private String dni;

  private Integer edad;
}
