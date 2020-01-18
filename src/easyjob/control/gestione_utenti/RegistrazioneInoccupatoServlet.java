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
     * @param cittàNascita oggetto di tipo <strong>String</strong> che rappresenta il campo " città di nascita".
     * @param curriculum oggetto di tipo <strong>Part</strong> che rappresenta il file pdf con il curriculum.
     * @param check oggetto di tipo <strong>Boolean</strong>
     * @return valido = true se le validazioni sono corrette. False altrimenti.
     */
    private boolean validazione(String nome, String cognome, String username, String dataNascitaString, String email, String password, String confermaPassword, String indirizzo, String cittàNascita, Part curriculum, String check) {
		boolean valido = true;
		String nomeexp = "^[A-Za-zàèìòù ]{2,50}$";
		String cognomeexp = "^[A-Za-zàèìòù ]{2,50}$";
		String userexp = "^[A-Za-z0-9]{5,20}$";
		String passexp = "^[A-Za-z0-9-._]{8,16}$";
		String residexp = "^[A-Za-z ]{3,6}[A-Za-zàèìòù ]{2,35}[,]{1}[0-9 ]{2,5}$";
		String cittaexp = "^[A-Za-zàèìòù' ]{2,20}$";
		String emailexp = "^[A-Za-z0-9_.]+@[a-zA-Z.]{2,}\\.[a-zA-Z]{2,3}$";
		
		if(!Pattern.matches(nomeexp, nome) || nome == null || nome.equals("")) {
			valido = false;
		}
		if(!Pattern.matches(cognomeexp, cognome) || cognome == null || cognome.equals("")) {
			valido = false;
			System.out.println(cognome);
		}
		if(!Pattern.matches(userexp, username) || username == null || username.equals("")){
			valido = false;
			System.out.println(username);
		}
		
		if(!Pattern.matches(passexp, password) || password == null || password.equals("")) {
			valido = false;
			System.out.println(password);
		} 
		if(!password.equals(confermaPassword) || confermaPassword == null || confermaPassword.equals("")) {
			valido = false;
			System.out.println(confermaPassword);
		} 
		if(!password.equals(confermaPassword)) {
			valido = false;
			System.out.println(confermaPassword + " due");
		}
		
		if(!Pattern.matches(residexp, indirizzo) || indirizzo == null || indirizzo.equals("")) {
			valido = false;
			System.out.println(indirizzo);
		}
		if(!Pattern.matches(cittaexp, cittàNascita) || cittàNascita == null || cittàNascita.equals("")) {
			valido = false;
			System.out.println(cittàNascita);
		}
		if(!Pattern.matches(emailexp, email) || email == null || email.equals("")) {
			valido = false;
			System.out.println(email);
		}
		if(check == null || !check.equalsIgnoreCase("ON")) {
			valido = false;
			System.out.println(check);
		}
		
		if(curriculum != null && curriculum.getSize() > 0) {
			if(!Paths.get(curriculum.getSubmittedFileName()).getFileName().toString().substring(Paths.get(curriculum.getSubmittedFileName()).getFileName().toString().length() - 3).equals("pdf")) {
				valido = false;
				System.out.println(curriculum.getSubmittedFileName().replaceAll(" ", "_"));
			}
		}
		long fileSizeInMB = (curriculum.getSize() / 1024)/ 1024;

		if (fileSizeInMB > 10) {
		  valido = false;
		  System.out.println(fileSizeInMB);
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
	 * Questo metodo si occupa di prendere i dati di input inseriti dall’inoccupatp e memorizzarli al fine di renderlo registrato
	 *  al sito.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * @precondition
	 * (request.getParameter(“nome”) != null) && (formato Nome ^[A-Za-z ]{2,50}$) && 
	   (request.getParameter(“cognome”) != null) && (formato Cognome  ^[A-Za-z ]{2,50}$) && 
       (request.getParameter(“username”) != null) &&(formato Username ^[A-Za-z0-9]{5,20}$) &&
       (request.getParameter(“password”) != null) && (formato Password ^[A-Za-z0-9-._]{8,16}$) && 
       (request.getParameter(“conferma password”) != null) && (request.getParameter(“Conferma password”).equals(request.getParameter(“Password”))) && 
       (request.getParameter(“e-mail”) != null) && (formato e-mail ^[A-Za-z0-9_.]+@[a-zA-Z.]{2,}\.[a-zA-Z]{2,3}$) &&
       (request.getParameter(“città”) != null) && (formato Città ^[A-Za-z' ]{2,20}$) && (request.getParameter(“Indirizzo”) != null) && (formato Indirizzo ^[A-Za-z ]{3,6}[A-Za-z ]{2,35}[,]{1}[0-9 ]{2,5}$)

	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Inoccupato inoccupato = new Inoccupato();
		ManagerUtenti mu = new ManagerUtenti();
		String redirect="";
		
		String nome = request.getParameter("nome");	
		String cognome = request.getParameter("cognome");
		String cittàNascita = request.getParameter("cittaNascita");
		String username = request.getParameter("username");
		String indirizzo = request.getParameter("residenza");
		String dataNascitaString = request.getParameter("dataNascita");
		String email = request.getParameter("email");	
		String password = request.getParameter("password");
		String confermaPassword = request.getParameter("confermaPassword");
		Part curriculum = request.getPart("curriculum");
		String check = request.getParameter("trattamentoDati");
	
		String rootFolder = "";
		String rootPath = "";
		String userPath  = "";
		
		if(!validazione(nome, cognome, username, dataNascitaString, email, password, confermaPassword, indirizzo, cittàNascita, curriculum, check)) {
			redirect = "/registrazioneInoccupato.jsp";
			request.setAttribute("errorReg", "Uno o più campi del form non sono validi!");
			response.sendRedirect(request.getContextPath()+redirect);
		} else {
			
			inoccupato.setNome(nome);
			inoccupato.setCognome(cognome);
			inoccupato.setCittà(cittàNascita);
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
			rootFolder = "resources"; //serve a dare il nome della cartella di root per salvare i file se gia c'è non la crea
			rootPath = request.getServletContext().getRealPath("") + rootFolder; //costruisce la stringa contenete il percorso della root dove salviamo i file 
			userPath = rootPath + File.separator + inoccupato.getUsername(); //serve per definire la cartella dell'utente se gia esiste non viene creata
			inoccupato.setCurriculum("resources" + File.separator + inoccupato.getUsername() + File.separator + curriculum.getSubmittedFileName().replaceAll(" ", "_"));
			
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
					redirect = "/SuccessfulReg.jsp";
					response.sendRedirect(request.getContextPath()+redirect);
				}else {
					response.getWriter().write("Formato dati errati");
					redirect = "/registrazioneInoccupato.jsp";
					request.getSession().setAttribute("errorReg", "Username o email già in uso!");
					response.sendRedirect(request.getContextPath()+redirect);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				redirect = "/registrazioneInoccupato.jsp";
				request.getSession().setAttribute("errorReg", "Errore nella registrazione");
				response.sendRedirect(request.getContextPath()+redirect);
			}
		}
	}

}
