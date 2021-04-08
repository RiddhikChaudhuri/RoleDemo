package com.auth.dbauth.server.dto;

import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import com.auth.dbauth.entity.AuthRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRoleDTO {

  @Email
  @NotBlank
  private String email;

  private List<AuthRole> roles;
  
  
}
