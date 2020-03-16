import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WelcomeSplash {

	protected JFrame frame;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
					window.frame.setVisible(true);
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
	public WelcomeSplash() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Initializer.icon); // Set frame's icon
		frame.getContentPane().setBackground(new Color(102, 102, 255));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setFont(new Font("supermarket", Font.PLAIN, 18));
		frame.getContentPane().setLayout(null);
	    frame.setLocationRelativeTo(null);  // *** this will center your app ***
		
		lblNewLabel = new JLabel("Keptang");
		lblNewLabel.setBounds(65, 134, 322, 43);
		lblNewLabel.setFont(new Font("supermarket", Font.PLAIN, 48));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setUndecorated(true); // Make this form borderless

	}
}
