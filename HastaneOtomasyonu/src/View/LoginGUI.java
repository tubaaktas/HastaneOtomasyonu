package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_doktorTc;
	private JPasswordField fld_doktorPass;
	private DBConnection conn = new DBConnection();
	private JPasswordField fld_hastaPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("hospital.png")));
		lbl_logo.setBounds(225, 5, 70, 60);
		w_pane.add(lbl_logo);

		JLabel lblNewLabel = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeldiniz");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblNewLabel.setBounds(73, 70, 374, 25);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 126, 476, 236);
		w_pane.add(w_tabpane);

		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Giriþi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);

		JLabel lblTcNo = new JLabel("T.C. Numaran\u0131z :");
		lblTcNo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblTcNo.setBounds(30, 30, 170, 30);
		w_hastaLogin.add(lblTcNo);

		JLabel lblifre = new JLabel("\u015Eifre :");
		lblifre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblifre.setBounds(30, 80, 170, 30);
		w_hastaLogin.add(lblifre);

		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		fld_hastaTc.setBounds(165, 30, 273, 30);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);

		JButton btn_register = new JButton("Kay\u0131t Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_register.setBounds(30, 148, 190, 50);
		w_hastaLogin.add(btn_register);

		JButton btn_hastaLogin = new JButton("Giri\u015F Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_hastaTc.getText().length() == 0 || fld_hastaPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean key = true;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user ");
						while (rs.next()) {
							if (fld_hastaTc.getText().equals(rs.getString("tcno"))
									&& fld_hastaPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;
								}

							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (key) {
						Helper.showMsg("Böyle bir hasta bulunamadý, lütfen kayýt olunuz !");
					}
				}
			}
		});
		btn_hastaLogin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_hastaLogin.setBounds(248, 148, 190, 50);
		w_hastaLogin.add(btn_hastaLogin);

		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		fld_hastaPass.setBounds(165, 80, 273, 30);
		w_hastaLogin.add(fld_hastaPass);

		JPanel w_doktorLogin = new JPanel();
		w_doktorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Giriþi", null, w_doktorLogin, null);
		w_doktorLogin.setLayout(null);

		JLabel lblTcNo_1 = new JLabel("T.C. Numaran\u0131z :");
		lblTcNo_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblTcNo_1.setBounds(30, 30, 170, 30);
		w_doktorLogin.add(lblTcNo_1);

		fld_doktorTc = new JTextField();
		fld_doktorTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		fld_doktorTc.setColumns(10);
		fld_doktorTc.setBounds(165, 30, 273, 30);
		w_doktorLogin.add(fld_doktorTc);

		JLabel lblifre_1 = new JLabel("\u015Eifre :");
		lblifre_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblifre_1.setBounds(30, 80, 170, 30);
		w_doktorLogin.add(lblifre_1);

		JButton btn_doktorLogin = new JButton("Giri\u015F Yap");
		btn_doktorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doktorTc.getText().length() == 0 || fld_doktorPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user ");
						while (rs.next()) {
							if (fld_doktorTc.getText().equals(rs.getString("tcno"))
									&& fld_doktorPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();

								}

								if (rs.getString("type").equals("doktor")) {
									Doctor doktor = new Doctor();
									doktor.setId(rs.getInt("id"));
									doktor.setPassword("password");
									doktor.setTcno(rs.getString("tcno"));
									doktor.setName(rs.getString("name"));
									doktor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doktor);
									dGUI.setVisible(true);
									dispose();

								}

							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doktorLogin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_doktorLogin.setBounds(30, 148, 408, 50);
		w_doktorLogin.add(btn_doktorLogin);

		fld_doktorPass = new JPasswordField();
		fld_doktorPass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		fld_doktorPass.setBounds(165, 80, 273, 30);
		w_doktorLogin.add(fld_doktorPass);
	}
}
