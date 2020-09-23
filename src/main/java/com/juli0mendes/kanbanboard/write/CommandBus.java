package com.juli0mendes.kanbanboard.write;

import com.juli0mendes.kanbanboard.write.domain.application.Command;
import com.juli0mendes.kanbanboard.write.domain.application.Handler;
import com.juli0mendes.kanbanboard.write.event.CommandEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CommandBus {

    private ApplicationContext context;
    private ApplicationEventPublisher publisher;

    public CommandBus(ApplicationContext context, ApplicationEventPublisher publisher) {
        this.context = context;
        this.publisher = publisher;
    }

    public void execute(Command command) {
        CommandEvent event = new CommandEvent(this, command);

        try {
            this.handle(command);
        } catch (Exception exception) {
            event.setException(exception);
            throw exception;
        } finally {
            this.publisher.publishEvent(event);
        }
    }

    private void handle(Command command) {
        String handlerName = String.format("%sHandler", command.getClass().getSimpleName());
        String handlerBeanName = Character.toLowerCase(handlerName.charAt(0)) + handlerName.substring(1);
        Handler handler = (Handler) this.context.getBean(handlerBeanName);
        handler.handle(command);
    }
}
