package testpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditAccount")
public class EditAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer uid = (Integer) request.getSession().getAttribute("uid");
		DB_Access db = new DB_Access();
		String uname = db.getUserName(uid); 
		request.setAttribute("name", uname);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/editAccount.jsp");
		rd.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		int uid = (Integer) request.getSession().getAttribute("uid");

		DB_Access db = new DB_Access();
		int res = db.updateUserInFullName(name,uid);

		switch(res) {
		case 0:
			response.sendRedirect("Home?msg=User Not Found");
			break;
		case 1:
			response.sendRedirect("Home?msg=User Update Sucessfully");
			break;
		default:
			response.sendRedirect("EditAccount?msg=Error Unknown");
			break;
		}
	}

}
