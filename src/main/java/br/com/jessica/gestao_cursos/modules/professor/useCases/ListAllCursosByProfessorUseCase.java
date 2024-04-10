package br.com.jessica.gestao_cursos.modules.professor.useCases;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jessica.gestao_cursos.modules.professor.entities.CursoEntity;
import br.com.jessica.gestao_cursos.modules.professor.repositories.CursoRepository;

@Service
public class ListAllCursosByProfessorUseCase {
    
    @Autowired
    private CursoRepository cursoRepository;

    public List<CursoEntity> execute(UUID professorId){
        return this.cursoRepository.findByProfessorId(professorId);
    }
}

