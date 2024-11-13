package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.service.VehicleService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vehicles")
public class VehiclesController {

    private final VehicleService vehicleService;

    @GetMapping
    public String showAllVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        return "vehicles";
    }
}
