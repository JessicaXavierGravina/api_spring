package br.com.jessica.gestao_cursos.modules.aluno.useCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.jessica.gestao_cursos.exceptions.UserNotFoundException;
import br.com.jessica.gestao_cursos.modules.aluno.AlunoEntity;
import br.com.jessica.gestao_cursos.modules.aluno.AlunoRepository;
import br.com.jessica.gestao_cursos.modules.aluno.entity.ApplyCursoEntity;
import br.com.jessica.gestao_cursos.modules.aluno.repository.ApplyCursoRepository;
import br.com.jessica.gestao_cursos.modules.professor.entities.CursoEntity;
import br.com.jessica.gestao_cursos.modules.professor.repositories.CursoRepository;

@ExtendWith(MockitoExtension.class)
public class TestApplyCursoAlunoUseCase {
    
    @InjectMocks
    private ApplyCursoAlunoUseCase applyCursoAlunoUseCase;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private ApplyCursoRepository applyCursoRepository;

    @Test
    @DisplayName("O aluno não deve se cadastrar a um curso se ele não for encontrado")
    public void não_ser_aplicado_quando_aluno_nao_existir() {
        try{
            applyCursoAlunoUseCase.execute(null, null);
        } catch (Exception e){
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    public void nao_deve_ser_possivel_cadastrar_se_vaga_nao_existir() {
        var idAluno = UUID.randomUUID();
        var idCurso = UUID.randomUUID();

        var applyCurso = ApplyCursoEntity.builder().alunoId(idAluno)
                .cursoId(idCurso).build();

        var applyCourseCreated = ApplyCursoEntity.builder().id(UUID.randomUUID()).build();

        when(alunoRepository.findById(idAluno)).thenReturn(Optional.of(new AlunoEntity()));
        when(cursoRepository.findById(idCurso)).thenReturn(Optional.of(new CursoEntity()));

        when(applyCursoRepository.save(applyCurso)).thenReturn(applyCourseCreated);

        var result = applyCursoAlunoUseCase.execute(idAluno, idCurso);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());

    }
}