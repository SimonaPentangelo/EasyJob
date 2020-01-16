package easyjob.control.gestione_segnalazioni_notifiche_inviti;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import easyjob.entity.Segnalazione;
import easyjob.model.ManagerSegnalazioni;
/**
 * Servlet implementation class VisualizzaSegnalazioneServlet
 */
@WebServlet("/VisualizzaSegnalazioneServlet")
public class VisualizzaSegnalazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaSegnalazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Effettua la visualizzazione della pagina della singola segnalazione.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * @precondition request.getParameter("idAz")>=1
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idAzienda =Integer.parseInt(request.getParameter("idAz"));
		int idModeratore =Integer.parseInt(request.getParameter("idMod"));
		System.out.println("id mod: " + idModeratore);
		ManagerSegnalazioni manager = new ManagerSegnalazioni();
		Segnalazione segnalazione = new Segnalazione();
		String redirect = "";
		try{
			segnalazione  = manager.retrieveSingleReport(idAzienda, idModeratore);
			request.getSession().setAttribute("segnalazione", segnalazione);
			redirect = "/visualizzaSegnalazione.jsp";
		}catch (Exception e){
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