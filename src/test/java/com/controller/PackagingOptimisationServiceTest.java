package com.controller;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.model.Package;
import com.model.Transport;


public class PackagingOptimisationServiceTest {

	private PackagingOptimisationService packagingOptimisationService = new PackagingOptimisationService();

	@Test
	public void shouldAllBeAvailable() throws Exception {

		Transport transport = buildTransport(3d, 3d, 3d);

		Package package1 = buildPackage(1d, 1d, 2d);
		Package package2 = buildPackage(2d, 2d, 1d);
		Package package3 = buildPackage(1d, 1d, 1d);

		List<Package> packages = newArrayList(package1, package2, package3);

		TransportPackagingDto result = packagingOptimisationService.optimise(transport, packages);
		assertThat(result.getPackages()).allMatch(PackageDto::isAccepted);
	}

	@Test
	public void shouldTheBiggestBeUnavailable() throws Exception {

		Transport transport = buildTransport(3d, 3d, 1d);

		Package package1 = buildPackage(10d, 10d, 1d);
		Package package2 = buildPackage(1d, 1d, 1d);
		Package package3 = buildPackage(1d, 1d, 1d);

		List<Package> packages = newArrayList(package1, package2, package3);

		TransportPackagingDto result = packagingOptimisationService.optimise(transport, packages);

		assertThat(result.getPackages())
			.extracting("accepted")
			.containsExactly(false, true, true);
	}

	@Test
	public void shouldTheSmallestBeUnavailable() throws Exception {

		Transport transport = buildTransport(4d, 2d, 1d);

		Package package1 = buildPackage(2d, 2d, 1d);
		Package package2 = buildPackage(2d, 2d, 1d);
		Package package3 = buildPackage(1d, 1d, 1d);

		List<Package> packages = newArrayList(package1, package2, package3);

		TransportPackagingDto result = packagingOptimisationService.optimise(transport, packages);

		assertThat(result.getPackages())
			.extracting("accepted")
			.containsExactly(true, true, false);
	}

	@Test
	public void shouldTst1() throws Exception {

		Transport transport = buildTransport(20d, 10d, 10d);

		Package package1 = buildPackage(20d, 10d, 4d);
		Package package2 = buildPackage(20d, 10d, 4d);
		Package package3 = buildPackage(20d, 10d, 4d);
		Package package4 = buildPackage(20d, 10d, 4d);

		List<Package> packages = newArrayList(package1, package2, package3, package4);

		TransportPackagingDto result = packagingOptimisationService.optimise(transport, packages);

		assertThat(result.getPackages())
			.extracting("accepted")
			.containsExactlyInAnyOrder(true, true, false, false);
	}

	@Test
	public void shouldTst2() throws Exception {

		Transport transport = buildTransport(40d, 10d, 10d);

		Package package1 = buildPackage(20d, 10d, 4d);
		Package package2 = buildPackage(20d, 10d, 4d);
		Package package3 = buildPackage(20d, 10d, 4d);
		Package package4 = buildPackage(20d, 10d, 4d);

		List<Package> packages = newArrayList(package1, package2, package3, package4);

		TransportPackagingDto result = packagingOptimisationService.optimise(transport, packages);

		assertThat(result.getPackages())
			.extracting("accepted")
			.containsExactlyInAnyOrder(true, true, true, true);
	}

	@Test
	public void shouldTst3() throws Exception {

		Transport transport = buildTransport(10d, 10d, 15d);

		Package package1 = buildPackage(10d, 10d, 5d);
		Package package2 = buildPackage(10d, 10d, 5d);
		Package package3 = buildPackage(10d, 10d, 5d);
		Package package4 = buildPackage(10d, 10d, 5d);

		List<Package> packages = newArrayList(package1, package2, package3, package4);

		TransportPackagingDto result = packagingOptimisationService.optimise(transport, packages);

		assertThat(result.getPackages())
			.extracting("accepted")
			.containsExactlyInAnyOrder(true, true, true, false);
	}

	private Transport buildTransport(double xDimension, double yDimension, double zDimension) {

		Transport transport = new Transport();
		transport.setxDimension(xDimension);
		transport.setyDimension(yDimension);
		transport.setzDimension(zDimension);
		return transport;
	}

	private Package buildPackage(double xDimension, double yDimension, double zDimension) {

		Package package1 = new Package();
		package1.setxDimension(xDimension);
		package1.setyDimension(yDimension);
		package1.setzDimension(zDimension);
		return package1;
	}

}