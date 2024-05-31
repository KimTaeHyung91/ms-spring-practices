package com.example.ms.application.out;

import com.example.ms.domain.StudentSubscription;
import com.example.ms.domain.StudentSubscriptionId;
import java.util.function.Consumer;

public interface UpdateStudentSubscriptionPort {

  StudentSubscription update(StudentSubscriptionId studentSubscriptionId,
      Consumer<StudentSubscription> consumer);
}
