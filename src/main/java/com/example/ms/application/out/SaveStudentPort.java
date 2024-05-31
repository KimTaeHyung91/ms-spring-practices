package com.example.ms.application.out;

import com.example.ms.domain.SchoolPageId;
import com.example.ms.domain.Student;
import com.example.ms.domain.StudentId;
import com.example.ms.domain.StudentSubscription;
import com.example.ms.domain.StudentSubscriptionId;

public interface SaveStudentPort {

  StudentId save(Student domain);

  StudentSubscriptionId save(StudentId studentId, SchoolPageId schoolPageId,
      StudentSubscription domain);
}
