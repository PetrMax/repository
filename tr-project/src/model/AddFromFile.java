package model;
import java.io.BufferedReader;
import java.io.FileReader;

import log.logger;

public class AddFromFile {
	protected BufferedReader input;
	protected FileReader file;

	public  AddFromFile(String filename) {	
		q=new Question();
		an=new Answer();
		d=new WorkActionClass();
		try {
			file = new FileReader("/"+filename);
			input = new BufferedReader(file); 
			
		} catch (Exception e) {		
			e.printStackTrace();			
		}
	}
}
