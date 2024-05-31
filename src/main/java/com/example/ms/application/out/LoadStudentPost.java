package com.example.ms.application.out;

import com.example.ms.domain.Student;
import com.example.ms.domain.StudentId;

public interface LoadStudentPost {

  Student load(StudentId studentId);
}
