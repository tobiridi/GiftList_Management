package be.Jadoulle_Declercq.Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.Jadoulle_Declercq.JavaBeans.Customer;
import be.Jadoulle_Declercq.JavaBeans.GiftList;

/**
 * Servlet implementation class GiftListServlet
 */
public class GiftListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiftListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/AddGiftList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, String> errorsMessage = new HashMap<>();
		LocalDate giftListDeadLine = null;
		String giftListType = request.getParameter("giftListType");
		String giftListDeadLineParam = request.getParameter("giftListDeadLine");
		
		if(giftListType != null && giftListDeadLineParam != null) {
			try {
				if(giftListDeadLineParam != "" && giftListDeadLineParam != null) {
					giftListDeadLine = LocalDate.parse(giftListDeadLineParam, DateTimeFormatter.ISO_LOCAL_DATE);					
				}
				
				if(this.isValidGiftList(giftListType, giftListDeadLine, errorsMessage)) {
					HttpSession session = request.getSession(false);
					Customer customerLog = (Customer) session.getAttribute("customerLog");
					GiftList newGiftList = new GiftList(0, giftListType, true, giftListDeadLine, customerLog);
					if(newGiftList.create()) {
						customerLog.addGiftList(newGiftList);
						request.setAttribute("successMessage", "Liste créer avec succès !");
						doGet(request, response);
					}
					else {
						errorsMessage.put("GiftListError", "Une erreur est survenue lors de la création de la liste.");
						request.setAttribute("errorsMessage", errorsMessage);
						doGet(request, response);
					}
				}
				else {
					request.setAttribute("errorsMessage", errorsMessage);
					request.setAttribute("previousGiftListType", giftListType);
					request.setAttribute("previousGiftListDeadLine", giftListDeadLineParam);
					doGet(request, response);
				}
				
			} catch (DateTimeParseException e) {
				e.printStackTrace();
				errorsMessage.put("dateError", "date non valide.");
				request.setAttribute("errorsMessage", errorsMessage);
				doGet(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				doGet(request, response);
			}
		}
		else {
			request.setAttribute("previousGiftListType", giftListType);
			request.setAttribute("previousGiftListDeadLine", giftListDeadLineParam);
			doGet(request, response);
		}
	}
	
	private boolean isValidGiftList(String giftListType, LocalDate giftListDeadLine, HashMap<String, String> errorsMessage) {
		LocalDate dateNow = LocalDate.now();
		
		if(giftListType.trim().length() < 3) {
			errorsMessage.put("giftListTypeError", "Le type de la liste doit contenir au moins 3 caractères.");
		}
		if(giftListDeadLine != null) {
			if(giftListDeadLine.isBefore(dateNow) || giftListDeadLine.isEqual(dateNow)) {
				errorsMessage.put("giftListDeadLineError", "La date doit être après le \""+ dateNow.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\".");
			}
		}
		
		return errorsMessage.isEmpty();
	}

}
