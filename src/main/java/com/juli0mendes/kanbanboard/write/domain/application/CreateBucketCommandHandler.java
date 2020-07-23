package com.juli0mendes.kanbanboard.write.domain.application;

import com.juli0mendes.kanbanboard.write.domain.core.Bucket;
import com.juli0mendes.kanbanboard.write.domain.core.BucketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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