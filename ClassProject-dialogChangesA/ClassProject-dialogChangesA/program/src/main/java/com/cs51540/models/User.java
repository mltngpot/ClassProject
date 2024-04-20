package com.cs51540.models;

import java.awt.Color;

public class User {
    public Integer Id;
    public Color DisplayColor;
    public String Name;

    public User(Integer Id, Color DisplayColor, String Name) {
        this.Id = Id;
        this.DisplayColor = DisplayColor;
        this.Name = Name;
    }

}
