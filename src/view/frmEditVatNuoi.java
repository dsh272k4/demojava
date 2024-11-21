package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.TrangTraiController;
import controller.VatNuoiController;
import model.TrangTrai;
import model.VatNuoi;

public class frmEditVatNuoi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaVatNuoi;
	private JTextField txtSoLuong;
	private JTable tblVatNuoi;
	private DefaultTableModel txTableModel;

	private VatNuoiController vatnuoiController;
	private JTextField txtTenTrangTrai;
	private JTextField txtTTSK;
	private JComboBox<String> cbMaTrangTrai;
	private TrangTraiController trangtraiController;
	private JComboBox<String> cbTenVatNuoi;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmEditVatNuoi frame = new frmEditVatNuoi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public frmEditVatNuoi() {
		setBounds(100, 100, 791, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTieuDe = new JLabel("Quản lý danh mục trang trại");
		lblTieuDe.setBounds(150, 10, 180, 20);
		contentPane.add(lblTieuDe);

		JLabel lblMaTrangTrai = new JLabel("Mã trang trại:");
		lblMaTrangTrai.setBounds(34, 59, 90, 13);
		contentPane.add(lblMaTrangTrai);

		JLabel lblTenTrangTrai = new JLabel("Tên trang trại:");
		lblTenTrangTrai.setBounds(34, 92, 90, 13);
		contentPane.add(lblTenTrangTrai);

		JLabel lblMaVatNuoi = new JLabel("Mã vật nuôi :");
		lblMaVatNuoi.setBounds(34, 126, 90, 13);
		contentPane.add(lblMaVatNuoi);

		JLabel lblTenVatNuoi = new JLabel("Tên vật nuôi :");
		lblTenVatNuoi.setBounds(34, 154, 90, 13);
		contentPane.add(lblTenVatNuoi);

		JLabel lblSoLuong = new JLabel("số Lượng :");
		lblSoLuong.setBounds(34, 183, 90, 13);
		contentPane.add(lblSoLuong);

		JLabel lblTinhTrangSucKhoe = new JLabel("Tình trạng sức khỏe :");
		lblTinhTrangSucKhoe.setBounds(34, 213, 106, 13);
		contentPane.add(lblTinhTrangSucKhoe);

		txtMaVatNuoi = new JTextField();
		txtMaVatNuoi.setBounds(148, 122, 138, 19);
		contentPane.add(txtMaVatNuoi);
		txtMaVatNuoi.setColumns(10);

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(148, 180, 138, 19);
		contentPane.add(txtSoLuong);

		txtSoLuong.setColumns(10);

		txtTenTrangTrai = new JTextField();
		txtTenTrangTrai.setColumns(10);
		txtTenTrangTrai.setBounds(148, 89, 138, 19);
		contentPane.add(txtTenTrangTrai);

		cbTenVatNuoi = new JComboBox<>();
		cbTenVatNuoi.addItem("");
		cbTenVatNuoi.addItem("Gà");
		cbTenVatNuoi.addItem("Lợn");
		cbTenVatNuoi.setBounds(148, 151, 138, 19);
		contentPane.add(cbTenVatNuoi);

		txtTTSK = new JTextField();
		txtTTSK.setColumns(10);
		txtTTSK.setBounds(148, 210, 138, 19);
		contentPane.add(txtTTSK);
		cbMaTrangTrai = new JComboBox<>();
		cbMaTrangTrai.setBounds(148, 56, 138, 19);
		contentPane.add(cbMaTrangTrai);

		txTableModel = new DefaultTableModel();
		initTable();
		try {
			vatnuoiController = new VatNuoiController();
			trangtraiController = new TrangTraiController();
			fillData();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		}

		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(531, 36, 89, 23);
		contentPane.add(btnThem);

		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Kiểm tra nếu có trường nào bị bỏ trống
					if (cbMaTrangTrai.getSelectedItem() == null || txtTenTrangTrai.getText().isEmpty()
							|| txtMaVatNuoi.getText().isEmpty() || cbTenVatNuoi.getSelectedItem() == null
							|| txtSoLuong.getText().isEmpty() || txtTTSK.getText().isEmpty()) {
						JOptionPane.showMessageDialog(frmEditVatNuoi.this, "Vui lòng nhập đầy đủ thông tin!",
								"Thông báo", JOptionPane.WARNING_MESSAGE);
						return;
					}

					// Lấy các thông tin từ giao diện
					String maTrangTrai = (String) cbMaTrangTrai.getSelectedItem();
					String tenTrangTrai = txtTenTrangTrai.getText();
					String maVatNuoi = txtMaVatNuoi.getText();
					String tenVatNuoi = (String) cbTenVatNuoi.getSelectedItem();
					TrangTrai trangTrai = trangtraiController.getTrangTraiById(maTrangTrai);
					float dienTich = trangTrai.getDienTichTrangTrai();
					float vonTrangTrai = trangTrai.getVonTrangTrai();

					// Tính giới hạn số lượng dựa trên diện tích
					int maxSoLuongByDienTich = 0;
					float chiPhiMoiCon = 0;

					if (tenVatNuoi.equalsIgnoreCase("ga") || tenVatNuoi.equalsIgnoreCase("gà")) {
						maxSoLuongByDienTich = (int) (dienTich * 10);
						chiPhiMoiCon = 90f; // Chi phí chăn nuôi mỗi con gà
					} else if (tenVatNuoi.equalsIgnoreCase("lon") || tenVatNuoi.equalsIgnoreCase("lợn")) {
						maxSoLuongByDienTich = (int) (dienTich * 3);
						chiPhiMoiCon = 3500f; // Chi phí chăn nuôi mỗi con lợn
					}

					// Tính giới hạn số lượng dựa trên vốn
					int maxSoLuongByVon = (int) (vonTrangTrai / chiPhiMoiCon);

					// Lấy tổng số lượng vật nuôi hiện tại trong trang trại
					int soLuongHienTai = vatnuoiController.getTongSoLuongVatNuoiByTrangTrai(maTrangTrai);

					// Tính giới hạn cuối cùng còn lại
					int maxSoLuongConLai = Math.min(maxSoLuongByDienTich, maxSoLuongByVon) - soLuongHienTai;

					// Lấy số lượng từ giao diện và kiểm tra
					int soLuongMoi = 0;
					try {
						soLuongMoi = Integer.parseInt(txtSoLuong.getText());

						if (soLuongMoi > maxSoLuongConLai) {
							JOptionPane
									.showMessageDialog(frmEditVatNuoi.this,
											"Tổng số lượng vật nuôi vượt quá giới hạn cho phép! Giới hạn còn lại: "
													+ maxSoLuongConLai + " con.",
											"Thông báo", JOptionPane.WARNING_MESSAGE);
							return;
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frmEditVatNuoi.this, "Số lượng phải là số!", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					// Lấy các thông tin còn lại
					String tinhTrangSucKhoe = txtTTSK.getText();
					float giaChamSoc = soLuongMoi * chiPhiMoiCon;
					float sanLuongThuDuoc = tenVatNuoi.equalsIgnoreCase("ga") ? soLuongMoi * 1.7f : soLuongMoi * 70f;

					// Tạo đối tượng vật nuôi và thêm vào cơ sở dữ liệu
					VatNuoi vatNuoi = new VatNuoi(maTrangTrai, tenTrangTrai, maVatNuoi, tenVatNuoi, soLuongMoi,
							tinhTrangSucKhoe, giaChamSoc, sanLuongThuDuoc);
					vatnuoiController.addVatNuoi(vatNuoi);
					fillData();

					// Hiển thị thông báo thành công
					JOptionPane.showMessageDialog(frmEditVatNuoi.this, "Thêm vật nuôi thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frmEditVatNuoi.this, "Có lỗi khi thêm vật nuôi: " + ex.getMessage(),
							"Thông báo", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTenTrangTrai.setText("");
				txtMaVatNuoi.setText("");
				cbTenVatNuoi.getSelectedItem();
				txtSoLuong.setText("");
				txtTTSK.setText("");
				fillData();
			}
		});
		btnLamMoi.setBounds(665, 88, 85, 21);
		contentPane.add(btnLamMoi);

		JButton btnSua = new JButton("Sửa");

		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = tblVatNuoi.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn vật nuôi cần sửa");
						return;
					}
					if (cbMaTrangTrai.getSelectedItem() == null || txtTenTrangTrai.getText().isEmpty()
							|| txtMaVatNuoi.getText().isEmpty() || cbTenVatNuoi.getSelectedItem() == null
							|| txtSoLuong.getText().isEmpty() || txtTTSK.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					String maVatNuoi = txTableModel.getValueAt(row, 2).toString();
					String maTrangTrai = (String) cbMaTrangTrai.getSelectedItem();
					String tenTrangTrai = txtTenTrangTrai.getText();
					String tenVatNuoi = (String) cbTenVatNuoi.getSelectedItem();
					int soLuong;
					try {
						soLuong = Integer.parseInt(txtSoLuong.getText());
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Số lượng phải là số!", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					TrangTrai trangTrai = trangtraiController.getTrangTraiById(maTrangTrai);
					float dienTich = trangTrai.getDienTichTrangTrai();
					int maxSoLuong;
					if (tenVatNuoi.equalsIgnoreCase("ga") || tenVatNuoi.equalsIgnoreCase("gà")) {
						maxSoLuong = (int) (dienTich * 10);
					} else if (tenVatNuoi.equalsIgnoreCase("lon") || tenVatNuoi.equalsIgnoreCase("lợn")) {
						maxSoLuong = (int) (dienTich * 6);
					} else {
						JOptionPane.showMessageDialog(null, "Loại vật nuôi không hợp lệ!", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					if (soLuong > maxSoLuong) {
						JOptionPane.showMessageDialog(null,
								"Số lượng vật nuôi vượt quá giới hạn cho phép (" + maxSoLuong + " con).", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					String tinhTrangSucKhoe = txtTTSK.getText();
					Float giaChamSoc = 0f, sanLuongThuDuoc = 0f;

					if (tenVatNuoi.equalsIgnoreCase("ga") || tenVatNuoi.equalsIgnoreCase("gà")) {
						giaChamSoc = soLuong * 90f;
						sanLuongThuDuoc = soLuong * 1.7f;
					} else if (tenVatNuoi.equalsIgnoreCase("lon") || tenVatNuoi.equalsIgnoreCase("lợn")) {
						giaChamSoc = soLuong * 3500f;
						sanLuongThuDuoc = soLuong * 70f;
					}
					VatNuoi vatNuoi = new VatNuoi(maTrangTrai, tenTrangTrai, maVatNuoi, tenVatNuoi, soLuong,
							tinhTrangSucKhoe, giaChamSoc, sanLuongThuDuoc);
					vatnuoiController.updateVatNuoi(vatNuoi);
					fillData();
					JOptionPane.showMessageDialog(null, "Cập nhật vật nuôi thành công!");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Lỗi cập nhật vật nuôi: " + ex.getMessage());
				}
			}
		});

		btnSua.setBounds(665, 37, 85, 21);
		contentPane.add(btnSua);

		JButton btnXoa = new JButton("Xóa");

		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = tblVatNuoi.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn vật nuôi cần xóa");
						return;
					}
					String maVatNuoi = txTableModel.getValueAt(row, 2).toString();
					int confirmation = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa vật nuôi này?",
							"Xác nhận xóa", JOptionPane.YES_NO_OPTION);
					if (confirmation == JOptionPane.YES_OPTION) {
						vatnuoiController.deleteVatNuoi(maVatNuoi);
						fillData();
						JOptionPane.showMessageDialog(null, "Xóa vật nuôi thành công!");
					}
					txtTenTrangTrai.setText("");
					txtMaVatNuoi.setText("");
					cbTenVatNuoi.getSelectedItem();
					txtSoLuong.setText("");
					txtTTSK.setText("");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Lỗi xóa vật nuôi: " + ex.getMessage());
				}
			}
		});

		btnXoa.setBounds(531, 88, 85, 21);
		contentPane.add(btnXoa);
		tblVatNuoi = new JTable(txTableModel);
		tblVatNuoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tblVatNuoi.getSelectedRow();
				if (selectedRow != -1) {
					cbMaTrangTrai.setSelectedItem(txTableModel.getValueAt(selectedRow, 0).toString());
					txtTenTrangTrai.setText(txTableModel.getValueAt(selectedRow, 1).toString());
					txtMaVatNuoi.setText(txTableModel.getValueAt(selectedRow, 2).toString());
					cbTenVatNuoi.setSelectedItem(txTableModel.getValueAt(selectedRow, 3).toString());
					txtSoLuong.setText(txTableModel.getValueAt(selectedRow, 4).toString());
					txtTTSK.setText(txTableModel.getValueAt(selectedRow, 5).toString());
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(tblVatNuoi);
		scrollPane.setBounds(34, 252, 741, 244);
		contentPane.add(scrollPane);
//		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(
//				new Component[] { lblTieuDe, lblMaTrangTrai, lblTenTrangTrai, lblMaVatNuoi, lblTenVatNuoi, lblSoLuong,
//						lblTinhTrangSucKhoe, cbMaTrangTrai, txtTenTrangTrai, txtMaVatNuoi, cbTenVatNuoi, txtSoLuong,
//						txtTTSK, btnThem, btnSua, btnXoa, btnLamMoi, tblVatNuoi, scrollPane }));
//		setFocusTraversalPolicy(new FocusTraversalOnArray(
//				new Component[] { lblTieuDe, lblMaTrangTrai, lblTenTrangTrai, lblMaVatNuoi, lblTenVatNuoi, lblSoLuong,
//						lblTinhTrangSucKhoe, cbMaTrangTrai, txtTenTrangTrai, txtMaVatNuoi, cbTenVatNuoi, txtSoLuong,
//						txtTTSK, btnThem, btnSua, btnXoa, btnLamMoi, tblVatNuoi, contentPane, scrollPane }));
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
			List<VatNuoi> vatnuois = vatnuoiController.getAllVatNuoi();
			txTableModel.setRowCount(0);
			cbMaTrangTrai.removeAllItems();
			List<TrangTrai> trangTrais = trangtraiController.getAllTT();
			for (TrangTrai trangTrai : trangTrais) {
				cbMaTrangTrai.addItem(trangTrai.getMaTrangTrai());
			}

			for (VatNuoi vatNuoi : vatnuois) {
				String[] row = { vatNuoi.getMaTrangTrai(), vatNuoi.getTenTrangTrai(), vatNuoi.getMaVatNuoi(),
						vatNuoi.getTenVatNuoi(), String.valueOf(vatNuoi.getSoLuongVatNuoi()),
						vatNuoi.getTinhTrangSucKhoe(), String.valueOf(vatNuoi.getGiaChamNuoi()),
						String.valueOf(vatNuoi.getSanLuongThuDuoc()) };
				txTableModel.addRow(row);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu trang trại!", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
