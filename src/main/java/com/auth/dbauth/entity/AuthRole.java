package com.auth.dbauth.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRole {
  
  @Id
  @Column(name = "roleId")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  
  private boolean isAdmin;


  public Integer getId() {
    return id;
  }

}
