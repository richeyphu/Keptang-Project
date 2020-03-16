package Stock;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Account.Account;

public class DeleteProduct extends Item {

	private boolean[] delCheck;
	private int delCount;
	private String username = Account.username;

	public DeleteProduct(boolean[] deleteCheck, int delCount) {
		this.delCheck = deleteCheck;
		this.delCount = delCount;
	}

	public boolean deleteConfirm() {
		if (JOptionPane.showConfirmDialog(null,
				"Are you sure you will delete " + delCount + " selected item" + (delCount == 1 ? "" : "s")
						+ "?\n This action cannot be undone.",
				"Delete?", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			return true;
		return false;
	}

	public void deleteActivate(Object[][] prod) {
		// FileWriter file = new FileWriter(getUserConfigPath(username), true);
		PrintWriter write;
		while (true) {
			try {
				// True = Append to file, false = Overwrite
				write = new PrintWriter(new FileWriter(super.getUserConfigPath(username), false));
				//System.out.println(super.getUserConfigPath(username));
				DecimalFormat f = new DecimalFormat("0.00");
				for (int i = 0; i < prod.length; i++) {
					if (delCheck[i]) {
						//System.out.println("line " + i + " deleted");
						continue;
					}
					for (int j = 0; j < prod[i].length; j++) {
						String check = prod[i][j].toString();
						if (j == 0) {
							write.print("false,");
						} else if (j == prod[i].length - 2) {
							write.print(f.format(Double.parseDouble(check)) + ",");
						} else if (j == prod[i].length - 1) {
							write.print(check);
						} else {
							write.print(check + ",");
						}
					}
					write.println();
				}
				write.close();
			} catch (FileNotFoundException e) {
				// System.out.println("e1");
				// e.printStackTrace();
				// System.err.println("Error: " + e.getMessage());
				createUserFolder();
			} catch (IOException e2) {
				// e.printStackTrace();
				// System.out.println("e2");
			}
//			} catch (Exception e3) {
//				System.out.println("e3");
//				//JOptionPane.showMessageDialog(null, "Unhandled Exception Occured\nPlease DELETE IT", "FETAL CORONA ERROR", JOptionPane.ERROR_MESSAGE);;
//			}
			JOptionPane.showMessageDialog(null, "Deleted completed");
			break;
		}

	}
}
