package easyjob.control.gestione_bacheca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easyjob.entity.Annuncio;
import easyjob.entity.Azienda;
import easyjob.model.ManagerAnnunci;

/**
 * Servlet implementation class PubblicaAnnuncioServlet
 */
@WebServlet("/PubblicaAnnuncioServlet")
public class PubblicaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Annuncio annuncio = new Annuncio();
		String redirect = "";
		ManagerAnnunci manager = new ManagerAnnunci();
		/*Prendo dalla sessione l'id dell'azienda che è loggata*/
		//Azienda azienda = (Azienda) request.getSession().getAttribute("utenteAzienda");
		
		/*Prelevo dall'azienda l'id per agganciare l'annuncio alla azienda che lo sta pubblicando*/
		//int id = azienda.getIdUser();
		/*Prelevo i parametri passati dalla form*/
		String titolo = request.getParameter("titolo");
		String desc = request.getParameter("descrizione");
		String req = request.getParameter("requisiti");
		String typeContratto = request.getParameter("tcontratto");
		String città = request.getParameter("city");
		String tag = request.getParameter("tags");
		
		/*Formatto la stringa dei tags in modo tale da gestirli come lista*/
		ArrayList<String> tags = new ArrayList<String>(Arrays.asList(tag.split(",")));
		/*Incapsulo i paremtri in un oggetto annuncio da passare al metodo del manager*/
	
		annuncio.setAzienda(1); // N.B E' FITTIZIO AL FINE DEI TEST PER IL MANAGER E DELLA SERVLET
		annuncio.setCittà(città);
		annuncio.setData("26/12/2019");
		annuncio.setTitolo(titolo);
		annuncio.setDescrizione(desc);
		annuncio.setTipoContratto(typeContratto);
		annuncio.setRequisiti(req);
		annuncio.setTags(tags);
		
		
		try{
			if(manager.pubblicaAnnuncio(annuncio))
			{
				redirect = "/WEB-PAGES/view/SuccesfulPublish.jsp";
			}
			else
				redirect = "/WEB-PAGES/view/ErrorPublish.jsp";
		}catch (Exception e){
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+redirect);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
