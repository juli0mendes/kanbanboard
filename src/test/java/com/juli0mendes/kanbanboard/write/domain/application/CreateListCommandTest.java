package com.juli0mendes.kanbanboard.write.domain.application;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateListCommandTest {

    @Test
    void GIVEN_ValidData_MUST_RetrieveSameData() {

        // 1 given : fixture
        UUID id = UUID.randomUUID();
        int postion = 1;
        String name = "TODO";

        // 2 when : exercuse (execution)
        CreateListCommand command = new CreateListCommand(id, postion, name);

        // 3 then : verify
        assertThat(command.getId()).isEqualTo(id);
        assertThat(command.getPosition()).isEqualTo(postion);
        assertThat(command.getName()).isEqualTo(name);
    }
}
