package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.EmployeeDao;
import model.entities.Employee;
import model.entities.Role;

public class EmployeeDaoJdbc implements EmployeeDao{

	private Connection conn = null;
	
	public EmployeeDaoJdbc(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					  "select f.fun_id, fun_nome, fun_cpf, c.car_id, car_nome "
					+ "from funcionarios f "
					+ "inner join cargosfuncionarios cf "
					+ "on f.fun_id = cf.fun_id "
					+ "inner join cargos c "
					+ "on cf.car_id = c.car_id "
					+ "where f.fun_id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				
				Employee emp = new Employee();
				
				emp.setId(rs.getInt("fun_id"));
				emp.setName(rs.getString("fun_nome"));
				emp.setCpf(rs.getString("fun_cpf"));
				
				do{
					Role rol = new Role();
					rol.setId(rs.getInt("car_id"));
					rol.setName(rs.getString("car_nome"));
					
					emp.addRole(rol);
				} while(rs.next());
				
				return emp;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatemant(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
