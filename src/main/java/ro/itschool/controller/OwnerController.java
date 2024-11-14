package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Owner;
import ro.itschool.exception.OwnerNotFoundException;
import ro.itschool.service.OwnerService;

import java.util.List;

@Controller
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    // Get all owners
    @GetMapping("/list")
    public String listOwners(Model model) {
        List<Owner> owners = ownerService.getAllOwners();
        model.addAttribute("owners", owners);
        return "owner/list";
    }

    // Get owner by id
    @GetMapping("/details/{id}")
    public String ownerDetails(@PathVariable Integer id, Model model) {
        Owner owner = ownerService.getOwnerById(id)
                .orElseThrow(() -> new OwnerNotFoundException("Owner not found"));
        model.addAttribute("owner", owner);
        return "owner/details";
    }

    // Create a new owner page
    @GetMapping("/create")
    public String createOwnerForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owner/create";
    }

    // Create Owner
    @PostMapping("/save")
    public String saveOwner(@ModelAttribute Owner owner) {
        ownerService.saveOwner(owner);
        return "redirect:/owner/list";
    }

    // Update owner page
    @GetMapping("/update/{id}")
    public String updateOwnerForm(@PathVariable Integer id, Model model) {
        Owner owner = ownerService.getOwnerById(id)
                .orElseThrow(() -> new OwnerNotFoundException("Owner not found"));
        model.addAttribute("owner", owner);
        return "owner/update";
    }

    // Update owner
    @PostMapping("/update/{id}")
    public String updateOwner(@PathVariable Integer id, @ModelAttribute Owner owner) {
        owner.setId(id);
        ownerService.updateOwner(owner);
        return "redirect:/owner/list";
    }

    // Delete owner by id
    @GetMapping("/delete/{id}")
    public String deleteOwner(@PathVariable Integer id) {
        ownerService.deleteOwnerById(id);
        return "redirect:/owner/list";
    }
}
