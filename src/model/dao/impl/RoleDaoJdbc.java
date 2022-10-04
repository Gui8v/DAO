package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.RoleDao;
import model.entities.Role;

public class RoleDaoJdbc implements RoleDao{
	
	private Connection conn = null;
	
	public RoleDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Role findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select * from cargos where car_id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			Role role = new Role(); 
			
			if(rs.next()) {

				 role = instantiateRole(rs); 
	
			}
			return role;
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
	public List<Role> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select * from cargos");

			rs = st.executeQuery();
			
			List<Role> list = new ArrayList<>(); 
			
			while(rs.next()) {

				list.add(instantiateRole(rs));
	
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

	private Role instantiateRole(ResultSet rs) throws SQLException {
		
		Role role = new Role();
		
		role.setId(rs.getInt("car_id"));
		role.setName(rs.getString("car_nome"));
		
		return role;
	}
}
