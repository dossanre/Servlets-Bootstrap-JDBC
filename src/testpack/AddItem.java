package testpack;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer uid = (Integer) request.getSession().getAttribute("uid");
		if(uid == null) {
			// not logged in, send to Login with error message
			response.sendRedirect("Login?msg=have to login first...");
		}
		else {
			// show the home page
			DB_Access db = new DB_Access();
			String uname = db.getUserName(uid); request.setAttribute("name", uname);
			ArrayList<Item> allItems = db.getAllUserItems(uid); request.setAttribute("allItems", allItems);
			String [] itemsProd = {"Apple","Banana","Grape","Pineapple","Kiwi","Tomato","Carrot","Lettuce","Strawberry","Onion","Yorgut"};
			request.setAttribute("itemsProd", itemsProd);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/add.jsp");
			rd.forward(request, response);			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String name = request.getParameter("name");
			String qty = request.getParameter("qty");
			Integer uid = (Integer) request.getSession().getAttribute("uid");
			
			DB_Access db = new DB_Access();
			int res = 0;
			int quant  = 0;
			try {
				quant = Integer.parseInt(qty);
				res = db.addItem(name, quant, uid);
			}catch (Exception e) {
				res = 2;
			}
			if (name.isEmpty()) {
				res = 1;
			}

			switch(res) {
			case 0:
				request.getSession().setAttribute("pname", "");
				response.sendRedirect("AddItem?msg=Item registered successfully");
				break;
			case 1:
				response.sendRedirect("AddItem?msg=Item name must be provided");
				break;
			case 2:
				response.sendRedirect("AddItem?msg=Item quantity must be provided and must be an integer");
				break;
			case 3:
				response.sendRedirect("AddItem?msg=Item quantity must be greater then 0");
				break;				
			}
		}


}
