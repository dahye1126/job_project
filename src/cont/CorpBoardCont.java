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

import com.sun.corba.se.spi.protocol.RequestDispatcherDefault;

import model.CorpBoardBean;
import model.CorpBoardDao;
import model.MemberBean;


/**
 * Servlet implementation class Cont
 */
@WebServlet("/corpboard")
public class CorpBoardCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CorpBoardCont() {
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
		//CorpBoardDao bd = new CorpBoardDao();
		//bd.inserBoard(bb);
		
		if(code.equals("list")) {
			CorpBoardDao bd = new CorpBoardDao();
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
			//int listcount = Integer.parseInt(request.getParameter("page"));
			ArrayList<CorpBoardBean> list = bd.BoardMain(listopt);
//			request.setAttribute("ALLBOARD", list);
//			RequestDispatcher rd = request.getRequestDispatcher("corp_boardList.jsp");
//			rd.forward(request, response);

			//전체 페이지 수
			int maxPage = (int)(listcount/10.0 + 0.9);
			//시작 페이지 번호
			int startPage = (int)(spage/5.0 + 0.8) * 5 - 4;
			//마지막 페이지 번호
			int endPage = startPage + 4;
			if (endPage > maxPage) {
				endPage = maxPage;
			}
			//페이지 번호 4개 저장

			request.setAttribute("spage", spage);
			request.setAttribute("maxpage", maxPage);
			request.setAttribute("startpage", startPage);
			request.setAttribute("endpage", endPage);
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("corp_boardList.jsp");
			rd.forward(request, response);
		}

		else if(code.equals("detail")) {
			CorpBoardDao cd = new CorpBoardDao();
			int no = Integer.parseInt(request.getParameter("num"));
			CorpBoardBean bb1 = cd.viewBoard(no);
			request.setAttribute("BOARD", bb1);
			RequestDispatcher rd = request.getRequestDispatcher("corp_boardView.jsp");
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
			CorpBoardBean bb = new CorpBoardBean();
//			bb.setId(request.getParameter("i_id"));
			bb.setCorp_code("00001");
			bb.setId("test7");
			bb.setTitle(request.getParameter("i_title"));
			bb.setContent(request.getParameter("i_content"));
			bb.setRecommend("0");
			bb.setNot_recommend("0");
//			bb.setTime("");
//			bb.setNo("0");
			bb.setCorp_name(request.getParameter("i_corp_name"));
			
			CorpBoardDao bd = new CorpBoardDao();
			CorpBoardBean bb1 = bd.insertBoard(bb);
			
//			ArrayList<BoardBean> list = bd.viewBoard(bb.getNo());
			request.setAttribute("BOARD", bb1);
			RequestDispatcher rd = request.getRequestDispatcher("corp_boardView.jsp");
			rd.forward(request, response);
		}
	}
}