package cont;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class Cont
 */
@WebServlet("/corp.do")
public class CorpCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String corp_name = request.getParameter("corp_name");
		if(corp_name == null) {
			corp_name = "intermission";
		}
		CorpDao cd = new CorpDao();
		ArrayList<CorpSNFBean> corp_list = cd.findCorp(corp_name);
		String corp_code = request.getParameter("corp_code");
		if (corp_list.size() == 1 || corp_code != null) {
			CorpSNFBean cb = new CorpSNFBean();
			if (corp_code != null) {
				cb = cd.findCorp(Integer.parseInt(corp_code));
			}
			else {
				cb = corp_list.get(0);				
			}
			ArrayList<CorpSFBean> corp_status = cd.findCorp_Status(cb.getCorp_code());
			request.setAttribute("CORP", cb);
			request.setAttribute("STATUS", corp_status);
			RequestDispatcher rd = request.getRequestDispatcher("corp.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("LIST", corp_list);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
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

		String code = request.getParameter("member_code");

		if(code.equals("login")) {			
			String id = request.getParameter("loginId");

			MemberDao md = new MemberDao();
			MemberBean mb = md.getMember(id);

			if (mb.getPassword().equals(request.getParameter("loginPwd"))) {
				HttpSession hs = request.getSession();
				hs.setAttribute("MEMBERBEAN", mb);
				
				
				
				
				
				
				
				
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);				
			}
		}
		else if(code.equals("join")) {
			MemberBean mb = new MemberBean();
			mb.setName(request.getParameter("i_name"));
			mb.setBirth(request.getParameter("i_birth"));

			MemberDao md = new MemberDao();

			
		}
	}

}
