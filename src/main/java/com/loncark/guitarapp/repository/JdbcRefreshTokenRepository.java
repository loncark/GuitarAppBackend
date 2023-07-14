package com.loncark.guitarapp.repository;

import com.loncark.guitarapp.model.RefreshToken;
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
public class JdbcRefreshTokenRepository implements RefreshTokenRepository {

    private static final String SELECT_ALL = "SELECT * from refreshtoken";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcRefreshTokenRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("refreshtoken")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public RefreshToken save(RefreshToken rToken) {
        try {
            rToken.setId(saveRefreshTokenDetails(rToken));
            return rToken;
        } catch (DuplicateKeyException e) {
            return null;
        }
    }

    @Override
    public Optional<RefreshToken> findByToken(String rToken) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(SELECT_ALL + " WHERE token = ?", this::mapRowToRefreshToken, rToken)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(RefreshToken rToken) {
        jdbcTemplate.update("DELETE FROM refreshtoken WHERE token = ?", rToken.getToken());
    }

    private RefreshToken mapRowToRefreshToken(ResultSet rs, int i) throws SQLException {
        return new RefreshToken(
                rs.getLong("id"),
                rs.getString("token"),
                rs.getDate("expiry_date").toInstant(),
                new UserInfo(rs.getLong("user_id"), "", "", "")
        );
    }

    private long saveRefreshTokenDetails(RefreshToken rToken) {
        Map<String, Object> values = new HashMap<>();

        values.put("token", rToken.getToken());
        values.put("expiry_date", rToken.getExpiryDate());
        values.put("user_info", rToken.getUserInfo());

        return simpleJdbcInsert.executeAndReturnKey(values).longValue();
    }
}
