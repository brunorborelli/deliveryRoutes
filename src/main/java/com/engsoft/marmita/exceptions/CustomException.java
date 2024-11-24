package com.engsoft.marmita.exceptions;

/**
 * Exceção personalizada que pode ser lançada para indicar erros específicos no sistema.
 * Esta exceção estende a classe RuntimeException para permitir que seja lançada sem a necessidade de declaração explícita.
 */
public class CustomException extends RuntimeException {

    /**
     * Construtor que recebe uma mensagem de erro para descrever a causa da exceção.
     *
     * @param message Mensagem que descreve a causa específica da exceção.
     */
    public CustomException(String message) {
        super(message);
    }
}
