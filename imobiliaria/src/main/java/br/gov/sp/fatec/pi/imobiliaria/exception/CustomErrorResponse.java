package br.gov.sp.fatec.pi.imobiliaria.exception;

/**
 * Classe que representa uma resposta personalizada de erro.
 */
public class CustomErrorResponse {
    private String error;
    private String message;

    /**
     * Cria uma nova instância de CustomErrorResponse com o erro e a mensagem fornecidos.
     *
     * @param error   O erro ocorrido
     * @param message A mensagem de erro
     */
    public CustomErrorResponse(final String error, final String message) {
        this.error = error;
        this.message = message;
    }

    /**
     * Define o erro ocorrido.
     *
     * @param error O erro ocorrido
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Obtém o erro ocorrido.
     *
     * @return O erro ocorrido
     */
    public String getError() {
        return error;
    }

    /**
     * Obtém a mensagem de erro.
     *
     * @return A mensagem de erro
     */
    public String getMessage() {
        return message;
    }

    /**
     * Define a mensagem de erro.
     *
     * @param message A mensagem de erro
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

