package br.com.jessica.gestao_cursos.modules.aluno.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "João da Silva")
    private String name;

    @Schema(example = "joaosilva")
    private String username;

    @Schema(example = "joao@gmail.com")
    private String email;

    @Schema(example = "Desenvolvedor Java")
    private String description;
    
}