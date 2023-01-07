package be.Jadoulle_Declercq.Servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import be.Jadoulle_Declercq.JavaBeans.Gift;
import be.Jadoulle_Declercq.JavaBeans.GiftList;

/**
 * Servlet implementation class AddGiftServlet
 */
public class AddGiftServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGiftServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String idGiftList = request.getParameter("idGiftList");
		
		if(idGiftList != null) {
			session.setAttribute("idGiftList", idGiftList);
		}
		
		if(session.getAttribute("idGiftList") != null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/AddGift.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("DetailsGiftList?" + idGiftList);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		HashMap<String, String> errorsMessage = new HashMap<>();
		String giftPriority = request.getParameter("giftPriority");
		String giftName = request.getParameter("giftName");
		String giftDescription = request.getParameter("giftDescription");
		String giftPrice = request.getParameter("giftPrice");
		String giftLink = request.getParameter("giftLink");
		Part giftPicture = request.getPart("giftPicture");
		
		if(this.isValidGift(giftPriority, giftName, giftDescription, giftPrice, errorsMessage)) {
			int prio = Integer.parseInt(giftPriority);
			double averagePrice = Double.parseDouble(giftPrice);
			//cast in base64 the image file
			String picture = null;
			if(giftPicture != null) {
				InputStream stream = giftPicture.getInputStream();
				picture = Base64.getEncoder().encodeToString(stream.readAllBytes());
				stream.close();
			}
			
			GiftList list = new GiftList();
			int idList = Integer.parseInt((String) session.getAttribute("idGiftList"));
			list.setId(idList);
			
			Gift newGift = new Gift(0, prio, giftName, averagePrice, giftDescription, giftLink, picture, list);
			if(newGift.create()) {
				request.setAttribute("successMessage", "Cadeau créer avec succès !");
				doGet(request, response);
			}
			else {
				errorsMessage.put("GiftError", "Une erreur est survenue lors de la création du cadeau.");
				request.setAttribute("errorsMessage", errorsMessage);
				doGet(request, response);
			}
		}
		else {
			request.setAttribute("giftPriority", giftPriority);
			request.setAttribute("giftName", giftName);
			request.setAttribute("giftDescription", giftDescription);
			request.setAttribute("giftPrice", giftPrice);
			request.setAttribute("errorsMessage", errorsMessage);
			doGet(request, response);
		}
		
	}
	
	private boolean isValidGift(String priority, String giftName, String giftDesc, String giftPrice, HashMap<String, String> errorsMessage) {
		try {
			int prio = Integer.parseInt(priority);
			if(prio < 0) {
				errorsMessage.put("giftPriorityNumberError", "La priorité du cadeau est inférieur à 0.");
			}
		} catch (NumberFormatException e) {
			errorsMessage.put("giftPriorityError", "La priorité du cadeau n'est pas valide.");
		}
		
		try {
			double price = Double.parseDouble(giftPrice);
			if(price < 0.5 || price > 999.99) {
				errorsMessage.put("giftPriceNumberError", "Le prix du cadeau doit etre compris entre 0,5 et 999,99.");
			}
		} catch (NumberFormatException e) {
			errorsMessage.put("giftPriceError", "Le prix du cadeau n'est pas valide.");
		}
		
		if(giftName.trim().length() < 3) {
			errorsMessage.put("giftNameError", "Le nom du cadeau doit contenir au moins 3 caractères.");
		}
		
		if(giftDesc.trim().length() < 5 || giftDesc.trim().length() > 80) {
			errorsMessage.put("giftDescriptionError", "La description du cadeau doit contenir entre 5 et 80 caractères.");
		}
		
		return errorsMessage.isEmpty();
	}

}
