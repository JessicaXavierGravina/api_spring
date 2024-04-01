package br.com.jessica.gestao_cursos.modules.aluno;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository  extends JpaRepository<AlunoEntity, UUID> {
    
    Optional<AlunoEntity> findByUsernameOrEmail(String username, String email);
    Optional<AlunoEntity> findByUsername(String username);

}