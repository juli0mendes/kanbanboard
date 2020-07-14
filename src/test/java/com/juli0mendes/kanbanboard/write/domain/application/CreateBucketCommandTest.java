package com.juli0mendes.kanbanboard.write.domain.application;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateBucketCommandTest {

    @Test
    void GIVEN_ValidData_MUST_RetrieveSameData() {

        // 1 given : fixture
        UUID id = UUID.randomUUID();
        int postion = 1;
        String name = "TODO";

        // 2 when : exercuse (execution)
        CreateBucketCommand command = new CreateBucketCommand(id, postion, name);

        // 3 then : verify
        assertThat(command.id()).isEqualTo(id);
        assertThat(command.position()).isEqualTo(postion);
        assertThat(command.name()).isEqualTo(name);
    }
}
