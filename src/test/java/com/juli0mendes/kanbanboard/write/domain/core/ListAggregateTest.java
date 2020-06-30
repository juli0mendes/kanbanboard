package com.juli0mendes.kanbanboard.write.domain.core;

import com.juli0mendes.kanbanboard.write.adapter.out.ListRepositoryImpl;
import helper.DataSourceHelper;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ListAggregateTest extends DataSourceHelper {

    @Test
    void GIVEN_ValidData_MUST_PersistInDatabase() {

        // given
        UUID id = UUID.randomUUID();
        int position = 1;
        String name = "TODO";
        ListRepository repository = new ListRepositoryImpl(dataSource);

        // when
        ListAggregate listAggregate = new ListAggregate(repository);
        listAggregate.create(id, position, name);

        // then
//        verify(repository).create(id, position, name);

        assertThat(repository.hasList(id)).isTrue();
    }
}
