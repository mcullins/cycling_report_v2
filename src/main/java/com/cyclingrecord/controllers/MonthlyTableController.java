package com.cyclingrecord.controllers;

import com.cyclingrecord.data.EntryRepository;
import com.cyclingrecord.models.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class MonthlyTableController {

    @Autowired
    private EntryRepository entryRepository;

    public EntryRepository getCurrentMonth(){
        int month = Calendar.MONTH;
        YearMonth currentMonth = YearMonth.of(2020, month+1);
        ArrayList<Entry> outputs = new ArrayList<>();
        for (int i = 1; i <= currentMonth.lengthOfMonth(); i++){
            LocalDate ld = currentMonth.atDay(i);
            String formattedDate = ld.format(DateTimeFormatter.ofPattern("dd-MMM"));
            Entry entries = new Entry(formattedDate);
            entryRepository.save(entries);
        }
        return entryRepository;
    }

    public ArrayList<Entry> matchingFormatDate(){
        int month = Calendar.MONTH;
        YearMonth currentMonth = YearMonth.of(2020, month+1);
        ArrayList<Entry> outputs = new ArrayList<>();
        for (int i = 1; i <= currentMonth.lengthOfMonth(); i++){
            LocalDate ld = currentMonth.atDay(i);
            String formattedDate = ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Entry entries = new Entry(formattedDate);
            outputs.add(entries);
        }
        return outputs;
    }

    @RequestMapping("monthly")
    public String showMonthlyTable(Model model, @RequestParam String date, @RequestParam int time, @RequestParam int distance){
        getCurrentMonth();
        ArrayList<Entry> formatDates = matchingFormatDate();
        for(int i=0; i<formatDates.size(); i++) {
            if (date.equals(formatDates.get(i).getDate())){
                entryRepository.findByDate(date).setDistance(distance);
                entryRepository.findByDate(date).setTime(time);
            }
        }
        model.addAttribute("entries", entryRepository);
        return "monthly";
    }

}
