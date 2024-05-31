package com.example.ms.adapter.out.persistence.repository;

import com.example.ms.adapter.out.persistence.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpaRepository extends JpaRepository<StudentEntity, String> {}
