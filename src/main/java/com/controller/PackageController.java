package com.controller;

import com.model.Package;
import com.repository.PackageRepository;
import com.service.PackageService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.sun.deploy.trace.Trace.flush;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/package")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @Autowired
    private PackageRepository packageRepository;


    @RequestMapping(value = "/create", method = POST)//,headers = "content-type=application/x-www-form-urlencoded")
    public Package create(@RequestBody Package pack){
        return packageService.create(pack);
    }

    @RequestMapping(value = "/remove/{id}", method = DELETE)
    public Package remove(@PathVariable long id){

        Package pack = packageRepository.findOneById(id);
        packageService.remove(pack);
        packageRepository.flush();
        return pack;
    }

   /* @RequestMapping(value = "/remove", method = DELETE)
    //@ResponseStatus(HttpStatus.OK)
    public Package remove( Package pack) {
        //  packageRepository.delete(packageRepository.findOneById(id));
        packageRepository.delete(pack);
        return pack;

    }*/
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

}
