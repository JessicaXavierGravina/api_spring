package br.com.jessica.gestao_cursos.modules.aluno.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthAlunoResponseDTO {

    private String acess_token;
    private Long expires_in;
    
}
