package com.joymain.jecs.util;

import java.util.UUID;

/**
 * 
 * @author houxyu
 *
 */
public class GuidHelper{

  public static String genRandomGUID() {
    return getUuidStr();
  }

  public static String genRandomGUID(boolean secure) {
    return getUuidStr();
  }

  public static String getUuidStr() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }


  public static void main(String args[]) {
    for (int i = 1; i < 10; i++) {
//      String uid = UUID.randomUUID().toString();
      // uid = uid.replaceAll("-","");
      System.out.println(genRandomGUID());
    }
  }
}