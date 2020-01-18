package easyjob.control.gestione_bacheca;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

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

	
/**
 * Il metodo controlla se la validazione di tutti i parametri è corretta
 * 
 * @param titolo oggetto di tipo <strong>String</strong>
 * @param desc   oggetto di tipo <strong>String</strong>
 * @param req    oggetto di tipo <strong>String</strong>
 * @param città  oggetto di tipo <strong>String</strong>
 * @param tag    oggetto di tipo <strong>String</strong>
 * @param typeContratto oggetto di tipo <strong>String</strong>
 * @return valido = true se tutte le validazioni sono corrette. False altrimenti.
 */
private boolean valida(String titolo, String desc, String req, String città, String tag, String typeContratto) {
	
	boolean valido = true;
	String expTitolo= "^[A-Za-z0-9,. ]{6,50}$";
    String expDesc="^[A-Za-zàèìòù.,!?' ]{10,7000}$";
    String expReq="^[A-Za-zàèìòù0-9, ]{10,3000}$";
    String expCittà= "^[A-Za-z' ]{2,20}$";
    String expTag="^[A-Za-z, ]{4,50}$";
	
    if (!Pattern.matches(expTitolo, titolo) || titolo == null || titolo.equals("")) {
		valido=false;
		System.out.print(titolo);
	}
    
    if (!Pattern.matches(expDesc, desc) || desc == null || desc.equals("")) {
		valido=false;
		System.out.print(desc);
	}
    
    if (!Pattern.matches(expReq, req) || req == null || req.equals("")) {
		valido=false;
		System.out.print(req);
	}
    
    if (!Pattern.matches(expCittà, città) || città == null || città.equals("")) {
		valido=false;
		System.out.print(città);
	}
    
    if (!Pattern.matches(expTag, tag) || tag == null || tag.equals("")) {
		valido=false;
		System.out.print(tag);
	}
    
	if(typeContratto.equalsIgnoreCase("full-time") || typeContratto.equalsIgnoreCase("part-time") || typeContratto.equalsIgnoreCase("apprendistato") ||
			typeContratto.equalsIgnoreCase("stagista") || typeContratto.equalsIgnoreCase("tirocinio") || typeContratto.equalsIgnoreCase("progetto")) {
	}else {
		valido = false;
	}
    
	return valido;
}
	
  
	/**
	 *Si occupa di effettuare la pubblicazione di un annuncio utilizzando il form compilato dall’azienda.
	 *
	 *@precondition un opzione tra : full-time, part-time, apprendistato, stagista, tirocinio, progetto.
	 *titolo!= null && !titolo.equals("")&& titolo.length()>=6 && titolo.length()<=50 && desc != null && !desc.equals("") 
	 *&& desc.length()>=10 && desc.length()<=7000 && req!=null && !req.equals("") && req.length()>=10 && req.length()<=3000 &&
	  typeContratto != null && !typeContratto.equals("") && città!=null && !città.equals("") && tag!=null && !tag.equals("")
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Annuncio annuncio = new Annuncio();
		String redirect = "";
		ManagerAnnunci manager = new ManagerAnnunci();
		
		Azienda azienda = (Azienda) request.getSession().getAttribute("utenteAzienda");
		
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
		
		boolean validate = valida(titolo, desc, req, città, tag, typeContratto);
		System.out.println("Validate: "+validate);
	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String localDate = dtf.format(LocalDate.now());
		
		annuncio.setAzienda(azienda.getIdUser()); // N.B E' FITTIZIO AL FINE DEI TEST PER IL MANAGER E DELLA SERVLET
		annuncio.setCittà(città);
		annuncio.setData(localDate);
		annuncio.setTitolo(titolo);
		annuncio.setDescrizione(desc);
		annuncio.setTipoContratto(typeContratto);
		annuncio.setRequisiti(req);
		annuncio.setTags(tags);
		
		try {
			if(validate) {
				
				if(manager.pubblicaAnnuncio(annuncio)) {
					System.out.println("annuncio pubblicato");
					response.getWriter().write("Pubblicato");
					redirect = "/SuccesfulPublish.jsp";
				} else {
					System.out.println("primo else");
					response.getWriter().write("Errore");
					redirect="/pubblicaAnnuncio.jsp";
					request.setAttribute("errore", "Si è verificato un errore");
				}
			} else {
				System.out.println("secondo else");
				response.getWriter().write("Errore nel formato");
				request.setAttribute("errore", "Formato dati errato");
				redirect="/pubblicaAnnuncio.jsp";
			}
		} catch (Exception e){
			e.printStackTrace();
			redirect="/pubblicaAnnuncio.jsp";
			request.setAttribute("errore", "Si è verificato un errore");
		} finally {
			System.out.println("finallyyyyyy");
			response.sendRedirect(request.getContextPath()+redirect);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
