package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Warehouse warehouseStart;

    @OneToOne
    private Warehouse warehouseEnd;

    private String description;

    @JsonIgnore
    private LocalDateTime startRoute;

    @JsonIgnore
    private LocalDateTime endRoute;

    @OneToOne
    private Transport transport;

    @OneToMany
    @JoinTable(name = "route_packages")
    private List<Package> packages = new ArrayList<>();

    @JsonIgnore
    private boolean isArrived = false;
}
