package Stock;

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

import javax.swing.JOptionPane;

public class Checkout extends Item {

	private int[] date = new int[6];
	private String[] idList;
	private int[] amount;

	// private double totalPrice = 0;
	private double[] priceList;

	public Checkout() {
		super();
	}

	public Checkout(String username, String[] idList, int amount[]) {
		super(username);
		this.idList = idList;
		this.amount = amount;
	}

	public Checkout(String username, String[] idList, int amount[], double[] priceList) {
		super(username);
		this.idList = idList;
		this.amount = amount;
		this.priceList = priceList;
	}
	
	public Checkout(double[] priceList, int[] amount) {
		super();
		this.priceList = priceList;
		this.amount = amount;

	}

	@Override
	public Object[][] getProductList() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(getUserConfigPath(super.username)));

			String temp;

			// ArrayList<String[]> members = new ArrayList<String[]>();
			ArrayList<ArrayList<Object>> mainList = new ArrayList<ArrayList<Object>>();

			while ((temp = br.readLine()) != null) {

				String[] stocktemp = temp.split(",");
				Object[] chkoutRow = new Object[6];

				for (int i = 0; i < idList.length; i++) {
					if (idList[i].equals(stocktemp[1])) {
						chkoutRow[0] = false;
						try {
							chkoutRow[1] = amount[i];

						} catch (Exception e) {
							chkoutRow[1] = 1;
						}
						chkoutRow[2] = idList[i];
						chkoutRow[3] = stocktemp[2];
						chkoutRow[4] = stocktemp[5];

						ArrayList<Object> mem = new ArrayList<Object>();
						for (Object datum : chkoutRow) {
							mem.add(datum);
						}

						mainList.add(mem);

						break;
					}
				}

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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return null;
	}

	public double getNetPrice(double totalPrice, int memDis, double specialDis, int vat, int serviceCharge) {
		double net = totalPrice - (memDis / 100d * totalPrice) - (specialDis / 100d * totalPrice);
		net = net + (vat / 100d * net) + (serviceCharge / 100d * net);
		return net;
	}

//	public void addPrice(double price) {
//		totalPrice += price;
//	}

	public double getTotalPrice() {
		double total = 0;
		for (int i = 0; i < priceList.length; i++) {
			total += priceList[i] * amount[i];
		}
		return total;
	}

	public void decreaseStock() {

		int[] newStock = new int[amount.length];

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(getUserConfigPath(username)));

			String temp;
			String text = "";
			while ((temp = br.readLine()) != null) {

				String[] data = temp.split(",");

				for (int i = 0; i < amount.length; i++) {
					if (data[1].equals(idList[i])) {
						data[4] = Integer.toString(Integer.parseInt(data[4]) - amount[i]);

						temp = "";
						for (String datum : data) {
							temp += datum + ",";
						}
						temp = temp.substring(0, temp.length() - 1);
					}

				}
				text += temp + "\n";

			}

			// text = text.replace(oldStr, newStr);

			PrintWriter pr = new PrintWriter(new FileWriter(getUserConfigPath(username)), false);
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

	public void getDate() {
		LocalDateTime currentDate = LocalDateTime.now();
		date[0] = currentDate.getYear();
		date[1] = currentDate.getDayOfMonth();
		date[2] = currentDate.getMonthValue();
		date[3] = currentDate.getHour();
		date[4] = currentDate.getMinute();
		date[5] = currentDate.getSecond();
	}

	protected String getLogPath(String username) {
		getDate();
		return getUserFolderPath(username) + "\\log\\" + username + "_log_" + date[0] + date[1] + date[2] + "_"
				+ date[3] + date[4] + date[5] + ".txt";
	}

	public void createReceipe(String toString) {
		DecimalFormat f = new DecimalFormat("0.00");
		PrintWriter write;
		while (true) {
			try {
				// True = Append to file, false = Overwrite
				write = new PrintWriter(new FileWriter(getLogPath(username), true));
				write.println(date[1] + "/" + date[2] + "/" + date[0] + "\t" + date[3] + ":" + date[4] + ":" + date[5]);
				write.println("***********************");
				for (int i = 0; i < idList.length; i++) {
					write.println(idList[i] + "\t" + amount[i] + "\t" + f.format(priceList[i]) + "\t"
							+ f.format(priceList[i] * amount[i]));
				}
				write.println("***********************");
				write.println(toString);
				write.close();
				break;
			} catch (FileNotFoundException e) {
				// e.printStackTrace();
				// System.err.println("Error: " + e.getMessage());
				createFolder();
			} catch (IOException e2) {
				// e.printStackTrace();
				//createFolder();
			}
		}
	}
	
	@Override
	protected void createFolder() {
		File folder = new File(getUserFolderPath(username) + "\\log\\");
		// Create a User Folder
		folder.mkdirs();
	}

}
