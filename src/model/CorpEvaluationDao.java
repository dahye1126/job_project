package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CorpEvaluationDao {

	
	public CorpEvaluationBean selectCorpEvaluation(String corp_name) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CorpEvaluationBean ceb = new CorpEvaluationBean();

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");

			String sql = "select corp_code, corp_name from corp_status_non_fixed where corp_name=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, corp_name);

			rs = ps.executeQuery();

			if (rs.next()) {
				ceb.setCorp_code(rs.getInt("corp_code"));
				ceb.setCorp_name(rs.getString("corp_name"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ceb;
	}

	public ArrayList<CorpEvaluationBean> searchcorpname(String corp_name) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CorpEvaluationBean> list = new ArrayList<CorpEvaluationBean>();

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");

			String sql = "select corp_code, corp_name from corp_status_non_fixed where corp_name LIKE ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + corp_name + "%");

			rs = ps.executeQuery();

			while (rs.next()) {
				CorpEvaluationBean ceb = new CorpEvaluationBean();
				ceb.setCorp_code(rs.getInt("corp_code"));
				ceb.setCorp_name(rs.getString("corp_name"));

				list.add(ceb);

			}
			System.out.println("배열크기" + list.size());
			System.out.println("0번인덱스 이름" + list.get(0).getCorp_name());
			System.out.println("1번인덱스 이름" + list.get(1).getCorp_name());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public int insertCorpEvaluation(CorpEvaluationBean ceb, String id) {

		Connection con = null;
		PreparedStatement ps = null;
		int cnt = 0; // insert 된 갯수 저장
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");

			String sql = "insert into evaluation values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			
			//ps.setInt(1, ceb.getCorp_code());
			
			ps.setString(1, id);
			ps.setInt(2, ceb.getCorp_code());
			// ps.setString(2, ceb.getCorp_name());
			ps.setString(3, ceb.getOccupation1());
			ps.setString(4, ceb.getOccupation2());
			ps.setString(5, ceb.getPosition());
			ps.setString(6, ceb.getEducation_level());
			ps.setString(7, ceb.getCareer());
			ps.setString(8, ceb.getAnnual_income());
			ps.setString(9, ceb.getWork_type());
			ps.setString(10, ceb.getPromotion());
			ps.setString(11, ceb.getWelfare());
			ps.setString(12, ceb.getBalance());
			ps.setString(13, ceb.getCulture());
			ps.setString(14, ceb.getManagement());

			cnt = ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}

	public int insertCorpEvaluation1(CorpEvaluationBean ceb, String id) {

		Connection con = null;
		PreparedStatement ps = null;
		int cnt = 0; // insert 된 갯수 저장
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");

			String sql = "insert into evaluation values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			// ps.setString(1, ceb.getCorp_code());
			// ps.setString(1, ceb.getCorp_name());.
			
			ps.setString(1, id);
			ps.setInt(2, ceb.getCorp_code());
			ps.setString(3, ceb.getOccupation1());
			ps.setString(4, ceb.getOccupation2());
			ps.setString(5, ceb.getPosition());
			ps.setString(6, ceb.getEducation_level());
			ps.setString(7, ceb.getCareer());
			ps.setString(8, ceb.getAnnual_income());
			ps.setString(9, ceb.getWork_type());
			ps.setString(10, ceb.getPromotion());
			ps.setString(11, ceb.getWelfare());
			ps.setString(12, ceb.getBalance());
			ps.setString(13, ceb.getCulture());
			ps.setString(14, ceb.getManagement());

			cnt = ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}
	
	
	
	
	
	
	public int insertCorporation(String corp_name) {

		Connection con = null;
		PreparedStatement ps = null;
		int cnt = 0; // insert 된 갯수 저장
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");

			String sql = "insert into corp_status_non_fixed(corp_name, corp_code) values(?,(select max(corp_code) from corp_status_non_fixed)+1)";

			ps = con.prepareStatement(sql);
			
			ps.setString(1, corp_name);


			cnt = ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}
	
	
	
	
	
	
	

}
