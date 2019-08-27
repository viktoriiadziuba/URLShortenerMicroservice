package com.viktoriia.controller;

import com.viktoriia.dto.Dto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UrlsController {
    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping(value = "/addUrl")
    public String addUrl(Model model) {
        Dto dto = new Dto();
        model.addAttribute("dto", dto);
        return "addUrls";
    }
}
