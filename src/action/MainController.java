package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.frmCayTrong;
import view.frmDangNhap;
import view.frmMain;
import view.frmProfile;
import view.frmTrangChu;
import view.frmTrangTrai;
import view.frmVatNuoi;

public class MainController {
	private frmMain view;
	private boolean isPanelVisible = false;

	public MainController(frmMain view) {
		this.view = view;
		// Hiển thị Trang chủ mặc định
		frmTrangChu trangChuPanel = new frmTrangChu();
		view.getPlPage().add(trangChuPanel);
		view.getLblTitlePage().setText(view.getBtnTrangChu().getText());

// xử lý sự kiện nút trang chủ
		view.getBtnTrangChu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Hiển thị TrangChu trên plPage
				view.getPlPage().removeAll();
				frmTrangChu trangChuPanel = new frmTrangChu();
				view.getPlPage().add(trangChuPanel);
				view.getPlPage().revalidate();
				view.getPlPage().repaint();

				// Hiển thị tiêu đề trang
				String buttonText = view.getBtnTrangChu().getText();
				// Đặt text cho titlePage
				view.getLblTitlePage().setText(buttonText);

				// ... (Các xử lý khác của nút Trang chủ)
			}
		});

		// xử lý khi trước đó danh sách quản lý được mở ra
		view.getBtnTrangChu().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Xử lý khi nút được nhấn
				if (isPanelVisible) {
					isPanelVisible = false;
					view.getPlVachNgan2().setVisible(isPanelVisible);
				}
			}
		});

// xử lý sự kiện nút quản lý	
		view.getBtnQuanLy().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buttonText1 = view.getBtnQuanLy().getText();
				// Lấy text của nút Quản lý
				String buttonText = view.getBtnQuanLy().getText();
				// Đặt text cho titlePage
				view.getLblTitlePage().setText(buttonText);

				// ... (Các xử lý khác của nút Quản lý)

				// mặc định mở trang trại
				view.getPlPage().removeAll();
				frmTrangTrai qlTrangTrai = new frmTrangTrai();
				view.getPlPage().add(qlTrangTrai);
				view.getPlPage().revalidate();
				view.getPlPage().repaint();
			}
		});

//ẩn hiện danh sách quản lý
		// Thêm ActionListener để hiển thị/ẩn plVachNgan2 khi nhấn vào quản lý
		view.getBtnQuanLy().addActionListener(e -> {
			isPanelVisible = !isPanelVisible;
			view.getPlVachNgan2().setVisible(isPanelVisible);
		});
		// Thêm MouseListener cho toàn bộ frame
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Kiểm tra xem click có nằm ngoài vùng btnNewButton_1 và plVachNgan2 không
				if (!view.getBtnQuanLy().getBounds().contains(e.getPoint())
						&& !view.getPlVachNgan2().getBounds().contains(e.getPoint())) {
					isPanelVisible = false;
					view.getPlVachNgan2().setVisible(isPanelVisible);
				}
			}
		});

// xử lý sự kiện nút Vật nuôi	
		view.getBtnVatNuoi().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ... (Các xử lý khác của nút Cây trồng)

				// Hiển thị QuanLyVatNuoi trên plPage
				view.getPlPage().removeAll();
				frmVatNuoi qlVatNuoi = new frmVatNuoi();
				view.getPlPage().add(qlVatNuoi);
				view.getPlPage().revalidate();
				view.getPlPage().repaint();

				// Lấy text của nút Vật nuôi
				String buttonText = view.getBtnVatNuoi().getText();
				// Đặt text cho titlePage
				view.getLblTitlePage().setText(buttonText);
			}
		});

//xử lý sự kiện nút Cây trồng			
		view.getBtnCayTrong().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ... (Các xử lý khác của nút Cây trồng)

				// Hiển thị QuanLyCayTrong trên plPage
				view.getPlPage().removeAll();
				frmCayTrong qlCayTrong = new frmCayTrong();
				view.getPlPage().add(qlCayTrong);
				view.getPlPage().revalidate();
				view.getPlPage().repaint();

				// Lấy text của nút Cây trồng
				String buttonText = view.getBtnCayTrong().getText();
				// Đặt text cho titlePage
				view.getLblTitlePage().setText(buttonText);
			}
		});

//xử lý sự kiện nút trang trại
		view.getBtnTrangTrai().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ... (Các xử lý khác của nút TrangTrai)

				view.getPlPage().removeAll();
				frmTrangTrai qlTrangTrai = new frmTrangTrai();
				view.getPlPage().add(qlTrangTrai);
				view.getPlPage().revalidate();
				view.getPlPage().repaint();

				// Lấy text của nút TrangTrai
				String buttonText = view.getBtnTrangTrai().getText();
				// Đặt text cho titlePage
				view.getLblTitlePage().setText(buttonText);

			}

		});
// xử lý sự kiện nút thống kê
		view.getBtnThongKe().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lấy text của nút Thống kê
				String buttonText = view.getBtnThongKe().getText();
				// Đặt text cho titlePage
				view.getLblTitlePage().setText(buttonText);

				// ... (Các xử lý khác của nút Thống kê)
			}
		});

		// xử lý khi trước đó danh sách quản lý được mở ra
		view.getBtnThongKe().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Xử lý khi nút được nhấn
				if (isPanelVisible) {
					isPanelVisible = false;
					view.getPlVachNgan2().setVisible(isPanelVisible);
				}
			}
		});

// xử lý sự kiện nút thông báo
		view.getBtnThongBao().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lấy text của nút Trang chủ
				String buttonText = view.getBtnThongBao().getText();
				// Đặt text cho titlePage
				view.getLblTitlePage().setText(buttonText);

				// ... (Các xử lý khác của nút Trang chủ)
			}
		});

		// xử lý khi trước đó danh sách quản lý được mở ra
		view.getBtnThongBao().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Xử lý khi nút được nhấn
				if (isPanelVisible) {
					isPanelVisible = false;
					view.getLblTitlePage().setVisible(isPanelVisible);
				}
			}
		});

// xử lý sự kiện panel user
		view.getPlUser().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				view.getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
			}
		});

		view.getLblAvata().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				view.getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
			}
		});

		view.getLblUserName().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				view.getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
			}
		});

// xử lý sự kiện profile
		view.getMiProfile().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getPlPage().removeAll();
				frmProfile profile = new frmProfile();
				view.getPlPage().add(profile);
				view.getPlPage().revalidate();
				view.getPlPage().repaint();
			}
		});

// xử lý sự kiện đăng xuất
		view.getMiDangXuat().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Ẩn giao diện chính
				view.dispose();

				// Hiển thị giao diện đăng nhập
				frmDangNhap loginView = new frmDangNhap();
				loginView.setVisible(true);
			}
		});
	}
}
