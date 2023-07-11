package com.loncark.guitarapp.repository;

import com.loncark.guitarapp.model.UserInfo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcUserInfoRepository implements UserInfoRepository {

    private static final String SELECT_ALL = "SELECT * from userinfo";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcUserInfoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("userinfo")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Optional<UserInfo> findByName(String name) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(SELECT_ALL + " WHERE name = ?", this::mapRowToUserInfo, name)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private UserInfo mapRowToUserInfo(ResultSet rs, int rowNum) throws SQLException {
        return new UserInfo(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("password")
        );
    }
}
