package com.example.ms.application.out;

import com.example.ms.domain.SchoolPage;
import com.example.ms.domain.SchoolPageId;

public interface SaveSchoolPagePort {

  SchoolPageId save(SchoolPage schoolPage);
}
