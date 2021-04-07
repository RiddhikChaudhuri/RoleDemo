package com.auth.dbauth.util;

import java.util.HashMap;
import java.util.Map;

public enum RoleEnum {

  ADMIN(1, "Admin"), MANAGER(2, "Manager"), REP(3, "Representative");

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

  private RoleEnum(Integer id, String description) {
    this.id = id;
    this.description = description;
  }

  public class Code {
    public static final String ADMIN = "Admin";
    public static final String MANAGER = "Manager";
    public static final String REPRESENTATIVE = "Representative";

  }

}
