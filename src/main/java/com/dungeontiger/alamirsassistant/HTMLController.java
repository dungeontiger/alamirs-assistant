package com.dungeontiger.alamirsassistant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HTMLController extends BaseController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/dice")
    public String dice() {
        return "dice";
    }

    @GetMapping("/tables")
    public String tables(Model model) {
        model.addAttribute("campaign", tableManager.getCampaigns());
        return "tables";
    }

    @GetMapping("/initiative")
    public String initiative() {
        return "initiative";
    }
}
