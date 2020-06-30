package com.juli0mendes.kanbanboard.write.domain.core;

import java.util.Optional;
import java.util.UUID;

public class ListAggregate {

    private final ListRepository repository;

    public ListAggregate(ListRepository repository) {
        this.repository = repository;
    }

    public void create(UUID id, int position, String name) {
        this.repository.create(id, position, name);
    }
}
