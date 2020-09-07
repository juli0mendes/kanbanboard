package com.juli0mendes.kanbanboard.write.domain.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DuplicatedDataExceptionTest {

    @Test
    void duplicatedDataException_MUST_extend_domainException() {

        // given
        var exception = new DuplicatedDataException("Test", new RuntimeException());

        // then
        assertThat(exception).isInstanceOf(DomainException.class);
    }
}
