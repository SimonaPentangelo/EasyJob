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

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	/**
	 * Si occupa di effettuare l'invio dell’invito  utilizzando il form compilato dall'azienda.
	 * 
	 * @precondition titolo != null && !titolo.equals("") && !titolo.equals(" ") && titolo.length() >= 5 && titolo.length() <= 60
	 * 				 messaggio != null && !messaggio.contentEquals("") && !messaggio.contentEquals(" ") && messaggio.length() >= 10 && 
	 * 				 messaggio.length() <= 10000
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String redirect="";
		Invito invito = new Invito();
		ManagerInviti mi = new ManagerInviti();
		
		//Prendo l'azienda dalla sessione, quella che sta contattando il candidato
		
		int idAnnuncio = Integer.parseInt(request.getParameter("idAnnuncio"));
		int idInoccupato = Integer.parseInt(request.getParameter("idUtente")); //in qualche modo prendo l'id dell'inoccupato che voglio contattare
			
		invito.setAnnuncio(idAnnuncio);
		invito.setInoccupato(idInoccupato);
		
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
		try{
		
			if(mi.contattaCandidato(invito)){
				redirect="/WEB-PAGES/view/SuccessfullInvite.jsp";
			}
			else{
				redirect ="/WEB-PAGES/view/ErrorInvite.jsp";
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+redirect);
	}

}
