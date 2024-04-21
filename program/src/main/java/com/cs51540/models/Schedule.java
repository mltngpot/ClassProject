package com.cs51540.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;


public class Schedule {
    public Integer Id;
    public Integer Owner;
    public String MeetingType;
    public String Location;
    public List<Integer> Attendees;
    public String Title;
    public LocalDateTime Start;
    public LocalDateTime End;
    public String Description;
    
    public Schedule(Integer Id, Integer Owner, String MeetingType, String Location, String Title, LocalDateTime Start, LocalDateTime End) {
        this.Id = Id;
        this.Owner = Owner;
        this.MeetingType = MeetingType;
        this.Location = Location;
        this.Title = Title;
        this.Start = Start;
        this.End = End;
        this.Attendees = new Vector<Integer>();
        System.out.println(Id + Owner + MeetingType + Location + Title + Start + End + Attendees);
    }
    
   /* public Schedule(Integer Id, Integer Owner, String MeetingType, String Location, String Title, LocalDateTime Start, LocalDateTime End, List<Integer> Attendees) {
        this.Id = Id;
        this.Owner = Owner;
        this.MeetingType = MeetingType;
        this.Location = Location;
        this.Title = Title;
        this.Start = Start;
        this.End = End;
        this.Attendees = new Vector<Integer>();
        System.out.println(Id + Owner + MeetingType + Location + Title + Start + End + Attendees);
    }*/
    
    public void AddAddendee(Integer userId)
    {
        Attendees.add(userId);
    }
            

    public void AddDescription(String Description) {
        this.Description = Description;
    }
}
