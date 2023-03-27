package com.springboot.mockapiproject.entity;

import com.springboot.mockapiproject.entity.converter.HashMapConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "data"
)
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> dataEntry;

    @Column(name = "data_entry")
    private String dataJson;

    @ManyToOne
    @JoinColumn(name = "mock_id")
    private MockProject mockProject;
}
