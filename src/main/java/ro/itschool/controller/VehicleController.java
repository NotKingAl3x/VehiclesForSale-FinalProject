package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Vehicle;
import ro.itschool.exception.VehicleNotFoundException;
import ro.itschool.service.VehicleService;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

//    // Get all vehicles
//    @GetMapping("/list")
//    public String listVehicles(Model model) {
//        List<Vehicle> vehicles = vehicleService.getAllVehicles();
//        model.addAttribute("vehicles", vehicles);
//        return "vehicle/list";
//    }

    @GetMapping
    public String listVehicles(@RequestParam(required = false) boolean sortByYear, Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();

        if (sortByYear) {
            vehicles = vehicles.stream()
                    .sorted(Comparator.comparingInt(Vehicle::getYear))
                    .toList();
        }

        model.addAttribute("vehicles", vehicles);
        model.addAttribute("sortByYear", sortByYear); // Preserve the checkbox state
        return "vehicle/list";
    }

    // Get vehicle by id
    @GetMapping("/details/{id}")
    public String vehicleDetails(@PathVariable Integer id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
        model.addAttribute("vehicle", vehicle);
        return "vehicle/details";
    }

    // Create vehicle page
    @GetMapping("/create")
    public String createVehicleForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "vehicle/create";
    }

    // Create vehicle
    @PostMapping("/save")
    public String saveVehicle(@ModelAttribute Vehicle vehicle) {
        vehicleService.saveVehicle(vehicle);
        return "redirect:/vehicle/list";
    }

    // Update vehicle page
    @GetMapping("/update/{id}")
    public String updateVehicleForm(@PathVariable Integer id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
        model.addAttribute("vehicle", vehicle);
        return "vehicle/update";
    }

    // Update vehicle
    @PostMapping("/update/{id}")
    public String updateVehicle(@PathVariable Integer id, @ModelAttribute Vehicle vehicle) {
        vehicle.setId(id);
        vehicleService.updateVehicle(vehicle);
        return "redirect:/vehicle/list";
    }

    // Delete vehicle by id
    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Integer id) {
        vehicleService.deleteVehicleById(id);
        return "redirect:/vehicle/list";
    }


}
