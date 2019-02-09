package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.User;

public class EmployeeRequestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public EmployeeRequestServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (!User.isNull() && !User.isManager()) {
			RequestDispatcher rq = request.getRequestDispatcher("Views/Employee/Requests.html");
			rq.forward(request, response);
		} else if(!User.isNull() && User.isManager()) {
			RequestDispatcher rq = request.getRequestDispatcher("Views/Employee/Requests.html");
			rq.forward(request, response);
		}
		else {
			RequestDispatcher rq = request.getRequestDispatcher("Views/Login.html");
			rq.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
