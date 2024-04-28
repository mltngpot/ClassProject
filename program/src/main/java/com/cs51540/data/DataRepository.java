package com.cs51540.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cs51540.interfaces.IDataRepository;
import com.cs51540.models.Schedule;
import com.cs51540.models.User;

public class DataRepository implements IDataRepository {
    private Map<Integer, User> usersMap;
    private Map<Integer, Schedule> schedulesMap;
    
    public DataRepository() {
        usersMap = new HashMap<>();
        schedulesMap = new HashMap<>();

        ScheduleIO sio = new ScheduleIO(this);
        loadUsers(sio);
        loadSchedules(sio);
    }

    private void loadUsers(ScheduleIO sio) {
        User[] users = sio.loadUsers();
        for(int i = 0; i < users.length; i++) {
            usersMap.put(users[i].Id, users[i]);
        }
    }

    private void loadSchedules(ScheduleIO sio) {
        Schedule[] schedules = sio.loadSchedules();
        for(int i = 0; i < schedules.length; i++) {
            if(schedulesMap.containsKey(schedules[i].Id))
                continue;
            schedulesMap.put(schedules[i].Id, schedules[i]);
        }

    }

    @Override
    public User GetUser(Integer userId) {
        return usersMap.get(userId);
    }

    @Override
    public User[] GetUsers() {
        return usersMap.values().toArray(new User[0]);
    }

    @Override
    public Integer AddSchedule(Schedule schedule) {
        int scheduleId = schedulesMap.size() + 1; // Generate a new ID
        schedulesMap.put(scheduleId, schedule);
        return scheduleId;
    }

    @Override
    public void UpdateSchedule(Schedule schedule) {
        if (schedulesMap.containsKey(schedule.Id)) {
            schedulesMap.put(schedule.Id, schedule);
        } else {
            throw new IllegalArgumentException("Schedule not found");
        }
    }

    @Override
    public Schedule GetSchedule(Integer scheduleId) {
        return schedulesMap.get(scheduleId);
    }

    @Override
    public Schedule[] GetUserSchedule(Integer userId) {
        List<Schedule> userSchedules = new ArrayList<>();
        for (Schedule schedule : schedulesMap.values()) {
            if (schedule.Owner == userId) {
                userSchedules.add(schedule);
            }
        }
        return userSchedules.toArray(new Schedule[0]);
    }


    @Override
public Schedule[] GetWeekSchedule(LocalDate weekOfDate) {
    List<Schedule> weekSchedules = new ArrayList<>();
    for (Schedule schedule : schedulesMap.values()) {
        weekSchedules.add(schedule);
    }
    return weekSchedules.toArray(new Schedule[0]);
}



    @Override
    public void Save() {
        // Implementation of saving data to a persistent storage (e.g., database, file)
        ScheduleIO sio = new ScheduleIO(this);
        sio.saveSchedules();
        System.out.println("Saving data to persistent storage...");
    }

    
    public void DeleteSchedule(Integer scheduleId) {
        if (schedulesMap.containsKey(scheduleId)) {
            schedulesMap.remove(scheduleId);
        } else {
            throw new IllegalArgumentException("Schedule not found");
        }
    }
}