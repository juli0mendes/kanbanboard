package com.juli0mendes.kanbanboard.write.domain.application;

import com.juli0mendes.kanbanboard.write.adapter.out.BucketRepositoryImpl;
import com.juli0mendes.kanbanboard.write.domain.core.Bucket;
import com.juli0mendes.kanbanboard.write.domain.core.BucketRepository;

public class CreateBucketCommandHandler {

    private final BucketRepository repository;

    public CreateBucketCommandHandler(BucketRepository repository) {
        this.repository = repository;
    }

    public void handle(CreateBucketCommand command) {

        var bucket = new Bucket()
                .setUuid(command.id())
                .setPosition(command.position())
                .setName(command.name());

        this.repository.create(bucket);
    }
}