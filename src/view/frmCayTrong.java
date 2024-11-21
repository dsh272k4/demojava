package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.CayTrongController;
import controller.TrangTraiController;
import model.CayTrong;
import model.TrangTrai;

public class frmCayTrong extends JPanel {

	private static final long serialVersionUID = 1L;

	private TrangTraiController trangtraiController;
	private JTable tblCayTrong;
	private DefaultTableModel txTableModel;
	private CayTrongController caytrongController;
	private JComboBox<String> cbMaTrangTrai;

	/**
	 * Create the panel.
	 */
	public frmCayTrong() {
		setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 578, 432);
		add(tabbedPane);

		plBangThongTin = new JPanel();
		tabbedPane.addTab("Bảng thông tin", null, plBangThongTin, null);
		plBangThongTin.setLayout(null);

		lblTimKiemTheoTen = new JLabel("Chọn loại cây trồng");
		lblTimKiemTheoTen.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblTimKiemTheoTen.setBounds(10, 40, 147, 17);
		plBangThongTin.add(lblTimKiemTheoTen);

		cbMaTrangTrai = new JComboBox<>();
//		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		cbMaTrangTrai.setBounds(116, 37, 131, 22);
		plBangThongTin.add(cbMaTrangTrai);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedTrangTrai = (String) cbMaTrangTrai.getSelectedItem();
				if (selectedTrangTrai == null || selectedTrangTrai.equals("---Chọn trang trại---")) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một trang trại để tìm kiếm.", "Thông báo",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				try {
					List<CayTrong> filteredCayTrong = caytrongController.getCayTrongByTrangTrai(selectedTrangTrai);
					txTableModel.setRowCount(0);
					for (CayTrong cayTrong : filteredCayTrong) {
						Object[] row = { cayTrong.getMaTrangTrai(), cayTrong.getTenTrangTrai(),
								cayTrong.getMaCayTrong(), cayTrong.getTenCayTrong(), cayTrong.getSoLuongCayTrong(),
								cayTrong.getTinhTrangSucKhoe(), cayTrong.getGiaChamNuoi(),
								cayTrong.getSanLuongThuDuoc() };
						txTableModel.addRow(row);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + ex.getMessage(), "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnTimKiem.setBounds(270, 37, 89, 23);
		plBangThongTin.add(btnTimKiem);

		txTableModel = new DefaultTableModel();
		initTable();
		try {
			caytrongController = new CayTrongController();
			trangtraiController = new TrangTraiController();
			fillData();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		}
		tblCayTrong = new JTable(txTableModel);
		JScrollPane scrollPane2 = new JScrollPane(tblCayTrong);
		scrollPane2.setBounds(0, 157, 573, 247);
		tblCayTrong.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		plBangThongTin.add(scrollPane2);

		JButton btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fillData();
					cbMaTrangTrai.setSelectedIndex(0);
					JOptionPane.showMessageDialog(null, "Dữ liệu đã được làm mới!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Lỗi khi làm mới dữ liệu: " + ex.getMessage(), "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnLamMoi.setBounds(370, 37, 89, 23);
		plBangThongTin.add(btnLamMoi);

		JButton btnEdit = new JButton("Chỉnh Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEditCayTrong frmEdit = new frmEditCayTrong();
				frmEdit.setVisible(true);
			}
		});
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnEdit.setBounds(468, 37, 89, 23);
		plBangThongTin.add(btnEdit);

	}

	private JTable tableBangThongTin;
	private JTabbedPane tabbedPane;
	private JPanel plBangThongTin;
	private JLabel lblTimKiemTheoTen;
	private JButton btnTimKiem;

	public JTable getTableBangThongTin() {
		return tableBangThongTin;
	}

	public void setTableBangThongTin(JTable tableBangThongTin) {
		this.tableBangThongTin = tableBangThongTin;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public JPanel getPlBangThongTin() {
		return plBangThongTin;
	}

	public void setPlBangThongTin(JPanel plBangThongTin) {
		this.plBangThongTin = plBangThongTin;
	}

	public JComboBox<String> getComboBox() {
		return cbMaTrangTrai;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.cbMaTrangTrai = comboBox;
	}

	public JLabel getLblTimKiemTheoTen() {
		return lblTimKiemTheoTen;
	}

	public void setLblTimKiemTheoTen(JLabel lblTimKiemTheoTen) {
		this.lblTimKiemTheoTen = lblTimKiemTheoTen;
	}

	public JButton getBtnTimKiem() {
		return btnTimKiem;
	}

	public void setBtnTimKiem(JButton btnTimKiem) {
		this.btnTimKiem = btnTimKiem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void initTable() {
		txTableModel.addColumn("Mã trang trại");
		txTableModel.addColumn("Tên trang trại");
		txTableModel.addColumn("Mã Cây Trồng");
		txTableModel.addColumn("Tên Cây Trồng");
		txTableModel.addColumn("KG giống");
		txTableModel.addColumn("Tình Trạng");
		txTableModel.addColumn("Giá chăm sóc");
		txTableModel.addColumn("Sản Lượng Ước Tính");
	}

	public void fillData() {
		try {
			List<CayTrong> caytrongs = caytrongController.getAllCayTrong(); // Gọi hàm getAllCayTrong
			txTableModel.setRowCount(0);
			cbMaTrangTrai.removeAllItems();
			cbMaTrangTrai.addItem("---Chọn trang trại---");
			List<TrangTrai> trangTrais = trangtraiController.getAllTT();
			for (TrangTrai trangTrai : trangTrais) {
				cbMaTrangTrai.addItem(trangTrai.getMaTrangTrai());
			}

			for (CayTrong cayTrong : caytrongs) {
				String[] row = { cayTrong.getMaTrangTrai(), cayTrong.getTenTrangTrai(), cayTrong.getMaCayTrong(),
						cayTrong.getTenCayTrong(), String.valueOf(cayTrong.getSoLuongCayTrong()),
						cayTrong.getTinhTrangSucKhoe(), String.valueOf(cayTrong.getGiaChamNuoi()),
						String.valueOf(cayTrong.getSanLuongThuDuoc()) };
				txTableModel.addRow(row);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu cây trồng!" + e.getMessage(), "Thông báo",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
