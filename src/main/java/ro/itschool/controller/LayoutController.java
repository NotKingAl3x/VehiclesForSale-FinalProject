package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/layout")
@RequiredArgsConstructor
public class LayoutController {
    @GetMapping()
    public String showLayoutPage() {
        return "layout";
    }
}
