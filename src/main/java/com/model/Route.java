package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
