package com.example.ms.application.in;

import com.example.ms.domain.StudentId;
import java.time.ZonedDateTime;
import lombok.Builder;

public interface RetrieveStudentUseCase {

  RetrieveStudentInfo retrieveStudent(StudentId studentId);

  @Builder
  record RetrieveStudentInfo(String studentName, Long age, String address,
                             ZonedDateTime createdAt) {}
}
