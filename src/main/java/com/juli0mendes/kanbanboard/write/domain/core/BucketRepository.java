package com.juli0mendes.kanbanboard.write.domain.core;

import org.springframework.stereotype.Repository;

public interface BucketRepository {
    void create(Bucket bucket);
}