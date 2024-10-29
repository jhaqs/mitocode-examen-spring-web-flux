package com.chvc.academia.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "matriculas")
public class Matricula {

  @Id
  @EqualsAndHashCode.Include
  private String id;

  private LocalDateTime fechaMatricula;

  private Estudiante estudiante;

  private List<Curso> cursos;

  private Boolean estado;

}
