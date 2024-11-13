package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.service.OwnerService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnersController {

    private final OwnerService ownerService;

    @GetMapping
    public String showAllOwners(Model model) {
        model.addAttribute("owners", ownerService.getAllOwners());
        return "owners";
    }
}
