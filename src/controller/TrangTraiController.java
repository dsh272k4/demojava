package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.TrangTrai;

public class TrangTraiController {
	private Connection conn;

	public TrangTraiController() throws Exception {
		conn = new dbConnect().getConnection();
	}

	// Lấy tất cả các trang trại
	public List<TrangTrai> getAllTT() throws SQLException {
		List<TrangTrai> lst = new ArrayList<>();
		String query = "SELECT * FROM tblTrangTrai";
		try (PreparedStatement prst = conn.prepareStatement(query); ResultSet rs = prst.executeQuery()) {
			while (rs.next()) {
				TrangTrai obj = new TrangTrai(rs);
				lst.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi lấy dữ liệu: " + e.getMessage());
		}
		return lst;
	}

	// Thêm một trang trại mới
	public void addTrangTrai(TrangTrai trangTrai) throws SQLException {
		String query = "INSERT INTO tblTrangTrai (maTrangTrai, tenTrangTrai, dienTichTrangTrai, vonTrangTrai, doanhThuTrangTrai) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, trangTrai.getMaTrangTrai());
			prst.setString(2, trangTrai.getTenTrangTrai());
			prst.setFloat(3, trangTrai.getDienTichTrangTrai());
			prst.setFloat(4, trangTrai.getVonTrangTrai());
			prst.setFloat(5, trangTrai.getDoanhThuTrangTrai());
			prst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi thêm trang trại: " + e.getMessage());
		}
	}

	// Cập nhật thông tin trang trại
	public void updateTrangTrai(TrangTrai trangTrai) throws SQLException {
		String query = "UPDATE tblTrangTrai SET tenTrangTrai = ?, dienTichTrangTrai = ?, vonTrangTrai = ?, doanhThuTrangTrai = ? WHERE maTrangTrai = ?";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, trangTrai.getTenTrangTrai());
			prst.setFloat(2, trangTrai.getDienTichTrangTrai());
			prst.setFloat(3, trangTrai.getVonTrangTrai());
			prst.setFloat(4, trangTrai.getDoanhThuTrangTrai());
			prst.setString(5, trangTrai.getMaTrangTrai());
			prst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi cập nhật trang trại: " + e.getMessage());
		}
	}

	// Xóa trang trại
	public void deleteTrangTrai(String ma) throws SQLException {
		String query = "DELETE FROM tblTrangTrai WHERE maTrangTrai = ?";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, ma);
			prst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi xóa trang trại: " + e.getMessage());
		}
	}

	public TrangTrai getTrangTraiById(String maTrangTrai) throws SQLException {
		String query = "SELECT * FROM tblTrangTrai WHERE maTrangTrai = ?";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, maTrangTrai);
			try (ResultSet rs = prst.executeQuery()) {
				if (rs.next()) {
					return new TrangTrai(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi lấy dữ liệu: " + e.getMessage());
		}
		return null;
	}

	public float calculateDoanhThu(String maTrangTrai) throws SQLException {
		float doanhThu = 0;

		// Tính doanh thu từ vật nuôi
		String vatNuoiQuery = """
				    SELECT SUM(SanLuongThuDuoc / 1.5 * 120 - GiaChamNuoi) AS DoanhThuVatNuoi
				    FROM tblVatNuoi
				    WHERE maTrangTrai = ?
				      AND tenVatNuoi IN ('gà', 'ga')
				    UNION ALL
				    SELECT SUM(SanLuongThuDuoc * 60 - GiaChamNuoi) AS DoanhThuVatNuoi
				    FROM tblVatNuoi
				    WHERE maTrangTrai = ?
				      AND tenVatNuoi IN ('lợn', 'lon')
				""";

		// Tính doanh thu từ cây trồng
		String cayTrongQuery = """
				    SELECT SUM(SanLuongThuDuoc * 5 - GiaChamNuoi) AS DoanhThuCayTrong
				    FROM tblCayTrong
				    WHERE maTrangTrai = ?
				    	AND tenCayTrong IN ('lúa', 'lua')
				    UNION ALL
				    SELECT SUM(SanLuongThuDuoc * 4 - GiaChamNuoi) AS DoanhThuCayTrong
				    FROM tblCayTrong
				    WHERE maTrangTrai = ?
				      AND tenCayTrong IN ('ngô', 'ngo')
				""";

		try (PreparedStatement prstVatNuoi = conn.prepareStatement(vatNuoiQuery);
				PreparedStatement prstCayTrong = conn.prepareStatement(cayTrongQuery)) {
			// Tính từ tblVatNuoi
			prstVatNuoi.setString(1, maTrangTrai);
			prstVatNuoi.setString(2, maTrangTrai);
			try (ResultSet rs = prstVatNuoi.executeQuery()) {
				while (rs.next()) {
					doanhThu += rs.getFloat(1);
				}
			}

			// Tính từ tblCayTrong
			prstCayTrong.setString(1, maTrangTrai);
			prstCayTrong.setString(2, maTrangTrai); // Thêm giá trị cho tham số thứ hai
			try (ResultSet rs = prstCayTrong.executeQuery()) {
				while (rs.next()) {
					doanhThu += rs.getFloat("DoanhThuCayTrong");
				}
			}
		}

		return doanhThu;
	}

	public void updateDoanhThu(String maTrangTrai) throws SQLException {
		float doanhThu = calculateDoanhThu(maTrangTrai);
		String updateQuery = "UPDATE tblTrangTrai SET DoanhThuTrangTrai = ? WHERE maTrangTrai = ?";
		try (PreparedStatement prst = conn.prepareStatement(updateQuery)) {
			prst.setFloat(1, doanhThu);
			prst.setString(2, maTrangTrai);
			prst.executeUpdate();
		}
	}

	// Tính tổng vốn
	public float calculateTongVon() throws SQLException {
		String query = "SELECT SUM(vonTrangTrai) AS TongVon FROM tblTrangTrai";
		try (PreparedStatement prst = conn.prepareStatement(query); ResultSet rs = prst.executeQuery()) {
			if (rs.next()) {
				return rs.getFloat("TongVon");
			}
		}
		return 0;
	}

	// Tính tổng sản lượng cây trồng
	public float calculateTongSanLuongCayTrong() throws SQLException {
		String query = "SELECT SUM(SanLuongThuDuoc) AS TongSanLuong FROM tblCayTrong";
		try (PreparedStatement prst = conn.prepareStatement(query); ResultSet rs = prst.executeQuery()) {
			if (rs.next()) {
				return rs.getFloat("TongSanLuong");
			}
		}
		return 0;
	}

	public float calculateTongSanLuongVatNuoi() throws SQLException {
		String query = "SELECT SUM(SanLuongThuDuoc) AS TongSanLuong FROM tblVatNuoi";
		try (PreparedStatement prst = conn.prepareStatement(query); ResultSet rs = prst.executeQuery()) {
			if (rs.next()) {
				return rs.getFloat("TongSanLuong");
			}
		}
		return 0;
	}

	// Tính tổng doanh thu (sử dụng phương thức đã có)
	public float calculateTongDoanhThu() throws SQLException {
		float tongDoanhThu = 0;
		String query = "SELECT maTrangTrai FROM tblTrangTrai";
		try (PreparedStatement prst = conn.prepareStatement(query); ResultSet rs = prst.executeQuery()) {
			while (rs.next()) {
				String maTrangTrai = rs.getString("maTrangTrai");
				tongDoanhThu += calculateDoanhThu(maTrangTrai);
			}
		}
		return tongDoanhThu;
	}

}
