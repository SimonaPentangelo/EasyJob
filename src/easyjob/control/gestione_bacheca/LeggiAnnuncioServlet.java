package easyjob.control.gestione_bacheca;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easyjob.entity.Annuncio;
import easyjob.model.ManagerAnnunci;

/**
 * Servlet implementation class LeggiAnnuncioServlet
 */
@WebServlet("/LeggiAnnuncioServlet")
public class LeggiAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManagerAnnunci manager = new ManagerAnnunci();
		Annuncio annuncio = new Annuncio();
		String redirect = "";
		
		int id = Integer.parseInt(request.getParameter("idAnnuncio"));
		try{
			annuncio = manager.searchById(id);
			request.getSession().setAttribute("annuncioSelezionato",annuncio);
			redirect ="/WEB-PAGES/view/ShowAzienda.jsp";
		} catch (Exception e){
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+redirect);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
