package com.juli0mendes.kanbanboard.read.adapter.out;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@Repository
public class ReadBucketRepositoryImpl {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public ReadBucketRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<BucketDto> findAll() {

        var sql = "select uuid, position, name FROM bucket ORDER BY position";

        return this.jdbcTemplate.query(sql, (rs, rowNum) ->
                new BucketDto(
                        UUID.fromString(rs.getString("uuid")),
                        rs.getInt("position"),
                        rs.getString("name")
                ));
    }
}
