package com.example.ms.adapter.out.persistence.repository;

import com.example.ms.adapter.out.persistence.entity.StudentSchoolPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentSchoolPageJpaRepository extends
    JpaRepository<StudentSchoolPageEntity, String> {}
