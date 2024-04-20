package com.cs51540; 
import com.cs51540.data.TestDataRepository;
import com.cs51540.interfaces.IDataRepository;
import com.cs51540.views.*;

public class Main{
    public static void main(String[] args) {
    IDataRepository DataRepository = new TestDataRepository();  
		Scheduler frame = new Scheduler(DataRepository);
		frame.setVisible(true);
    }
}