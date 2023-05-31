package br.gov.sp.fatec.pi.imobiliaria.exception;

/**
 * Exceção personalizada para erros relacionados à entidade Imovel.
 */
public class ImovelException extends RuntimeException {

  /**
   * Cria uma nova instância de ImovelException com a mensagem fornecida.
   *
   * @param message A mensagem de erro da exceção
   */
  private ImovelException(String message) {
    super(message);
  }

  /**
   * Cria uma nova instância de ImovelException para o caso em que o proprietário não é encontrado pelo código.
   *
   * @param codProprietario O código do proprietário não encontrado
   * @return Uma instância de ImovelException com a mensagem de erro apropriada
   */
  public static ImovelException proprietarioNaoEncontrada(final Long codProprietario) {
    return new ImovelException(String.format("Proprietario não encontrada", codProprietario));
  }

  /**
   * Cria uma nova instância de ImovelException para o caso em que o locatário não é encontrado pelo código.
   *
   * @param codLocatario O código do locatário não encontrado
   * @return Uma instância de ImovelException com a mensagem de erro apropriada
   */
  public static ImovelException locatarioNaoEncontrada(final Long codLocatario) {
    return new ImovelException(String.format("Locatario não encontrada", codLocatario));
  }

  /**
   * Cria uma nova instância de ImovelException para o caso em que a imobiliária não é encontrada pelo código.
   *
   * @param codImobiliaria O código da imobiliária não encontrada
   * @return Uma instância de ImovelException com a mensagem de erro apropriada
   */
  public static ImovelException imobiliariaNaoEncontrada(final Long codImobiliaria) {
    return new ImovelException(String.format("Imobiliaria não encontrada", codImobiliaria));
  }
}

