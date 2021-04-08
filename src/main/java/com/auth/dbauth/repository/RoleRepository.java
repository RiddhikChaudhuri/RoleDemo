package com.auth.dbauth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.auth.dbauth.entity.AuthRole;

@Repository
public interface RoleRepository extends JpaRepository<AuthRole, Integer>{

  public Optional<AuthRole> findByName(String roleName);
  
  //public Set<AuthRole> findAllById(Set<Integer> idList);


}
