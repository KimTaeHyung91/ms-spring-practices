package com.example.ms.application.in;

import com.example.ms.domain.Student;
import com.example.ms.domain.vo.Address;
import com.example.ms.domain.vo.Age;
import com.example.ms.domain.vo.Name;
import lombok.Getter;
import lombok.Setter;

public interface RegisterStudentUseCase {

  RegisterStudentInfo register(RegisterStudentCommand command);

  @Getter
  class RegisterStudentCommand {

    private Name studentName;
    private Age age;
    private Address address;

    public RegisterStudentCommand(Name studentName, Age age, Address address) {
      this.studentName = studentName;
      this.age = age;
      this.address = address;
    }

    public Student toEntity() {
      return Student.init(this.studentName, this.age, this.address);
    }
  }

  @Getter
  @Setter
  class RegisterStudentInfo {

    private String id;

    public RegisterStudentInfo(String id) {
      this.id = id;
    }
  }
}
