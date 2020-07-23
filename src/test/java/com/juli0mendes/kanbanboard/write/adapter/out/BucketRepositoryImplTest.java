package com.juli0mendes.kanbanboard.write.adapter.out;

import com.juli0mendes.kanbanboard.write.domain.core.Bucket;
import helper.DataSourceHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BucketRepositoryImplTest extends DataSourceHelper {

    @ParameterizedTest
    @MethodSource("dataProvider")
    void GIVEN_ValidBucket_MUST_PersistOnDataBase(UUID id, int position, String name) {

        // given
        var expected = new Bucket()
                .setUuid(id)
                .setPosition(position)
                .setName(name);

        // when
        var repository = new BucketRepositoryImpl(dataSource);
        repository.create(expected);

        // then
        var actual = repository.findByUuid(id);
        assertThat(actual.get()).isEqualTo(expected);
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments(UUID.randomUUID(), 1, "TODO"),
                arguments(UUID.randomUUID(), 2, "DOING"),
                arguments(UUID.randomUUID(), 3, "DONE")
        );
    }
}
