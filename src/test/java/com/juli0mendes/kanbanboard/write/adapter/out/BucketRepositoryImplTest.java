package com.juli0mendes.kanbanboard.write.adapter.out;

import com.juli0mendes.kanbanboard.write.domain.exception.DuplicatedDataException;
import com.juli0mendes.kanbanboard.write.domain.core.Bucket;
import com.juli0mendes.kanbanboard.write.domain.core.BucketRepository;
import helper.DataSourceHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BucketRepositoryImplTest extends DataSourceHelper {

    BucketRepository repository;

    @BeforeEach
    void setup() {
        repository = new BucketRepositoryImpl(dataSource);
    }

    @ParameterizedTest
    @MethodSource("validDataProvider")
    void GIVEN_ValidBucket_MUST_PersistOnDataBase(UUID id, int position, String name) {

        // given
        var expected = new Bucket()
                .setUuid(id)
                .setPosition(position)
                .setName(name);

        // when
        repository.create(expected);

        // then
        var actualOptional = repository.findByUuid(id);

        Bucket actual = actualOptional.get();

        assertThat(actual.getUuid()).isEqualTo(expected.getUuid());
        assertThat(actual.getPosition()).isEqualTo(expected.getPosition());
        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getCreatedAt()).isNotNull();
        assertThat(actual.getUpdatedAt()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("invalidDataProvider")
    void GIVEN_AlreadyExistentBucket_MUST_ThrowException(UUID id, int position, String name, String errorField) {

        // given
        var expected = new Bucket()
                .setUuid(id)
                .setPosition(position)
                .setName(name);

        // then
        DuplicatedDataException exception = assertThrows(DuplicatedDataException.class, () -> repository.create(expected));

        // when
        assertThat(exception.getMessage()).isEqualTo("Invalid duplicated data: " + errorField);
    }

    private static Stream<Arguments> validDataProvider() {
        return Stream.of(
                arguments(UUID.randomUUID(), 1, "TODO"),
                arguments(UUID.randomUUID(), 2, "EXISTENT")
        );
    }

    private static Stream<Arguments> invalidDataProvider() {
        return Stream.of(
                arguments(UUID.fromString("8d5732c8-cc85-11ea-87d0-0242ac130003"), 1, "TODO", "id"),
                arguments(UUID.randomUUID(), 100, "DOING", "position")
        );
    }
}
