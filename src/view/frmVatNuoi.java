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

import controller.TrangTraiController;
import controller.VatNuoiController;
import model.TrangTrai;
import model.VatNuoi;

public class frmVatNuoi extends JPanel {

	private static final long serialVersionUID = 1L;

	private TrangTraiController trangtraiController;
	private JTable tblVatNuoi;
	private DefaultTableModel txTableModel;
	private VatNuoiController vatnuoiController;
	private JComboBox<String> cbMaTrangTrai;

	/**
	 * Create the panel.
	 */
	public frmVatNuoi() {
		setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 578, 432);
		add(tabbedPane);

		plBangThongTin = new JPanel();
		tabbedPane.addTab("Bảng thông tin", null, plBangThongTin, null);
		plBangThongTin.setLayout(null);

		lblTimKiemTheoTen = new JLabel("Chọn loại vật nuôi");
		lblTimKiemTheoTen.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblTimKiemTheoTen.setBounds(10, 40, 147, 17);
		plBangThongTin.add(lblTimKiemTheoTen);

		cbMaTrangTrai = new JComboBox<>();
		cbMaTrangTrai.setFont(new Font("Times New Roman", Font.PLAIN, 11));
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
					List<VatNuoi> filteredVatNuoi = vatnuoiController.getVatNuoiByTrangTrai(selectedTrangTrai);
					txTableModel.setRowCount(0); // Xóa dữ liệu cũ
					for (VatNuoi vatNuoi : filteredVatNuoi) {
						Object[] row = { vatNuoi.getMaTrangTrai(), vatNuoi.getTenTrangTrai(), vatNuoi.getMaVatNuoi(),
								vatNuoi.getTenVatNuoi(), vatNuoi.getSoLuongVatNuoi(), vatNuoi.getTinhTrangSucKhoe(),
								vatNuoi.getGiaChamNuoi(), vatNuoi.getSanLuongThuDuoc() };
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
			vatnuoiController = new VatNuoiController();
			trangtraiController = new TrangTraiController();

			fillData();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		}
		tblVatNuoi = new JTable(txTableModel);
		JScrollPane scrollPane2 = new JScrollPane(tblVatNuoi);
		scrollPane2.setBounds(0, 157, 573, 247);
		tblVatNuoi.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		plBangThongTin.add(scrollPane2);

		JButton btnEdit = new JButton("Chỉnh Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEditVatNuoi frmEdit = new frmEditVatNuoi();
				frmEdit.setVisible(true);
			}
		});
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnEdit.setBounds(468, 37, 89, 23);
		plBangThongTin.add(btnEdit);

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

	}

	private JTable tableBangThongTin;
	private JTabbedPane tabbedPane;
	private JPanel plBangThongTin;
	private JScrollPane scrollPaneBangThongTin;

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

	public JScrollPane getScrollPaneBangThongTin() {
		return scrollPaneBangThongTin;
	}

	public void setScrollPaneBangThongTin(JScrollPane scrollPaneBangThongTin) {
		this.scrollPaneBangThongTin = scrollPaneBangThongTin;
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
		txTableModel.addColumn("Mã Vật Nuôi");
		txTableModel.addColumn("Tên Vật Nuôi");
		txTableModel.addColumn("Số Lượng");
		txTableModel.addColumn("Tình Trạng");
		txTableModel.addColumn("Giá chăm sóc");
		txTableModel.addColumn("Sản Lượng Ước Tính");
	}

	public void fillData() {
		try {
			// Lấy dữ liệu vật nuôi
			List<VatNuoi> vatnuois = vatnuoiController.getAllVatNuoi();
			txTableModel.setRowCount(0);
			cbMaTrangTrai.removeAllItems();
			cbMaTrangTrai.addItem("---Chọn trang trại---");
			List<TrangTrai> trangTrais = trangtraiController.getAllTT();
			for (TrangTrai trangTrai : trangTrais) {
				cbMaTrangTrai.addItem(trangTrai.getMaTrangTrai());
			}
			for (VatNuoi vatNuoi : vatnuois) {
				Object[] row = { vatNuoi.getMaTrangTrai(), vatNuoi.getTenTrangTrai(), vatNuoi.getMaVatNuoi(),
						vatNuoi.getTenVatNuoi(), vatNuoi.getSoLuongVatNuoi(), vatNuoi.getTinhTrangSucKhoe(),
						vatNuoi.getGiaChamNuoi(), vatNuoi.getSanLuongThuDuoc() };
				txTableModel.addRow(row);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Thông báo",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
