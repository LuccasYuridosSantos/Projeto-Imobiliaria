package br.gov.sp.fatec.pi.imobiliaria.exception;

public class UsuarioException extends RuntimeException {

    private UsuarioException(String message) {
        super(message);
    }

    public static UsuarioException usuarioNaoEncontado(final String username){
        return new UsuarioException(String.format("Usuario não encontrado com %s", username));
    }
    public static UsuarioException usuarioExistente(final String username){
        return new UsuarioException(String.format("Usuário já existente com username: %s",username));
    }

    public static UsuarioException usuarioJaVinculado(final String username, final String tipoVinculado) {
        return new UsuarioException(String.format("Usuário %s já vinculado a um %s.",username, tipoVinculado));
    }

    public static UsuarioException clienteNaoVinculado(final Long codCliente, final  String username) {
        return new UsuarioException(String.format("Não foi possivel vincular o cliente %s ao usuario %s.", codCliente, username));
    }

    public static UsuarioException imobiliariaNaoVinculado(final Long codImobiliaria, final  String username) {
        return new UsuarioException(String.format("Não foi possivel vincular a imobiliaria %s ao usuario %s.", codImobiliaria, username));
    }

    public static UsuarioException corretorNaoViculado(final Long codCorretor, final String username) {
        return new UsuarioException(String.format("Não foi possivel vincular a imobiliaria %s ao usuario %s.", codCorretor, username));
    }

    public static UsuarioException usuarioNaoEncontrado(final Long id) {
        return new UsuarioException(String.format("Usuário não encontrado com id $s", id));
    }
}