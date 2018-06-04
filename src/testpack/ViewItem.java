package testpack;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewItem")
public class ViewItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer uid = (Integer) request.getSession().getAttribute("uid");
		int iid = Integer.parseInt(request.getParameter("id"));
		if(uid == null) {
			response.sendRedirect("Login?msg=have to login first...");
		}
		else {
			Item iView = new Item();
			iView.setId(iid);
			DB_Access db = new DB_Access();
			iView = db.getNameQty(iView);
			request.setAttribute("iView", iView);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/view.jsp");
			rd.forward(request, response);				
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
