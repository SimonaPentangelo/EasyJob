package easyjob.control.gestione_bacheca;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easyjob.model.ManagerCandidature;

/**
 * Servlet implementation class CandidaturaServlet
 */
@WebServlet("/CandidaturaServlet")
public class CandidaturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	/**
	 *Questo metodo si occupa di gestire la richiesta di candidatura di un inoccupato. 
	 *
	 * idAzienda >=0 && idInoccupato >=0
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ManagerCandidature manager = new ManagerCandidature();
		int idAzienda = Integer.parseInt(request.getParameter("idAz"));
		int idInoccupato = Integer.parseInt(request.getParameter("idUt"));
		String redirect= "";
		
		try{
			if(idAzienda >= 0 && idInoccupato >= 0){
			if(!(manager.isAlreadyCandidate(idInoccupato,idAzienda))){
				if(manager.candidate(idInoccupato,idAzienda)) {
					response.getWriter().write("candidato");
					redirect ="./SuccesfulCandidate.jsp";
				}else {
					response.setHeader("errorCand","Non è stato possibile effettuare la candidatura");
					redirect=  "./showAnnuncio.jsp";
					response.getWriter().write("unespected");
				}
			}else {
				response.setHeader("errorCand","Ti sei già candidato per questo annuncio");
				response.getWriter().write("già candidato");
				redirect="./showAnnuncio.jsp";
			}
		}else {
			response.setHeader("errorCand","L'id dell'utente che sta provando a candidarsi non è valido");
			response.getWriter().write("id non valido");
			redirect=  "./showAnnuncio.jsp";
		}
		
		}catch (Exception e){
			e.printStackTrace();
			response.setHeader("errorCand","Non è stato possibile effettuare la candidatura");
			redirect=  "./showAnnuncio.jsp";
		}
		
		request.getRequestDispatcher(redirect).forward(request, response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
