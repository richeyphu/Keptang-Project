package Account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SettingOption {

	protected String username;
	private String[][] parameter;

	public SettingOption(String username, String[][] parameter) {
		this.username = username;
		this.parameter = parameter;
	}

	public SettingOption(String username) {
		this(username, null);
	}

	public void saveSetting() throws IOException {
		PrintWriter write;

		boolean check = true;
		while (check) {
			try {
				// Overwrite '.cfg' every time
				write = new PrintWriter(new FileWriter(getUserConfigPath(username), false));

				// write.println(formatSetting("test1", true));
				// write.println(formatSetting("test2", false));
				// write.println(formatSetting("test3", 0));

				for (String[] value : parameter) {
					write.println(formatSetting(value[0], value[1]));
				}

				write.close();

				check = false;
			} catch (FileNotFoundException e) {
				// e.printStackTrace();

				createUserFolder();
			}
		}
	}

	public String[] loadSetting() throws IOException {

		String value_list = "";

		BufferedReader read;
		try {
			read = new BufferedReader(new FileReader(getUserConfigPath(username)));

			String temp;
			while ((temp = read.readLine()) != null) {
				String[] data = temp.split("=", 2);
				value_list += data[1] + "\n";
			}
			
			read.close();
			
		} catch (FileNotFoundException e1) {
			createUserFolder();
			return null;
		} catch (Exception e2) {
			// JOptionPane.showMessageDialog(null, "Cannot load user configuration file!" + "\nLoading default preset config instead...", "Error!", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Cannot load user configuration file!", "Error!", JOptionPane.ERROR_MESSAGE);
		}

		return value_list.split("\n");
	}

	public void createUserFolder() {
		File folder = new File(getUserFolderPath(username));
		// Create a User Folder
		folder.mkdirs();
	}

	protected String getUserFolderPath(String username) {
		return "user\\" + username + "\\";
	}

	protected String getUserConfigPath(String username) {
		return getUserFolderPath(username) + username + ".cfg";
	}

	protected String formatSetting(String parameter, int value) {
		return parameter + "=" + value;
	}

	protected String formatSetting(String parameter, boolean value) {
		return parameter + "=" + value;
	}

	protected String formatSetting(String parameter, String value) {
		return parameter + "=" + value;
	}

}
