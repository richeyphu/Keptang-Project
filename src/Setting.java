import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.Window.Type;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.attribute.GroupPrincipal;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Account.Account;
import Account.AccountEdit;
import Account.Customer;
import Account.SaveCustomer;
import Account.SettingOption;
import Stock.DeleteProduct;
import Stock.RegisterItem;
import Stock.Item;
import Stock.SaveStock;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Setting {

	protected JFrame frmSettings;
	
	private JTable tblMembers;
	private JTable tblStock;
	private JTextField txtSymbol;
	private JRadioButton mem_dis_rdbtnDis;
	private JTextField OP_txtField_amount;
	private JTextField OP_txtField_point;
	
	private JFrame parentFrame;
	
	private static boolean isSaveClicked = false;
	private static String agent = "";
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Setting window = new Setting();
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
	public Setting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSettings = new JFrame();
		frmSettings.setIconImage(Initializer.icon); // Set frame's icon
		frmSettings.setAlwaysOnTop(true);
		frmSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSettings.setType(Type.NORMAL);
		frmSettings.setTitle("Keptang - Settings");
		frmSettings.setBounds(100, 100, 500, 500);
		frmSettings.getContentPane().setLayout(null);
		frmSettings.setResizable(false); // Disable window resizing on 'Setting' frame
		
		JTabbedPane settingPane = new JTabbedPane(JTabbedPane.TOP);
		settingPane.setBounds(0, 0, 486, 415);
		frmSettings.getContentPane().add(settingPane);
		
		JPanel setting_general = new JPanel();
		settingPane.addTab("General", null, setting_general, null);
		setting_general.setLayout(null);
		
		JPanel gen_vat = new JPanel();
		gen_vat.setBounds(10, 11, 461, 85);
		setting_general.add(gen_vat);
		gen_vat.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		gen_vat.setLayout(null);
		
		JLabel gen_vat_lbl = new JLabel("VAT");
		gen_vat_lbl.setBounds(10, 8, 110, 17);
		gen_vat.add(gen_vat_lbl);
		gen_vat_lbl.setVerticalAlignment(SwingConstants.TOP);
		gen_vat_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		gen_vat_rdbtnDisable = new JRadioButton("disable");
		gen_vat_rdbtnDisable.setSelected(true);
		gen_vat_rdbtnDisable.setBounds(25, 32, 65, 20);
		gen_vat.add(gen_vat_rdbtnDisable);
		
		gen_vat_rdbtnEnable = new JRadioButton("enable");
		gen_vat_rdbtnEnable.setBounds(25, 53, 65, 20);
		gen_vat.add(gen_vat_rdbtnEnable);
		
		//Group the Vat radio buttons.
	    groupVat = new ButtonGroup();
	    groupVat.add(gen_vat_rdbtnDisable);
	    groupVat.add(gen_vat_rdbtnEnable);
		
		gen_vat_lblAmt = new JLabel("amount(%)");
		//gen_vat_lblAmt.setEnabled(false);
		gen_vat_lblAmt.setEnabled(gen_vat_rdbtnEnable.isSelected());
		gen_vat_lblAmt.setBounds(100, 56, 55, 14);
		gen_vat.add(gen_vat_lblAmt);
		
		gen_vat_amtSpinner = new JSpinner();
		//gen_vat_amtSpinner.setEnabled(false);
		gen_vat_amtSpinner.setEnabled(gen_vat_rdbtnEnable.isSelected());
		gen_vat_amtSpinner.setBounds(165, 52, 50, 20);
		gen_vat.add(gen_vat_amtSpinner);
		gen_vat_amtSpinner.setModel(new SpinnerNumberModel(7, 1, 100, 1));
		
		// Listening to state change for VAT
		gen_vat_rdbtnEnable.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				gen_vat_lblAmt.setEnabled(gen_vat_rdbtnEnable.isSelected());
				gen_vat_amtSpinner.setEnabled(gen_vat_rdbtnEnable.isSelected());
			}
		});
		
		JPanel gen_SC = new JPanel();
		gen_SC.setLayout(null);
		gen_SC.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		gen_SC.setBounds(10, 107, 461, 85);
		setting_general.add(gen_SC);
		
		JLabel gen_SC_lbl = new JLabel("Service charge");
		gen_SC_lbl.setVerticalAlignment(SwingConstants.TOP);
		gen_SC_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		gen_SC_lbl.setBounds(10, 8, 110, 17);
		gen_SC.add(gen_SC_lbl);
		
		gen_SC_rdbtnDisable = new JRadioButton("disable");
		gen_SC_rdbtnDisable.setSelected(true);
		gen_SC_rdbtnDisable.setBounds(25, 32, 65, 20);
		gen_SC.add(gen_SC_rdbtnDisable);
		
		gen_SC_rdbtnEnable = new JRadioButton("enable");
		gen_SC_rdbtnEnable.setBounds(25, 53, 65, 20);
		gen_SC.add(gen_SC_rdbtnEnable);
		
		//Group the Service Charge radio buttons.
	    groupSC = new ButtonGroup();
	    groupSC.add(gen_SC_rdbtnDisable);
	    groupSC.add(gen_SC_rdbtnEnable);
		
		gen_SC_lblAmt = new JLabel("amount(%)");
		//gen_SC_lblAmt.setEnabled(false);
		gen_SC_lblAmt.setEnabled(gen_SC_rdbtnEnable.isSelected());
		gen_SC_lblAmt.setBounds(100, 56, 55, 14);
		gen_SC.add(gen_SC_lblAmt);
		
		gen_SC_amtSpinner = new JSpinner();
		gen_SC_amtSpinner.setModel(new SpinnerNumberModel(15, 1, 100, 1));
		//gen_SC_amtSpinner.setEnabled(false);
		gen_SC_amtSpinner.setEnabled(gen_SC_rdbtnEnable.isSelected());
		gen_SC_amtSpinner.setBounds(165, 52, 50, 20);
		gen_SC.add(gen_SC_amtSpinner);
		
		// Listening to state change for Service Charge
		gen_SC_rdbtnEnable.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				gen_SC_lblAmt.setEnabled(gen_SC_rdbtnEnable.isSelected());
				gen_SC_amtSpinner.setEnabled(gen_SC_rdbtnEnable.isSelected());
			}
		});
		
		JPanel gen_curr = new JPanel();
		gen_curr.setLayout(null);
		gen_curr.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		gen_curr.setBounds(10, 203, 461, 173);
		setting_general.add(gen_curr);
		
		gen_curr_lbl = new JLabel("Currency");
		gen_curr_lbl.setVerticalAlignment(SwingConstants.TOP);
		gen_curr_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		gen_curr_lbl.setBounds(10, 8, 110, 17);
		gen_curr.add(gen_curr_lbl);
		
		gen_curr_rdbthDisable = new JRadioButton("disable");
		gen_curr_rdbthDisable.setBounds(25, 32, 65, 18);
		gen_curr.add(gen_curr_rdbthDisable);
		
		gen_curr_rdbthEnable = new JRadioButton("enable");
		gen_curr_rdbthEnable.setSelected(true);
		gen_curr_rdbthEnable.setBounds(25, 53, 65, 20);
		gen_curr.add(gen_curr_rdbthEnable);
		
		//Group the Currency radio buttons.
	    groupCurr = new ButtonGroup();
	    groupCurr.add(gen_curr_rdbthDisable);
	    groupCurr.add(gen_curr_rdbthEnable);
		
		JPanel gen_curr_pos = new JPanel();
		gen_curr_pos.setLayout(null);
		gen_curr_pos.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		gen_curr_pos.setBounds(10, 78, 120, 85);
		gen_curr.add(gen_curr_pos);
		
		gen_curr_rdbtnFront = new JRadioButton("front");
		gen_curr_rdbtnFront.setBounds(25, 28, 65, 20);
		gen_curr_pos.add(gen_curr_rdbtnFront);
		
		gen_curr_rdbtnBack = new JRadioButton("back");
		gen_curr_rdbtnBack.setSelected(true);
		gen_curr_rdbtnBack.setBounds(25, 53, 65, 20);
		gen_curr_pos.add(gen_curr_rdbtnBack);
		
		//Group the Currency Position radio buttons.
	    groupCurrPos = new ButtonGroup();
	    groupCurrPos.add(gen_curr_rdbtnFront);
	    groupCurrPos.add(gen_curr_rdbtnBack);
		
		JLabel gen_curr_lblPos = new JLabel("position");
		gen_curr_lblPos.setBounds(12, 8, 55, 14);
		gen_curr_pos.add(gen_curr_lblPos);
		
		gen_curr_format = new JPanel();
		gen_curr_format.setLayout(null);
		gen_curr_format.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		gen_curr_format.setBounds(140, 78, 120, 85);
		gen_curr.add(gen_curr_format);
		
		gen_curr_rdbtnFormat1 = new JRadioButton("0,000.00");
		gen_curr_rdbtnFormat1.setSelected(true);
		gen_curr_rdbtnFormat1.setBounds(25, 28, 85, 20);
		gen_curr_format.add(gen_curr_rdbtnFormat1);
		
		gen_curr_rdbtnFormat2 = new JRadioButton("0.000,00");
		gen_curr_rdbtnFormat2.setBounds(25, 53, 85, 20);
		gen_curr_format.add(gen_curr_rdbtnFormat2);
		
		//Group the Currency Format radio buttons.
	    groupCurrFormat = new ButtonGroup();
	    groupCurrFormat.add(gen_curr_rdbtnFormat1);
	    groupCurrFormat.add(gen_curr_rdbtnFormat2);
		
		JLabel gen_curr_lblFormat = new JLabel("Currency format");
		gen_curr_lblFormat.setBounds(12, 8, 98, 14);
		gen_curr_format.add(gen_curr_lblFormat);
		
		JPanel gen_curr_name = new JPanel();
		gen_curr_name.setLayout(null);
		gen_curr_name.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		gen_curr_name.setBounds(270, 78, 181, 85);
		gen_curr.add(gen_curr_name);
		
		JLabel gen_curr_lblName = new JLabel("<html>Enter currency name or symbol<br \\>(ex. USD, baht)</html>");
		gen_curr_lblName.setBounds(12, 8, 159, 28);
		gen_curr_name.add(gen_curr_lblName);
		
		txtSymbol = new JTextField();
		txtSymbol.setText("baht");
		txtSymbol.setBounds(12, 47, 60, 20);
		gen_curr_name.add(txtSymbol);
		txtSymbol.setColumns(10);
		
		chckbxNewCheckBox = new JCheckBox("Auto add blank");
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setToolTipText("Add blank between number and currency name automatically.");
		chckbxNewCheckBox.setBounds(78, 46, 97, 23);
		gen_curr_name.add(chckbxNewCheckBox);
		
		// Listening to state change for Currency Format
		gen_curr_rdbthEnable.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				boolean isSelect = gen_curr_rdbthEnable.isSelected();
				
				gen_curr_pos.setEnabled(isSelect);
				gen_curr_rdbtnFront.setEnabled(isSelect);
				gen_curr_rdbtnBack.setEnabled(isSelect);
				gen_curr_lblPos.setEnabled(isSelect);
				
				gen_curr_format.setEnabled(isSelect);
				gen_curr_rdbtnFormat1.setEnabled(isSelect);
				gen_curr_rdbtnFormat2.setEnabled(isSelect);
				gen_curr_lblFormat.setEnabled(isSelect);
				
				gen_curr_name.setEnabled(isSelect);
				gen_curr_lblName.setEnabled(isSelect);
				txtSymbol.setEnabled(isSelect);
				chckbxNewCheckBox.setEnabled(isSelect);
			}
		});
		
		JPanel setting_member = new JPanel();
		settingPane.addTab("Members", null, setting_member, null);
		setting_member.setLayout(null);
	    
	    JScrollPane scrollPane_Members = new JScrollPane();
	    scrollPane_Members.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane_Members.setBounds(10, 11, 461, 266);
	    setting_member.add(scrollPane_Members);
	    tblMembers = new JTable();
	    tblMembers.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    scrollPane_Members.setViewportView(tblMembers);
	    
	    JPanel panel_MemberDiscount = new JPanel();
	    panel_MemberDiscount.setLayout(null);
	    panel_MemberDiscount.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	    panel_MemberDiscount.setBounds(10, 288, 289, 88);
	    setting_member.add(panel_MemberDiscount);
	    
	    lblMemberPromotion = new JLabel("Member Discount");
	    lblMemberPromotion.setVerticalAlignment(SwingConstants.TOP);
	    lblMemberPromotion.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblMemberPromotion.setBounds(10, 8, 145, 17);
	    panel_MemberDiscount.add(lblMemberPromotion);
	    
	    gen_MD_rdbtnDisable = new JRadioButton("disable");
	    gen_MD_rdbtnDisable.setSelected(true);
	    gen_MD_rdbtnDisable.setBounds(25, 32, 65, 20);
	    panel_MemberDiscount.add(gen_MD_rdbtnDisable);
	    
	    gen_MD_rdbtnEnable = new JRadioButton("enable");
	    gen_MD_rdbtnEnable.setBounds(25, 53, 65, 20);
	    panel_MemberDiscount.add(gen_MD_rdbtnEnable);
	    
		//Group the 'Member Discount' radio buttons.
	    groupMemDis = new ButtonGroup();
	    groupMemDis.add(gen_MD_rdbtnDisable);
	    groupMemDis.add(gen_MD_rdbtnEnable);
	    
	    JLabel gen_MD_lblAmt = new JLabel("amount(%)");
	    gen_MD_lblAmt.setEnabled(false);
	    gen_MD_lblAmt.setBounds(107, 56, 55, 14);
	    panel_MemberDiscount.add(gen_MD_lblAmt);
	    
	    gen_MD_amtSpinner = new JSpinner();
	    gen_MD_amtSpinner.setModel(new SpinnerNumberModel(10, 0, 100, 1));
	    gen_MD_amtSpinner.setEnabled(false);
	    gen_MD_amtSpinner.setBounds(172, 53, 50, 20);
	    panel_MemberDiscount.add(gen_MD_amtSpinner);
		
		// Listening to state change for Member Discount
		gen_MD_rdbtnEnable.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				gen_MD_lblAmt.setEnabled(gen_MD_rdbtnEnable.isSelected());
				gen_MD_amtSpinner.setEnabled(gen_MD_rdbtnEnable.isSelected());
			}
		});
	    
		JPanel setting_stock = new JPanel();
		settingPane.addTab("Stock", null, setting_stock, "managing your product");
	setting_stock.setLayout(null);
			
	JScrollPane scrollPane_Stock = new JScrollPane();
		scrollPane_Stock.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_Stock.setBounds(10, 11, 461, 319);
		setting_stock.add(scrollPane_Stock);
		
		tblStock = new JTable();
		tblStock.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_Stock.setViewportView(tblStock);
		/*tblStock.setModel(new DefaultTableModel(
			new Object[][] {
				{Boolean.FALSE, "000000", "walls Magnum Original", "wallsMagOrig", "65", "100", Boolean.TRUE},
				{Boolean.FALSE, "000001", "A&W(325ml)", "A&W(325ml)", "15", "120", Boolean.TRUE},
				{Boolean.FALSE, "000002", "Coke Can(320ml)", "CokeC(320ml)", "15", "0", Boolean.TRUE},
			},
			new String[] {
				"", "product id", "product name", "display name (max:12)", "price", "in stock", "enable"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblStock.getColumnModel().getColumn(0).setResizable(false);
		tblStock.getColumnModel().getColumn(0).setPreferredWidth(16);
		tblStock.getColumnModel().getColumn(0).setMinWidth(16);
		tblStock.getColumnModel().getColumn(0).setMaxWidth(16);
		tblStock.getColumnModel().getColumn(1).setResizable(false);
		tblStock.getColumnModel().getColumn(1).setPreferredWidth(60);
		tblStock.getColumnModel().getColumn(1).setMinWidth(60);
		tblStock.getColumnModel().getColumn(1).setMaxWidth(60);
		tblStock.getColumnModel().getColumn(2).setPreferredWidth(150);
		tblStock.getColumnModel().getColumn(2).setMinWidth(150);
		tblStock.getColumnModel().getColumn(2).setMaxWidth(400);
		tblStock.getColumnModel().getColumn(3).setPreferredWidth(130);
		tblStock.getColumnModel().getColumn(3).setMinWidth(130);
		tblStock.getColumnModel().getColumn(3).setMaxWidth(130);
		tblStock.getColumnModel().getColumn(4).setResizable(false);
		tblStock.getColumnModel().getColumn(4).setPreferredWidth(50);
		tblStock.getColumnModel().getColumn(4).setMinWidth(50);
		tblStock.getColumnModel().getColumn(4).setMaxWidth(50);
		tblStock.getColumnModel().getColumn(5).setResizable(false);
		tblStock.getColumnModel().getColumn(5).setPreferredWidth(50);
		tblStock.getColumnModel().getColumn(5).setMinWidth(50);
		tblStock.getColumnModel().getColumn(5).setMaxWidth(50);
		tblStock.getColumnModel().getColumn(6).setResizable(false);
		tblStock.getColumnModel().getColumn(6).setPreferredWidth(45);
		tblStock.getColumnModel().getColumn(6).setMinWidth(45);
		tblStock.getColumnModel().getColumn(6).setMaxWidth(45);
		scrollPane_Stock.setViewportView(tblStock);*/
		
		JButton btnAddNewProduct = new JButton("Add New Product");
		btnAddNewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSettings.setAlwaysOnTop(false);
				registerNewProduct();
				
				isSaveClicked = true;
				agent = LoginForm.username;
				btnCancel.setText("Refresh");
				frmSettings.setAlwaysOnTop(true);
				
				//saveUserConfig();
			}
		});
		btnAddNewProduct.setBounds(10, 341, 140, 35);
		setting_stock.add(btnAddNewProduct);
		
		JButton btnDeleteProduct = new JButton("Delete Product");
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSettings.setAlwaysOnTop(false);
				deleteProduct();
				
				isSaveClicked = true;
				agent = LoginForm.username;
				btnCancel.setText("Refresh");
				frmSettings.setAlwaysOnTop(true);
			}
		});
		btnDeleteProduct.setBounds(331, 341, 140, 35);
		setting_stock.add(btnDeleteProduct);
		
		/*
		JPanel setting_history = new JPanel();
		settingPane.addTab("History", null, setting_history, "bill history");
		setting_history.setLayout(null);

		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"log00000001_01012020_00.00.00", "log00000002_01012020_01.00.00"}));
		comboBox.setBounds(67, 11, 404, 22);
		setting_history.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("log name");
		lblNewLabel.setBounds(10, 15, 47, 14);
		setting_history.add(lblNewLabel);
		
		JTextPane txtpnAsdfAsdfAsdf = new JTextPane();
		txtpnAsdfAsdfAsdf.setEditable(false);
		txtpnAsdfAsdfAsdf.setBounds(10, 40, 461, 290);
		setting_history.add(txtpnAsdfAsdfAsdf);
		
		JButton btnExportAsxls = new JButton("Export as .xls");
		btnExportAsxls.setBounds(331, 341, 140, 35);
		setting_history.add(btnExportAsxls);
		*/
		
		JPanel setting_user = new JPanel();
		settingPane.addTab("User setting", null, setting_user, "managing user account");
		setting_user.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBounds(10, 11, 461, 100);
		setting_user.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSettings.setAlwaysOnTop(false);
				changePassword();
				frmSettings.setAlwaysOnTop(true);
			}
		});
		btnChangePassword.setBounds(38, 32, 163, 33);
		panel_1.add(btnChangePassword);
		
		JButton btnChangeQuestion = new JButton("Change Security Question");
		btnChangeQuestion.setBounds(260, 32, 163, 33);
		panel_1.add(btnChangeQuestion);
		btnChangeQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSettings.setAlwaysOnTop(false);
				changeSecurityQuestion();
				frmSettings.setAlwaysOnTop(true);
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setBounds(309, 288, 162, 88);
		setting_member.add(panel_2);
		
		JLabel lb_ObtainablePoint = new JLabel("Point Ratio");
		lb_ObtainablePoint.setVerticalAlignment(SwingConstants.TOP);
		lb_ObtainablePoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_ObtainablePoint.setBounds(10, 8, 120, 17);
		panel_2.add(lb_ObtainablePoint);
		
		OP_txtField_amount = new JTextField();
		OP_txtField_amount.setHorizontalAlignment(SwingConstants.RIGHT);
		OP_txtField_amount.setText("50");
		OP_txtField_amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();
				if (!Character.isDigit(input)) {
					e.consume();
				}
			}
		});
		OP_txtField_amount.setBounds(20, 53, 45, 20);
		panel_2.add(OP_txtField_amount);
		OP_txtField_amount.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Price : Point", SwingConstants.CENTER);
		lblNewLabel_1.setBounds(20, 36, 100, 14);
		panel_2.add(lblNewLabel_1);
		
		OP_txtField_point = new JTextField();
		OP_txtField_point.setText("1");
		OP_txtField_point.setColumns(10);
		OP_txtField_point.setBounds(75, 53, 45, 20);
		
		OP_txtField_point.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();
				if (!Character.isDigit(input)) {
					e.consume();
				}
			}
		});
		
		panel_2.add(OP_txtField_point);
		
		// Get user config from config file
		getUserConfig();
		/*
		//
		// Loading settings from user config
		try {
			loadUserCfg();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (cfg != null) {
			// VAT
			if(cfg[0].equals("enable")) {
				gen_vat_rdbtnEnable.setSelected(true);
				
				gen_vat_lblAmt.setEnabled(true);
				gen_vat_amtSpinner.setEnabled(true);
			}
			// VAT amount
			if(!cfg[1].equals("7")) {
				gen_vat_amtSpinner.setModel(new SpinnerNumberModel(Integer.parseInt(cfg[1]), 1, 100, 1));
			}
			
			// Service Charge
			if(cfg[2].equals("enable")) {
				gen_SC_rdbtnEnable.setSelected(true);
				
				gen_SC_lblAmt.setEnabled(true);
				gen_SC_amtSpinner.setEnabled(true);
			}
			// Service Charge amount
			if(!cfg[3].equals("15")) {
				gen_SC_amtSpinner.setModel(new SpinnerNumberModel(Integer.parseInt(cfg[3]), 1, 100, 1));
			}
			
			// Currency
			if(cfg[4].equals("disable")) {
				gen_curr_rdbthDisable.setSelected(true);
			}
			// Currency position
			if(cfg[5].equals("front")) {
				gen_curr_rdbtnFront.setSelected(true);
			}
			// Currency format
			if(cfg[6].equals("2")) {
				gen_curr_rdbtnFormat2.setSelected(true);
			}
			// Currency symbol
			if(!cfg[7].equals("baht")) {
				txtSymbol.setText(cfg[7].substring(1, cfg[7].length() - 1));
			}
			// Currency auto add blank
			if(cfg[8].equals("false")) {
				chckbxNewCheckBox.setSelected(false);
			}
			
			// MemberRIGHcount
			if(cfg[9].equals("enable")) {
				gen_MD_rdbtnEnable.setSelected(true);
			}
			// Member Discount amount
			if (!cfg[10].equals("10")) {
				gen_MD_amtSpinner.setModel(new SpinnerNumberModel(Integer.parseInt(cfg[10]), 1, 100, 1));
			}
			// Member Point Ratio
			if (!cfg[11].equals("50")) {
				 OP_txtField_amount.setText(cfg[11].substring(0));
			}
			if (!cfg[12].equals("1")) {
				 OP_txtField_point.setText(cfg[12].substring(0));
			}
		}
		//
		//
		// Load Members List
		try {
			loadMemberList();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		tblMembers.setModel(new DefaultTableModel(
	    	memberList,
	    	new String[] {
	    		"ID", "Name", "PhoneNum", "E-mail", "Birthdate", "Points"
	    	}
	    ) {
	    	boolean[] columnEditables = new boolean[] {
	    		false, true, true, true, true, true
	    	};
	    	public boolean isCellEditable(int row, int column) {
	    		return columnEditables[column];
	    	}
	    }); 
		
	    tblMembers.getColumnModel().getColumn(0).setResizable(false);
	    tblMembers.getColumnModel().getColumn(0).setPreferredWidth(55);
	    tblMembers.getColumnModel().getColumn(0).setMinWidth(55);
	    tblMembers.getColumnModel().getColumn(0).setMaxWidth(55);
	    tblMembers.getColumnModel().getColumn(1).setPreferredWidth(110);
	    tblMembers.getColumnModel().getColumn(1).setMinWidth(110);
	    tblMembers.getColumnModel().getColumn(2).setResizable(false);
	    tblMembers.getColumnModel().getColumn(2).setMinWidth(75);
	    tblMembers.getColumnModel().getColumn(2).setMaxWidth(75);
	    tblMembers.getColumnModel().getColumn(3).setPreferredWidth(150);
	    tblMembers.getColumnModel().getColumn(3).setMinWidth(150);
	    tblMembers.getColumnModel().getColumn(4).setResizable(false);
	    tblMembers.getColumnModel().getColumn(4).setPreferredWidth(65);
	    tblMembers.getColumnModel().getColumn(4).setMinWidth(65);
	    tblMembers.getColumnModel().getColumn(4).setMaxWidth(65);
	    tblMembers.getColumnModel().getColumn(5).setResizable(false);
	    tblMembers.getColumnModel().getColumn(5).setPreferredWidth(45);
	    tblMembers.getColumnModel().getColumn(5).setMinWidth(45);
	    tblMembers.getColumnModel().getColumn(5).setMaxWidth(45);
	    */
		//System.out.println(Arrays.toString(memberList));
		//
		//
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSettings.setAlwaysOnTop(false);
				
				try {
					saveMemberTable();
				} catch (NullPointerException ex) {

				}
				try {
					saveStockTable();
				} catch (NullPointerException ex) {

				}
				
				saveUserConfig();
				
				
				isSaveClicked = true;
				agent = LoginForm.username;
				btnCancel.setText("Refresh");
				
				if (JOptionPane.showConfirmDialog(null, "Do you want to refresh Keptang now?", "Refresh?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					btnCancel.doClick();
				}
				
				frmSettings.setAlwaysOnTop(true);

				/*
				// get RadioButton value...
				String vat, vat_amount;
				String sc, sc_amount;
				String currency, curr_position, curr_format, curr_symbol, curr_auto_add_blank;
				String md, md_amount, md_op_price, md_op_point;
				
				gen_vat_rdbtnDisable.setActionCommand("disable");
				gen_vat_rdbtnEnable.setActionCommand("enable");
				vat = groupVat.getSelection().getActionCommand();
				vat_amount = gen_vat_amtSpinner.getValue().toString();
				
				gen_SC_rdbtnDisable.setActionCommand("disable");
				gen_SC_rdbtnEnable.setActionCommand("enable");
				sc = groupSC.getSelection().getActionCommand();
				sc_amount = gen_SC_amtSpinner.getValue().toString();
				
				gen_curr_rdbthDisable.setActionCommand("disable");
				gen_curr_rdbthEnable.setActionCommand("enable");
				currency = groupCurr.getSelection().getActionCommand();
				
				gen_curr_rdbtnFront.setActionCommand("front");
				gen_curr_rdbtnBack.setActionCommand("back");
				curr_position = groupCurrPos.getSelection().getActionCommand();
				
				gen_curr_rdbtnFormat1.setActionCommand("1");
				gen_curr_rdbtnFormat2.setActionCommand("2");
				curr_format = groupCurrFormat.getSelection().getActionCommand();
				curr_symbol = "\"" + txtSymbol.getText() + "\"";
				curr_auto_add_blank = (chckbxNewCheckBox.isSelected() ? "true" : "false");
				
				gen_MD_rdbtnDisable.setActionCommand("disable");
				gen_MD_rdbtnEnable.setActionCommand("enable");
				md = groupMemDis.getSelection().getActionCommand();
				md_amount = gen_MD_amtSpinner.getValue().toString();
				md_op_price = OP_txtField_amount.getText();
				md_op_point = OP_txtField_point.getText();
				
				String[][] parameter = { { "vat", vat }, { "vat_amount", vat_amount }, { "service_charge", sc },
						{ "service_charge_amount", sc_amount }, { "currency", currency },
						{ "currency_position", curr_position }, { "currency_format", curr_format },
						{ "currency_symbol", curr_symbol }, { "currency_auto_add_blank", curr_auto_add_blank },
						{ "member_discount", md }, { "member_discount_amount", md_amount },
						{ "member_op_price", md_op_price }, { "member_op_point", md_op_point } };
				
				// Configuration saving process...
				frmSettings.setAlwaysOnTop(false);
				
				SettingOption save = new SettingOption(LoginForm.username, parameter);
				try {
					save.saveSetting();
					JOptionPane.showMessageDialog(null, "Settings have been saved!", "Saved!", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Settings cannot be saved!", "Error!", JOptionPane.ERROR_MESSAGE);
					
					e1.printStackTrace();
				} finally {
					frmSettings.setAlwaysOnTop(true);
				}
				*/
			}
		});
		btnSave.setBounds(10, 422, 80, 30);
		frmSettings.getContentPane().add(btnSave);
		
		btnCancel = new JButton("Cancel");
		
		isSaveClicked = agent.equals(LoginForm.username) ? isSaveClicked : false;
		btnCancel.setText(isSaveClicked ? "Refresh" : "Cancel");
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (isSaveClicked) {
					isSaveClicked = false;
					
					Index index = new Index();
					// index.frmKeptang.setEnabled(true);
					parentFrame.dispose();
					index.frmKeptang.setLocationRelativeTo(frmSettings);
					index.frmKeptang.setVisible(true);
				}
				
				button.setEnabled(true);
				frmSettings.setVisible(false);
				
			}
		});
		btnCancel.setBounds(100, 422, 80, 30);
		frmSettings.getContentPane().add(btnCancel);
		
		JButton gen_btnExit = new JButton("Exit Keptang");
		gen_btnExit.setBounds(351, 422, 120, 30);
		frmSettings.getContentPane().add(gen_btnExit);
		gen_btnExit.setBackground(new Color(153, 0, 51));
		gen_btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSettings.setAlwaysOnTop(false);
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit 'Keptang'?",
						"Exit Keptang?", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					frmSettings.setAlwaysOnTop(true);
				}
			}
		});
		
		frmSettings.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				//Index index = new Index();
				//index.frmKeptang.setEnabled(true);
				frmSettings.setVisible(false);
				button.setEnabled(true);
			}
		});
	}
	
	public JSpinner getGen_SC_amtSpinner() {
		return gen_SC_amtSpinner;
	}

	JButton button;
	public void setButton(JButton button) {
		this.button = button;
	}
	
	private String[] cfg; 
	public void loadUserCfg() throws IOException {
		cfg = new SettingOption(LoginForm.username).loadSetting();
	}
	
	public String[] getCfg() {
		return cfg;
	}

	private JRadioButton gen_vat_rdbtnDisable;
	private JRadioButton gen_vat_rdbtnEnable;
	private ButtonGroup groupVat;
	private JLabel gen_vat_lblAmt;
	private JSpinner gen_vat_amtSpinner;

	private ButtonGroup groupSC;
	private JLabel gen_SC_lblAmt;
	private JSpinner gen_SC_amtSpinner;
	
	private JLabel gen_curr_lbl;
	private JRadioButton gen_curr_rdbthDisable;
	private JRadioButton gen_curr_rdbthEnable;
	private ButtonGroup groupCurr;
	private JRadioButton gen_curr_rdbtnFront;
	private JRadioButton gen_curr_rdbtnBack;
	private ButtonGroup groupCurrPos;
	private JPanel gen_curr_format;
	private JRadioButton gen_curr_rdbtnFormat1;
	private JRadioButton gen_curr_rdbtnFormat2;
	private ButtonGroup groupCurrFormat;
	private JCheckBox chckbxNewCheckBox;
	
	private JLabel lblMemberPromotion;

	private JRadioButton gen_MD_rdbtnDisable;
	private JRadioButton gen_MD_rdbtnEnable;
	private ButtonGroup groupMemDis;
	private JSpinner gen_MD_amtSpinner;

	private JRadioButton gen_SC_rdbtnDisable;
	private JRadioButton gen_SC_rdbtnEnable; 
	
	public void getUserConfig() {
		// Loading settings from user config
				try {
					loadUserCfg();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if (cfg != null) {
					// VAT
					if(cfg[0].equals("enable")) {
						gen_vat_rdbtnEnable.setSelected(true);
						
						gen_vat_lblAmt.setEnabled(true);
						gen_vat_amtSpinner.setEnabled(true);
					}
					// VAT amount
					if(!cfg[1].equals("7")) {
						gen_vat_amtSpinner.setModel(new SpinnerNumberModel(Integer.parseInt(cfg[1]), 1, 100, 1));
					}
					
					// Service Charge
					if(cfg[2].equals("enable")) {
						gen_SC_rdbtnEnable.setSelected(true);
						
						gen_SC_lblAmt.setEnabled(true);
						gen_SC_amtSpinner.setEnabled(true);
					}
					// Service Charge amount
					if(!cfg[3].equals("15")) {
						gen_SC_amtSpinner.setModel(new SpinnerNumberModel(Integer.parseInt(cfg[3]), 1, 100, 1));
					}
					
					// Currency
					if(cfg[4].equals("disable")) {
						gen_curr_rdbthDisable.setSelected(true);
					}
					// Currency position
					if(cfg[5].equals("front")) {
						gen_curr_rdbtnFront.setSelected(true);
					}
					// Currency format
					if(cfg[6].equals("2")) {
						gen_curr_rdbtnFormat2.setSelected(true);
					}
					// Currency symbol
					if(!cfg[7].equals("baht")) {
						txtSymbol.setText(cfg[7].substring(1, cfg[7].length() - 1));
					}
					// Currency auto add blank
					if(cfg[8].equals("false")) {
						chckbxNewCheckBox.setSelected(false);
					}
					
					// MemberRIGHcount
					if(cfg[9].equals("enable")) {
						gen_MD_rdbtnEnable.setSelected(true);
					}
					// Member Discount amount
					if (!cfg[10].equals("10")) {
						gen_MD_amtSpinner.setModel(new SpinnerNumberModel(Integer.parseInt(cfg[10]), 1, 100, 1));
					}
					// Member Point Ratio
					if (!cfg[11].equals("50")) {
						 OP_txtField_amount.setText(cfg[11].substring(0));
					}
					if (!cfg[12].equals("1")) {
						 OP_txtField_point.setText(cfg[12].substring(0));
					}
				}
				//
				//
				// Load Members List
				try {
					loadMemberList();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				tblMembers.setModel(new DefaultTableModel(
			    	memberList,
			    	new String[] {
			    		"ID", "Name", "PhoneNum", "E-mail", "Birthdate", "Points"
			    	}
			    ) {
			    	boolean[] columnEditables = new boolean[] {
				    		false, true, true, true, false, true
			    	};
			    	public boolean isCellEditable(int row, int column) {
			    		return columnEditables[column];
			    	}
			    });
				tblMembers.getColumnModel().getColumn(0).setResizable(false);
				tblMembers.getColumnModel().getColumn(0).setPreferredWidth(55);
				tblMembers.getColumnModel().getColumn(0).setMinWidth(55);
				tblMembers.getColumnModel().getColumn(0).setMaxWidth(55);
				tblMembers.getColumnModel().getColumn(1).setPreferredWidth(150);
				tblMembers.getColumnModel().getColumn(1).setMinWidth(150);
				tblMembers.getColumnModel().getColumn(1).setMaxWidth(400);
				tblMembers.getColumnModel().getColumn(2).setResizable(false);
				tblMembers.getColumnModel().getColumn(2).setMinWidth(75);
				tblMembers.getColumnModel().getColumn(2).setMaxWidth(75);
				tblMembers.getColumnModel().getColumn(3).setPreferredWidth(150);
		    	tblMembers.getColumnModel().getColumn(3).setMinWidth(150);
		    	tblMembers.getColumnModel().getColumn(3).setMaxWidth(400);
		    	tblMembers.getColumnModel().getColumn(4).setResizable(false);
		    	tblMembers.getColumnModel().getColumn(4).setPreferredWidth(65);
		    	tblMembers.getColumnModel().getColumn(4).setMinWidth(65);
		    	tblMembers.getColumnModel().getColumn(4).setMaxWidth(65);
		    	tblMembers.getColumnModel().getColumn(5).setResizable(false);
		    	tblMembers.getColumnModel().getColumn(5).setPreferredWidth(45);
		    	tblMembers.getColumnModel().getColumn(5).setMinWidth(45);
		    	tblMembers.getColumnModel().getColumn(5).setMaxWidth(45);
		    	
		    	//
		    	//
		    	// Load Stock List
		    	try {
					loadProductList();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	tblStockLoad();
	}
	
	public void saveUserConfig() {
		// get RadioButton value...
		String vat, vat_amount;
		String sc, sc_amount;
		String currency, curr_position, curr_format, curr_symbol, curr_auto_add_blank;
		String md, md_amount, md_op_price, md_op_point;
		
		gen_vat_rdbtnDisable.setActionCommand("disable");
		gen_vat_rdbtnEnable.setActionCommand("enable");
		vat = groupVat.getSelection().getActionCommand();
		vat_amount = gen_vat_amtSpinner.getValue().toString();
		
		gen_SC_rdbtnDisable.setActionCommand("disable");
		gen_SC_rdbtnEnable.setActionCommand("enable");
		sc = groupSC.getSelection().getActionCommand();
		sc_amount = gen_SC_amtSpinner.getValue().toString();
		
		gen_curr_rdbthDisable.setActionCommand("disable");
		gen_curr_rdbthEnable.setActionCommand("enable");
		currency = groupCurr.getSelection().getActionCommand();
		
		gen_curr_rdbtnFront.setActionCommand("front");
		gen_curr_rdbtnBack.setActionCommand("back");
		curr_position = groupCurrPos.getSelection().getActionCommand();
		
		gen_curr_rdbtnFormat1.setActionCommand("1");
		gen_curr_rdbtnFormat2.setActionCommand("2");
		curr_format = groupCurrFormat.getSelection().getActionCommand();
		curr_symbol = "\"" + txtSymbol.getText() + "\"";
		curr_auto_add_blank = (chckbxNewCheckBox.isSelected() ? "true" : "false");
		
		gen_MD_rdbtnDisable.setActionCommand("disable");
		gen_MD_rdbtnEnable.setActionCommand("enable");
		md = groupMemDis.getSelection().getActionCommand();
		md_amount = gen_MD_amtSpinner.getValue().toString();
		md_op_price = OP_txtField_amount.getText();
		md_op_point = OP_txtField_point.getText();
		
		String[][] parameter = { { "vat", vat }, { "vat_amount", vat_amount }, { "service_charge", sc },
				{ "service_charge_amount", sc_amount }, { "currency", currency },
				{ "currency_position", curr_position }, { "currency_format", curr_format },
				{ "currency_symbol", curr_symbol }, { "currency_auto_add_blank", curr_auto_add_blank },
				{ "member_discount", md }, { "member_discount_amount", md_amount },
				{ "member_op_price", md_op_price }, { "member_op_point", md_op_point } };
		//;
		//;
		// Configuration saving process...
		frmSettings.setAlwaysOnTop(false);
		
		SettingOption save = new SettingOption(LoginForm.username, parameter);
		try {
			save.saveSetting();
			JOptionPane.showMessageDialog(null,
					"Settings have been saved!" + "\nPlease click 'Refresh' for updating data", "Saved!",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Settings cannot be saved!", "Error!", JOptionPane.ERROR_MESSAGE);
			
			e1.printStackTrace();
		} finally {
			//frmSettings.setAlwaysOnTop(true);
		}
	}
	
	private Object[][] memberList;
	private Object[][] productList;
	
	private Object[][] memberListSave;
	private Object[][] productListSave;

	private JButton btnCancel;
	
	private JButton btnSave;

	public void loadMemberList() throws IOException {
		memberList = new Customer(LoginForm.username).getMemberList();
	}

	public void loadProductList() throws IOException {
		productList = new Item(LoginForm.username).getProductList();
	}

	private int saveJtbMember() {
		SaveCustomer save = null;
		ArrayList<ArrayList<Object>> mainList = new ArrayList<ArrayList<Object>>();
		if(memberList.length!=0) {
			for (int i = 0; i < memberList.length; i++) {
				ArrayList<Object> mem = new ArrayList<Object>();
				for (int j = 0; j < memberList[i].length; j++) {
					//System.out.print(j + ". ");
					mem.add(tblMembers.getModel().getValueAt(i, j));
					//System.out.println(mem.get(j).toString());
				}
				save = new SaveCustomer(LoginForm.username, mem.get(1).toString(), mem.get(2).toString(),
						mem.get(3).toString(), mem.get(4).toString(), Integer.parseInt(mem.get(5).toString()),
						mem.get(0).toString());
				mainList.add(mem);
				
				if (!save.checkSave(mainList))
					return -1;
			}
		}
		try {
			memberListSave = save.convertArrayListTo2DObjectArray(mainList);
			return 0;
		} catch (NullPointerException e) {
			return 1;
		}
	}

	private int saveJtbStock() {
		SaveStock save = null;
		ArrayList<ArrayList<Object>> mainList = new ArrayList<ArrayList<Object>>();
		if(productList.length!=0) {
			for (int i = 0; i < productList.length; i++) {
				ArrayList<Object> mem = new ArrayList<Object>();
				for (int j = 0; j < productList[i].length; j++) {
					//System.out.print(j + ". ");
					mem.add(tblStock.getModel().getValueAt(i, j));
					//System.out.println(mem.get(j).toString());
				}
				try {
					save = new SaveStock(LoginForm.username, mem.get(1).toString(), mem.get(2).toString(),
							mem.get(3).toString(), Integer.parseInt(mem.get(4).toString()), mem.get(5).toString(),
							mem.get(6).toString());
					mem.set(3, save.checkDisplayNameAndSend());
					mainList.add(mem);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Some value in \"Stock\" tab is invalid," + "\nStock data cannot be saved!",
							"Please try again", JOptionPane.WARNING_MESSAGE);
					return -1;
				}
				if (!save.checkSave())
					return -1;
			}
		}
		try {
			productListSave = save.convertArrayListTo2DObjectArray(mainList);
			return 0;
		} catch (NullPointerException e) {
			return 1;
		}
	}
	
	private void saveMemberTable() {
		switch (saveJtbMember()) {
		case 0:
			SaveCustomer saveCust = new SaveCustomer();
			saveCust.saveMemberFile(memberListSave);
			break;
		case 1:
			break;
		case -1:
			break;
		default:
			break;
		}
	}
	
	private void saveStockTable() {
		switch (saveJtbStock()) {
		case 0:
			SaveStock saveStk = new SaveStock();
			saveStk.saveStockFile(productListSave);
			break;
		case 1:
			break;
		case -1:
			break;
		default:
			break;
		}
	}
	
	public void registerNewProduct() {
		RegisterItem item = new RegisterItem();
		try {
			loadProductList();
			tblStockLoad();
		} catch (Exception e) {
			
		}
	}
	
	public void deleteProduct() {
		if(productList.length!=0) {
			boolean[] delCheck = new boolean[productList.length];
			int count = 0;
			for (int i = 0; i < productList.length; i++) {
				//delCheck[i]=Boolean.parseBoolean(tblStock.getModel().getValueAt(i, 0).toString());
				if(delCheck[i]=Boolean.parseBoolean(tblStock.getModel().getValueAt(i, 0).toString())) {
					count++;
				}
			}
			if (count>0) {
				DeleteProduct del=new DeleteProduct(delCheck,count);
				if(del.deleteConfirm()) {
					del.deleteActivate(productList);
					try {
						loadProductList();
						//System.out.println("LoadMemberList");
						tblStockLoad();
						//System.out.println("LoadNewtable");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null,"You haven't selected anything to delete.",
						"Please try again.", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			
		}
	}
	
	public void tblStockLoad() {
		tblStock.setModel(new DefaultTableModel(
	    		productList,
	    		new String[] {
	    			"", "product id", "product name", "display name (max:12)", "in stock", "price", "enable"
	    		}
	    	) {
	    		Class[] columnTypes = new Class[] {
	    			Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
	    		};
	    		public Class getColumnClass(int columnIndex) {
	    				return columnTypes[columnIndex];
	    		}
	    		boolean[] columnEditables = new boolean[] {
			    		true, false, true, true, true, true, true
		    	};
		    	public boolean isCellEditable(int row, int column) {
		    		return columnEditables[column];
		    	}
	    	});
	    	tblStock.getColumnModel().getColumn(0).setResizable(false);
	    	tblStock.getColumnModel().getColumn(0).setPreferredWidth(16);
	   		tblStock.getColumnModel().getColumn(0).setMinWidth(16);
	   		tblStock.getColumnModel().getColumn(0).setMaxWidth(16);
	   		tblStock.getColumnModel().getColumn(1).setResizable(false);
	   		tblStock.getColumnModel().getColumn(1).setPreferredWidth(60);
    		tblStock.getColumnModel().getColumn(1).setMinWidth(60);
    		tblStock.getColumnModel().getColumn(1).setMaxWidth(60);
	    	tblStock.getColumnModel().getColumn(2).setPreferredWidth(150);
	    	tblStock.getColumnModel().getColumn(2).setMinWidth(150);
	   		tblStock.getColumnModel().getColumn(2).setMaxWidth(400);
	   		tblStock.getColumnModel().getColumn(3).setPreferredWidth(130);
	   		tblStock.getColumnModel().getColumn(3).setMinWidth(130);
	   		tblStock.getColumnModel().getColumn(3).setMaxWidth(130);
    		tblStock.getColumnModel().getColumn(4).setResizable(false);
    		tblStock.getColumnModel().getColumn(4).setPreferredWidth(50);
	    	tblStock.getColumnModel().getColumn(4).setMinWidth(50);
	    	tblStock.getColumnModel().getColumn(4).setMaxWidth(50);
	   		tblStock.getColumnModel().getColumn(5).setResizable(false);
	   		tblStock.getColumnModel().getColumn(5).setPreferredWidth(70);
	   		tblStock.getColumnModel().getColumn(5).setMinWidth(70);
	   		tblStock.getColumnModel().getColumn(5).setMaxWidth(70);
	   		tblStock.getColumnModel().getColumn(6).setResizable(false);
	    	tblStock.getColumnModel().getColumn(6).setPreferredWidth(45);
	   		tblStock.getColumnModel().getColumn(6).setMinWidth(45);
	   		tblStock.getColumnModel().getColumn(6).setMaxWidth(45);
	}
	
	public void changePassword() {
		final String TITLE = "Change Password";
		
		JTextField password = new JPasswordField();
		Object[] message = { "Please enter your old password:", password };

		int option = JOptionPane.showConfirmDialog(null, message, TITLE, JOptionPane.OK_CANCEL_OPTION);
		
		Account user = new Account(LoginForm.username, password.getText());
		
		if (option == JOptionPane.OK_OPTION) {
		    if (!user.login()) {
		        JOptionPane.showMessageDialog(null, "Your password is incorrect!", TITLE, JOptionPane.ERROR_MESSAGE);
		    } else {
		    	JTextField newPwd = new JPasswordField();
		    	JTextField reNewPwd = new JPasswordField();
		    	Object[] msg = {
		    	    "New password:", newPwd,
		    	    "Re-enter new password:", reNewPwd
		    	};

				while (true) {
					int opt = JOptionPane.showConfirmDialog(null, msg, TITLE, JOptionPane.OK_CANCEL_OPTION);
					if (opt == JOptionPane.OK_OPTION) {
						AccountEdit pwd = new AccountEdit(newPwd.getText(), reNewPwd.getText(), user);

						if (!pwd.isValidPassword()) {
							JOptionPane.showMessageDialog(null,
									"Password must be 8 characters or longer and contain"
											+ "\nat least 1 'capital letter', 'small letter' and 'number'",
											TITLE, JOptionPane.ERROR_MESSAGE);
						} else if (pwd.isContainNG()) {
							JOptionPane.showMessageDialog(null, "Any field must not contain character : ','",
									TITLE, JOptionPane.ERROR_MESSAGE);
						} else if (pwd.isPasswordMatched()) {
							pwd.changePassword();

							JOptionPane.showMessageDialog(null, "Password has been changed!", TITLE,
									JOptionPane.INFORMATION_MESSAGE);
							break;
						} else {
							JOptionPane.showMessageDialog(null, "Please re-enter your new password again",
									TITLE, JOptionPane.ERROR_MESSAGE);
						}
					} else {
						// Do nothing...
						break;
					}
				}
		    }
		} else {
			// Do nothing...
		}
	}
	
	public void changeSecurityQuestion() {
		final String TITLE = "Change Security Question";
		
		JTextField password = new JPasswordField();
		Object[] message = { "Please enter your password:", password };

		int option = JOptionPane.showConfirmDialog(null, message, TITLE, JOptionPane.OK_CANCEL_OPTION);
		
		Account user = new Account(LoginForm.username, password.getText());
		
		if (option == JOptionPane.OK_OPTION) {
		    if (!user.login()) {
		        JOptionPane.showMessageDialog(null, "Your password is incorrect!", TITLE, JOptionPane.ERROR_MESSAGE);
		    } else {
		    	JTextField newQuestion = new JTextField();
		    	JTextField newAnswer = new JTextField();
		    	Object[] msg = {
		    	    "New question:", newQuestion,
		    	    "New Answer:", newAnswer
		    	};

				while (true) {
					int opt = JOptionPane.showConfirmDialog(null, msg, TITLE, JOptionPane.OK_CANCEL_OPTION);
					if (opt == JOptionPane.OK_OPTION) {
						user = new Account(LoginForm.username, "", newQuestion.getText(), newAnswer.getText());

						if (Character.isWhitespace(user.getQuestion().charAt(0))
								|| Character.isWhitespace(user.getAnswer().charAt(0))) {
							JOptionPane.showMessageDialog(null,
									"Please fill up the fields properly",
									TITLE, JOptionPane.ERROR_MESSAGE);
						} else {
							int confirm = JOptionPane.showConfirmDialog(null,
									"Question:\n" + user.getQuestion() + "\n\nAnswer:\n" + user.getAnswer(), "Confirm Changing",
									JOptionPane.YES_NO_OPTION);
							
							if (confirm == JOptionPane.YES_OPTION) {
								AccountEdit question = new AccountEdit(user);
								question.changeSecurityQuestion();
								JOptionPane.showMessageDialog(null, "Security Question has been changed!", TITLE,
										JOptionPane.INFORMATION_MESSAGE);
								break;
							} else if (confirm == -1) {
								break;
							}
						}

						
					} else {
						// Do nothing...
						break;
					}
				}
		    }
		} else {
			// Do nothing...
		}
	}

	public ButtonGroup getGroupVat() {
		return groupVat;
	}

	public JSpinner getGen_vat_amtSpinner() {
		return gen_vat_amtSpinner;
	}

	public ButtonGroup getGroupSC() {
		return groupSC;
	}

	public ButtonGroup getGroupCurr() {
		return groupCurr;
	}

	public ButtonGroup getGroupCurrPos() {
		return groupCurrPos;
	}

	public ButtonGroup getGroupCurrFormat() {
		return groupCurrFormat;
	}

	public ButtonGroup getGroupMemDis() {
		return groupMemDis;
	}

	public JSpinner getGen_MD_amtSpinner() {
		return gen_MD_amtSpinner;
	}
	
	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}
}
