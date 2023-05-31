package br.gov.sp.fatec.pi.imobiliaria.exception;

/**
 * Exceção personalizada para erros relacionados à entidade Imobiliaria.
 */
public class ImobiliariaException extends RuntimeException {

  /**
   * Cria uma nova instância de ImobiliariaException com a mensagem fornecida.
   *
   * @param message A mensagem de erro da exceção
   */
  private ImobiliariaException(String message) {
    super(message);
  }

  /**
   * Cria uma nova instância de ImobiliariaException para o caso em que a imobiliária não é encontrada pelo código.
   *
   * @param codImobiliaria O código da imobiliária não encontrada
   * @return Uma instância de ImobiliariaException com a mensagem de erro apropriada
   */
  public static ImobiliariaException imobiliariaNaoEncontrada(final Long codImobiliaria) {
    return new ImobiliariaException(String.format("Imobiliaria com id %s não encontrada", codImobiliaria));
  }

  /**
   * Cria uma nova instância de ImobiliariaException para o caso em que o corretor não é encontrado pelo código.
   *
   * @param codCorretor O código do corretor não encontrado
   * @return Uma instância de ImobiliariaException com a mensagem de erro apropriada
   */
  public static ImobiliariaException corretorNaoEncontrado(final Long codCorretor) {
    return new ImobiliariaException(String.format("Corretor com id %s não encontrado", codCorretor));
  }
}
