package com.sunbeam.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sunbeam.pojos.User;

public class UserDaoImpl extends Dao implements UserDao {

	public UserDaoImpl() throws Exception {
		
	}

	@Override
	public User findByEmail(String email) throws Exception {
		String sql = "SELECT * FROM users WHERE email=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					int id = rs.getInt("id");
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					email = rs.getString("email");
					String password = rs.getString("password");
					int status = rs.getInt("status");
					Date birth = rs.getDate("dob");
					String role = rs.getString("role");
					User u = new User(id, firstName, lastName, email, password, birth, status, role);
					return u;
				}
			} // rs.close();
		} // stmt.close();
		return null;
	}

	@Override
	public User findById(int id) throws Exception {
		String sql = "Select * from users where id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					 id = rs.getInt("id");
					 String firstName = rs.getString("first_name");
					 String lastName = rs.getString("first_name");
					 String email = rs.getString("email");
					String password = rs.getString("password");
					int status = rs.getInt("status");
					Date birth = rs.getDate("dob");
					String role = rs.getString("role");
					
					User u = new User(id, firstName, lastName, email, password, birth, status, role);
					 return u;
					 
				}
			}
		}
		return null;
	}

	@Override
	public int save(User user) throws Exception {
		String sql = "insert into users values(default,?, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());
			stmt.setDate(5, user.getBirth());
			stmt.setInt(6, user.getStatus());
			stmt.setString(7, user.getRole());
			int cnt = stmt.executeUpdate();
			return cnt;
			
		}
		
	}

	@Override
	public int deleteById(int id) throws Exception {
		String sql = "DELETE FROM users where id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			int cnt = stmt.executeUpdate();
			return cnt;
		}
	
	}


	@Override
	public int updateStatus(int userId, boolean voted) throws Exception {
		String sql = "update users SET status=? where id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, (voted ? 1 : 0));
			int cnt = stmt.executeUpdate();
			return cnt;
		}
		
	}
	
}
