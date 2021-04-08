package com.auth.dbauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.auth.dbauth.entity.JWTBlackListTokenEntity;


@Repository
public interface JwtBlacklistTokenRepository extends JpaRepository<JWTBlackListTokenEntity, Long> {

	Optional<JWTBlackListTokenEntity> findByToken(String token);

}
