package easyjob.control.gestione_segnalazioni_notifiche_inviti;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easyjob.entity.Azienda;
import easyjob.entity.Inoccupato;
import easyjob.entity.Invito;
import easyjob.model.ManagerInviti;

/**
 * Servlet implementation class VisualizzaInvitiServlet
 */
@WebServlet("/VisualizzaInvitiServlet")
public class VisualizzaInvitiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public VisualizzaInvitiServlet() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Inoccupato inocc = (Inoccupato) request.getSession().getAttribute("utenteInoccupato");
		ManagerInviti mi = new ManagerInviti();
		List<Invito> result = new ArrayList<Invito>();
		List<Azienda> aziende = new ArrayList<Azienda>();
		String redirect = "/WEB-PAGES/view/visualizzaElencoInviti.jsp";
		
		try {
			result = mi.visualizzaInviti(inocc);
			request.getSession().setAttribute("elencoInviti", result);
			response.sendRedirect(request.getContextPath() + redirect);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
