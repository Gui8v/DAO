package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.entities.Employee;
import model.entities.Role;

public class Program {

	public static void main(String[] args) {
		
		EmployeeDao employeeDao = DaoFactory.createEmployeeDao();	
		
		System.out.println("-------------- Test 01: FindById ----------------");
		Employee emp = employeeDao.findById(1);
		System.out.println(emp);
		
		System.out.println("\n-------------- Test 02: FindByRole ------------");
		List<Employee> list = new ArrayList<>();
		Role role = new Role(3, "", "");
		list = employeeDao.findByRole(role);
		
		for(Employee employee : list) {
			System.out.println(employee);
		}
		
		System.out.println("\n--------------- Test 03: FindAll --------------");
	    list = employeeDao.findAll();
	    
	    for(Employee employee : list) {
			System.out.println(employee);
		}
	}

}
