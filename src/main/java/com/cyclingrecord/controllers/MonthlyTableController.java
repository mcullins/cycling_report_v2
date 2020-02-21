package com.cyclingrecord.controllers;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import com.cyclingrecord.data.EntryData;
import com.cyclingrecord.models.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class MonthlyTableController {


    static public ArrayList<String> getCurrentMonth(){
        int month = Calendar.MONTH;
        YearMonth currentMonth = YearMonth.of(2020, month);
        Locale locale = Locale.US;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM", locale);
        ArrayList<String> outputs = new ArrayList<>();
        for (int i = 1; i <= currentMonth.lengthOfMonth(); i++){
            LocalDate ld = currentMonth.atDay(i);
            String output = ld.format(format);
            outputs.add(output);

        }
        return outputs;
    }

    @RequestMapping("monthly")
    public String showMonthlyTable(Model model, Entry newEntry, String date){

        for (int i=0; i <= getCurrentMonth().size()-1; i++){
            if (getCurrentMonth().get(i).equals(date)){
   //             EntryData.add(newEntry);
            }
        }

        model.addAttribute("month", getCurrentMonth());
        return "monthly";
    }

}
