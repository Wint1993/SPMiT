package com.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;
import java.util.stream.Collectors;

import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Package;
import com.model.Warehouse;
import com.repository.PackageRepository;
import com.service.PackageService;
import com.service.WarehouseService;


@RestController
@RequestMapping("/api/package")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired private WarehouseService warehouseService;

    @RequestMapping(value = "/create", method = POST, headers = "Content-Type=application/json;charset=UTF-8")
    public Package create(@RequestBody Package pack){
        return packageService.create(pack);
    }

    @ModelAttribute("warehouses")
	public List<Warehouse> warehouses() {
    	return warehouseService.findAll();
	}

    @RequestMapping(value = "/remove/{id}", method = DELETE)
    public Package remove(@PathVariable long id){

        Package pack = packageRepository.findOneById(id);
        packageService.remove(pack);
        packageRepository.flush();
        return pack;
    }

    @RequestMapping(value = "/edit/{id}", method = PUT)
    public ResponseEntity<?> edit(@PathVariable("id") Long id,@RequestBody Package pack){

        Package currentPack = packageRepository.findOneById(id);
        currentPack.setName(pack.getName());
        currentPack.setzDimension(pack.getzDimension());
        currentPack.setxDimension(pack.getxDimension());
        currentPack.setyDimension(pack.getyDimension());
        currentPack.setWarehouse(pack.getWarehouse());
        currentPack.setCapacity(pack.getCapacity());
        currentPack.setTimeString(pack.getTimeString());
        currentPack.setWhenTake(pack.getWhenTake());
        currentPack.setUser(pack.getUser());
        currentPack.setWeight(pack.getWeight());
        currentPack.setDescription(pack.getDescription());
        packageService.updatePackage(currentPack);

        return new ResponseEntity<Package>(currentPack,HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = GET)
    public List<Package> findAll(){
	        return packageService.findAll();
    }

	@RequestMapping(value = "/available", method = GET)
	public List<Package> findAvailable(){
		return packageService.findAll().stream()
			.filter(p -> null == p.getRoute())
			.collect(Collectors.toList());
	}

    @GetMapping(value = "/{id}")
	public Package findOne(@PathVariable Long id) {

    	return packageRepository.findOne(id);
	}

    @RequestMapping(value = "/info/{id}", method = GET)
    public User userInfo(@PathVariable long id){

        return userRepository.findOneById(packageRepository.findOneById(id).getUser().getId());
    }
}
