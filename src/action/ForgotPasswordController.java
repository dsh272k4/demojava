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

import view.frmDangKy;
import view.frmDangNhap;
import view.frmQuenMatKhau;

public class ForgotPasswordController {
	private frmQuenMatKhau frmQuenMatKhau;

	public ForgotPasswordController(frmQuenMatKhau view) {
		this.frmQuenMatKhau = view;

		// Đặt placeholder "Nhập email"
		frmQuenMatKhau.gettfEmail().addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (frmQuenMatKhau.gettfEmail().getText().equals("Nhập email")) {
					frmQuenMatKhau.gettfEmail().setText("");
					frmQuenMatKhau.gettfEmail().setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (frmQuenMatKhau.gettfEmail().getText().isEmpty()) {
					frmQuenMatKhau.gettfEmail().setForeground(Color.GRAY);
					frmQuenMatKhau.gettfEmail().setText("Nhập email");
				}
			}
		});
		// placeholder cho tfMatKhau1
		frmQuenMatKhau.gettfMatKhau1().addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (new String(frmQuenMatKhau.gettfMatKhau1().getPassword()).equals("Nhập mật khẩu mới")) {
					frmQuenMatKhau.gettfMatKhau1().setText("");
					frmQuenMatKhau.gettfMatKhau1().setForeground(Color.BLACK);
					frmQuenMatKhau.gettfMatKhau1().setEchoChar('*'); // Bật chế độ ẩn mật khẩu
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (frmQuenMatKhau.gettfMatKhau1().getPassword().length == 0) {
					frmQuenMatKhau.gettfMatKhau1().setForeground(Color.GRAY);
					frmQuenMatKhau.gettfMatKhau1().setText("Nhập mật khẩu mới");
					frmQuenMatKhau.gettfMatKhau1().setEchoChar((char) 0); // Tắt chế độ ẩn mật khẩu
				}
			}
		});

		// placeholder cho tfMatKhau2
		frmQuenMatKhau.gettfMatKhau2().addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (new String(frmQuenMatKhau.gettfMatKhau2().getPassword()).equals("Nhập lại mật khẩu")) {
					frmQuenMatKhau.gettfMatKhau2().setText("");
					frmQuenMatKhau.gettfMatKhau2().setForeground(Color.BLACK);
					frmQuenMatKhau.gettfMatKhau2().setEchoChar('*'); // Bật chế độ ẩn mật khẩu
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (frmQuenMatKhau.gettfMatKhau2().getPassword().length == 0) {
					frmQuenMatKhau.gettfMatKhau2().setForeground(Color.GRAY);
					frmQuenMatKhau.gettfMatKhau2().setText("Nhập lại mật khẩu");
					frmQuenMatKhau.gettfMatKhau2().setEchoChar((char) 0); // Tắt chế độ ẩn mật khẩu
				}
			}
		});
		// xử lý sự kiện nhấn vào checkbox hiển thị mật khẩu: bật -> dấu * chuyển thành
		// ký tự và ngược lại
		frmQuenMatKhau.getCbHienThiMatKhau().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (frmQuenMatKhau.getCbHienThiMatKhau().isSelected()) {
					frmQuenMatKhau.gettfMatKhau1().setEchoChar((char) 0); // Hiển thị mật khẩu
					frmQuenMatKhau.gettfMatKhau2().setEchoChar((char) 0); // Hiển thị mật khẩu nhập lại
				} else {
					frmQuenMatKhau.gettfMatKhau1().setEchoChar('*'); // Ẩn mật khẩu
					frmQuenMatKhau.gettfMatKhau2().setEchoChar('*'); // Ẩn mật khẩu nhập lại
				}
			}
		});
		// xử lý event nhấn vào nút Khôi phục
		frmQuenMatKhau.getbtnKhoiPhuc().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = frmQuenMatKhau.gettfEmail().getText();
				String matKhau1 = String.valueOf(frmQuenMatKhau.gettfMatKhau1().getPassword());
				String matKhau2 = String.valueOf(frmQuenMatKhau.gettfMatKhau2().getPassword());

				// Validate email
				if (!isValidEmail(email)) {
					JOptionPane.showMessageDialog(frmQuenMatKhau.getContenPane(),
							"Email không hợp lệ. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Validate password
				if (!isValidPassword(matKhau1)) {
					JOptionPane.showMessageDialog(frmQuenMatKhau.getContenPane(),
							"Mật khẩu phải chứa ít nhất: 1 chữ in hoa, 1 ký tự đặc biệt và 1 số.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Validate password match
				if (!matKhau1.equals(matKhau2)) {
					JOptionPane.showMessageDialog(frmQuenMatKhau.getContenPane(),
							"Mật khẩu và mật khẩu xác nhận không khớp.", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Show success message
				JOptionPane.showMessageDialog(frmQuenMatKhau.getContenPane(), "Đăng ký thành công!", "Thành công",
						JOptionPane.INFORMATION_MESSAGE);

				// Ẩn ForgotForm hiện tại
				frmQuenMatKhau.setVisible(false);
				frmQuenMatKhau.dispose();

				// Tạo và hiển thị LoginForm
				frmDangNhap loginForm = new frmDangNhap();
				loginForm.setVisible(true);
			}
		});

		frmQuenMatKhau.getlblDangNhap().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) { // Kiểm tra nút chuột trái
					// ẩn forgotform
					frmQuenMatKhau.getContenPane().setVisible(false);
					frmQuenMatKhau.dispose();

					// Hiển thị LoginForm
					frmDangNhap loginForm = new frmDangNhap();
					loginForm.setVisible(true);
				}
			}
		});

		frmQuenMatKhau.getlblDangKy().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) { // Kiểm tra nút chuột trái
					// ẩn forgotform
					frmQuenMatKhau.getContenPane().setVisible(false);
					frmQuenMatKhau.dispose();

					// Hiển thị RegisterForm
					frmDangKy registerForm = new frmDangKy();
					registerForm.setVisible(true);
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

}
