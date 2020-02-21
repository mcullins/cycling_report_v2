package com.cyclingrecord.controllers;

import com.cyclingrecord.data.EntryData;
import com.cyclingrecord.models.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class InputController {

    @RequestMapping()
    public String inputForm(Model model){
        model.addAttribute(new Entry());
        return "form";
    }

    @PostMapping()
    public String processInput(Model model, Entry newEntry){
        EntryData.add(newEntry);
        return "monthly";
    }
}
