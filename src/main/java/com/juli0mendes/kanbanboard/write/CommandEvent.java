package com.juli0mendes.kanbanboard.write;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juli0mendes.kanbanboard.write.domain.application.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandEvent {

    private final Command command;
    private Exception exception;

    public CommandEvent(Object source, Command command) {
        this.command = command;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String toJson() {

        var mapper = new ObjectMapper();

        try {

            Map<String, String> message = Map.of("content", command.toString());

            if (exception != null) {
                message.put("error", exception.getMessage());
            }

            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
