package br.com.jessica.gestao_cursos.modules.aluno.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jessica.gestao_cursos.modules.professor.entities.CursoEntity;
import br.com.jessica.gestao_cursos.modules.professor.repositories.CursoRepository;


@Service
public class ListAllCursosByFilterUseCase {

    @Autowired
  private CursoRepository cursoRepository;

    public List<CursoEntity> execute(String filter) {
        return this.cursoRepository.findByDescriptionContainingIgnoreCase(filter);

    }
    
}
