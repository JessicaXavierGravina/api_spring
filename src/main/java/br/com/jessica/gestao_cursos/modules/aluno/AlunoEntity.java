package br.com.jessica.gestao_cursos.modules.aluno;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "Aluno")
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Schema(example = "Maria Cunha", requiredMode = RequiredMode.REQUIRED, description = "Nome do aluno")
    private String name;

   @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    @Schema(example = "maria", requiredMode = RequiredMode.REQUIRED, description = "Username do aluno")
    private String username;

    @Email(message = "O campo [email] deve conter um e-mail válido")
  @Schema(example = "maria@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "E-mail do aluno")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve conter entre (10) e (100) caracteres")
  @Schema(example = "maria@1234", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "Senha do aluno")
    private String password;

    @Schema(example = "Desenvolvedor Python", requiredMode = RequiredMode.REQUIRED, description = "Breve descrição do aluno")
    private String description;

    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}