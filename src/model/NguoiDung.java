package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NguoiDung {
	private String taikhoan;
	private String matkhau;
	private String hoten;
	private String gioitinh;
	private String quequan;

	public NguoiDung(String taikhoan, String matkhau, String hoten, String gioitinh, String quequan) {
		this.taikhoan = taikhoan;
		this.matkhau = matkhau;
		this.hoten = hoten;
		this.gioitinh = gioitinh;
		this.quequan = quequan;
	}

	public NguoiDung(ResultSet rs) throws SQLException {
		this.taikhoan = rs.getString("taikhoan");
		this.matkhau = rs.getString("matkhau");
		this.hoten = rs.getString("hoten");
		this.gioitinh = rs.getString("gioitinh");
		this.quequan = rs.getString("quequan");
	}

	public String getTaikhoan() {
		return taikhoan;
	}

	public void setTaikhoan(String taikhoan) {
		this.taikhoan = taikhoan;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getQuequan() {
		return quequan;
	}

	public void setQuequan(String quequan) {
		this.quequan = quequan;
	}

}
