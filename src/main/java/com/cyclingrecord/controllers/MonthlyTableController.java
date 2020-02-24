package com.cyclingrecord.controllers;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import com.cyclingrecord.data.EntryData;
import com.cyclingrecord.models.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class MonthlyTableController {

    static public ArrayList<Entry> getCurrentMonth(){
        int month = Calendar.MONTH;
        YearMonth currentMonth = YearMonth.of(2020, month);
        Locale locale = Locale.US;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM", locale);
        ArrayList<Entry> outputs = new ArrayList<>();
        for (int i = 1; i <= currentMonth.lengthOfMonth(); i++){
            LocalDate ld = currentMonth.atDay(i);
            String date = ld.format(format);
            Date output = (Date) format.parse(date);
            Entry entries = new Entry();
            entries.setDate(output);
            outputs.add(entries);
        }
        return outputs;
    }

    @RequestMapping("monthly")
    public String showMonthlyTable(Model model){

        model.addAttribute("month", getCurrentMonth());
        return "monthly";
    }

}
