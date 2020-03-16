import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Window.Type;
import java.awt.dnd.MouseDragGestureRecognizer;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import Account.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.chrono.JapaneseDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.event.WindowFocusListener;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MemberRegisterForm {

	protected JFrame frmMemberRegister;
	private JTextField nameField;
	private JFormattedTextField phoneField;
	private JTextField emailField;
	
	int xMouse;
	int yMouse;
	
	private JButton button;

	boolean isFirstTimeChanged = true;
	
	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { RegisterForm window = new
	 * RegisterForm(); window.frmMemberRegister.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the application.
	 */
	public MemberRegisterForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMemberRegister = new JFrame();
		frmMemberRegister.setIconImage(Initializer.icon); // Set frame's icon
		frmMemberRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMemberRegister.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				button.setEnabled(true);
				//frmMemberRegister.setVisible(false);
			}
		});
		frmMemberRegister.setType(Type.POPUP);
		frmMemberRegister.setAlwaysOnTop(true);
		frmMemberRegister.setTitle("Register");
		frmMemberRegister.getContentPane().setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));
		frmMemberRegister.setBounds(100, 100, 250, 480);
		frmMemberRegister.setBackground(new java.awt.Color(255, 255, 255)); // Set Background Color of form
		frmMemberRegister.setLocationRelativeTo(null); // *** this will center your app ***
		frmMemberRegister.setUndecorated(true); // Make this form borderless
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 250, 480);
		panel.setBackground(new java.awt.Color(255, 255, 255)); // Set Background Color of panel

		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(35, 391, 184, 30);
		btnRegister.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnRegister.doClick();
				}
			}
		});
		
		nameField = new JTextField();
		nameField.setBounds(35, 160, 184, 26);
		nameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// limit nameField to 12 characters
				/*
				if (nameField.getText().length() >= 12) { 
					e.consume();
				}
				*/
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnRegister.doClick();
				}
			}
		});
		nameField.setColumns(10);

		JLabel lblName = new JLabel("Member Name:");
		lblName.setBounds(35, 135, 184, 22);
		lblName.setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(35, 265, 184, 22);
		lblEmail.setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));

		JLabel lblPhone = new JLabel("Phone number:");
		lblPhone.setBounds(35, 200, 184, 22);
		lblPhone.setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));

		// Format phoneField
		MaskFormatter phoneMask = null;
		try {
			phoneMask = new MaskFormatter("0##-###-####");
			phoneMask.setPlaceholderCharacter('_');
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		phoneField = new JFormattedTextField(phoneMask);
		phoneField.setBounds(35, 225, 184, 26);
		phoneField.setColumns(10);
		phoneField.setCaretPosition(0);

		JLabel lblBD = new JLabel("Member Birth Date:");
		lblBD.setToolTipText("DD/MM/YYYY");
		lblBD.setBounds(35, 330, 184, 22);
		lblBD.setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));

		JLabel lblTopic = new JLabel("Register new Member");
		lblTopic.setBounds(35, 89, 184, 37);
		lblTopic.setHorizontalAlignment(SwingConstants.CENTER);
		lblTopic.setFont(new Font("Sukhumvit Set", Font.BOLD, 18));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 255, 80);
		panel_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// sets frame position when mouse dragged
				frmMemberRegister.setLocation(e.getXOnScreen() - xMouse, e.getYOnScreen() - yMouse);
				
				for (Frame f : JFrame.getFrames()) {
					if (f.getTitle().equals("Keptang")) {
						f.setLocation(e.getXOnScreen() - xMouse, e.getYOnScreen() - yMouse);
					}
				}
			}
		});
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		panel_1.setBackground(new Color(102, 102, 255));

		JLabel lblNewLabel_2 = new JLabel("Keptang");
		lblNewLabel_2.setBounds(10, 12, 227, 65);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("supermarket", Font.PLAIN, 72));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));

		JLabel lblX = new JLabel("X");
		lblX.setBounds(236, 0, 9, 23);
		lblX.setVisible(false); // Hide 'X' label
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmMemberRegister.setVisible(false);
				button.setEnabled(true);
			}
		});
		frmMemberRegister.getContentPane().setLayout(null);
		lblX.setForeground(Color.LIGHT_GRAY);
		lblX.setFont(new Font("Sukhumvit Set", Font.BOLD, 14));
		panel.setLayout(null);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.add(lblNewLabel_2);
		panel_1.add(lblX);
		panel.add(phoneField);
		panel.add(lblTopic);
		panel.add(lblName);
		panel.add(lblEmail);
		panel.add(lblPhone);
		panel.add(lblBD);
		panel.add(btnRegister);
		panel.add(nameField);
				
		JComboBox BDdate = new JComboBox();
		BDdate.setEditable(true);
		BDdate.setToolTipText("Day");
		BDdate.setFont(new Font("Sukhumvit Set", Font.PLAIN, 13));
		BDdate.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
				"27", "28", "29", "30", "31" }));
		BDdate.setBounds(35, 355, 50, 25);
		panel.add(BDdate);

		JComboBox BDmonth = new JComboBox();
		BDmonth.setEditable(true);
		BDmonth.setToolTipText("Month");
		BDmonth.setModel(new DefaultComboBoxModel(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		BDmonth.setFont(new Font("Sukhumvit Set", Font.PLAIN, 13));
		BDmonth.setBounds(95, 355, 50, 25);
		panel.add(BDmonth);
		
		JComboBox BDyear = new JComboBox();
		BDyear.setEditable(true);
		BDyear.setToolTipText("Year");
		BDyear.setModel(new DefaultComboBoxModel(getYearList()));
		/* BDyear.setModel(new DefaultComboBoxModel(new String[] { "2020", "2019", "2018", "2017", "2016", "2015", "2014",
				"2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2setToolTipText("Year");
		BDyear.004", "2003", "2002", "2001",
				1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990" })); */
		BDyear.setFont(new Font("Sukhumvit Set", Font.PLAIN, 13));
		BDyear.setBounds(155, 355, 62, 25);
		panel.add(BDyear);
		frmMemberRegister.getContentPane().add(panel);
		
		emailField = new JTextField();
		emailField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnRegister.doClick();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();
				if (Character.isWhitespace(input)) {
					e.consume();
				}
			}
		});
		emailField.setColumns(10);
		emailField.setBounds(35, 290, 184, 26);
		panel.add(emailField);
		
		BDmonth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int year = Integer.parseInt(BDyear.getSelectedItem().toString());
				int month = BDmonth.getSelectedIndex() + 1;
					
				BDdate.setModel(new DefaultComboBoxModel(getDateInMonth(month, year)));
					

				BDdate.setEnabled(true);
			}
		});
		
		BDyear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int year = Integer.parseInt(BDyear.getSelectedItem().toString());
				int month = BDmonth.getSelectedIndex() + 1;
				
				BDdate.setModel(new DefaultComboBoxModel(getDateInMonth(month, year)));
				
				BDdate.setEnabled(true);
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setEnabled(true);
				frmMemberRegister.setVisible(false);
			}
		});
		btnCancel.setBounds(35, 430, 184, 30);
		panel.add(btnCancel);

		btnRegister.addActionListener(new ActionListener() {
			//@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				String username = LoginForm.username;
				
				Customer member = new Customer(username, nameField.getText(), phoneField.getText(), emailField.getText(),
						BDdate.getSelectedItem().toString() + "/" + BDmonth.getSelectedItem().toString() + "/" + BDyear.getSelectedItem().toString());

				frmMemberRegister.setAlwaysOnTop(false);
				
				if (member.isFieldBlank()) {
					JOptionPane.showMessageDialog(null, "All fields must not leave blank", "Please try again",
							JOptionPane.WARNING_MESSAGE);
				} else if (member.isIdDuplicated()) {
					JOptionPane.showMessageDialog(null, "Please use another phone number or email", "Member is duplicate!",
							JOptionPane.WARNING_MESSAGE);
				} else if (member.isContainNG()) {
					JOptionPane.showMessageDialog(null, "Any field must not contain character : ','", "Please try again",
							JOptionPane.WARNING_MESSAGE);
				} else if (!member.isValidEmail()) {
					JOptionPane.showMessageDialog(null, "Please use another email\n" + "('@gmail.com' or '@hotmail.com')" , "Please try again",
							JOptionPane.WARNING_MESSAGE);
				} else if (member.isContainWhitespaceAtFirstChar()){
					JOptionPane.showMessageDialog(null, "Please fill up the fields properly", "Please try again",
							JOptionPane.WARNING_MESSAGE);
				} else {
					
					member.create();
					
					JOptionPane.showMessageDialog(null, "Member registered!", "Success!",
							JOptionPane.INFORMATION_MESSAGE);
					
					button.setEnabled(true);
					frmMemberRegister.setVisible(false);

				}
				 
				frmMemberRegister.setAlwaysOnTop(true);
			}
		});
		
	}
	
	public void setButton(JButton button) {
		this.button = button;
	}
	
	public void setDialogLocation(int x, int y) {
		frmMemberRegister.setLocation(x, y);
	}
	
	public String[] getYearList() {
		LocalDateTime currentDate = LocalDateTime.now();

		int year = currentDate.getYear();
		String yearS = "";

		for (int i = year - 7; i >= year - 100; i--) {
			yearS += i + ",";
		}

		return yearS.split(",");
	}
	
	public String[] getDateInMonth(int month, int year) {
		String[] dateList;
		
		if (month == 2) {
			boolean isLeap = false;
			if (year % 4 == 0) {
				if (year % 100 == 0) {
					if (year % 400 == 0)
						isLeap = true;
					else
						isLeap = false;
				} else
					isLeap = true;
			} else {
				isLeap = false;
			}
			dateList = isLeap
					? new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
							"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
							"27", "28", "29" }
					: new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
							"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
							"27", "28" };
		} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			dateList = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
					"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
					"28", "29", "30", "31" };
		} else {
			dateList = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
					"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
					"28", "29", "30" };
		}
		
		return dateList;
	}
}
