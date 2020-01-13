package easyjob.control.gestione_utenti;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LogoutServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * Effettua il logout dell'utente chiudendone la sessione.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getSession().removeAttribute("autenticato");
		request.getSession().removeAttribute("utenteInoccupato");
		request.getSession().removeAttribute("utenteAzienda");
		request.getSession().removeAttribute("utenteModeratore");
		request.getSession().removeAttribute("utenteAdmin");
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
