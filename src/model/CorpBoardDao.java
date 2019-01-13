package model;
//insert select	update	delete
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
//Member 관련된 작업을 Database에 연결하여 처리하는 객체
public class CorpBoardDao {
	// 게시글 등록
	public CorpBoardBean insertBoard(CorpBoardBean bb) {
		
		Connection con = null;
		PreparedStatement ps = null;
//		CorpBoardBean bb1 = new CorpBoardBean(); 
//		ResultSet rs = null;
		
		int cnt = 0;	// insert된 개수 저장 변수
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			
			ps = con.prepareStatement("INSERT INTO corp_board(corp_code, id, title, content, recommend, not_recommend, time, no, corp_name) "
					+ "VALUES(?, ?, ?, ?, ?, ?, sysdate, corp_board_seq.nextval, ?)");
			ps.setString(1, bb.getCorp_code());
			ps.setString(2, bb.getId());
			ps.setString(3, bb.getTitle());
			ps.setString(4, bb.getContent());
			ps.setString(5, bb.getRecommend());
			ps.setString(6, bb.getNot_recommend());
			ps.setString(7, bb.getCorp_name());

			cnt = ps.executeUpdate();
			
			ps = con.prepareStatement("SELECT corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM corp_board WHERE id = ? order by no desc");
			ps.setString(1, bb.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CorpBoardBean cb = new CorpBoardBean();
				cb.setCorp_code(rs.getString("corp_code"));
				cb.setId(rs.getString("id"));
				cb.setTitle(rs.getString("title"));
				cb.setContent(rs.getString("content"));
				cb.setRecommend(rs.getString("recommend"));
				cb.setNot_recommend(rs.getString("not_recommend"));
				cb.setTime(rs.getString("time"));
				cb.setNo(rs.getString("no"));
				cb.setCorp_name(rs.getString("corp_name"));
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
			ps = con.prepareStatement("select count(*) from corp_board");
		}
		else if(opt.equals("0")) { //제목으로 검색한 글 갯수
			ps = con.prepareStatement("select count(*) from corp_board where title like ?");
			ps.setString(1, '%'+condition+'%');
		}
		else if(opt.equals("1")) { //내용검색 글 갯수
			ps = con.prepareStatement("select count(*) from corp_board where content like ?");
			ps.setString(1, '%'+condition+'%');
		}
		else if(opt.equals("2")) { //제목+내용 검색
			ps = con.prepareStatement("select count(*) from corp_board where title like ? or content like ?");
			ps.setString(1, '%'+condition+'%');
			ps.setString(2, '%'+condition+'%');
		}
		else if(opt.equals("3")) { //id로 검색한 글 갯수
			ps = con.prepareStatement("select count(*) from corp_board where id like ?");
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
	// 게시판 목록 조회
	public ArrayList<CorpBoardBean> BoardMain(HashMap<String, Object> listopt){	
		Connection con = null;
		PreparedStatement ps = null;
		String opt = (String)listopt.get("opt"); //검색옵션
		String condition = (String)listopt.get("condition"); // 검색내용
        int start = (Integer)listopt.get("start"); // 현재 페이지번호
		
		ArrayList<CorpBoardBean> list = new ArrayList<CorpBoardBean>();
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			
			//ps = con.prepareStatement("SELECT r, id, title, content, recommend, not_recommend, time, no FROM (SELECT rownum r, id, title, content, recommend, not_recommend, time, no FROM board) WHERE r<11 ORDER BY r desc");
			if(opt == null) { //전체 글
				ps = con.prepareStatement("SELECT r, corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM (SELECT rownum r, corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM (SELECT corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM corp_board)) WHERE r BETWEEN ? AND ? order by r desc");
				ps.setInt(1, start);
				ps.setInt(2, start+9);
			}
			else if(opt.equals("0")) { //회사명 검색
				ps = con.prepareStatement("SELECT r, corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM (SELECT rownum r, corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM (SELECT corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM corp_board WHERE corp_name like ?)) where r BETWEEN ? AND ? order by r desc");
				ps.setString(1, "%"+condition+"%");
				ps.setInt(2, start);
				ps.setInt(3, start+9);
			}
			else if(opt.equals("1")) { //제목 검색
				ps = con.prepareStatement("SELECT r, corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM (SELECT rownum r, corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM (SELECT corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM corp_board WHERE title like ?)) where r BETWEEN ? AND ? order by r desc");
				ps.setString(1, "%"+condition+"%");
				ps.setInt(2, start);
	            ps.setInt(3, start+9);
			}
			else if(opt.equals("2")) { //내용 검색
				ps = con.prepareStatement("SELECT r, corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM (SELECT rownum r, corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM (SELECT corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM corp_board WHERE content like ?)) where r BETWEEN ? AND ? order by r desc");
				ps.setString(1, "%"+condition+"%");
				ps.setInt(2, start);
	            ps.setInt(3, start+9);
			}
			else if(opt.equals("3")) { //제목+내용 검색
				ps = con.prepareStatement("SELECT r, corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM (SELECT rownum r, corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM (SELECT corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM corp_board WHERE content like ? or title like ?)) where r BETWEEN ? AND ? order by r desc");
				ps.setString(1, "%"+condition+"%");
				ps.setString(2, "%"+condition+"%");
				ps.setInt(3, start);
	            ps.setInt(4, start+9);
			}
			else if(opt.equals("4")) { //id 검색
				ps = con.prepareStatement("SELECT r, corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM (SELECT rownum r, corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM (SELECT corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM corp_board WHERE id like ?)) where r BETWEEN ? AND ? order by r desc");
				ps.setString(1, "%"+condition+"%");
				ps.setInt(2, start);
	            ps.setInt(3, start+9);
			}
			rs = ps.executeQuery();

			while(rs.next()) {
				CorpBoardBean bb = new CorpBoardBean();
				bb.setCorp_code(rs.getString("corp_code"));
				bb.setId(rs.getString("id"));
				bb.setTitle(rs.getString("title"));
				bb.setContent(rs.getString("content"));
				bb.setRecommend(rs.getString("recommend"));
				bb.setNot_recommend(rs.getString("not_recommend"));
				bb.setTime(rs.getString("time"));
				bb.setNo(rs.getString("no"));
				bb.setCorp_name(rs.getString("corp_name"));
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
	public CorpBoardBean viewBoard(int no){
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CorpBoardBean bean = new CorpBoardBean();
		ArrayList<CorpBoardBean> list = new ArrayList<CorpBoardBean>();
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			
			//ps = con.prepareStatement("SELECT corp_code, id, title, content, recommend, not_recommend, time, no, corp_name FROM corp_board WHERE no = ?");
			  ps = con.prepareStatement("SELECT id, title, content, time, no FROM corp_board WHERE no = ?");
			ps.setInt(1, no);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				//CorpBoardBean bean = new CorpBoardBean();
				//bean.setCorp_code(rs.getString("corp_code"));
				bean.setId(rs.getString("id"));
				bean.setTitle(rs.getString("title"));
				bean.setContent(rs.getString("content"));
				//bean.setRecommend(rs.getString("recommend"));
				//bean.setNot_recommend(rs.getString("not_recommend"));
				bean.setTime(rs.getString("time"));
				bean.setNo(rs.getString("no"));
				//bean.setCorp_name(rs.getString("corp_name"));
				//list.add(bean);
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
	
	// 게시판 목록 -> 조회
	/*public CorpBoardBean viewBoard(String no){
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CorpBoardBean bb = new CorpBoardBean();
		System.out.println(no);
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			
			ps = con.prepareStatement("SELECT corp_code, id, title, content, recommend, not_recommend, time, no, corp_name "
					+ "FROM board WHERE no = ?");

			ps.setString(1, no);
			rs = ps.executeQuery();
			System.out.println(no+"2");
			if(rs.next()) {
				bb.setCorp_code(rs.getString("corp_code"));
				bb.setId(rs.getString("id"));
				bb.setTitle(rs.getString("title"));
				bb.setContent(rs.getString("content"));
				bb.setRecommend(rs.getString("recommend"));
				bb.setNot_recommend(rs.getString("not_recommend"));
				bb.setTime(rs.getString("time"));
				bb.setNo(rs.getString("no"));
				bb.setCorp_name(rs.getString("corp_name"));
				System.out.println(no+"1");
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
	}*/
}