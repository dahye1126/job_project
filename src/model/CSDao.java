package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CSDao {
	// 게시글 등록
	public CSBean inserBoard(CSBean bb) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int cnt = 0;	// insert된 개수 저장 변수
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			
			ps = con.prepareStatement("INSERT INTO customer_service(id, title, content, time, no) "
					+ "VALUES(?, ?, ?, sysdate, cs_seq.nextval)");
			ps.setString(1, bb.getId());
			ps.setString(2, bb.getTitle());
			ps.setString(3, bb.getContent());
			
			cnt = ps.executeUpdate();
			
			ps = con.prepareStatement("select id, title, content, secret_check, time, no, reply_check from customer_service where id=? order by no desc");
			ps.setString(1, bb.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CSBean cb = new CSBean();
				cb.setContent(rs.getString("content"));
				cb.setTitle(rs.getString("title"));
				cb.setId(rs.getString("id"));
				cb.setTime(rs.getString("time"));
				cb.setNo(rs.getString("no"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Connection과 PreparedStatement 닫기
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
		return bb;
	}
	//글 갯수 구하는 메서드(배낀거)
	public int BoardCount(HashMap<String, Object> listopt) {
		int result = 0;
		String opt = (String)listopt.get("opt"); //검색옵션(제목, 내용, 글쓴이 등)
		String condition = (String)listopt.get("condition"); //검색내용
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
		Class.forName("oracle.jdbc.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
		
		if(opt == null) { //검색조건없음 = 전체 글 갯수
			ps = con.prepareStatement("select count(*) from customer_service");
		}
		else if(opt.equals("0")) { //제목으로 검색한 글 갯수
			ps = con.prepareStatement("select count(*) from customer_service where title like ?");
			ps.setString(1, '%'+condition+'%');
		}
		else if(opt.equals("1")) { //내용검색 글 갯수
			ps = con.prepareStatement("select count(*) from customer_service where content like ?");
			ps.setString(1, '%'+condition+'%');
		}
		else if(opt.equals("2")) { //제목+내용 검색
			ps = con.prepareStatement("select count(*) from customer_service where title like ? or content like ?");
			ps.setString(1, '%'+condition+'%');
			ps.setString(2, '%'+condition+'%');
		}
		else if(opt.equals("3")) { //id로 검색한 글 갯수
			ps = con.prepareStatement("select count(*) from customer_service where id like ?");
			ps.setString(1, '%'+condition+'%');
		}
		ResultSet rs = ps.executeQuery();
		if(rs.next()) result = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// Connection과 PreparedStatement 닫기
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
		return result;
	}
	//글 목록 가져오기
	public ArrayList<CSBean> BoardMain(HashMap<String, Object> listopt){
		Connection con = null;
		PreparedStatement ps = null;
		String opt = (String)listopt.get("opt"); //검색옵션
		String condition = (String)listopt.get("condition"); // 검색내용
        int start = (Integer)listopt.get("start"); // 현재 페이지번호
		ArrayList<CSBean> list = new ArrayList<CSBean>();
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			
			if(opt == null) { //전체 글
				ps = con.prepareStatement("select r, id, title, content, time, secret_check, reply_check, no from (select rownum r, id, title, content, time, secret_check, reply_check, no from (select id, title, content, time, secret_check, reply_check, no from customer_service order by no desc)) where r between ? AND ?");
				ps.setInt(1, start);
	            ps.setInt(2, start+9);
			}
			else if(opt.equals("0")) {//제목검색
				ps = con.prepareStatement("select r, id, title, content, time, secret_check, reply_check, no from (select rownum r, id, title, content, time, secret_check, reply_check, no from (select id, title, content, time, secret_check, reply_check, no from customer_service where title like ? order by no desc)) where r between ? AND ?");
				ps.setString(1, "%"+condition+"%");
				ps.setInt(2, start);
	            ps.setInt(3, start+9);
			}
			else if(opt.equals("1")) {//내용검색
				ps = con.prepareStatement("select r, id, title, content, time, secret_check, reply_check, no from (select rownum r, id, title, content, time, secret_check, reply_check, no from (select id, title, content, time, secret_check, reply_check, no from customer_service where content like ? order by no desc)) where r between ? AND ?");
				ps.setString(1, "%"+condition+"%");
				ps.setInt(2, start);
	            ps.setInt(3, start+9);
			}
			else if(opt.equals("2")) {//제목+내용검색
				ps = con.prepareStatement("select r, id, title, content, time, secret_check, reply_check, no from (select rownum r, id, title, content, time, secret_check, reply_check, no from (select id, title, content, time, secret_check, reply_check, no from customer_service where content like ? or title like ? order by no desc)) where r between ? AND ?");
				ps.setString(1, "%"+condition+"%");
				ps.setString(2, "%"+condition+"%");
				ps.setInt(3, start);
	            ps.setInt(4, start+9);
			}
			else if(opt.equals("3")) {//id검색
				ps = con.prepareStatement("select r, id, title, content, time, secret_check, reply_check, no from (select rownum r, id, title, content, time, secret_check, reply_check, no from (select id, title, content, time, secret_check, reply_check, no from customer_service where id like ? order by no desc)) where r between ? AND ?");
				ps.setString(1, "%"+condition+"%");
				ps.setInt(2, start);
	            ps.setInt(3, start+9);
			}
//			ps = con.prepareStatement("select r, id, title, content, time, secret_check, reply_check, no from (select rownum r, id, title, content, time, secret_check, reply_check, no from (select id, title, content, time, secret_check, reply_check, no from customer_service order by no desc)) where r between 10*(?-1)+1 AND ?*10");
//			ps.setInt(1, page);
//			ps.setInt(2, page);
			rs = ps.executeQuery();
			while(rs.next()) {
				CSBean bb = new CSBean();
				bb.setId(rs.getString("id"));
				bb.setTitle(rs.getString("title"));
				bb.setContent(rs.getString("content"));
				bb.setTime(rs.getString("time"));
				bb.setReply(rs.getString("reply_check"));
				bb.setSecret(rs.getString("secret_check"));
				bb.setNo(rs.getString("no"));
				list.add(bb);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Connection과 PreparedStatement 닫기
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
	
		return list;
	}
	
	// 게시글 조회
	public CSBean viewBoard(int no){
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CSBean bean = new CSBean();
		ArrayList<CSBean> list = new ArrayList<CSBean>();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			
			
			ps = con.prepareStatement("SELECT id, title, secret_check, reply_check, content, time, no FROM customer_service WHERE no = ?");
			
			ps.setInt(1, no);
			rs = ps.executeQuery();
			while(rs.next()) {
				bean.setId(rs.getString("id"));
				bean.setTitle(rs.getString("title"));
				bean.setContent(rs.getString("content"));
				bean.setTime(rs.getString("time"));
				bean.setNo(rs.getString("no"));
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Connection과 PreparedStatement 닫기
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
	
		return bean;
	}
}
