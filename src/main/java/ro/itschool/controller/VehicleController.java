package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Vehicle;
import ro.itschool.exception.VehicleNotFoundException;
import ro.itschool.service.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    // Create Vehicle
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(Vehicle vehicle){
        if(vehicle.getId() != null && vehicleService.getVehicleById(vehicle.getId()).isPresent()){
            throw new VehicleNotFoundException("Vehicle with ID " + vehicle.getId() + " already exists");
        }
        Vehicle createdVehicle = vehicleService.saveVehicle(vehicle);
        return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
    }

    // Update Vehicle
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle){
        if(!vehicleService.getVehicleById(id).isPresent()){
            throw new VehicleNotFoundException("Vehicle with ID " + id + " doesn't exist");
        }
        vehicle.setId(id);
        Vehicle updatedVehicle = vehicleService.updateVehicle(vehicle);
        return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
    }

    //Get All Vehicles
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    //Get Vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Integer id){
        return vehicleService.getVehicleById(id)
                .map(vehicle -> new ResponseEntity<>(vehicle, HttpStatus.OK))
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with ID " + id + " not found"));
    }
    //Get Vehicle by VIN
    @GetMapping("/{vin}")
    public ResponseEntity<Vehicle> getVehicleByVin(@PathVariable String vin){
        return vehicleService.getVehicleByVin(vin)
                .map(vehicle -> new ResponseEntity<>(vehicle, HttpStatus.OK))
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with ID " + vin + " not found"));
    }

    //Delete Owner by ID
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVehicleById(@PathVariable Integer id){
        if(!vehicleService.getVehicleById(id).isPresent()){
            throw new VehicleNotFoundException("Vehicle with ID " + id + " not found");
        }
        vehicleService.deleteVehicleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
