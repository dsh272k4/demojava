package action;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controller.NguoiDungController;
import view.frmDangKy;
import view.frmDangNhap;
import view.frmMain;
import view.frmQuenMatKhau;

public class LoginController {
	private frmDangNhap view;

	public LoginController(frmDangNhap view) {
		this.view = view;

		// thêm placeholder cho tf email
		view.gettfEmail().addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (view.gettfEmail().getText().equals("Nhập email")) {
					view.gettfEmail().setText("");
					view.gettfEmail().setForeground(Color.BLACK); // Đổi màu chữ khi người dùng nhập
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (view.gettfEmail().getText().isEmpty()) {
					view.gettfEmail().setForeground(Color.GRAY);
					view.gettfEmail().setText("Nhập email"); // Hiển thị lại placeholder nếu trống
				}
			}
		});

		// thêm placeholder cho pf mật khẩu
		view.gettfMatKhau().addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (new String(view.gettfMatKhau().getPassword()).equals("Nhập mật khẩu")) { // Use getPassword()
					view.gettfMatKhau().setText("");
					view.gettfMatKhau().setForeground(Color.BLACK);
					view.gettfMatKhau().setEchoChar('*'); // Bật chế độ ẩn mật khẩu
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (view.gettfMatKhau().getPassword().length == 0) {
					view.gettfMatKhau().setForeground(Color.GRAY);
					view.gettfMatKhau().setText("Nhập mật khẩu");
					view.gettfMatKhau().setEchoChar((char) 0); // Tắt chế độ ẩn mật khẩu
				}
			}
		});

		// xử lý sự kiện nhấn vào checkbox hiển thị mật khẩu: bật -> dấu * chuyển thành
		// ký tự và ngược lại
		view.getcbHienThiMatKhau().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (view.getcbHienThiMatKhau().isSelected()) {
					view.gettfMatKhau().setEchoChar((char) 0); // Hiển thị mật khẩu
				} else {
					if (new String(view.gettfMatKhau().getPassword()).equals("Nhập mật khẩu")) { // Use getPassword()
						view.gettfMatKhau().setEchoChar((char) 0); // Tắt chế độ ẩn cho placeholder
					} else {
						view.gettfMatKhau().setEchoChar('*'); // Ẩn mật khẩu
					}
				}
			}
		});

		// xử lý sự kiện nhấn vào đăng ký -> chuyển hướng đến trang đăng ký
		view.getlblDangKy().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) { // Kiểm tra nút chuột trái
					// ẩn loginform
					view.setVisible(false);
					view.dispose();

					// Hiển thị Registerform
					frmDangKy FrmDangKy = new frmDangKy();
					FrmDangKy.setVisible(true);
				}
			}
		});

		// xử lý sự kiện nhấn vào quên mật khẩu -> chuyển hướng đến trang quên mật khẩu
		view.getlblQuenMatKhau().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) { // Kiểm tra nút chuột trái
					// ẩn loginform
					view.setVisible(false);
					view.dispose();

					// Hiển thị ForgotPasswordForm
					frmQuenMatKhau forgotpasswordForm = new frmQuenMatKhau();
					forgotpasswordForm.setVisible(true);
				}
			}
		});
		view.getbtnDangNhap().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String taikhoan = view.gettfEmail().getText().trim();
					String matkhau = String.valueOf(view.gettfMatKhau().getPassword());

					if (taikhoan.isEmpty() || matkhau.isEmpty()) {
						JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin");
						return;
					}

					NguoiDungController nguoiDungController = new NguoiDungController();
					if (nguoiDungController.CheckLogin(taikhoan, matkhau)) {
						JOptionPane.showMessageDialog(view, "Đăng nhập thành công");
						frmMain frm = new frmMain();
						frm.setVisible(true);
						view.dispose();
					} else {
						JOptionPane.showMessageDialog(view, "Đăng nhập thất bại");
						view.gettfMatKhau().setText("");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(view, "Có lỗi xảy ra. Vui lòng thử lại sau.");
				}
			}
		});
	}
}
