package com.juli0mendes.kanbanboard.write.domain.application;

import com.juli0mendes.kanbanboard.write.adapter.out.WriteBucketRepositoryImpl;
import com.juli0mendes.kanbanboard.write.domain.core.Bucket;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreateBucketCommandHandlerTest {

    ArgumentCaptor<Bucket> captor = ArgumentCaptor.forClass(Bucket.class);

    @Test
    void GIVEN_ValidCommand_MUST_DelegateToListAggregate() {

        // given : fixture
        var id = UUID.randomUUID();
        var position = 1;
        var name = "TODO";
        var command = new CreateBucketCommand(id, position, name);
        var repository = mock(WriteBucketRepositoryImpl.class);

        // when
        var handler = new CreateBucketCommandHandler(repository);
        handler.handle(command);

        // then
        verify(repository).create(captor.capture());
        var bucket = captor.getValue();
        assertThat(bucket.getUuid()).isEqualTo(id);
        assertThat(bucket.getPosition()).isEqualTo(position);
        assertThat(bucket.getName()).isEqualTo(name);
    }
}