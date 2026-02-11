package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// @Controller is a stereotype annotation that indicates that this class is a Spring MVC controller
// It is responsible for handling HTTP requests and returning appropriate responses (views or data)
// Use the @ResponseBody annotation on methods to indicate that the 
// return value should be used as the response body (e.g., for REST APIs)

@Controller
public class HomeController {

    // This method handles GET requests to the root URL ("/") and returns the "home" view
    // It also adds attributes to the model that can be accessed in the JSP view (home.jsp)
    // The @GetMapping annotation is a shortcut for @RequestMapping(method = RequestMethod.GET)
    // "home" will be resolved to /WEB-INF/views/home.jsp 
    // by the InternalResourceViewResolver defined in WebMvcConfig
    // home.jsp can access the model attributes "pageTitle" and "welcomeMessage" 
    // to display dynamic content. E.g. ${pageTitle} and ${welcomeMessage} in the JSP file.
    
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
