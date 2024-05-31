package com.example.ms.adapter.in.rest;

import com.example.ms.adapter.StudentMapper;
import com.example.ms.adapter.in.rest.StudentRestDto.RequestCancelSubscribeSchoolPage;
import com.example.ms.adapter.in.rest.response.Response;
import com.example.ms.application.in.CancelSubscribeSchoolPageUseCase;
import com.example.ms.application.in.RegisterStudentUseCase;
import com.example.ms.application.in.RetrieveStudentUseCase;
import com.example.ms.application.in.SubscribeSchoolPageUseCase;
import com.example.ms.domain.StudentSubscriptionId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
@Slf4j
public class StudentRestController {

  private final CancelSubscribeSchoolPageUseCase cancelSubscribeSchoolPageUseCase;
  private final RetrieveStudentUseCase retrieveStudentUseCase;
  private final RegisterStudentUseCase registerStudentUseCase;
  private final SubscribeSchoolPageUseCase subscribeSchoolPageUseCase;
  private final StudentMapper studentMapper;

  @PostMapping()
  public Response<RegisterStudentUseCase.RegisterStudentInfo> registerStudentInfo(
      @RequestBody StudentRestDto.RequestRegisterStudent request) {
    var command = this.studentMapper.mapFromDtoToCommand(request);
    var info = this.registerStudentUseCase.register(command);

    return Response.ok(info);
  }

  @PostMapping("/subscribe")
  public Response<SubscribeSchoolPageUseCase.SubscribeSchoolPageInfo> subscribeSchoolPage(
      @RequestBody StudentRestDto.RequestSubscribeSchoolPage request) {
    var command = this.studentMapper.mapFromDtoToCommand(request);

    var info = subscribeSchoolPageUseCase.subscribeSchoolPage(command);

    return Response.ok(info);
  }

  @PutMapping("/subscribe/cancel")
  public Response<CancelSubscribeSchoolPageUseCase.CancelSubscribeSchoolPageInfo> cancelSubscribeSchoolPage(
      @RequestBody RequestCancelSubscribeSchoolPage request) {
    var info = cancelSubscribeSchoolPageUseCase.cancelSubscribeSchoolPage(
        new StudentSubscriptionId(request.studentSubscriptionId())
    );

    return Response.ok(info);
  }
}
