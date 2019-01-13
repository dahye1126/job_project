package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CorpDao {
	
	public ArrayList<CorpSNFBean> findCorp(String name){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CorpSNFBean> list = new ArrayList<CorpSNFBean>();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			
			String sql = "SELECT corp_name, location, corp_code, established_year, area, web_site, representative FROM corp_status_non_fixed WHERE corp_name LIKE ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+name+"%");
			
			rs = ps.executeQuery();
			while(rs.next()) {
				CorpSNFBean cb = new CorpSNFBean();
				cb.setCorp_name(rs.getString("corp_name"));
				cb.setLocation(rs.getString("location"));
				cb.setCorp_code(rs.getInt("corp_code"));
				cb.setEst_year(rs.getString("established_year"));
				cb.setArea(rs.getString("area"));
				cb.setWeb_site(rs.getString("web_site"));
				cb.setRepresentative(rs.getString("representative"));
				list.add(cb);
			}			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public CorpSNFBean findCorp(int code){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CorpSNFBean cb = new CorpSNFBean();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			
			String sql = "SELECT corp_name, location, established_year, area, web_site, representative FROM corp_status_non_fixed WHERE corp_code = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, code);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				cb.setCorp_name(rs.getString("corp_name"));
				cb.setLocation(rs.getString("location"));
				cb.setCorp_code(code);
				cb.setEst_year(rs.getString("established_year"));
				cb.setArea(rs.getString("area"));
				cb.setWeb_site(rs.getString("web_site"));
				cb.setRepresentative(rs.getString("representative"));
			}			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cb;
	}
	
	public ArrayList<CorpSFBean> findCorp_Status(int code){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CorpSFBean> list = new ArrayList<CorpSFBean>();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			
			String sql = "SELECT number_of_emp, sales, average_wage, continuous_year, year FROM corp_status_fixed WHERE corp_code = ? ORDER BY year DESC";
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, code);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				CorpSFBean cb = new CorpSFBean();
				cb.setEmp(rs.getInt("number_of_emp"));
				cb.setSales(rs.getLong("sales"));
				cb.setAvg_wage(rs.getLong("average_wage"));
				cb.setContinuous_year(rs.getInt("continuous_year"));
				cb.setCorp_code(code);
				cb.setYear(rs.getInt("year"));
				list.add(cb);
			}			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
