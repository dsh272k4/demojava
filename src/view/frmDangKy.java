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

import action.RegisterController;

public class frmDangKy extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDangKy;
	private JTextField tfEmail;
	private JPasswordField tfMatKhau1;
	private JPasswordField tfMatKhau2;
	private JButton btnDangKy;
	private JLabel lblBnC;
	private JLabel lblDangNhap;
	private JCheckBox cbHienThiMatKhau;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmDangKy frame = new frmDangKy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public frmDangKy() {
		setTitle("Đăng ký");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDangKy = new JLabel("Đăng ký");
		lblDangKy.setHorizontalAlignment(SwingConstants.CENTER);
		lblDangKy.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDangKy.setForeground(new Color(61, 187, 164));
		lblDangKy.setBounds(10, 32, 266, 53);
		contentPane.add(lblDangKy);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(47, 96, 200, 30);
		tfEmail.setText("Nhập email");
		tfEmail.setForeground(Color.GRAY);
		contentPane.add(tfEmail);

		tfMatKhau1 = new JPasswordField();
		tfMatKhau1.setColumns(10);
		tfMatKhau1.setBounds(47, 149, 200, 30);
		tfMatKhau1.setText("Nhập mật khẩu");
		tfMatKhau1.setForeground(Color.GRAY);
		tfMatKhau1.setEchoChar((char) 0);
		contentPane.add(tfMatKhau1);

		tfMatKhau2 = new JPasswordField();
		tfMatKhau2.setColumns(10);
		tfMatKhau2.setBounds(47, 202, 200, 30);
		tfMatKhau2.setText("Nhập lại mật khẩu");
		tfMatKhau2.setForeground(Color.GRAY);
		tfMatKhau2.setEchoChar((char) 0);
		contentPane.add(tfMatKhau2);

		btnDangKy = new JButton("Đăng ký");
		btnDangKy.setForeground(Color.WHITE);
		btnDangKy.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnDangKy.setBackground(new Color(61, 187, 164));
		btnDangKy.setBounds(96, 265, 89, 23);
		contentPane.add(btnDangKy);

		lblBnC = new JLabel("Bạn đã có tài khoản?");
		lblBnC.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblBnC.setBounds(70, 337, 115, 14);
		contentPane.add(lblBnC);

		lblDangNhap = new JLabel("Đăng nhập");
		lblDangNhap.setForeground(new Color(61, 187, 164));
		lblDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblDangNhap.setBounds(172, 337, 49, 14);
		contentPane.add(lblDangNhap);

		cbHienThiMatKhau = new JCheckBox("Hiển thị mật khẩu");
		cbHienThiMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 9));
		cbHienThiMatKhau.setBounds(47, 235, 142, 23);
		contentPane.add(cbHienThiMatKhau);
//		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { lblDangKy, tfEmail, tfMatKhau1,
//				tfMatKhau2, cbHienThiMatKhau, btnDangKy, lblBnC, lblDangNhap }));

		RegisterController controller = new RegisterController(this);
	}

	public JTextField gettfEmail() {
		return this.tfEmail;
	}

	public JPasswordField gettfMatKhau1() {
		return this.tfMatKhau1;
	}

	public JPasswordField gettfMatKhau2() {
		return this.tfMatKhau2;
	}

	public JButton getbtnDangKy() {
		return this.btnDangKy;
	}

	public JLabel getlblDangNhap() {
		return this.lblDangNhap;
	}

	public JPanel getContenPane() {
		return this.contentPane;
	}

	public JCheckBox getCbHienThiMatKhau() {
		return this.cbHienThiMatKhau;
	}

}
