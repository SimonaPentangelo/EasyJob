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

/**
 * Servlet implementation class RicercaAnnunciModeratore
 */
@WebServlet("/RicercaAnnunciModeratore")
public class RicercaAnnunciModeratore extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ManagerUtenti managerUt = new ManagerUtenti();
		ManagerAnnunci managerAn = new ManagerAnnunci();
		String dataDaCercare = request.getParameter("data");
		System.out.println(dataDaCercare);
		String redirect = "";
		List<Annuncio> annunci = new ArrayList<>();	
		List<Azienda> aziende = new ArrayList<>();
		try{
			if(dataDaCercare!= null && !dataDaCercare.equals("")){
				annunci = managerAn.filterSearch(dataDaCercare);
				
				for (Annuncio an: annunci){
					aziende.add(managerUt.findAziendaById(an.getAzienda()));
				}
				
				request.getSession().setAttribute("annunci",annunci);
				request.getSession().setAttribute("aziendeAnnunci",aziende );
				redirect="/WEB-PAGES/view/Bacheca.jsp";
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

}
