package easyjob.control.gestione_bacheca;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easyjob.model.ManagerCandidature;

/**
 * Servlet implementation class CandidaturaServlet
 */
@WebServlet("/CandidaturaServlet")
public class CandidaturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ManagerCandidature manager = new ManagerCandidature();
		int idAzienda = Integer.parseInt(request.getParameter("idAz"));
		int idInoccupato = Integer.parseInt(request.getParameter("idUt"));
		String redirect= "";
		try{
			if(!(manager.isAlreadyCandidate(idInoccupato,idAzienda))){
				if(manager.candidate(idInoccupato,idAzienda))
				redirect ="/WEB-PAGES/view/SuccesfulCandidate.jsp";
			}
			else
				redirect= "/WEB-PAGES/view/ErroreCandidatura.jsp";
		}catch (Exception e){
			e.printStackTrace();
		}
		
	response.sendRedirect(request.getContextPath()+redirect);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
