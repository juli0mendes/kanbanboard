package com.juli0mendes.kanbanboard.write.domain.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BucketTest {

    @Test
    void GIVEN_ValidData_MUST_RetrieveSameDate() {

        // given
        var id = 1;
        var uuid = UUID.randomUUID();
        var position = 1;
        var name = "TODO";
        var createdAt = LocalDateTime.now();
        var updatedAt = LocalDateTime.now();

        // when
        var bucket = new Bucket()
                .setId(id)
                .setUuid(uuid)
                .setName(name)
                .setPosition(position);

        // then
        assertThat(bucket.getId()).isEqualTo(id);
        assertThat(bucket.getUuid()).isEqualTo(uuid);
        assertThat(bucket.getPosition()).isEqualTo(position);
        assertThat(bucket.getName()).isEqualTo(name);
    }
}
