package com.vikings.controller;

import com.vikings.dao.TestDAO;
import com.vikings.domain.TestDomainObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Test controller
 */
@Controller
public class TestController {
    
    // Controllers shouldn't be touching DAOs.
    // But this is just a test.
    @Autowired
    TestDAO testDAO;
    
    // @GetMapping("/") means that the default page url
    // http://localhost:8080/308Vikings/ gets mapped to
    // this controller. This should be changed eventually
    @GetMapping("/")
    public String index(Model model) {
        boolean result = testDAO.dbAliveTest();
        if (result)
            model.addAttribute("result", "Yes!");
        return "index";
    }
    
    @GetMapping("/test/getObject/{id}")
    public String testGetObject(@PathVariable String id, Model model) {
        TestDomainObj result = testDAO.getObj(id);
        model.addAttribute("result", result);
        return "getObject";
    }
    
}
