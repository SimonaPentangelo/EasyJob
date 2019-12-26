package easyjob.control.gestione_bacheca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easyjob.entity.Annuncio;
import easyjob.model.ManagerAnnunci;


@WebServlet("/FiltraAnnunciServlet")
public class FiltraAnnunciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ManagerAnnunci manager =  new ManagerAnnunci();
		List<Annuncio> annunci = new ArrayList<>();
		String redirect = "";
		String città = request.getParameter("advancedSearch");
		String tag = request.getParameter("tag");
		try{
			annunci = manager.searchAdAdvanced(tag, città);
			request.getSession().setAttribute("annunci", annunci);
			redirect ="/WEB-PAGES/view/Bacheca.jsp";
		} catch (Exception e){
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+redirect);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
