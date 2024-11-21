package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VatNuoi {
	private String maTrangTrai;
	private String tenTrangTrai;
	private String maVatNuoi;
	private String tenVatNuoi;
	private int soLuongVatNuoi;
	private String tinhTrangSucKhoe;
	private float giaChamNuoi;
	private float sanLuongThuDuoc;

	// Constructor
	public VatNuoi(String maTrangTrai, String tenTrangTrai, String maVatNuoi, String tenVatNuoi, int soLuongVatNuoi,
			String tinhTrangSucKhoe, float giaChamNuoi, float sanLuongThuDuoc) {
		this.maTrangTrai = maTrangTrai;
		this.tenTrangTrai = tenTrangTrai;
		this.maVatNuoi = maVatNuoi;
		this.tenVatNuoi = tenVatNuoi;
		this.soLuongVatNuoi = soLuongVatNuoi;
		this.tinhTrangSucKhoe = tinhTrangSucKhoe;
		this.giaChamNuoi = giaChamNuoi;
		this.sanLuongThuDuoc = sanLuongThuDuoc;
	}

	// Constructor lấy dữ liệu từ ResultSet
	public VatNuoi(ResultSet rs) throws SQLException {
		this.maVatNuoi = rs.getString("maVatNuoi");
		this.maTrangTrai = rs.getString("maTrangTrai");
		this.tenTrangTrai = rs.getString("tenTrangTrai");
		this.tenVatNuoi = rs.getString("tenVatNuoi");
		this.soLuongVatNuoi = rs.getInt("SoLuongVatNuoi");
		this.tinhTrangSucKhoe = rs.getString("tinhTrangSucKhoe");
		this.giaChamNuoi = rs.getFloat("GiaChamNuoi");
		this.sanLuongThuDuoc = rs.getFloat("SanLuongThuDuoc");
	}

	// Getter và Setter
	public String getMaVatNuoi() {
		return maVatNuoi;
	}

	public void setMaVatNuoi(String maVatNuoi) {
		this.maVatNuoi = maVatNuoi;
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

	public String getTenVatNuoi() {
		return tenVatNuoi;
	}

	public void setTenVatNuoi(String tenVatNuoi) {
		this.tenVatNuoi = tenVatNuoi;
	}

	public int getSoLuongVatNuoi() {
		return soLuongVatNuoi;
	}

	public void setSoLuongVatNuoi(int soLuongVatNuoi) {
		this.soLuongVatNuoi = soLuongVatNuoi;
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
