package action;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controller.NguoiDungController;
import model.NguoiDung;
import view.frmDangKy;
import view.frmDangNhap;

public class RegisterController {
	private frmDangKy register;

	public RegisterController(frmDangKy registerForm) {
		this.register = registerForm;

		// dat placeholder "Nhập email"
		register.gettfEmail().addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (register.gettfEmail().getText().equals("Nhập email")) {
					register.gettfEmail().setText("");
					register.gettfEmail().setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (register.gettfEmail().getText().isEmpty()) {
					register.gettfEmail().setForeground(Color.GRAY);
					register.gettfEmail().setText("Nhập email");
				}
			}
		});
		// Xử lý placeholder cho tfMatKhau1
		register.gettfMatKhau1().addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (new String(register.gettfMatKhau1().getPassword()).equals("Nhập mật khẩu")) {
					register.gettfMatKhau1().setText("");
					register.gettfMatKhau1().setForeground(Color.BLACK);
					register.gettfMatKhau1().setEchoChar('*'); // Bật chế độ ẩn mật khẩu
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (register.gettfMatKhau1().getPassword().length == 0) {
					register.gettfMatKhau1().setForeground(Color.GRAY);
					register.gettfMatKhau1().setText("Nhập mật khẩu");
					register.gettfMatKhau1().setEchoChar((char) 0); // Tắt chế độ ẩn mật khẩu
				}
			}
		});

		// Xử lý placeholder cho tfMatKhau2
		register.gettfMatKhau2().addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (new String(register.gettfMatKhau2().getPassword()).equals("Nhập lại mật khẩu")) {
					register.gettfMatKhau2().setText("");
					register.gettfMatKhau2().setForeground(Color.BLACK);
					register.gettfMatKhau2().setEchoChar('*'); // Bật chế độ ẩn mật khẩu
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (register.gettfMatKhau2().getPassword().length == 0) {
					register.gettfMatKhau2().setForeground(Color.GRAY);
					register.gettfMatKhau2().setText("Nhập lại mật khẩu");
					register.gettfMatKhau2().setEchoChar((char) 0); // Tắt chế độ ẩn mật khẩu
				}
			}
		});
		// xử lý sự kiện nhấn vào checkbox hiển thị mật khẩu: bật -> dấu * chuyển thành
		// ký tự và ngược lại
		register.getCbHienThiMatKhau().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (register.getCbHienThiMatKhau().isSelected()) {
					register.gettfMatKhau1().setEchoChar((char) 0); // Hiển thị mật khẩu
					register.gettfMatKhau2().setEchoChar((char) 0); // Hiển thị mật khẩu nhập lại
				} else {
					register.gettfMatKhau1().setEchoChar('*'); // Ẩn mật khẩu
					register.gettfMatKhau2().setEchoChar('*'); // Ẩn mật khẩu nhập lại
				}
			}
		});

		// xử lý event nhấn vào đăng nhập -> chuyển hướng đến trang đăng nhập
		register.getlblDangNhap().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) { // Kiểm tra nút chuột trái
					// ẩn registerform
					register.getContenPane().setVisible(false);
					register.dispose();
					// Hiển thị LoginForm
					frmDangNhap loginForm = new frmDangNhap();
					loginForm.setVisible(true);
				}
			}
		});
		register.getbtnDangKy().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = register.gettfEmail().getText();
				String matKhau1 = String.valueOf(register.gettfMatKhau1().getPassword());
				String matKhau2 = String.valueOf(register.gettfMatKhau2().getPassword());

				try {
					NguoiDungController nguoiDungController = new NguoiDungController();

					// Validate email
					if (!isValidEmail(email)) {
						JOptionPane.showMessageDialog(register.getContenPane(),
								"Email không hợp lệ. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Kiểm tra tài khoản đã tồn tại
					if (nguoiDungController.getAllNguoiDung().stream()
							.anyMatch(nguoiDung -> nguoiDung.getTaikhoan().equals(email))) {
						JOptionPane.showMessageDialog(register.getContenPane(),
								"Email đã tồn tại. Vui lòng chọn email khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Validate mật khẩu
					if (!isValidPassword(matKhau1)) {
						JOptionPane.showMessageDialog(register.getContenPane(),
								"Mật khẩu phải chứa ít nhất: 1 chữ in hoa, 1 ký tự đặc biệt và 1 số.", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Xác nhận mật khẩu
					if (!matKhau1.equals(matKhau2)) {
						JOptionPane.showMessageDialog(register.getContenPane(),
								"Mật khẩu và mật khẩu xác nhận không khớp.", "Lỗi", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Thêm tài khoản mới
					nguoiDungController.addNguoiDung(new NguoiDung(email, matKhau1));

					JOptionPane.showMessageDialog(register.getContenPane(), "Đăng ký thành công!", "Thành công",
							JOptionPane.INFORMATION_MESSAGE);

					// Đóng RegisterForm và mở LoginForm
					register.setVisible(false);
					register.dispose();

					frmDangNhap loginForm = new frmDangNhap();
					loginForm.setVisible(true);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(register.getContenPane(), "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});

	}

	// Email validation
	private boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		return pattern.matcher(email).matches();
	}

	// Password validation
	private boolean isValidPassword(String password) {
		// xác thực tính hợp lệ của mật khẩu (ít nhất 1 chữ hoa, 1 ký tự đặc biệt, 1 số
		// và dài ít nhất 8 ký tự)
		String passwordRegex = "(?=.*[A-Z])(?=.*[0-9])(?=.*[^_=!#$%&()*+,-.:/'?@]).{8,}";
		return password.matches(passwordRegex);
	}

	public void hienThiMatKhau(boolean hienThi) {
		if (hienThi) {
			register.gettfMatKhau1().setEchoChar((char) 0);
			register.gettfMatKhau2().setEchoChar((char) 0);
		} else {
			if (new String(register.gettfMatKhau1().getPassword()).equals("Nhập mật khẩu")) {
				register.gettfMatKhau1().setEchoChar((char) 0);
			} else {
				register.gettfMatKhau1().setEchoChar('*');
			}

			if (new String(register.gettfMatKhau2().getPassword()).equals("Nhập lại mật khẩu")) {
				register.gettfMatKhau2().setEchoChar((char) 0);
			} else {
				register.gettfMatKhau2().setEchoChar('*');
			}
		}
	}
}
