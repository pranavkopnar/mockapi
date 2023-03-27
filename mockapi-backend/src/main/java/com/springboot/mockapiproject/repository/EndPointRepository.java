package com.springboot.mockapiproject.repository;

import com.springboot.mockapiproject.entity.EndPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndPointRepository extends JpaRepository<EndPoint, Long> {
    EndPoint findEndPointByUrl(String url);
}
