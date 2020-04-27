package com.cyclingrecord.controllers;

import com.cyclingrecord.data.EntryRepository;
import com.cyclingrecord.models.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class InputController {

    @Autowired
    public EntryRepository entryRepository;

    @RequestMapping("/form")
    public String inputForm(Model model){
        Entry entry = new Entry(0.0f,0.0f);
        model.addAttribute("entry", entry);
        return "form";
    }

    @PostMapping("/form")
    public String processInput(Entry newEntry, @RequestParam(required=false) String date, @RequestParam(required=false) Float time, @RequestParam(required=false) Float distance) {
        entryRepository.save(newEntry);
        return "monthly";
    }
}
