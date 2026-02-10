package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * CONTROLLER - Home Controller
 * Handles home page requests
 */
@Controller
public class HomeController {

    /**
     * Home page
     * URL: GET /
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Home");
        model.addAttribute("welcomeMessage",
                "Welcome to the MVC World !!!");

        return "home";  // Resolves to /WEB-INF/views/home.jsp
    }

    @GetMapping("/ping")
    public @ResponseBody String ping() {
        return "pong";  // Resolves to /WEB-INF/views/home.jsp
    }
}
