package com.cs51540.interfaces;

import java.time.LocalDate;

import com.cs51540.models.Schedule;
import com.cs51540.models.User;

public interface IDataRepository {
    User GetUser(Integer userId);
    User[] GetUsers();
    Integer AddSchedule(Schedule schedule);
    void UpdateSchedule(Schedule schedule);
    Schedule GetSchedule(Integer scheduleId);
    Schedule[] GetUserSchedule(Integer userId);
    Schedule[] GetWeekSchedule(LocalDate weekOfDate);
    void Save();
}
