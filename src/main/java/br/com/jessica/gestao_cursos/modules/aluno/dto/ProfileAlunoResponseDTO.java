package br.com.jessica.gestao_cursos.modules.aluno.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileAlunoResponseDTO {

    private UUID id;
    private String name;
    private String username;
    private String email;
    private String description;
    
}
