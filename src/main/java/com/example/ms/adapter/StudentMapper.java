package com.example.ms.adapter;

import com.example.ms.adapter.in.rest.StudentRestDto;
import com.example.ms.adapter.out.persistence.entity.StudentEntity;
import com.example.ms.application.in.RegisterStudentUseCase;
import com.example.ms.application.in.SubscribeSchoolPageUseCase;
import com.example.ms.common.annotation.Mapper;
import com.example.ms.domain.SchoolPageId;
import com.example.ms.domain.Student;
import com.example.ms.domain.StudentId;
import com.example.ms.domain.vo.Address;
import com.example.ms.domain.vo.Age;
import com.example.ms.domain.vo.Name;

@Mapper
public class StudentMapper {

  public RegisterStudentUseCase.RegisterStudentCommand mapFromDtoToCommand(
      StudentRestDto.RequestRegisterStudent dto) {
    var name = new Name(dto.getStudentName());
    var age = new Age(dto.getAge().longValue());
    var address = new Address(dto.getCity(), dto.getRoadAddress(), dto.getNumberingAddress());

    return new RegisterStudentUseCase.RegisterStudentCommand(name, age, address);
  }

  public SubscribeSchoolPageUseCase.SubscribeSchoolPageCommand mapFromDtoToCommand(
      StudentRestDto.RequestSubscribeSchoolPage dto) {
    return new SubscribeSchoolPageUseCase.SubscribeSchoolPageCommand(new StudentId(dto.studentId()),
                                                                     new SchoolPageId(dto.schoolPageId()));
  }

  public StudentEntity mapFromDomainToEntity(Student student) {
    return StudentEntity.builder()
                        .id(student.getId().value())
                        .age(student.getAge().value())
                        .studentName(student.getName().value())
                        .city(student.getAddress().city())
                        .roadAddress(student.getAddress().road())
                        .numberingAddress(student.getAddress().numbering())
                        .build();
  }

  public Student mapFromEntityToDomain(StudentEntity entity) {
    return Student.of(
        new StudentId(entity.getId()),
        new Name(entity.getStudentName()),
        new Age(entity.getAge()),
        new Address(
            entity.getCity(),
            entity.getRoadAddress(),
            entity.getNumberingAddress())
    );
  }
}
