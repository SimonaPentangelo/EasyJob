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
import easyjob.entity.Candidatura;
import easyjob.entity.Inoccupato;
import easyjob.model.ManagerAnnunci;
import easyjob.model.ManagerCandidature;

/**
 * Servlet implementation class VisualizzaCandidatureServlet
 */
@WebServlet("/VisualizzaCandidatureServlet")
public class VisualizzaCandidatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public VisualizzaCandidatureServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManagerCandidature manager = new ManagerCandidature();
		ManagerAnnunci ma = new ManagerAnnunci();
		Inoccupato inocc = (Inoccupato) request.getSession().getAttribute("utenteInoccupato");
		System.out.println("Tizio" +inocc.getNome());
		List<Candidatura> elencoCand = new ArrayList<>();
		List<Annuncio> elencoAnn = new ArrayList<>();
		String redirect="";
		if(inocc.getIdUser()>0) {
			try {
				elencoCand = manager.visualizzaCandidatureEffettuate(inocc);
				for(int i=0;i<elencoCand.size();i++) {
					elencoAnn.add(ma.searchById(elencoCand.get(i).getAnnuncio()));
					System.out.println("Dim arrey: "+elencoAnn.size());
				}
				request.getSession().setAttribute("elencoAnn",elencoAnn);
				request.getSession().setAttribute("candidature",elencoCand);
				redirect="/WEB-PAGES/view/ElencoCandidature.jsp";
			}catch(Exception e) {
				
			}
		}else {
			redirect="/WEB-PAGES/view/ErroreElencoCandidature.jsp";
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
