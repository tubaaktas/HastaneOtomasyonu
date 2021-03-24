package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setTitle("Hastane Y\u00F6netim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel label = new JLabel("Ad Soyad");
		label.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		label.setBounds(10, 10, 100, 20);
		w_pane.add(label);
		
		fld_name = new JTextField();
		fld_name.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		fld_name.setColumns(10);
		fld_name.setBounds(10, 35, 270, 30);
		w_pane.add(fld_name);
		
		JLabel lblTcNumarasý = new JLabel("T.C. Numaras\u0131");
		lblTcNumarasý.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblTcNumarasý.setBounds(10, 75, 100, 20);
		w_pane.add(lblTcNumarasý);
		
		fld_tcno = new JTextField();
		fld_tcno.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 105, 270, 30);
		w_pane.add(fld_tcno);
		
		JLabel lbl_sifre = new JLabel("\u015Eifre");
		lbl_sifre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_sifre.setBounds(10, 145, 100, 20);
		w_pane.add(lbl_sifre);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(10, 175, 270, 30);
		w_pane.add(fld_pass);
		
		
		JButton btn_register = new JButton("Kayýt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length()==0 || fld_pass.getText().length()==0 || fld_name.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = hasta.register(fld_tcno.getText(),fld_pass.getText(), fld_name.getText());
						if(control) {
							Helper.showMsg("success");
							LoginGUI login=new LoginGUI();
							login.setVisible(true);
							dispose();
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_register.setBounds(10, 222, 270, 30);
		w_pane.add(btn_register);
		
		JButton btn_backto = new JButton("Geri D\u00F6n");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backto.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_backto.setBounds(10, 262, 270, 30);
		w_pane.add(btn_backto);
	}
}
