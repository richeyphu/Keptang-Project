package Account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Customer extends SettingOption implements RegisterOption {

	private String name;
	private String phone;
	private String email;
	private String birthdate;

	public Customer(String username, String name, String phone, String email, String birthdate) {
		super(username);
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.birthdate = birthdate;
	}

	public Customer(String username, String name, String phone, String birthdate) {
		this(username, name, phone, "", birthdate);
	}

	public Customer(String username, String name, String phone) {
		this(username, name, phone, "", "");
	}

	public Customer(String username) {
		this(username, "", "", "", "");
	}

	public Customer(String username, String phone) {
		this(username, "", phone, "", "");
	}
	
	public Customer() {
		this("", "", "", "", "");
	}
	
	public void setname(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getPhone() {
		return this.phone;
	}

	public String getEmail() {
		return this.email;
	}

	public String getBirthdate() {
		return this.birthdate;
	
	// private String path = username + "_cus.acc";
	}
	
	public void create() {
		// FileWriter file = new FileWriter(getUserConfigPath(username), true);
		PrintWriter write;

		boolean check = true;
		while (check) {
			try {
				
				// True = Append to file, false = Overwrite
				write = new PrintWriter(new FileWriter(getUserConfigPath(username), true));

				write.println(getMemberId() + "," + name + "," + phone + "," + email + "," + birthdate + ",0");
				write.close();

				check = false;

			} catch (FileNotFoundException e) {
				// e.printStackTrace();
				// System.err.println("Error: " + e.getMessage());

				createUserFolder();
			} catch (IOException e2) {
				// e.printStackTrace();
			}
		}
	}

	public boolean check(String phone) throws IOException {
		try {
			File file = new File(getUserConfigPath(username));
			BufferedReader br = new BufferedReader(new FileReader(file));
			String temp;

			while ((temp = br.readLine()) != null) {
				// System.out.println(temp);

				String[] member = temp.split(",");

				if (member[2].equals(phone)) {
					br.close();
					return true;
				}
			}

			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// System.err.println("Error: " + e.getMessage());

			createUserFolder();
		}

		return false;
	}

	@Override
	protected String getUserConfigPath(String username) {
		return getUserFolderPath(username) + username + "_cus.kpt";
	}

	private String getMemberId() throws IOException {
		int id = 0;
		BufferedReader read = new BufferedReader(new FileReader(getUserConfigPath(username)));
		String temp;
		while ((temp=read.readLine()) != null) {
			int value = Integer.parseInt(temp.split(",")[0]);
			if(value>id)
				id = value;
		}
		DecimalFormat f = new DecimalFormat("00000000");
		return f.format(id+1);
	}
	
	@Override
	public boolean isFieldBlank() {
		return name.equals("") || phone.equals("") || email.equals("");
	}//if Right return false

	//0.0.22change equals to contains
	@Override
	public boolean isContainNG() {
		return name.contains(",") || phone.contains(",") || email.contains(",");
	}//if Right return false

	public boolean isContainWhitespaceAtFirstChar() {
		return Character.isWhitespace(name.charAt(0));
	}//if Right return false

	@Override
	public boolean isIdDuplicated() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(getUserConfigPath(username)));
			String temp;

			while ((temp = br.readLine()) != null) {

				String[] member = temp.split(",");

				if (member[2].equalsIgnoreCase(phone) || member[3].equalsIgnoreCase(email)) {
					br.close();
					return true;
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// System.err.println("Error: " + e.getMessage());
			createUserFolder();
		}
		return false;
	}//if Right return false

	public boolean isValidEmail() {
		if (!email.contains("@") || email.contains(" ")) {
			// System.out.println(1);
			return false;
		}
		if (email.split("@").length != 2) {
			// System.out.println(2);
			return false;
		}
		if (email.split("@")[0].equals("")) {
			// System.out.println(3);
			return false;
		}
		if (!email.endsWith("@gmail.com") && !email.endsWith("@hotmail.com")) {
			// System.out.println(4);
			return false;
		}

		return true;
	}//if Right return true

	public Object[][] getMemberList() {

		try {
			BufferedReader br = new BufferedReader(new FileReader(getUserConfigPath(username)));

			String temp;

			// ArrayList<String[]> members = new ArrayList<String[]>();
			ArrayList<ArrayList<Object>> mainList = new ArrayList<ArrayList<Object>>();

			while ((temp = br.readLine()) != null) {

				Object[] cus = temp.split(",");

				ArrayList<Object> mem = new ArrayList<Object>();
				for (Object datum : cus) {
					mem.add(datum);
				}

				mainList.add(mem);
			}
			br.close();

			// System.out.println(Arrays.toString(mainList.toArray()));
			
			return convertArrayListTo2DObjectArray(mainList);

		} catch (Exception e) {
			// TODO Auto-generated catch block

			createUserFolder();
		}

		return null;
	}
	
	public Object[][] convertArrayListTo2DObjectArray(ArrayList<ArrayList<Object>> mainList) {
		Object[][] array = new Object[mainList.size()][];
		for (int i = 0; i < mainList.size(); i++) {
			ArrayList<Object> row = mainList.get(i);
			array[i] = row.toArray(new Object[row.size()]);
		}
		return array;
	}
	
	public boolean isPhoneMatched() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(getUserConfigPath(username)));
			String temp;

			while ((temp = br.readLine()) != null) {

				String[] member = temp.split(",");

				if (member[2].equalsIgnoreCase(phone)) {
					this.name = member[1];
					
					br.close();
					return true;
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// System.err.println("Error: " + e.getMessage());

			createUserFolder();
		}

		return false;
	}

	public String getName() {
		return name;
	}

	
}
