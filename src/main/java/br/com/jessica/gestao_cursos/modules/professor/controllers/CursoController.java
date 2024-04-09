package br.com.jessica.gestao_cursos.modules.professor.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jessica.gestao_cursos.modules.professor.dto.CreateCursoDTO;
import br.com.jessica.gestao_cursos.modules.professor.entities.CursoEntity;
import br.com.jessica.gestao_cursos.modules.professor.useCases.CreateCursoUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/professor/curso")
public class CursoController {
    @Autowired
    private CreateCursoUseCase createCursoUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('PROFESSOR')")
    public CursoEntity create(@Valid @RequestBody CreateCursoDTO createCursoDTO, HttpServletRequest request) {
        var professorID = request.getAttribute("professor_id");

        var cursoEntity = CursoEntity.builder()
            .skill_necessaria(createCursoDTO.getSkill_necessaria())
            .professorId(UUID.fromString(professorID.toString()))
            .description(createCursoDTO.getDescription())
            .level(createCursoDTO.getLevel())
            .build();

        return this.createCursoUseCase.execute(cursoEntity);
    }
}