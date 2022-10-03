package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(
					"insert into funcionarios "
				  + "(fun_nome, fun_cpf) "
				  + "values(?, ?)"
				  , Statement.RETURN_GENERATED_KEYS );
			
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getCpf());
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				
				if(rs.next()) {
					int id = rs.getInt(1);
					employee.setId(id);
				}

				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected Error ! no rows affcted");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DB.closeStatemant(ps);
		}
		
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
				
				Employee emp = instantiateEmployeeWithRoles(rs);

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
	public List<Employee> findByRole(Role role) {
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
				  + "where c.car_id = ?");
			
			st.setInt(1, role.getId());
			rs = st.executeQuery();
			
			List<Employee> list = new ArrayList<>(); 
			
			while(rs.next()) {

				list.add(instantiateEmployee(rs));
	
			}
			return list;
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
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"select f.fun_id, fun_nome, fun_cpf, c.car_id, car_nome "
				  + "from funcionarios f "
				  + "inner join cargosfuncionarios cf "
				  + "on f.fun_id = cf.fun_id "
				  + "inner join cargos c "
				  + "on cf.car_id = c.car_id ");
			
			
			rs = st.executeQuery();
			
			List<Employee> list = new ArrayList<>(); 
			
			while(rs.next()) {

				list.add(instantiateEmployee(rs));
	
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatemant(st);
			DB.closeResultSet(rs);
		}
	}
 	
	private Employee instantiateEmployeeWithRoles(ResultSet rs) throws SQLException {
		
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
	
	private Employee instantiateEmployee(ResultSet rs) throws SQLException {
		
		Employee emp = new Employee();
		
		emp.setId(rs.getInt("fun_id"));
		emp.setName(rs.getString("fun_nome"));
		emp.setCpf(rs.getString("fun_cpf"));
		
		Role rol = new Role();
		rol.setId(rs.getInt("car_id"));
		rol.setName(rs.getString("car_nome"));
			
			emp.addRole(rol);
		
		return emp;
	}

	


}
