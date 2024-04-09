package br.com.jessica.gestao_cursos.modules.aluno.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.jessica.gestao_cursos.modules.aluno.AlunoRepository;
import br.com.jessica.gestao_cursos.modules.aluno.dto.ProfileAlunoResponseDTO;

@Service
public class ProfileAlunoUseCase {

    @Autowired
    private AlunoRepository alunoRepository;

    public ProfileAlunoResponseDTO execute(UUID idAluno) {
        var aluno = this.alunoRepository.findById(idAluno).orElseThrow(() -> {
            throw new UsernameNotFoundException("Usuario não encontrado");
        });

        var alunoDTO = ProfileAlunoResponseDTO.builder()
            .description(aluno.getDescription())
            .username(aluno.getUsername())
            .email(aluno.getEmail())
            .name(aluno.getName())
            .id(aluno.getId())
            .build();
        return alunoDTO;

    }
}