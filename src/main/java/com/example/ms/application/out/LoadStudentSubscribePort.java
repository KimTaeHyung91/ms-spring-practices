package com.example.ms.application.out;

import com.example.ms.domain.StudentSubscription;
import com.example.ms.domain.StudentSubscriptionId;

public interface LoadStudentSubscribePort {

  StudentSubscription load(StudentSubscriptionId studentSubscriptionId);
}
