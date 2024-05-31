package com.example.ms.adapter.in.rest.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response<T> {

  private Result result;
  private String message;
  private T data;
  private String errorCode;

  public static <T> Response<T> ok(T data, String message) {
    return (Response<T>) Response.builder()
                                 .result(Result.OK)
                                 .data(data)
                                 .message(message)
                                 .build();
  }

  public static <T> Response<T> ok(T data) {
    return ok(data, null);
  }
}
