package com.cyclingrecord.controllers;


import com.cyclingrecord.data.EntryRepository;
import com.cyclingrecord.models.Entry;
import com.cyclingrecord.models.SatTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class MonthlyTableController {

    @Autowired
    private EntryRepository entryRepository;

    public ArrayList<LocalDate> getMonth() {
        ArrayList<LocalDate> entireMonth = new ArrayList<>();
        int getMonth = Calendar.MONTH + 1;
        YearMonth currentMonth = YearMonth.of(2020, getMonth);
        for (int i = 1; i < currentMonth.lengthOfMonth() + 1; i++) {
            LocalDate ld = currentMonth.atDay(i);
            entireMonth.add(ld);
        }
        return entireMonth;
    }

    public String formatDate(LocalDate dateToFormat) {
        LocalDate ld = dateToFormat;
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM");
        String dayString = ld.format(myFormatObj);
        return dayString;
    }

    public String formatDateToMatch(LocalDate dateToFormat) {
        LocalDate ld = dateToFormat;
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dayString = ld.format(myFormatObj);
        return dayString;
    }

    public ArrayList<String> formatMonth() {
        ArrayList<String> dateString = new ArrayList<>();
        for (int i = 0; i <= getMonth().size() - 1; i++) {
            dateString.add(formatDate(getMonth().get(i)));
        }
        return dateString;
    }

    public Float sumDistance(ArrayList<Float> distance) {
        Float sum = 0.0f;
        for (int i = 0; i < distance.size(); i++) {
            sum += distance.get(i);
        }
        return sum;
    }

    @RequestMapping("monthly")
    public String showMonthlyTable(@ModelAttribute Entry entries, Model model, @RequestParam String date, @RequestParam float distance, @RequestParam float time) {
        LocalDate localDate = LocalDate.parse(date);
        String formatDate = formatDate(localDate);
        SatTotal total = new SatTotal();
        ArrayList<Integer> weekdays = new ArrayList();
        for (int i = 0; i < getMonth().size(); i++) {

            double speed = Math.round((distance / (time / 60)) * 100.0) / 100.0;
            Entry existingDate = entryRepository.findByDate(formatMonth().get(i));


            if (existingDate == null || !existingDate.getDate().equals(formatMonth().get(i))) {
                Entry newEntry = new Entry();
                newEntry.setDate(formatMonth().get(i));

                if (formatMonth().get(i).equals(formatDate)) {
                    newEntry.setDistance(distance);
                    newEntry.setTime(time);
                    newEntry.setSpeed(speed);


                }
                entryRepository.save(newEntry);
            } else {
                if (formatMonth().get(i).equals(formatDate)) {
                    existingDate.setTime(time);
                    existingDate.setDistance(distance);
                    existingDate.setSpeed(speed);
                    entryRepository.save(existingDate);
                }


//                ArrayList<Float> sum = new ArrayList<>();
//                Iterable<Entry> existingDistance = entryRepository.findAll();
//                for (Entry e : existingDistance) {
//                    sum.add(e.getDistance());
//                }
//                float totalDistance = sumDistance(sum);

            }

            Calendar calendar = Calendar.getInstance();
            int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);


            ArrayList<Float> sum = new ArrayList<>();
            Iterable<Entry> existingDistance = entryRepository.findAll();
            for (Entry e : existingDistance) {
                sum.add(e.getDistance());
            }
            float totalDistance = sumDistance(sum);


//            DayOfWeek dayOfWeek = month.get(i).getDayOfWeek();
//            int dayNumber = dayOfWeek.getValue();
//            weekdays.add(dayNumber);
//            for(int j : weekdays){
//                model.addAttribute("totalDistance", j);
//            }

            //   if (dayNumber==6) {
            //       total.setTotal(totalDistance);

        }

    //

for(int k = 0; k<getMonth().size(); k++) {
            ArrayList<LocalDate> month = getMonth();
            DayOfWeek dayOfWeek = month.get(k).getDayOfWeek();
    int dayNumber = dayOfWeek.getValue();
    weekdays.add(dayNumber);
        }


            for(int j : weekdays){
                model.addAttribute("totalDistance", j);
            }


        model.addAttribute("entries", entryRepository.findAll());


        return "monthly";
    }
}
