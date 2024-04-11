package br.com.jessica.gestao_cursos.exceptions;

public class CursoNotFoundException extends RuntimeException{
    public CursoNotFoundException(){
        super("Curso not found");
    }
    
}
