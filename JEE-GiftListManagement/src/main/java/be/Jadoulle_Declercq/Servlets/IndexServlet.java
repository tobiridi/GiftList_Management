package be.Jadoulle_Declercq.Servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = -2310207585040676841L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO : check if already have a session
		//if no session login form
		//else redirect to main page
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/Index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, String> errorsMessage = new HashMap<>();
		if(this.isValidForm(request, errorsMessage)) {
			//TODO : success redirect to main page
		}
		else {
			request.setAttribute("errorsMessage", errorsMessage);
			request.setAttribute("previousEmail", request.getParameter("email"));
			request.setAttribute("previousPassword", request.getParameter("password"));
			doGet(request, response);
		}
	}
	
	private boolean isValidForm(HttpServletRequest request, HashMap<String, String> errorsMessage) {
		String userEmail = request.getParameter("email");
		String userPassword = request.getParameter("password");
		
		if(userEmail != null && userPassword != null) {
			Pattern pattern = Pattern.compile("^[A-Z0-9._]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
			if(userEmail.isBlank()) {
				errorsMessage.put("emailEmptyError", "Email vide.");
			}
			if(!pattern.matcher(userEmail).find()) {
				errorsMessage.put("emailInvalidError", "Email non valide.");
			}
			if(userPassword.isBlank()) {
				errorsMessage.put("passwordEmptyError", "Mot de passe vide ou ne contient que des espaces.");
			}
			if(userPassword.trim().length() < 8) {
				errorsMessage.put("passwordLengthError", "Mot de passe inférieur à 8 caractères.");
			}
			return errorsMessage.isEmpty();
		}
		
		return false;
	}
}
