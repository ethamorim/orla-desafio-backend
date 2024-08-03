package com.ethamorim.orlachallengebackend.exception;

/**
 * Exceção utilizada para indicar que nenhum registro foi encontrado,
 * dado um id ou outro atributo identificador.
 *
 * @author ethamorim
 */
public class NoRecordFoundException extends RuntimeException {
    public NoRecordFoundException(String message) {
        super(message);
    }
}
