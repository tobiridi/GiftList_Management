package be.Jadoulle_Declercq.Servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.Jadoulle_Declercq.JavaBeans.Customer;
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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(this.isValidLogin(email, password, errorsMessage)) {
			Customer customerConnected = Customer.login(email, password);
			
			if(customerConnected != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("customerConnected", customerConnected);
				//TODO : redirect to main page
			}
			else {
				errorsMessage.put("loginError", "Email et/ou mot de passe incorrect.");
			}
		}
		request.setAttribute("errorsMessage", errorsMessage);
		request.setAttribute("previousEmail", email);
		request.setAttribute("previousPassword", password);
		doGet(request, response);
	}
	
	private boolean isValidLogin(String userEmail, String userPassword, HashMap<String, String> errorsMessage) {
		if(userEmail != null && userPassword != null) {
			Pattern pattern = Pattern.compile("^[A-Z0-9._]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
			
			if(!pattern.matcher(userEmail).find() || userEmail.isBlank()) {
				errorsMessage.put("emailInvalidError", "Email non valide.");
			}
			if(userPassword.trim().length() < 8 || userPassword.isBlank()) {
				errorsMessage.put("passwordLengthError", "Mot de passe non valide.");
			}
			
			return errorsMessage.isEmpty();
		}
		
		return false;
	}
}
