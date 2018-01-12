package com.controller;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;
import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.skjolberg.packing.Box;
import com.github.skjolberg.packing.Container;
import com.github.skjolberg.packing.Dimension;
import com.github.skjolberg.packing.Packager;
import com.model.Package;
import com.model.Route;
import com.model.Transport;


@Service
public class PackagingOptimisationService {

	public Route optimise(InputDto inputDto) {

		Transport transport = inputDto.getTransport();
		List<Package> packages = inputDto.getPackages();

		Dimension containerDimensions = wrapTransportDimensions(transport);
		Container container = new Container(containerDimensions);

		List<PackageDto> packagesDimensions = packages.stream()
				.map(PackageDto::new)
				.sorted(BOX_VOLUME_DESCENDING)
				.collect(toList());

		Packager packager = new Packager(newArrayList(container));
		List<PackageDto> r = newArrayList();
		for (PackageDto item : packagesDimensions) {
			if (null != packager.pack(newArrayList(item.getBox()))) {
				r.add(item);
			}
		}
//		int counter = 1;
//
//		while (packager.pack(getAvailableBoxes(packagesDimensions)) == null) {
//			packagesDimensions.get(packagesDimensions.size() - counter).makeUnavailable();
//			counter++;
//		}
//		do {
//			packagesDimensions.get(packagesDimensions.size() - counter).makeUnavailable();
//			counter++;
//		} while(packager.pack(getAvailableBoxes(packagesDimensions)) != null);

		List<PackageDto> optimised = optimise(packager, newArrayList(), r);

		List<Package> acceptedPackages = packages.stream()
			.filter(p -> optimised.stream().anyMatch(optim -> optim.getPackageId().equals(p.getId())))
			.collect(toList());

		Route route = new Route();

		route.setWarehouseStart(inputDto.getWarehouseStart());
		route.setWarehouseEnd(inputDto.getWarehouseEnd());
		route.setPackages(acceptedPackages);
		route.setDescription(inputDto.getDescription());
		route.setTransport(transport);
		route.setStartRoute(inputDto.getStartRoute());
		route.setEndRoute(inputDto.getEndRoute());
       // route.setStartRoute1(getLocalDateTime(inputDto.getStartRoute()));
      //  route.setEndRoute1(getLocalDateTime(inputDto.getEndRoute()));

		return route;
	}

	private List<PackageDto> optimise(Packager packager, List<PackageDto> optimised, List<PackageDto> unknown) {

		if(unknown.size() <= 1) {
			return optimised;
		}

		List<PackageDto> toTest = newArrayList(optimised);
		toTest.addAll(unknown);

		while (packager.pack(getBoxes(toTest)) == null) {
			toTest.remove(toTest.size() - 1);
		}
		if(toTest.equals(optimised)) {
			return optimised;
		}

		unknown.removeAll(toTest);

		return optimise(packager, toTest, reverse(newArrayList(unknown)));
	}

	private List<Box> getBoxes(List<PackageDto> packageDtos) {

		return packageDtos.stream()
			.map(PackageDto::getBox)
			.collect(toList());
	}

	private static final Comparator<PackageDto> BOX_VOLUME_DESCENDING = (o1, o2) -> o2.getBox().getVolume() - o1.getBox().getVolume();

	private Dimension wrapTransportDimensions(Transport transport) {

		return new Dimension(transport.getxDimension().intValue(), transport.getyDimension().intValue(), transport.getzDimension().intValue());
	}

	private LocalDateTime getLocalDateTime(String str){
		ZonedDateTime zdt = ZonedDateTime.parse(str);
		return zdt.toLocalDateTime();
	}
}
