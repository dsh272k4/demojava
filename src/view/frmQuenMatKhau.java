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

import action.ForgotPasswordController;

public class frmQuenMatKhau extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfEmail;
	private JPasswordField tfMatKhau1;
	private JPasswordField tfMatKhau2;
	private JCheckBox cbHienThiMatKhau;
	private JButton btnKhoiPhuc;
	private JLabel lblDangNhap, lblDangKy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmQuenMatKhau frame = new frmQuenMatKhau();
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
	public frmQuenMatKhau() {
		setTitle("Quên mật khẩu\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblQuenMatKhau = new JLabel("Quên mật khẩu");
		lblQuenMatKhau.setBounds(10, 32, 266, 53);
		lblQuenMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuenMatKhau.setForeground(new Color(61, 187, 164));
		lblQuenMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblQuenMatKhau);

		tfEmail = new JTextField();
		tfEmail.setText("Nhập email");
		tfEmail.setColumns(10);
		tfEmail.setBounds(43, 100, 200, 30);
		tfEmail.setForeground(Color.GRAY);
		contentPane.add(tfEmail);

		tfMatKhau1 = new JPasswordField();
		tfMatKhau1.setText("Nhập mật khẩu mới");
		tfMatKhau1.setColumns(10);
		tfMatKhau1.setBounds(43, 150, 200, 30);
		tfMatKhau1.setEchoChar((char) 0);
		tfMatKhau1.setForeground(Color.GRAY);
		contentPane.add(tfMatKhau1);

		tfMatKhau2 = new JPasswordField();
		tfMatKhau2.setText("Nhập lại mật khẩu");
		tfMatKhau2.setColumns(10);
		tfMatKhau2.setBounds(43, 200, 200, 30);
		tfMatKhau2.setForeground(Color.GRAY);
		tfMatKhau2.setEchoChar((char) 0);
		contentPane.add(tfMatKhau2);

		cbHienThiMatKhau = new JCheckBox("Hiển thị mật khẩu");
		cbHienThiMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 9));
		cbHienThiMatKhau.setBounds(43, 237, 142, 23);
		contentPane.add(cbHienThiMatKhau);

		btnKhoiPhuc = new JButton("Khôi phục");
		btnKhoiPhuc.setForeground(Color.WHITE);
		btnKhoiPhuc.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnKhoiPhuc.setBackground(new Color(61, 187, 164));
		btnKhoiPhuc.setBounds(96, 280, 89, 23);
		contentPane.add(btnKhoiPhuc);

		lblDangNhap = new JLabel("Đăng nhập");
		lblDangNhap.setForeground(new Color(61, 187, 164));
		lblDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblDangNhap.setBounds(79, 351, 49, 14);
		contentPane.add(lblDangNhap);

		lblDangKy = new JLabel("Đăng ký");
		lblDangKy.setForeground(new Color(61, 187, 164));
		lblDangKy.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblDangKy.setBounds(167, 351, 49, 14);
		contentPane.add(lblDangKy);

		ForgotPasswordController controller = new action.ForgotPasswordController(this);
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

	public JButton getbtnKhoiPhuc() {
		return this.btnKhoiPhuc;
	}

	public JLabel getlblDangNhap() {
		return this.lblDangNhap;
	}

	public JLabel getlblDangKy() {
		return this.lblDangKy;
	}

	public JPanel getContenPane() {
		return this.contentPane;
	}

	public JCheckBox getCbHienThiMatKhau() {
		return this.cbHienThiMatKhau;
	}

}
