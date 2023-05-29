package br.gov.sp.fatec.pi.imobiliaria.exception;

public class ImovelException extends RuntimeException {

  private ImovelException(String message) {
    super(message);
  }


  public static ImovelException proprietarioNaoEncontrada(final Long codProprietario) {
    return new ImovelException(String.format("Proprietario não encontrada", codProprietario));
  }

  public static ImovelException locatarioNaoEncontrada(final Long codLocatario) {
    return new ImovelException(String.format("Locatario não encontrada", codLocatario));
  }

  public static ImovelException imobiliariaNaoEncontrada(final Long codImobiliaria) {
    return new ImovelException(String.format("Imobiliaria não encontrada", codImobiliaria));
  }
}
