package com.example.ms.adapter.out.persistence;

import com.example.ms.adapter.StudentMapper;
import com.example.ms.adapter.out.persistence.entity.StudentSchoolPageEntity;
import com.example.ms.adapter.out.persistence.entity.StudentSchoolPageEntity.StudentSchoolPageStatus;
import com.example.ms.adapter.out.persistence.repository.SchoolPageJpaRepository;
import com.example.ms.adapter.out.persistence.repository.StudentJpaRepository;
import com.example.ms.adapter.out.persistence.repository.StudentSchoolPageJpaRepository;
import com.example.ms.application.out.LoadStudentPost;
import com.example.ms.application.out.LoadStudentSubscribePort;
import com.example.ms.application.out.SaveStudentPort;
import com.example.ms.common.annotation.Adapter;
import com.example.ms.domain.SchoolPageId;
import com.example.ms.domain.Student;
import com.example.ms.domain.StudentId;
import com.example.ms.domain.StudentSubscription;
import com.example.ms.domain.StudentSubscription.StudentSubscriptionStatus;
import com.example.ms.domain.StudentSubscriptionId;
import com.example.ms.domain.vo.Date;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class StudentPersistenceAdapter implements SaveStudentPort, LoadStudentPost,
    LoadStudentSubscribePort {

  private final StudentJpaRepository studentJpaRepository;
  private final SchoolPageJpaRepository schoolPageJpaRepository;
  private final StudentSchoolPageJpaRepository studentSchoolPageJpaRepository;
  private final StudentMapper studentMapper;

  @Override
  public Student load(StudentId studentId) {
    var student = this.studentJpaRepository.findById(studentId.value())
                                           .orElseThrow(EntityNotFoundException::new);

    return this.studentMapper.mapFromEntityToDomain(student);
  }

  @Override
  public StudentSubscription load(StudentSubscriptionId studentSubscriptionId) {
    var load = this.studentSchoolPageJpaRepository.findById(studentSubscriptionId.value())
                                                  .orElseThrow(EntityNotFoundException::new);

    return StudentSubscription.of(new StudentSubscriptionId(load.getId()),
                                  new Date(load.getSubscribeAt()),
                                  new Date(load.getCancelSubscribeAt()),
                                  StudentSubscriptionStatus.mapFromEntityEnum(load.getSubscribeStatus()
                                                                                  .name()));
  }

  @Override
  public StudentId save(Student domain) {
    var entity = this.studentMapper.mapFromDomainToEntity(domain);
    this.studentJpaRepository.save(entity);

    return domain.getId();
  }


  @Override
  public StudentSubscriptionId
  save(StudentId studentId, SchoolPageId schoolPageId,
      StudentSubscription domain) {
    var studentEntity = this.studentJpaRepository.findById(studentId.value())
                                                 .orElseThrow(EntityNotFoundException::new);

    var schoolPageEntity = this.schoolPageJpaRepository.findById(schoolPageId.value())
                                                       .orElseThrow(EntityNotFoundException::new);

    var subscribeStatus = StudentSchoolPageStatus.mapFromDomainEnum(domain.getStatus());

    var studentSchoolPageEntity = StudentSchoolPageEntity.builder()
                                                         .id(domain.getStudentSubscriptionId()
                                                                   .value())
                                                         .studentEntity(studentEntity)
                                                         .schoolPageEntity(schoolPageEntity)
                                                         .subscribeAt(domain.getSubscribeAt()
                                                                            .value())
                                                         .subscribeStatus(subscribeStatus)
                                                         .build();

    this.studentSchoolPageJpaRepository.save(studentSchoolPageEntity);

    return domain.getStudentSubscriptionId();
  }
}
