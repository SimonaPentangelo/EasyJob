package easyjob.control.gestione_bacheca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import easyjob.model.ManagerAnnunci;


/**
 * Servlet implementation class RimuoviAnnuncioServlet
 */
@WebServlet("/RimuoviAnnuncioServlet")
public class RimuoviAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ManagerAnnunci manager = new ManagerAnnunci();
		String redirect = "";
		int idDaRimuovere = Integer.parseInt(request.getParameter("idDaRimuovere"));
		System.out.println(idDaRimuovere);
		if (idDaRimuovere>=0){
			try{
				if(manager.removeAd(idDaRimuovere)){
				redirect="/WEB-PAGES/view/SuccessRemove.jsp";
			}
				else{
					System.out.println("C'è qualcosa che non va");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		response.sendRedirect(request.getContextPath()+redirect);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
