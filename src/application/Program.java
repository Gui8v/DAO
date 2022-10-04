package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.dao.RoleDao;
import model.entities.Employee;
import model.entities.Role;

public class Program {

	public static void main(String[] args) {
		
		EmployeeDao employeeDao = DaoFactory.createEmployeeDao();	
		RoleDao roleDao = DaoFactory.createRoleDao();	
		
		System.out.println("-------------- Test 01: FindByIdWithRoles ----------------");
		Employee emp = employeeDao.findByIdWithRoles(1);
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
	    
	    System.out.println("\n------------ Test 04: Insert Employee ---------");
	    Employee empInsert = new Employee(null, "Ribamar", "145788-123-12", null);
	    
	    //employeeDao.insert(empInsert);
	    //System.out.println("Id: " + empInsert.getId());
	    
	    System.out.println("\n------------ Test 05: Update Employee ---------");
	    //Employee empUpdate = new Employee();
	    //empUpdate = employeeDao.findById(10);
	    //empUpdate.setName("ribamar");
	    //employeeDao.update(empUpdate);
	    
	    System.out.println("\n------------ Test 05: Delete Employee ---------");
	    //Integer id = null;
	    //employeeDao.deleteById(id); 
	    
	    System.out.println("\n------------ Test 06: FindAll Roles ---------");
	    List<Role> rolelist = new ArrayList<>();
	    
	    rolelist = roleDao.findAll();
	    
	    for(Role rol : rolelist) {
			System.out.println(rol);
		}
	    
	    
	}

}
