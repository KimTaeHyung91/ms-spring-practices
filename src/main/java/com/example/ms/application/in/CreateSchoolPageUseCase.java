package com.example.ms.application.in;

import com.example.ms.domain.SchoolPage;
import com.example.ms.domain.vo.Address;
import com.example.ms.domain.vo.Name;

public interface CreateSchoolPageUseCase {

  CreateSchoolPageInfo createSchoolPage(CreateSchoolPageCommand command);

  record CreateSchoolPageCommand(Name schoolName, Address address) {

    public SchoolPage toDomain() {
      return SchoolPage.init(schoolName, address);
    }
  }

  record CreateSchoolPageInfo(String schoolPageId) {}
}
