package com.dataInitiation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.model.Package;
import com.model.Transport;
import com.model.User;
import com.model.Warehouse;
import com.repository.PackageRepository;
import com.repository.TransportRepository;
import com.repository.UserRepository;
import com.repository.WarehouseRepository;

@Component
public class Data implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired private TransportRepository transportRepository;

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
        user3.setFirmName("Nazwa firmy");
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
        pack.setName("Paczka 2x2x1");
        pack.setCapacity(20.0);
        pack.setDescription("Kolo jest okrągłe");
        pack.setWeight(29.0);
        pack.setUser(user);
        pack.setxDimension(2.0);
        pack.setyDimension(2.0);
        pack.setzDimension(1.0);
        pack.setTimeString(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        pack.setWarehouse(warehouse);
        packageRepository.save(pack);

		Package pack2 = new Package();
		pack2.setName("Paczka 2x1x1");
		pack2.setCapacity(1.0);
		pack2.setDescription("Kolo jest okrągłe");
		pack2.setWeight(29.0);
		pack2.setUser(user);
		pack2.setxDimension(2.0);
		pack2.setyDimension(1.0);
		pack2.setzDimension(1.0);
		pack2.setTimeString(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
		pack2.setWarehouse(warehouse);
		packageRepository.save(pack2);

		Package pack3 = new Package();
		pack3.setName("Paczka 1x1x1");
		pack3.setCapacity(1.0);
		pack3.setDescription("Kolo jest okrągłe");
		pack3.setWeight(29.0);
		pack3.setUser(user);
		pack3.setxDimension(1.0);
		pack3.setyDimension(1.0);
		pack3.setzDimension(1.0);
		pack3.setTimeString(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
		pack3.setWarehouse(warehouse);
		packageRepository.save(pack3);

        Package pack4 = new Package();
        pack4.setName("Paczka 1x1x1");
        pack4.setCapacity(1.0);
        pack4.setDescription("Kolo jest okrągłe");
        pack4.setWeight(29.0);
        pack4.setUser(user);
        pack4.setxDimension(1.0);
        pack4.setyDimension(1.0);
        pack4.setzDimension(1.0);
        pack4.setTimeString(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        pack4.setWarehouse(warehouse);
        packageRepository.save(pack4);

		Package pack5 = new Package();
		pack5.setName("Paczka 2x1x1");
		pack5.setCapacity(1.0);
		pack5.setDescription("Kolo jest okrągłe");
		pack5.setWeight(29.0);
		pack5.setUser(user);
		pack5.setxDimension(2.0);
		pack5.setyDimension(1.0);
		pack5.setzDimension(1.0);
		pack5.setTimeString(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
		pack5.setWarehouse(warehouse);
		packageRepository.save(pack5);

		Package pack6 = new Package();
		pack6.setName("Paczka 2x2x2");
		pack6.setCapacity(1.0);
		pack6.setDescription("Kolo jest okrągłe");
		pack6.setWeight(29.0);
		pack6.setUser(user);
		pack6.setxDimension(2.0);
		pack6.setyDimension(2.0);
		pack6.setzDimension(2.0);
		pack6.setTimeString(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
		pack6.setWarehouse(warehouse);
		packageRepository.save(pack6);

		Transport transport = new Transport();
       transport.setTransportName("Transport 4x1x1");
       transport.setxDimension(4.0);
       transport.setyDimension(1.0);
       transport.setzDimension(1.0);
       transport.setDriverFirstName("Blazej");
       transport.setDriverLastName("Rejnowski");
       transport.setDriverTelephoneName("Rejnowski");
       transport.setMaxCapacity(12300.0);
       transport.setMaxWeight(100.0);
       transportRepository.save(transport);

		Transport transport2 = new Transport();
		transport2.setTransportName("Transport 2x2x1");
		transport2.setxDimension(2.0);
		transport2.setyDimension(2.0);
		transport2.setzDimension(1.0);
		transport2.setDriverFirstName("Blazej");
		transport2.setDriverLastName("Rejnowski");
		transport2.setDriverTelephoneName("Rejnowski");
		transport2.setMaxCapacity(12300.0);
		transport2.setMaxWeight(100.0);
		transportRepository.save(transport2);

		Transport transport3 = new Transport();
		transport3.setTransportName("Transport 3x2x2");
		transport3.setxDimension(3.0);
		transport3.setyDimension(2.0);
		transport3.setzDimension(2.0);
		transport3.setDriverFirstName("Blazej");
		transport3.setDriverLastName("Rejnowski");
		transport3.setDriverTelephoneName("Rejnowski");
		transport3.setMaxCapacity(12300.0);
		transport3.setMaxWeight(100.0);
		transportRepository.save(transport3);
    }
}
