package com.auth.dbauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.auth.dbauth.entity.Privilege;

@Repository
public interface PrivilageRepository extends JpaRepository<Privilege, Long> {
    Privilege findByAuthority(String authority);
}