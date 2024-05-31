package com.example.ms.adapter.in.rest;

public class SchoolPageRestDto {

  public record RequestCreateSchoolPage(
      String name,
      String city,
      String roadAddress,
      String numberingAddress
  ) {}
}
