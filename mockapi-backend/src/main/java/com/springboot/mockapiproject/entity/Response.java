package com.springboot.mockapiproject.entity;

import com.springboot.mockapiproject.entity.converter.HashMapConverter;
import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "response"
)
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resid")
    private Long rid;

    @Column(name = "response")
    private String responseJson;

    @Transient
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> response;

    @ManyToOne
    @JoinColumn(name = "endpoint_id")
    private EndPoint endPoint;
}
