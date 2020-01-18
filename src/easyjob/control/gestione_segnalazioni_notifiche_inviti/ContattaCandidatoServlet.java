package easyjob.control.gestione_segnalazioni_notifiche_inviti;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

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

		System.out.println("id annuncio = " + request.getParameter("idAnnuncio"));
		int idAnnuncio = Integer.parseInt(request.getParameter("idAnnuncio"));
		int idInoccupato = Integer.parseInt(request.getParameter("idUtente"));
		Azienda aziendaInSessione = (Azienda) request.getSession().getAttribute("utenteAzienda");
		int idAzienda = aziendaInSessione.getIdUser(); //in qualche modo prendo l'id dell'inoccupato che voglio contattare
		String titolo = request.getParameter("titolo");
		String messaggio = request.getParameter("messaggio");
		if(validazione(idAnnuncio,idInoccupato,titolo,messaggio,idAzienda)) {
			invito.setAnnuncio(idAnnuncio);
			invito.setInoccupato(idInoccupato);
			invito.setTitolo(titolo);
			invito.setCorpo(messaggio);
			invito.setAzienda(idAzienda);
			try {
			
				if(mi.contattaCandidato(invito)){
					response.getWriter().write("invitato");
					redirect="/SuccessfullInvite.jsp";
					response.sendRedirect(request.getContextPath()+redirect);
				} else {
					System.out.println("primo else");
					response.getWriter().write("già invitato");
					redirect ="/contattaCandidato.jsp?idUt=" + idInoccupato + "&idAn=" + idAnnuncio;
					request.getSession().setAttribute("errore", "Hai già contattato questo candidato per quest'annuncio!");
					response.sendRedirect(request.getContextPath()+redirect);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("secondo else");
			request.getSession().setAttribute("errore", "Formato non valido");
					response.getWriter().write("formato non valido");
					redirect ="/contattaCandidato.jsp?idUt=" + idInoccupato + "&idAn=" + idAnnuncio;
					response.sendRedirect(request.getContextPath()+redirect);
		}
	}

	/**
	 * @param idAn oggetto di tipo <strong>int</strong> che rappresenta l'identificativo dell'annuncio.
	 * @param idUt oggetto di tipo <strong>int</strong> che rappresenta l'identificativo dell'utente.
	 * @param tit  oggetto di tipo <strong>String</strong> che descrive il titolo del messaggio.
	 * @param msg  oggetto di tipo <strong>String</strong> che descrive il corpo del messaggio.
	 * @param idAzienda oggetto di tipo <strong>String</strong> che rappresenta l'identificativo dell'azienda.
	 * @return valido = true se tutte le validazioni sono corrette. False altrimenti.
	 */
	private boolean validazione (int idAn,int idUt,String tit,String msg,int idAzienda) {
		boolean valido = true;
		String expTit= "^[A-Za-zàèìòù .,!?']{5,60}$";
		String expMsg="^[A-Za-zàèìòù .,!?']{10,10000}$";
		
		if(idAn<1) {
			valido = false;
		}
		if(idUt<1) {
			valido = false;
		}
		if(!Pattern.matches(expTit, tit) || tit == null || tit.equals("")){
			valido = false;
		}
		if(!Pattern.matches(expMsg, msg) || msg == null || msg.equals("")) {
			valido = false;
		}
		if(idAzienda<1) {
			valido=false;
		}
		System.out.println("print valido:"+ valido);
		return valido;
	}
}
