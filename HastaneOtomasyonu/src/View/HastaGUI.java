package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Clinic;
import Model.Hasta;
import Model.Whour;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {

	private JPanel w_pane;
	private static Hasta hasta = new Hasta();
	private Clinic clinic = new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table_whour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoctorID = 0;
	private String selectDoctorName = null;
	private JTable table_appoint;
	private DefaultTableModel appointmentModel;
	private Object[] appointData = null;
	private Appointment appoint =new Appointment();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public HastaGUI(Hasta hasta) throws SQLException {

		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2];
		colDoctor[0] = "ID";
		colDoctor[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2];

		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		
		appointmentModel = new DefaultTableModel();
		Object[] colAppoint= new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Doktor";
		colAppoint[2] = "Tarih";
		appointmentModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		for(int i = 0; i<appoint.getHastaList(hasta.getId()).size();i++) {
			appointData[0]=appoint.getHastaList(hasta.getId()).get(i).getId();
			appointData[1]=appoint.getHastaList(hasta.getId()).get(i).getDoctorName();
			appointData[2]=appoint.getHastaList(hasta.getId()).get(i).getAppDate();
			appointmentModel.addRow(appointData);
		}
		

		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 520);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JButton btnNewButton = new JButton("Çýkýþ Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btnNewButton.setBounds(615, 20, 110, 25);
		w_pane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn " + hasta.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblNewLabel.setBounds(20, 20, 350, 20);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		w_tab.setBounds(10, 76, 716, 397);
		w_pane.add(w_tab);

		JPanel w_appoint = new JPanel();
		w_appoint.setBackground(Color.WHITE);
		w_tab.addTab("Randevu Sistemi", null, w_appoint, null);
		w_appoint.setLayout(null);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 30, 260, 325);
		w_appoint.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);

		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 7, 100, 20);
		w_appoint.add(lblNewLabel_1);

		JLabel lbl_poliniklikName = new JLabel("Polinik Ad\u0131");
		lbl_poliniklikName.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_poliniklikName.setBounds(290, 70, 100, 20);
		w_appoint.add(lbl_poliniklikName);

		JComboBox select_clinic = new JComboBox();
		select_clinic.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		select_clinic.setBounds(290, 93, 170, 30);
		select_clinic.addItem("--Poliklinik Seç--");
		for (int i = 0; i < clinic.getList().size(); i++) {
			select_clinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));
		}
		select_clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < clinic.getClinicDoktorList(item.getKey()).size(); i++) {
							doctorData[0] = clinic.getClinicDoktorList(item.getKey()).get(i).getId();
							doctorData[1] = clinic.getClinicDoktorList(item.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}

			}

		});
		w_appoint.add(select_clinic);

		JLabel btn_selDoctor = new JLabel("Doktor Se\u00E7");
		btn_selDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_selDoctor.setBounds(290, 165, 100, 20);
		w_appoint.add(btn_selDoctor);

		JButton btn_addClinic_1 = new JButton("Se\u00E7");
		btn_addClinic_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doctor.getSelectedRow();
				if (row >= 0) {
					String value = table_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_whour.setModel(whourModel);
					selectDoctorID = id;
					selectDoctorName = table_doctor.getModel().getValueAt(row, 1).toString();
					// System.out.println(selectDoctorID +" - "+ selectDoctorName);
				} else {
					Helper.showMsg("Lütfen bir doktor seçiniz ! ");
				}
			}
		});
		btn_addClinic_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_addClinic_1.setBounds(290, 195, 170, 30);
		w_appoint.add(btn_addClinic_1);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(480, 30, 220, 325);
		w_appoint.add(w_scrollWhour);

		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);

		JLabel lblNewLabel_1_1 = new JLabel("Uygun Saatler");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(480, 7, 220, 20);
		w_appoint.add(lblNewLabel_1_1);

		JButton btn_addAppoint = new JButton("Randevu Al");
		btn_addAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if (selRow >= 0) {
					String date = table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean control = hasta.addAppointment(selectDoctorID, hasta.getId(), selectDoctorName,
								hasta.getName(), date);
						if (control) {
							Helper.showMsg("success");
							hasta.updateWhourStatus(selectDoctorID, date);
							updateWhourModel(selectDoctorID);
							updateAppointModel(hasta.getId());
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen geçerli bir tarih giriniz !");
				}
			}
		});
		btn_addAppoint.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_addAppoint.setBounds(290, 290, 170, 30);
		w_appoint.add(btn_addAppoint);

		JLabel btn_selDoctor_1 = new JLabel("Randevu");
		btn_selDoctor_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_selDoctor_1.setBounds(290, 260, 100, 20);
		w_appoint.add(btn_selDoctor_1);
		
		JPanel w_app = new JPanel();
		w_tab.addTab("Randevularým", null, w_app, null);
		w_app.setLayout(null);
		
		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(10, 10, 691, 347);
		w_app.add(w_scrollAppoint);
		
		table_appoint = new JTable(appointmentModel);
		w_scrollAppoint.setViewportView(table_appoint);

	}

	public void updateWhourModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < whour.getWhourList(doctor_id).size(); i++) {
			whourData[0] = whour.getWhourList(doctor_id).get(i).getId();
			whourData[1] = whour.getWhourList(doctor_id).get(i).getWdate();
			whourModel.addRow(whourData);

		}
	}
	
	
	public void updateAppointModel(int hasta_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0);
		for(int i = 0; i<appoint.getHastaList(hasta_id).size();i++) {
			appointData[0]=appoint.getHastaList(hasta_id).get(i).getId();
			appointData[1]=appoint.getHastaList(hasta_id).get(i).getDoctorName();
			appointData[2]=appoint.getHastaList(hasta_id).get(i).getAppDate();
			appointmentModel.addRow(appointData);
		}
	}
	
}
