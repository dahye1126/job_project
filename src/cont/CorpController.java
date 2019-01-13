package cont;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CorpEvaluationBean;
import model.CorpEvaluationDao;

/**
 * Servlet implementation class CorpController
 */
@WebServlet("/corp_name.do")
public class CorpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		if (code.equals("corpname_code")) {
			System.out.println("corpname_code 실행");
			CorpEvaluationDao ced = new CorpEvaluationDao();
			ArrayList<CorpEvaluationBean> list = new ArrayList<CorpEvaluationBean>();

			list = ced.searchcorpname(request.getParameter("corp_name"));

			System.out.println(request.getParameter("corp_name"));

			request.setAttribute("CORPNAME", list);

			RequestDispatcher rd = request.getRequestDispatcher("corpname_search.jsp");
			rd.forward(request, response);
			

			

		} else if (code.equals("aaa")) {
			String code1 = request.getParameter("s_corpcode");
			String name = request.getParameter("s_corpname");

			request.setAttribute("SELECT_NAME", name);

			RequestDispatcher rd = request.getRequestDispatcher("corp_evaluation.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
