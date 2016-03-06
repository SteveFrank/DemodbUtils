package cn.itcast.jdbcutils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.activation.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	//使用的配置文件中的默认值，必须给出c3p0-config.xml
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	//事务专用连接
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	/**
	 * 返回连接池中的一个连接Connection的对象
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		//是否重新创建在于是否存在超过了最大连接数的用户的存在，如果超过了自然会进行重新的创建
		Connection conn = tl.get();
		if(conn != null) return conn;//保证只有一个连接
		return dataSource.getConnection();
	}
	
	/**
	 * 返回连接池对象
	 * @return
	 */
	public static ComboPooledDataSource getDataSource(){
		return dataSource;
	}
	/**
	 * 开启事务
	 * 1、获取一个Connection , 设置它的setAutoCommit(false)
	 * 2、还要保证Connection连接我们刚才所创建的连接
	 * --------------------------
	 * 1、创建一个Connection，设置为手动提交
	 * 2、把这个Connection给dao使用
	 * 3、还要让CommitTransaction或rollbackTransaction可以获取到
	 * @throws SQLException 
	 */
	public static void beginTransaction() throws SQLException {
		Connection conn = tl.get();
		if(conn != null) throw new SQLException("请不要重复开启连接");
		/**
		 * 1、给conn赋值
		 * 2、给conn设置为手动提交
		 */
		conn = getConnection();
		conn.setAutoCommit(false);
		//保存当前现在的连接
		tl.set(conn);
	}
	/**
	 * 提交事务
	 * 1、获取beginTransaction提供的Connection，然后调用commit
	 * @throws SQLException 
	 */
	public static void commitTransaction() throws SQLException {
		Connection conn = tl.get();//获取当前线程的专用连接
		if(conn == null) throw new SQLException("没有开启事务无法提交");
		
		conn.commit();
		conn.close();
		tl.remove();//从t1中进行移除
		conn = null;
	} 
	/**
	 * 回滚事务
	 * 1、获取beginTransaction提供的Connection，然后调用rollback
	 * @throws SQLException 
	 */
	public static void rollbackTransation() throws SQLException {
		Connection conn = tl.get();//获取当前线程的专用连接
		conn.rollback();
		conn.close();
		conn = null;
		tl.get();
	}
	/**
	 * 释放链接
	 * @throws SQLException 
	 */
	public static void releaseConnection(Connection connection) throws SQLException{
		/*
		 * 判断它是不是事务专用，如果是，就不关闭！
		 * 如果不是事务的专用，那么就关闭
		 */
		Connection conn = tl.get();//获取当前线程的专用连接
		if(conn == null) connection.close();
		if(conn != connection) connection.close();
	}
}
