package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.VatNuoi;

public class VatNuoiController {
	private Connection conn;

	public VatNuoiController() throws Exception {
		conn = new dbConnect().getConnection();
	}

	// Lấy tất cả vật nuôi
	public List<VatNuoi> getAllVatNuoi() throws SQLException {
		List<VatNuoi> lst = new ArrayList<>();
		String query = "SELECT * FROM tblVatNuoi";
		try (PreparedStatement prst = conn.prepareStatement(query); ResultSet rs = prst.executeQuery()) {
			while (rs.next()) {
				VatNuoi obj = new VatNuoi(rs);
				lst.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi lấy dữ liệu vật nuôi: " + e.getMessage());
		}
		return lst;
	}

	// Thêm một vật nuôi mới
	public void addVatNuoi(VatNuoi vatNuoi) throws SQLException {
		String query = "INSERT INTO tblVatNuoi(maTrangTrai, tenTrangTrai, maVatNuoi, tenVatNuoi, SoLuongVatNuoi, tinhTrangSucKhoe, GiaChamNuoi, SanLuongThuDuoc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, vatNuoi.getMaTrangTrai());
			prst.setString(2, vatNuoi.getTenTrangTrai());
			prst.setString(3, vatNuoi.getMaVatNuoi());
			prst.setString(4, vatNuoi.getTenVatNuoi());
			prst.setInt(5, vatNuoi.getSoLuongVatNuoi());
			prst.setString(6, vatNuoi.getTinhTrangSucKhoe());
			prst.setFloat(7, vatNuoi.getGiaChamNuoi());
			prst.setFloat(8, vatNuoi.getSanLuongThuDuoc());
			prst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi thêm vật nuôi: " + e.getMessage());
		}
	}

	// Cập nhật thông tin vật nuôi
	public void updateVatNuoi(VatNuoi vatNuoi) throws SQLException {
		String query = "UPDATE tblVatNuoi SET tenTrangTrai = ?, tenVatNuoi = ?, SoLuongVatNuoi = ?, tinhTrangSucKhoe = ?, GiaChamNuoi = ?, SanLuongThuDuoc = ? WHERE maVatNuoi = ?";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, vatNuoi.getTenTrangTrai());
			prst.setString(2, vatNuoi.getTenVatNuoi());
			prst.setInt(3, vatNuoi.getSoLuongVatNuoi());
			prst.setString(4, vatNuoi.getTinhTrangSucKhoe());
			prst.setFloat(5, vatNuoi.getGiaChamNuoi());
			prst.setFloat(6, vatNuoi.getSanLuongThuDuoc());
			prst.setString(7, vatNuoi.getMaVatNuoi());
			prst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi cập nhật vật nuôi: " + e.getMessage());
		}
	}

	// Xóa vật nuôi
	public void deleteVatNuoi(String mavn) throws SQLException {
		String query = "DELETE FROM tblVatNuoi WHERE maVatNuoi = ?";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, mavn);
			prst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi xóa vật nuôi: " + e.getMessage());
		}
	}

	public VatNuoi getVatNuoiById(String maVatNuoi) throws SQLException {
		String query = "SELECT * FROM tblVatNuoi WHERE maVatNuoi = ?";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(3, maVatNuoi);
			try (ResultSet rs = prst.executeQuery()) {
				if (rs.next()) {
					return new VatNuoi(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi lấy dữ liệu: " + e.getMessage());
		}
		return null;
	}

	public int getTongSoLuongVatNuoiByTrangTrai(String maTrangTrai) throws SQLException {
		String query = "SELECT SUM(SoLuongVatNuoi) AS TongSoLuong FROM tblVatNuoi WHERE maTrangTrai = ?";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, maTrangTrai);
			try (ResultSet rs = prst.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("TongSoLuong");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi tính tổng số lượng vật nuôi: " + e.getMessage());
		}
		return 0;
	}

	public List<VatNuoi> getVatNuoiByTrangTrai(String maTrangTrai) throws SQLException {
		List<VatNuoi> lst = new ArrayList<>();
		String query = "SELECT * FROM tblVatNuoi WHERE maTrangTrai = ?";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, maTrangTrai);
			try (ResultSet rs = prst.executeQuery()) {
				while (rs.next()) {
					VatNuoi vatNuoi = new VatNuoi(rs);
					lst.add(vatNuoi);
				}
			}
		}
		return lst;
	}

}
