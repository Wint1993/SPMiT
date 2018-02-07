package com.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.model.Package;
import com.model.Transport;
import com.model.Warehouse;


public class InputDto {

	private Transport transport;
	private List<Package> packages;
	private Warehouse warehouseStart;
	private Warehouse warehouseEnd;
	private String description;
	private String startRoute;
	private String endRoute;

	//@JsonSerialize(using = ToStringSerializer.class)
	//private LocalDateTime startRoute1;

//	@JsonSerialize(using = ToStringSerializer.class)
	//private LocalDateTime endRoute1;


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

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public Warehouse getWarehouseEnd() {

		return warehouseEnd;
	}

	public void setWarehouseEnd(Warehouse warehouseEnd) {

		this.warehouseEnd = warehouseEnd;
	}

	public Warehouse getWarehouseStart() {

		return warehouseStart;
	}

	public void setWarehouseStart(Warehouse warehouseStart) {

		this.warehouseStart = warehouseStart;
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

/*	public LocalDateTime getStartRoute1() {
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
	} */
}
