package com.controller;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.model.Package;
import com.model.Route;
import com.model.Transport;


public class PackagingOptimisationServiceTest {

	private PackagingOptimisationService packagingOptimisationService = new PackagingOptimisationService();

	@Test
	public void shouldAllBeAvailable() throws Exception {

		Transport transport = buildTransport(3d, 3d, 3d);

		Package package1 = buildPackage(1d, 1d, 2d, 1l);
		Package package2 = buildPackage(2d, 2d, 1d, 2l);
		Package package3 = buildPackage(1d, 1d, 1d, 3l);

		List<Package> packages = newArrayList(package1, package2, package3);

		Route result = packagingOptimisationService.optimise(getInputDto(transport, packages));
		assertThat(result.getPackages()).containsExactly(package1,package2,package3);
	}

	@Test
	public void shoulddsada() throws Exception {

		Transport transport = buildTransport(30d, 3d, 2d);

		Package package1 = buildPackage(28d, 3d, 2d, 1l);
		Package package2 = buildPackage(20d, 2d, 1d, 2l);
		Package package3 = buildPackage(2d, 3d, 2d, 3l);
		Package package4 = buildPackage(2d, 3d, 2d, 4l);

		List<Package> packages = newArrayList(package1, package2, package3, package4);

		Route result = packagingOptimisationService.optimise(getInputDto(transport, packages));
		assertThat(result.getPackages()).containsExactly(package1,package3);
	}

	@Test
	public void shouldTheBiggestBeUnavailable() throws Exception {

		Transport transport = buildTransport(3d, 3d, 1d);

		Package package1 = buildPackage(10d, 10d, 1d, 1l);
		Package package2 = buildPackage(1d, 1d, 1d, 2l);
		Package package3 = buildPackage(1d, 1d, 1d, 3l);

		List<Package> packages = newArrayList(package1, package2, package3);

		Route result = packagingOptimisationService.optimise(getInputDto(transport, packages));

		assertThat(result.getPackages()).containsExactly(package2,package3);
	}

	@Test
	public void shouldTheSmallestBeUnavailable() throws Exception {

		Transport transport = buildTransport(4d, 2d, 1d);

		Package package1 = buildPackage(2d, 2d, 1d, 1l);
		Package package2 = buildPackage(2d, 2d, 1d, 2l);
		Package package3 = buildPackage(1d, 1d, 1d, 3l);

		List<Package> packages = newArrayList(package1, package2, package3);

		Route result = packagingOptimisationService.optimise(getInputDto(transport, packages));

		assertThat(result.getPackages()).containsExactly(package1, package2);
	}

	private InputDto getInputDto(Transport transport, List<Package> packages) {

		InputDto inputDto = new InputDto();
		inputDto.setTransport(transport);
		inputDto.setPackages(packages);
		return inputDto;
	}

	@Test
	public void shouldTst1() throws Exception {

		Transport transport = buildTransport(20d, 10d, 10d);

		Package package1 = buildPackage(20d, 10d, 4d, 1l);
		Package package2 = buildPackage(20d, 10d, 4d, 2l);
		Package package3 = buildPackage(20d, 10d, 4d, 3l);
		Package package4 = buildPackage(20d, 10d, 4d, 4l);

		List<Package> packages = newArrayList(package1, package2, package3, package4);

		Route result = packagingOptimisationService.optimise(getInputDto(transport, packages));

		assertThat(result.getPackages()).containsExactlyInAnyOrder(package1, package2);
	}

	@Test
	public void shouldTst2() throws Exception {

		Transport transport = buildTransport(40d, 10d, 10d);

		Package package1 = buildPackage(20d, 10d, 4d,1l);
		Package package2 = buildPackage(20d, 10d, 4d,2l);
		Package package3 = buildPackage(20d, 10d, 4d,3l);
		Package package4 = buildPackage(20d, 10d, 4d,4l);

		List<Package> packages = newArrayList(package1, package2, package3, package4);

		Route result = packagingOptimisationService.optimise(getInputDto(transport, packages));

		assertThat(result.getPackages()).containsExactlyInAnyOrder(package1,package2, package3, package4);
	}

	@Test
	public void shouldTst3() throws Exception {

		Transport transport = buildTransport(10d, 10d, 15d);

		Package package1 = buildPackage(10d, 10d, 5d, 1l);
		Package package2 = buildPackage(10d, 10d, 5d, 2l);
		Package package3 = buildPackage(10d, 10d, 5d, 3l);
		Package package4 = buildPackage(10d, 10d, 5d, 4l);

		List<Package> packages = newArrayList(package1, package2, package3, package4);

		Route result = packagingOptimisationService.optimise(getInputDto(transport, packages));

		assertThat(result.getPackages()).containsExactlyInAnyOrder(package1,package2,package3);
	}

	private Transport buildTransport(double xDimension, double yDimension, double zDimension) {

		Transport transport = new Transport();
		transport.setxDimension(xDimension);
		transport.setyDimension(yDimension);
		transport.setzDimension(zDimension);
		return transport;
	}

	private Package buildPackage(double xDimension, double yDimension, double zDimension, long id) {

		Package package1 = new Package();
		package1.setxDimension(xDimension);
		package1.setyDimension(yDimension);
		package1.setzDimension(zDimension);
		package1.setId(id);
		return package1;
	}

}