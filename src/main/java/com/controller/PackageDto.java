package com.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.skjolberg.packing.Box;
import com.github.skjolberg.packing.Dimension;
import com.model.Package;

import lombok.Data;


@Data
public class PackageDto {

	private String packageName;
	private Long packageId;
	private boolean accepted;

	@JsonIgnore
	private Box box;

	public PackageDto(Package aPackage) {

		this.packageName = aPackage.getName();
		this.packageId = aPackage.getId();
		this.box = wrapPackageDimensions(aPackage);
		accepted = true;
	}


	private Box wrapPackageDimensions(Package pack) {

		return new Box(new Dimension(pack.getxDimension().intValue(), pack.getyDimension().intValue(), pack.getzDimension().intValue()));
	}

	public String getPackageName() {

		return packageName;
	}

	public Long getPackageId() {

		return packageId;
	}

	public boolean isAccepted() {

		return accepted;
	}

	public Box getBox() {

		return box;
	}

	public void makeUnavailable(){

		this.accepted = false;
	}
}
