package servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDaoImp;
import dao.RequestDao;
import dao.RequestDaoImp;
import model.Employee;
import model.Request;
import util.User;

public class SubmitRequestServlet extends HttpServlet {
	private static final long serialVersionUID = -5296796852497141597L;

	public SubmitRequestServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqD = null;
		if (!User.isNull() && !User.isManager()) {
			double a = Double.parseDouble(request.getParameter("amount"));		
			BigDecimal amount = BigDecimal.valueOf(a);
			request.getParameter("reason");
			RequestDao rd = new RequestDaoImp();
			Request r = new Request();
			r.setStatus("pending");
			r.setAmount(amount);
			Employee e = new EmployeeDaoImp().getEmployeeById(User.getUserId());
			r.setEmployee(e);
			r.setManager(null);
			rd.addRequest(r);
			reqD = request.getRequestDispatcher("Views/Employee/Requests.html");
		} else {
			reqD = request.getRequestDispatcher("Views/Login.html");
		}
		reqD.forward(request, response);
	}
}

