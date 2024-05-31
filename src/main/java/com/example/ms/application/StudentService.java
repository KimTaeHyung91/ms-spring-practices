package com.example.ms.application;

import com.example.ms.application.in.CancelSubscribeSchoolPageUseCase;
import com.example.ms.application.in.RegisterStudentUseCase;
import com.example.ms.application.in.RetrieveStudentUseCase;
import com.example.ms.application.in.SubscribeSchoolPageUseCase;
import com.example.ms.application.out.LoadStudentPost;
import com.example.ms.application.out.SaveStudentPort;
import com.example.ms.application.out.UpdateStudentSubscriptionPort;
import com.example.ms.domain.StudentId;
import com.example.ms.domain.StudentSubscription;
import com.example.ms.domain.StudentSubscriptionId;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService implements RegisterStudentUseCase, RetrieveStudentUseCase,
    SubscribeSchoolPageUseCase, CancelSubscribeSchoolPageUseCase {

  private final UpdateStudentSubscriptionPort updateStudentSubscriptionPort;
  private final LoadStudentPost loadStudentPost;
  private final SaveStudentPort saveStudentPort;

  @Transactional
  @Override
  public RegisterStudentInfo register(RegisterStudentCommand command) {
    var id = saveStudentPort.save(command.toEntity());

    return new RegisterStudentInfo(id.value());
  }

  @Transactional(readOnly = true)
  @Override
  public RetrieveStudentInfo retrieveStudent(StudentId studentId) {
    var student = loadStudentPost.load(studentId);
    return RetrieveStudentInfo.builder()
                              .studentName(student.getName().value())
                              .age(student.getAge().value())
                              .address(student.getAddress().makeFullAddress())
                              .build();
  }

  @Transactional
  @Override
  public SubscribeSchoolPageInfo subscribeSchoolPage(SubscribeSchoolPageCommand command) {
    var studentSubscription = command.toEntity();

    studentSubscription.startSubscribe();

    var id = saveStudentPort.save(command.studentId(),
                                  command.schoolPageId(),
                                  studentSubscription);

    return new SubscribeSchoolPageInfo(id.value());
  }

  @Transactional
  @Override
  public CancelSubscribeSchoolPageInfo cancelSubscribeSchoolPage(
      StudentSubscriptionId studentSubscriptionId) {

    var domain = updateStudentSubscriptionPort.update(studentSubscriptionId,
                                                      StudentSubscription::cancelSubscribe);

    return new CancelSubscribeSchoolPageInfo(domain.getCancelSubscribeAt().value().format(
        DateTimeFormatter.ISO_LOCAL_DATE_TIME));
  }
}
