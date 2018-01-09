package com.controller;

import java.util.List;

import com.model.Package;
import com.model.Transport;
import com.model.Warehouse;


public class InputDto {

	private Transport transport;
	private List<Package> packages;
	private Warehouse warehouseStart;
	private Warehouse warehouseEnd;
	private String description;

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
}
