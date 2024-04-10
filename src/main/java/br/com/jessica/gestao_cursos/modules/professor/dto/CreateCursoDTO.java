package br.com.jessica.gestao_cursos.modules.professor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCursoDTO {

    @Schema(example = "Curso para iniciantes no desenvolvimento front-end", requiredMode = RequiredMode.REQUIRED)
    private String description;

    @Schema(example = "HTML, CSS", requiredMode = RequiredMode.REQUIRED)
    private String skill_necessaria;

    @Schema(example = "INICIANTE", requiredMode = RequiredMode.REQUIRED)
    private String level;
}