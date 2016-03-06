package cn.itcast.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.activation.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	//使用的配置文件中的默认值，必须给出c3p0-config.xml
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	/**
	 * 返回连接池中的一个连接Connection的对象
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		//是否重新创建在于是否存在超过了最大连接数的用户的存在，如果超过了自然会进行重新的创建
		return dataSource.getConnection();
	}
	
	/**
	 * 返回连接池对象
	 * @return
	 */
	public static ComboPooledDataSource getDataSource(){
		return dataSource;
	}

	public static void releaseConnection(Connection conn) {
		
	}
}
