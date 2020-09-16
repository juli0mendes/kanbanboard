package com.juli0mendes.kanbanboard.read.adapter.out;

import helper.DataSourceHelper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadBucketRepositoryImplTest extends DataSourceHelper {

    @Test
    void WHEN_QueryAllBucket_MUST_RetrieveSuccessful() {

        // when
        var repository = new ReadBucketRepositoryImpl(dataSource);
        List<BucketDto> actual = repository.findAll();

        // then
        var expected = new BucketDto(UUID.fromString("8d5732c8-cc85-11ea-87d0-0242ac130003"), 100, "EXISTENT");

        assertThat(actual)
                .containsExactlyInAnyOrderElementsOf(List.of(expected));
    }
}
