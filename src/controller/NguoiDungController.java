package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.NguoiDung;

public class NguoiDungController {
	private Connection conn;

	public NguoiDungController() throws Exception {
		conn = new dbConnect().getConnection();
	}

	// Phương thức kiểm tra đăng nhập
	public boolean CheckLogin(String taikhoan, String matkhau) {
		String query = "SELECT * FROM DangNhap WHERE taikhoan=? AND matkhau=?";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, taikhoan);
			prst.setString(2, matkhau);
			try (ResultSet rs = prst.executeQuery()) {
				return rs.next(); // Trả về true nếu người dùng tồn tại
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Phương thức lấy danh sách tất cả người dùng
	public List<NguoiDung> getAllNguoiDung() throws SQLException {
		List<NguoiDung> lst = new ArrayList<>();
		String query = "SELECT * FROM DangNhap";
		try (PreparedStatement prst = conn.prepareStatement(query); ResultSet rs = prst.executeQuery()) {
			while (rs.next()) {
				NguoiDung obj = new NguoiDung(rs);
				lst.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi lấy dữ liệu: " + e.getMessage());
		}
		return lst;
	}

	// Phương thức thêm người dùng mới
	public void addNguoiDung(NguoiDung nguoiDung) throws SQLException {
		String query = "INSERT INTO DangNhap (taikhoan, matkhau) VALUES (?, ?)";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, nguoiDung.getTaikhoan());
			prst.setString(2, nguoiDung.getMatkhau());

			int rowsInserted = prst.executeUpdate(); // Kiểm tra số dòng được thêm
			if (rowsInserted > 0) {
				System.out.println("Đăng ký thành công. Người dùng đã được thêm vào cơ sở dữ liệu.");
			} else {
				System.out.println("Đăng ký thất bại. Không có dòng nào được thêm.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi tạo tài khoản: " + e.getMessage());
		}
	}

	// Đóng kết nối
	public void closeConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
