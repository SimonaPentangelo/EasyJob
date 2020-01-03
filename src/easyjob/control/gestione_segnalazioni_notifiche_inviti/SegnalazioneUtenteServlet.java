package easyjob.control.gestione_segnalazioni_notifiche_inviti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import easyjob.entity.Segnalazione;
import easyjob.model.ManagerSegnalazioni;

/**
 * Servlet implementation class SegnalazioneUtenteServlet
 */
@WebServlet("/SegnalazioneUtenteServlet")
public class SegnalazioneUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SegnalazioneUtenteServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * Si occupa di effettuare l�invio della segnalazione  utilizzando il form  inserito.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * @precondition  request.getParameter("titolo")!=null && request.getParameter("titolO")!= " " &&
         formato titolo[A-Z,a-z\�\�\�\�\�\�\.,!?�] {5,60} &&
         request.getParameter("corpo") !=null &&
         request.getParameter("corpo") !=" " 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Segnalazione segnalazione = new Segnalazione();
		String redirect = "";
		ManagerSegnalazioni manager = new ManagerSegnalazioni();
		int azienda = Integer.parseInt(request.getParameter("azienda"));
		int mod = Integer.parseInt(request.getParameter("moderatore"));
		
		String titolo = request.getParameter("titolo");
		String corpo = request.getParameter("corpo");
		String data = request.getParameter("data");
		
		segnalazione.setTitolo(titolo);
		segnalazione.setCorpo(corpo);
		segnalazione.setData(data);
		segnalazione.setModeratore(mod);
		segnalazione.setAzienda(azienda);

		try{
			if(titolo!= null && !titolo.equals("")&& titolo.length()>=5 && titolo.length()<=60 && corpo != null && !corpo.equals("") 
					&& corpo.length()>=10 && corpo.length()<=1000)
			{
			if(manager.segnalaUtente(segnalazione))
			{
				redirect = "/WEB-PAGES/view/CorrectSegnalazione.jsp";
			}
			else
				redirect = "/WEB-PAGES/view/ErroreSegnalazione.jsp";
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		response.sendRedirect(redirect);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
