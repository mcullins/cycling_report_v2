package com.cyclingrecord.controllers;

import com.cyclingrecord.data.EntryRepository;
import com.cyclingrecord.models.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class InputController {

    public EntryRepository entryRepository;

    @RequestMapping()
    public String inputForm(Model model){
        model.addAttribute(new Entry());
        return "form";
    }

    @PostMapping()
    public String processInput(Model model, Entry newEntry, @RequestParam String date, @RequestParam int time, @RequestParam int distance) {
        entryRepository.save(newEntry);
        return "monthly";
    }
}
