package com.cs51540.views;

public class EventListener {
    private String[][] events = new String[7][48]; // Assuming 7 days and 48 slots

    public void addEvent(int day, int startTime, int endTime, String eventName) {
        for (int slot = startTime * 2; slot < endTime * 2; slot++) {
            events[day][slot] = eventName;
        }
    }

    public String getEvent(int day, int slot) {
        return events[day][slot];
    }
}