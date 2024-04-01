package br.com.jessica.gestao_cursos.modules.aluno.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jessica.gestao_cursos.modules.aluno.dto.AuthAlunoRequestDTO;
import br.com.jessica.gestao_cursos.modules.aluno.useCases.AuthAlunoUseCase;

@RestController
@RequestMapping("/aluno")
public class AuthAlunoController {

    @Autowired
    private AuthAlunoUseCase authAlunoUseCase;
    
    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthAlunoRequestDTO authAlunoRequestDTO) {

        try{
            var token = this.authAlunoUseCase.execute(authAlunoRequestDTO);
            return ResponseEntity.ok().body(token);

        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(e.getMessage());

        }

    }
}
