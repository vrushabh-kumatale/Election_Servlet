package com.sunbeam.daos;

import com.sunbeam.pojos.User;

public interface UserDao extends AutoCloseable {

	User findByEmail(String email) throws Exception;
	User findById(int id) throws Exception;
	int save(User user) throws Exception;
	int updateStatus(int userId, boolean voted) throws Exception;
	int deleteById(int id) throws Exception;
	
}
