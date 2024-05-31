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
@Table(name = "school_page")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SchoolPageEntity extends BaseEntity<String> {

  @Id
  @Column(nullable = false)
  private String id;

  @Column(nullable = false)
  private String schoolName;

  @Column(nullable = false)
  private String city;

  @Column
  private String roadAddress;

  @Column
  private String numberingAddress;

  @Builder
  public SchoolPageEntity(String id, String schoolName, String city, String roadAddress,
      String numberingAddress) {
    this.id = id;
    this.schoolName = schoolName;
    this.city = city;
    this.roadAddress = roadAddress;
    this.numberingAddress = numberingAddress;
  }
}
