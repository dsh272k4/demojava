package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CayTrong;

public class CayTrongController {
	private Connection conn;

	public CayTrongController() throws Exception {
		conn = new dbConnect().getConnection();
	}

	// Lấy tất cả cây trồng
	public List<CayTrong> getAllCayTrong() throws SQLException {
		List<CayTrong> lst = new ArrayList<>();
		String query = "SELECT * FROM tblCayTrong";
		try (PreparedStatement prst = conn.prepareStatement(query); ResultSet rs = prst.executeQuery()) {
			while (rs.next()) {
				CayTrong obj = new CayTrong(rs);
				lst.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi lấy dữ liệu cây trồng: " + e.getMessage());
		}
		return lst;
	}

	// Thêm một cây trồng mới
	public void addCayTrong(CayTrong cayTrong) throws SQLException {
		String query = "INSERT INTO tblCayTrong ( maTrangTrai, tenTrangTrai,maCayTrong, tenCayTrong, SoLuongCayTrong, tinhTrangSucKhoe, GiaChamNuoi, SanLuongThuDuoc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, cayTrong.getMaTrangTrai());
			prst.setString(2, cayTrong.getTenTrangTrai());
			prst.setString(3, cayTrong.getMaCayTrong());
			prst.setString(4, cayTrong.getTenCayTrong());
			prst.setInt(5, cayTrong.getSoLuongCayTrong());
			prst.setString(6, cayTrong.getTinhTrangSucKhoe());
			prst.setFloat(7, cayTrong.getGiaChamNuoi());
			prst.setFloat(8, cayTrong.getSanLuongThuDuoc());
			prst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi thêm cây trồng: " + e.getMessage());
		}
	}

	// Cập nhật thông tin cây trồng
	public void updateCayTrong(CayTrong cayTrong) throws SQLException {
		String query = "UPDATE tblCayTrong SET tenTrangTrai = ?,  tenCayTrong = ?, SoLuongCayTrong = ?, tinhTrangSucKhoe = ?, GiaChamNuoi = ?, SanLuongThuDuoc = ? WHERE maCayTrong = ?";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, cayTrong.getTenTrangTrai());
			prst.setString(2, cayTrong.getTenCayTrong());
			prst.setInt(3, cayTrong.getSoLuongCayTrong());
			prst.setString(4, cayTrong.getTinhTrangSucKhoe());
			prst.setFloat(5, cayTrong.getGiaChamNuoi());
			prst.setFloat(6, cayTrong.getSanLuongThuDuoc());
			prst.setString(7, cayTrong.getMaCayTrong());
			prst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi cập nhật cây trồng: " + e.getMessage());
		}
	}

	// Xóa cây trồng
	public void deleteCayTrong(String ma) throws SQLException {
		String query = "DELETE FROM tblCayTrong WHERE maCayTrong = ?";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, ma);
			prst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi xóa cây trồng: " + e.getMessage());
		}
	}

	public CayTrong getCayTrongById(String maCayTrong) throws SQLException {
		String query = "SELECT * FROM tblCayTrong WHERE maCayTrong = ?";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(3, maCayTrong);
			try (ResultSet rs = prst.executeQuery()) {
				if (rs.next()) {
					return new CayTrong(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi lấy dữ liệu: " + e.getMessage());
		}
		return null;
	}

	public int getTongSoLuongCayTrongByTrangTrai(String maTrangTrai) throws SQLException {
		String query = "SELECT SUM(SoLuongCayTrong) AS TongSoLuong FROM tblCayTrong WHERE maTrangTrai = ?";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, maTrangTrai);
			try (ResultSet rs = prst.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("TongSoLuong");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Có lỗi xảy ra khi tính tổng số lượng cây trồng: " + e.getMessage());
		}
		return 0;
	}

	public List<CayTrong> getCayTrongByTrangTrai(String maTrangTrai) throws SQLException {
		List<CayTrong> lst = new ArrayList<>();
		String query = "SELECT * FROM tblCayTrong WHERE maTrangTrai = ?";
		try (PreparedStatement prst = conn.prepareStatement(query)) {
			prst.setString(1, maTrangTrai);
			try (ResultSet rs = prst.executeQuery()) {
				while (rs.next()) {
					CayTrong vatNuoi = new CayTrong(rs);
					lst.add(vatNuoi);
				}
			}
		}
		return lst;
	}

}