import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Account.Account;
import Account.Recovery;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SpringLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;

public class LoginForm {

	protected JFrame frmLogin;
	private JTextField UserField;
	private JPasswordField passwordField;
	
	int xMouse;
	int yMouse;
	
	protected static String username;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frmLogin = new JFrame();
		frmLogin.setIconImage(Initializer.icon); // Set frame's icon
		frmLogin.getContentPane().setBackground(Color.WHITE);
		frmLogin.setTitle("Keptang - Welcome");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frmLogin.setLocationRelativeTo(null);  // *** this will center your app ***
	    frmLogin.setUndecorated(true); // Make this form borderless
		
		UserField = new JTextField();
		UserField.setToolTipText("Please input your username here");

		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));
		UserField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));
		
		passwordField = new JPasswordField();

		passwordField.setToolTipText("Please input your password here");
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				btnLogin.doClick();
			}
		});
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(new Color(102, 102, 255));
		
		UserField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (UserField.getText().length() >= 12 ) // limit textfield to 12 characters
		            e.consume();
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});
		
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Account acc = new Account(UserField.getText(), new String(passwordField.getPassword()));

				if (!acc.isUserFileExist()) {
					JOptionPane.showMessageDialog(null, "Please sign up an account first!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else if (UserField.getText().equals("")
						|| new String(passwordField.getPassword()).contentEquals("")) {
					JOptionPane.showMessageDialog(null, "Please enter your username and password.", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else if (!acc.login()) {
					JOptionPane.showMessageDialog(null, "Your username or password is incorrect!",
							"Please try again...", JOptionPane.ERROR_MESSAGE);
					passwordField.setText(null);
				} else {

					username = acc.getUsername();

					Index window = new Index();

					// JOptionPane.showMessageDialog(null, "Welcome, " + username + "!");

					// window.setUsername(acc.getUsername(););
					
					frmLogin.setVisible(false);
					
					window.frmKeptang.setVisible(true);

				}
			}
		});
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		JLabel lblForgotPassword = new JLabel("Forgot password?");
		lblForgotPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		lblForgotPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {

					while (true) {

						String username = JOptionPane.showInputDialog(null, "Please enter your username:", "Password Recovery", JOptionPane.INFORMATION_MESSAGE);

						Recovery rec = new Recovery(username);

						if (!rec.getQuestion().equals("")) {

							while (true) {
								String answer = JOptionPane
										.showInputDialog(null, "Question:\n" + rec.getQuestion() + "\n\nAnswer:", "Answer your secret question", JOptionPane.QUESTION_MESSAGE);

								if (rec.checkAnswer(answer)) {
									JOptionPane.showMessageDialog(null, "Your password is:\n\n" + rec.getPassword(), "Success!", JOptionPane.INFORMATION_MESSAGE);

									break;
								} else if (answer.equals("")) {
									break;
								} else {
									JOptionPane.showMessageDialog(null, "Your answer is wrong", "Please try again...",
											JOptionPane.ERROR_MESSAGE);
								}
							}
							break;
						} else if (username.equals(null)) {
							// Do nothing on pressing 'Cancel'
						} else {
							JOptionPane.showMessageDialog(null, "No username found!", "Please try again...",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (NullPointerException a) {

				}
				
				
				
			}
		});
		lblForgotPassword.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel ribbon = new JPanel();
		ribbon.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		ribbon.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				
				// sets frame position when mouse dragged
				frmLogin.setLocation(x - xMouse, y - yMouse);
			}
		});
		ribbon.setBackground(new Color(102, 102, 255));
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				btnSignUp.doClick();
			}
		});
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterForm signup = new RegisterForm();
				signup.frmRegister.setUndecorated(true); // Make this form borderless
				frmLogin.setVisible(false);
				signup.frmRegister.setVisible(true);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmLogin.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(ribbon, GroupLayout.PREFERRED_SIZE, 568, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(78)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
						.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
						.addComponent(UserField, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
					.addGap(245))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(132)
					.addComponent(lblForgotPassword, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
					.addGap(245))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(147)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSignUp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
						.addComponent(btnLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
					.addGap(265))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(ribbon, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblUsername)
						.addComponent(UserField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(btnLogin)
					.addGap(18)
					.addComponent(btnSignUp)
					.addGap(16)
					.addComponent(lblForgotPassword)
					.addContainerGap())
		);
								
										
										JLabel lblWelcome = new JLabel("Keptang");
										lblWelcome.setBackground(new Color(0, 0, 255));
										lblWelcome.setForeground(Color.WHITE);
										lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
										lblWelcome.setFont(new Font("supermarket", Font.BOLD, 36));
										
										JLabel lblClose = new JLabel("X");
										lblClose.addMouseListener(new MouseAdapter() {
											@Override
											public void mouseClicked(MouseEvent e) {
												frmLogin.setVisible(false);
												
												// Exit the program
												System.exit(0);
											}
										});
										lblClose.setForeground(Color.LIGHT_GRAY);
										lblClose.setFont(new Font("Sukhumvit Set", Font.BOLD, 14));
										GroupLayout gl_ribbon = new GroupLayout(ribbon);
										gl_ribbon.setHorizontalGroup(
											gl_ribbon.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_ribbon.createSequentialGroup()
													.addGap(163)
													.addComponent(lblWelcome)
													.addPreferredGap(ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
													.addComponent(lblClose)
													.addGap(124))
										);
										gl_ribbon.setVerticalGroup(
											gl_ribbon.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_ribbon.createSequentialGroup()
													.addContainerGap()
													.addComponent(lblWelcome, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
													.addGap(5))
												.addGroup(gl_ribbon.createSequentialGroup()
													.addComponent(lblClose)
													.addContainerGap(39, Short.MAX_VALUE))
										);
										ribbon.setLayout(gl_ribbon);
		frmLogin.getContentPane().setLayout(groupLayout);
		
		
	}

}
