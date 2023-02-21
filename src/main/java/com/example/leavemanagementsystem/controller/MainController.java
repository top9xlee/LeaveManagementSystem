package com.example.leavemanagementsystem.controller;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage(Model model) {
        return "redirect:/user";
    }

    @GetMapping("/403")
    public String notFound() {
        return "403";
    }
}