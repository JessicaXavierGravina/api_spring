package br.com.jessica.gestao_cursos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ErrorMessageDTO {

  private String message;
  private String field;
}