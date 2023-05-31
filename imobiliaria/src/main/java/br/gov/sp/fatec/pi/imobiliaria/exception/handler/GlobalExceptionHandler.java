package br.gov.sp.fatec.pi.imobiliaria.exception.handler;

import br.gov.sp.fatec.pi.imobiliaria.exception.AgendamentoException;
import br.gov.sp.fatec.pi.imobiliaria.exception.CustomErrorResponse;
import br.gov.sp.fatec.pi.imobiliaria.exception.UsuarioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe responsável por lidar com exceções globais na aplicação e fornecer
 * respostas personalizadas com base no tipo de exceção.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manipula exceções genéricas e retorna uma resposta de erro interno do servidor.
     *
     * @param ex A exceção ocorrida
     * @return A resposta personalizada de erro interno do servidor
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(Exception ex) {
        CustomErrorResponse error = new CustomErrorResponse("Erro interno", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Manipula exceções relacionadas a agendamento e retorna uma resposta de erro de solicitação inválida.
     *
     * @param ex A exceção de AgendamentoException ocorrida
     * @return A resposta personalizada de erro de solicitação inválida
     */
    @ExceptionHandler(AgendamentoException.class)
    public ResponseEntity<CustomErrorResponse> handleAgendamentoException(AgendamentoException ex) {
        CustomErrorResponse error = new CustomErrorResponse("Erro de agendamento", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manipula exceções relacionadas a usuários e retorna uma resposta de erro de entidade não processável.
     *
     * @param ex A exceção de UsuarioException ocorrida
     * @return A resposta personalizada de erro de entidade não processável
     */
    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<CustomErrorResponse> handleUsuarioException(UsuarioException ex) {
        CustomErrorResponse error = new CustomErrorResponse("Erro de usuário", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
