package com.cs51540.data;

import com.cs51540.models.Schedule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class ScheduleIO {
    private final Gson gson;
    private final String dataDirectory;

    public ScheduleIO(String dataDirectory) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.dataDirectory = dataDirectory;
    }

    public void saveSchedule(Schedule schedule) throws IOException {
        String fileName = "schedule_" + schedule.Owner + ".json"; // File name based on owner
        String filePath = Paths.get(dataDirectory, fileName).toString();

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(schedule, writer);
            System.out.println("Schedule saved to JSON file: " + filePath);
        }
    }

    public Schedule loadSchedule(int ownerId) throws IOException {
        String fileName = "schedule_" + ownerId + ".json"; // File name based on owner
        String filePath = Paths.get(dataDirectory, fileName).toString();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return gson.fromJson(reader, Schedule.class);
        }
    }
}