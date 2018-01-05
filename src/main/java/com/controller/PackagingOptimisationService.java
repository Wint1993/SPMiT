package com.controller;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.skjolberg.packing.Container;
import com.github.skjolberg.packing.Dimension;
import com.github.skjolberg.packing.Packager;
import com.model.Package;
import com.model.Transport;


@Service
public class PackagingOptimisationService {

	public TransportPackagingDto optimise(Transport transport, List<Package> packages) {

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

		return new TransportPackagingDto(transport, packagesDimensions);
	}

	private static final Comparator<PackageDto> BOX_VOLUME_DESCENDING = (o1, o2) -> o2.getBox().getVolume() - o1.getBox().getVolume();

	private Dimension wrapTransportDimensions(Transport transport) {

		return new Dimension(transport.getxDimension().intValue(), transport.getyDimension().intValue(), transport.getzDimension().intValue());
	}
}
