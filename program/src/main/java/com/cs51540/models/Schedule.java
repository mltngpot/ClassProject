package com.cs51540.models;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Schedule {
    public static Integer maxId = 0;
    public Integer Id;
    public Integer Owner;
    public String MeetingType;
    public String Location;
    public ArrayList<Integer> Attendees;
    public String Title;
    public LocalDateTime Start;
    public LocalDateTime End;
    public String Description;
    
    public Schedule(Integer Owner, String MeetingType, String Location, String Title, LocalDateTime Start, LocalDateTime End, ArrayList<Integer> Attendees) 
    {
        this(-1, Owner, MeetingType, Location, Title, Start, End, Attendees);
    }

    public Schedule() {}
   public Schedule(Integer Id, Integer Owner, String MeetingType, String Location, String Title, LocalDateTime Start, LocalDateTime End, ArrayList<Integer> Attendees) {
        this.Id = Id;
        this.Owner = Owner;
        this.MeetingType = MeetingType;
        this.Location = Location;
        this.Title = Title;
        this.Start = Start;
        this.End = End;
        this.Attendees = Attendees;
        System.out.println(Id + Owner + MeetingType + Location + Title + Start + End + Attendees);
    }
    
    public Schedule(Integer Id, Integer Owner, String MeetingType, String Location, String Title, LocalDateTime Start, LocalDateTime End) {
        this.Id = Id;
        this.Owner = Owner;
        this.MeetingType = MeetingType;
        this.Location = Location;
        this.Title = Title;
        this.Start = Start;
        this.End = End;
        System.out.println(Id + Owner + MeetingType + Location + Title + Start + End);
    }

    public void setId(Integer newId)
    {
        Id = newId;
    }

    public void AddAttendee(Integer userId) {
        Attendees.add(userId);
    }
    
    public void AddDescription(String Description) {
        this.Description = Description;
    }
}
