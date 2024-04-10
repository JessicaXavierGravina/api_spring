package br.com.jessica.gestao_cursos.modules.aluno.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import br.com.jessica.gestao_cursos.modules.aluno.AlunoEntity;
import br.com.jessica.gestao_cursos.modules.aluno.dto.ProfileAlunoResponseDTO;
import br.com.jessica.gestao_cursos.modules.aluno.useCases.CreateAlunoUseCase;
import br.com.jessica.gestao_cursos.modules.aluno.useCases.ListAllCursosByFilterUseCase;
import br.com.jessica.gestao_cursos.modules.aluno.useCases.ProfileAlunoUseCase;
import br.com.jessica.gestao_cursos.modules.professor.entities.CursoEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/aluno")
@Tag(name = "Aluno", description = "Informações do aluno")
public class AlunoController {

    @Autowired
    private CreateAlunoUseCase createAlunoUseCase;

    @Autowired
    private ProfileAlunoUseCase profileAlunoUseCase;

    @Autowired
    private ListAllCursosByFilterUseCase ListAllCursosByFilterUseCase;

    @PostMapping("/")
    @Operation(summary = "Cadastro de alunos", description = "Essa função é responsável por cadastrar um aluno")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AlunoEntity.class))
        }),
        @ApiResponse(responseCode = "400", description = "Usuário já existe")
    })

    public ResponseEntity<Object> create(@Valid @RequestBody AlunoEntity alunoEntity) {
        try{
            var result = this.createAlunoUseCase.execute(alunoEntity);
            return ResponseEntity.ok().body(result);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
  

    @GetMapping("/")
    @PreAuthorize("hasRole('ALUNO')")
    @Operation(summary = "Perfil do aluno", description = "Essa função é responsável por buscar as informações do perfil do aluno")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ProfileAlunoResponseDTO.class))
        }),
        @ApiResponse(responseCode = "400", description = "User not found")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idAluno = request.getAttribute("aluno_id");
    
        try {
            // Verificar se idAluno não é nulo antes de tentar convertê-lo para uma string
            if (idAluno != null) {
                var profile = this.profileAlunoUseCase.execute(UUID.fromString(idAluno.toString()));
                return ResponseEntity.ok().body(profile);
            } else {
                // Se idAluno for nulo, retornar uma resposta adequada (por exemplo, 404 Not Found)
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/curso")
    @PreAuthorize("hasRole('ALUNO')")
    @Operation(summary = "Listagem de cursos disponíveis para o aluno", description = "Essa função é responsável por listar todos os cursos disponíveis, baseada no filtro")
    @SecurityRequirement(name = "jwt_auth")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = CursoEntity.class)))
        })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<CursoEntity> findJobByFilter(@RequestParam String filter) {
        return this.ListAllCursosByFilterUseCase.execute(filter);
    }
}