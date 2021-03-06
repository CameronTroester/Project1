package dao;

import java.sql.Connection;
import java.util.List;

import model.Request;

public interface RequestDao {
	List<Request> getRequests();
	Request getRequestById(int id);
	Request getRequestById(int id, Connection con);
	List<Request> getPendingRequests();
	List<Request> getResolvedRequests();
	List<Request> getRequestsByEmployeeId(int employeeId);
	List<Request> getPendingRequestsByEmployeeId(int employeeId);
	List<Request> getResolvedRequestsByEmployeeId(int employeeId);
	void addRequest(Request r);
	void approveRequest(int id);
}
