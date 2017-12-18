package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Package")
@NoArgsConstructor
@AllArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Double weight;

    private Double xDimension;

    private Double yDimension;

    private Double zDimension;

    private Double capacity;

    private LocalDateTime whenTake;

    private String timeString;


    @OneToOne
    private User user;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    @Null
    @JsonIgnore
    private Route route;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getWhenTake() {
        return whenTake;
    }

    public void setWhenTake(LocalDateTime whenTake) {
        this.whenTake = whenTake;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }
}
