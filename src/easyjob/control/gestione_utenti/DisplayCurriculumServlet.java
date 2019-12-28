package easyjob.control.gestione_utenti;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import easyjob.entity.Inoccupato;
import easyjob.entity.Utente;
import easyjob.model.ManagerUtenti;

/**
 * Servlet implementation class DisplayCurriculumServlet
 */
@WebServlet("/DisplayCurriculumServlet")
public class DisplayCurriculumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayCurriculumServlet() {
        super();
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
		ManagerUtenti mu = new ManagerUtenti();
		HttpSession session = request .getSession(false);
		boolean auth = (boolean) session.getAttribute("autenticato");
		if(auth) {
			
			if(session.getAttribute("utenteInoccupato") != null) {
				Inoccupato inoccupato = (Inoccupato) session.getAttribute("utenteInoccupato");
				response.setContentType("application/pdf");
				String nomeFile = inoccupato.getCurriculum().substring(inoccupato.getCurriculum().lastIndexOf("\\"));
				response.setHeader("Content-Disposition", "inline; filename=" + nomeFile + ";");
				String rootFolder = "resources"; //serve a dare il nome della cartella di root per salvare i file se gia c'� non la crea
				String rootPath = request.getServletContext().getRealPath(""); //costruisce la stringa contenete il percorso della root dove salviamo i file 
				String userPath = rootPath + inoccupato.getCurriculum();
				
				OutputStream out = response.getOutputStream(); 
				try (FileInputStream in = new FileInputStream(userPath)) {
				    int content;
				    while ((content = in.read()) != -1) {
				        out.write(content);
				    }
				} catch (IOException e) {
				    e.printStackTrace();
				}
				out.close();
			} else if(session.getAttribute("utenteAzienda") != null) {
				Inoccupato inoccupato = mu.findInoccupato((Integer) request.getAttribute(/*attributo per l'id*/));
				response.setContentType("application/pdf");
				String nomeFile = inoccupato.getCurriculum().substring(inoccupato.getCurriculum().lastIndexOf("\\"));
				response.setHeader("Content-Disposition", "inline; filename=" + nomeFile + ";");
				String rootFolder = "resources"; //serve a dare il nome della cartella di root per salvare i file se gia c'� non la crea
				String rootPath = request.getServletContext().getRealPath(""); //costruisce la stringa contenete il percorso della root dove salviamo i file 
				String userPath = rootPath + inoccupato.getCurriculum();
				
				OutputStream out = response.getOutputStream(); 
				try (FileInputStream in = new FileInputStream(userPath)) {
				    int content;
				    while ((content = in.read()) != -1) {
				        out.write(content);
				    }
				} catch (IOException e) {
				    e.printStackTrace();
				}
				out.close();
			}
		}
	}
}