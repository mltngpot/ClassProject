package com.cs51540.models;
import javax.swing.*;

public class Slot extends JButton{
    private Schedule schedule;
    private Integer id = this.schedule.Id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    
    
}
