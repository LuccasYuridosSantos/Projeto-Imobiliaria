package br.gov.sp.fatec.pi.imobiliaria.exception;

/**
 * Exceção personalizada para erros relacionados a agendamentos.
 */
public class AgendamentoException extends RuntimeException {

    private static final String PERIODO_INVALIDO = "Periodo de Agendamento é inválido";
    private static final String EXISTE_UM_AGENDAMENTO = "Já existe um agendamento para a data e hora informadas!";

    /**
     * Construtor privado para criar uma nova instância de AgendamentoException com a mensagem fornecida.
     *
     * @param message A mensagem de erro da exceção
     */
    private AgendamentoException(String message) {
        super(message);
    }

    /**
     * Cria e retorna uma nova instância de AgendamentoException para o erro de período inválido.
     *
     * @return A AgendamentoException criada
     */
    public static AgendamentoException periodoInvalido() {
        return new AgendamentoException(PERIODO_INVALIDO);
    }

    /**
     * Cria e retorna uma nova instância de AgendamentoException para o erro de agendamento já existente.
     *
     * @return A AgendamentoException criada
     */
    public static AgendamentoException agendamentoJaExistente() {
        return new AgendamentoException(EXISTE_UM_AGENDAMENTO);
    }

    /**
     * Cria e retorna uma nova instância de AgendamentoException com uma mensagem personalizada.
     *
     * @param message A mensagem de erro da exceção
     * @return A AgendamentoException criada
     */
    public static AgendamentoException naoEncontradoParaAgendamento(final String message) {
        return new AgendamentoException(message);
    }
}
