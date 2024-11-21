package view;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.dbConnect;

public class frmProfile extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public frmProfile() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panel = new JPanel();
		add(panel);
		panel.setLayout(null);

		lblHoTen = new JLabel("Họ tên");
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblHoTen.setBounds(154, 150, 49, 14);
		panel.add(lblHoTen);

		textHoTen = new JTextField();
		textHoTen.setBounds(247, 147, 135, 20);
		panel.add(textHoTen);
		textHoTen.setColumns(10);

		lblQueQuan = new JLabel("Quê quán");
		lblQueQuan.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblQueQuan.setBounds(154, 262, 61, 14);
		panel.add(lblQueQuan);

		textQueQuan = new JTextField();
		textQueQuan.setBounds(247, 259, 135, 20);
		panel.add(textQueQuan);
		textQueQuan.setColumns(10);

		lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblGioiTinh.setBounds(154, 207, 49, 14);
		panel.add(lblGioiTinh);

		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		rdbtnNam.setBounds(240, 203, 54, 23);
		panel.add(rdbtnNam);

		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		rdbtnNu.setBounds(317, 203, 49, 23);
		panel.add(rdbtnNu);

		// Tạo ButtonGroup
		ButtonGroup groupGioiTinh = new ButtonGroup();

		// Thêm JRadioButton vào ButtonGroup
		groupGioiTinh.add(rdbtnNam);
		groupGioiTinh.add(rdbtnNu);

		lblHoSoCaNhan = new JLabel("Hồ sơ cá nhân");
		lblHoSoCaNhan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblHoSoCaNhan.setBounds(240, 64, 108, 14);
		panel.add(lblHoSoCaNhan);

		btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(e -> {
			// Lấy thông tin từ các trường nhập liệu
			String hoTen = textHoTen.getText();
			String queQuan = textQueQuan.getText();
			String gioiTinh = rdbtnNam.isSelected() ? "Nam" : "Nữ";

			// Kiểm tra dữ liệu trước khi lưu
			if (hoTen.isEmpty() || queQuan.isEmpty()) {
				System.out.println("Vui lòng điền đầy đủ thông tin trước khi lưu.");
				return;
			}

			// Cập nhật dữ liệu vào cơ sở dữ liệu
			try (Connection conn = new dbConnect().getConnection()) {
				String sql = "UPDATE DangNhap SET hoten = ?, quequan = ?, gioitinh = ? WHERE taikhoan = ?";
				try (PreparedStatement stmt = conn.prepareStatement(sql)) {
					stmt.setString(1, hoTen);
					stmt.setString(2, queQuan);
					stmt.setString(3, gioiTinh);
					stmt.setString(4, "taikhoan_cua_ban");

					int rowsUpdated = stmt.executeUpdate();
					if (rowsUpdated > 0) {
						System.out.println("Cập nhật thông tin thành công.");
						textHoTen.setEditable(false);
						textQueQuan.setEditable(false);
						rdbtnNam.setEnabled(false);
						rdbtnNu.setEnabled(false);
					} else {
						System.out.println("Không tìm thấy tài khoản để cập nhật.");
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Lỗi khi lưu thông tin: " + ex.getMessage());
			}
		});

		btnLuu.setBounds(293, 338, 89, 23);
		panel.add(btnLuu);

		btnSua = new JButton("Sửa");
		btnSua.addActionListener(e -> {
			// Cho phép chỉnh sửa các trường
			textHoTen.setEditable(true);
			textQueQuan.setEditable(true);
			rdbtnNam.setEnabled(true);
			rdbtnNu.setEnabled(true);
			System.out.println("Chế độ chỉnh sửa được kích hoạt.");
		});
		btnSua.setBounds(172, 338, 89, 23);
		panel.add(btnSua);

//		ProfileController controller = new ProfileController(this);

	}

	private JPanel panel;
	private JLabel lblHoTen, lblQueQuan, lblGioiTinh, lblHoSoCaNhan;
	private JTextField textHoTen, textQueQuan;
	private JButton btnLuu, btnSua;
	private JRadioButton rdbtnNam, rdbtnNu;

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextField getTextHoTen() {
		return textHoTen;
	}

	public void setTextHoTen(JTextField textHoTen) {
		this.textHoTen = textHoTen;
	}

	public JTextField getTextQueQuan() {
		return textQueQuan;
	}

	public void setTextQueQuan(JTextField textQueQuan) {
		this.textQueQuan = textQueQuan;
	}

	public JButton getBtnLuu() {
		return btnLuu;
	}

	public void setBtnLuu(JButton btnLuu) {
		this.btnLuu = btnLuu;
	}

	public JButton getBtnSua() {
		return btnSua;
	}

	public void setBtnSua(JButton btnSua) {
		this.btnSua = btnSua;
	}

	public JRadioButton getRdbtnNam() {
		return rdbtnNam;
	}

	public void setRdbtnNam(JRadioButton rdbtnNam) {
		this.rdbtnNam = rdbtnNam;
	}

	public JRadioButton getRdbtnNu() {
		return rdbtnNu;
	}

	public void setRdbtnNu(JRadioButton rdbtnNu) {
		this.rdbtnNu = rdbtnNu;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
