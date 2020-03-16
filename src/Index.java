import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import Account.Customer;
import Stock.Checkout;
import Stock.Item;

import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Index {

	JFrame frmKeptang;

	private int xMouse;
	private int yMouse;
	
	//private String username;
	//private JFrame frame;
	private JTable checkoutTable;
	private JPanel panel_menu;
	private JComboBox cmbxPage;
	private JFormattedTextField txtfd_MemberLogin;
	private JFormattedTextField txtfd_SpecialDiscount;

	private JLabel lblSumSeller;
	private JLabel lblSumBuyer;
	private JLabel lblSumDiscount;
	private JLabel lblSumSpecialDiscount;
	private JLabel lblSumVat;
	private JLabel lblSumServiceCharge;
	private JLabel lblSumNetPrice;
	
	private JComboBox cmbx_SpecialDiscount;
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
					window.frmKeptang.setVisible(true);
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
	
	public Index() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Load User Config file
//		try {
//			new Setting().loadUserCfg();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		
		frmKeptang = new JFrame();
		frmKeptang.setIconImage(Initializer.icon); // Set frame's icon
		frmKeptang.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
				
			}
		});
		frmKeptang.setTitle("Keptang");
		frmKeptang.getContentPane().setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));
		frmKeptang.getContentPane().setBackground(new Color(255, 255, 255));
		frmKeptang.setBounds(100, 100, 850, 550);
		frmKeptang.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    frmKeptang.setLocationRelativeTo(null);  // *** this will center your app ***
		frmKeptang.setUndecorated(true); // Make this form borderless
		
		JPanel ribbon = new JPanel();
		ribbon.setBounds(0, 0, 850, 80);
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
				frmKeptang.setLocation(x - xMouse, y - yMouse);
				
				for (Frame f : JFrame.getFrames()) {
					if (f.getTitle().equals("Register")) {
						f.setLocation(x - xMouse, y - yMouse);
					}
				}
			}
		});
		ribbon.setBackground(new Color(102, 102, 255));
		
		JPanel menu = new JPanel();
		menu.setBounds(0, 85, 250, 465);
		menu.setBackground(new Color(102, 102, 102));
		
		JLabel lblGreeting = new JLabel("Hello, " + LoginForm.username + "!");
		lblGreeting.setBounds(536, 11, 304, 29);
		lblGreeting.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGreeting.setForeground(new Color(255, 255, 255));
		lblGreeting.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		
		JLabel lblLogo = new JLabel("Keptang");
		lblLogo.setBounds(10, 11, 244, 68);
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setFont(new Font("supermarket", Font.PLAIN, 72));
		
		JButton btnMenu = new JButton("Menu");
		
		btnMenu.setBackground(new Color(102, 102, 255));
		
		btnMenu.setFont(new Font("Sukhumvit Set", Font.BOLD, 18));
		
		JButton btnRegisMem = new JButton("add New Member");
		btnRegisMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberRegisterForm member = new MemberRegisterForm();
				member.frmMemberRegister.setLocation(frmKeptang.getLocationOnScreen());
				member.frmMemberRegister.setVisible(true);
				member.setButton(btnRegisMem);
				btnRegisMem.setEnabled(false);
				
			}
		});
		btnRegisMem.setFont(new Font("Sukhumvit Set", Font.BOLD, 18));
		
		JButton btnCheckout = new JButton("Checkout");

		btnCheckout.setFont(new Font("Sukhumvit Set", Font.BOLD, 18));
		
		JButton btnSetting = new JButton("Settings");
		btnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (Frame f : JFrame.getFrames()) {
					if (f.getTitle().equals("Register")) {
						f.dispose();
						btnRegisMem.setEnabled(true);
					}
				}
				
				Setting form = new Setting();
				
				form.frmSettings.setLocationRelativeTo(frmKeptang); // Centering relative to 'Index' frame
				form.frmSettings.setVisible(true);
				//frmKeptang.setEnabled(false);
				
				form.setButton(btnSetting);
				form.setParentFrame(frmKeptang);
				btnSetting.setEnabled(false);
			}	
		});
		btnSetting.setFont(new Font("Sukhumvit Set", Font.BOLD, 18));
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBackground(new Color(153, 0, 51));
		btnLogOut.addActionListener(new ActionListener() { // Confirm Logging Out
			public void actionPerformed(ActionEvent e) {
				
				for (Frame f : JFrame.getFrames()) {
					if (!f.getTitle().equals("Keptang")) {
						f.setAlwaysOnTop(false);
					}
				}
				
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to 'Log Out'?",
						"Log out?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (choice == 0) {
					// Close all active forms
					for (Frame f : JFrame.getFrames())
				    {
				        f.dispose();
				    }
					
					new LoginForm().frmLogin.setVisible(true);
					
					//frmKeptang.dispose();
					//frmKeptang.setVisible(false);
				} else {
					// Do nothing...
					for (Frame f : JFrame.getFrames()) {
						if (!f.getTitle().equals("Keptang")) {
							f.setAlwaysOnTop(true);
						}
					}
				}
			}
		});
		btnLogOut.setFont(new Font("Sukhumvit Set", Font.BOLD, 18));
		
		JPanel lblPage = new JPanel();
		
		JButton page_btnLeftArrow = new JButton("<");
		page_btnLeftArrow.setEnabled(false);
		JButton page_btnRightArrow = new JButton(">");
		
		page_btnLeftArrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int page = cmbxPage.getSelectedIndex();
				
				if (page != 0) {
					cmbxPage.setSelectedIndex(--page);
					page_btnRightArrow.setEnabled(true);
					if (page == 0) {
						page_btnLeftArrow.setEnabled(false);
					}
				}
			}
		});
		page_btnLeftArrow.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		page_btnRightArrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int page = cmbxPage.getSelectedIndex();
				
				if (page != cmbxPage.getItemCount() - 1) {
					cmbxPage.setSelectedIndex(++page);
					page_btnLeftArrow.setEnabled(true);
					//panels_menus[page++].setVisible(true);
					if (page == cmbxPage.getItemCount() - 1) {
						page_btnRightArrow.setEnabled(false);
					}
				}
			}
		});
		page_btnRightArrow.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_menu = new GroupLayout(menu);
		gl_menu.setHorizontalGroup(
			gl_menu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_menu.createParallelGroup(Alignment.LEADING)
						.addComponent(btnMenu, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
						.addComponent(btnCheckout, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
						.addComponent(btnRegisMem, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
						.addGroup(gl_menu.createSequentialGroup()
							.addComponent(btnSetting, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnLogOut, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_menu.createSequentialGroup()
							.addComponent(page_btnLeftArrow, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblPage, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
							.addComponent(page_btnRightArrow, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_menu.setVerticalGroup(
			gl_menu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnMenu, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnCheckout, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRegisMem, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_menu.createParallelGroup(Alignment.LEADING)
						.addComponent(page_btnLeftArrow, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(page_btnRightArrow, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPage, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
					.addGroup(gl_menu.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSetting, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogOut, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		lblPage.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Page ");
		lblNewLabel.setBounds(10, 6, 35, 17);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPage.add(lblNewLabel);
		
		cmbxPage = new JComboBox();
		cmbxPage.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int page = cmbxPage.getSelectedIndex();
				
				closeAllPanelMenu();
				panels_menus[page].setVisible(true);
				
				if (page == 0) {
					page_btnLeftArrow.setEnabled(false);
					page_btnRightArrow.setEnabled(true);
				} else if (page == cmbxPage.getItemCount() - 1) {
					page_btnRightArrow.setEnabled(false);
					page_btnLeftArrow.setEnabled(true);
				} else {
					page_btnLeftArrow.setEnabled(true);
					page_btnRightArrow.setEnabled(true);
				}
				/*
				 	if (page != 0 && page != cmbxPage.getItemCount() - 1) {
						page_btnLeftArrow.setEnabled(true);
						page_btnRightArrow.setEnabled(true);
				}
				 */
			}
		});
		cmbxPage.setMaximumRowCount(6);
		cmbxPage.setBounds(45, 5, 45, 21);
		cmbxPage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		//cmbxPage.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		cmbxPage.setModel(new DefaultComboBoxModel(new String[] {"1"}));
		lblPage.add(cmbxPage);
		menu.setLayout(gl_menu);
		
		JLabel lblDateTime = new JLabel("HH:MM:SS DD/MM/YYYY");
		lblDateTime.setBounds(621, 42, 219, 29);
		lblDateTime.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblDateTime.setForeground(Color.WHITE);
		lblDateTime.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// Make Clock running in background
		new DynamicClock(lblDateTime).run(); 
		
		stage = new JLayeredPane();
		stage.setBounds(255, 85, 595, 465);
		
//		panel_menu = new JPanel();
//		panel_menu.setBounds(0, 0, 595, 465);
//		panel_menu.setLayout(new GridLayout(0, 4, 0, 0));
//		stage.add(panel_menu);
		
		// Generate product menu from stock file
		loadProductMenu();
		
		/*
		JPanel[] menu_cell = new JPanel[12];
		JButton[] btn_cell = new JButton[12];
		JLabel[] lb_cell = new JLabel[12];
		
		// Generate menu stage button
		Item item = new Item(LoginForm.username);
		Object[][] product = item.getProductList();
		
		if (product != null) {
			panelPage = (int) Math.ceil(product.length / 12d);
			panels_menus = new JPanel[panelPage];

			cmbxPage.setModel(new DefaultComboBoxModel(getNumberPageList(panelPage)));
			// System.out.println(panelPage);
			// System.out.println(Arrays.toString(getNumberPageList(panelPage)));

			// loadSumPanelData();

			int countItem = 0;

			for (int page = 0; page < panelPage; page++) {
				panels_menus[page] = new JPanel();
				panels_menus[page].setBounds(0, 0, 595, 465);
				panels_menus[page].setLayout(new GridLayout(0, 4, 0, 0));
				stage.add(panels_menus[page]);
				panels_menus[page].setVisible(false);

				for (int i = 0; i < menu_cell.length; i++) {

					menu_cell[i] = new JPanel();

					btn_cell[i] = new JButton("not available");
					btn_cell[i].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					btn_cell[i].setBounds(10, 81, 125, 63);
					btn_cell[i].setFont(new Font("supermarket", Font.BOLD, 18));
					btn_cell[i].setEnabled(false);

					lb_cell[i] = new JLabel(
							"<html><i>id: 000000</i><br \\>\r\n<b>N/A</b><br \\>\r\nin stock : <b>N/A</b><br \\>\r\nprice : <b>N/A</b></html>");
					lb_cell[i].setFont(new Font("Tahoma", Font.PLAIN, 11));
					lb_cell[i].setBounds(10, 11, 125, 59);

					if (countItem <= product.length - 1) {
						String id = product[countItem][1].toString();
						String name = product[countItem][3].toString();
						int stock = Integer.parseInt(product[countItem][4].toString());
						double price = Double.parseDouble(product[countItem][5].toString());
						boolean availability = Boolean.parseBoolean(product[countItem][6].toString());

						lb_cell[i].setText(
								"<html><i>id: " + id + "</i><br \\>\r\n<b>" + name + "</b><br \\>\r\nin stock : <b>"
										+ stock + "</b><br \\>\r\nprice : <b>" + price + "</b> " + "</html>");

						if (availability && stock > 0) {
							btn_cell[i].setText("add");
							btn_cell[i].setEnabled(true);
						} else if (!availability) {
							btn_cell[i].setText("not available");
						} else {
							btn_cell[i].setText("out of stock");
						}

						countItem++;
					}

					panels_menus[page].add(menu_cell[i]);
					menu_cell[i].setLayout(null);
					menu_cell[i].add(lb_cell[i]);
					menu_cell[i].add(btn_cell[i]);
				}
			}

			panels_menus[0].setVisible(true);
		} else {
			panels_menus = new JPanel[1];
			panels_menus[0] = new JPanel();
			panels_menus[0].setBounds(0, 0, 595, 465);
			panels_menus[0].setLayout(new GridLayout(0, 4, 0, 0));
			stage.add(panels_menus[0]);
			panels_menus[0].setVisible(true);
			panels_menus[0].setLayout(null);
			
			JLabel lblNoStock = new JLabel(
					"<html><center>Welcome to Keptang!</center>" + "<br /><center>Please add some products first</center>"
							+ "<br /><i>Settings >> Stock >> Add New Product</i></html>");
			lblNoStock.setHorizontalAlignment(SwingConstants.CENTER);
			lblNoStock.setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));
			lblNoStock.setBounds(0, 0, 594, 465);
			panels_menus[0].add(lblNoStock);
			
		}
		*/
		
		/*
		JPanel menu_cell1 = new JPanel();

		JButton btn_cell1 = new JButton("add");
		btn_cell1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btn_cell1.setBounds(10, 81, 125, 63);
		btn_cell1.setFont(new Font("supermarket", Font.BOLD, 18));
		
		JLabel lb_cell1 = new JLabel("<html><i>id: 000000</i><br \\>\r\n<b>N/A</b><br \\>\r\nin stock : <b>N/A</b><br \\>\r\nN/A</html>");
		lb_cell1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lb_cell1.setBounds(10, 11, 125, 59);
		panel_menu.add(menu_cell1);
		menu_cell1.setLayout(null);
		menu_cell1.add(lb_cell1);
		menu_cell1.add(btn_cell1);
		
		JPanel menu_cell2 = new JPanel();
		panel_menu.add(menu_cell2);
		menu_cell2.setLayout(null);
		
		JLabel lb_cell2 = new JLabel("<html><i>id: 000000</i><br \\>\r\n<b>N/A</b><br \\>\r\nin stock : <b>N/A</b><br \\>\r\nN/A</html>");
		lb_cell2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lb_cell2.setBounds(10, 11, 125, 59);
		menu_cell2.add(lb_cell2);
		
		JButton btn_cell2 = new JButton("add");
		btn_cell2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btn_cell2.setFont(new Font("supermarket", Font.BOLD, 18));
		btn_cell2.setBounds(10, 81, 125, 63);
		menu_cell2.add(btn_cell2);
		
		JPanel menu_cell3 = new JPanel();
		panel_menu.add(menu_cell3);
		menu_cell3.setLayout(null);
		
		JLabel lb_cell3 = new JLabel("<html><i>id: 000002</i><br \\>\r\n<b>CokeC(320ml)</b><br \\>\r\nin stock : <b>0</b><br \\>\r\n15 baht</html>");
		lb_cell3.setBounds(10, 11, 125, 56);
		lb_cell3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		menu_cell3.add(lb_cell3);
		
		JButton btn_cell3 = new JButton("Out of stock");
		btn_cell3.setEnabled(false);
		btn_cell3.setFont(new Font("supermarket", Font.BOLD, 18));
		btn_cell3.setBounds(10, 78, 125, 66);
		menu_cell3.add(btn_cell3);
		
		JPanel menu_cell4 = new JPanel();
		panel_menu.add(menu_cell4);
		menu_cell4.setLayout(null);
		
		JLabel lb_cell4 = new JLabel("<html><i>line1</i><br \\>\r\n<b>line2</b><br \\>\r\nline<b>3</b><br \\>\r\nline4</html>");
		lb_cell4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lb_cell4.setBounds(10, 11, 125, 56);
		menu_cell4.add(lb_cell4);
		
		JButton btn_cell4 = new JButton("Not avalible");
		btn_cell4.setFont(new Font("supermarket", Font.BOLD, 18));
		btn_cell4.setEnabled(false);
		btn_cell4.setBounds(10, 78, 125, 66);
		menu_cell4.add(btn_cell4);
		
		JPanel menu_cell5 = new JPanel();
		panel_menu.add(menu_cell5);
		menu_cell5.setLayout(null);
		
		JLabel lb_cell5 = new JLabel("<html><i>line1</i><br \\>\r\n<b>line2</b><br \\>\r\nline<b>3</b><br \\>\r\nline4</html>");
		lb_cell5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lb_cell5.setBounds(10, 11, 125, 56);
		menu_cell5.add(lb_cell5);
		
		JButton btn_cell5 = new JButton("Not avalible");
		btn_cell5.setFont(new Font("supermarket", Font.BOLD, 18));
		btn_cell5.setEnabled(false);
		btn_cell5.setBounds(10, 78, 125, 66);
		menu_cell5.add(btn_cell5);
		
		JPanel menu_cell6 = new JPanel();
		panel_menu.add(menu_cell6);
		menu_cell6.setLayout(null);
		
		JLabel lb_cell6 = new JLabel("<html><i>line1</i><br \\>\r\n<b>line2</b><br \\>\r\nline<b>3</b><br \\>\r\nline4</html>");
		lb_cell6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lb_cell6.setBounds(10, 11, 125, 56);
		menu_cell6.add(lb_cell6);
		
		JButton bt_cell6 = new JButton("Not avalible");
		bt_cell6.setFont(new Font("supermarket", Font.BOLD, 18));
		bt_cell6.setEnabled(false);
		bt_cell6.setBounds(10, 78, 125, 66);
		menu_cell6.add(bt_cell6);
		
		JPanel menu_cell7 = new JPanel();
		panel_menu.add(menu_cell7);
		menu_cell7.setLayout(null);
		
		JLabel lb_cell7 = new JLabel("<html><i>line1</i><br \\>\r\n<b>line2</b><br \\>\r\nline<b>3</b><br \\>\r\nline4</html>");
		lb_cell7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lb_cell7.setBounds(10, 11, 125, 56);
		menu_cell7.add(lb_cell7);
		
		JButton btn_cell7 = new JButton("Not avalible");
		btn_cell7.setFont(new Font("supermarket", Font.BOLD, 18));
		btn_cell7.setEnabled(false);
		btn_cell7.setBounds(10, 78, 125, 66);
		menu_cell7.add(btn_cell7);
		
		JPanel menu_cell8 = new JPanel();
		panel_menu.add(menu_cell8);
		menu_cell8.setLayout(null);
		
		JLabel lb_cell8 = new JLabel("<html><i>line1</i><br \\>\r\n<b>line2</b><br \\>\r\nline<b>3</b><br \\>\r\nline4</html>");
		lb_cell8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lb_cell8.setBounds(10, 11, 125, 56);
		menu_cell8.add(lb_cell8);
		
		JButton btn_cell8 = new JButton("Not avalible");
		btn_cell8.setFont(new Font("supermarket", Font.BOLD, 18));
		btn_cell8.setEnabled(false);
		btn_cell8.setBounds(10, 78, 125, 66);
		menu_cell8.add(btn_cell8);
		
		JPanel menu_cell9 = new JPanel();
		panel_menu.add(menu_cell9);
		menu_cell9.setLayout(null);
		
		JLabel lb_cell9 = new JLabel("<html><i>line1</i><br \\>\r\n<b>line2</b><br \\>\r\nline<b>3</b><br \\>\r\nline4</html>");
		lb_cell9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lb_cell9.setBounds(10, 11, 125, 56);
		menu_cell9.add(lb_cell9);
		
		JButton btn_cell9 = new JButton("Not avalible");
		btn_cell9.setFont(new Font("supermarket", Font.BOLD, 18));
		btn_cell9.setEnabled(false);
		btn_cell9.setBounds(10, 78, 125, 66);
		menu_cell9.add(btn_cell9);
		
		JPanel menu_cell10 = new JPanel();
		panel_menu.add(menu_cell10);
		menu_cell10.setLayout(null);
		
		JLabel lb_cell10 = new JLabel("<html><i>line1</i><br \\>\r\n<b>line2</b><br \\>\r\nline<b>3</b><br \\>\r\nline4</html>");
		lb_cell10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lb_cell10.setBounds(10, 11, 125, 56);
		menu_cell10.add(lb_cell10);
		
		JButton btn_cell10 = new JButton("Not avalible");
		btn_cell10.setFont(new Font("supermarket", Font.BOLD, 18));
		btn_cell10.setEnabled(false);
		btn_cell10.setBounds(10, 78, 125, 66);
		menu_cell10.add(btn_cell10);
		
		JPanel menu_cell11 = new JPanel();
		panel_menu.add(menu_cell11);
		menu_cell11.setLayout(null);
		
		JLabel lb_cell11 = new JLabel("<html><i>line1</i><br \\>\r\n<b>line2</b><br \\>\r\nline<b>3</b><br \\>\r\nline4</html>");
		lb_cell11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lb_cell11.setBounds(10, 11, 125, 56);
		menu_cell11.add(lb_cell11);
		
		JButton btn_cell11 = new JButton("Not avalible");
		btn_cell11.setFont(new Font("supermarket", Font.BOLD, 18));
		btn_cell11.setEnabled(false);
		btn_cell11.setBounds(10, 78, 125, 66);
		menu_cell11.add(btn_cell11);
		
		JPanel menu_cell12 = new JPanel();
		panel_menu.add(menu_cell12);
		menu_cell12.setLayout(null);
		
		JLabel lb_cell12 = new JLabel("<html><i>line1</i><br \\>\r\n<b>line2</b><br \\>\r\nline<b>3</b><br \\>\r\nline4</html>");
		lb_cell12.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lb_cell12.setBounds(10, 11, 125, 56);
		menu_cell12.add(lb_cell12);
		
		JButton btn_cell12 = new JButton("Not avalible");
		btn_cell12.setFont(new Font("supermarket", Font.BOLD, 18));
		btn_cell12.setEnabled(false);
		btn_cell12.setBounds(10, 78, 125, 66);
		menu_cell12.add(btn_cell12);
		*/
		
		JPanel panel_checkout = new JPanel();
		panel_checkout.setBounds(0, 0, 594, 465);
		stage.add(panel_checkout);
		panel_checkout.setLayout(null);
		panel_checkout.setVisible(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 574, 300);
		panel_checkout.add(scrollPane);
		
		checkoutTable = new JTable();
		scrollPane.setViewportView(checkoutTable);
		
		// Get add item menu to check out
		loadCheckoutDefaultTable();
		
		/*checkoutTable.setModel(new DefaultTableModel(
		//new Object[][] { {null, "2", "000001","walls Magnum Original", "65", "130"}, }
				 new Object[][] {},
				new String[] { "", "amount", "product id", "Product name", "price" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		checkoutTable.getColumnModel().getColumn(0).setResizable(false);
		checkoutTable.getColumnModel().getColumn(0).setPreferredWidth(16);
		checkoutTable.getColumnModel().getColumn(0).setMinWidth(16);
		checkoutTable.getColumnModel().getColumn(0).setMaxWidth(16);
		checkoutTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		checkoutTable.getColumnModel().getColumn(1).setMinWidth(50);
		checkoutTable.getColumnModel().getColumn(1).setMaxWidth(50);
		checkoutTable.getColumnModel().getColumn(2).setResizable(false);
		checkoutTable.getColumnModel().getColumn(2).setPreferredWidth(65);
		checkoutTable.getColumnModel().getColumn(2).setMinWidth(65);
		checkoutTable.getColumnModel().getColumn(2).setMaxWidth(65);
		checkoutTable.getColumnModel().getColumn(3).setPreferredWidth(175);
		checkoutTable.getColumnModel().getColumn(3).setMinWidth(175);
		checkoutTable.getColumnModel().getColumn(4).setResizable(false);
		checkoutTable.getColumnModel().getColumn(4).setPreferredWidth(70);
		checkoutTable.getColumnModel().getColumn(4).setMinWidth(70);
		checkoutTable.getColumnModel().getColumn(4).setMaxWidth(70);
		scrollPane.setViewportView(checkoutTable);
		*/
		
		//////////////////////////////////////////////////////////////////
		
		JPanel panel_MemberLogin = new JPanel();
		panel_MemberLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_MemberLogin.setBounds(10, 322, 140, 132);
		panel_checkout.add(panel_MemberLogin);
		panel_MemberLogin.setLayout(null);
		
		JLabel lb_MemberLogin = new JLabel("Member Login");
		lb_MemberLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_MemberLogin.setBounds(10, 11, 120, 23);
		panel_MemberLogin.add(lb_MemberLogin);
		
		// Format Phone Field
		MaskFormatter phoneMask = null;
		try {
			phoneMask = new MaskFormatter("0##-###-####");
			phoneMask.setPlaceholderCharacter('_');
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		txtfd_MemberLogin = new JFormattedTextField(phoneMask);
		txtfd_MemberLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btn_MemberLogin.doClick();
				}
			}
		});
		txtfd_MemberLogin.setBounds(10, 70, 120, 20);
		panel_MemberLogin.add(txtfd_MemberLogin);
		txtfd_MemberLogin.setColumns(10);
		
		btn_MemberLogin = new JButton("Login");
		btn_MemberLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer mem = new Customer(LoginForm.username, txtfd_MemberLogin.getText());
				if (mem.isPhoneMatched()) {
					buyerName = mem.getName();
					JOptionPane.showMessageDialog(null, "Hello, " + buyerName + "!");
					lblSumBuyer.setText("Buyer : " + buyerName);
					//loadSumPanelData();
				} else {
					JOptionPane.showMessageDialog(null, "Member not found!", "Member Login", JOptionPane.ERROR_MESSAGE);
					txtfd_MemberLogin.setText(null);
					buyerName = "guest";
					lblSumBuyer.setText("Buyer : " + buyerName);
					//loadSumPanelData();
				}
				loadSumPanelData();
			}
		});
		btn_MemberLogin.setBounds(10, 98, 120, 23);
		panel_MemberLogin.add(btn_MemberLogin);
		
		JLabel lblNewLabel_2 = new JLabel("Enter phone number :");
		lblNewLabel_2.setBounds(10, 45, 120, 14);
		panel_MemberLogin.add(lblNewLabel_2);
		
		JPanel panel_SpecialDiscount = new JPanel();
		panel_SpecialDiscount.setLayout(null);
		panel_SpecialDiscount.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_SpecialDiscount.setBounds(160, 322, 140, 132);
		panel_checkout.add(panel_SpecialDiscount);
		
		JLabel lb_SpecialDiscount = new JLabel("Special Discount");
		lb_SpecialDiscount.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_SpecialDiscount.setBounds(10, 11, 120, 23);
		panel_SpecialDiscount.add(lb_SpecialDiscount);
		
		cmbx_SpecialDiscount = new JComboBox();
		cmbx_SpecialDiscount.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				txtfd_SpecialDiscount.setText(null);
			}
		});
		cmbx_SpecialDiscount.setModel(new DefaultComboBoxModel(new String[] {"percent"}));
		cmbx_SpecialDiscount.setBounds(10, 40, 120, 20);
		panel_SpecialDiscount.add(cmbx_SpecialDiscount);
		
		// Format Special Discount Field
		MaskFormatter discountMask = null;
		try {
			phoneMask = new MaskFormatter("#.##");
			phoneMask.setPlaceholderCharacter('_');
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		txtfd_SpecialDiscount = new JFormattedTextField(discountMask);
		txtfd_SpecialDiscount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String input = txtfd_SpecialDiscount.getText() + e.getKeyChar();
				if (cmbx_SpecialDiscount.getSelectedIndex() == 0 && input.length() > 5) {
					e.consume();
				}
				try {
					double d = Double.parseDouble(input);
					if (cmbx_SpecialDiscount.getSelectedIndex() == 0 && d > 100) {
						e.consume();
					} else if (d > 10000) {
						// Cannot discount more than net price
						e.consume();
					}
				} catch (NumberFormatException ex) {
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btn_SpecialDiscount.doClick();
				}
			}
		});
		txtfd_SpecialDiscount.setColumns(10);
		txtfd_SpecialDiscount.setBounds(10, 70, 120, 20);
		panel_SpecialDiscount.add(txtfd_SpecialDiscount);
		
		btn_SpecialDiscount = new JButton("Add");
		btn_SpecialDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					specialDiscount = Double.parseDouble(txtfd_SpecialDiscount.getText());
					} catch (NumberFormatException ex) {
						specialDiscount = 0;
					}
					
				if (specialDiscount != 0) {
					if (!currFormat.equals("#,###.00")) {
						DecimalFormat format = new DecimalFormat("#,###.00");
						String f1 = format.format(specialDiscount);
						String f2 = f1.replace(",", ".").substring(0, f1.lastIndexOf(".")) + ","
								+ f1.substring(f1.lastIndexOf(".") + 1);

						lblSumSpecialDiscount.setText("Extra discount : " + f2
								+ (cmbx_SpecialDiscount.getSelectedIndex() == 0 ? "%" : " " + currSymbol));
					} else {
						//if (cmbx_SpecialDiscount.getSelectedIndex() == 0) {
							DecimalFormat format = new DecimalFormat(currFormat);
							lblSumSpecialDiscount.setText("Extra discount : " + format.format(specialDiscount)
									+ (cmbx_SpecialDiscount.getSelectedIndex() == 0 ? "%" : " " + currSymbol));
						//}
					}
				} else {
					lblSumSpecialDiscount.setText("<html><i>No Special Discount</i></html>");
				}
				
				loadSumPanelData();
				//System.out.println(specialDiscount);
			}
		});
		btn_SpecialDiscount.setBounds(10, 98, 120, 23);
		panel_SpecialDiscount.add(btn_SpecialDiscount);
		
		JPanel panel_Sum = new JPanel();
		panel_Sum.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_Sum.setBounds(310, 322, 170, 132);
		panel_checkout.add(panel_Sum);
		panel_Sum.setLayout(null);
		
		lblSumSeller = new JLabel("Seller : " + LoginForm.username);
		lblSumSeller.setBounds(10, 11, 150, 14);
		panel_Sum.add(lblSumSeller);
		
		lblSumBuyer = new JLabel("Buyer : guest");
		lblSumBuyer.setBounds(10, 25, 150, 14);
		panel_Sum.add(lblSumBuyer);
		
		lblSumDiscount = new JLabel("<html>\r\n<i>No Discount</i>\r\n</html>");
		lblSumDiscount.setBounds(10, 42, 150, 14);
		panel_Sum.add(lblSumDiscount);
		
		lblSumSpecialDiscount = new JLabel("<html><i>No Special Discount</i></html>");
		lblSumSpecialDiscount.setBounds(10, 57, 150, 14);
		panel_Sum.add(lblSumSpecialDiscount);
		
		lblSumVat = new JLabel("<html>\r\n<i>No VAT</i>\r\n</html>");
		lblSumVat.setBounds(10, 75, 150, 14);
		panel_Sum.add(lblSumVat);
		
		lblSumServiceCharge = new JLabel("<html>\r\n<i>No Service Charge</i>\r\n</html>");
		lblSumServiceCharge.setBounds(10, 90, 150, 14);
		panel_Sum.add(lblSumServiceCharge);
		
		lblSumNetPrice = new JLabel("<html>\r\nNet Price = <b>0.00 baht</b>\r\n</html>");
		lblSumNetPrice.setBounds(10, 107, 150, 14);
		panel_Sum.add(lblSumNetPrice);
		
		// Set text in Sum panel
		loadSumPanelData();
		
//		JLabel lb_line1 = new JLabel("<html>\r\nSeller : admin<br />\r\nBuyer : Guest<br />\r\n<i>No Discount</i><br />\r\n<i>No Special Discount</i><br />\r\nVAT <b>(7%)</b><br />\r\nService Charge <b>(10%)</b><br />\r\nNet Price = <b>1,000.00 baht</b><br />\r\n</html>");
//		lb_line1.setVisible(false);
//		lb_line1.setVerticalAlignment(SwingConstants.TOP);
//		lb_line1.setBounds(10, 11, 150, 110);
//		panel_Sum.add(lb_line1);
		
		btn_Checkout = new JButton("Checkout");
		btn_Checkout.setBackground(new Color(0, 51, 255));
		btn_Checkout.setForeground(Color.BLACK);
		btn_Checkout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (idListDynamic.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No item in the cart!", "Checkout", JOptionPane.WARNING_MESSAGE);
				} else {
					if (JOptionPane.showConfirmDialog(null, "Are you sure to checkout this order?", "Checkout",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_NO_OPTION) {

//					System.out.println(Arrays.toString(idList));
//					System.out.println(Arrays.toString(amountList));
//					System.out.println(Arrays.toString(priceList));

						Checkout chk = new Checkout(LoginForm.username, idList, amountList, priceList);
						chk.decreaseStock();
						
						String msg = lblSumSeller.getText() + "\n" + lblSumBuyer.getText() + "\n" + lblSumDiscount.getText()
						+ "\n" + lblSumSpecialDiscount.getText() + "\n" + lblSumVat.getText() + "\n"
						+ lblSumServiceCharge.getText() + "\n\n" + lblSumNetPrice.getText();
						
						chk.createReceipe(msg);
						
						JOptionPane.showMessageDialog(null, msg, "Order Complete!", JOptionPane.INFORMATION_MESSAGE);
						
						// frmKeptang.setVisible(false);

						new IndexRefresh(frmKeptang);
						
					}
				}
			}
		});
		btn_Checkout.setBounds(490, 370, 94, 84);
		panel_checkout.add(btn_Checkout);
		
		btnDeleteOrder = new JButton("Clear Order");
		btnDeleteOrder.setBackground(new Color(204, 0, 0));
		btnDeleteOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure to clear all order is this table?", "Clear Order",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_NO_OPTION) {
					loadCheckoutDefaultTable();

					idList = null;
					idListDynamic.clear();

					amountList = null;
					amountListDynamic.clear();

					priceList = null;
					priceListDynamic.clear();

					loadSumPanelData();
					getNetPrice();
				}
				
			}
		});
		btnDeleteOrder.setBounds(489, 322, 95, 37);
		panel_checkout.add(btnDeleteOrder);
		stage.setLayout(null);
		frmKeptang.getContentPane().setLayout(null);
		frmKeptang.getContentPane().add(menu);
		frmKeptang.getContentPane().add(stage);
		frmKeptang.getContentPane().add(ribbon);
		ribbon.setLayout(null);
		ribbon.add(lblLogo);
		ribbon.add(lblGreeting);
		ribbon.add(lblDateTime);

		
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Load User Config file
				try {
					new Setting().loadUserCfg();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				// panel_menu.setVisible(true);
				if (cmbxPage.getSelectedIndex() >= 0) {
					panels_menus[cmbxPage.getSelectedIndex()].setVisible(true);
					panel_checkout.setVisible(false);
				}
			}
		});
		
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//panel_checkout.setVisible(true);
				//panel_menu.setVisible(false);
				
				if (cmbxPage.getSelectedIndex() >= 0) {
					panel_checkout.setVisible(true);
					panels_menus[cmbxPage.getSelectedIndex()].setVisible(false);
					
					try {
						idList = convertArrayListToStringArray(idListDynamic);
						amountList = convertArrayListToIntegersArray(amountListDynamic);
						priceList = convertArrayListToDoubleArray(priceListDynamic);
						
//						System.out.println(Arrays.toString(idList));
//						System.out.println(Arrays.toString(amountList));
//						System.out.println(Arrays.toString(priceList));

						
						loadCheckoutTable();
						loadSumPanelData();
					} catch (NullPointerException ex) {
						// No item in cart
						//System.out.println("No item in cart");
					} catch (Exception ex2) {
						
					}
				}
			}
		});
		
		frmKeptang.addWindowListener(new java.awt.event.WindowAdapter() { // Confirm exiting the Application
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to close the application?",
						"Close Application?", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

	}

	private JButton btn_MemberLogin;

	private JButton btn_SpecialDiscount;

	private JPanel[] panels_menus;

	private int panelPage;

	private JLayeredPane stage;
	
	//////////////////////////////////////////////////////////////////////////////////
	
	private Object[][] productList;
	private Object[][] chkList;
	private String[] idList;
	private ArrayList<String> idListDynamic = new ArrayList<String>();
	private int[] amountList;
	private ArrayList<Integer> amountListDynamic = new ArrayList<Integer>();
	
	private double[] priceList;
	private ArrayList<Double> priceListDynamic = new ArrayList<Double>();
	
	// Load item from stocks to display in Menu state cells
	public void loadProductList() throws IOException {
		productList = new Item(LoginForm.username).getProductList();
	}
	
	// Get product list assign to Checkout List (cart)
	public void loadCart() {
		Checkout chk = new Checkout(LoginForm.username, idList, amountList);
		chkList = chk.getProductList();
	}
	
	// Load added item from Menu state cells to display in Checkout Table (Cart)
	public void loadCheckoutTable() {
		loadCart();
		checkoutTable.setModel(new DefaultTableModel(
			chkList,
			new String[] {
				"", "amount", "product id", "Product name", "price"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		    boolean[] columnEditables = new boolean[] {
			    true, false, false, false, false
		    };
		    public boolean isCellEditable(int row, int column) {
		    	return columnEditables[column];
		    }
		});
		checkoutTable.getColumnModel().getColumn(0).setResizable(false);
		checkoutTable.getColumnModel().getColumn(0).setPreferredWidth(16);
		checkoutTable.getColumnModel().getColumn(0).setMinWidth(16);
		checkoutTable.getColumnModel().getColumn(0).setMaxWidth(16);
		checkoutTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		checkoutTable.getColumnModel().getColumn(1).setMinWidth(50);
		checkoutTable.getColumnModel().getColumn(1).setMaxWidth(50);
		checkoutTable.getColumnModel().getColumn(2).setResizable(false);
		checkoutTable.getColumnModel().getColumn(2).setPreferredWidth(65);
		checkoutTable.getColumnModel().getColumn(2).setMinWidth(65);
		checkoutTable.getColumnModel().getColumn(2).setMaxWidth(65);
		checkoutTable.getColumnModel().getColumn(3).setPreferredWidth(175);
		checkoutTable.getColumnModel().getColumn(3).setMinWidth(175);
		checkoutTable.getColumnModel().getColumn(4).setResizable(false);
		checkoutTable.getColumnModel().getColumn(4).setPreferredWidth(70);
		checkoutTable.getColumnModel().getColumn(4).setMinWidth(70);
		checkoutTable.getColumnModel().getColumn(4).setMaxWidth(70);
		/////////////////////////////////////////////////////////////////////////////////
		//
		loadSumPanelData();
	}
	
	public void loadCheckoutDefaultTable() {
		checkoutTable.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"", "amount", "product id", "Product name", "price"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Object.class, Object.class, Object.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			    boolean[] columnEditables = new boolean[] {
				    true, false, false, false, false
			    };
			    public boolean isCellEditable(int row, int column) {
			    	return columnEditables[column];
			    }
			});
			checkoutTable.getColumnModel().getColumn(0).setResizable(false);
			checkoutTable.getColumnModel().getColumn(0).setPreferredWidth(16);
			checkoutTable.getColumnModel().getColumn(0).setMinWidth(16);
			checkoutTable.getColumnModel().getColumn(0).setMaxWidth(16);
			checkoutTable.getColumnModel().getColumn(1).setPreferredWidth(50);
			checkoutTable.getColumnModel().getColumn(1).setMinWidth(50);
			checkoutTable.getColumnModel().getColumn(1).setMaxWidth(50);
			checkoutTable.getColumnModel().getColumn(2).setResizable(false);
			checkoutTable.getColumnModel().getColumn(2).setPreferredWidth(65);
			checkoutTable.getColumnModel().getColumn(2).setMinWidth(65);
			checkoutTable.getColumnModel().getColumn(2).setMaxWidth(65);
			checkoutTable.getColumnModel().getColumn(3).setPreferredWidth(175);
			checkoutTable.getColumnModel().getColumn(3).setMinWidth(175);
			checkoutTable.getColumnModel().getColumn(4).setResizable(false);
			checkoutTable.getColumnModel().getColumn(4).setPreferredWidth(70);
			checkoutTable.getColumnModel().getColumn(4).setMinWidth(70);
			checkoutTable.getColumnModel().getColumn(4).setMaxWidth(70);
	}
	
	
	
	private String buyerName = "guest";
	private double specialDiscount = 0;
	public static int vatValue = 0;
	public static int scValue = 0;
	public static int memDisValue = 0;
	public static String currFormat, currSymbol;
	public double netPrice = 0;
	
	public void loadSumPanelData() {
		Setting cfg = new Setting();
		cfg.getUserConfig();
		
		lblSumSeller.setText("Seller : " + LoginForm.username);
		
		String[] userCfg = cfg.getCfg();
		
		if (userCfg != null) {
			vatValue = userCfg[0].equals("enable") ? Integer.parseInt(userCfg[1]) : 0;
			String lblVat = userCfg[0].equals("enable") ? "<html>VAT <b>(7%)</b></html>".replace("7", userCfg[1])
					: "<html><i>No VAT</i></html>";
			lblSumVat.setText(lblVat);

			scValue = userCfg[2].equals("enable") ? Integer.parseInt(userCfg[3]) : 0;
			String lblSc = userCfg[2].equals("enable")
					? "<html>Service Charge <b>(10%)</b></html>".replace("10", userCfg[3])
					: "<html><i>No Service Charge</i></html>";
			lblSumServiceCharge.setText(lblSc);

			currFormat = userCfg[6].equals("1") ? "#,###.00" : "#.###,00";
			currSymbol = userCfg[7].substring(1, userCfg[7].length() - 1);

			memDisValue = userCfg[9].equals("enable") ? Integer.parseInt(userCfg[10]) : 0;
			String lblMemDis = userCfg[9].equals("enable") && !buyerName.equals("guest")
					? "<html>Discount <b>(10%)</b></html>".replace("10", userCfg[10])
					: "<html><i>No Discount</i></html>";
			lblSumDiscount.setText(lblMemDis);

			// System.out.println(cfg.getGen_vat_amtSpinner().getValue());

			// System.out.println(userCfg[0]);

		} else {
			buyerName = "guest";
			specialDiscount = 0;
			vatValue = 0;
			scValue = 0;
			memDisValue = 0;
			netPrice = 0;
		}
		
		try {
//			System.out.println(specialDiscount);
//			System.out.println(vatValue);
//			System.out.println(scValue);
//			System.out.println(memDisValue);
//			System.out.println(netPrice);

			getNetPrice();
		} catch (NullPointerException ex) {
			// No item added in cart
			priceList = new double[] {0};
			amountList = new int[] {0};
		}
	}
	
	private Checkout checkout;

	private JButton btnDeleteOrder;

	private JButton btn_Checkout;
	
	public void getNetPrice() {
		checkout = new Checkout(priceList, amountList);
		netPrice = checkout.getNetPrice(checkout.getTotalPrice(),
				lblSumDiscount.getText().equals("<html><i>No Discount</i></html>") ? 0 : memDisValue, specialDiscount,
				vatValue, scValue);
		// lblSumNetPrice.setText("<html>Net Price = <b>0.00 baht</b></html>");

		if (!currFormat.equals("#,###.00")) {
			DecimalFormat format = new DecimalFormat("#,##0.00");
			String f1 = format.format(netPrice);
			String f2 = f1.replace(",", ".").substring(0, f1.lastIndexOf(".")) + ","
					+ f1.substring(f1.lastIndexOf(".") + 1);

			lblSumNetPrice.setText("<html>Net Price = <b>" + f2 + " " + currSymbol + "</b></html>");

		} else {
				DecimalFormat format = new DecimalFormat("#,##0.00");
			lblSumNetPrice.setText("<html>Net Price = <b>" + format.format(netPrice) + " " + currSymbol + "</b></html>");
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	
	public String[] getNumberPageList(int maxPage) {
		String[] list = new String[maxPage];
		for (int i = 0; i < list.length; i++) {
			list[i] = Integer.toString(i + 1);
		}
		return list;
	}
	
	public void closeAllPanelMenu() {
		for (int i = 0; i < panels_menus.length; i++) {
			panels_menus[i].setVisible(false);
		}
	}
	
	// Load menu in memnu state
	public void loadProductMenu() {
		JPanel[] menu_cell = new JPanel[12];
		JButton[] btn_cell = new JButton[12];
		JLabel[] lb_cell = new JLabel[12];
		
		// Generate menu stage button
		try {
			loadProductList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		if (productList != null) {
			// panelPage = (int) Math.ceil(product.length / 12d);
			panelPage = productList.length % 12 == 0 ? (productList.length / 12) : (productList.length / 12) + 1;
			panels_menus = new JPanel[panelPage];

			cmbxPage.setModel(new DefaultComboBoxModel(getNumberPageList(panelPage)));
			// System.out.println(panelPage);
			// System.out.println(Arrays.toString(getNumberPageList(panelPage)));

			// loadSumPanelData();

			int countItem = 0;

			for (int page = 0; page < panelPage; page++) {
				panels_menus[page] = new JPanel();
				panels_menus[page].setBounds(0, 0, 595, 465);
				panels_menus[page].setLayout(new GridLayout(0, 4, 0, 0));
				stage.add(panels_menus[page]);
				panels_menus[page].setVisible(false);

				for (int i = 0; i < menu_cell.length; i++) {

					menu_cell[i] = new JPanel();

					btn_cell[i] = new JButton("not available");
					btn_cell[i].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					btn_cell[i].setBounds(10, 81, 125, 63);
					btn_cell[i].setFont(new Font("supermarket", Font.BOLD, 18));
					btn_cell[i].setEnabled(false);

					
					////////////////////////////////////////////////////////////////////////////
					// Add item to Checkout table (cart)
					////////////////////////////////////////////////////////////////////////////
					// Assign index to button clicked action
					btn_cell[i].setActionCommand(Integer.toString(i));
					btn_cell[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// Get index of clicked button
							int btnIndex = Integer.parseInt(e.getActionCommand());
							
							//panels_menus[cmbxPage.getSelectedIndex()].get
							
							//btn_cell[btnIndex].setEnabled(false);
							//btn_cell[btnIndex].setText("added");
							
							int productLocation = btnIndex + (cmbxPage.getSelectedIndex() * 12);
							String clickedProductId = productList[productLocation][1].toString();
							
							if (!idListDynamic.contains(clickedProductId)) {

								int maxBuyable = Integer.parseInt(productList[productLocation][4].toString());

								try {
									while (true) {

										String strAmt = JOptionPane.showInputDialog(
												"How much amount do you want?" + "\n(max : " + maxBuyable + " unit)");
										if (strAmt.equals("") || strAmt == null) {
											break;
										}
										try {
											int amt = Integer.parseInt(strAmt);
											while (amt > maxBuyable || amt < 1) {
												strAmt = JOptionPane.showInputDialog((amt < 1
														? "Error, amount cannot below 0\n>> (max : " + maxBuyable
																+ " unit)"
														: "Please do not input more than limit\n>> (max : " + maxBuyable
																+ " unit)"));
												if (strAmt.equals("") || strAmt == null) {
													break;
												}
												amt = Integer.parseInt(strAmt);
											}
											
											// Add product data to Checkout Cart
											idListDynamic.add(clickedProductId);
											amountListDynamic.add(amt);
											priceListDynamic.add(Double.parseDouble(productList[productLocation][5].toString()));

//										System.out.println(productList[productLocation][1].toString());
//										System.out.println(amt);

											break;
										} catch (NullPointerException ex3) {
											// Do nothing on close
											break;
										} catch (NumberFormatException ex2) {
											JOptionPane.showMessageDialog(null, "Please input only integer!", "Error",
													JOptionPane.ERROR_MESSAGE);
										}
									}
								} catch (NullPointerException ex1) {
									// Do nothing on close
								}
								
							} else {
								JOptionPane.showMessageDialog(null, "This item(s) had been added", "Error",
										JOptionPane.WARNING_MESSAGE);
							}
							
						}
					});
					////////////////////////////////////////////////////////////////////////////
					
					
					lb_cell[i] = new JLabel(
							"<html><i>id: N/A</i><br \\>\r\n<b>N/A</b><br \\>\r\nin stock : <b>N/A</b><br \\>\r\nprice : <b>N/A</b></html>");
					lb_cell[i].setFont(new Font("Tahoma", Font.PLAIN, 11));
					lb_cell[i].setBounds(10, 11, 125, 59);

					if (countItem <= productList.length - 1) {
						DecimalFormat form = new DecimalFormat("0.00");
						String id = productList[countItem][1].toString();
						String name = productList[countItem][3].toString();
						int stock = Integer.parseInt(productList[countItem][4].toString());
						double price = Double.parseDouble(productList[countItem][5].toString());
						boolean availability = Boolean.parseBoolean(productList[countItem][6].toString());
						

						lb_cell[i].setText(
								"<html><i>id: " + id + "</i><br \\>\r\n<b>" + name + "</b><br \\>\r\nin stock : <b>"
										+ stock + "</b><br \\>\r\nprice : <b>" + form.format(price) + "</b> " + "</html>");

						if (availability && stock > 0) {
							btn_cell[i].setText("add");
							btn_cell[i].setEnabled(true);
						} else if (!availability) {
							btn_cell[i].setText("not available");
						} else {
							btn_cell[i].setText("out of stock");
						}

						countItem++;
						
					}

					panels_menus[page].add(menu_cell[i]);
					menu_cell[i].setLayout(null);
					menu_cell[i].add(lb_cell[i]);
					menu_cell[i].add(btn_cell[i]);
					
				}
			}

			if (panels_menus.length > 0) {
				panels_menus[0].setVisible(true);
			} else {
				panels_menus = new JPanel[1];
				panels_menus[0] = new JPanel();
				panels_menus[0].setBounds(0, 0, 595, 465);
				panels_menus[0].setLayout(new GridLayout(0, 4, 0, 0));
				stage.add(panels_menus[0]);
				panels_menus[0].setVisible(true);
				panels_menus[0].setLayout(null);
				
				JLabel lblNoStock = new JLabel(
						"<html><center>Welcome to Keptang!</center>" + "<br /><center>Please add some products first</center>"
								+ "<br /><i>Settings >> Stock >> Add New Product</i></html>");
				lblNoStock.setHorizontalAlignment(SwingConstants.CENTER);
				lblNoStock.setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));
				lblNoStock.setBounds(0, 0, 594, 465);
				panels_menus[0].add(lblNoStock);
			}
		} else {
			panels_menus = new JPanel[1];
			panels_menus[0] = new JPanel();
			panels_menus[0].setBounds(0, 0, 595, 465);
			panels_menus[0].setLayout(new GridLayout(0, 4, 0, 0));
			stage.add(panels_menus[0]);
			panels_menus[0].setVisible(true);
			panels_menus[0].setLayout(null);
			
			JLabel lblNoStock = new JLabel(
					"<html><center>Welcome to Keptang!</center>" + "<br /><center>Please add some products first</center>"
							+ "<br /><i>Settings >> Stock >> Add New Product</i></html>");
			lblNoStock.setHorizontalAlignment(SwingConstants.CENTER);
			lblNoStock.setFont(new Font("Sukhumvit Set", Font.PLAIN, 16));
			lblNoStock.setBounds(0, 0, 594, 465);
			panels_menus[0].add(lblNoStock);
			
		}
	}
	
	public int[] convertArrayListToIntegersArray(ArrayList<Integer> integers) {
		int[] ret = new int[integers.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = integers.get(i).intValue();
		}
		return ret;
	}

	public String[] convertArrayListToStringArray(ArrayList<String> strings) {
		String[] ret = new String[strings.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = strings.get(i).toString();
		}
		return ret;
	}
	
	public double[] convertArrayListToDoubleArray(ArrayList<Double> doubles) {
		double[] ret = new double[doubles.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = doubles.get(i).doubleValue();
		}
		return ret;
	}
	
//	public void addItemToCart() {
//		Checkout cart = new Checkout();
	
//	}
}
