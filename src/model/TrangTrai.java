package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrangTrai {
	private String maTrangTrai;
	private String tenTrangTrai;
	private Float dienTichTrangTrai;
	private Float vonTrangTrai;
	private Float doanhThuTrangTrai;

	// Constructor với các tham số
	public TrangTrai(String maTrangTrai, String tenTrangTrai, Float dienTichTrangTrai, Float vonTrangTrai,
			Float doanhThuTrangTrai) {
		this.maTrangTrai = maTrangTrai;
		this.tenTrangTrai = tenTrangTrai;
		this.dienTichTrangTrai = dienTichTrangTrai;
		this.vonTrangTrai = vonTrangTrai;
		this.doanhThuTrangTrai = doanhThuTrangTrai;
	}

	// Constructor nhận dữ liệu từ ResultSet (khi lấy dữ liệu từ cơ sở dữ liệu)
	public TrangTrai(ResultSet rs) throws SQLException {
		this.maTrangTrai = rs.getString("maTrangTrai");
		this.tenTrangTrai = rs.getString("tenTrangTrai");
		this.dienTichTrangTrai = rs.getFloat("dienTichTrangTrai");
		this.vonTrangTrai = rs.getFloat("vonTrangTrai");
		this.doanhThuTrangTrai = rs.getFloat("doanhThuTrangTrai");
	}

	// Getter và setter
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

	public Float getDienTichTrangTrai() {
		return dienTichTrangTrai;
	}

	public void setDienTichTrangTrai(Float dienTichTrangTrai) {
		this.dienTichTrangTrai = dienTichTrangTrai;
	}

	public Float getVonTrangTrai() {
		return vonTrangTrai;
	}

	public void setVonTrangTrai(Float vonTrangTrai) {
		this.vonTrangTrai = vonTrangTrai;
	}

	public Float getDoanhThuTrangTrai() {
		return doanhThuTrangTrai;
	}

	public void setDoanhThuTrangTrai(Float doanhThuTrangTrai) {
		this.doanhThuTrangTrai = doanhThuTrangTrai;
	}
}
