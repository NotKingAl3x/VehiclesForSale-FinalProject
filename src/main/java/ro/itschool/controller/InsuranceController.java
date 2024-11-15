package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Insurance;
import ro.itschool.exception.InsuranceNotFoundException;
import ro.itschool.service.InsuranceService;

import java.util.List;

@Controller
@RequestMapping("/insurance")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

//    // Get All Insurances
//    @GetMapping("/list")
//    public String listInsurances(Model model) {
//        List<Insurance> insurances = insuranceService.getAllInsurances();
//        model.addAttribute("insurances", insurances);
//        return "insurance/list";
//    }

    @GetMapping
    public String listInsurances(
            @RequestParam(required = false, defaultValue = "false") boolean onlyWithVehicles,
            Model model) {

        List<Insurance> insurances = insuranceService.getAllInsurances();

        if (onlyWithVehicles) {
            insurances = insurances.stream()
                    .filter(insurance -> !insurance.getVehicles().isEmpty())
                    .toList();
        }

        model.addAttribute("insurances", insurances);
        model.addAttribute("onlyWithVehicles", onlyWithVehicles);

        return "insurance/list";
    }


    // View insurance by ID
    @GetMapping("/details/{id}")
    public String insuranceDetails(@PathVariable Integer id, Model model) {
        Insurance insurance = insuranceService.getInsuranceById(id)
                .orElseThrow(() -> new InsuranceNotFoundException("Insurance not found"));
        model.addAttribute("insurance", insurance);
        return "insurance/details";
    }

    // Create new insurance page
    @GetMapping("/create")
    public String createInsuranceForm(Model model) {
        model.addAttribute("insurance", new Insurance());
        return "insurance/create";
    }

    // Create insurance
    @PostMapping("/save")
    public String saveInsurance(@ModelAttribute Insurance insurance) {
        insuranceService.saveInsurance(insurance);
        return "redirect:/insurance/list";
    }

    // Update insurance page
    @GetMapping("/update/{id}")
    public String updateInsuranceForm(@PathVariable Integer id, Model model) {
        Insurance insurance = insuranceService.getInsuranceById(id)
                .orElseThrow(() -> new InsuranceNotFoundException("Insurance not found"));
        model.addAttribute("insurance", insurance);
        return "insurance/update";
    }

    // Update insurance
    @PostMapping("/update/{id}")
    public String updateInsurance(@PathVariable Integer id, @ModelAttribute Insurance insurance) {
        insurance.setId(id);
        insuranceService.updateInsurance(insurance);
        return "redirect:/insurance/list";
    }

    // Delete insurance by id
    @GetMapping("/delete/{id}")
    public String deleteInsurance(@PathVariable Integer id) {
        insuranceService.deleteInsuranceById(id);
        return "redirect:/insurance/list";
    }
}
