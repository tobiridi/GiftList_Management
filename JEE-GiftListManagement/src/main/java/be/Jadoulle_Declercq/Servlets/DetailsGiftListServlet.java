package be.Jadoulle_Declercq.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.Jadoulle_Declercq.JavaBeans.Customer;
import be.Jadoulle_Declercq.JavaBeans.GiftList;

/**
 * Servlet implementation class DetailsGiftListServlet
 */
public class DetailsGiftListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailsGiftListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Customer customerLog = (Customer) session.getAttribute("customerLog");
		String idGiftList = request.getParameter("idGiftList");
		String idFriendGiftList = request.getParameter("idFriendGiftList");
		if(idGiftList != null || idFriendGiftList != null) {
			try {
				int idList = (idGiftList != null) ? Integer.parseInt(idGiftList) : Integer.parseInt(idFriendGiftList);
				GiftList listCustomer = null;
				//check if customerLog has this giftList, not consult a giftList from another Customer
				if(idFriendGiftList != null) {
					//friends giftList
					listCustomer = customerLog.getOtherCustomerList().stream().filter(gl -> gl.getId() == idList).findFirst().orElse(null);
				}
				else {
					//owner giftList
					listCustomer = customerLog.getGiftList().stream().filter(gl -> gl.getId() == idList).findFirst().orElse(null);
				}
				if(listCustomer != null) {
					listCustomer = GiftList.get(idList);
					
					if(listCustomer != null) {
						if(idFriendGiftList != null) {
							request.setAttribute("isFriendGiftList", true);
						}
						request.setAttribute("listCustomer", listCustomer);
						RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/DetailsGiftList.jsp");
						dispatcher.forward(request, response);
					}
					else {
						response.sendRedirect("MainPage");
					}
				}
				else {
					response.sendRedirect("MainPage");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("MainPage");
			}
		}
		else {
			response.sendRedirect("MainPage");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
