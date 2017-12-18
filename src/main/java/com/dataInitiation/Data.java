package com.dataInitiation;

import com.model.Package;
import com.model.Transport;
import com.model.User;
import com.model.Warehouse;
import com.repository.PackageRepository;
import com.repository.UserRepository;
import com.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Component
public class Data implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    public void setPackageRepository(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){

        User user = new User();
        user.setFirstName("Karol");
        user.setLastName("Cichowski");
        user.setAddress("Graniczna 25");
        user.setTelephoneNumber("700880774");
        user.setFirmName("Swinouscie Comapny");
        userRepository.save(user);

        User user1 = new User();
        user1.setFirstName("Grzegorz");
        user1.setLastName("Polak");
        user1.setAddress("Graniczna 25");
        user1.setTelephoneNumber("700880774");
        user1.setFirmName("Swinouscie Comapny");
        userRepository.save(user1);

        User user2 = new User();
        user2.setFirstName("Mateusz");
        user2.setLastName("świdzinski");
        user2.setAddress("Graniczna 25");
        user2.setTelephoneNumber("700880774");
        user2.setFirmName("Swinouscie Comapny");
        userRepository.save(user2);

        User user3 = new User();
        user3.setFirstName("Asia");
        user3.setLastName("Motyka");
        user3.setAddress("Graniczna 25");
        user3.setTelephoneNumber("700880774");
        user3.setFirmName("Cwel Comapny");
        userRepository.save(user3);

        Warehouse warehouse = new Warehouse();
        warehouse.setName("Magazyn Amazon");
        warehouse.setAddress("Sportowa 16");
        warehouse.setTelephoneNumber("74-816-342-465");
        warehouseRepository.save(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setName("Magazyn Budowlany");
        warehouse1.setAddress("Wrocławska 25/A");
        warehouse1.setTelephoneNumber("74-532-123-917");
        warehouseRepository.save(warehouse1);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setName("Magazyn");
        warehouse2.setAddress("Wrocławska");
        warehouse2.setTelephoneNumber("74-532-123-917");
        warehouseRepository.save(warehouse2);

        Package pack = new Package();
        pack.setName("Koła Audi");
        pack.setCapacity(20.0);
        pack.setDescription("Kolo jest okrągłe");
        pack.setWeight(29.0);
        pack.setUser(user);
        pack.setxDimension(18.2);
        pack.setyDimension(18.2);
        pack.setzDimension(18.2);
        pack.setTimeString(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        pack.setWarehouse(warehouse);
        packageRepository.save(pack);

       Transport transport = new Transport();
       //transport.set


    }
}
