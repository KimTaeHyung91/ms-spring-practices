package com.example.ms.application.in;

import com.example.ms.domain.StudentSubscriptionId;

public interface CancelSubscribeSchoolPageUseCase {

  CancelSubscribeSchoolPageInfo cancelSubscribeSchoolPage(
      StudentSubscriptionId studentSubscriptionId);

  record CancelSubscribeSchoolPageInfo(String cancelSubscribeAt) {}
}
