package cont;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberBean;
import model.MemberDao;

/**
 * Servlet implementation class Cont
 */
@WebServlet("/member.do")
public class MemberCont extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberCont() {
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

		String code = request.getParameter("member_code");
		if(code != null && code.equals("logout")) {		
			HttpSession hs = request.getSession();
			hs.removeAttribute("MEMBERBEAN");
		}
		response.sendRedirect("index.jsp");
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
				
				
				
				
			}
			else {
				request.setAttribute("ID", id);
			}
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);			
		}
		else if(code.equals("join")) {
			MemberBean mb = new MemberBean();
			mb.setName(request.getParameter("M_Name"));
			String dd = request.getParameter("dd").length() == 1 ? "0"+request.getParameter("dd") : request.getParameter("dd");
			mb.setBirth(request.getParameter("yy") + request.getParameter("mm") + dd);
			mb.setId(request.getParameter("idcheck"));
			mb.setPassword(request.getParameter("M_Pwd"));
			mb.setEmail(request.getParameter("M_Email"));
			mb.setPhone(request.getParameter("M_Phone"));

			MemberDao md = new MemberDao();

			// �쟾�솕踰덊샇 以묐났 �뾾�쓬 => 媛��엯
			if(!md.checkMember(request.getParameter("idcheck"))) {
				
				md.insertMember(mb);
				response.sendRedirect("index.jsp");
			}

			// �쟾�솕踰덊샇 以묐났 �엳�쓬 => 媛��엯 遺덇�
			else {
				RequestDispatcher rd = request.getRequestDispatcher("memberJoinCheck.jsp");
				rd.forward(request, response);
			}
		}
	}

}
