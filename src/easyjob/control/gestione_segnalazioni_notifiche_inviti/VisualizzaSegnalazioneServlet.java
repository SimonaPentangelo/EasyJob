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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idAzienda =Integer.parseInt(request.getParameter("idAz"));
		ManagerSegnalazioni manager = new ManagerSegnalazioni();
		Segnalazione segnalazione = new Segnalazione();
		String redirect = "";
		try{
			segnalazione  = manager.retrieveSegnByAz(idAzienda);
			request.getSession().setAttribute("segnalazione", segnalazione);
			redirect = "/WEB-PAGES/view/VisualizzaSegnalazione.jsp";
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
