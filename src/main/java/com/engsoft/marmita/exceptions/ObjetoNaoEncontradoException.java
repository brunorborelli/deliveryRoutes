package com.engsoft.marmita.exceptions;

/**
 * Exceção que pode ser lançada para indicar que um objeto específico não foi encontrado no sistema.
 * Esta exceção estende a classe RuntimeException para permitir que seja lançada sem a necessidade de declaração explícita.
 */
public class ObjetoNaoEncontradoException extends RuntimeException {

    /**
     * Construtor que recebe uma mensagem de erro para descrever a causa da exceção.
     *
     * @param message Mensagem que descreve a causa específica da exceção.
     */
    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }
}
