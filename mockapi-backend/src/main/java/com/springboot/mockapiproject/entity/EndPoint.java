package com.springboot.mockapiproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "endpoints"
)
public class EndPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long epID;

    @Column(name = "url")
    private String url;

    @Column(name = "operation")
    private String operation;

    @ManyToOne
    @JoinColumn(name = "mock_id")
    private MockProject mockProject;

    @OneToMany(mappedBy = "endPoint", cascade = CascadeType.ALL)
    private List<Response> responses;
}
