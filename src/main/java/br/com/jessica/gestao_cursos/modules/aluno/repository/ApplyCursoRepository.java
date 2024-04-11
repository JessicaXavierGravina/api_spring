package br.com.jessica.gestao_cursos.modules.aluno.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jessica.gestao_cursos.modules.aluno.entity.ApplyCursoEntity;

public interface  ApplyCursoRepository  extends JpaRepository<ApplyCursoEntity, UUID> {
    
}
