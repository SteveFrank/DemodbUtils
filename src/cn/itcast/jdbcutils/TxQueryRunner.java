package cn.itcast.jdbcutils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import cn.itcast.jdbc.JdbcUtils;
/**
 * 这个类中的方法自己处理连接中的问题
 * 无需要外界的传递！
 * 怎么进行处理呢？
 * 	通过JdbcUtils.getConnection()得到连接！有可能是事务连接，也可能是普通连接
 *  JdbcUtils.releaseConnection()完成对于连接的释放！如果是普通连接关闭之！
 * @author 杨谦
 * @date 2015-8-23 下午11:43:45
 *
 */
public class TxQueryRunner extends QueryRunner {

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		//批处理方法
		/**
		 * 1、得到连接
		 * 2、执行父类方法
		 * 3、释放链接
		 * 4、返回值
		 */
		Connection conn = JdbcUtils.getConnection();
		int[] result = super.batch(conn,sql, params);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh)
			throws SQLException {
		/**
		 * 1、得到连接
		 * 2、执行父类方法
		 * 3、释放链接
		 * 4、返回值
		 */
		Connection conn = JdbcUtils.getConnection();
		T result = super.query(conn,sql, param,rsh);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh)
			throws SQLException {
		// TODO Auto-generated method stub
		return super.query(sql, params, rsh);
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		/**
		 * 1、得到连接
		 * 2、执行父类方法
		 * 3、释放链接
		 * 4、返回值
		 */
		Connection conn = JdbcUtils.getConnection();
		T result = super.query(conn,sql, params,rsh);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		/**
		 * 1、得到连接
		 * 2、执行父类方法
		 * 3、释放链接
		 * 4、返回值
		 */
		Connection conn = JdbcUtils.getConnection();
		T result = super.query(conn,sql,rsh);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		/**
		 * 1、得到连接
		 * 2、执行父类方法
		 * 3、释放链接
		 * 4、返回值
		 */
		Connection conn = JdbcUtils.getConnection();
		int result = super.update(conn,sql);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		/**
		 * 1、得到连接
		 * 2、执行父类方法
		 * 3、释放链接
		 * 4、返回值
		 */
		Connection conn = JdbcUtils.getConnection();
		int result = super.update(conn,sql,param);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		/**
		 * 1、得到连接
		 * 2、执行父类方法
		 * 3、释放链接
		 * 4、返回值
		 */
		Connection conn = JdbcUtils.getConnection();
		int result = super.update(conn,sql,params);
		JdbcUtils.releaseConnection(conn);
		return result;
	}
	
}
