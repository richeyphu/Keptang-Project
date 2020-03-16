
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Initializer {

	public static Image icon;

	public static void main(String[] args) throws FontFormatException, IOException, InterruptedException {

		// Makes UI feel like System Look
		setSystemLookAndFeel();

		// Imports Fonts
		importFont();

		// Import Icons
		importIcon();

		// Shows Splash Screen
		showSplashScreen();

//		if (isFirstTime()) {
//			JOptionPane.showMessageDialog(null,
//					"Hello! It seems like you come here for the first time." + "\n\nLet's create an account first!",
//					"Welcome to Keptang!", JOptionPane.INFORMATION_MESSAGE);
//
//			RegisterForm register = new RegisterForm();
//			register.frmRegister.setVisible(true);
//		} else {
//			LoginForm login = new LoginForm();
//			login.frmLogin.setVisible(true);
//		}

	}

	public static void showSplashScreen() throws InterruptedException {
		WelcomeSplash welcome = new WelcomeSplash();

		welcome.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // make pointer to load status
		welcome.frame.setVisible(true);
		welcome.frame.setAlwaysOnTop(true);

		welcome.frame.setLocationRelativeTo(null); // *** this will center your app ***
		Thread.sleep(1500); // Pause Splash for 1.5 seconds
		welcome.frame.setAlwaysOnTop(false);
		
		// Fade out effect
		for (int i = 10; i > 0; i--) {
			welcome.frame.setOpacity((float) i / 10);
			Thread.sleep(50);
		}
		
		welcome.frame.setVisible(false);
		
		// Is user.acc file exist?
		showFirstFrame();
	}

	public static boolean isFirstTime() {
		File path = new File("user.acc");

		return !path.exists();
	}

	public static void showFirstFrame() {
		if (isFirstTime()) {
			JOptionPane.showMessageDialog(null,
					"Hello! It seems like you come here for the first time." + "\n\nLet's create an account first!",
					"Welcome to Keptang!", JOptionPane.INFORMATION_MESSAGE);

			RegisterForm register = new RegisterForm();
			register.frmRegister.setVisible(true);
		} else {
			LoginForm login = new LoginForm();
			login.frmLogin.setVisible(true);
		}
	}

	public static void setSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		// UIManager.put("OptionPane.background", new ColorUIResource(102, 102, 255));
		// UIManager.put("Panel.background", new ColorUIResource(102, 102, 255));
		// UIManager.put("OptionPane.errorDialog.titlePane.foreground", new
		// ColorUIResource(102, 102, 255));
		// UIManager.put("OptionPane.errorDialog.titlePane.background", new
		// ColorUIResource(102, 102, 255));
		// UIManager.put("OptionPane.errorDialog.border.background", Color.white);
	}

	public static void importFont() throws FontFormatException, IOException {
		try {
			Font f1 = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/SukhumvitSet-Text.ttf"));
			Font f2 = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/supermarket.ttf"));

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(f1);
			ge.registerFont(f2);

		} catch (IOException e) {
			JOptionPane
					.showMessageDialog(
							null, "Some fonts cannot be loaded or missing!\n"
									+ "Some labels may not display properly\n\n" + "Detail:\n" + e.getMessage(),
							"Keptang - Error!", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void importIcon() {
		// Create an image instance from the image that you want to use as icon for your
		// app.
		icon = Toolkit.getDefaultToolkit().getImage("asset\\keptang.png");
	}
}
