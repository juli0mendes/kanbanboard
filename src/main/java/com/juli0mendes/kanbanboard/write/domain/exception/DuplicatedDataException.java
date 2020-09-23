package com.juli0mendes.kanbanboard.write.domain.exception;

public class DuplicatedDataException extends DomainException {

    public DuplicatedDataException(Error error, Throwable throwable) {
        super(error, throwable);
    }
}
