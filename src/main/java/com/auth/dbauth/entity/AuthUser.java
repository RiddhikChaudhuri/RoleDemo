package com.auth.dbauth.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class AuthUser {

  @Id
  @Column(name = "userid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  
  private String fullname;
  
  private String password;
  private boolean enabled;
  
  private String email;

   
  @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "userid"),
      inverseJoinColumns = @JoinColumn(name = "roleId"))
  private List<AuthRole> roles = new ArrayList<>();

  public Long getId() {
      return id;
  }

}
