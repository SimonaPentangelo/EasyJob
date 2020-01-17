package easyjob.control.gestione_utenti;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import easyjob.entity.Inoccupato;
import easyjob.entity.Utente;
import easyjob.model.ManagerUtenti;

/**
 * Servlet implementation class ModificaCurriculumServlet
 */
@WebServlet("/ModificaCurriculumServlet")
@MultipartConfig
public class ModificaCurriculumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ModificaCurriculumServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Questo metodo si occupa di fornire all’inoccupato la possibilità di modificare il proprio cv.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * @precondition request.getPart("curriculum") != "" && request.getPart("curriculum") != null 

	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ManagerUtenti mu = new ManagerUtenti();
		HttpSession session=request .getSession(false);
		boolean auth = (boolean) session.getAttribute("autenticato");
		String redirect = "/protectedPageInoccupato.jsp";
		
		if(auth) {
			Utente utente = (Utente) session.getAttribute("utenteInoccupato");
			if(utente instanceof Inoccupato) {
				Inoccupato inoccupato = (Inoccupato) utente;
				
				Part curriculum = request.getPart("curriculum");
				if(!Paths.get(curriculum.getSubmittedFileName()).getFileName().toString().substring(Paths.get(curriculum.getSubmittedFileName()).getFileName().toString().length() - 3).equals("pdf")) {
					response.setHeader("errorUpdate", "Il file deve essere in formato PDF.");
				}
				long fileSizeInMB = (curriculum.getSize() / 1024)/ 1024;

				if (fileSizeInMB > 10) {
					response.setHeader("errorUpdate", "La dimensione non deve superare i 10MB.");
				}
				//Codice per creare la directory dove salvare l'immagine del logo
				//BISOGNA VEDERE SE FUNZIONA!
					
				String rootFolder = "resources"; //serve a dare il nome della cartella di root per salvare i file se gia c'è non la crea
				String rootPath = request.getServletContext().getRealPath(""); //costruisce la stringa contenete il percorso della root dove salviamo i file 
				String userPath = rootPath + File.separator + rootFolder + File.separator + inoccupato.getUsername();; //serve per definire la cartella dell'utente se gia esiste non viene creata
						
				String oldCurriculum = inoccupato.getCurriculum();			
				inoccupato.setCurriculum("resources" + File.separator + inoccupato.getUsername() + File.separator + curriculum.getSubmittedFileName().replaceAll(" ", "_"));
				
				try {
					if(mu.modificaCurriculum(inoccupato.getIdUser(), "resources" + File.separator + inoccupato.getUsername() + File.separator + curriculum.getSubmittedFileName().replaceAll(" ", "_"))) {
						System.out.println("mo modifico");
						Files.deleteIfExists(Paths.get(rootPath + File.separator + oldCurriculum));
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
						inputStream.close();
						response.setHeader("successUpdate", "Modifica avvenuta con successo");
						System.out.println(response.getHeader("successUpdate") + " ciao");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.setHeader("errorUpdate", "Si è verificato un errore.");
				} finally {
					request.getRequestDispatcher(redirect).forward(request, response);
				}
			}
		}
	}
}
