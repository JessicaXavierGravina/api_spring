package br.com.jessica.gestao_cursos.modules.professor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthProfessorDTO {

    private String password;
    private String username;
    
}
