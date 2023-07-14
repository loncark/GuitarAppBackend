package com.loncark.guitarapp.repository;

import com.loncark.guitarapp.model.RefreshToken;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken rToken);

    Optional<RefreshToken> findByToken(String rToken);

    void delete(RefreshToken rToken);
}
