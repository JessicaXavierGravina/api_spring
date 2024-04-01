package br.com.jessica.gestao_cursos.modules.aluno.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jessica.gestao_cursos.modules.aluno.AlunoEntity;
import br.com.jessica.gestao_cursos.modules.aluno.useCases.CreateAlunoUseCase;
import br.com.jessica.gestao_cursos.modules.aluno.useCases.ProfileAlunoUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private CreateAlunoUseCase createAlunoUseCase;

    @Autowired
    private ProfileAlunoUseCase profileAlunoUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody AlunoEntity createAlunoRequest) {
        try{
            var result = this.createAlunoUseCase.execute(createAlunoRequest);
            return ResponseEntity.ok().body(result);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ALUNO')")
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
    
}