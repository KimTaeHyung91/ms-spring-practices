package com.example.ms.adapter.out.persistence;

import com.example.ms.adapter.SchoolPageMapper;
import com.example.ms.adapter.out.persistence.repository.SchoolPageJpaRepository;
import com.example.ms.application.out.SaveSchoolPagePort;
import com.example.ms.common.annotation.Adapter;
import com.example.ms.domain.SchoolPage;
import com.example.ms.domain.SchoolPageId;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class SchoolPagePersistenceAdapter implements SaveSchoolPagePort {

  private final SchoolPageJpaRepository schoolPageJpaRepository;
  private final SchoolPageMapper schoolPageMapper;

  @Override
  public SchoolPageId save(SchoolPage schoolPage) {
    var entity = this.schoolPageMapper.mapFromDomainToEntity(schoolPage);

    this.schoolPageJpaRepository.save(entity);

    return new SchoolPageId(entity.getId());
  }
}
