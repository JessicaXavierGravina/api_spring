package br.com.jessica.gestao_cursos.modules.professor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthProfessorResponseDTO {

    private String access_token;
    private Long expires_in;
    
}
