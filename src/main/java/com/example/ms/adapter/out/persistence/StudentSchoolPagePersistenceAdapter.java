package com.example.ms.adapter.out.persistence;

import com.example.ms.adapter.out.persistence.repository.StudentSchoolPageJpaRepository;
import com.example.ms.application.out.UpdateStudentSubscriptionPort;
import com.example.ms.common.annotation.Adapter;
import com.example.ms.domain.StudentSubscription;
import com.example.ms.domain.StudentSubscriptionId;
import jakarta.persistence.EntityNotFoundException;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class StudentSchoolPagePersistenceAdapter implements UpdateStudentSubscriptionPort {

  private final StudentSchoolPageJpaRepository studentSchoolPageJpaRepository;

  @Override
  public StudentSubscription update(
      StudentSubscriptionId studentSubscriptionId, Consumer<StudentSubscription> consumer) {
    var entity = this.studentSchoolPageJpaRepository.findById(studentSubscriptionId.value())
                                                    .orElseThrow(
                                                        EntityNotFoundException::new);

    var domain = entity.toDomain();
    consumer.accept(domain);

    entity.update(domain);

    return domain;
  }
}
