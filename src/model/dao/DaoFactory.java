package model.dao;

import db.DB;
import model.dao.impl.EmployeeDaoJdbc;
import model.dao.impl.RoleDaoJdbc;

public class DaoFactory {
	
	public static EmployeeDao createEmployeeDao() {
		return new EmployeeDaoJdbc(DB.getConnection());
	}
	
	public static RoleDao createRoleDao() {
		return new RoleDaoJdbc(DB.getConnection());
	}
}
