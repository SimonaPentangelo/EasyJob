package easyjob.control.gestione_segnalazioni_notifiche_inviti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easyjob.entity.Inoccupato;
import easyjob.entity.Invito;
import easyjob.model.ManagerInviti;

/**
 * Servlet implementation class VisualizzaInvitoServlet
 */
@WebServlet("/VisualizzaInvitoServlet")
public class VisualizzaInvitoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaInvitoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * Effettua la visualizzazione della pagina con il singolo invito.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * val==true && (requst.getSession().getAttribute("utenteInoccupato") != null)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean val = (boolean) request.getSession().getAttribute("autenticato");
		if(val && request.getSession().getAttribute("utenteInoccupato") != null) {
			int idAnnuncio = Integer.parseInt(request.getParameter("idAd"));	
			Inoccupato inocc = (Inoccupato) request.getSession().getAttribute("utenteInoccupato");
			ManagerInviti mi = new ManagerInviti();
			String redirect = "/WEB-PAGES/view/visualizzaInvito.jsp";
			Invito i = new Invito();
			
			try {
				i = mi.getInvito(idAnnuncio, inocc.getIdUser());
				request.getSession().setAttribute("invito", i);
				response.sendRedirect(request.getContextPath() + redirect);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
