package com.chvc.academia.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "estudiantes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estudiante {

  @Id
  @EqualsAndHashCode.Include
  private String id;


  private String nombres;

  private String apellidos;

  private String dni;

  private Integer edad;
}
