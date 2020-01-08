package easyjob.control.gestione_bacheca;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easyjob.entity.Annuncio;
import easyjob.model.ManagerAnnunci;
import easyjob.model.ManagerUtenti;

/**
 * Servlet implementation class LeggiAnnuncioServlet
 */
@WebServlet("/LeggiAnnuncioServlet")
public class LeggiAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	/**
	 *Questo metodo si occupa di fornire all’inoccupato la possibilità di visualizzare l’annuncio su cui ha cliccato
	 *
	 * request.getParameter("idAnnuncio") >=1 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManagerAnnunci manager = new ManagerAnnunci();
		ManagerUtenti mu = new ManagerUtenti();
		Annuncio annuncio = new Annuncio();
		String redirect = "";
		
		int id = Integer.parseInt(request.getParameter("idAnnuncio"));
		try{
			if(id>=0){
			annuncio = manager.searchById(id);
			annuncio.setNomeAzienda(mu.getNomeAzienda(annuncio.getAzienda()));
			request.getSession().setAttribute("annuncioSelezionato",annuncio);
			redirect ="/WEB-PAGES/view/ShowAnnuncio.jsp";
			}
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
