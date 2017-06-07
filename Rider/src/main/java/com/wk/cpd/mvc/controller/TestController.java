package com.wk.cpd.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wk.cpd.mvc.service.TestService;

/**
 * @description:
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;
    
    @RequestMapping(value = "/test", method = { RequestMethod.POST })
    public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String id = request.getParameter("id");
       testService.insert(Integer.valueOf(id));
        
    }
}
