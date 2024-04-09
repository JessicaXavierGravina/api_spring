package br.com.jessica.gestao_cursos.modules.professor.repositories;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jessica.gestao_cursos.modules.professor.entities.CursoEntity;

public interface CursoRepository extends JpaRepository<CursoEntity, UUID> {
    List<CursoEntity> findByDescriptionContainingIgnoreCase(String title);

    List<CursoEntity> findByProfessorId(UUID professorId);
}