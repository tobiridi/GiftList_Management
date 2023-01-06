package be.Jadoulle_Declercq.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.Jadoulle_Declercq.JavaBeans.GiftList;

/**
 * Servlet implementation class DeleteGiftListServlet
 */
public class DeleteGiftListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGiftListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idDelete = request.getParameter("id");
			if(idDelete != null) {
				GiftList deleteGiftList = new GiftList();
				deleteGiftList.setId(Integer.parseInt(idDelete));
				deleteGiftList.delete(); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("MainPage");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
