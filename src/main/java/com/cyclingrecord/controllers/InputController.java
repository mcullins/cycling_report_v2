package com.cyclingrecord.controllers;

import com.cyclingrecord.models.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class InputController {

    @RequestMapping()
    public String inputForm(Model model){
 //       model.addAttribute(new Entry());
        return "form";
    }

    @PostMapping()
    public String processInput(@RequestParam String date, Model model, Entry newEntry) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date date1 = formatter.parse(date);
        model.addAttribute("date", date1);
        return "monthly";
    }
}
