package cont;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardBean;
import model.BoardDao;

/**
 * Servlet implementation class Cont
 */
@WebServlet("/board")
public class BoardCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCont() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		String code = request.getParameter("board_code");
		BoardDao bd = new BoardDao();
		
		if(code.equals("list")) {
			int spage = 1;
			String page = request.getParameter("page");
			if(page !=null) {
				spage = Integer.parseInt(page);
			}
			String opt = request.getParameter("opt");
			String condition = request.getParameter("condition");
			HashMap<String, Object> listopt = new HashMap<String, Object>();
			listopt.put("opt", opt);
			listopt.put("condition", condition);
			listopt.put("start", spage*10-9);
			int listcount = bd.BoardCount(listopt);
			ArrayList<BoardBean> list = bd.BoardMain(listopt);
			
			//전체 페이지 수
			int maxPage = (int)(listcount/10.0 + 0.9);
			//시작 페이지 번호
			int startPage = (int)(spage/5.0 + 0.8) * 5 - 4;
			//마지막 페이지 번호
			int endPage = startPage + 4;
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			//페이지 번호 4개 저장
			request.setAttribute("spage", spage);
			request.setAttribute("maxpage", maxPage);
			request.setAttribute("startpage", startPage);
			request.setAttribute("endpage", endPage);
			request.setAttribute("ALLBOARD", list);
			RequestDispatcher rd = request.getRequestDispatcher("boardList.jsp");
			rd.forward(request, response);
		}

		else if(code.equals("detail")) {	// 글 상세보기
			BoardBean bb = new BoardBean();

			int no = Integer.parseInt(request.getParameter("num"));

			bb = bd.viewBoard(no);

			request.setAttribute("BOARD", bb);
			RequestDispatcher rd = request.getRequestDispatcher("boardView.jsp");
			rd.forward(request, response);
		}
		
		else if(code.equals("delete")) {	// 글 삭제
			bd = new BoardDao();
			int no = Integer.parseInt(request.getParameter("num"));
			
			bd.deleteBoard(no);
			
			int spage = 1;
			String page = request.getParameter("page");
			if(page !=null) {
				spage = Integer.parseInt(page);
			}
			String opt = request.getParameter("opt");
			String condition = request.getParameter("condition");
			HashMap<String, Object> listopt = new HashMap<String, Object>();
			listopt.put("opt", opt);
			listopt.put("condition", condition);
			listopt.put("start", spage*10-9);
			int listcount = bd.BoardCount(listopt);
			ArrayList<BoardBean> list = bd.BoardMain(listopt);
			
			//전체 페이지 수
			int maxPage = (int)(listcount/10.0 + 0.9);
			//시작 페이지 번호
			int startPage = (int)(spage/5.0 + 0.8) * 5 - 4;
			//마지막 페이지 번호
			int endPage = startPage + 4;
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			//페이지 번호 4개 저장
			request.setAttribute("spage", spage);
			request.setAttribute("maxpage", maxPage);
			request.setAttribute("startpage", startPage);
			request.setAttribute("endpage", endPage);
			request.setAttribute("ALLBOARD", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("boardList.jsp");
			rd.forward(request, response);
		}
		
		else if(code.equals("modify")) {	// 글 수정
			bd = new BoardDao();
			BoardBean bb = new BoardBean();
			
			int no = Integer.parseInt(request.getParameter("num"));
			bb = bd.viewBoard(no);
			
			request.setAttribute("BOARD", bb);
			RequestDispatcher rd = request.getRequestDispatcher("boardModify.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String code = request.getParameter("board_code");
		
		if(code.equals("insert")) {	// 게시글 등록 -> 조회
			BoardBean bb = new BoardBean();
			//bb.setId(request.getParameter("i_id"));
			bb.setId("test2");
			bb.setTitle(request.getParameter("i_title"));
			bb.setContent(request.getParameter("i_content"));
			bb.setRecommend("0");
			bb.setNot_recommend("0");
			
			BoardDao bd = new BoardDao();
			BoardBean bb1 = bd.insertBoard(bb);
			
			request.setAttribute("BOARD", bb1);
			RequestDispatcher rd = request.getRequestDispatcher("boardView.jsp");
			rd.forward(request, response);
		}
		
		else if(code.equals("modify2")) {	// 글 수정
			BoardDao bd = new BoardDao();
			BoardBean bb = new BoardBean();
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			bb.setTitle(request.getParameter("mod_title"));
			bb.setContent(request.getParameter("mod_content"));
			bd.updateBoard(bb, no);
			
			bb = bd.viewBoard(no);

			request.setAttribute("BOARD", bb);
			RequestDispatcher rd = request.getRequestDispatcher("boardView.jsp");
			rd.forward(request, response);
		}
	}
}
