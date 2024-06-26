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
    private Integer currentId = 0;
    
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
            if(schedules[i].Id > currentId) {
                currentId = schedules[i].Id;
            }
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
    public Integer AddSchedule(Schedule schedule) throws Exception {
        checkConflict(schedule);
        int scheduleId = ++currentId; // Generate a new ID
        schedule.setId(scheduleId);
        schedulesMap.put(scheduleId, schedule);
        return scheduleId;
    }

    @Override
    public void UpdateSchedule(Schedule schedule) throws Exception {
        checkConflict(schedule);
        if (schedulesMap.containsKey(schedule.Id)) {
            schedulesMap.remove(schedule.Id);
            schedulesMap.put(schedule.Id, schedule);
        } else {
            throw new IllegalArgumentException("Schedule not found");
        }
    }

    private void checkConflict(Schedule schedule) throws Exception{
        ArrayList<Integer> scheduledUsers = (ArrayList)schedule.Attendees.clone();
        Schedule[] schedules = GetWeekSchedule(schedule.Start.toLocalDate());
        for(int i = 0; i < schedules.length; i++) {
            if(schedules[i].Id == schedule.Id) continue;
            if(schedules[i].Start.isAfter(schedule.End) || schedules[i].End.isBefore(schedule.Start)) continue;
            boolean hasMember = scheduledUsers.contains(schedules[i].Owner);
            for(int j = 0; i < schedules[i].Attendees.size(); i++) {
                hasMember = hasMember && scheduledUsers.contains(schedules[i].Attendees.get(j));
            }
            if(hasMember) 
                throw new Exception("CONFLICT");
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
            if ((schedule.Owner == userId) || (schedule.Attendees.contains(userId))) {
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