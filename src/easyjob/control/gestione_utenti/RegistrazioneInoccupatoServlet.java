package easyjob.control.gestione_utenti;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import easyjob.entity.Inoccupato;
import easyjob.model.ManagerUtenti;

/**
 * Servlet implementation class RegistrazioneInoccupatoServlet
 */
@WebServlet("/RegistrazioneInoccupatoServlet")
@MultipartConfig
public class RegistrazioneInoccupatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegistrazioneInoccupatoServlet() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Valida i campi di compilazione per la registrazione dell'utente come inoccupato.
     * 
     * @param nome oggetto di tipo <strong>String</strong> che rappresenta il campo "nome".
     * @param cognome oggetto di tipo <strong>String</strong> che rappresenta il campo "cognome".
     * @param username oggetto di tipo <strong>String</strong> che rappresenta il campo "username".
     * @param dataNascitaString oggetto di tipo <strong>String</strong> che rappresenta il campo "data di nascita".
     * @param email oggetto di tipo <strong>String</strong> che rappresenta il campo "email".
     * @param password oggetto di tipo <strong>String</strong> che rappresenta il campo "password".
     * @param confermaPassword oggetto di tipo <strong>String</strong> che rappresenta il campo "conferma password".
     * @param indirizzo oggetto di tipo <strong>String</strong> che rappresenta il campo "residenza".
     * @param citt‡Nascita oggetto di tipo <strong>String</strong> che rappresenta il campo " citt‡ di nascita".
     * @param curriculum oggetto di tipo <strong>Part</strong> che rappresenta il file pdf con il curriculum.
     * @param check oggetto di tipo <strong>Boolean</strong>
     * @return valido = true se le validazioni sono corrette. False altrimenti.
     */
    private boolean validazione(String nome, String cognome, String username, String dataNascitaString, String email, String password, String confermaPassword, String indirizzo, String citt‡Nascita, Part curriculum, boolean check) {
		boolean valido = true;
		String nomeexp = "^[A-Za-z‡ËÏÚ˘ ]{2,50}$";
		String cognomeexp = "^[A-Za-z‡ËÏÚ˘ ]{2,50}$";
		String userexp = "^[A-Za-z0-9]{5,20}$";
		String passexp = "^[A-Za-z0-9-._]{8,16}$";
		String residexp = "^[A-Za-z ]{3,6}[A-Za-z‡ËÏÚ˘ ]{2,35}[,]{1}[0-9 ]{2,5}$";
		String cittaexp = "^[A-Za-z‡ËÏÚ˘' ]{2,20}$";
		String emailexp = "^[A-Za-z0-9_.]+@[a-zA-Z.]{2,}\\.[a-zA-Z]{2,3}$";
		
		if(!Pattern.matches(nomeexp, nome)) {
			valido = false;
		}
		if(!Pattern.matches(cognomeexp, cognome)) {
			valido = false;
		}
		if(!Pattern.matches(userexp, username)){
			valido = false;
		}
		LocalDateTime dateInput = LocalDateTime.parse(dataNascitaString);
		LocalDateTime currentDate = LocalDateTime.now();
		if(!dateInput.isBefore(currentDate)) {
			valido = false;
		}
		if(!Pattern.matches(passexp, password)) {
			valido = false;
		}
		if(!password.equals(confermaPassword)) {
			valido = false;
		}
		if(!Pattern.matches(residexp, indirizzo)) {
			valido = false;
		}
		if(!Pattern.matches(cittaexp, citt‡Nascita)) {
			valido = false;
		}
		if(!Pattern.matches(emailexp, email)) {
			valido = false;
		}
		if(!check) {
			valido = false;
		}
		if(!Paths.get(curriculum.getSubmittedFileName()).getFileName().toString().substring(Paths.get(curriculum.getSubmittedFileName()).getFileName().toString().length() - 3).equals("pdf")) {
			valido = false;
		}
		long fileSizeInMB = curriculum.getSize() / 1024;

		if (fileSizeInMB > 10) {
		  valido = false;
		}
		
		return valido;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * Questo metodo si occupa di prendere i dati di input inseriti dallíinoccupatp e memorizzarli al fine di renderlo registrato
	 *  al sito.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * @precondition
	 * (request.getParameter(ìnomeî) != null) && (formato Nome ^[A-Za-z ]{2,50}$) && 
	   (request.getParameter(ìcognomeî) != null) && (formato Cognome  ^[A-Za-z ]{2,50}$) && 
       (request.getParameter(ìusernameî) != null) &&(formato Username ^[A-Za-z0-9]{5,20}$) &&
       (request.getParameter(ìpasswordî) != null) && (formato Password ^[A-Za-z0-9-._]{8,16}$) && 
       (request.getParameter(ìconferma passwordî) != null) && (request.getParameter(ìConferma passwordî).equals(request.getParameter(ìPasswordî))) && 
       (request.getParameter(ìe-mailî) != null) && (formato e-mail ^[A-Za-z0-9_.]+@[a-zA-Z.]{2,}\.[a-zA-Z]{2,3}$) &&
       (request.getParameter(ìcitt‡î) != null) && (formato Citt‡ ^[A-Za-z' ]{2,20}$) && (request.getParameter(ìIndirizzoî) != null) && (formato Indirizzo ^[A-Za-z ]{3,6}[A-Za-z ]{2,35}[,]{1}[0-9 ]{2,5}$)

	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Inoccupato inoccupato = new Inoccupato();
		ManagerUtenti mu = new ManagerUtenti();
		
		String nome = request.getParameter("nome");	
		String cognome = request.getParameter("cognome");
		String citt‡Nascita = request.getParameter("cittaNascita");
		String username = request.getParameter("username");
		String indirizzo = request.getParameter("residenza");
		String dataNascitaString = request.getParameter("dataNascita");
		String email = request.getParameter("email");	
		String password = request.getParameter("password");
		String confermaPassword = request.getParameter("confermaPassword");
		Part curriculum = request.getPart("curriculum");
		boolean check = Boolean.getBoolean(request.getParameter("trattamentoDati"));
	
		String rootFolder = "";
		String rootPath = "";
		String userPath  = "";
		
		if(!validazione(nome, cognome, username, dataNascitaString, email, password, confermaPassword, indirizzo, citt‡Nascita, curriculum, check)) {
			
		} else {
			rootFolder = "resources"; //serve a dare il nome della cartella di root per salvare i file se gia c'Ë non la crea
			rootPath = request.getServletContext().getRealPath("") + rootFolder; //costruisce la stringa contenete il percorso della root dove salviamo i file 
			userPath = rootPath + File.separator + inoccupato.getUsername(); //serve per definire la cartella dell'utente se gia esiste non viene creata
			
			inoccupato.setNome(nome);
			inoccupato.setCognome(cognome);
			inoccupato.setCitt‡(citt‡Nascita);
			inoccupato.setUsername(username);
			inoccupato.setPassword(password);
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			String dataNascita;
			try {
				dataNascita = sdf2.format(sdf1.parse(dataNascitaString));
				inoccupato.setDataNascita(dataNascita);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inoccupato.setEmail(email);
			inoccupato.setResidenza(indirizzo);
			inoccupato.setCurriculum("resources" + File.separator + inoccupato.getUsername() + File.separator + curriculum.getSubmittedFileName().replaceAll(" ", "_"));
		}
		
		try {
			if(!mu.isPresent(inoccupato)) {
				mu.registerUserInoccupato(inoccupato);
				request.setAttribute("message","Successo");
				
				File dirRoot = new File(rootPath); //cartella delle resources
				if(!dirRoot.exists())//se la cartella esiste non la crea altrimenti genera la cartella 
				{
					dirRoot.mkdirs();
				}
						
				File userDir = new File(userPath); //cartella propria dell'utente
				if(!userDir.exists())//serve a creare la cartella dell'utente
				{
					userDir.mkdir();
							
				}
		
				String cvFullPath = userPath + File.separator + curriculum.getSubmittedFileName().replaceAll(" ", "_");
				InputStream inputStream = curriculum.getInputStream();
						
				Files.copy(inputStream, Paths.get(cvFullPath), StandardCopyOption.REPLACE_EXISTING);
			}else {
				response.getWriter().write("Formato dati errati");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String redirect = "/index.jsp";
		//String responseGsonString= new Gson().toJson(redirect);
		//response.getWriter().write(responseGsonString);
		//response.sendRedirect(redirect);
		request.getRequestDispatcher(redirect).forward(request, response);
	}

}
