package br.com.jessica.gestao_cursos.modules.aluno.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.jessica.gestao_cursos.exceptions.UserFoundException;
import br.com.jessica.gestao_cursos.modules.aluno.AlunoEntity;
import br.com.jessica.gestao_cursos.modules.aluno.AlunoRepository;

@Service
public class CreateAlunoUseCase {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AlunoEntity execute(AlunoEntity alunoEntity) {
        this.alunoRepository
        .findByUsernameOrEmail(alunoEntity.getUsername(), alunoEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
          });
          
        var password = passwordEncoder.encode(alunoEntity.getPassword());
        alunoEntity.setPassword(password);

        return this.alunoRepository.save(alunoEntity);
    }
    
}