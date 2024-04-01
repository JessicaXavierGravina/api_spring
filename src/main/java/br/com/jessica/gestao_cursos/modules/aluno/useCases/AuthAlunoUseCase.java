package br.com.jessica.gestao_cursos.modules.aluno.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.jessica.gestao_cursos.modules.aluno.AlunoRepository;
import br.com.jessica.gestao_cursos.modules.aluno.dto.AuthAlunoRequestDTO;
import br.com.jessica.gestao_cursos.modules.aluno.dto.AuthAlunoResponseDTO;

@Service
public class AuthAlunoUseCase {

    @Value("${security.token.secret.aluno}")
    private String secretKey;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthAlunoResponseDTO execute(AuthAlunoRequestDTO authAlunoRequestDTO)
     throws AuthenticationException {
        var aluno = this.alunoRepository.findByUsername(authAlunoRequestDTO.username())
        .orElseThrow(() -> {
            throw new UsernameNotFoundException("Nome/Senha incorretos");
        });

        var passwordMatches = this.passwordEncoder
        .matches(authAlunoRequestDTO.password(), aluno.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException();
        }

        var roles = Arrays.asList("ALUNO");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofMinutes(20));

        var token = JWT.create()
        .withIssuer("javacursos")
        .withSubject(aluno.getId().toString())
        .withClaim("roles", roles)
        .withExpiresAt(expiresIn)
        .sign(algorithm);

        var authAlunoResponse = AuthAlunoResponseDTO.builder()
        .acess_token(token)
        .expires_in(expiresIn.toEpochMilli())
        .build();

        return authAlunoResponse;
    }
}
