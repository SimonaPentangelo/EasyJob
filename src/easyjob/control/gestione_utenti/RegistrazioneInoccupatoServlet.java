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


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.catalina.servlet4preview.RequestDispatcher;

import com.google.gson.Gson;

import easyjob.entity.Inoccupato;
import easyjob.model.ManagerUtenti;

/**
 * Servlet implementation class RegistrazioneInoccupatoServlet
 */
@WebServlet("/RegistrazioneInoccupatoServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10,//10MB
maxFileSize=1024*1024*100000,//100GB
maxRequestSize=1024*1024*100000)//100GB per la dimensione dei file
public class RegistrazioneInoccupatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegistrazioneInoccupatoServlet() {
        // TODO Auto-generated constructor stub
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
		if(nome != null && !nome.equals("") && !nome.equals(" ") && nome.length() >= 2 && nome.length() <= 50) {
				inoccupato.setNome(nome);
		} else {
			//errore nel nome 
		}
		
		String cognome = request.getParameter("cognome");
		if(cognome != null && !nome.equals("") && !cognome.equals(" ") && cognome.length() >= 2 && cognome.length() <= 50) {
				inoccupato.setCognome(cognome);
		} else {
			//errore nel cognome
		}
		
		String citt‡Nascita = request.getParameter("cittaNascita");
		if(citt‡Nascita != null && !citt‡Nascita.equals("") && !citt‡Nascita.equals(" ") && citt‡Nascita.length() >= 2 && citt‡Nascita.length() <= 20) {
				inoccupato.setCitt‡(citt‡Nascita);
				System.out.println(inoccupato.getCitt‡());
		} else {
			//errore nella citt‡
		}
	
		String username = request.getParameter("username");
		if(username != null && !username.equals("") && !username.contentEquals(" ") && username.length() >= 5 && username.length() <= 20) {
			inoccupato.setUsername(username);
		} else {
			//errore nell'username
		}
		
		String indirizzo = request.getParameter("residenza");
		if(indirizzo != null && !indirizzo.equals("") && !indirizzo.equals(" ") && indirizzo.length() >= 6 && indirizzo.length() <= 30) {
			inoccupato.setResidenza(indirizzo);
		} else {
			//errore nell'indirizzo
		}
		
		String dataNascitaString = request.getParameter("dataNascita");
		if(dataNascitaString != null) {
			System.out.println(dataNascitaString);
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			try {	
				String dataNascita = sdf2.format(sdf1.parse(dataNascitaString));
				inoccupato.setDataNascita(dataNascita);
			
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} else {
			//errore data
		}
		
		String email = request.getParameter("email");
		if(email != null && !email.contentEquals("") && !email.contentEquals(" ")) {
			inoccupato.setEmail(email);
		}
		
		String password = request.getParameter("password");
		String confermaPassword = request.getParameter("confermaPassword");
		if(password != null && !password.contentEquals("") && !password.contentEquals(" ") && password.length() >= 8 && password.length() <= 16) {
			if(confermaPassword != null && !confermaPassword.contentEquals("") && !confermaPassword.contentEquals(" ") && confermaPassword.length() >= 8 && confermaPassword.length() <= 16)
			{
				if(password.equals(confermaPassword)) {
					inoccupato.setPassword(password);
				} else {
					//le due password non corrispondono
				}
			} else {
				//errore nella password
			}
		}
		

		Part curriculum = request.getPart("curriculum");
		if(curriculum == null) {
			//no CV
		} else if(curriculum.getSize() == 0) {
			//non c'Ë il curriculum
		}
		
		//Codice per creare la directory dove salvare l'immagine del logo
		//BISOGNA VEDERE SE FUNZIONA!
			
		String rootFolder = "resources"; //serve a dare il nome della cartella di root per salvare i file se gia c'Ë non la crea
		String rootPath = request.getServletContext().getRealPath("") + rootFolder; //costruisce la stringa contenete il percorso della root dove salviamo i file 
		String userPath = rootPath + File.separator + inoccupato.getUsername(); //serve per definire la cartella dell'utente se gia esiste non viene creata
				
		inoccupato.setCurriculum("resources" + File.separator + inoccupato.getUsername() + File.separator + curriculum.getSubmittedFileName().replaceAll(" ", "_"));
		System.out.println(inoccupato.getCurriculum());
		System.out.println(request.getParameter("trattamentoDati"));
		if(request.getParameter("trattamentoDati") == null) {
			//deve fare il check
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
		String redirect = "/WEB-PAGES/view/index.jsp";
		//String responseGsonString= new Gson().toJson(redirect);
		//response.getWriter().write(responseGsonString);
		//response.sendRedirect(redirect);
		request.getRequestDispatcher(redirect).forward(request, response);
	}

}
