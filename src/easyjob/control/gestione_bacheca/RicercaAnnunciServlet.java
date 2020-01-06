package easyjob.control.gestione_bacheca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import easyjob.entity.Annuncio;
import easyjob.entity.Azienda;
import easyjob.model.ManagerAnnunci;
import easyjob.model.ManagerUtenti;

/**
 * Servlet implementation class RicercaAnnunciServlet
 */
@WebServlet("/RicercaAnnunciServlet")
public class RicercaAnnunciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	/**
	 *Si occupa di effettuare la ricerca di un annuncio utilizzando la stringa inserita dall'utente nella barra di ricerca.
	 *
	 *request.getParameter("searchTag")!= null && Formato ricerca [A-Z,a-z] {1,}
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ManagerUtenti managerUt = new ManagerUtenti();
		ManagerAnnunci manager = new ManagerAnnunci();
		String redirect = "";
		String tagDaCercare = request.getParameter("searchTag");
		List<Annuncio> annunci = new ArrayList<>();
		List<Azienda> aziende = new ArrayList<>(); // Mi serve per prendere le immagini e farle visualizzare in bacheca
		boolean test = valida(tagDaCercare);
		System.out.println(test);
		try{
			if(valida(tagDaCercare)){
			annunci = manager.searchAd(tagDaCercare);
			
			for (Annuncio an : annunci){
				aziende.add(managerUt.findAziendaById(an.getAzienda()));
				
			}
			
			request.getSession().setAttribute("annunci", annunci);
			request.getSession().setAttribute("tag",tagDaCercare);
			request.getSession().setAttribute("aziendeAnnunci",aziende );
			response.getWriter().write("ok");
			redirect = "/WEB-PAGES/view/Bacheca.jsp";			
			}else {
				request.getSession().setAttribute("errorTag","Il formato del tag non può contenere solo spazi bianchi");
				response.getWriter().write("formato errato");
				redirect="/WEB-PAGES/view/index.jsp";
				
				
			}
			
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

	protected boolean valida (String tagDaCercare) {
		 boolean validato = true;
		 String regExpTag = "^[A-Za-z]{1,}$";
		 if(!Pattern.matches(regExpTag, tagDaCercare)) {
			 validato = false;
		 }
		return validato;
	}
}
