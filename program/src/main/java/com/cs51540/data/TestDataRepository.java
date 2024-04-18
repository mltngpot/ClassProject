package com.cs51540.data;

import java.awt.Color;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Vector;

import com.cs51540.interfaces.IDataRepository;
import com.cs51540.models.Schedule;
import com.cs51540.models.User;

public class TestDataRepository implements IDataRepository {

    private User[] Users;
    private Vector<Schedule> Schedules;

    public TestDataRepository() {
        Users = new User[4];
        Users[0] = new User(0, Color.blue, "Frodo Baggins");
        Users[1] = new User(1, Color.green, "Samwise Gamgee");
        Users[2] = new User(2, Color.red, "Merry Brandybuck");
        Users[3] = new User(3, Color.yellow, "Pippin Took");

        Schedules = GenerateSchedules(LocalDate.now());
    }

    private Vector<Schedule> GenerateSchedules(LocalDate weekOfDate) {
        Vector<Schedule> schedules = new Vector<Schedule>();
        DayOfWeek dayOfWeek = weekOfDate.getDayOfWeek();
        LocalDate sunday = null;
        switch(dayOfWeek) {
            case SUNDAY:
                sunday = weekOfDate;
                break;
            case MONDAY:
                sunday = weekOfDate.minusDays(1);
                break;
            case TUESDAY:
                sunday = weekOfDate.minusDays(2);
                break;
            case WEDNESDAY:
                sunday = weekOfDate.minusDays(3);
                break;
            case THURSDAY:
                sunday = weekOfDate.minusDays(4);
                break;
            case FRIDAY:
                sunday = weekOfDate.minusDays(5);
                break;
            case SATURDAY:
                sunday = weekOfDate.minusDays(6);
                break;
        }
        schedules.addAll(getMeals(sunday));

        return schedules;
    }

    private Vector<Schedule> getMeals(LocalDate day) {
        Schedule breakfast = new Schedule(0, 0, "Breakfast", day.atTime(7, 00), day.atTime(7, 30) );
        Schedule secondBreakfast = new Schedule(0, 0, "Second Breakfast", day.atTime(9, 00), day.atTime(9, 30) );
        Schedule elevensies = new Schedule(0, 0, "Elevenses", day.atTime(11, 00), day.atTime(11, 45) );
        Schedule luncheon = new Schedule(0, 0, "Luncheon", day.atTime(13, 00), day.atTime(13, 30) );
        Schedule afternoonTea = new Schedule(0, 0, "Afternoon Tea", day.atTime(15, 00), day.atTime(15, 30) );
        Schedule dinner = new Schedule(0, 0, "Dinner", day.atTime(18, 00), day.atTime(19, 00) );
        Schedule supper = new Schedule(0, 0, "Supper", day.atTime(21, 00), day.atTime(22, 00) );

        breakfast.AddAddendee(1);
        breakfast.AddAddendee(2);
        breakfast.AddAddendee(3);
        
        Vector<Schedule> meals = new Vector<>();
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
        Schedules.remove(schedule.Id);
        Schedules.add(schedule.Id, schedule);
    }

    @Override
    public Schedule GetSchedule(Integer scheduleId) {
        return Schedules.elementAt(scheduleId);
    }

    @Override
    public Schedule[] GetUserSchedule(Integer userId) {
        Vector<Schedule> usersSchedule = new Vector<Schedule>();
        for (Schedule schedule : Schedules) {
            if(schedule.Owner.equals(userId)) {
                usersSchedule.add(schedule);
                continue;
            }
            if(Arrays.asList(schedule.Attendees).contains(userId))
            {
                usersSchedule.add(schedule);
            }
        }

        Schedule[] tempArray = new Schedule[usersSchedule.size()];
        usersSchedule.toArray(tempArray);
        return tempArray;
    }

    @Override
    public Schedule[] GetWeekSchedule(LocalDate weekOfDate) {
        Vector<Schedule> weekSchedule = GenerateSchedules(weekOfDate);
        Schedule[] tempArray = new Schedule[weekSchedule.size()];
        weekSchedule.toArray(tempArray);
        return tempArray;
    }

    @Override
    public void Save() {
        System.out.println("Saved");
    }

}