package cn.itcast.dbutils.demo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import cn.itcast.jdbc.JdbcUtils;

/**
 * commons-dbutils
 * 简化jdbc的代码
 * @author 杨谦
 * @date 2015-8-17 下午9:50:18
 *
 */
public class Demo1 {
	@Test
	/**
	 * 添加
	 * @throws SQLException
	 */
	public void addStu(Stu stu) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "INSERT INTO t_stu values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,stu.getSID());
			pstmt.setString(2,stu.getSNAME());
			pstmt.setString(3,stu.getSAGE());
			pstmt.setString(4,stu.getSEX());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//处理异常
			throw new RuntimeException(e);
		} finally{
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	}
	@Test
	/**
	 * 修改
	 * @throws SQLException
	 */
	public void updateStu(Stu stu) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "UPDATE t_stu set sname=?,age=?,gender=? where sid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(4,stu.getSID());
			pstmt.setString(1,stu.getSNAME());
			pstmt.setString(2,stu.getSAGE());
			pstmt.setString(3,stu.getSEX());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//处理异常
			throw new RuntimeException(e);
		} finally{
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	}
	@Test
	/**
	 * 删除
	 * @throws SQLException
	 */
	public void deleteStu(Stu stu) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "DELETE FROM t_stu where sid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,stu.getSID());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//处理异常
			throw new RuntimeException(e);
		} finally{
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	}
	
	public Stu load(int sid) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "SELECT * FROM t_stu WHERE sid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,sid);
			
			rs = pstmt.executeQuery(sql);
			if(!rs.next()) return null;
			/**
			 * 需要把rs转换成Stu对象
			 */
			Stu stu = new Stu();
			stu.setSID(rs.getInt("SID"));
			stu.setSNAME(rs.getString("SNAME"));
			stu.setSAGE(rs.getString("SAGE"));
			stu.setSEX(rs.getString("SEX"));
			return stu;
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