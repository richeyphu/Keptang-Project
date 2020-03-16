import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import Account.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterForm {

	protected JFrame frmRegister;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField rePasswordField;
	private JTextField questionField;
	private JTextField answerField;

	int xMouse;
	int yMouse;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { RegisterForm window = new
	 * RegisterForm(); window.frmRegister.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the application.
	 */
	public RegisterForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegister = new JFrame();
		frmRegister.setIconImage(Initializer.icon); // Set frame's icon
		frmRegister.setTitle("Register");
		frmRegister.getContentPane().setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));
		frmRegister.setBounds(100, 100, 255, 480);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegister.setBackground(new java.awt.Color(255, 255, 255)); // Set Background Color of form
		frmRegister.setLocationRelativeTo(null); // *** this will center your app ***
		frmRegister.setUndecorated(true); // Make this form borderless

		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(255, 255, 255)); // Set Background Color of panel

		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(34, 429, 184, 29);
		btnRegister.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnRegister.doClick();
				}
			}
		});
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Register acc = new Register(usernameField.getText(), new String(passwordField.getPassword()), new String(rePasswordField.getPassword()),
						questionField.getText(), answerField.getText());

				final String TITLE = "Sign up";
				
				if (acc.isFieldBlank()) {
					JOptionPane.showMessageDialog(null, "All fields must not leave blank", TITLE,
							JOptionPane.ERROR_MESSAGE);
				} else if (acc.isIdDuplicated()) {
					JOptionPane.showMessageDialog(null, "This username already has been taken", TITLE,
							JOptionPane.ERROR_MESSAGE);
				} else if (!acc.isValidPassword()) {
					JOptionPane.showMessageDialog(null,
							"Password must be 8 characters or longer and contain"
									+ "\nat least 1 'capital letter', 'small letter' and 'number'",
							"Password is not correct!", JOptionPane.ERROR_MESSAGE);
				} else if (!acc.isPasswordMatched()) {
					JOptionPane.showMessageDialog(null, "Please re-enter your password again",
							"Password does not match!", JOptionPane.ERROR_MESSAGE);
				} else if (acc.isContainNG()) {
					JOptionPane.showMessageDialog(null, "Any field must not contain character : ','", TITLE,
							JOptionPane.ERROR_MESSAGE);
				} else if (acc.isContainWhitespaceAtFirstChar() || acc.isContainWhitespace()) {
					JOptionPane.showMessageDialog(null, "Please fill up all fields properly", TITLE,
							JOptionPane.ERROR_MESSAGE);
				} else {

					acc.createUserFolder();
					if (!acc.isFolderCreate()) {
						JOptionPane.showMessageDialog(null, "Username is invalid", "Error!", JOptionPane.ERROR_MESSAGE);
					} else {
						// Account acc = new Account(usernameField.getText(), passwordField.getText(),
						// questionField.getText(), answerField.getText());

						acc.create();

						JOptionPane.showMessageDialog(null, "Account registered!", "Completed!",
								JOptionPane.INFORMATION_MESSAGE);

						frmRegister.setVisible(false);

						LoginForm login = new LoginForm();
						login.frmLogin.setVisible(true);
					}
				}

			}
		});
		
		usernameField = new JTextField();
		usernameField.setToolTipText("Warning, username cannot be changed later");
		usernameField.setBounds(34, 123, 184, 26);
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (usernameField.getText().length() >= 12) { // limit textfield to 12 characters
					e.consume();
				}
				char input = e.getKeyChar();
				if (Character.isWhitespace(input)) {
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnRegister.doClick();
				}
			}
		});
		usernameField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(34, 100, 98, 22);
		lblNewLabel.setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(34, 160, 98, 22);
		lblPassword.setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));

		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password must be 8 characters or longer and contain at least 1 'capital letter', 'small letter' and 'number'");
		passwordField.setBounds(34, 182, 184, 26);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnRegister.doClick();
				}
			}
		});

		JLabel lblReenter = new JLabel("Re-Enter Password:");
		lblReenter.setBounds(34, 219, 184, 30);
		lblReenter.setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));

		rePasswordField = new JPasswordField();
		rePasswordField.setBounds(34, 252, 184, 26);
		rePasswordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnRegister.doClick();
				}
			}
		});

		questionField = new JTextField();
		questionField.setToolTipText("Please use your memorable question and answer for password recovery");
		questionField.setBounds(34, 317, 184, 26);
		questionField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnRegister.doClick();
				}
			}
		});
		questionField.setColumns(10);

		JLabel lblSecurityQuestion = new JLabel("Security Question:");
		lblSecurityQuestion.setBounds(34, 289, 184, 28);
		lblSecurityQuestion.setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));

		answerField = new JTextField();
		answerField.setBounds(34, 382, 184, 26);
		answerField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnRegister.doClick();
				}
			}
		});
		answerField.setColumns(10);

		JLabel lblAnswer = new JLabel("Answer:");
		lblAnswer.setBounds(34, 354, 184, 28);
		lblAnswer.setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));

		JLabel lblNewLabel_1 = new JLabel("Register a new user");
		lblNewLabel_1.setBounds(34, 55, 184, 37);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Sukhumvit Set", Font.BOLD, 18));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 255, 49);
		panel_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// sets frame position when mouse dragged
				frmRegister.setLocation(e.getXOnScreen() - xMouse, e.getYOnScreen() - yMouse);
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
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("supermarket", Font.BOLD, 32));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));

		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmRegister.setVisible(false);
				new LoginForm().frmLogin.setVisible(true);
			}
		});
		lblX.setForeground(Color.LIGHT_GRAY);
		lblX.setFont(new Font("Sukhumvit Set", Font.BOLD, 14));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap(52, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addGap(36).addComponent(lblX).addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE).addGap(6))
				.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblX).addContainerGap(26, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		GroupLayout groupLayout = new GroupLayout(frmRegister.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(panel,
				Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE));
		panel.setLayout(null);
		panel.add(lblNewLabel_1);
		panel.add(lblNewLabel);
		panel.add(lblPassword);
		panel.add(passwordField);
		panel.add(lblReenter);
		panel.add(rePasswordField);
		panel.add(lblSecurityQuestion);
		panel.add(questionField);
		panel.add(lblAnswer);
		panel.add(answerField);
		panel.add(btnRegister);
		panel.add(usernameField);
		panel.add(panel_1);
		frmRegister.getContentPane().setLayout(groupLayout);
	}
}
