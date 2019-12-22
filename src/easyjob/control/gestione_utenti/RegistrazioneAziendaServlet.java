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
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import easyjob.entity.Azienda;
import easyjob.model.ManagerUtenti;

/**
 * Servlet implementation class RegistrazioneAziendaServlet
 */
@WebServlet("/RegistrazioneAziendaServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10,//10MB
maxFileSize=1024*1024*100000,//100GB
maxRequestSize=1024*1024*100000)//100GB per la dimensione dei file

public class RegistrazioneAziendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegistrazioneAziendaServlet() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Azienda azienda = new Azienda();
		ManagerUtenti mu = new ManagerUtenti();
		
		String nomeAzienda = request.getParameter("nomeAzienda");
		if(nomeAzienda != null && !nomeAzienda.equals("") && !nomeAzienda.equals(" ") && nomeAzienda.length() >= 6 && nomeAzienda.length() <= 30) {
				azienda.setNomeAzienda(nomeAzienda);
		} else {
			//errore nel nome dell'aziendas
		}
		
		Part logoAzienda = request.getPart("logoAzienda");
		if(logoAzienda.getSize() == 0) {
			//non c'� il logo 
		}
		
		String partitaIVA = request.getParameter("partitaIVA");
		if(partitaIVA != null && !partitaIVA.equals("") && !partitaIVA.equals(" ") && partitaIVA.length() == 11) {
			azienda.setPartitaIVA(partitaIVA);
		} else {
			//errore nella partita iva
		}
		
		String username = request.getParameter("username");
		if(username != null && !username.equals("") && !username.contentEquals(" ") && username.length() >= 5 && username.length() <= 20) {
			azienda.setUsername(username);
		} else {
			//errore nell'username
		}
		
		String indirizzo = request.getParameter("IndirizzoSede");
		if(indirizzo != null && !indirizzo.equals("") && !indirizzo.contentEquals(" ") && indirizzo.length() >= 6 && indirizzo.length() <= 30) {
			azienda.setIndirizzoSede(indirizzo);
		} else {
			//errore nell'indirizzo
		}
		
		String dataFondazioneString = request.getParameter("dataFondazione");
		SimpleDateFormat sdf = new SimpleDateFormat("gg/mm/yyyy");
		try {
			Date dataFondazione = sdf.parse(dataFondazioneString);
			azienda.setDataFondazione(dataFondazioneString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String descrizione = request.getParameter("descrizione");
		if(descrizione != null && !descrizione.equals("") && !descrizione.equals(" ") && descrizione.length() <= 500) {
			azienda.setDescrizione(descrizione);
		} else {
			//errore nella descrizione
		}
		
		String numeroDipendentiString = request.getParameter("numeroDipendenti");
		if(numeroDipendentiString != null && !numeroDipendentiString.contentEquals("") && !numeroDipendentiString.contentEquals(" ")) {
			try {
				azienda.setNumeroDipendenti(Integer.parseInt(numeroDipendentiString));
			} catch(NumberFormatException e) {
				//non � stato passato un numero in input
			}
		}
		
		String email = request.getParameter("email");
		if(email != null && !email.contentEquals("") && !email.contentEquals(" ")) {
			azienda.setEmail(email);
		}
		
		String password = request.getParameter("password");
		String confermaPassword = request.getParameter("confermaPassword");
	
		if(password != null && !password.contentEquals("") && !password.contentEquals(" ") && password.length() >= 8 && password.length() <= 16) {
			if(confermaPassword != null && !confermaPassword.contentEquals("") && !confermaPassword.contentEquals(" ") && confermaPassword.length() >= 8 && confermaPassword.length() <= 16)
			{
				if(password.equals(confermaPassword)) {
					azienda.setPassword(password);
				} else {
					//le due password non corrispondono
				}
			} else {
				//errore nella password
			}
		}
		
		azienda.setBanned(false);
		
		if(request.getParameter("trattamentoDati") == null) {
			//deve fare il check
		}
		
		//Codice per creare la directory dove salvare l'immagine del logo
		//BISOGNA VEDERE SE FUNZIONA!
		
		String rootFolder = "resources"; //serve a dare il nome della cartella di root per salvare i file se gia c'� non la crea
		String rootPath = request.getServletContext().getRealPath("") + rootFolder; //costruisce la stringa contenete il percorso della root dove salviamo i file 
		String userPath = rootPath + File.separator + azienda.getUsername();; //serve per definire la cartella dell'utente se gia esiste non viene creata
		String userImagePath = userPath + File.separator + "image";//crea la cartella dove verr� inserita l'immagine del logo
		
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

		File userImagesDir = new File(userImagePath);
		if(!userImagesDir.exists())//serve a creare la cartella immagini dell'utente
		{
			userImagesDir.mkdir();
		}
		
		String imageFullPath = userImagePath + logoAzienda.getSubmittedFileName().replaceAll(" ", "_");
		InputStream inputStream = logoAzienda.getInputStream();
		
		Files.copy(inputStream, Paths.get(imageFullPath), StandardCopyOption.REPLACE_EXISTING);
		azienda.setLogoAzienda(imageFullPath);
		inputStream.close();
		
		try {
			if(!mu.isPresent(azienda)) {
				mu.registerUserAzienda(azienda);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//response.sendRedirect(/*pagina di registrazione avvenuta con successo*/);
	}

}
