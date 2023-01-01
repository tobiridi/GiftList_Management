package be.Jadoulle_Declercq.Servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.Jadoulle_Declercq.JavaBeans.Customer;

/**
 * Servlet implementation class InscriptionServlet
 */
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = -2166711020822680948L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, String> errorsMessage = new HashMap<>();
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(this.isValidInscription(email, password, firstname, lastname, errorsMessage)) {
			Customer newCustomer = new Customer(0, email, password, firstname, lastname);
			if(newCustomer.create()) {
				request.setAttribute("successMessage", "Compte créer avec succès !");
				doGet(request, response);
			}
			else {
				errorsMessage.put("inscriptionError", "Une erreur est survenue lors de l'inscription.");
				request.setAttribute("errorsMessage", errorsMessage);
				doGet(request, response);
			}
		}
		else {
			request.setAttribute("errorsMessage", errorsMessage);
			request.setAttribute("previousEmail", email);
			request.setAttribute("previousPassword", password);
			request.setAttribute("previousFirstname", firstname);
			request.setAttribute("previousLastname", lastname);
			doGet(request, response);
		}
	}

	private boolean isValidInscription(String userEmail, String userPassword, String userFirstname, String userLastname, HashMap<String, String> errorsMessage) {
		if(userEmail != null && userPassword != null && userFirstname != null && userLastname != null) {
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
			if(userFirstname.isBlank()) {
				errorsMessage.put("firstnameError", "Prénom non valide.");
			}
			if(userLastname.isBlank()) {
				errorsMessage.put("lastnameError", "Nom non valide.");
			}
			
			return errorsMessage.isEmpty();
		}
		
		return false;
	}
}
