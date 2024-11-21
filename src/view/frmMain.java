package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import action.MainController;

public class frmMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel plMenu, plVachNgan1, plVachNgan2, plVachNgan3, plUser;
	private JButton btnTrangChu, btnQuanLy, btnThongKe, btnThongBao, btnVatNuoi, btnCayTrong, btnTrangTrai;
	private boolean isPanelVisible = false;
	private JPanel plMain, plAboveMain, plPage;
	private JLabel lblTitlePage, lblAvata, lblUserName;
	private JPopupMenu popupMenu;
	private JMenuItem miProfile, miDangXuat;

	public JMenuItem getMiProfile() {
		return miProfile;
	}

	public void setMiProfile(JMenuItem miProfile) {
		this.miProfile = miProfile;
	}

	public JMenuItem getMiDangXuat() {
		return miDangXuat;
	}

	public void setMiDangXuat(JMenuItem miDangXuat) {
		this.miDangXuat = miDangXuat;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMain frame = new frmMain();
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
	public frmMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		setTitle("Nông trại thông minh");

		plMenu = new JPanel();
		plMenu.setBounds(10, 11, 170, 552);
		plMenu.setBackground(new Color(61, 187, 164));

		btnTrangChu = new JButton("Trang chủ");
		btnTrangChu.setForeground(Color.WHITE);
		btnTrangChu.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnTrangChu.setBackground(new Color(61, 187, 164));
		btnTrangChu.setBorder(null);

		plVachNgan1 = new JPanel();
		plVachNgan1.setBackground(new Color(61, 187, 164));

		btnQuanLy = new JButton("Quản lý\r\n");
		btnQuanLy.setForeground(Color.WHITE);
		btnQuanLy.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnQuanLy.setBackground(new Color(61, 187, 164));
//		btnQuanLy.setIcon(new ImageIcon("D:\\Javagithub\\Login\\pic\\arrow.png"));
		btnQuanLy.setBorder(null);

		plVachNgan2 = new JPanel();
		plVachNgan2.setBackground(new Color(61, 187, 164));
		plVachNgan2.setLayout(null);

		btnVatNuoi = new JButton("Vật nuôi\r\n");
		btnVatNuoi.setBounds(0, 0, 170, 30);
		btnVatNuoi.setForeground(Color.WHITE);
		btnVatNuoi.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnVatNuoi.setBorder(null);
		btnVatNuoi.setBackground(new Color(61, 187, 164));
		plVachNgan2.setVisible(isPanelVisible);
		plVachNgan2.add(btnVatNuoi);

		btnCayTrong = new JButton("Cây trồng");
		btnCayTrong.setBounds(0, 30, 170, 30);
		btnCayTrong.setForeground(Color.WHITE);
		btnCayTrong.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnCayTrong.setBorder(null);
		btnCayTrong.setBackground(new Color(61, 187, 164));
		plVachNgan2.add(btnCayTrong);

		btnThongKe = new JButton("Thống kê");
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnThongKe.setBackground(new Color(61, 187, 164));
		btnThongKe.setBorder(null);

		plVachNgan3 = new JPanel();
		plVachNgan3.setBackground(new Color(61, 187, 164));

		btnThongBao = new JButton("Thông báo");
		btnThongBao.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnThongBao.setForeground(Color.WHITE);
		btnThongBao.setBackground(new Color(61, 187, 164));
		btnThongBao.setBorder(null);

		GroupLayout gl_plMenu = new GroupLayout(plMenu);
		gl_plMenu.setHorizontalGroup(gl_plMenu.createParallelGroup(Alignment.LEADING)
				.addComponent(btnTrangChu, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
				.addComponent(plVachNgan1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
				.addComponent(btnQuanLy, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
				.addComponent(plVachNgan2, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
				.addComponent(btnThongKe, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
				.addComponent(plVachNgan3, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
				.addComponent(btnThongBao, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE));
		gl_plMenu.setVerticalGroup(gl_plMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_plMenu.createSequentialGroup().addGap(159)
						.addComponent(btnTrangChu, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(plVachNgan1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnQuanLy, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(plVachNgan2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addGap(20).addComponent(btnThongKe, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(plVachNgan3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnThongBao, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)));

		btnTrangTrai = new JButton("Trang trại");
		btnTrangTrai.setForeground(Color.WHITE);
		btnTrangTrai.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnTrangTrai.setBorder(null);
		btnTrangTrai.setBackground(new Color(61, 187, 164));
		btnTrangTrai.setBounds(0, 60, 170, 30);
		plVachNgan2.add(btnTrangTrai);
		plMenu.setLayout(gl_plMenu);
		plMain = new JPanel();
		plMain.setBounds(186, 11, 599, 552);
		plMain.setBackground(new Color(251, 251, 251));

		plAboveMain = new JPanel();
		plAboveMain.setBounds(0, 0, 598, 59);
		plAboveMain.setBackground(new Color(251, 251, 251));

		lblTitlePage = new JLabel("Page");
		lblTitlePage.setBounds(215, 65, 100, 17);
		lblTitlePage.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		plUser = new JPanel();
		GroupLayout gl_plAboveMain = new GroupLayout(plAboveMain);
		gl_plAboveMain
				.setHorizontalGroup(gl_plAboveMain.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_plAboveMain.createSequentialGroup().addContainerGap(464, Short.MAX_VALUE)
								.addComponent(plUser, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		gl_plAboveMain.setVerticalGroup(gl_plAboveMain.createParallelGroup(Alignment.LEADING).addComponent(plUser,
				Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE));
		plUser.setLayout(null);

		lblUserName = new JLabel("UserName");
		lblUserName.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblUserName.setBounds(69, 23, 49, 25);
		plUser.add(lblUserName);

		lblAvata = new JLabel(new ImageIcon("D:\\Java github\\Login\\pic\\avata.jpg"));
		lblAvata.setBounds(0, 0, 59, 59);
		plUser.add(lblAvata);

		popupMenu = new JPopupMenu();
		addPopup(lblAvata, popupMenu);

		miProfile = new JMenuItem("Hồ sơ cá nhân");
		popupMenu.add(miProfile);

		miDangXuat = new JMenuItem("Đăng xuất");
		popupMenu.add(miDangXuat);

		plAboveMain.setLayout(gl_plAboveMain);
		getContentPane().setLayout(null);
		getContentPane().add(plMenu);
		getContentPane().add(plMain);
		plMain.setLayout(null);
		plMain.add(plAboveMain);
		plMain.add(lblTitlePage);

		plPage = new JPanel();
		plPage.setBounds(10, 93, 580, 450);
		plMain.add(plPage);
		plPage.setLayout(new BorderLayout(0, 0));

		MainController controller = new MainController(this);

	}

	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}

	public void setPopupMenu(JPopupMenu popupMenu) {
		this.popupMenu = popupMenu;
	}

	public JPanel getPlUser() {
		return plUser;
	}

	public void setPlUser(JPanel plUser) {
		this.plUser = plUser;
	}

	public void setLblTitlePage(JLabel lblTitlePage) {
		this.lblTitlePage = lblTitlePage;
	}

	public void setLblAvata(JLabel lblAvata) {
		this.lblAvata = lblAvata;
	}

	public JButton getBtnTrangChu() {
		return this.btnTrangChu;
	}

	public JButton getBtnQuanLy() {
		return this.btnQuanLy;
	}

	public JButton getBtnVatNuoi() {
		return this.btnVatNuoi;
	}

	public JButton getBtnCayTrong() {
		return this.btnCayTrong;
	}

	public JButton getBtnThongKe() {
		return this.btnThongKe;
	}

	public JButton getBtnThongBao() {
		return this.btnThongBao;
	}

	public JLabel getLblTitlePage() {
		return this.lblTitlePage;
	}

	public JPanel getPlVachNgan2() {
		return this.plVachNgan2;
	}

	public JLabel getLblUserName() {
		return lblUserName;
	}

	public JLabel getLblAvata() {
		return lblAvata;
	}

	public JPanel getPlMain() {
		return plMain;
	}

	public JButton getBtnTrangTrai() {
		return this.btnTrangTrai;
	}

	public void setPlMain(JPanel plMain) {
		this.plMain = plMain;
	}

	public JPanel getPlPage() {
		return plPage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
