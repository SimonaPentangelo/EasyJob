package easyjob.control.gestione_segnalazioni_notifiche_inviti;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

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
	
	private boolean valida(String titolo, String body) {
		
		boolean valido = true;
		String expTitolo= "^[A-Za-zàèìòù .,!?']{5,60}$";
	    String expBody="^[A-Za-zàèìòù .,!?']{10,10000}$";
		
	    if (!Pattern.matches(expTitolo, titolo)) {
			valido=false;
		}
	    
	    if (!Pattern.matches(expBody, body)) {
			valido=false;
		}
	    
		return valido; 
	}

    /**
     * Default constructor. 
     */
    public SegnalazioneUtenteServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * Si occupa di effettuare l’invio della segnalazione  utilizzando il form  inserito.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * @precondition  request.getParameter("titolo")!=null && request.getParameter("titolO")!= " " &&
         formato titolo[A-Z,a-z\é\è\ò\à\ù\ì\.,!?’] {5,60} &&
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
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String localDate = dtf.format(LocalDate.now());
		
		if(titolo != null && corpo != null) {
			segnalazione.setTitolo(titolo);
			segnalazione.setCorpo(corpo);
			segnalazione.setData(localDate);
			segnalazione.setModeratore(mod);
			segnalazione.setAzienda(azienda);
		}

		try{
			System.out.println(valida(titolo, corpo));
			if(valida(titolo, corpo))
			{
				if(!manager.alreadyReported(mod, azienda)) {
					if(manager.segnalaUtente(segnalazione))
					{
						redirect = "/WEB-PAGES/view/CorrectSegnalazione.jsp";
						response.getWriter().write("segnalato");
					} else {
						redirect = "/WEB-PAGES/view/ErroreSegnalazione.jsp";
					}
				} else {
					response.getWriter().write("è già stato segnalato");
				}
			} else {
				 response.getWriter().write("formato non valido");
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
