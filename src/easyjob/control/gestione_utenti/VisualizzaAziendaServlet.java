package easyjob.control.gestione_utenti;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easyjob.entity.Azienda;
import easyjob.model.ManagerUtenti;

/**
 * Effettua la visualizzazione della pagina di un�azienda.
 * 
 * Servlet implementation class VisualizzaAziendaServlet
 * 
 * @precondition request.getParameter(�az�) >=0.
 */
@WebServlet("/VisualizzaAziendaServlet")
public class VisualizzaAziendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String redirect ="";
		ManagerUtenti manager = new ManagerUtenti();
		Azienda azienda = new Azienda();
		int id = Integer.parseInt(request.getParameter("az"));
		if (id>=0){
		 redirect = "/ShowAzienda.jsp";
		}
		try{
			azienda = manager.findAziendaById(id);
			request.getSession().setAttribute("aziendaDaVisualizzare", azienda);
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
