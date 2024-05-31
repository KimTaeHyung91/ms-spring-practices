package com.example.ms.application;

import com.example.ms.application.in.CreateSchoolPageUseCase;
import com.example.ms.application.out.SaveSchoolPagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolPageService implements CreateSchoolPageUseCase {

  private final SaveSchoolPagePort saveSchoolPagePort;


  @Override

  public CreateSchoolPageInfo createSchoolPage(CreateSchoolPageCommand command) {
    var domain = command.toDomain();

    var id = this.saveSchoolPagePort.save(domain);

    return new CreateSchoolPageInfo(id.value());
  }

}

