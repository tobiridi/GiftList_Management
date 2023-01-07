package be.Jadoulle_Declercq.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.Jadoulle_Declercq.JavaBeans.Customer;

/**
 * Servlet implementation class ShareGiftListServlet
 */
public class ShareGiftListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareGiftListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Customer> allCustomers = Customer.getAll();
		request.setAttribute("allCustomers", allCustomers);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/ShareGiftList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String idList = request.getParameter("giftList");
		String idCustomer = request.getParameter("customer");
		
		if(idList != null && idCustomer != null) {
			Customer customerLog = (Customer) session.getAttribute("customerLog");
			int idGiftList = Integer.parseInt(idList);
			int idFriend = Integer.parseInt(idCustomer);
			//TODO : not implemented
//			if(customerLog.shareGiftList(idGiftList, idFriend)) {
//				request.setAttribute("successMessage", "La liste a été partager avec succès !");
//				doGet(request, response);
//			}
//			else {
//				HashMap<String, String> errorsMessage = new HashMap<>();
//				errorsMessage.put("shareListError", "Une erreur est survenue lors du partage de la liste.");
//				request.setAttribute("errorsMessage", errorsMessage);
//				doGet(request, response);
//			}
		}
		doGet(request, response);
	}

}
