package br.com.jessica.gestao_cursos.modules.professor.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jessica.gestao_cursos.modules.professor.entities.ProfessorEntity;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, UUID>{
    Optional<ProfessorEntity> findByUsernameOrEmail(String username, String email);

    Optional<ProfessorEntity> findByUsername(String username);

}
    