package com.cs51540.data;

import java.awt.Color;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.cs51540.interfaces.IDataRepository;
import com.cs51540.models.Schedule;
import com.cs51540.models.User;

public class TestDataRepository implements IDataRepository {

    private final User[] Users;
    private final ArrayList<Schedule> Schedules;
    private Map<Integer, Schedule> schedulesMap;


    public TestDataRepository() {
        Users = new User[4];
        Users[0] = new User(0, Color.blue, "Kenneth");
        Users[1] = new User(1, Color.green, "Henry");
        Users[2] = new User(2, Color.red, "Jacob");
        Users[3] = new User(3, Color.yellow, "Travis");

        Schedules = GenerateSchedules(LocalDate.now());
    }

    private ArrayList<Schedule> GenerateSchedules(LocalDate weekOfDate) {
        ArrayList<Schedule> schedules = new ArrayList<>();
        DayOfWeek dayOfWeek = weekOfDate.getDayOfWeek();
        LocalDate sunday = null;
        switch(dayOfWeek) {
            case SUNDAY -> sunday = weekOfDate;
            case MONDAY -> sunday = weekOfDate.minusDays(1);
            case TUESDAY -> sunday = weekOfDate.minusDays(2);
            case WEDNESDAY -> sunday = weekOfDate.minusDays(3);
            case THURSDAY -> sunday = weekOfDate.minusDays(4);
            case FRIDAY -> sunday = weekOfDate.minusDays(5);
            case SATURDAY -> sunday = weekOfDate.minusDays(6);
        }
        schedules.addAll(getMeals(sunday));

        return schedules;
    }



    private ArrayList<Schedule> getMeals(LocalDate day) {
        Schedule breakfast = new Schedule(0, 1, "string", "string", "Breakfast", day.atTime(8, 00), day.atTime(8, 30));
        Schedule secondBreakfast = new Schedule(0, 1, "string", "string", "Second Breakfast", day.atTime(9, 00), day.atTime(9, 30) );
        Schedule elevensies = new Schedule(1, 1, "string", "string", "Elevenses", day.atTime(11, 00), day.atTime(12, 45) );
        Schedule luncheon = new Schedule(1, 1, "string", "string", "Luncheon", day.atTime(13, 00), day.atTime(13, 30) );
        Schedule afternoonTea = new Schedule(2, 1, "string", "string", "Afternoon Tea", day.atTime(15, 00), day.atTime(15, 30) );
        Schedule dinner = new Schedule(2, 1,  "string", "string","Dinner", day.atTime(18, 00), day.atTime(19, 00) );
        Schedule supper = new Schedule(3, 1, "string", "string", "Supper", day.atTime(21, 00), day.atTime(22, 00) );

    //    breakfast.AddAddendee(1);
    //    breakfast.AddAddendee(2);
    //    breakfast.AddAddendee(3);
        
        ArrayList<Schedule> meals = new ArrayList<>();
        meals.add(breakfast);
        meals.add(secondBreakfast);
        meals.add(elevensies);
        meals.add(luncheon);
        meals.add(luncheon);
        meals.add(afternoonTea);
        meals.add(dinner);
        meals.add(supper);
        return meals;
    }
    
    @Override
    public User GetUser(Integer userId) {
        return Users[userId];
    }

    @Override
    public User[] GetUsers() {
        return Users;
    }

    @Override
    public Integer AddSchedule(Schedule schedule) {
        schedule.Id = Schedules.size();
        Schedules.add(schedule);
        return schedule.Id;
    }

    @Override
    public void UpdateSchedule(Schedule schedule) {
        Schedules.remove(schedule);
        Schedules.add(schedule.Id, schedule);
    }

    @Override
    public Schedule GetSchedule(Integer scheduleId) {
        System.out.println("I triggered");
        return Schedules.get(scheduleId);
    }

 
    @Override
    public Schedule[] GetUserSchedule(Integer userId) {
        List<Schedule> userSchedules = new ArrayList<>();
        for (Schedule schedule : schedulesMap.values()) {
            if ((schedule.Owner == userId) || (schedule.Attendees.contains(userId))) {
                userSchedules.add(schedule);
            }
        }
        return userSchedules.toArray(new Schedule[0]);
    }

    @Override
    public Schedule[] GetWeekSchedule(LocalDate weekOfDate) {
        ArrayList<Schedule> weekSchedule = GenerateSchedules(weekOfDate);
        Schedule[] tempArray = new Schedule[weekSchedule.size()];
        return Schedules.toArray(tempArray);
    }

    @Override
    public void Save() {
        System.out.println("Saved");
    }

}
