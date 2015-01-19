package motor;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReader {
	private BufferedReader reader;		
	public FileReader (String fileName)  throws FileNotFoundException{		
		reader=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	}
	public String getNewLine() {
		String str=null;
		try {
			str=reader.readLine();
			if(str!=null && str.equals("exit"))
				str=null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}
