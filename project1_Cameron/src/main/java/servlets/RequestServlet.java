package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RequestDao;
import dao.RequestDaoImp;
import model.Request;
import util.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public RequestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	
		if (!User.isNull() && !User.isManager()) {
			RequestDao rd = new RequestDaoImp();
			ObjectMapper om = new ObjectMapper();
			PrintWriter pw = response.getWriter();
			List<Request> requests = rd.getRequestsByEmployeeId(User.getUserId());
			String requestString = om.writeValueAsString(requests);
			requestString = "{\"requests\":"+requestString+"}";
			pw.print(requestString);
		} 
		else if (!User.isNull() && User.isManager()) {
			RequestDao rd = new RequestDaoImp();
			ObjectMapper om = new ObjectMapper();
			PrintWriter pw = response.getWriter();
			List<Request> requests = rd.getRequests();
			String requestString = om.writeValueAsString(requests);
			requestString = "{\"requests\":" + requestString + "}";
			pw.print(requestString);
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
