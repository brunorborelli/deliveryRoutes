package com.engsoft.marmita.exceptions;

/**
 * Exceção que pode ser lançada para indicar problemas relacionados a regras de negócio no sistema.
 * Esta exceção estende a classe RuntimeException para permitir que seja lançada sem a necessidade de declaração explícita.
 */
public class NegocioException extends RuntimeException {

    /**
     * Construtor que recebe uma mensagem de erro para descrever a causa da exceção.
     *
     * @param message Mensagem que descreve a causa específica da exceção.
     */
    public NegocioException(String message) {
        super(message);
    }
}
