package com.controller;

import java.util.List;

import com.model.Transport;


public class TransportPackagingDto {

	private String transportName;
	private Long transportId;
	private List<PackageDto> packages;

	public TransportPackagingDto(Transport transport, List<PackageDto> packagesDimensions) {

		this.transportId = transport.getId();
		this.transportName = transport.getTransportName();
		this.packages = packagesDimensions;
	}

	public String getTransportName() {

		return transportName;
	}

	public Long getTransportId() {

		return transportId;
	}

	public List<PackageDto> getPackages() {

		return packages;
	}
}
