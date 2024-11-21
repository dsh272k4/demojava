package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.TrangTraiController;
import model.TrangTrai;

public class frmTrangTrai extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable tblTrangTrai;
	private JTable tblTrangTrai2;
	private DefaultTableModel txTableModel;
	private DefaultTableModel txTableModel2;
	private TrangTraiController trangtraiController;
	private boolean enabledText = false;

	/**
	 * Create the panel.
	 */
	public frmTrangTrai() {
		setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 578, 431);
		add(panel);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 578, 432);
		panel.add(tabbedPane);

		plBangThongTin = new JPanel();
		tabbedPane.addTab("Bảng thông tin", null, plBangThongTin, null);
		plBangThongTin.setLayout(null);

		txTableModel = new DefaultTableModel();
		initTable();

		try {
			trangtraiController = new TrangTraiController();
			System.out.println("ket noi thanh cong Trang Trai");
			fillData();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		}
		tblTrangTrai = new JTable(txTableModel);
		JScrollPane scrollPane = new JScrollPane(tblTrangTrai);
		scrollPane.setBounds(0, 187, 574, 218);
		tblTrangTrai.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		plBangThongTin.add(scrollPane);

		JLabel lblTimKiem = new JLabel("Nhập mã trang trại");
		lblTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblTimKiem.setBounds(55, 52, 110, 14);
		plBangThongTin.add(lblTimKiem);

		textTimKiem = new JTextField();
		textTimKiem.setBounds(163, 49, 118, 20);
		plBangThongTin.add(textTimKiem);
		textTimKiem.setColumns(10);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnTimKiem.setBounds(303, 48, 89, 23);
		plBangThongTin.add(btnTimKiem);

		JButton btnLamMoi1 = new JButton("Làm Mới");
		btnLamMoi1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillData();
			}
		});
		btnLamMoi1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnLamMoi1.setBounds(410, 49, 89, 23);
		plBangThongTin.add(btnLamMoi1);

		plThongTinChiTiet = new JPanel();
		tabbedPane.addTab("Chỉnh sửa", null, plThongTinChiTiet, null);
		plThongTinChiTiet.setLayout(null);

		lblMaTrangTrai = new JLabel("Mã trang trại");
		lblMaTrangTrai.setBounds(44, 22, 84, 14);
		plThongTinChiTiet.add(lblMaTrangTrai);

		txtMaTrangTrai = new JTextField();
		txtMaTrangTrai.setBounds(138, 19, 96, 20);
		plThongTinChiTiet.add(txtMaTrangTrai);
		txtMaTrangTrai.setColumns(10);

		plTuyChon = new JPanel();
		plTuyChon.setBackground(new Color(251, 251, 251));
		plTuyChon.setBounds(10, 154, 554, 33);
		plThongTinChiTiet.add(plTuyChon);

		txTableModel2 = new DefaultTableModel();
		initTable2();
		try {
			trangtraiController = new TrangTraiController();
			fillData2();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		}
		tblTrangTrai2 = new JTable(txTableModel2); // Sử dụng tblTrangTrai2 cho bảng thứ hai
		tblTrangTrai2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblTrangTrai2.getSelectedRow();
				if (row != -1) {
					txtMaTrangTrai.setText(txTableModel2.getValueAt(row, 0).toString());
					txtTenTrangTrai.setText(txTableModel2.getValueAt(row, 1).toString());
					txtDienTich.setText(txTableModel2.getValueAt(row, 2).toString());
					txtVon.setText(txTableModel2.getValueAt(row, 3).toString());

				}
			}
		});
		JScrollPane scrollPane2 = new JScrollPane(tblTrangTrai2);
		scrollPane2.setBounds(0, 187, 574, 206);
		tblTrangTrai2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		plThongTinChiTiet.add(scrollPane2);

		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Check if any of the input fields are empty
					if (txtMaTrangTrai.getText().isEmpty() || txtTenTrangTrai.getText().isEmpty()
							|| txtDienTich.getText().isEmpty() || txtVon.getText().isEmpty()) {

						JOptionPane.showMessageDialog(frmTrangTrai.this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					String maTrangTrai = txtMaTrangTrai.getText();
					String tenTrangTrai = txtTenTrangTrai.getText();
					float dienTich = 0, von = 0;

					try {
						dienTich = Float.parseFloat(txtDienTich.getText());
						von = Float.parseFloat(txtVon.getText());
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frmTrangTrai.this, "Diện tích và vốn phải là số!", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					float doanhThu = 0;
					TrangTrai trangTrai = new TrangTrai(maTrangTrai, tenTrangTrai, dienTich, von, doanhThu);
					trangtraiController.addTrangTrai(trangTrai);
					fillData2();
					JOptionPane.showMessageDialog(frmTrangTrai.this, "Thêm trang trại thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frmTrangTrai.this, "Có lỗi khi thêm trang trại: " + ex.getMessage(),
							"Thông báo", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		plTuyChon.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Lấy dòng được chọn từ bảng
					int row = tblTrangTrai2.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn trang trại cần sửa", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					String maTrangTrai = txTableModel.getValueAt(row, 0).toString();
					String tenTrangTrai = txtTenTrangTrai.getText().trim();
					if (tenTrangTrai.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tên trang trại không được để trống", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					float dienTich = 0;
					float von = 0;
					try {
						dienTich = Float.parseFloat(txtDienTich.getText().trim());
						von = Float.parseFloat(txtVon.getText().trim());

						if (dienTich <= 0 || von <= 0) {
							JOptionPane.showMessageDialog(null, "Diện tích và vốn phải là số dương", "Thông báo",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Diện tích và vốn phải là số hợp lệ", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					float doanhThu = trangtraiController.calculateDoanhThu(maTrangTrai);
					TrangTrai trangTrai = new TrangTrai(maTrangTrai, tenTrangTrai, dienTich, von, doanhThu);
					trangtraiController.updateTrangTrai(trangTrai);
					fillData2();
					JOptionPane.showMessageDialog(null, "Cập nhật trang trại thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Lỗi cập nhật trang trại: " + ex.getMessage(), "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		plTuyChon.add(btnSua);

		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = tblTrangTrai2.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn trang trại cần xóa");
						return;
					}
					String maTrangTrai = txTableModel2.getValueAt(row, 0).toString();
					trangtraiController.deleteTrangTrai(maTrangTrai);
					JOptionPane.showMessageDialog(null, "Xóa trang trại thành công!");
					fillData2();
					enabledText = false;
					setEnabledTextField();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Lỗi xóa trang trại: " + ex.getMessage());
				}
			}
		});
		plTuyChon.add(btnXoa);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtMaTrangTrai.setText("");
					txtTenTrangTrai.setText("");
					txtDienTich.setText("");
					txtVon.setText("");
					for (TrangTrai trangTrai : trangtraiController.getAllTT()) {
						trangtraiController.updateDoanhThu(trangTrai.getMaTrangTrai());
					}

					fillData();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(frmTrangTrai.this, "Có lỗi khi làm mới: " + ex.getMessage(),
							"Thông báo", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		plTuyChon.add(btnLamMoi);

		txtTenTrangTrai = new JTextField();
		txtTenTrangTrai.setBounds(138, 50, 96, 20);
		plThongTinChiTiet.add(txtTenTrangTrai);
		txtTenTrangTrai.setColumns(10);

		txtDienTich = new JTextField();
		txtDienTich.setBounds(138, 81, 96, 20);
		plThongTinChiTiet.add(txtDienTich);
		txtDienTich.setColumns(10);

		txtVon = new JTextField();
		txtVon.setBounds(138, 112, 96, 20);
		plThongTinChiTiet.add(txtVon);
		txtVon.setColumns(10);

		lblTenTrangTrai = new JLabel("Tên trang trại");
		lblTenTrangTrai.setBounds(44, 53, 49, 14);
		plThongTinChiTiet.add(lblTenTrangTrai);

		lblDienTich = new JLabel("Diện tích");
		lblDienTich.setBounds(44, 84, 49, 14);
		plThongTinChiTiet.add(lblDienTich);

		lblVon = new JLabel("Vốn");
		lblVon.setBounds(44, 114, 49, 14);
		plThongTinChiTiet.add(lblVon);

//		QuanLyTrangTraiController controller = new QuanLyTrangTraiController(this);

	}

	public JButton getBtnThem() {
		return btnThem;
	}

	public void setBtnThem(JButton btnThem) {
		this.btnThem = btnThem;
	}

	public JButton getBtnSua() {
		return btnSua;
	}

	public void setBtnSua(JButton btnSua) {
		this.btnSua = btnSua;
	}

	public JButton getBtnXoa() {
		return btnXoa;
	}

	public void setBtnXoa(JButton btnXoa) {
		this.btnXoa = btnXoa;
	}

	public JButton getBtnLamMoi() {
		return btnLamMoi;
	}

	public void setBtnLamMoi(JButton btnLamMoi) {
		this.btnLamMoi = btnLamMoi;
	}

	public JButton getBtnTimKiem() {
		return btnTimKiem;
	}

	public void setBtnTimKiem(JButton btnTimKiem) {
		this.btnTimKiem = btnTimKiem;
	}

	public JPanel getPlTuyChon() {
		return plTuyChon;
	}

	public void setPlTuyChon(JPanel plTuyChon) {
		this.plTuyChon = plTuyChon;
	}

	public JPanel getPlBangThongTin() {
		return plBangThongTin;
	}

	public void setPlBangThongTin(JPanel plBangThongTin) {
		this.plBangThongTin = plBangThongTin;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JPanel getPlThongTinChiTiet() {
		return plThongTinChiTiet;
	}

	public void setPlThongTinChiTiet(JPanel plThongTinChiTiet) {
		this.plThongTinChiTiet = plThongTinChiTiet;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JScrollPane getScrollPane_1() {
		return scrollPane_1;
	}

	public void setScrollPane_1(JScrollPane scrollPane_1) {
		this.scrollPane_1 = scrollPane_1;
	}

	public JTextField getTextVon() {
		return txtVon;
	}

	public void setTextVon(JTextField textVon) {
		this.txtVon = textVon;
	}

	public JTextField getTextDienTich() {
		return txtDienTich;
	}

	public void setTextDienTich(JTextField textDienTich) {
		this.txtDienTich = textDienTich;
	}

	public JTextField getTextTenTrangTrai() {
		return txtTenTrangTrai;
	}

	public void setTextTenTrangTrai(JTextField textTenTrangTrai) {
		this.txtTenTrangTrai = textTenTrangTrai;
	}

	public JTextField getTextMaTrangTrai() {
		return txtMaTrangTrai;
	}

	public void setTextMaTrangTrai(JTextField textMaTrangTrai) {
		this.txtMaTrangTrai = textMaTrangTrai;
	}

	public JTextField getTextTimKiem() {
		return textTimKiem;
	}

	public void setTextTimKiem(JTextField textTimKiem) {
		this.textTimKiem = textTimKiem;
	}

	public JTable getTableThongTinChiTiet() {
		return tableThongTinChiTiet;
	}

	public void setTableThongTinChiTiet(JTable tableThongTinChiTiet) {
		this.tableThongTinChiTiet = tableThongTinChiTiet;
	}

	public JTable getTableBangThongTin() {
		return tableBangThongTin;
	}

	public void setTableBangThongTin(JTable tableBangThongTin) {
		this.tableBangThongTin = tableBangThongTin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private JLabel lblVon, lblDienTich, lblTenTrangTrai, lblMaTrangTrai;
	private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnTimKiem;
	private JPanel plTuyChon, plBangThongTin, panel, plThongTinChiTiet;
	private JScrollPane scrollPane, scrollPane_1;
	private JTextField txtVon, txtDienTich, txtTenTrangTrai, txtMaTrangTrai, textTimKiem;
	private JTable tableThongTinChiTiet, tableBangThongTin;

	public void initTable() {
		txTableModel.addColumn("Mã trang trại");
		txTableModel.addColumn("Tên trang trại");
		txTableModel.addColumn("Diện tích");
		txTableModel.addColumn("Vốn");
		txTableModel.addColumn("Doanh thu");
	}

	public DefaultTableModel getTxTableModel() {
		return txTableModel;
	}

	public void setTxTableModel(DefaultTableModel txTableModel) {
		this.txTableModel = txTableModel;
	}

	public DefaultTableModel getTxTableModel2() {
		return txTableModel2;
	}

	public void setTxTableModel2(DefaultTableModel txTableModel2) {
		this.txTableModel2 = txTableModel2;
	}

	public TrangTraiController getTrangtraiController() {
		return trangtraiController;
	}

	public void setTrangtraiController(TrangTraiController trangtraiController) {
		this.trangtraiController = trangtraiController;
	}

	public void fillData() {
		try {
			List<TrangTrai> trangTrais = trangtraiController.getAllTT();
			txTableModel.setRowCount(0);
			for (TrangTrai trangTrai : trangTrais) {

				String[] row = { trangTrai.getMaTrangTrai(), trangTrai.getTenTrangTrai(),
						String.valueOf(trangTrai.getDienTichTrangTrai()), String.valueOf(trangTrai.getVonTrangTrai()),
						String.valueOf(trangTrai.getDoanhThuTrangTrai()) };
				txTableModel.addRow(row);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu trang trại!", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void initTable2() {
		txTableModel2.addColumn("Mã trang trại");
		txTableModel2.addColumn("Tên trang trại");
		txTableModel2.addColumn("Diện tích");
		txTableModel2.addColumn("Vốn");
		txTableModel2.addColumn("Doanh thu");
	}

	public void setEnabledTextField() {
		this.getTextMaTrangTrai().setEnabled(enabledText);
		this.getTextTenTrangTrai().setEnabled(enabledText);
		this.getTextDienTich().setEnabled(enabledText);
		this.getTextVon().setEnabled(enabledText);

	}

	public void fillData2() {
		try {
			List<TrangTrai> trangTrais = trangtraiController.getAllTT();
			txTableModel2.setRowCount(0);
			for (TrangTrai trangTrai : trangTrais) {

				String[] row = { trangTrai.getMaTrangTrai(), trangTrai.getTenTrangTrai(),
						String.valueOf(trangTrai.getDienTichTrangTrai()), String.valueOf(trangTrai.getVonTrangTrai()),
						String.valueOf(trangTrai.getDoanhThuTrangTrai()) };
				txTableModel2.addRow(row);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu trang trại!", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
