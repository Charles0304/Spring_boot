package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.pnu.domain.LogDTO;

public class LogDao {
	Connection con;
	public LogDao() {
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "1234");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public LogDTO insertLog(LogDTO l) throws SQLException {
		PreparedStatement psmt = con.prepareStatement("insert into dblog(method,sqlstring,success) values(?,?,?)");
		psmt.setString(1, l.getMethod());
		psmt.setString(2, l.getSqlstring());
		psmt.setBoolean(3, l.isSuccess());
		psmt.executeUpdate();
		return l;
	}
}
