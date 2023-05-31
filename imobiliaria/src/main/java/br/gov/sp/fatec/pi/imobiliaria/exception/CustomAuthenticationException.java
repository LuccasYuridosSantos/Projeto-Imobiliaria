package br.gov.sp.fatec.pi.imobiliaria.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Exceção personalizada para erros de autenticação personalizada.
 */
public class CustomAuthenticationException extends AuthenticationException {

  /**
   * Cria uma nova instância de CustomAuthenticationException com a mensagem fornecida.
   *
   * @param message A mensagem de erro da exceção
   */
  public CustomAuthenticationException(String message) {
    super(message);
  }

  /**
   * Cria uma nova instância de CustomAuthenticationException com a mensagem e a causa fornecidas.
   *
   * @param message A mensagem de erro da exceção
   * @param cause   A causa da exceção
   */
  public CustomAuthenticationException(String message, Throwable cause) {
    super(message, cause);
  }
}

