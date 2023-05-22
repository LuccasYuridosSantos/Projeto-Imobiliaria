package br.gov.sp.fatec.pi.imobiliaria.exception;

public class AgendamentoException extends RuntimeException {

    /**
     *
     */
    private static final String PERIODO_INVALIDO = "Periodo de Agendamento é inválido";
    /**
     *
     */
    private static final String EXISTE_UM_AGENDAMENTO = "Já existe um agendamento para data e hora informada!";

    private AgendamentoException(String message) {
        super(message);
    }

    public static AgendamentoException periodoInvalido(){
        return new AgendamentoException(PERIODO_INVALIDO);
    }

    public static AgendamentoException agendamentoJaExistente() {
        return new AgendamentoException(EXISTE_UM_AGENDAMENTO);
    }
}