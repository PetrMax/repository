package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AddFromFile {
	FileInputStream file;
	StringBuffer sb;
	public AddFromFile(){
		try {
			file = new FileInputStream("d");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
