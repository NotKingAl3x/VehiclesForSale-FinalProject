package ro.itschool;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.itschool.entity.Owner;
import ro.itschool.entity.Vehicle;
import ro.itschool.service.OwnerService;
import ro.itschool.service.VehicleService;
import java.time.LocalDate;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner initData(OwnerService ownerService, VehicleService vehicleService) {
        return args -> {

            Owner owner1 = new Owner("John Doe", LocalDate.of(1980, 5, 15), 'M',"Elm Street, House 21");
            Owner owner2 = new Owner("Jane Smith", LocalDate.of(1990, 7, 25), 'F', "Houlley Valley Street, House 6");

            owner1 = ownerService.saveOwner(owner1);
            owner2 = ownerService.saveOwner(owner2);

            Vehicle vehicle1 = new Vehicle(null, "1HGCM82633A123456", "Sedan", 2015, "Toyota", "Corolla", "Blue", owner1);
            Vehicle vehicle2 = new Vehicle(null, "1HGCM82633A654321", "SUV", 2018, "Honda", "CR-V", "Black", owner1);
            Vehicle vehicle3 = new Vehicle(null, "1HGCM82633A789012", "Truck", 2017, "Ford", "F-150", "Red", owner2);

            vehicleService.saveVehicle(vehicle1);
            vehicleService.saveVehicle(vehicle2);
            vehicleService.saveVehicle(vehicle3);
        };
    }
}
