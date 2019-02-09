package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;
import dao.EmployeeDaoImp;
import util.User;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 2902623799834818962L;

	public HomeServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDao ed = new EmployeeDaoImp();
		
		String button = request.getParameter("button");
		
		if("Info".equals(button)) {
			ed.getEmployeeByUsername("Sneaky");
		}
		
		doGet(request, response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			  throws ServletException, IOException {
		RequestDispatcher rq;
		if (!User.isNull() && !User.isManager()) {
			rq = request.getRequestDispatcher("Views/Employee/EmployeeHome.html");
		} else {
			rq = request.getRequestDispatcher("Views/Login.html");
		}
		rq.forward(request, response);
	}
}
