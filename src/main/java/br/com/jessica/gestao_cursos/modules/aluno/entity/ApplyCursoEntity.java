package br.com.jessica.gestao_cursos.modules.aluno.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import br.com.jessica.gestao_cursos.modules.aluno.AlunoEntity;
import br.com.jessica.gestao_cursos.modules.professor.entities.CursoEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "apply_cursos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyCursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", insertable = false, updatable = false)
    private AlunoEntity alunoEntity;

    @ManyToOne
    @JoinColumn(name = "curso_id", insertable = false, updatable = false)
    private CursoEntity cursoEntity;

    @Column(name = "aluno_id")
    private UUID alunoId;

    @Column(name = "job_id")
    private UUID cursoId;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
}
