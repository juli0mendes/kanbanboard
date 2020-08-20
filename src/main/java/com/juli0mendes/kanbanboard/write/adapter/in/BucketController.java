package com.juli0mendes.kanbanboard.write.adapter.in;

import com.juli0mendes.kanbanboard.write.domain.application.CreateBucketCommand;
import com.juli0mendes.kanbanboard.write.domain.application.CreateBucketCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/buckets")
public class BucketController {

    @Autowired
    private CreateBucketCommandHandler handler;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Validated  @RequestBody CreateBucketCommand command) throws URISyntaxException {
        handler.handle(command);
        return ResponseEntity.created(new URI("")).build();
    }
}