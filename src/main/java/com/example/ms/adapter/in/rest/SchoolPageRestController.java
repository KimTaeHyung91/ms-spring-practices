package com.example.ms.adapter.in.rest;

import com.example.ms.adapter.SchoolPageMapper;
import com.example.ms.adapter.in.rest.response.Response;
import com.example.ms.application.in.CreateSchoolPageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/school-page")
public class SchoolPageRestController {

  private final CreateSchoolPageUseCase createSchoolPageUseCase;
  private final SchoolPageMapper schoolPageMapper;

  @PostMapping
  public Response<CreateSchoolPageUseCase.CreateSchoolPageInfo> createSchoolPage(
      @RequestBody SchoolPageRestDto.RequestCreateSchoolPage request) {
    var command = this.schoolPageMapper.mapFromDtoToCommand(request);

    var info = this.createSchoolPageUseCase.createSchoolPage(command);

    return Response.ok(info);
  }
}
