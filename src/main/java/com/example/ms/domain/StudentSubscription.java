package com.example.ms.domain;


import com.example.ms.common.util.IdGenerator;
import com.example.ms.domain.vo.Date;
import java.time.ZonedDateTime;
import java.util.Arrays;
import lombok.Getter;

@Getter
public class StudentSubscription {

  private StudentSubscriptionId studentSubscriptionId;
  private Date subscribeAt;
  private Date cancelSubscribeAt;
  private StudentSubscriptionStatus status;

  public enum StudentSubscriptionStatus {
    ACTIVE, IN_ACTIVE;

    public static StudentSubscriptionStatus mapFromEntityEnum(String status) {
      return Arrays.stream(StudentSubscriptionStatus.values())
                   .filter((value) -> value.name().equals(status))
                   .findFirst()
                   .orElseThrow();
    }
  }

  private StudentSubscription(StudentSubscriptionId studentSubscriptionId) {
    this.studentSubscriptionId = studentSubscriptionId;
  }

  private StudentSubscription(StudentSubscriptionId studentSubscriptionId, Date subscribeAt,
      Date cancelSubscribeAt,
      StudentSubscriptionStatus status) {
    this.studentSubscriptionId = studentSubscriptionId;
    this.subscribeAt = subscribeAt;
    this.cancelSubscribeAt = cancelSubscribeAt;
    this.status = status;
  }

  public static StudentSubscription init() {
    return new StudentSubscription(new StudentSubscriptionId(IdGenerator.generateId()));
  }

  public static StudentSubscription of(StudentSubscriptionId studentSubscriptionId,
      Date subscribeAt,
      Date cancelSubscribeAt,
      StudentSubscriptionStatus status) {
    return new StudentSubscription(studentSubscriptionId, subscribeAt, cancelSubscribeAt, status);
  }

  public void startSubscribe() {
    this.subscribeAt = new Date(ZonedDateTime.now());
    this.status = StudentSubscriptionStatus.ACTIVE;
  }

  public void cancelSubscribe() {

    this.cancelSubscribeAt = new Date(ZonedDateTime.now());
    this.status = StudentSubscriptionStatus.IN_ACTIVE;
  }

}
