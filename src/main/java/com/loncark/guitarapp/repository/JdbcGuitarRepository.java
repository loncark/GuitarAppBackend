package com.loncark.guitarapp.repository;

import com.loncark.guitarapp.model.Guitar;
import com.loncark.guitarapp.model.Material;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Primary
@Repository
public class JdbcGuitarRepository implements GuitarRepository {

    private static final String TABLE_NAME = "guitar";
    private static final String GENERATED_KEY_COLUMN = "id";

    private static final String SELECT_ALL = "SELECT * from guitar";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcGuitarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(GENERATED_KEY_COLUMN);
    }

    @Override
    public Set<Guitar> findAll() {
        return Set.copyOf(jdbcTemplate.query(SELECT_ALL, this::mapRowToGuitar));
    }

    @Override
    public Optional<Guitar> findByCode(String code) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(SELECT_ALL + " WHERE code = ?", this::mapRowToGuitar, code)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(String code) {
        jdbcTemplate.update("DELETE FROM guitar WHERE code = ?", code);
    }

    @Override
    public Optional<Guitar> save(Guitar guitar) {
        try {
            guitar.setId(saveGuitarDetails(guitar));
            return Optional.of(guitar);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Guitar> update(Guitar updatedGuitar) {
        int executed = jdbcTemplate.update("UPDATE guitar set " +
                        "name = ?, " +
                        "neck = ?, " +
                        "body = ?, " +
                        "stock = ?, " +
                        "price = ?, " +
                        "code = ?" +
                        "WHERE id = ?",
                updatedGuitar.getName(),
                updatedGuitar.getNeck().toString(),
                updatedGuitar.getBody().toString(),
                updatedGuitar.getStock(),
                updatedGuitar.getPrice(),
                updatedGuitar.getCode(),
                updatedGuitar.getId()
        );

        return executed > 0 ? Optional.of(updatedGuitar) : Optional.empty();
    }

    private Guitar mapRowToGuitar(ResultSet rs, int rowNum) throws SQLException {
        return new Guitar(
                rs.getLong("id"),
                Material.valueOf(rs.getString("body")),
                Material.valueOf(rs.getString("neck")),
                rs.getString("name"),
                rs.getBigDecimal("price"),
                rs.getLong("stock"),
                rs.getString("code")
        );
    }

    private long saveGuitarDetails(Guitar guitar) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", guitar.getName());
        values.put("neck", guitar.getNeck());
        values.put("body", guitar.getBody());
        values.put("code", guitar.getCode());
        values.put("stock", guitar.getStock());
        values.put("price", guitar.getPrice());

        return simpleJdbcInsert.executeAndReturnKey(values).longValue();
    }
}
