package com.cyclingrecord.controllers;


import com.cyclingrecord.data.EntryRepository;
import com.cyclingrecord.models.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.*;


@Controller
public class MonthlyTableController {

    @Autowired
    private EntryRepository entryRepository;

    public ArrayList<LocalDate> getMonth() {
        ArrayList<LocalDate> entireMonth = new ArrayList<>();
        int getMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
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

    public ArrayList<String> formatMonth() {
        ArrayList<String> dateString = new ArrayList<>();
        for (int i = 0; i <= getMonth().size() - 1; i++) {
            dateString.add(formatDate(getMonth().get(i)));
        }
        return dateString;
    }

    public static float sum(List<Float> list) {
        int sum = 0;
        for (float i: list) {
            sum += i;
        }
        return sum;
    }


    @RequestMapping("monthly")
    public String showMonthlyTable(@ModelAttribute Entry entry, Model model, @RequestParam(required=false) String date, @RequestParam(required=false) Float distance, @RequestParam(required=false) Float time) throws Exception {

        if(date == null || distance == null || time == null){
            model.addAttribute("entries", entryRepository.findAll());
        } else {
            LocalDate localDate = LocalDate.parse(date);
            String formatDate = formatDate(localDate);
            List<Integer> weekdays = new ArrayList();
            Map<Integer, List<Float>> distanceByWeek = new HashMap<>();

        for (int i = 0; i < getMonth().size(); i++) {
            double speed = Math.round((distance / (time / 60)) * 100.0) / 100.0;
            Entry existingDate = entryRepository.findByDate(formatMonth().get(i));

            if (existingDate == null || !formatMonth().get(i).equals(existingDate.getDate())) {
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

                DayOfWeek dayOfWeek = getMonth().get(i).getDayOfWeek();
                int dayNumber = dayOfWeek.getValue();
                weekdays.add(dayNumber);

                LocalDate weekday = getMonth().get(i);
                int week = weekday.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

                if (existingDate == null || !formatMonth().get(i).equals(existingDate.getDate())) {
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
//                DayOfWeek dayOfWeek = getMonth().get(i).getDayOfWeek();
//                int dayNumber = dayOfWeek.getValue();
//                weekdays.add(dayNumber);
//
//                LocalDate weekday = getMonth().get(i);
//                int week = weekday.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

                    List<Float> distances = new ArrayList<>();
                    distances.add(existingDate.getDistance());
                    for (Float listOfDistances : distances) {
                        distanceByWeek.computeIfAbsent(week, k -> new ArrayList<>()).add(listOfDistances);
                    }
                    //distanceByWeek.put(week, distances);

//                String dayToCheck = formatDate(getMonth().get(i));
//                ArrayList<Integer> allDistances = new ArrayList<>();

//                try {
//                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyclingrecord", "cyclingrecord", "Hmveonl00");
//                    String sql = ("SELECT * FROM entry");
//                    PreparedStatement ps = con.prepareStatement(sql);
//                    ResultSet rs = ps.executeQuery();
//
//                    while (rs.next()) {
//                        String dateToMatch = rs.getString("date");
//                        int distanceToSum = rs.getInt("distance");
//                        allDistances.add(distanceToSum);
//                    }

//                    rs.close();
//                    ps.close();
//                    con.close();
//                } catch (SQLException ex) {
//                    System.out.println(ex.getMessage());
//                }

                }
                model.addAttribute("entries", entryRepository.findAll());
                for (int j = 0; j < getMonth().size(); j++) {
                    if (distanceByWeek.containsKey(j) && dayNumber == 7) {
                        existingDate.setTotalDistance(sum(distanceByWeek.get(j)));
                    }
                }
            }
            entryRepository.save(existingDate);
        }
        }
        return "monthly";
    }
}




