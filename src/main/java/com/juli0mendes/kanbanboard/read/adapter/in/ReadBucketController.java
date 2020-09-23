package com.juli0mendes.kanbanboard.read.adapter.in;

import com.juli0mendes.kanbanboard.read.adapter.out.BucketDto;
import com.juli0mendes.kanbanboard.read.adapter.out.ReadBucketRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/buckets")
public class ReadBucketController {

    @Autowired
    private ReadBucketRepositoryImpl repository;

    @GetMapping()
    public ResponseEntity<List<BucketDto>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }
}
