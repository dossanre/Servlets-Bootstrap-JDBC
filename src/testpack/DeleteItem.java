package testpack;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteItem")
public class DeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer uid = (Integer) request.getSession().getAttribute("uid");
		int iid = Integer.parseInt(request.getParameter("id"));
		String itemName = request.getParameter("name");
		if(uid == null) {
			response.sendRedirect("Login?msg=have to login first...");
		}
		else {
			Item idel = new Item();
			idel.setId(iid);
			idel.setName(itemName);
			request.setAttribute("idel", idel);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/delete.jsp");
			rd.forward(request, response);			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Integer uid = (Integer)request.getSession().getAttribute("uid");
		int iid = Integer.parseInt(request.getParameter("iid"));
		if(uid == null) {
			response.sendRedirect("Login?msg=you have to login first");
		} else {
			DB_Access db = new DB_Access();
			db.deleteItem(iid);
			response.sendRedirect("Home?msg=Item Deleted Sucessfully");
		}
	}

}
