package com.example.ms.adapter.in.rest;

import lombok.Getter;
import lombok.Setter;

public class StudentRestDto {

  @Getter
  @Setter
  public static class RequestRegisterStudent {

    private String studentName;

    private Integer age;

    private String city;

    private String roadAddress;

    private String numberingAddress;
  }

  public record RequestSubscribeSchoolPage(String studentId, String schoolPageId) {}

  public record RequestCancelSubscribeSchoolPage(String studentSubscriptionId) {}

}
