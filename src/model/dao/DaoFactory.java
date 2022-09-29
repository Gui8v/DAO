package model.dao;

import model.dao.impl.EmployeeDaoJdbc;
import model.dao.impl.RoleDaoJdbc;

public class DaoFactory {
	
	public static EmployeeDao createEmployeeDao() {
		return new EmployeeDaoJdbc();
	}
	
	public static RoleDao createRoleDao() {
		return new RoleDaoJdbc();
	}
}
