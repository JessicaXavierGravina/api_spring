package br.com.jessica.gestao_cursos.modules.professor.dto;

import lombok.Data;

@Data
public class CreateCursoDTO {
    private String description;
    private String skill_necessaria;
    private String level;
}