package com.example.ms.application.in;

import com.example.ms.domain.SchoolPageId;
import com.example.ms.domain.StudentId;
import com.example.ms.domain.StudentSubscription;

public interface SubscribeSchoolPageUseCase {

  SubscribeSchoolPageInfo subscribeSchoolPage(SubscribeSchoolPageCommand command);

  record SubscribeSchoolPageCommand(StudentId studentId, SchoolPageId schoolPageId) {

    public StudentSubscription toEntity() {
      return StudentSubscription.init();
    }
  }


  record SubscribeSchoolPageInfo(String studentSubscriptionId) {

  }
}
