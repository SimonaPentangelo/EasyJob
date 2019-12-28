package easyjob.control.gestione_bacheca;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easyjob.entity.Annuncio;
import easyjob.entity.Azienda;
import easyjob.model.ManagerAnnunci;

/**
 * Servlet implementation class VisualizzaElencoAnnunciServlet
 */
@WebServlet("/VisualizzaElencoAnnunciServlet")
public class VisualizzaElencoAnnunciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ManagerAnnunci manager = new ManagerAnnunci();
		ArrayList<Annuncio> listaAnnunciPubblicati = new ArrayList<>();
		Azienda azienda = (Azienda) request.getSession().getAttribute("utenteAzienda");
		String redirect = "";
		try{
			
			listaAnnunciPubblicati = (ArrayList<Annuncio>) manager.visualizzaElencoAnnunci(azienda);
			request.getSession().setAttribute("listaAnnunci",listaAnnunciPubblicati);
			redirect = "/WEB-PAGES/view/ElencoAnnunci.jsp";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+redirect);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
