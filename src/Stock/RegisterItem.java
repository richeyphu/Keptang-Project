package Stock;

import Account.Account;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RegisterItem {
	private Item product;
	public RegisterItem() {
		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		JTextField field3 = new JTextField();
		String name,dname,temp;
		double price;
		int option;
		Object[] fields = { "Product Name:", field1, "Display name (max 12 characters)\n(optional):", field2,
				"Price per unit:", field3, };
		do {
			option=JOptionPane.showConfirmDialog(null, fields, "Register New Item", JOptionPane.OK_CANCEL_OPTION);
			if (option!=JOptionPane.OK_OPTION) {
				break;
			}
			product = new Item(Account.username,field1.getText(), field2.getText(), field3.getText());
		} while(!product.check());
		if (option==JOptionPane.OK_OPTION) {
			DecimalFormat f = new DecimalFormat(",##0.00");
			if(JOptionPane.showConfirmDialog(null, "Product name : " + product.getName() + "\nDisplay name : " + product.getDisplayName() + "\nPrice : "
					+ f.format(product.getPrice()) + "\nif correct select ok", "Register New Item", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
					product.createNewProduct();
					JOptionPane.showMessageDialog(null, "Sussces adding new product.\nPlease Restart program for update.");
			}
		}
	}
	
//	public static void main(String[] args) {
//		JTextField field1 = new JTextField();
//		JTextField field2 = new JTextField();
//		JTextField field3 = new JTextField();
//		String name,dname,temp;
//		double price;
//		int option;
//		Object[] fields = { "Product Name:", field1, "Display name (max 12 characters)\n(optional):", field2,
//				"Product prices:", field3, };
//		do {
//			option=JOptionPane.showConfirmDialog(null, fields, "Register New Item", JOptionPane.OK_CANCEL_OPTION);
//			if (option!=JOptionPane.OK_OPTION) {
//				break;
//			}
//			product = new Item(Account.username,field1.getText(), field2.getText(), field3.getText());
//		} while(!product.check());
//		if (option==JOptionPane.OK_OPTION) {
//			if(JOptionPane.showConfirmDialog(null, "Product name : " + product.getName() + "\nDisplay name : " + product.getDisplayName() + "\nPrice : "
//					+ product.getPrice() + "\nif correct select ok", "Register New Item", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
//					product.createNewProduct();
//					JOptionPane.showMessageDialog(null, "Sussces adding new product.");
//			}
//		}
//	}
}
