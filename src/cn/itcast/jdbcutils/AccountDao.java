package cn.itcast.jdbcutils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

public class AccountDao {
	public static void update(String name,double money) throws SQLException{
		QueryRunner qr = new TxQueryRunner();
		String sql = "update account set balance = balance + ? where name = ?";
		Object[] params = {money,name};
		
		//我们需要自己来提供连接
//		Connection conn = JdbcUtils.getConnection();
//		qr.update(conn,sql,params);
		qr.update(sql,params);
//		JdbcUtils.releaseConnection(conn);
	}
}
