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

import model.CSBean;
import model.CSDao;
import model.MemberBean;


@WebServlet("/csboard")
public class CScont extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String code = request.getParameter("board_code");
		
		if(code.equals("list")) {
		CSDao bd = new CSDao();
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
		ArrayList<CSBean> list = bd.BoardMain(listopt);
		
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
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("cslist.jsp");
		rd.forward(request, response);
		}
		//String code = request.getParameter("board_code");
		else if(code.equals("detail")) {
			
			CSDao cd = new CSDao();
			int no = Integer.parseInt(request.getParameter("num"));
			CSBean cb = cd.viewBoard(no);
			request.setAttribute("csb", cb);
			RequestDispatcher rd2 = request.getRequestDispatcher("csview.jsp");
			//System.out.println(request.getAttribute("csb"));
			rd2.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String code = request.getParameter("board_code");
		
		if(code.equals("insert")) {	// 게시글 등록, 조회
			CSBean bb = new CSBean();
			//bb.setId(request.getParameter("i_id"));
			bb.setId("test3");
			bb.setTitle(request.getParameter("i_title"));
			bb.setContent(request.getParameter("i_content"));
			bb.setTime("");
			//bb.setNo("0");
			
			CSDao bd = new CSDao();
			CSBean cb = bd.inserBoard(bb);
			
			request.setAttribute("csb", cb);
			RequestDispatcher rd = request.getRequestDispatcher("csview.jsp");
			rd.forward(request, response);
		}
		
		
		
	}

}
