package Account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SaveCustomer extends Customer {

	private int point;
	private String id;
	private String username = Account.username;

	public SaveCustomer(String username, String name, String phone, String email, String birthdate, int point,
			String id) {
		super(username, name, phone, email, birthdate);
		this.point = point;
		this.id = id;
	}

	public SaveCustomer() {
		super();
	}

	public boolean checkSave(ArrayList<ArrayList<Object>> list) {
		if (super.isFieldBlank()// if Right return false
				|| isIdDuplicated(list)// if Right return false
				|| super.isContainNG()// if Right return false
				|| !super.isValidEmail()// if Right return true
				|| super.isContainWhitespaceAtFirstChar()// if Right return false
				|| isPointValid()// if Right return false
				// || !checkDate()//if Right return true
				|| checkPhoneNum())// if Right return false
		{
			JOptionPane.showMessageDialog(null,
					"Some value in \"Members\" tab is invalid," + "\nMembers data cannot be saved!",
					"Please try again", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	// if Right return false
	public boolean isPointValid() {
		if (point < 0)
			return true;
		if (point > 1000000000)
			return true;
		return false;
	}

	// check phone number format
	// if Right return false
	private boolean checkPhoneNum() {
		String check = super.getPhone();
		if (check.length() == 12) {
			if (check.charAt(3) == '-' && check.charAt(7) == '-' && check.startsWith("0")) {
				for (int i = 0; i < check.length(); i++) {
					if (i == 3 || i == 7) {
						continue;
					} else if (Character.isDigit(check.charAt(i))) {
						continue;
					} else {
						return true;
					}
				}
				return false;
			}
		}
		return true;
	}

	// if Right return false
	public boolean isIdDuplicated(ArrayList<ArrayList<Object>> list) {
		Object[][] check = super.convertArrayListTo2DObjectArray(list);
		for (int i = 0; i < check.length - 1; i++) {
			if (check[i][2].toString().equalsIgnoreCase(check[check.length - 1][2].toString())
					|| check[i][3].toString().equalsIgnoreCase(check[check.length - 1][3].toString())) {
				return true;
			}
		}
		return false;
	}

	public void saveMemberFile(Object[][] cust) {
		// FileWriter file = new FileWriter(getUserConfigPath(username), true);
		PrintWriter write;
		while (true) {
			try {
				// True = Append to file, false = Overwrite
				//System.out.println(cust.length);
				write = new PrintWriter(new FileWriter(super.getUserConfigPath(username), false));
				//System.out.println(super.getUserConfigPath(username));

				for (int i = 0; i < cust.length; i++) {
					for (int j = 0; j < cust[i].length; j++) {
						write.print(cust[i][j].toString() + (j == cust[i].length - 1 ? "" : ","));
					}
					write.println();
				}
				write.close();
				break;
			} catch (FileNotFoundException e) {
				System.out.println("e1");
				// e.printStackTrace();
				// System.err.println("Error: " + e.getMessage());
				createUserFolder();
			} catch (IOException e2) {
				// e.printStackTrace();
				System.out.println("e2");
			}
//			catch (Exception e3) {
//				System.out.println("e3");
//				//JOptionPane.showMessageDialog(null, "Unhandled Exception Occured\nPlease DELETE IT", "FETAL CORONA ERROR", JOptionPane.ERROR_MESSAGE);;
//			}
		}

		
	}
}
