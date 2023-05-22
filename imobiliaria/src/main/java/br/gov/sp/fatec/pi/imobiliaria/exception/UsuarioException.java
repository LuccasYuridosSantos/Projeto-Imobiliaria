package br.gov.sp.fatec.pi.imobiliaria.exception;

public class UsuarioException extends RuntimeException {

    private UsuarioException(String message) {
        super(message);
    }

    public static UsuarioException usuarioInvalido(){
        return new UsuarioException("Usuario inválido");
    }
}