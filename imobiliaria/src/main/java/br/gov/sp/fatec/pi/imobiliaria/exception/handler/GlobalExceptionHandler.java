package br.gov.sp.fatec.pi.imobiliaria.exception.handler;

import br.gov.sp.fatec.pi.imobiliaria.exception.AgendamentoException;
import br.gov.sp.fatec.pi.imobiliaria.exception.CustomErrorResponse;
import br.gov.sp.fatec.pi.imobiliaria.exception.UsuarioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(Exception ex) {
        CustomErrorResponse error = new CustomErrorResponse("Erro interno", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AgendamentoException.class)
    public ResponseEntity<CustomErrorResponse> handleAgendamentoException(AgendamentoException ex) {
        CustomErrorResponse error = new CustomErrorResponse("Erro de agendamento", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<CustomErrorResponse> handleAgendamentoException(UsuarioException ex) {
        CustomErrorResponse error = new CustomErrorResponse("Erro de usuario", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
