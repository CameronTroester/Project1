package servlets;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.CallableStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;
import dao.EmployeeDaoImp;
import dao.ManagerDao;
import dao.ManagerDaoImp;
import model.Employee;
import model.Manager;
import util.User;

public class HomeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = -224765235287518367L;

	public HomeLoginServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rq = null;
		if (request.getParameter("btn").equals("Manager")) {
			ManagerDao md = new ManagerDaoImp();
			String username = request.getParameter("username");
			if (username.equals("rick")) {
				  Desktop desktop = java.awt.Desktop.getDesktop();
				  URI oURL = null;
				try {
					oURL = new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
					rq = request.getRequestDispatcher("Views/Login.html");
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
				  desktop.browse(oURL);
			}
			Manager manager = md.getManagerByUsername(username);
			
			System.out.println("username: " + username);
			System.out.println("manager: " + manager);
			String password = request.getParameter("password");
			//CallableStatement cs = con.prepareCall("{? = call GET_CUSTOMER_HASH(?,?)}");
			System.out.println("password: " + password);
			if(manager == null) {
				rq = request.getRequestDispatcher("Views/Login.html");
			}
			else if (manager.login(password)) {
				User.setUser(manager.getId(), true);
				rq = request.getRequestDispatcher("Views/Employee/Manager.html");
			} else {
				rq = request.getRequestDispatcher("Views/Login.html");
			}
		} else if (request.getParameter("btn").equals("Employee")) {
			EmployeeDao ed = new EmployeeDaoImp();
			String username = request.getParameter("username");
			Employee employee = ed.getEmployeeByUsername(username);
			
			if (username.equals("rick")) {
				  Desktop desktop = java.awt.Desktop.getDesktop();
				  URI oURL = null;
				try {
					oURL = new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
					rq = request.getRequestDispatcher("Views/Login.html");
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
				  desktop.browse(oURL);
			}
			
			String password = request.getParameter("password");
			if (employee == null) {
				rq = request.getRequestDispatcher("Views/Login.html");
			}
			else if (employee.login(password)) {
				User.setUser(employee.getId(), false);
				rq = request.getRequestDispatcher("Views/Employee/EmployeeHome.html");
			} else {
				rq = request.getRequestDispatcher("Views/Login.html");
			}
		}
		System.out.println(request.toString() + " test " + response.toString());
		rq.forward(request, response);
	}
}
