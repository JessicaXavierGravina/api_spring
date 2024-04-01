package br.com.jessica.gestao_cursos.modules.professor.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jessica.gestao_cursos.modules.professor.entities.CursoEntity;

public interface CursoRepository extends JpaRepository<CursoEntity, UUID> {
    
}
