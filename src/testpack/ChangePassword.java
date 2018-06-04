package testpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/changePass.jsp");
		rd.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oPass = request.getParameter("oldpass");
		String nPass = request.getParameter("newpass");
		String cPass = request.getParameter("confpass");
		Integer uid = (Integer) request.getSession().getAttribute("uid");
		
		if (!nPass.equals(cPass)) {
			response.sendRedirect("ChangePassword?msg=Password do not match");
		}else if(oPass.isEmpty()){
			response.sendRedirect("ChangePassword?msg=Old Password must be filled");
		}else {
			DB_Access db = new  DB_Access();
			String dbPass = db.getUserPassword(uid);
			if (dbPass.equals(oPass)) {
				String ret = Utilities.isPassValid(nPass);
				if (ret.isEmpty()) {
					int retDB = db.updateUserPassword(nPass, uid);
					if (retDB == 0) {
						response.sendRedirect("Home?msg=Password Updated Successfully");
					}else {
						response.sendRedirect("ChangePassword?msg=Password was not updated DB Error");
					}
				}else {
					response.sendRedirect("ChangePassword?msg="+ret);
				}
			}else {
				response.sendRedirect("ChangePassword?msg=Old Password Incorret");
			}
		}

	}

}
