package br.com.jessica.gestao_cursos.modules.professor.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jessica.gestao_cursos.exceptions.ProfessorNotFoundException;
import br.com.jessica.gestao_cursos.modules.professor.entities.CursoEntity;
import br.com.jessica.gestao_cursos.modules.professor.repositories.CursoRepository;
import br.com.jessica.gestao_cursos.modules.professor.repositories.ProfessorRepository;

@Service
public class CreateCursoUseCase {

    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private ProfessorRepository professorRepository;

    public CursoEntity execute(CursoEntity cursoEntity) {

        professorRepository.findById(cursoEntity.getProfessorId()).orElseThrow(() -> {
            throw new ProfessorNotFoundException();
        });
        return this.cursoRepository.save(cursoEntity);
    }
}