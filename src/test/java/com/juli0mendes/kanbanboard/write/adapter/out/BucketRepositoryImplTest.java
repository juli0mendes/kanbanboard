package com.juli0mendes.kanbanboard.write.adapter.out;

import com.juli0mendes.kanbanboard.write.domain.core.Bucket;
import com.juli0mendes.kanbanboard.write.domain.core.BucketRepository;
import com.juli0mendes.kanbanboard.write.domain.exception.DuplicatedDataException;
import helper.DataSourceHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BucketRepositoryImplTest extends DataSourceHelper {

    WriteBucketRepositoryImpl repository;

    @BeforeEach
    void setup() {
        repository = new WriteBucketRepositoryImpl(dataSource);
    }

    @Test
    void MUST_ImplementInterface() {
        assertThat(repository).isInstanceOf(BucketRepository.class);
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
    void GIVEN_AlreadyExistentBucket_MUST_ThrowException(UUID id,
                                                         int position,
                                                         String name,
                                                         Map<String, Object> expectedError) {

        // given
        var expected = new Bucket()
                .setUuid(id)
                .setPosition(position)
                .setName(name);

        // then
        DuplicatedDataException exception = assertThrows(DuplicatedDataException.class, () -> repository.create(expected));

        // when
        assertThat(exception.getMessage()).isEqualTo("Invalid duplicated data");
        assertThat(exception.getErrors()).containsExactlyInAnyOrderEntriesOf(expectedError);
    }

    private static Stream<Arguments> validDataProvider() {
        return Stream.of(
                arguments(UUID.randomUUID(), 1, "TODO"),
                arguments(UUID.randomUUID(), 2, "EXISTENT")
        );
    }

    private static Stream<Arguments> invalidDataProvider() {

        UUID existentUuid = UUID.fromString("8d5732c8-cc85-11ea-87d0-0242ac130003");
        int existentPosition = 100;

        return Stream.of(
                arguments(existentUuid, 1, "TODO", Map.of("id", existentUuid)),
                arguments(UUID.randomUUID(), existentPosition, "DOING", Map.of("position", existentPosition)),
                arguments(existentUuid, existentPosition, "WHATEVER", Map.of("id", existentUuid, "position", Integer.valueOf(existentPosition)))
                );
    }
}
