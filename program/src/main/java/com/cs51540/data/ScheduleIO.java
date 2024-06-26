package com.cs51540.data;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.cs51540.data.TypeAdapters.ColorAdapter;
import com.cs51540.data.TypeAdapters.DataTimeAdapter;
import com.cs51540.interfaces.IDataRepository;
import com.cs51540.models.Schedule;
import com.cs51540.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ScheduleIO {
    private final Gson gson;
    private final GsonBuilder GsonBuilder;
    private final String dataDirectory;
    private final IDataRepository dataRepository;

    public ScheduleIO(IDataRepository dataRepository) {
        this.dataRepository = dataRepository;
        this.GsonBuilder = new GsonBuilder();
        this.GsonBuilder.registerTypeAdapter(Color.class, new ColorAdapter());
        this.GsonBuilder.registerTypeAdapter(LocalDateTime.class, new DataTimeAdapter());
        this.gson = GsonBuilder.setPrettyPrinting().create(); 
        dataDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
    }

    public void saveUsers() {
        User[] users = dataRepository.GetUsers();
        String filename = "users.json";
        String filePath = Paths.get(dataDirectory, filename).toString();

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(users, writer);
            System.out.println("Schedule saved to JSON file: " + filePath);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void saveSchedules() {
        User[] users = dataRepository.GetUsers();
        
        for(int i = 0; i < users.length; i++) {
            Schedule[] usersSchedules = dataRepository.GetUserSchedule(users[i].Id);

            String fileName = "schedule_" + users[i].Id + ".json"; // File name based on owner

            String filePath = Paths.get(dataDirectory, fileName).toString();
    
            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(usersSchedules, writer);
                System.out.println("Schedule saved to JSON file: " + filePath);
            }
            catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public User[] loadUsers() {
        String filename = "users.json";
        String filePath = Paths.get(dataDirectory, filename).toString();      
        
        ArrayList<User> users = new ArrayList<>();
        Type typeToken = new TypeToken<ArrayList<User>>(){}.getType();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            users.addAll(gson.fromJson(reader, typeToken));
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return users.toArray(new User[0]);
    }

    public Schedule[] loadSchedules() {
        User[] users = dataRepository.GetUsers();
        ArrayList<Schedule> schedules = new ArrayList<>();
        Type typeToken = new TypeToken<ArrayList<Schedule>>(){}.getType();
        
        for(int i = 0; i < users.length; i++) {

            String fileName = "schedule_" + users[i].Id + ".json"; // File name based on owner
            String filePath = Paths.get(dataDirectory, fileName).toString();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                schedules.addAll(gson.fromJson(reader, typeToken));
            }
            catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return schedules.toArray(new Schedule[0]);
    }
}