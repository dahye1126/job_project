package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardDao {
	
	// 게시글 등록
	public BoardBean insertBoard(BoardBean bb) {
		
		Connection con = null;
		PreparedStatement ps = null;
		BoardBean bb1 = new BoardBean(); 
		ResultSet rs = null;
		
		int cnt = 0;	// insert된 개수 저장 변수
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			
			ps = con.prepareStatement("INSERT INTO board(id, title, content, recommend, not_recommend, time, no) "
					+ "VALUES(?, ?, ?, ?, ?, sysdate, board_seq.nextval)");
			ps.setString(1, bb.getId());
			ps.setString(2, bb.getTitle());
			ps.setString(3, bb.getContent());
			ps.setString(4, bb.getRecommend());
			ps.setString(5, bb.getNot_recommend());
			
			cnt = ps.executeUpdate();
			
			ps = con.prepareStatement("SELECT id, title, content, recommend, not_recommend, time, no "
					+ "FROM board WHERE id = ? order by no desc");
			
			ps.setString(1, bb.getId());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bb1.setId(rs.getString("id"));
				bb1.setTitle(rs.getString("title"));
				bb1.setContent(rs.getString("content"));
				bb1.setRecommend(rs.getString("recommend"));
				bb1.setNot_recommend(rs.getString("not_recommend"));
				bb1.setTime(rs.getString("time"));
				bb1.setNo(rs.getInt("no"));
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
		return bb1;
	}
	
	//글 개수 구하는 메소드
	public int BoardCount(HashMap<String, Object> listopt) {
		Connection con = null;
		PreparedStatement ps = null;
		
		int result = 0;
		String opt = (String)listopt.get("opt");	// 검색옵션(제목, 내용, 글쓴이 등)
		String condition = (String)listopt.get("condition");	// 검색내용
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			
			if(opt == null) {	// 검색조건없음 = 전체 글 개수
				ps = con.prepareStatement("SELECT COUNT(*) FROM board");
			}
			else if(opt.equals("0")) {	// 제목으로 검색한 글 개수
				ps = con.prepareStatement("SELECT COUNT(*) FROM board WHERE title LIKE ?");
				ps.setString(1, '%'+condition+'%');
			}
			else if(opt.equals("1")) {	// 내용검색 글 개수
				ps = con.prepareStatement("SELECT COUNT(*) FROM board WHERE content LIKE ?");
				ps.setString(1, '%'+condition+'%');
			}
			else if(opt.equals("2")) {	// 제목+내용 검색
				ps = con.prepareStatement("SELECT COUNT(*) FROM board WHERE title LIKE ? OR content LIKE ?");
				ps.setString(1, '%'+condition+'%');
				ps.setString(2, '%'+condition+'%');
			}
			else if(opt.equals("3")) {	// id로 검색한 글 개수
				ps = con.prepareStatement("SELECT COUNT(*) FROM board WHERE id LIKE ?");
				ps.setString(1, '%'+condition+'%');
			}
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
	public ArrayList<BoardBean> BoardMain(HashMap<String, Object> listopt){
		Connection con = null;
		PreparedStatement ps = null;
		
		String opt = (String)listopt.get("opt");	// 검색옵션
		String condition = (String)listopt.get("condition");	// 검색내용
		int start = (Integer)listopt.get("start");	// 현재 페이지번호
		ArrayList<BoardBean> list = new ArrayList<BoardBean>();
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			
			if(opt == null) {	// 전체 글
				ps = con.prepareStatement("SELECT r, id, title, content, time, recommend, not_recommend, no FROM (SELECT rownum r, id, title, content, time, recommend, not_recommend, no FROM (SELECT id, title, content, time, recommend, not_recommend, no FROM board ORDER BY no DESC)) WHERE r BETWEEN ? AND ?");
				ps.setInt(1, start);
				ps.setInt(2, start+9);
			}
			else if(opt.equals("0")) {	// 제목검색
				ps = con.prepareStatement("SELECT r, id, title, content, time, recommend, not_recommend, no FROM (SELECT rownum r, id, title, content, time, recommend, not_recommend, no FROM (SELECT id, title, content, time, recommend, not_recommend, no FROM board WHERE title LIKE ? ORDER BY no DESC)) WHERE r BETWEEN ? AND ?");
				ps.setString(1, "%"+condition+"%");
				ps.setInt(2, start);
				ps.setInt(3, start+9);
			}
			else if(opt.equals("1")) {	// 내용검색
				ps = con.prepareStatement("SELECT r, id, title, content, time, recommend, not_recommend, no FROM (SELECT rownum r, id, title, content, time, recommend, not_recommend, no FROM (SELECT id, title, content, time, recommend, not_recommend, no FROM board WHERE content LIKE ? ORDER BY no DESC)) WHERE r BETWEEN ? AND ?");
				ps.setString(1, "%"+condition+"%");
				ps.setInt(2, start);
				ps.setInt(3, start+9);
			}
			else if(opt.equals("2")) {	// 제목+내용검색
				ps = con.prepareStatement("SELECT r, id, title, content, time, secret_check, reply_check, no FROM (SELECT rownum r, id, title, content, time, secret_check, reply_check, no FROM (SELECT id, title, content, time, secret_check, reply_check, no FROM board WHERE content LIKE ? or title LIKE ? ORDER BY no DESC)) WHERE r BETWEEN ? AND ?");
				ps.setString(1, "%"+condition+"%");
				ps.setString(2, "%"+condition+"%");
				ps.setInt(3, start);
				ps.setInt(4, start+9);
			}
			else if(opt.equals("3")) {	// id검색
				ps = con.prepareStatement("SELECT r, id, title, content, time, recommend, not_recommend, no from (SELECT rownum r, id, title, content, time, recommend, not_recommend, no FROM (SELECT id, title, content, time, recommend, not_recommend no FROM board WHERE id LIKE ? ORDER BY no DESC)) WHERE r BETWEEN ? AND ?");
				ps.setString(1, "%"+condition+"%");
				ps.setInt(2, start);
				ps.setInt(3, start+9);
			}
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardBean bb = new BoardBean();
				bb.setId(rs.getString("id"));
				bb.setTitle(rs.getString("title"));
				bb.setContent(rs.getString("content"));
				bb.setTime(rs.getString("time"));
				bb.setRecommend(rs.getString("recommend"));
				bb.setNot_recommend(rs.getString("not_recommend"));
				bb.setNo(rs.getInt("no"));
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
	
	// 게시판 목록 -> 조회
	public BoardBean viewBoard(int no){
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BoardBean bb = new BoardBean();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			
			ps = con.prepareStatement("SELECT id, title, content, recommend, not_recommend, time, no "
					+ "FROM board WHERE no = ?");
			
			ps.setInt(1, no);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bb.setId(rs.getString("id"));
				bb.setTitle(rs.getString("title"));
				bb.setContent(rs.getString("content"));
				bb.setRecommend(rs.getString("recommend"));
				bb.setNot_recommend(rs.getString("not_recommend"));
				bb.setTime(rs.getString("time"));
				bb.setNo(rs.getInt("no"));
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
	
	// 글 수정
	public int updateBoard(BoardBean bb, int no) {
		Connection con = null;
		PreparedStatement ps = null;

		int cnt = 0;	// update된 개수 저장
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			// 트랜젝션 처리 설정하기
			con.setAutoCommit(false);
			
			ps = con.prepareStatement("UPDATE board SET title = ?, content = ? WHERE no = ?");
			ps.setString(1, bb.getTitle());
			ps.setString(2, bb.getContent());
			ps.setInt(3, no);
		
			cnt = ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			// 쿼리문 에러 발생시 catch 영역
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			// Connection과 PreparedStatement 닫기
			try {
				con.commit();
				con.setAutoCommit(true);//트랜젝션 마무리 후 오토커밋 활성화
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
	
	// 글 삭제
	public int deleteBoard(int no) {
		Connection con = null;
		PreparedStatement ps = null;
		int cnt = 0;	// delete된 개수 저장
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@183.101.196.162:1521:XE", "job_project", "1234");
			// 트랜젝션 처리 설정하기
			con.setAutoCommit(false);
			
			ps = con.prepareStatement("DELETE FROM board WHERE no =?");
			ps.setInt(1, no);
			cnt = ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			// 쿼리문 에러 발생시 catch 영역
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			// Connection과 PreparedStatement 닫기
			
			try {
				con.commit();
				con.setAutoCommit(true);	// 트랜젝션 마무리 후 오토커밋 활성화
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
}
