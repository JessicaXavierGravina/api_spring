package br.com.jessica.gestao_cursos.modules.professor.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.jessica.gestao_cursos.exceptions.UserFoundException;
import br.com.jessica.gestao_cursos.modules.professor.entities.ProfessorEntity;
import br.com.jessica.gestao_cursos.modules.professor.repositories.ProfessorRepository;

@Service
public class CreateProfessorUseCase {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ProfessorEntity execute(ProfessorEntity professorEntity) {

        this.professorRepository
            .findByUsernameOrEmail(professorEntity.getUsername(), professorEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });

        var password = passwordEncoder.encode(professorEntity.getPassword());
        professorEntity.setPassword(password);

        return this.professorRepository.save(professorEntity);
    }
    }