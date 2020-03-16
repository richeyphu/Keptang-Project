package Stock;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Account.Account;

public class SaveStock extends Item {

	private String enable;
	private String username = Account.username;
	public SaveStock(String username, String id, String name, String displayname, int unit, String pricetemp, String enable) {
		super(username, id, name, displayname, unit, pricetemp);
		this.enable=enable;
	}
	
	public SaveStock() {
		super();
	}
	
	public boolean checkSave() {
		if(super.isFieldBlank()
				|| super.checkPrice()
				|| super.isContainNG()
				|| isStockValid())
		{
			JOptionPane.showMessageDialog(null, "Some value in \"Stock\" tab is invalid," + "\nStock data cannot be saved!",
					"Please try again", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	// if Right return false
	public boolean isStockValid() {
		if (super.getUnit() < 0)
			return true;
		if (super.getUnit() > 1000000000)
			return true;
		return false;
	}
	
	public String checkDisplayNameAndSend() {
		if (super.getDisplayName().replace(" ", "").equals("")) {
			return super.generateDisplayName();
		} else if (super.getDisplayName().length() > 12) {
			return super.getDisplayName().replace(" ", "").substring(0, 12);
		} else {
			return super.getDisplayName().replace(" ", "");
		}
	}
	public Object[][] convertArrayListTo2DObjectArray(ArrayList<ArrayList<Object>> mainList) {
		Object[][] array = new Object[mainList.size()][];
		for (int i = 0; i < mainList.size(); i++) {
			ArrayList<Object> row = mainList.get(i);
			array[i] = row.toArray(new Object[row.size()]);
		}
		return array;
	}
	
	public void saveStockFile(Object[][] prod) {
		// FileWriter file = new FileWriter(getUserConfigPath(username), true);
		PrintWriter write;
		while(true) {
			try {
				// True = Append to file, false = Overwrite
				write = new PrintWriter(new FileWriter(super.getUserConfigPath(username), false));
				//System.out.println(super.getUserConfigPath(username));
				DecimalFormat f = new DecimalFormat("0.00");
				for (int i = 0; i < prod.length; i++) {
					for (int j = 0; j < prod[i].length; j++) {
						String check = prod[i][j].toString();
						if (j==0) {
							write.print("false,");
						} else if (j==prod[i].length-2) {
							write.print(f.format(Double.parseDouble(check))+",");
						} else if (j==prod[i].length-1) {
							write.print(check);
						} else {
							write.print(check+",");
						}
					}
					write.println();
				}
				write.close();
				break;
			} catch (FileNotFoundException e) {
				//System.out.println("e1");
				// e.printStackTrace();
				// System.err.println("Error: " + e.getMessage());
				createUserFolder();
			} catch (IOException e2) {
				// e.printStackTrace();
				//System.out.println("e2");
			}
//			} catch (Exception e3) {
//				System.out.println("e3");
//				//JOptionPane.showMessageDialog(null, "Unhandled Exception Occured\nPlease DELETE IT", "FETAL CORONA ERROR", JOptionPane.ERROR_MESSAGE);;
//			}
		}

	}
}
