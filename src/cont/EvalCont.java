package cont;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CorpEvaluationBean;
import model.CorpEvaluationDao;
import model.MemberBean;
import model.MemberDao;

@WebServlet("/evaluation.do")
public class EvalCont extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MainController doget 실행");
		response.sendRedirect("corp_evaluation.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		

		HttpSession hs = request.getSession();	
		MemberBean mb = (MemberBean)hs.getAttribute("MEMBERBEAN");	
		String id = mb.getId();
		
		System.out.println("id : " + id);//////////////////////////////////
		

		String code = request.getParameter("code");

		if (code.equals("corp_code")) {
			System.out.println("corp_code 실행");////////////////
			CorpEvaluationBean ceb = new CorpEvaluationBean();
			CorpEvaluationDao ced = new CorpEvaluationDao();

			ceb = ced.selectCorpEvaluation(request.getParameter("corp_name"));

			if (ceb.getCorp_name() == null) {
				System.out.println("if 실행");////////////////
				System.out.println("ceb.getCorp_name()" + ceb.getCorp_name());
				System.out.println("request.getParameter(\"corp_name\")" + request.getParameter("corp_name"));
				
				
				int cnt = ced.insertCorporation(request.getParameter("corp_name"));
				
				ceb = ced.selectCorpEvaluation(request.getParameter("corp_name"));
				
				
				if(cnt==1) {
					
					ceb.setOccupation1(request.getParameter("occupation1"));
					ceb.setOccupation2(request.getParameter("occupation2"));
					ceb.setPosition(request.getParameter("position"));
					ceb.setEducation_level(request.getParameter("education_level"));
					ceb.setCareer(request.getParameter("career"));
					ceb.setAnnual_income(request.getParameter("annual_income"));
					ceb.setWork_type(request.getParameter("work_type"));
					ceb.setPromotion(request.getParameter("promotion_score"));
					ceb.setWelfare(request.getParameter("welfare_score"));
					ceb.setBalance(request.getParameter("balance_score"));
					ceb.setCulture(request.getParameter("culture_score"));
					ceb.setManagement(request.getParameter("management_score"));
					
					ced.insertCorpEvaluation1(ceb, id);
					
				}

			} else {
				System.out.println("else 실행");////////////////
				
				ceb.setOccupation1(request.getParameter("occupation1"));
				ceb.setOccupation2(request.getParameter("occupation2"));
				ceb.setPosition(request.getParameter("position"));
				ceb.setEducation_level(request.getParameter("education_level"));
				ceb.setCareer(request.getParameter("career"));
				ceb.setAnnual_income(request.getParameter("annual_income"));
				ceb.setWork_type(request.getParameter("work_type"));
				ceb.setPromotion(request.getParameter("promotion_score"));
				ceb.setWelfare(request.getParameter("welfare_score"));
				ceb.setBalance(request.getParameter("balance_score"));
				ceb.setCulture(request.getParameter("culture_score"));
				ceb.setManagement(request.getParameter("management_score"));

				ced.insertCorpEvaluation(ceb, id);

			}
			
			response.sendRedirect("index.jsp");

		}

	}

}
