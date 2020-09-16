package com.juli0mendes.kanbanboard.read.adapter.out;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class ReadBucketRepositoryImpl {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public ReadBucketRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<BucketDto> findAll() {

        var sql = "select uuid, position, name FROM bucket";

        return this.jdbcTemplate.query(sql, (rs, rowNum) ->
                new BucketDto(
                        UUID.fromString(rs.getString("uuid")),
                        rs.getInt("position"),
                        rs.getString("name")
                ));
    }
}
