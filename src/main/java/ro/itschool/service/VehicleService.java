package ro.itschool.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Vehicle;
import ro.itschool.exception.VehicleNotFoundException;
import ro.itschool.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    //Create Vehicle
    public Vehicle saveVehicle(Vehicle vehicle) {
            if (vehicle.getId() != null && vehicleRepository.existsById(vehicle.getId())) {
                throw new VehicleNotFoundException("Vehicle with ID " + vehicle.getId() + " already exists");
            }
            return vehicleRepository.save(vehicle);
    }

    //Update Vehicle
    public Vehicle updateVehicle(Vehicle vehicle) {
        if(vehicle.getId() == null || !vehicleRepository.existsById(vehicle.getId())){
            throw new VehicleNotFoundException("Vehicle doesn't exist");
        }
        return vehicleRepository.save(vehicle);
    }

    //Get All Vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    //Get Vehicle By ID
    public Optional<Vehicle> getVehicleById(Integer id) {
        return vehicleRepository.findById(id);
    }

    //Get Vehicle By VIN
    public Optional<Vehicle> getVehicleByVin(String vin) {
        return vehicleRepository.findByVin(vin);
    }

    //Delete Vehicle By ID
    public void deleteVehicleById(Integer id) {
        vehicleRepository.deleteById(id);
    }


}
