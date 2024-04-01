package br.com.jessica.gestao_cursos.modules.professor.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "professor")
@Data
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Pattern(regexp = "\\S+", message = "O campo não deve conter espaço")
    private String username;

    @Email(message = "O campo deve conter um email válido")
    private String email;

    @Length(min = 10, max = 100)
    private String password;
    
    private String website;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

}