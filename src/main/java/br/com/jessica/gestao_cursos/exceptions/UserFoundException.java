package br.com.jessica.gestao_cursos.exceptions;

public class UserFoundException  extends RuntimeException {
    public UserFoundException() {
        super("Usuário já existe");
    }
}
