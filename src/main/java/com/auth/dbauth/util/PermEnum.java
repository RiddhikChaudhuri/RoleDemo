package com.auth.dbauth.util;

import java.util.HashMap;
import java.util.Map;

public enum PermEnum {

  ADD(1, "Add"), UPDATE(2, "Update"), DELETE(3, "Delete"),INSERT(4,"Insert");
  
  private Integer id;
  private String description;

  // Reverse-lookup map for getting a day from an abbreviation
  private static final Map<String, RoleEnum> lookup = new HashMap<String, RoleEnum>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  private PermEnum(Integer id, String description) {
    this.id = id;
    this.description = description;
  }

}
