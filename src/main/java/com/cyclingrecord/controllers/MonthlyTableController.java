package com.cyclingrecord.controllers;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import com.cyclingrecord.data.EntryData;
import com.cyclingrecord.models.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class MonthlyTableController {

    static public ArrayList<Entry> getCurrentMonth(){
        int month = Calendar.MONTH;
        YearMonth currentMonth = YearMonth.of(2020, month);
        Locale locale = Locale.US;
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM", locale);
        ArrayList<Entry> outputs = new ArrayList<>();
        for (int i = 1; i <= currentMonth.lengthOfMonth(); i++){
            LocalDate ld = currentMonth.atDay(i);
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Date output = Date.from(ld.atStartOfDay(defaultZoneId).toInstant());
            Date dt = format.parse(format.format(output));
            Entry entries = new Entry(output);
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
