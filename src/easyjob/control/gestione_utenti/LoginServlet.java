package easyjob.control.gestione_utenti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import org.apache.catalina.Manager;
import org.eclipse.jdt.internal.compiler.ast.AND_AND_Expression;

import easyjob.entity.Utente;
import easyjob.model.ManagerUtenti;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession newSession = request.getSession(true);
		
		Utente user = new Utente();
		ManagerUtenti mu = new ManagerUtenti();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		user.setUsername(username);
		user.setPassword(password);
		
		if(username!=null && !username.equals("") && password!=null && password.equals(""))
		{
		
			try {
				if(mu.isPresent(user))
				{
					user = mu.logIn(username, password);
					
				}
			
				else {
						//errore
					 }
			
				} catch (SQLException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
										  }
			
			try {
				if(!mu.isAlreadyBanned(user.getIdUser()))
				{
					
					newSession.setAttribute("currentSessionUser", user);
					newSession.setAttribute("accessoEffettuato", true);
					request.setAttribute("loginSuccess", true);
					response.sendRedirect(/*Path*/"");
				}
				else { 
					//errore di ban
				}
			}
			     catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }	
			else {
			
				request.setAttribute("loginFailed", true);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(/*path da aggiungere*/"");
				dispatcher.forward(request, response);
			}	
		
	}

}
