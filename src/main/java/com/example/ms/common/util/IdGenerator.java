package com.example.ms.common.util;

public class IdGenerator {

  private IdGenerator() {
  }

  public static String generateId() {
    var ulid = new ULID();
    return ulid.nextMonotonicValue(ulid.nextValue()).toString();
  }
}
