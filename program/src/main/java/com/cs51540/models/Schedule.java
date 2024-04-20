package com.cs51540.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;


public class Schedule {
    public Integer Id;
    public Integer Owner;
    public List<Integer> Attendees;
    public String Title;
    public LocalDateTime Start;
    public LocalDateTime End;
    public String Description;
    
    public Schedule(Integer Id, Integer Owner, String Title, LocalDateTime Start, LocalDateTime End) {
        this.Id = Id;
        this.Owner = Owner;
        this.Title = Title;
        this.Start = Start;
        this.End = End;
        this.Attendees = new Vector<Integer>();
    }

    public Schedule(Integer Owner, String Title, LocalDateTime Start, LocalDateTime End) {
        this.Owner = Owner;
        this.Title = Title;
        this.Start = Start;
        this.End = End;
        this.Attendees = new Vector<Integer>();
    }
    public void AddAddendee(Integer userId) {
        Attendees.add(userId);
    }

    public void AddDescription(String Description) {
        this.Description = Description;
    }
}
