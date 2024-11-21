package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.TrangTraiController;

public class frmTrangChu extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public frmTrangChu() {
		setLayout(null);

		plThongBao = new JPanel();
		plThongBao.setBounds(0, 175, 570, 252);
		add(plThongBao);
		plThongBao.setLayout(null);

		lblThongBao = new JLabel("Thông báo mới nhất");
		lblThongBao.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblThongBao.setBounds(10, 11, 125, 14);
		plThongBao.add(lblThongBao);

		plTongQuan = new JPanel();
		plTongQuan.setBounds(0, 11, 570, 143);
		add(plTongQuan);
		plTongQuan.setLayout(null);

		lblTongQuan = new JLabel("Tổng quan");
		lblTongQuan.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTongQuan.setBounds(0, 0, 59, 14);
		plTongQuan.add(lblTongQuan);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 255, 255));
		panel_1.setBounds(10, 31, 100, 100);
		plTongQuan.add(panel_1);
		panel_1.setLayout(null);

		lblSLCayTrong = new JLabel("Sản lượng cây trồng");
		lblSLCayTrong.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblSLCayTrong.setBounds(0, 0, 100, 20);
		panel_1.add(lblSLCayTrong);

		lblCayTrong = new JLabel("");
		lblCayTrong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCayTrong.setBounds(0, 50, 100, 50);
		panel_1.add(lblCayTrong);

		panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(128, 255, 255));
		panel_1_1.setBounds(159, 31, 100, 100);
		plTongQuan.add(panel_1_1);
		panel_1_1.setLayout(null);

		lblSLVatNuoi = new JLabel("Sản lượng vật nuôi");
		lblSLVatNuoi.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblSLVatNuoi.setBounds(0, 0, 100, 22);
		panel_1_1.add(lblSLVatNuoi);

		lblVatNuoi = new JLabel("12192.50 kg");
		lblVatNuoi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVatNuoi.setBounds(0, 50, 100, 50);
		panel_1_1.add(lblVatNuoi);

		panel_1_2 = new JPanel();
		panel_1_2.setBackground(new Color(128, 255, 255));
		panel_1_2.setBounds(307, 31, 100, 100);
		plTongQuan.add(panel_1_2);
		panel_1_2.setLayout(null);

		JLabel lblVon = new JLabel("Vốn");
		lblVon.setBounds(0, 0, 49, 22);
		panel_1_2.add(lblVon);

		lblTongVon = new JLabel("");
		lblTongVon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTongVon.setBounds(0, 50, 100, 50);
		panel_1_2.add(lblTongVon);

		panel_1_3 = new JPanel();
		panel_1_3.setBackground(new Color(128, 255, 255));
		panel_1_3.setBounds(459, 31, 100, 100);
		plTongQuan.add(panel_1_3);
		panel_1_3.setLayout(null);

		lblDoanhThu = new JLabel("Doanh thu dự tính");
		lblDoanhThu.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblDoanhThu.setBounds(0, 0, 100, 21);
		panel_1_3.add(lblDoanhThu);

		lblTongDoanhThu = new JLabel("");
		lblTongDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTongDoanhThu.setBounds(0, 50, 100, 50);
		panel_1_3.add(lblTongDoanhThu);

		updateLabels();

	}

	private JPanel plThongBao, plTongQuan, panel_1, panel_1_2, panel_1_3, panel_1_1;
	private JLabel lblThongBao, lblTongQuan;
	private JLabel lblSLCayTrong;
	private JLabel lblSLVatNuoi;
	private JLabel lblDoanhThu;
	private JLabel lblCayTrong;
	private JLabel lblTongVon;
	private JLabel lblTongDoanhThu;
	private JLabel lblVatNuoi;

	private void updateLabels() {
		try {
			TrangTraiController controller = new TrangTraiController(); // Kết nối với controller

			// Tính toán tổng vốn
			float tongVon = controller.calculateTongVon();
			lblTongVon.setText(String.format("%.2f VNĐ", tongVon));

			// Tính toán tổng sản lượng cây trồng
			float tongCayTrong = controller.calculateTongSanLuongCayTrong();
			lblCayTrong.setText(String.format("%.2f kg", tongCayTrong));

			float tongVatNuoi = controller.calculateTongSanLuongVatNuoi();
			lblVatNuoi.setText(String.format("%.2f con", tongVatNuoi));
			// Tính toán tổng doanh thu
			float tongDoanhThu = controller.calculateTongDoanhThu();
			lblTongDoanhThu.setText(String.format("%.2f VNĐ", tongDoanhThu));

		} catch (Exception e) {
			e.printStackTrace();
			lblVatNuoi.setText("Lỗi tải dữ liệu");
			lblTongVon.setText("Lỗi tải dữ liệu");
			lblCayTrong.setText("Lỗi tải dữ liệu");
			lblTongDoanhThu.setText("Lỗi tải dữ liệu");
		}
	}

}