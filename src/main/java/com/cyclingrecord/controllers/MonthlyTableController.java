package com.cyclingrecord.controllers;


import com.cyclingrecord.models.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class MonthlyTableController {


    public ArrayList<String> getMonth() {

        ArrayList<String> monthString = new ArrayList<>();
        int getMonth = Calendar.MONTH;
        YearMonth currentMonth = YearMonth.of(2020, getMonth);

        for (int i = 1; i <= currentMonth.lengthOfMonth(); i++) {
            LocalDate ld = currentMonth.atDay(i);
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM");

            String dayString = ld.format(myFormatObj);

            monthString.add(dayString);
        }
        return monthString;
    }


    @RequestMapping("monthly")
    public String showMonthlyTable(Model model, @RequestParam String date, @RequestParam int distance, @RequestParam int time){
        ArrayList<Entry> entryList = new ArrayList<>();


        for (int i = 0; i<getMonth().size(); i++){
            Entry entries = new Entry();
            entries.setDate(getMonth().get(i));
            if(entries.getDate().equals("21-Feb")){
                entries.setDistance(distance);
                entries.setTime(time);
            }
            entryList.add(entries);
        }

        model.addAttribute("entries", entryList);
        return "monthly";
    }
}
