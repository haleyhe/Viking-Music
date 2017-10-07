package com.vikings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Test controller
 */
@Controller
public class IndexController {
    
    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }
    
}
