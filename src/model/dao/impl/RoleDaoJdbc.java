package model.dao.impl;

import java.sql.Connection;
import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}