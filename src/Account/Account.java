package Account;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Struct;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.FileReader;

public class Account {

	protected String id;
	protected String password;
	protected String question;
	protected String answer;

	public static String username;
	
	public Account(String id, String password, String question, String answer) {
		this.id = id;
		this.password = password;
		this.question = question;
		this.answer = answer;
	}
	
	public Account(String id, String password) {
		this(id, password, "", "");
	}

	public void setPassword(String pass) {
		this.password = pass;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setAnswer(String ans) {
		this.answer = ans;
	}
	
	public void setUsername(String username) {
		this.id = username;
	}
	
	public String getUsername() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}

	protected final String PATH = "user.acc";

	public String getQuestion() {
		return question;
	}
	public String getAnswer() {
		return answer;
	}
	
	@SuppressWarnings("static-access")
	public boolean login() {
		try {
			File file = new File(PATH);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String temp;

			while ((temp = br.readLine()) != null) {
				// System.out.println(temp);

				String[] acc = temp.split(",");

				if (acc[0].equalsIgnoreCase(id.toLowerCase()) && acc[1].equals(password)) {
					br.close();
					
					setUsername(acc[0]);
					this.username = id;
					
					return true;
				}
			}

			br.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block

			System.err.println("Error: " + e.getMessage());

		}

		return false;
	}
	
	public boolean isUserFileExist() {
		File path = new File("user.acc");
		return path.exists();
	}

}
