package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.MemberBean;

public class MemberDao {

	// Member �벑濡�
	public void insertMember(MemberBean mb) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");

			ps = con.prepareStatement("INSERT INTO member VALUES(?, ?, ?, ?, ?, ?, '1')");
			ps.setString(1, mb.getId());
			ps.setString(2, mb.getPassword());
			ps.setString(3, mb.getName());
			ps.setString(4, mb.getBirth());
			ps.setString(5, mb.getEmail());
			ps.setString(6, mb.getPhone());

			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
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
	}

	// Member 議고쉶
	public ArrayList<MemberBean> getMember(String name, int i) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");

			ps = con.prepareStatement("SELECT name, birth, gender, tel1, tel2, tel3 "
					+ "FROM memberjoin WHERE name = ?");
			ps.setString(1, name);
			rs = ps.executeQuery();

			while(rs.next()) {
				MemberBean mb = new MemberBean();
				mb.setName(rs.getString("name"));
				mb.setBirth(rs.getString("birth"));
				list.add(mb);
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

	// Member 濡쒓렇�씤
	public MemberBean getMember(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberBean mb = new MemberBean();

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");

			ps = con.prepareStatement("SELECT id, password, name, birth, email, phone, grade "
					+ "FROM member WHERE id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				mb.setId(rs.getString("id"));
				mb.setPassword(rs.getString("password"));
				mb.setName(rs.getString("name"));
				mb.setBirth(rs.getString("birth"));
				mb.setEmail(rs.getString("email"));
				mb.setPhone(rs.getString("phone"));
				mb.setGrade(rs.getString("grade"));
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
		return mb;
	}

	// Member �닔�젙
	public int updateMember(MemberBean mb, String tel1, String tel2, String tel3) {
		Connection con = null;
		PreparedStatement ps = null;
		int cnt = 0;	// update�맂 媛쒖닔 ���옣

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
			// �듃�옖�젥�뀡 泥섎━ �꽕�젙�븯湲�
			con.setAutoCommit(false);

			ps = con.prepareStatement("UPDATE memberjoin SET name = ?, birth = ?, gender = ?, tel1 = ?, tel2 = ?, tel3 = ? "
					+ "WHERE tel1 = ? AND tel2 = ? AND tel3 = ?");
			ps.setString(1, mb.getName());
			ps.setString(2, mb.getBirth());
			ps.setString(7, tel1);
			ps.setString(8, tel2);
			ps.setString(9, tel3);
			cnt = ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			// 荑쇰━臾� �뿉�윭 諛쒖깮�떆 catch �쁺�뿭
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			// Connection怨� PreparedStatement �떕湲�
			try {
				con.commit();
				con.setAutoCommit(true);//�듃�옖�젥�뀡 留덈Т由� �썑 �삤�넗而ㅻ컠 �솢�꽦�솕
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

	// Member �궘�젣
	public int delMember(String name) {
		Connection con = null;
		PreparedStatement ps = null;
		int cnt = 0;	// delete�맂 媛쒖닔 ���옣

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
			// �듃�옖�젥�뀡 泥섎━ �꽕�젙�븯湲�
			con.setAutoCommit(false);

			ps = con.prepareStatement("DELETE FROM memberjoin WHERE name =?");
			ps.setString(1, name);
			cnt = ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			// 荑쇰━臾� �뿉�윭 諛쒖깮�떆 catch �쁺�뿭
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			// Connection怨� PreparedStatement �떕湲�

			try {
				con.commit();
				con.setAutoCommit(true);	// �듃�옖�젥�뀡 留덈Т由� �썑 �삤�넗而ㅻ컠 �솢�꽦�솕
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

		return cnt;
	}

	// 以묐났 議고쉶
	public Boolean checkMember(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Boolean check = false;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");

			ps = con.prepareStatement("SELECT id FROM member WHERE id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();

			if(rs.next()) {
				check = true;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Connection怨� PreparedStatement, ResultSet �떕湲�
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
		return check;
	}
}
