package com.penjing.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("jdbc")
public class JdbcConn {

private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Connection getConn() throws Exception{
		@SuppressWarnings("deprecation")
		ConnectionProvider cp = ((SessionFactoryImplementor)sessionFactory).getConnectionProvider();
		Connection conn = cp.getConnection();
		return conn;
	}
	
	public void CloseConnect(Connection cnn) throws SQLException{
		if(cnn!=null){
			cnn.close();
		}
	}
}
