package com.juli0mendes.kanbanboard.write.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juli0mendes.kanbanboard.write.CommandEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class LogListener {

    Logger logger = LoggerFactory.getLogger(LogListener.class);

    @Async
    @EventListener(condition = "#event.exception == null")
    void onSuccess(CommandEvent event) {
        logger.info(event.toJson());
    }

    @Async
    @EventListener(condition = "#event.exception != null")
    void onError(CommandEvent event) {
        logger.error(event.toJson());
    }
}
