package com.springboot.mockapiproject.repository;

import com.springboot.mockapiproject.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long> {
    Data findDataById(Long id);
}
