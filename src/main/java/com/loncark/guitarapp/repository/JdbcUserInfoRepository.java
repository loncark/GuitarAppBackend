package com.loncark.guitarapp.repository;

import com.loncark.guitarapp.model.UserInfo;
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

@Primary
@Repository
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

    @Override
    public Optional<UserInfo> findById(String id) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(SELECT_ALL + " WHERE id = ?", this::mapRowToUserInfo, id)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserInfo> save(UserInfo userInfo) {
        try {
            userInfo.setId(saveUserInfoDetails(userInfo));
            return Optional.of(userInfo);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
    }

    private Long saveUserInfoDetails(UserInfo userInfo) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", userInfo.getName());
        values.put("password", userInfo.getPassword());
        values.put("roles", userInfo.getRoles());

        return simpleJdbcInsert.executeAndReturnKey(values).longValue();
    }

    private UserInfo mapRowToUserInfo(ResultSet rs, int rowNum) throws SQLException {
        return new UserInfo(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getString("roles")
        );
    }
}
