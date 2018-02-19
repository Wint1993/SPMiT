package com.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;


@Entity
@Table(name = "Package")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude="route")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String description;

    private Double weight;

    private Double xDimension;

    private Double yDimension;

    private Double zDimension;

    private Double capacity;

    @JsonIgnore
    private LocalDateTime whenTake;
    @JsonIgnore
    private String timeString;

    @OneToOne
    private User user;

    @ManyToOne
	@JsonIdentityReference()
    private Warehouse warehouse;

    @ManyToOne//(fetch = FetchType.EAGER)//(cascade={CascadeType.ALL})
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

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Package aPackage = (Package) o;

		if (xDimension != null ? !xDimension.equals(aPackage.xDimension) : aPackage.xDimension != null)
			return false;
		if (yDimension != null ? !yDimension.equals(aPackage.yDimension) : aPackage.yDimension != null)
			return false;
		return zDimension != null ? zDimension.equals(aPackage.zDimension) : aPackage.zDimension == null;
	}

	@Override
	public int hashCode() {

		int result = xDimension != null ? xDimension.hashCode() : 0;
		result = 31 * result + (yDimension != null ? yDimension.hashCode() : 0);
		result = 31 * result + (zDimension != null ? zDimension.hashCode() : 0);
		return result;
	}
}
