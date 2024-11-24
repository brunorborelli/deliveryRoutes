package com.engsoft.marmita.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe para manipulação global de exceções lançadas pelos controladores.
 * Centraliza o tratamento de exceções específicas e define como devem ser
 * retornadas as respostas HTTP adequadas.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Trata exceções do tipo NegocioException.
     *
     * @param ex Exceção do tipo NegocioException lançada.
     * @param request Requisição HTTP em que a exceção foi lançada.
     * @return ResponseEntity contendo um objeto StandardError com detalhes do erro e status HTTP 422 (Unprocessable Entity).
     */
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<StandardError> handleNegocioException(NegocioException ex,
                                                               HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Negocio Exception", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    /**
     * Trata exceções do tipo ObjetoNaoEncontradoException.
     *
     * @param ex Exceção do tipo ObjetoNaoEncontradoException lançada.
     * @param request Requisição HTTP em que a exceção foi lançada.
     * @return ResponseEntity contendo um objeto StandardError com detalhes do erro e status HTTP 404 (Not Found).
     */
    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<StandardError> handleCustomException(ObjetoNaoEncontradoException ex,
                                                               HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "ObjetoNaoEncontrado Exception", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Trata exceções do tipo CustomException.
     *
     * @param ex Exceção do tipo CustomException lançada.
     * @param request Requisição HTTP em que a exceção foi lançada.
     * @return ResponseEntity contendo um objeto StandardError com detalhes do erro e status HTTP 400 (Bad Request).
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<StandardError> handleCustomException(CustomException ex,
                                                               HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Custom Exception", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
