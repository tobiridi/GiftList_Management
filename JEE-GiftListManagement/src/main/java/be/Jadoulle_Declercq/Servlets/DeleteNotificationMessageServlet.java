package be.Jadoulle_Declercq.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.Jadoulle_Declercq.JavaBeans.Customer;
import be.Jadoulle_Declercq.JavaBeans.NotificationMessage;

/**
 * Servlet implementation class DeleteNotificationMessageServlet
 */
public class DeleteNotificationMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNotificationMessageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idNotification = request.getParameter("idNotification");
		
		if(idNotification != null) {
			try {
				HttpSession session = request.getSession(false);
				Customer customerLog = (Customer) session.getAttribute("customerLog");
				int idNotif = Integer.parseInt(idNotification);
				
				NotificationMessage notification = new NotificationMessage();
				notification.setId(idNotif);
				
				customerLog.removeNotificationMessage(notification);
				response.sendRedirect("NotificationMessage");
				
			} catch (NumberFormatException e) {
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
