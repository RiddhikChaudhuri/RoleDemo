package com.auth.dbauth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.auth.dbauth.entity.AuthUser;

@Repository
public interface UserRepository extends JpaRepository<AuthUser, Long> {

	
	public Optional<AuthUser> findByUsername(String userName);

	
	Optional<AuthUser> findByEmail(String email);
}