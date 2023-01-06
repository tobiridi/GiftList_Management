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
 * Servlet implementation class ModifyGiftListServlet
 */
public class ModifyGiftListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyGiftListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idGiftList = request.getParameter("id");
			if (idGiftList != null) {
				int idSearch = Integer.parseInt(idGiftList);
				HttpSession session = request.getSession(false);
				Customer customerLog = (Customer) session.getAttribute("customerLog");
				GiftList modifyList = customerLog.getGiftList().stream().filter(gl -> gl.getId() == idSearch).findFirst().orElse(null);

				if (modifyList != null) {
					session.setAttribute("modifyList", modifyList);
				}

				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/ModifyGiftList.jsp");
				dispatcher.forward(request, response);
			}
			else if (request.getAttribute("errorsMessage") != null) {
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/ModifyGiftList.jsp");
				dispatcher.forward(request, response);
			}
			else {
				response.sendRedirect("MainPage");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("MainPage");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, String> errorsMessage = new HashMap<>();
		HttpSession session = request.getSession(false);
		GiftList modifyList = (GiftList) session.getAttribute("modifyList");
		
		String giftListType = (String) request.getParameter("giftListType");
		String activation = (String) request.getParameter("activation");
		String giftListDeadLineParam = (String) request.getParameter("giftListDeadLine");
		LocalDate giftListDeadLine = null;
		
		if(modifyList != null && giftListType != null) {
			try {
				if(giftListDeadLineParam != "" && giftListDeadLineParam != null) {
					giftListDeadLine = LocalDate.parse(giftListDeadLineParam, DateTimeFormatter.ISO_LOCAL_DATE);					
				}
				
				if(this.isValidGiftList(giftListType, giftListDeadLine, errorsMessage)) {
					modifyList.setType(giftListType);
					if(activation == null && giftListDeadLine == null) {
						//disable with checkBox activation
						modifyList.setActive(false);
					}
					else {
						//active with deadLine date or checkBox activation
						modifyList.setActive(true);
					}
					modifyList.setDeadLine(giftListDeadLine);
					
					//list non updated
					if(!modifyList.update()) {
						errorsMessage.put("GiftListError", "Une erreur est survenue lors de la modification de la liste.");
						request.setAttribute("errorsMessage", errorsMessage);
						doGet(request, response);
					}
					else {
						//remove the list in the session after update
						session.removeAttribute("modifyList");
						response.sendRedirect("MainPage");
					}
				}
				else {
					request.setAttribute("errorsMessage", errorsMessage);
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
			response.sendRedirect("MainPage");
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
