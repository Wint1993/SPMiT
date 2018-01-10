package com.controller;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

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

		for (PackageDto item : packagesDimensions) {
			if (null == packager.pack(newArrayList(item.getBox()))) {
				item.makeUnavailable();
			}
		}
		int counter = 1;

		while (packager.pack(packagesDimensions.stream().filter(PackageDto::isAccepted).map(PackageDto::getBox).collect(toList())) == null) {
			packagesDimensions.get(packagesDimensions.size() - counter).makeUnavailable();
			counter++;
		}

		List<Long> acceptedPackagesIds = packagesDimensions.stream()
			.filter(PackageDto::isAccepted)
			.map(PackageDto::getPackageId)
			.collect(toList());

		Route route = new Route();

		route.setWarehouseStart(inputDto.getWarehouseStart());
		route.setWarehouseEnd(inputDto.getWarehouseEnd());
		route.setPackages(packages.stream()
		.filter(p -> acceptedPackagesIds.contains(p.getId()))
		.collect(toList()));
		route.setDescription(inputDto.getDescription());
		route.setTransport(transport);
		route.setStartRoute(inputDto.getStartRoute());
		route.setEndRoute(inputDto.getEndRoute());
       // route.setStartRoute1(getLocalDateTime(inputDto.getStartRoute()));
      //  route.setEndRoute1(getLocalDateTime(inputDto.getEndRoute()));

		return route;
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
