package com.juli0mendes.kanbanboard.write.domain.core;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface BucketRepository {
    void create(Bucket bucket);

    Optional<Bucket> findByUuid(UUID id);
}