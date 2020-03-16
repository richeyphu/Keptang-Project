package Account;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AccountEdit extends Register {

	private Account user;

	public AccountEdit(String password, String rePassword, Account user) {
		super("", password, rePassword, "", "");
		this.user = user;
	}

	public AccountEdit(Account user) {
		super("", "", "", "", "");
		this.user = user;
	}

	public void changePassword() {
		String oldPassword = user.getPassword();
		String newPassword = super.password;

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(super.PATH));

			String temp;
			String text = "";
			while ((temp = br.readLine()) != null) {

				String[] data = temp.split(",");

				if (data[0].equals(user.getUsername())) {
					data[1] = data[1].replace(oldPassword, newPassword);

					temp = "";
					for (String datum : data) {
						temp += datum + ",";
					}
					temp = temp.substring(0, temp.length() - 1);
				}

				text += temp + "\n";

			}

			// text = text.replace(oldStr, newStr);

			PrintWriter pr = new PrintWriter(new FileWriter(super.PATH), false);
			pr.print(text);

			pr.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void changeSecurityQuestion() {
		String oldQuestion;
		String oldAnswer;
		String newQuestion = user.getQuestion();
		String newAnswer = user.getAnswer();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(super.PATH));

			String temp;
			String text = "";
			while ((temp = br.readLine()) != null) {

				String[] data = temp.split(",");

				if (data[0].equals(user.getUsername())) {
					oldQuestion = data[2];
					oldAnswer = data[3];
					
					data[2] = data[2].replace(oldQuestion, newQuestion);
					data[3] = data[3].replace(oldAnswer, newAnswer);
					
					temp = "";
					for (String datum : data) {
						temp += datum + ",";
					}
					
					temp = temp.substring(0, temp.length() - 1);

				}
				
				text += temp + "\n";

			}

			// text = text.replace(oldStr, newStr);

			PrintWriter pr = new PrintWriter(new FileWriter(super.PATH), false);
			pr.print(text);

			pr.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean isPasswordAuthentic() {
		return user.login();
	}

}
