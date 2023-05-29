package br.gov.sp.fatec.pi.imobiliaria.exception;

public class ImobiliariaException extends RuntimeException{

  private ImobiliariaException(String message) {
    super(message);
  }

  public static ImobiliariaException imobiliariaNaoEncontrada(final Long codImobiliaria) {
    return new ImobiliariaException(String.format("Imobiliaria com id %s não encontrada", codImobiliaria));
  }

  public static ImobiliariaException corretorNaoEncontrado(final Long codCorretor) {
    return new ImobiliariaException(String.format("Corretor com id %s não encontrado", codCorretor));
  }
}
