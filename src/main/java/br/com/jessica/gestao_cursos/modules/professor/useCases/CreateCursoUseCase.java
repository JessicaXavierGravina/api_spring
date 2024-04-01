package br.com.jessica.gestao_cursos.modules.professor.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jessica.gestao_cursos.modules.professor.entities.CursoEntity;
import br.com.jessica.gestao_cursos.modules.professor.repositories.CursoRepository;

@Service
public class CreateCursoUseCase {

    @Autowired
    private CursoRepository cursoRepository;

    @SuppressWarnings("null")
    public CursoEntity execute(CursoEntity cursoEntity) {
        return this.cursoRepository.save(cursoEntity);

    }
}
