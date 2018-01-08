package com.controller;

import java.util.List;

import com.model.Package;
import com.model.Transport;


public class InputDto {

	private Transport transport;
	private List<Package> packages;

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
}
