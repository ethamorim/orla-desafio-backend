package com.ethamorim.orlachallengebackend.exception;

public class NoRecordWithGivenId extends RuntimeException {
    public NoRecordWithGivenId(String message) {
        super(message);
    }
}
