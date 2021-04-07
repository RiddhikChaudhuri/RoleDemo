package com.auth.dbauth.core.util;

import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class CoreUtil {

  public static boolean isNonEmpty(Collection<?> collection) {
    return (collection == null || collection.isEmpty()) ? false : true;
  }

  public static boolean isEmpty(Collection<?> collection) {
    return (collection == null || collection.isEmpty()) ? true : false;
  }

  public static boolean isNonEmpty(String str) {
    return (str == null || str.isEmpty()) ? false : true;
  }

  public static boolean isEmpty(String str) {
    return (str == null || str.isEmpty()) ? true : false;
  }

  public static boolean isNonEmpty(Object object) {
    return (object == null) ? false : true;
  }

  public static boolean isEmpty(Object object) {
    return (object == null) ? true : false;
  }

  public static boolean isNull(Object object) {
    return (object == null) ? true : false;
  }

  public static boolean isNonEmpty(Map<?, ?> map) {
    return (map == null || map.isEmpty()) ? false : true;
  }

  public static String getCurrentUserName() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userName = null;
    if (principal instanceof UserDetails) {
      userName = ((UserDetails) principal).getUsername();
    } else {
      userName = principal.toString();
    }
    return userName;
  }

  
}
