package com.example.ms.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "student")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentEntity extends BaseEntity<String> {

  @Id
  @Column(nullable = false)
  private String id;

  @Column(nullable = false)
  private String studentName;

  @Column(nullable = false)
  private Long age;

  @Column(nullable = false)
  private String city;

  @Column
  private String roadAddress;

  @Column
  private String numberingAddress;

  @Builder
  public StudentEntity(String id, String studentName, Long age, String city, String roadAddress,
      String numberingAddress) {
    this.id = id;
    this.studentName = studentName;
    this.age = age;
    this.city = city;
    this.roadAddress = roadAddress;
    this.numberingAddress = numberingAddress;
  }
}
