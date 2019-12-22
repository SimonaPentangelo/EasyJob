package easyjob.control.gestione_segnalazioni_notifiche_inviti;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easyjob.entity.Azienda;
import easyjob.entity.Invito;
import easyjob.model.ManagerInviti;
import easyjob.model.ManagerUtenti;

/**
 * Servlet implementation class ContattaCandidatoServlet
 */
@WebServlet("/ContattaCandidatoServlet")
public class ContattaCandidatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ContattaCandidatoServlet() {
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
		
		Invito invito = new Invito();
		ManagerInviti mi = new ManagerInviti();
		
		//Prendo l'azienda dalla sessione, quella che sta contattando il candidato
		Azienda azienda = (Azienda) request.getSession().getAttribute("Azienda");
		
		int idInoccupato; //in qualche modo prendo l'id dell'inoccupato che voglio contattare
			
		invito.setAzienda(azienda.getIdUser());
		
		String titolo = request.getParameter("titolo");
		if(titolo != null && !titolo.equals("") && !titolo.equals(" ") && titolo.length() >= 5 && titolo.length() <= 60) {
			invito.setTitolo(titolo);
		} else {
			//errore nella titolo
		}
		
		String messaggio = request.getParameter("messaggio");
		if(messaggio != null && !messaggio.contentEquals("") && !messaggio.contentEquals(" ") && messaggio.length() >= 10 && messaggio.length() <= 10000) {
			invito.setCorpo(messaggio);
		} else {
			//errore nel corpo
		}
		
		invito.setInoccupato(idInoccupato);
		mi.contattaCandidato(invito);
		
		//response.sendRedirect(/*pagina di invio avvenuto con successo*/);
	}

}
