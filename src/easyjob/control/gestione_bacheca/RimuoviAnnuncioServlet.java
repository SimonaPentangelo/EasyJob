package easyjob.control.gestione_bacheca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import easyjob.model.ManagerAnnunci;


/**
 * Servlet implementation class RimuoviAnnuncioServlet
 */
@WebServlet("/RimuoviAnnuncioServlet")
public class RimuoviAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	/**
	 *Questo metodo si occupa di rimuovere l’annuncio tramite id. 
	 *
	 *request.getParameter("idDaRimuovere") >=1
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ManagerAnnunci manager = new ManagerAnnunci();
		String redirect = "";
		int idDaRimuovere = Integer.parseInt(request.getParameter("idDaRimuovere"));
		System.out.println(idDaRimuovere);
		if (idDaRimuovere>=0){
			try{
				if(manager.removeAd(idDaRimuovere)){
				response.getWriter().write("rimosso");	
				redirect="/SuccessRemove.jsp";
			}
				else{
					response.getWriter().write("unespected");
					redirect="./showAnnuncio.jsp"; //non dovrebbe mai succedere
					response.setHeader("errorRemove", "Questo annuncio è già stato rimosso");
				}
			}catch(Exception e){
				response.setHeader("errorRemove", "Si è verificato un errore");
				e.printStackTrace();
			}
		}else {
			response.setHeader("errorRemove", "Id dell'annuncio non valido");
			response.getWriter().write("id non valido");
			redirect = "./showAnnuncio.jsp"; // non dovrebbe mai succedere
		}
		response.sendRedirect(redirect);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
