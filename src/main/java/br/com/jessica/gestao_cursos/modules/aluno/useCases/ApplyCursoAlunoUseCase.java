package br.com.jessica.gestao_cursos.modules.aluno.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jessica.gestao_cursos.exceptions.CursoNotFoundException;
import br.com.jessica.gestao_cursos.exceptions.UserNotFoundException;
import br.com.jessica.gestao_cursos.modules.aluno.AlunoRepository;
import br.com.jessica.gestao_cursos.modules.aluno.entity.ApplyCursoEntity;
import br.com.jessica.gestao_cursos.modules.aluno.repository.ApplyCursoRepository;
import br.com.jessica.gestao_cursos.modules.professor.repositories.CursoRepository;

@Service
public class ApplyCursoAlunoUseCase {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ApplyCursoRepository applyCursoRepository;

    public ApplyCursoEntity execute(UUID idAluno, UUID idCurso) {

        this.alunoRepository.findById(idAluno)
        .orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        this.cursoRepository.findById(idCurso)
        .orElseThrow(() -> {
            throw new CursoNotFoundException();
        });

        var applyCurso = ApplyCursoEntity.builder()
        .alunoId(idAluno)
        .cursoId(idCurso).build();

        applyCurso = applyCursoRepository.save(applyCurso);
        return applyCurso;
    }
}