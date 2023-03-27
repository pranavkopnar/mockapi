package com.springboot.mockapiproject.repository;

import com.springboot.mockapiproject.entity.MockProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockProjectRepository extends JpaRepository<MockProject, Long> {
    MockProject findByMockName(String mockName);
}
