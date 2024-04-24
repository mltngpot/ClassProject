package com.cs51540; 
import com.cs51540.data.TestDataRepository;
import com.cs51540.data.DataRepository;
import com.cs51540.views.Scheduler;

public class Main{
    public static void main(String[] args) {
    DataRepository DataRepository = new DataRepository();  
		Scheduler frame = new Scheduler(DataRepository);
		frame.setVisible(true);
    }
}