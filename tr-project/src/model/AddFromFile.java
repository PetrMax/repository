package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AddFromFile {
	protected BufferedReader input;
	protected FileReader file;		
	public  AddFromFile(){}	
	public  AddFromFile(String filename) {			
		try {
			file = new FileReader("D:/developer-workspaces/out_project/tr-project/"+filename);
			input = new BufferedReader(file); 
			//gettingText();
		} catch (Exception e) {		
			e.printStackTrace();			
		}
	}

	private void gettingText() throws Exception {
		String line; 
		while((line = input.readLine()) != null){ 
			String[] question_Parts = line.split(",,"); 
			if(question_Parts.length == 9){
				int level = Integer.parseInt(question_Parts[3]);
				int trueAnswerNumber = Integer.parseInt(question_Parts[8]); 
				List<String> answer = new ArrayList<String>();
				answer.add(question_Parts[4]);
				answer.add(question_Parts[5]);
				answer.add(question_Parts[6]);
				answer.add(question_Parts[7]);
				//createQuestion(question_Parts[1], question_Parts[2], level, answer, trueAnswerNumber);
			}else{
				System.out.println(" format this line not correct  "+ question_Parts.length);
			}
			
		}
	}
}
