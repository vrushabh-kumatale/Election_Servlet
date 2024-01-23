package com.sunbeam.daos;

import java.sql.Connection;

import com.sunbeam.utils.DbUtil;

public class Dao implements AutoCloseable {
  protected Connection con;
  
  public Dao() throws Exception {
	  con = DbUtil.getConnection();
  }

@Override
public void close() throws Exception {
	try {
		if(con !=null)
			con.close();
	} catch(Exception e) 
	{
		e.printStackTrace();
	}
	
}
}
