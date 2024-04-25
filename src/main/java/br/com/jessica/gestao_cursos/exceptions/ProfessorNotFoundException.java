package br.com.jessica.gestao_cursos.exceptions;

public class ProfessorNotFoundException extends RuntimeException {
    public ProfessorNotFoundException() {
        super("Teacher not found");
    }
}
