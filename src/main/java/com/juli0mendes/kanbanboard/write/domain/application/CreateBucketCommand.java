package com.juli0mendes.kanbanboard.write.domain.application;

import java.util.UUID;

public record CreateBucketCommand(
        UUID id,
        Integer position,
        String name) {
}