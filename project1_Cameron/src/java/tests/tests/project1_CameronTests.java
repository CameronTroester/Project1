package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.EmployeeDaoImp;

class project1_CameronTests {

	@Test
	void getFirstEmployeeUserName() {
		assertEquals("Sneaky" , new EmployeeDaoImp().getEmployeeByUsername("Sneaky").getUsername());
	}
	

}
