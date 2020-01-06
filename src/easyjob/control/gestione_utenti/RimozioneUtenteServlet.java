package easyjob.control.gestione_utenti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easyjob.entity.Azienda;
import easyjob.model.ManagerUtenti;

/**
 * Servlet implementation class RimozioneUtenteServlet
 */
@WebServlet("/RimozioneUtenteServlet")
public class RimozioneUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RimozioneUtenteServlet() {
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
	 * Rimuove l'utente tramite id.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * @precondition request.getParameter(“az”) >= 1
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("az");
		int idAzienda = Integer.parseInt(id);
		ManagerUtenti mu = new ManagerUtenti();
		
		try {
			if(!mu.isAlreadyBanned(idAzienda)) {
				Azienda az = mu.findAziendaById(idAzienda);
				mu.deleteUser(az);
				response.getWriter().write("Rimosso");
			}else {
			response.getWriter().write("L'utente risulta già bannato");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
