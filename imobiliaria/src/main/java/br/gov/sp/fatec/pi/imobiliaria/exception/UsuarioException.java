package br.gov.sp.fatec.pi.imobiliaria.exception;

/**
 * Exceção personalizada para erros relacionados a usuários.
 */
public class UsuarioException extends RuntimeException {

    /**
     * Cria uma nova instância de UsuarioException com a mensagem fornecida.
     *
     * @param message A mensagem de erro da exceção
     */
    private UsuarioException(String message) {
        super(message);
    }

    /**
     * Cria uma nova instância de UsuarioException para o caso em que o usuário não é encontrado pelo nome de usuário.
     *
     * @param username O nome de usuário não encontrado
     * @return Uma instância de UsuarioException com a mensagem de erro apropriada
     */
    public static UsuarioException usuarioNaoEncontado(final String username){
        return new UsuarioException(String.format("Usuario não encontrado com %s", username));
    }

    /**
     * Cria uma nova instância de UsuarioException para o caso em que o usuário já existe com o nome de usuário.
     *
     * @param username O nome de usuário existente
     * @return Uma instância de UsuarioException com a mensagem de erro apropriada
     */
    public static UsuarioException usuarioExistente(final String username){
        return new UsuarioException(String.format("Usuário já existente com username: %s",username));
    }

    /**
     * Cria uma nova instância de UsuarioException para o caso em que o usuário já está vinculado a um tipo específico.
     *
     * @param username        O nome de usuário já vinculado
     * @param tipoVinculado   O tipo ao qual o usuário já está vinculado
     * @return Uma instância de UsuarioException com a mensagem de erro apropriada
     */
    public static UsuarioException usuarioJaVinculado(final String username, final String tipoVinculado) {
        return new UsuarioException(String.format("Usuário %s já vinculado a um %s.",username, tipoVinculado));
    }

    /**
     * Cria uma nova instância de UsuarioException para o caso em que não é possível vincular um cliente ao usuário.
     *
     * @param codCliente O código do cliente não vinculado
     * @param username   O nome de usuário ao qual o cliente não pode ser vinculado
     * @return Uma instância de UsuarioException com a mensagem de erro apropriada
     */
    public static UsuarioException clienteNaoVinculado(final Long codCliente, final String username) {
        return new UsuarioException(String.format("Não foi possível vincular o cliente %s ao usuário %s.", codCliente, username));
    }

    /**
     * Cria uma nova instância de UsuarioException para o caso em que não é possível vincular uma imobiliária ao usuário.
     *
     * @param codImobiliaria O código da imobiliária não vinculada
     * @param username       O nome de usuário ao qual a imobiliária não pode ser vinculada
     * @return Uma instância de UsuarioException com a mensagem de erro apropriada
     */
    public static UsuarioException imobiliariaNaoVinculado(final Long codImobiliaria, final String username) {
        return new UsuarioException(String.format("Não foi possível vincular a imobiliaria %s ao usuario %s.", codImobiliaria, username));
    }

    /**
     * Cria uma nova instância de UsuarioException para o caso em que não é possível vincular um corretor ao usuário.
     *
     * @param codCorretor O código do corretor não vinculado
     * @param username    O nome de usuário ao qual o corretor não pode ser vinculado
     * @return Uma instância de UsuarioException com a mensagem de erro apropriada
     */
    public static UsuarioException corretorNaoViculado(final Long codCorretor, final String username) {
        return new UsuarioException(String.format("Não foi possível vincular a imobiliaria %s ao usuario %s.", codCorretor, username));
    }

    /**
     * Cria uma nova instância de UsuarioException para o caso em que o usuário não é encontrado pelo ID.
     *
     * @param id O ID do usuário não encontrado
     * @return Uma instância de UsuarioException com a mensagem de erro apropriada
     */
    public static UsuarioException usuarioNaoEncontrado(final Long id) {
        return new UsuarioException(String.format("Usuário não encontrado com id $s", id));
    }
}
