package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Route  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private Warehouse warehouseStart;

    @OneToOne
    private Warehouse warehouseEnd;

    private String description;

    private String startRoute;

    private String endRoute;


    private LocalDateTime startRoute1;

   // @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime endRoute1;


    //@OneToOne
    @ManyToOne
    private Transport transport;

    @OneToMany(mappedBy = "route")
    private List<Package> packages = new ArrayList<>();

    @JsonIgnore
    private boolean isArrived = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Warehouse getWarehouseStart() {
        return warehouseStart;
    }

    public void setWarehouseStart(Warehouse warehouseStart) {
        this.warehouseStart = warehouseStart;
    }

    public Warehouse getWarehouseEnd() {
        return warehouseEnd;
    }

    public void setWarehouseEnd(Warehouse warehouseEnd) {
        this.warehouseEnd = warehouseEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartRoute() {
        return startRoute;
    }

    public void setStartRoute(String startRoute) {
        this.startRoute = startRoute;
    }

    public String getEndRoute() {
        return endRoute;
    }

    public void setEndRoute(String endRoute) {
        this.endRoute = endRoute;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public boolean isArrived() {
        return isArrived;
    }

    public void setArrived(boolean arrived) {
        isArrived = arrived;
    }

    public LocalDateTime getStartRoute1() {
        return startRoute1;
    }

    public void setStartRoute1(LocalDateTime startRoute1) {
        this.startRoute1 = startRoute1;
    }

    public LocalDateTime getEndRoute1() {
        return endRoute1;
    }

    public void setEndRoute1(LocalDateTime endRoute1) {
        this.endRoute1 = endRoute1;
    }
}
