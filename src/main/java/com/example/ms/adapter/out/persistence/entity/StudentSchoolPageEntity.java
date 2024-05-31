package com.example.ms.adapter.out.persistence.entity;

import com.example.ms.domain.StudentSubscription;
import com.example.ms.domain.StudentSubscription.StudentSubscriptionStatus;
import com.example.ms.domain.StudentSubscriptionId;
import com.example.ms.domain.vo.Date;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "student_school_page")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentSchoolPageEntity extends BaseEntity<String> {

  @Id
  @Column(nullable = false)
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private StudentEntity studentEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "school_page_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private SchoolPageEntity schoolPageEntity;


  @Column(nullable = false)
  private ZonedDateTime subscribeAt;

  @Column
  private ZonedDateTime cancelSubscribeAt;

  @Enumerated(value = EnumType.STRING)
  private StudentSchoolPageStatus subscribeStatus;

  public enum StudentSchoolPageStatus {
    ACTIVE, IN_ACTIVE;


    public static StudentSchoolPageStatus mapFromDomainEnum(StudentSubscriptionStatus status) {
      return Arrays.stream(StudentSchoolPageStatus.values())
                   .filter((value) -> status.name().equals(value.name()))
                   .findFirst()
                   .orElseThrow();
    }
  }

  @Builder
  public StudentSchoolPageEntity(String id, StudentEntity studentEntity,
      SchoolPageEntity schoolPageEntity, ZonedDateTime subscribeAt, ZonedDateTime cancelSubscribeAt,
      StudentSchoolPageStatus subscribeStatus) {
    this.id = id;
    this.studentEntity = studentEntity;
    this.schoolPageEntity = schoolPageEntity;
    this.subscribeAt = subscribeAt;
    this.cancelSubscribeAt = cancelSubscribeAt;
    this.subscribeStatus = subscribeStatus;
  }

  public StudentSubscription toDomain() {
    return StudentSubscription.of(new StudentSubscriptionId(id),
                                  new Date(subscribeAt),
                                  new Date(cancelSubscribeAt),
                                  StudentSubscriptionStatus.mapFromEntityEnum(subscribeStatus.name()));
  }

  public void update(StudentSubscription studentSubscription) {
    this.cancelSubscribeAt = studentSubscription.getCancelSubscribeAt().value();
    this.subscribeStatus = StudentSchoolPageStatus.mapFromDomainEnum(studentSubscription.getStatus());
  }
}
