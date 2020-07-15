package com.juli0mendes.kanbanboard.write.domain.core;

import java.util.UUID;

public class ListAggregate {

    private final BucketRepository repository;

    public ListAggregate(BucketRepository repository) {
        this.repository = repository;
    }

    public void create(Bucket bucket) {
        this.repository.create(bucket);
    }
}