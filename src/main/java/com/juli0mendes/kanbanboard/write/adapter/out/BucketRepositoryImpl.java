package com.juli0mendes.kanbanboard.write.adapter.out;

import com.juli0mendes.kanbanboard.write.domain.core.Bucket;
import com.juli0mendes.kanbanboard.write.domain.core.BucketRepository;
import com.juli0mendes.kanbanboard.write.domain.exception.DuplicatedDataException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BucketRepositoryImpl implements BucketRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public BucketRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void create(Bucket bucket) {

        try {
            String sql = """
                INSERT INTO bucket(uuid, position, name)
                values (:uuid, :position, :name)""";

            MapSqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("uuid", bucket.getUuid())
                    .addValue("position", bucket.getPosition())
                    .addValue("name", bucket.getName());

            this.jdbcTemplate.update(sql, parameters);

        } catch (DuplicateKeyException exception) {

            var duplicatedDataException = new DuplicatedDataException("Invalid duplicated data", exception);

            var existentBucket = this.findByUuidOrPosition(bucket.getUuid(), bucket.getPosition()).get();

            if (existentBucket.getUuid().equals(bucket.getUuid()))
                duplicatedDataException.addError("id", bucket.getUuid());

            if (existentBucket.getPosition() == bucket.getPosition())
                duplicatedDataException.addError("position", bucket.getPosition());

            throw duplicatedDataException;
        }
    }

    @Override
    public Optional<Bucket> findByUuid(UUID id) {

        String sql = """
                SELECT uuid, position, name, created_at, updated_at
                FROM bucket
                WHERE uuid = :uuid
                """;

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("uuid", id);


        return jdbcTemplate.query(sql, parameters, resultSet -> {
            if (resultSet.next()) {
                return Optional.of(new Bucket()
                    .setUuid(UUID.fromString(resultSet.getString("uuid")))
                    .setPosition(resultSet.getInt("position"))
                    .setName(resultSet.getString("name"))
                    .setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                    .setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime())
                );
            }

            return Optional.empty();
        });
    }

    public Optional<Bucket> findByUuidOrPosition(UUID id, int position) {

        String sql = """
                SELECT uuid, position, name, created_at, updated_at
                FROM bucket
                WHERE uuid = :uuid OR position = :position
                """;

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("uuid", id)
                .addValue("position", position);


        return jdbcTemplate.query(sql, parameters, resultSet -> {
            if (resultSet.next()) {
                return Optional.of(new Bucket()
                        .setUuid(UUID.fromString(resultSet.getString("uuid")))
                        .setPosition(resultSet.getInt("position"))
                        .setName(resultSet.getString("name"))
                        .setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                        .setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime())
                );
            }

            return Optional.empty();
        });
    }
}