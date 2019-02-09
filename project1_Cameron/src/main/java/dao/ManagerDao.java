package dao;

import java.sql.Connection;
import java.util.List;

import model.Manager;

public interface ManagerDao {
	List<Manager> getManagers();
	public Manager getManagerById(int managerId);
	Manager getManagerById(int id, Connection con);
	Manager getManagerByUsername(String username);
}
