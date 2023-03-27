package com.springboot.mockapiproject.entity;

import com.springboot.mockapiproject.entity.converter.HashMapConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "mockproject"
)
public class MockProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mockID;

    @Column(name = "mockname", unique = true)
    private String mockName;

    @Column(name = "data")
    private String dataSchemaJson;

    @Transient
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> dataSchema;

    @OneToMany(mappedBy = "mockProject", cascade = CascadeType.ALL)
    private List<EndPoint> endpoints;

    @OneToMany(mappedBy = "mockProject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Data> dataEntries;
}
