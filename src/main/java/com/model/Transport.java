package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String transportName;

    private String driverFirstName;

    private String driverLastName;

    private String driverTelephoneName;

    private Double maxWeight;

    private Double maxCapacity;

    private Double xDimension;

    private Double yDimension;

    private Double zDimension;

    @OneToOne
    @JsonIgnore
    private Route route;

    @ManyToOne
    @JsonIgnore
    private Warehouse currentWarehouse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Double getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Double getxDimension() {
        return xDimension;
    }

    public void setxDimension(Double xDimension) {
        this.xDimension = xDimension;
    }

    public Double getyDimension() {
        return yDimension;
    }

    public void setyDimension(Double yDimension) {
        this.yDimension = yDimension;
    }

    public Double getzDimension() {
        return zDimension;
    }

    public void setzDimension(Double zDimension) {
        this.zDimension = zDimension;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Warehouse getCurrentWarehouse() {
        return currentWarehouse;
    }

    public void setCurrentWarehouse(Warehouse currentWarehouse) {
        this.currentWarehouse = currentWarehouse;
    }
}
