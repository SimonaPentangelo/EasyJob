package easyjob.control.gestione_segnalazioni_notifiche_inviti;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import easyjob.entity.Amministratore;
import easyjob.entity.Annuncio;
import easyjob.entity.Segnalazione;
import easyjob.model.ManagerAnnunci;
import easyjob.model.ManagerSegnalazioni;

/**
 * Servlet implementation class VisualizzaSegnalazioniServlet
 */
@WebServlet("/VisualizzaSegnalazioniServlet")
public class VisualizzaSegnalazioniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public VisualizzaSegnalazioniServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ManagerSegnalazioni manager = new ManagerSegnalazioni();
		String redirect = "";
		List<Segnalazione> segnalazioni = new ArrayList<>();
		Amministratore admin = new Amministratore();
		admin =(Amministratore) request.getSession().getAttribute("utenteAdmin");
		
		try{
			if(admin != null){
			segnalazioni = manager.visualizzaElencoSegnalazioni();
			request.getSession().setAttribute("segnalazioni", segnalazioni);
			redirect = "/WEB-PAGES/view/visualizzaSegnalazioni.jsp";
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+ redirect);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
