package com.juli0mendes.kanbanboard.write.adapter.out;

import com.juli0mendes.kanbanboard.write.domain.core.Bucket;
import com.juli0mendes.kanbanboard.write.domain.core.BucketRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.UUID;

public class BucketRepositoryImpl implements BucketRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public BucketRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void create(Bucket bucket) {

        String sql = """
                INSERT INTO list(uuid, position, name)
                values (:uuid, :position, :name)""";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("uuid", bucket.getId())
                .addValue("position", bucket.getPosition())
                .addValue("name", bucket.getName());

        this.jdbcTemplate.update(sql, parameters);
    }
}