package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {

	private JPanel contentPane;
	static Bashekim bashekim = new Bashekim();
	Clinic clinic = new Clinic();
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JTextField fld_dPass;
	private JTextField fld_doktorID;
	private JTable table_doktor;
	private DefaultTableModel doktorModel = null;
	private Object[] doktorData = null;
	private JTable table_clinic;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	public BashekimGUI(Bashekim bashekim) throws SQLException {

		// DoktorModel
		doktorModel = new DefaultTableModel();
		Object[] colDoktorName = new Object[4];
		colDoktorName[0] = "ID";
		colDoktorName[1] = "Ad Soyad";
		colDoktorName[2] = "T.C. No";
		colDoktorName[3] = "Þifre";
		doktorModel.setColumnIdentifiers(colDoktorName);
		doktorData = new Object[4];
		for (int i = 0; i < bashekim.getDoktorList().size(); i++) {
			doktorData[0] = bashekim.getDoktorList().get(i).getId();
			doktorData[1] = bashekim.getDoktorList().get(i).getName();
			doktorData[2] = bashekim.getDoktorList().get(i).getTcno();
			doktorData[3] = bashekim.getDoktorList().get(i).getPassword();
			doktorModel.addRow(doktorData);

		}

		// Clinic Model
		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "ID";
		colClinic[1] = "Polinik Adý";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);

		}

		// WorkerModel

		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 520);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn " + bashekim.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblNewLabel.setBounds(20, 20, 350, 20);
		contentPane.add(lblNewLabel);

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
		contentPane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 76, 716, 397);
		contentPane.add(w_tab);

		JPanel w_doktor = new JPanel();
		w_doktor.setBackground(Color.WHITE);
		w_tab.addTab("Doktor Yönetimi", null, w_doktor, null);
		w_tab.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		w_doktor.setLayout(null);

		JLabel label_1 = new JLabel("Ad Soyad");
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		label_1.setBounds(500, 10, 100, 20);
		w_doktor.add(label_1);

		fld_dName = new JTextField();
		fld_dName.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		fld_dName.setBounds(500, 35, 200, 30);
		w_doktor.add(fld_dName);
		fld_dName.setColumns(10);

		JLabel label_2 = new JLabel("T.C. No");
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		label_2.setBounds(500, 75, 100, 20);
		w_doktor.add(label_2);

		fld_dTcno = new JTextField();
		fld_dTcno.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(500, 100, 200, 30);
		w_doktor.add(fld_dTcno);

		JLabel label_3 = new JLabel("\u015Eifre");
		label_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		label_3.setBounds(500, 140, 100, 20);
		w_doktor.add(label_3);

		fld_dPass = new JTextField();
		fld_dPass.setColumns(10);
		fld_dPass.setBounds(500, 165, 200, 30);
		w_doktor.add(fld_dPass);

		JButton btn_addDoktor = new JButton("Ekle");
		btn_addDoktor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_dName.getText().length() == 0 || fld_dPass.getText().length() == 0
						|| fld_dTcno.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = bashekim.addDoctor(fld_dTcno.getText(), fld_dPass.getText(),
								fld_dName.getText());
						if (control) {
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_dTcno.setText(null);
							fld_dPass.setText(null);
							updateDoktorModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addDoktor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_addDoktor.setBounds(500, 220, 200, 30);
		w_doktor.add(btn_addDoktor);

		JLabel label_4 = new JLabel("Kullan\u0131c\u0131 ID ");
		label_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		label_4.setBounds(500, 260, 100, 20);
		w_doktor.add(label_4);

		fld_doktorID = new JTextField();
		fld_doktorID.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		fld_doktorID.setEnabled(false);
		fld_doktorID.setColumns(10);
		fld_doktorID.setBounds(500, 290, 200, 30);
		w_doktor.add(fld_doktorID);

		JButton btn_delDoktor = new JButton("Sil");
		btn_delDoktor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doktorID.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçiniz !");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doktorID.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_doktorID.setText(null);
								updateDoktorModel();

							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_delDoktor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_delDoktor.setBounds(500, 330, 200, 30);
		w_doktor.add(btn_delDoktor);

		JScrollPane w_scrollDoktor = new JScrollPane();
		w_scrollDoktor.setBounds(10, 10, 481, 350);
		w_doktor.add(w_scrollDoktor);

		table_doktor = new JTable(doktorModel);
		w_scrollDoktor.setViewportView(table_doktor);

		table_doktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doktorID.setText(table_doktor.getValueAt(table_doktor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {

				}
			}
		});

		table_doktor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_doktor.getValueAt(table_doktor.getSelectedRow(), 0).toString());
					String selectName = table_doktor.getValueAt(table_doktor.getSelectedRow(), 1).toString();
					String selectTcno = table_doktor.getValueAt(table_doktor.getSelectedRow(), 2).toString();
					String selectPass = table_doktor.getValueAt(table_doktor.getSelectedRow(), 3).toString();

					try {
						boolean control = bashekim.updateDoctor(selectID, selectTcno, selectName, selectPass);
						if (control) {
							Helper.showMsg("success");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});

		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tab.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 10, 242, 347);
		w_clinic.add(w_scrollClinic);

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {
			// UPDATE CLÝNÝC
			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
			}

		});

		// DELETE CLÝNÝC
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
						if (clinic.deleteClinic(selID)) {
							Helper.showMsg("success");
							updateClinicModel();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);

			}
		});

		w_scrollClinic.setViewportView(table_clinic);

		JLabel lbl_poliniklikName = new JLabel("Polinik Ad\u0131");
		lbl_poliniklikName.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_poliniklikName.setBounds(262, 10, 100, 20);
		w_clinic.add(lbl_poliniklikName);

		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(262, 40, 190, 30);
		w_clinic.add(fld_clinicName);

		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_clinicName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (clinic.addClinic(fld_clinicName.getText()))
							;
						Helper.showMsg("success");
						fld_clinicName.setText(null);
						updateClinicModel();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addClinic.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_addClinic.setBounds(262, 80, 190, 30);
		w_clinic.add(btn_addClinic);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(461, 10, 242, 347);
		w_clinic.add(w_scrollWorker);

		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);

		JComboBox select_doktor = new JComboBox();
		select_doktor.setBounds(262, 242, 190, 35);
		for (int i = 0; i < bashekim.getDoktorList().size(); i++) {
			select_doktor.addItem(
					new Item(bashekim.getDoktorList().get(i).getId(), bashekim.getDoktorList().get(i).getName()));
		}

		select_doktor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});

		w_clinic.add(select_doktor);

		JButton add_worker = new JButton("Ekle");
		add_worker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doktorItem = (Item) select_doktor.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doktorItem.getKey(), selClinicID);
						if (control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for (int i = 0; i < bashekim.getClinicDoktorList(selClinicID).size(); i++) {
								workerData[0] = bashekim.getClinicDoktorList(selClinicID).get(i).getId();
								workerData[1] = bashekim.getClinicDoktorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);

							}
							table_worker.setModel(workerModel);

						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else {
					Helper.showMsg("Lütfen bir polikinlik seçiniz !");
				}
			}
		});
		add_worker.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		add_worker.setBounds(262, 287, 190, 30);
		w_clinic.add(add_worker);

		JLabel lbl_poliniklikName_1 = new JLabel("Polinik Ad\u0131");
		lbl_poliniklikName_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_poliniklikName_1.setBounds(262, 137, 100, 20);
		w_clinic.add(lbl_poliniklikName_1);

		JButton btn_addClinic_1 = new JButton("Se\u00E7");
		btn_addClinic_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < bashekim.getClinicDoktorList(selClinicID).size(); i++) {
							workerData[0] = bashekim.getClinicDoktorList(selClinicID).get(i).getId();
							workerData[1] = bashekim.getClinicDoktorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);

						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);

				} else {
					Helper.showMsg("Lütfen bir polikinlik seçiniz !");
				}
			}
		});
		btn_addClinic_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_addClinic_1.setBounds(262, 167, 190, 30);
		w_clinic.add(btn_addClinic_1);
		w_tab.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));

	}

	public void updateDoktorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doktor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoktorList().size(); i++) {
			doktorData[0] = bashekim.getDoktorList().get(i).getId();
			doktorData[1] = bashekim.getDoktorList().get(i).getName();
			doktorData[2] = bashekim.getDoktorList().get(i).getTcno();
			doktorData[3] = bashekim.getDoktorList().get(i).getPassword();
			doktorModel.addRow(doktorData);

		}
	}

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);

		}
	}
}
