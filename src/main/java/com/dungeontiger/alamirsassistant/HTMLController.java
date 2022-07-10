package com.dungeontiger.alamirsassistant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HTMLController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/dice")
    public String dice() {
        return "dice";
    }

    @GetMapping("/tables")
    public String tables() {
        return "tables";
    }
}
