package com.springboot.oauth2.springboot2oauth2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/hallo")
public class ControllerHallo {

    @PreAuthorize("hasAuthority('ROLE_MASTERADMIN')")
    @GetMapping
    public String hello(){
        return "Halaman ini di protect dengan OAuth2";
    }
}
