package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CayTrong {
	private String maCayTrong;
	private String maTrangTrai;
	private String tenTrangTrai;
	private String tenCayTrong;
	private int soLuongCayTrong;
	private String tinhTrangSucKhoe;
	private float giaChamNuoi;
	private float sanLuongThuDuoc;

	// Constructor
	public CayTrong(String maTrangTrai, String tenTrangTrai, String maCayTrong, String tenCayTrong, int soLuongCayTrong,
			String tinhTrangSucKhoe, float giaChamNuoi, float sanLuongThuDuoc) {
		this.maTrangTrai = maTrangTrai;
		this.tenTrangTrai = tenTrangTrai;
		this.maCayTrong = maCayTrong;
		this.tenCayTrong = tenCayTrong;
		this.soLuongCayTrong = soLuongCayTrong;
		this.tinhTrangSucKhoe = tinhTrangSucKhoe;
		this.giaChamNuoi = giaChamNuoi;
		this.sanLuongThuDuoc = sanLuongThuDuoc;
	}

	// Constructor lấy dữ liệu từ ResultSet
	public CayTrong(ResultSet rs) throws SQLException {
		this.maTrangTrai = rs.getString("maTrangTrai");
		this.tenTrangTrai = rs.getString("tenTrangTrai");
		this.maCayTrong = rs.getString("maCayTrong");
		this.tenCayTrong = rs.getString("tenCayTrong");
		this.soLuongCayTrong = rs.getInt("SoLuongCayTrong");
		this.tinhTrangSucKhoe = rs.getString("tinhTrangSucKhoe");
		this.giaChamNuoi = rs.getFloat("GiaChamNuoi");
		this.sanLuongThuDuoc = rs.getFloat("SanLuongThuDuoc");
	}

	// Getter và Setter
	public String getMaCayTrong() {
		return maCayTrong;
	}

	public void setMaCayTrong(String maCayTrong) {
		this.maCayTrong = maCayTrong;
	}

	public String getMaTrangTrai() {
		return maTrangTrai;
	}

	public void setMaTrangTrai(String maTrangTrai) {
		this.maTrangTrai = maTrangTrai;
	}

	public String getTenTrangTrai() {
		return tenTrangTrai;
	}

	public void setTenTrangTrai(String tenTrangTrai) {
		this.tenTrangTrai = tenTrangTrai;
	}

	public String getTenCayTrong() {
		return tenCayTrong;
	}

	public void setTenCayTrong(String tenCayTrong) {
		this.tenCayTrong = tenCayTrong;
	}

	public int getSoLuongCayTrong() {
		return soLuongCayTrong;
	}

	public void setSoLuongCayTrong(int soLuongCayTrong) {
		this.soLuongCayTrong = soLuongCayTrong;
	}

	public String getTinhTrangSucKhoe() {
		return tinhTrangSucKhoe;
	}

	public void setTinhTrangSucKhoe(String tinhTrangSucKhoe) {
		this.tinhTrangSucKhoe = tinhTrangSucKhoe;
	}

	public float getGiaChamNuoi() {
		return giaChamNuoi;
	}

	public void setGiaChamNuoi(float giaChamNuoi) {
		this.giaChamNuoi = giaChamNuoi;
	}

	public float getSanLuongThuDuoc() {
		return sanLuongThuDuoc;
	}

	public void setSanLuongThuDuoc(float sanLuongThuDuoc) {
		this.sanLuongThuDuoc = sanLuongThuDuoc;
	}
}
