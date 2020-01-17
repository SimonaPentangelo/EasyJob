package easyjob.control.gestione_bacheca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easyjob.entity.Annuncio;
import easyjob.entity.Azienda;
import easyjob.model.ManagerAnnunci;
import easyjob.model.ManagerUtenti;


@WebServlet("/FiltraAnnunciServlet")
public class FiltraAnnunciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	/**
	 *Si occupa di effettuare la ricerca avanzata di un annuncio filtrando per la città inserita dall'utente come parametro stringa
	 *
	 *città != null && !città.equals("") && tag != null && !tag.equals("")
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ManagerUtenti managerUt = new ManagerUtenti();
		ManagerAnnunci manager =  new ManagerAnnunci();
		List<Annuncio> annunci = new ArrayList<>();
		List<Azienda> aziende = new ArrayList<>();
		String redirect = "";
		String città = request.getParameter("advancedSearch");
		String tag = request.getParameter("tag");
		try{
			
			annunci = manager.searchAdAdvanced(tag, città);
			for(Annuncio an: annunci) {
				aziende.add(managerUt.findAziendaById(an.getAzienda()));
			}
			request.getSession().setAttribute("tag",tag);
			request.getSession().setAttribute("annunci", annunci);
			request.getSession().setAttribute("aziendeAnnunci",aziende);
			request.getSession().setAttribute("cit",città);
			redirect ="/Bacheca.jsp";
			
		} catch (Exception e){
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
