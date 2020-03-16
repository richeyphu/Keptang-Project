package Account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Recovery extends Account {
	
	private String answer;
	private String password;
	
	public Recovery(String id) {
		super(id, "", "", "");
	}
	
	public String getQuestion() {
		try {
			File file = new File(PATH);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String temp;

			while ((temp = br.readLine()) != null) {
				// System.out.println(temp);

				String[] acc = temp.split(",");

				if (acc[0].equalsIgnoreCase(id)) {
					
					answer = acc[3];
					password = acc[1];
					
					br.close();
					return acc[2];
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Error: " + e.getMessage());
		}
		
		return "";

	}
	
	public boolean checkAnswer(String answer) {
		if(this.answer.equalsIgnoreCase(answer)) {
			return true;
		}
		return false;
	}
	
	public String getPassword() {
		return password;
	}
}
