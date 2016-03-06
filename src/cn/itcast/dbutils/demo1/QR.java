package cn.itcast.dbutils.demo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import cn.itcast.jdbc.JdbcUtils;

public class QR<T> {
	private DataSource dataSource = null;
	public QR() {
		super();
	}
	public QR(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	/**
	 * 做insert、update、delete
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public int update(String sql,Object... params) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//通过连接池得到连接对象
			conn = dataSource.getConnection();
			//使用sql创建pstmt
			pstmt = conn.prepareStatement(sql);
			//初始化设置参数
			initParams(pstmt,params);
			//执行语句
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//处理异常
			throw new RuntimeException(e);
		} finally{
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	}
	//给参数赋值
	private void initParams(PreparedStatement pstmt,Object... params) throws SQLException{
		System.out.println(params[0]);
		for(int i=0;i<params.length;i++){
			pstmt.setObject(i+1, params[i]);
		}
		//System.out.println(pstmt.toString());
	}
	public T query(String sql,RsHandler rh,Object... params) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//通过连接池得到连接对象
			conn = dataSource.getConnection();
			//使用sql创建pstmt
			pstmt = conn.prepareStatement(sql);
			//初始化设置参数
			initParams(pstmt,params);
			//执行语句
			rs =  pstmt.executeQuery();
			return (T)rh.handle(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//处理异常
			throw new RuntimeException(e);
		} finally{
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	}
}

//将结果集转换为需要的类型
interface RsHandler<T> {
	public T handle(ResultSet rs) throws SQLException;
}
