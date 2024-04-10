package br.com.jessica.gestao_cursos.modules.professor.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.jessica.gestao_cursos.modules.professor.dto.CreateCursoDTO;
import br.com.jessica.gestao_cursos.modules.professor.entities.CursoEntity;
import br.com.jessica.gestao_cursos.modules.professor.entities.ProfessorEntity;
import br.com.jessica.gestao_cursos.modules.professor.useCases.CreateCursoUseCase;
import br.com.jessica.gestao_cursos.modules.professor.useCases.ListAllCursosByProfessorUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/professor/curso")
public class CursoController {
    @Autowired
    private CreateCursoUseCase createCursoUseCase;

    @Autowired
    private ListAllCursosByProfessorUseCase listAllCursosByProfessorUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Tag(name = "Cursos", description = "Informações dos cursos")
    @Operation(summary = "Cadastro de cursos", description = "Essa função é responsável pelo cadastro dos cursos referente a cada professor")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
                @Content(schema = @Schema(implementation = CursoEntity.class))
        })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCursoDTO createCursoDTO, HttpServletRequest request) {
        var professorID = request.getAttribute("professor_id");

        try{
            var cursoEntity = CursoEntity.builder()
            .skill_necessaria(createCursoDTO.getSkill_necessaria())
            .professorId(UUID.fromString(professorID.toString()))
            .description(createCursoDTO.getDescription())
            .level(createCursoDTO.getLevel())
            .build();

            var result = this.createCursoUseCase.execute(cursoEntity);
            return ResponseEntity.ok().body(result);
            }catch(Exception e){
                    return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/")
        @PreAuthorize("hasRole('PROFESSOR')")
        @Tag(name = "Cursos", description = "Listagem dos cursos")
        @Operation(summary = "Listagem de cursos", description = "Essa função é responsável por listar os cursos de cada professor")
        @ApiResponses({
                @ApiResponse(responseCode = "200", content = {
                        @Content(schema = @Schema(implementation = ProfessorEntity.class))
                })
        })
        @SecurityRequirement(name = "jwt_auth")
        public ResponseEntity<Object> listByProfessor(HttpServletRequest request){
            var professorId = request.getAttribute("professor_id");
            var result = this.listAllCursosByProfessorUseCase.execute(UUID.fromString(professorId.toString()));
            return ResponseEntity.ok().body(result);
        }
    
    }
