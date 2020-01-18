package easyjob.control.gestione_utenti;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import easyjob.entity.*;
import easyjob.model.ManagerInviti;
import easyjob.model.ManagerUtenti;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * Questo metodo si occupa di far visualizzare i dati dell’utente prendendo dall’invio di una form, l’attributo username e 
	 * l’attributo password
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * @precondition (request.getParameter("username") != null && request.getParameter("username") != "")  && 
                     (request.getParameter("password") !=null && request.getParameter("password") != " " ) 

	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente user = new Utente();
		ManagerUtenti mu = new ManagerUtenti();
		ManagerInviti mi = new ManagerInviti();
		List<Invito> inviti = new ArrayList<Invito>();
		List<Invito> soloCinque = new ArrayList<Invito>();
		Inoccupato inoccupato = new Inoccupato();
		Azienda azienda = new Azienda();
		Amministratore admin = new Amministratore();
		Moderatore mod = new Moderatore();
		
		String redirect = "";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("Sono nella servlet");
		System.out.println("Parametri: "+ username +" " + password);
		user.setUsername(username);
		user.setPassword(password);
		
		
			try {
				if(mu.isPresent(user))
					
				{
					user = mu.logIn(username, password);
				
					/*Carichiamo il giusto utente nella sessione*/
					if(user != null) {
					if (user instanceof Inoccupato){
						inoccupato = (Inoccupato) user;
						System.out.println(inoccupato.getCognome());
						inviti = mi.visualizzaInviti(inoccupato);
						
						if(inviti.size() >= 5) {
							soloCinque = inviti.subList(0, 4);
						} else {
							soloCinque = inviti;
						}
						request.getSession().setAttribute("utenteInoccupato", inoccupato);
						request.getSession().setAttribute("autenticato",true);
						request.getSession().setAttribute("inviti", soloCinque);
						response.getWriter().write("Successo");
						redirect = "/protectedPageInoccupato.jsp";
					} 
					if(user instanceof Azienda) {
						azienda = (Azienda) user;
						if(!((Azienda) user).isBanned()) {
							System.out.println(azienda.getDescrizione());
							request.getSession().setAttribute("utenteAzienda", azienda);
							request.getSession().setAttribute("autenticato", true);
							response.getWriter().write("Successo");
							redirect = "/protectedPageAzienda.jsp";
						} else {
							redirect = "/bannedPage.jsp";
						}
					}
					if (user instanceof Moderatore){
						mod = (Moderatore) user;
						System.out.println(mod.getEmail());
						request.getSession().setAttribute("utenteModeratore", mod);
						request.getSession().setAttribute("autenticato", true);
						response.getWriter().write("Successo");
						redirect="/protectedPageModeratore.jsp";
					}if (user instanceof Amministratore){
						admin = (Amministratore) user;
						System.out.println(admin.getEmail());
						request.getSession().setAttribute("utenteAdmin", admin);
						request.getSession().setAttribute("autenticato", true);
						response.getWriter().write("Successo");
						redirect = "/protectedPageAdmin.jsp";
					}	
				}
			
				else {
					
						request.getSession().setAttribute("autenticato",false);
						response.getWriter().write("Username o password non validi");
						request.setAttribute("message","Username o password non validi");
						redirect="/login.jsp";
						System.out.println("puddi1");
					 }
				}else {
					request.getSession().setAttribute("autenticato",false);
					request.setAttribute("message","Username o password non validi");
					response.getWriter().write("Username o password non validi");
					redirect="/login.jsp";
					System.out.println("puddi2");
				}
				} catch (SQLException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute("message","Si è verificato un errore");
					redirect="/login.jsp";
					System.out.println("puddi3");
				}
			response.sendRedirect(request.getContextPath()+redirect);
}
}
