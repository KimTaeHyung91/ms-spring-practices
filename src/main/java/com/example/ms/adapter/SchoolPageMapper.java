package com.example.ms.adapter;

import com.example.ms.adapter.in.rest.SchoolPageRestDto;
import com.example.ms.adapter.out.persistence.entity.SchoolPageEntity;
import com.example.ms.application.in.CreateSchoolPageUseCase;
import com.example.ms.common.annotation.Mapper;
import com.example.ms.domain.SchoolPage;
import com.example.ms.domain.vo.Address;
import com.example.ms.domain.vo.Name;

@Mapper
public class SchoolPageMapper {

  public SchoolPageEntity mapFromDomainToEntity(SchoolPage domain) {
    return SchoolPageEntity.builder()
                           .id(domain.getId().value())
                           .schoolName(domain.getSchoolName().value())
                           .city(domain.getAddress().city())
                           .roadAddress(domain.getAddress().road())
                           .numberingAddress(domain.getAddress().numbering())
                           .build();
  }

  public CreateSchoolPageUseCase.CreateSchoolPageCommand mapFromDtoToCommand(
      SchoolPageRestDto.RequestCreateSchoolPage request) {
    var name = new Name(request.name());
    var address = new Address(
        request.city(),
        request.roadAddress(),
        request.numberingAddress()
    );

    return new CreateSchoolPageUseCase.CreateSchoolPageCommand(name, address);
  }
}
