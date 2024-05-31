package com.example.ms.domain.vo;

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public record Address(String city, String road, String numbering) {

  public Address {
    Objects.requireNonNull(city);
  }

  public Address changeAddress(String city, String road, String numbering) {
    return new Address(city, road, numbering);
  }

  public String makeFullAddress() {
    var buffer = new StringBuilder().append(city);

    if (StringUtils.isNotBlank(road)) {
      buffer.append(road);
    }
    
    return buffer.toString();
  }
}
