package com.auth.dbauth.repository;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.auth.dbauth.entity.AuthUser;

public interface UserRepository extends JpaRepository<AuthUser, Long> {

	
	public Optional<AuthUser> findByUserName(String userName);

	@Modifying
	@Transactional
    @Query("update xflow_user u set u.password = :password where u.username = :userEmail")
    void updateuserPassword(@Param("password") String password, @Param("userEmail") String userEmail);

}