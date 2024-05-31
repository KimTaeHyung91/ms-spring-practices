package com.example.ms.domain;

import com.example.ms.common.util.IdGenerator;
import com.example.ms.domain.vo.Address;
import com.example.ms.domain.vo.Name;
import lombok.Getter;

@Getter
public class SchoolPage {

  private SchoolPageId id;
  private Name schoolName;
  private Address address;

  private SchoolPage(SchoolPageId id, Name schoolName, Address address) {
    this.id = id;
    this.schoolName = schoolName;
    this.address = address;
  }

  public static SchoolPage init(Name shoolName, Address address) {
    return new SchoolPage(new SchoolPageId(IdGenerator.generateId()), shoolName, address);
  }

  public static SchoolPage of(SchoolPageId id, Name shoolName, Address address) {
    return new SchoolPage(id, shoolName, address);
  }
}
