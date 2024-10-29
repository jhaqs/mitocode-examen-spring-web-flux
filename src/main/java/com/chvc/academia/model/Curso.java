package com.chvc.academia.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "cursos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Curso {

  @Id
  @EqualsAndHashCode.Include
  private String id;

  private String nombre;

  private String siglas;

  private Boolean estado;
}
