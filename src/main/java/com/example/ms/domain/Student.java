package com.example.ms.domain;

import com.example.ms.common.util.IdGenerator;
import com.example.ms.domain.vo.Address;
import com.example.ms.domain.vo.Age;
import com.example.ms.domain.vo.Name;
import lombok.Getter;

@Getter
public class Student {

  private StudentId id;
  private Name name;
  private Age age;
  private Address address;

  private Student(StudentId id, Name name, Age age, Address address) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.address = address;
  }

  public static Student init(Name name, Age age, Address address) {
    var id = new StudentId(IdGenerator.generateId());
    return new Student(id, name, age, address);
  }

  public static Student of(StudentId id, Name name, Age age, Address address) {
    return new Student(id, name, age, address);
  }
}
