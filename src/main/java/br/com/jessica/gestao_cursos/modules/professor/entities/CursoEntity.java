package br.com.jessica.gestao_cursos.modules.professor.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "curso")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Curso de TypeScript")
    private String description;
    
    @Schema(example = "HTML, CSS, JavaScript")
    private String skill_necessaria;

    @NotBlank(message = "Campo obrigatório")
    @Schema(example = "AVANÇADO")
    private String level;

    @ManyToOne
    @JoinColumn(name = "professor_id", insertable = false, updatable = false)
    private ProfessorEntity professorEntity;

    @Column(name = "professor_id", nullable = false)
    private UUID professorId;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
}