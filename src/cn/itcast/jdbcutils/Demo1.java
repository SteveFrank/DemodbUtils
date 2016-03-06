package cn.itcast.jdbcutils;

import java.sql.SQLException;

import org.junit.Test;

public class Demo1 {
	private AccountDao dao = new AccountDao();
	@Test
	public void serviceMethod() {
		try {
			JdbcUtils.beginTransaction();
			
			dao.update("zs", -800);
			dao.update("ls", 800);
			
			JdbcUtils.commitTransaction();
			
//			JdbcUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			//TODO: handle exception
			try {
				JdbcUtils.rollbackTransation();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
