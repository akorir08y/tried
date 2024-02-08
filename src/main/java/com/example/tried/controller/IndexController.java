package com.example.tried.controller;

import com.example.tried.dto.example.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front")
public class IndexController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("account", new Account());
        return "index";
    }


    @PostMapping(value="/account")
    public String submit(Account account, Model model) {
        model.addAttribute("account", account);
        return "saved";
    }
}