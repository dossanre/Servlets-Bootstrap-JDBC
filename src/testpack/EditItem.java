package testpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditItem")
public class EditItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer uid = (Integer) request.getSession().getAttribute("uid");
		int iid = Integer.parseInt(request.getParameter("id"));
		if(uid == null) {
			response.sendRedirect("Login?msg=have to login first...");
		}
		else {
			Item iEdit = new Item();
			iEdit.setId(iid);
			DB_Access db = new DB_Access();
			iEdit = db.getNameQty(iEdit);
			request.setAttribute("iEdit", iEdit);
			String itemUser = "";
			if (!iEdit.getName().isEmpty()) {
				itemUser = iEdit.getName();
			}
			String [] itemsProd = {itemUser,"Apple","Banana","Grape","Pineapple","Kiwi","Tomato","Carrot","Lettuce","Strawberry","Onion","Yorgut"};
			request.setAttribute("itemsProd", itemsProd);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/edit.jsp");
			rd.forward(request, response);				
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer uid = (Integer)request.getSession().getAttribute("uid");

		if(uid == null) {
			response.sendRedirect("LoginForm?msg=you have to login first");
		} else {
			String name = request.getParameter("name");
			String qty = request.getParameter("qty");
			String iid = request.getParameter("iid");
			try {
				int iidA = Integer.parseInt(iid);
				int qtya = Integer.parseInt(qty);
				// access
				DB_Access db = new DB_Access();
				Item item = new Item(iidA, name,qtya,uid);
				int res = db.updateItem(item);
				switch(res) {
				case 0:
					response.sendRedirect("Home?msg=item name must be provided");
					break;
				case 1:
					response.sendRedirect("Home?msg=Item update Sucessfully");
					break;
				case 2:
					response.sendRedirect("EditItem?msg=item quantity must be provided and must be an integer");
					break;
				default:
					response.sendRedirect("EditItem?msg=error unknown");
					break;
				}
			}catch(Exception e) {
				response.sendRedirect("EditItem?id="+iid+"&msg=Item quantity must be provided and must be an integer");
			}

		}

	}

}
