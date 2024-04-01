package br.com.jessica.gestao_cursos.modules.professor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jessica.gestao_cursos.modules.professor.entities.ProfessorEntity;
import br.com.jessica.gestao_cursos.modules.professor.useCases.CreateProfessorUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private CreateProfessorUseCase createProfessorUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody ProfessorEntity professorEntity) {
        try{
            var result = this.createProfessorUseCase.execute(professorEntity);
            return ResponseEntity.ok().body(result);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    
}
