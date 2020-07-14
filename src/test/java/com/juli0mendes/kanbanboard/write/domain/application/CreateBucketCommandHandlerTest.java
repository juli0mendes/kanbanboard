package com.juli0mendes.kanbanboard.write.domain.application;

import com.juli0mendes.kanbanboard.write.adapter.out.BucketRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreateBucketCommandHandlerTest {

    @Test
    void GIVEN_ValidCommand_MUST_DelegateToListAggregate() {

        // given : fixture
        var id = UUID.randomUUID();
        var position = 1;
        var name = "TODO";
        var command = new CreateBucketCommand(id, position, name);
        var repository = mock(BucketRepositoryImpl.class);

        // when
        var handler = new CreateBucketCommandHandler(repository);
        handler.handle(command);

        // then
        verify(repository).create(id, position, name);
    }
}