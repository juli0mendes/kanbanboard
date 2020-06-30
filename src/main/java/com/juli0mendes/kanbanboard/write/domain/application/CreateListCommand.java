package com.juli0mendes.kanbanboard.write.domain.application;

import java.util.UUID;

public class CreateListCommand {

    private UUID id;
    private int position;
    private String name;

    public CreateListCommand(UUID id, int position, String name) {
        this.id = id;
        this.position = position;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}