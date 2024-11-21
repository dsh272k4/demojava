package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import action.LoginController;

public class frmDangNhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDangNhap;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhau;
	private JCheckBox cbHienThiMatKhau;
	private JButton btnDangNhap;
	private JLabel lblNewLabel;
	private JLabel lblDangKy;
	private JLabel lblQuenMatKhau;
	private Color color = new Color(61, 187, 164);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmDangNhap frame = new frmDangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmDangNhap() {
		setTitle("Đăng nhập\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDangNhap = new JLabel("Đăng nhập");
		lblDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
		lblDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDangNhap.setForeground(color); // set màu chữ "Login" là rbg(61,187,164)
		lblDangNhap.setBounds(10, 32, 266, 53);
		contentPane.add(lblDangNhap);

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setBounds(43, 107, 200, 30);
		txtTaiKhoan.setText("Nhập email");
		contentPane.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		txtTaiKhoan.setForeground(Color.GRAY);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setBounds(43, 177, 200, 30);
		txtMatKhau.setText("Nhập mật khẩu");
		contentPane.add(txtMatKhau);
		txtMatKhau.setColumns(10);
		txtMatKhau.setEchoChar((char) 0);
		txtMatKhau.setForeground(Color.GRAY);

		cbHienThiMatKhau = new JCheckBox("Hiển thị mật khẩu");
		cbHienThiMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 9));
		cbHienThiMatKhau.setBounds(43, 214, 142, 23);
		contentPane.add(cbHienThiMatKhau);

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnDangNhap.setBounds(96, 254, 89, 23);
		btnDangNhap.setBackground(color); // set màu nền nút
		btnDangNhap.setForeground(new Color(255, 255, 255)); // set màu chữ
		contentPane.add(btnDangNhap);

		lblNewLabel = new JLabel("Bạn chưa có tài khoản?");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel.setBounds(58, 337, 115, 14);
		contentPane.add(lblNewLabel);

		lblDangKy = new JLabel("Đăng ký");
		lblDangKy.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblDangKy.setBounds(168, 337, 49, 14);
		lblDangKy.setForeground(color);
		contentPane.add(lblDangKy);

		lblQuenMatKhau = new JLabel("Quên mật khẩu?");
		lblQuenMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblQuenMatKhau.setBounds(96, 312, 87, 14);
		lblQuenMatKhau.setForeground(color);
		contentPane.add(lblQuenMatKhau);
//		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { tfEmail, lblDangNhap, tfMatKhau,
//				cbHienThiMatKhau, btnDangNhap, lblQuenMatKhau, lblNewLabel, lblDangKy }));

		LoginController controller = new LoginController(this);
//		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { lblDangNhap, tfEmail, tfMatKhau,
//				cbHienThiMatKhau, btnDangNhap, lblQuenMatKhau, lblNewLabel, contentPane, lblDangKy }));
	}

	public JTextField gettfEmail() {
		return this.txtTaiKhoan;
	}

	public JPasswordField gettfMatKhau() {
		return this.txtMatKhau;
	}

	public JCheckBox getcbHienThiMatKhau() {
		return this.cbHienThiMatKhau;
	}

	public JButton getbtnDangNhap() {
		return this.btnDangNhap;
	}

	public JLabel getlblDangKy() {
		return this.lblDangKy;
	}

	public JLabel getlblQuenMatKhau() {
		return this.lblQuenMatKhau;
	}
}
