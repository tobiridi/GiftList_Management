package be.Jadoulle_Declercq.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String idGiftList = request.getParameter("id");
		String friendGiftList = request.getParameter("friendGiftList");
		if(idGiftList != null && friendGiftList == null) {
			try {
				int idList = Integer.parseInt(idGiftList);
				GiftList detailGiftList = GiftList.get(idList);
				System.out.println("list recu : " + detailGiftList.getId() + " " + detailGiftList.getType() + " " + detailGiftList.getGifts().size());
				
				//TODO : not finished
				if(detailGiftList != null) {
					request.setAttribute("detailGiftList", detailGiftList);
					RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/DetailsGiftList.jsp");
					dispatcher.forward(request, response);
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
