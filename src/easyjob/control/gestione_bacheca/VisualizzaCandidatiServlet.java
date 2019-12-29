package easyjob.control.gestione_bacheca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easyjob.entity.Candidatura;
import easyjob.entity.Inoccupato;
import easyjob.model.ManagerCandidature;
import easyjob.model.ManagerUtenti;

/**
 * Servlet implementation class VisualizzaCandidatiServlet
 */
@WebServlet("/VisualizzaCandidatiServlet")
public class VisualizzaCandidatiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idAnnuncio = Integer.parseInt(request.getParameter("idAn"));
		String titoloAnnuncio = request.getParameter("tit");   // MI SERVE PER FARLO VEDERE SULLA JSP
		ManagerCandidature managerCand = new ManagerCandidature();
		ManagerUtenti managerUt = new ManagerUtenti();
		List<Candidatura> listaCandidatura = new ArrayList<>();
		List<Inoccupato> listaCandidati = new ArrayList<>();
		String redirect="/WEB-PAGES/view/ElencoCandidati.jsp";
		
		try{
			
			listaCandidatura = managerCand.visualizzaCandidatureRicevute(idAnnuncio);
			for (Candidatura c : listaCandidatura){
				int idInocc = c.getInoccupato();
				listaCandidati.add(managerUt.findInoccupato(idInocc));
			}
			request.getSession().setAttribute("idAnnuncio", idAnnuncio);
			request.getSession().setAttribute("titoloAnnuncio", titoloAnnuncio);
			request.getSession().setAttribute("listaCandidati",listaCandidati);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+redirect);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
