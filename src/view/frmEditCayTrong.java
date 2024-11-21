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

import controller.CayTrongController;
import controller.TrangTraiController;
import model.CayTrong;
import model.TrangTrai;

public class frmEditCayTrong extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaCayTrong;
	private JTextField txtSoLuong;
	private JTable tblCayTrong;
	private DefaultTableModel txTableModel;

	private CayTrongController caytrongController;
	private JTextField txtTenTrangTrai;
	private JTextField txtTTSK;
	private JComboBox<String> cbMaTrangTrai;
	private TrangTraiController trangtraiController;
	private JComboBox<String> cbTenCayTrong;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmEditCayTrong frame = new frmEditCayTrong();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public frmEditCayTrong() {
		setBounds(100, 100, 824, 566);
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

		JLabel lblMaVatNuoi = new JLabel("Mã cây trồng :");
		lblMaVatNuoi.setBounds(34, 126, 90, 13);
		contentPane.add(lblMaVatNuoi);

		JLabel lblTenVatNuoi = new JLabel("Tên cây trồng :");
		lblTenVatNuoi.setBounds(34, 154, 90, 13);
		contentPane.add(lblTenVatNuoi);

		JLabel lblSoLuong = new JLabel("số Lượng :");
		lblSoLuong.setBounds(34, 183, 90, 13);
		contentPane.add(lblSoLuong);

		JLabel lblTinhTrangSucKhoe = new JLabel("Tình trạng sức khỏe :");
		lblTinhTrangSucKhoe.setBounds(34, 213, 106, 13);
		contentPane.add(lblTinhTrangSucKhoe);

		txtMaCayTrong = new JTextField();
		txtMaCayTrong.setBounds(148, 122, 138, 19);
		contentPane.add(txtMaCayTrong);
		txtMaCayTrong.setColumns(10);

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(148, 180, 138, 19);
		contentPane.add(txtSoLuong);

		txtSoLuong.setColumns(10);

		txtTenTrangTrai = new JTextField();
		txtTenTrangTrai.setColumns(10);
		txtTenTrangTrai.setBounds(148, 89, 138, 19);
		contentPane.add(txtTenTrangTrai);

		cbTenCayTrong = new JComboBox<>();
		cbTenCayTrong.addItem("");
		cbTenCayTrong.addItem("Lúa");
		cbTenCayTrong.addItem("Ngô");
		cbTenCayTrong.setBounds(148, 151, 138, 19);
		contentPane.add(cbTenCayTrong);

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
			caytrongController = new CayTrongController();
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
							|| txtMaCayTrong.getText().isEmpty() || cbTenCayTrong.getSelectedItem() == null
							|| txtSoLuong.getText().isEmpty() || txtTTSK.getText().isEmpty()) {
						JOptionPane.showMessageDialog(frmEditCayTrong.this, "Vui lòng nhập đầy đủ thông tin!",
								"Thông báo", JOptionPane.WARNING_MESSAGE);
						return;
					}

					// Lấy các thông tin từ giao diện
					String maTrangTrai = (String) cbMaTrangTrai.getSelectedItem();
					String tenTrangTrai = txtTenTrangTrai.getText();
					String maCayTrong = txtMaCayTrong.getText();
					String tenCayTrong = (String) cbTenCayTrong.getSelectedItem();
					TrangTrai trangTrai = trangtraiController.getTrangTraiById(maTrangTrai);
					float dienTich = trangTrai.getDienTichTrangTrai();
					float vonTrangTrai = trangTrai.getVonTrangTrai();

					// Tính giới hạn số lượng dựa trên diện tích và loại cây trồng
					int maxSoLuongByDienTich = 0;
					float chiPhiCayTrong = 0;
					if (tenCayTrong.equalsIgnoreCase("Lúa") || tenCayTrong.equalsIgnoreCase("lúa")) {
						maxSoLuongByDienTich = (int) (dienTich * 0.004);
						chiPhiCayTrong = 556.750f;
					} else if (tenCayTrong.equalsIgnoreCase("Ngô") || tenCayTrong.equalsIgnoreCase("Ngo")) {
						maxSoLuongByDienTich = (int) (dienTich * 0.002);
						chiPhiCayTrong = 1611f;
					}

					// Tính giới hạn số lượng dựa trên vốn
					int maxSoLuongByVon = (int) (vonTrangTrai / chiPhiCayTrong);

					// Lấy tổng số lượng cây trồng hiện tại trong trang trại
					int soLuongHienTai = caytrongController.getTongSoLuongCayTrongByTrangTrai(maTrangTrai);

					// Tính giới hạn cuối cùng còn lại
					int maxSoLuongConLai = Math.min(maxSoLuongByDienTich, maxSoLuongByVon) - soLuongHienTai;

					// Lấy số lượng từ giao diện và kiểm tra
					int soLuongMoi = 0;
					try {
						soLuongMoi = Integer.parseInt(txtSoLuong.getText());
						if (soLuongMoi > maxSoLuongConLai) {
							JOptionPane.showMessageDialog(frmEditCayTrong.this,
									"Tổng số lượng cây trồng vượt quá giới hạn cho phép! Giới hạn còn lại: "
											+ maxSoLuongConLai + " Kg Giống.",
									"Thông báo", JOptionPane.WARNING_MESSAGE);
							return;
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frmEditCayTrong.this, "Số lượng phải là số!", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					// Lấy các thông tin còn lại
					String tinhTrangSucKhoe = txtTTSK.getText();
					float giaChamSoc = soLuongMoi * chiPhiCayTrong;
					float sanLuongThuDuoc = tenCayTrong.equalsIgnoreCase("lua") ? soLuongMoi * 137.5f
							: soLuongMoi * 400f;

					CayTrong cayTrong = new CayTrong(maTrangTrai, tenTrangTrai, maCayTrong, tenCayTrong, soLuongMoi,
							tinhTrangSucKhoe, giaChamSoc, sanLuongThuDuoc);
					caytrongController.addCayTrong(cayTrong);
					fillData();

					// Hiển thị thông báo thành công
					JOptionPane.showMessageDialog(frmEditCayTrong.this, "Thêm cây trồng thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frmEditCayTrong.this, "Có lỗi khi thêm cây trồng: " + ex.getMessage(),
							"Thông báo", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTenTrangTrai.setText("");
				txtMaCayTrong.setText("");
				cbTenCayTrong.getSelectedItem();
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
					// Kiểm tra nếu không chọn dòng nào
					int row = tblCayTrong.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(frmEditCayTrong.this, "Vui lòng chọn cây trồng cần sửa!",
								"Thông báo", JOptionPane.WARNING_MESSAGE);
						return;
					}

					// Kiểm tra nếu có trường nào bị bỏ trống
					if (cbMaTrangTrai.getSelectedItem() == null || txtTenTrangTrai.getText().isEmpty()
							|| txtMaCayTrong.getText().isEmpty() || cbTenCayTrong.getSelectedItem() == null
							|| txtSoLuong.getText().isEmpty() || txtTTSK.getText().isEmpty()) {
						JOptionPane.showMessageDialog(frmEditCayTrong.this, "Vui lòng nhập đầy đủ thông tin!",
								"Thông báo", JOptionPane.WARNING_MESSAGE);
						return;
					}

					// Lấy thông tin từ giao diện
					String maTrangTrai = (String) cbMaTrangTrai.getSelectedItem();
					String tenTrangTrai = txtTenTrangTrai.getText();
					String maCayTrong = txtMaCayTrong.getText();
					String tenCayTrong = (String) cbTenCayTrong.getSelectedItem();
					TrangTrai trangTrai = trangtraiController.getTrangTraiById(maTrangTrai);
					float dienTich = trangTrai.getDienTichTrangTrai();
					float vonTrangTrai = trangTrai.getVonTrangTrai();

					// Tính giới hạn số lượng dựa trên diện tích và loại cây trồng
					int maxSoLuongByDienTich = 0;
					float chiPhiCayTrong = 0;

					if (tenCayTrong.equalsIgnoreCase("Lúa") || tenCayTrong.equalsIgnoreCase("lúa")) {
						maxSoLuongByDienTich = (int) (dienTich * 0.004);
						chiPhiCayTrong = 556.750f;
					} else if (tenCayTrong.equalsIgnoreCase("Ngô") || tenCayTrong.equalsIgnoreCase("Ngo")) {
						maxSoLuongByDienTich = (int) (dienTich * 0.002);
						chiPhiCayTrong = 1611f;
					}

					// Tính giới hạn số lượng dựa trên vốn
					int maxSoLuongByVon = (int) (vonTrangTrai / chiPhiCayTrong);

					// Lấy tổng số lượng cây trồng hiện tại trong trang trại
					int soLuongHienTai = caytrongController.getTongSoLuongCayTrongByTrangTrai(maTrangTrai);

					// Tính giới hạn cuối cùng còn lại
					int maxSoLuongConLai = Math.min(maxSoLuongByDienTich, maxSoLuongByVon) - soLuongHienTai;

					// Lấy số lượng từ giao diện và kiểm tra
					int soLuongMoi;
					try {
						soLuongMoi = Integer.parseInt(txtSoLuong.getText());
						if (soLuongMoi > maxSoLuongConLai) {
							JOptionPane.showMessageDialog(frmEditCayTrong.this,
									"Tổng số lượng cây trồng vượt quá giới hạn cho phép! Giới hạn còn lại: "
											+ maxSoLuongConLai + " kg giống.",
									"Thông báo", JOptionPane.WARNING_MESSAGE);
							return;
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frmEditCayTrong.this, "Số lượng phải là số!", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					// Lấy các thông tin còn lại
					String tinhTrangSucKhoe = txtTTSK.getText();
					float giaChamSoc = soLuongMoi * chiPhiCayTrong;
					float sanLuongThuDuoc = tenCayTrong.equalsIgnoreCase("lua") ? soLuongMoi * 137.5f
							: soLuongMoi * 400f;

					// Cập nhật thông tin cây trồng
					CayTrong cayTrong = new CayTrong(maTrangTrai, tenTrangTrai, maCayTrong, tenCayTrong, soLuongMoi,
							tinhTrangSucKhoe, giaChamSoc, sanLuongThuDuoc);
					caytrongController.updateCayTrong(cayTrong);
					fillData();

					// Hiển thị thông báo thành công
					JOptionPane.showMessageDialog(frmEditCayTrong.this, "Cập nhật cây trồng thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frmEditCayTrong.this, "Có lỗi khi sửa cây trồng: " + ex.getMessage(),
							"Thông báo", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnSua.setBounds(665, 37, 85, 21);
		contentPane.add(btnSua);

		JButton btnXoa = new JButton("Xóa");

		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = tblCayTrong.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn cây trồng cần xóa");
						return;
					}
					String maCayTrong = txTableModel.getValueAt(row, 2).toString();
					int confirmation = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa cây trồng này?",
							"Xác nhận xóa", JOptionPane.YES_NO_OPTION);
					if (confirmation == JOptionPane.YES_OPTION) {
						caytrongController.deleteCayTrong(maCayTrong);
						fillData();
						JOptionPane.showMessageDialog(null, "Xóa cây trồng thành công!");
					}
					txtTenTrangTrai.setText("");
					txtMaCayTrong.setText("");
					cbTenCayTrong.getSelectedItem();
					txtSoLuong.setText("");
					txtTTSK.setText("");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Lỗi xóa cây trồng: " + ex.getMessage());
				}
			}
		});

		btnXoa.setBounds(531, 88, 85, 21);
		contentPane.add(btnXoa);
		tblCayTrong = new JTable(txTableModel);
		tblCayTrong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tblCayTrong.getSelectedRow();
				if (selectedRow != -1) {
					cbMaTrangTrai.setSelectedItem(txTableModel.getValueAt(selectedRow, 0).toString());
					txtTenTrangTrai.setText(txTableModel.getValueAt(selectedRow, 1).toString());
					txtMaCayTrong.setText(txTableModel.getValueAt(selectedRow, 2).toString());
					cbTenCayTrong.setSelectedItem(txTableModel.getValueAt(selectedRow, 3).toString());
					txtSoLuong.setText(txTableModel.getValueAt(selectedRow, 4).toString());
					txtTTSK.setText(txTableModel.getValueAt(selectedRow, 5).toString());
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(tblCayTrong);
		scrollPane.setBounds(34, 252, 741, 244);
		contentPane.add(scrollPane);

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
			JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu cây trồng!", "Thông báo", // Sửa thông báo
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
