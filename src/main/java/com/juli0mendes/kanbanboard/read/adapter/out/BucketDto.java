package com.juli0mendes.kanbanboard.read.adapter.out;

import java.util.UUID;

public record BucketDto(UUID uuid, int position, String name){
}
