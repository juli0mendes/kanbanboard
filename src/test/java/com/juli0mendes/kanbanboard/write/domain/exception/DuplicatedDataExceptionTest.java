package com.juli0mendes.kanbanboard.write.domain.exception;

import org.junit.jupiter.api.Test;

import static com.juli0mendes.kanbanboard.write.domain.exception.DomainException.Error.INVALID_DUPLICATED_DATA;
import static org.assertj.core.api.Assertions.assertThat;

public class DuplicatedDataExceptionTest {

    @Test
    void duplicatedDataException_MUST_extend_domainException() {

        // given
        var exception = new DuplicatedDataException(INVALID_DUPLICATED_DATA, new RuntimeException());

        // then
        assertThat(exception).isInstanceOf(DomainException.class);
    }
}
