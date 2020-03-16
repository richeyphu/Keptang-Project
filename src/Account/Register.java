package Account;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Register extends Account implements RegisterOption {

	private String rePassword;

	public Register(String username, String password, String rePassword, String question, String answer) {
		super(username, password, question, answer);
		this.rePassword = rePassword;
	}

	private final int PASSWORD_MIN_LENGTH = 8;

	public boolean isValidPassword() {

		if (password.length() < PASSWORD_MIN_LENGTH) {
			return false;
		}

		int charUpperCount = 0;
		int charLowerCount = 0;
		int numCount = 0;

		for (int i = 0; i < password.length(); i++) {

			char ch = password.charAt(i);

			if (Character.isUpperCase(ch)) {
				charUpperCount++;
			} else if (Character.isLowerCase(ch)) {
				charLowerCount++;
			} else if (Character.isDigit(ch)) {
				numCount++;
			}
		}

		return (charUpperCount >= 1 && charLowerCount >= 1 && numCount >= 1);
	}

	public boolean isPasswordMatched() {
		return password.equals(rePassword);
	}

	@Override
	public boolean isFieldBlank() {
		return id.equals("") || password.equals("") || rePassword.equals("") || question.equals("")
				|| answer.equals("");
	}

	public boolean isContainNG() {
		return id.contains(",") || password.contains(",") || rePassword.contains(",") || question.contains(",")
				|| answer.contains(",");
	}
	
	public boolean isContainWhitespaceAtFirstChar() {
		return Character.isWhitespace(question.charAt(0)) || Character.isWhitespace(answer.charAt(0));
	}
	
	public boolean isContainWhitespace() {
		return id.contains(" ");
	}
	
	public boolean isFolderCreate() {
		File folder = new File("user\\" + super.id);
		return folder.exists();
	}
	
	@Override
	public boolean isIdDuplicated() {
		if (isUserFileExist()) {
			try {
				File file = new File(PATH);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String temp;

				while ((temp = br.readLine()) != null) {

					String[] acc = temp.split(",");

					if (acc[0].equalsIgnoreCase(id)) {
						br.close();
						return true;
					}
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println("Error: " + e.getMessage());
			}
		}
		return false;
	}

	@Override
	public void create() {
		// File file = new File(path);
		FileWriter writer;

		try {

			writer = new FileWriter(PATH, true); // True = Append to file, false = Overwrite

			writer.write(super.id + "," + super.password + "," + super.question + "," + super.answer + "\r\n");
			// writer.write(id + "," + password + "\r\n");

			writer.close();

			createUserFolder();

		} catch (IOException e) {
			// TODO Auto-generated catch block

			// e.printStackTrace();
			// System.err.println("Error: " + e.getMessage());
		}
	}
	
	public void createUserFolder() {
		File folder = new File("user\\" + super.id); 
		folder.mkdirs(); // Create a User Folder
	}
	
	public void deleteUserFolder() {
		File folder = new File("user\\" + super.id);
		folder.delete(); // Delete a User Folder
	}
	
}
