package Stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Item {

	private String id;
	private String name;
	private String displayName;
	private String pricetemp;
	private int unit;
	private double price;

	protected String username;

	public Item(String username, String id, String name, String displayname, int unit, String pricetemp) {
		this.username = username;
		this.id = id;
		this.name = name;
		this.displayName = displayname;
		this.unit = unit;
		this.pricetemp = pricetemp;

	}

	public Item(String username, String name, String displayName, String pricetemp) {
		this(username, "", name, displayName, 0, pricetemp);
	}

	public Item(String username, String name, String displayName) {
		this(username, "", name, displayName, 0, "");
	}

	public Item(String username, String name) {
		this(username, "", name, "", 0, "");
	}

	public Item(String username) {
		this(username, "", "", "", 0, "");
	}

	public Item() {
		this("", "", "", "", 0, "");
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPricetemp(String pricetemp) {
		this.pricetemp = pricetemp;
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int getUnit() {
		return unit;
	}

	public double getPrice() {
		return price;
	}

	public boolean check() {
		checkDisplayName();
		if (checkPrice()) {
			JOptionPane.showMessageDialog(null, "Error, price can be ONLY integer or decimal.", "Error",
					JOptionPane.OK_OPTION);
			return false;
		} else if (isContainNG()) {
			JOptionPane.showMessageDialog(null, "Error, input cannot have \",\".", "Error", JOptionPane.OK_OPTION);
			return false;
		} else if (isFieldBlank()) {
			JOptionPane.showMessageDialog(null, "Error, name cannot leaves blank.", "Error", JOptionPane.OK_OPTION);
			return false;
		}
		return true;
	}

	public void checkDisplayName() {
		if (displayName.replace(" ", "").equals("")) {
			setDisplayName(generateDisplayName());
		} else if (displayName.length() > 12) {
			setDisplayName(displayName.replace(" ", "").substring(0, 12));
		} else {
			setDisplayName(displayName.replace(" ", ""));
		}
	}

	public String generateDisplayName() {
		String temp = name.replace(" ", "");
		return temp.length() <= 12 ? temp : temp.substring(0, 12);
	}

	public boolean isFieldBlank() {
		String temp = name.replace(" ", "");
		return temp.equalsIgnoreCase("") || Character.isWhitespace(name.charAt(0));
	}

	public boolean isContainNG() {
		return name.contains(",") || displayName.contains(",");
	}

	public boolean checkPrice() {
		try {
			setPrice(Double.parseDouble(pricetemp));
			if (price < 0)
				return true;
		} catch (NumberFormatException e) {
			return true;
		}
		return false;
	}

	private String getProductId() throws IOException {
		int id = 0;
		BufferedReader read = new BufferedReader(new FileReader(getUserConfigPath(username)));
		String temp;
		while ((temp = read.readLine()) != null) {
			int value = Integer.parseInt(temp.split(",")[1]);
			if (value > id)
				id = value;
		}
		DecimalFormat f = new DecimalFormat("000000");
		return f.format(id + 1);
	}

	protected void createFolder() {
		File folder = new File(getUserFolderPath(username));
		// Create a User Folder
		folder.mkdirs();
	}

	protected String getUserFolderPath(String username) {
		return "user\\" + username + "\\";
	}

	protected String getUserConfigPath(String username) {
		return getUserFolderPath(username) + username + "_stock.cfg";
	}

	public void createNewProduct() {
		DecimalFormat f = new DecimalFormat("0.00");
		PrintWriter write;
		boolean check = true;
		while (check) {
			try {
				// True = Append to file, false = Overwrite
				write = new PrintWriter(new FileWriter(getUserConfigPath(username), true));
				write.println("false," + getProductId() + "," + name + "," + displayName + "," + unit + ","
						+ f.format(price) + ",true");
				write.close();
				check = false;
			} catch (FileNotFoundException e) {
				// e.printStackTrace();
				// System.err.println("Error: " + e.getMessage());
				createFolder();
			} catch (IOException e2) {
				// e.printStackTrace();
			}
		}
	}

	public void createUserFolder() {
		File folder = new File(getUserFolderPath(username));
		// Create a User Folder
		folder.mkdirs();
	}

	public Object[][] getProductList() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(getUserConfigPath(username)));

			String temp;

			// ArrayList<String[]> members = new ArrayList<String[]>();
			ArrayList<ArrayList<Object>> mainList = new ArrayList<ArrayList<Object>>();

			while ((temp = br.readLine()) != null) {

				String[] stocktemp = temp.split(",");
				Object[] stock = new Object[stocktemp.length];
				for (int i = 0; i < stocktemp.length; i++) {
					if (stocktemp[i].equals("true") || stocktemp[i].equals("false")) {
						stock[i] = Boolean.parseBoolean(stocktemp[i]);
					} else {
						stock[i] = stocktemp[i];
					}
				}
				ArrayList<Object> mem = new ArrayList<Object>();
				for (Object datum : stock) {
					mem.add(datum);
				}

				mainList.add(mem);
			}
			br.close();

			// System.out.println(Arrays.toString(mainList.toArray()));

			Object[][] array = new Object[mainList.size()][];
			for (int i = 0; i < mainList.size(); i++) {
				ArrayList<Object> row = mainList.get(i);
				array[i] = row.toArray(new Object[row.size()]);
			}
			// System.out.println(Arrays.toString(array));
			return array;

		} catch (Exception e) {
			// TODO Auto-generated catch block

			// createFolder();
		}
		return null;
	}

}
