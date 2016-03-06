package cn.itcast.dbutils.demo1;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.junit.Test;

import cn.itcast.jdbc.JdbcUtils;

public class Demo3 {
	@Test
	public void fun1() throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "insert into t_stu values(?,?,?,?)";
		Object[] params = {1008,"wangwu","88","female"};
		
		qr.update(sql,params);
	}
	
	@Test
	public void fun2() throws SQLException{
		//创建QueryRunner,需要提供数据库连接池对象
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		//给出sql模板
		String sql = "select * from t_stu where sid = ?";
		//给出参数
		Object[] params = {1008};
		
		//执行query()方法，需要给出结果集处理器，即ResultSetHandler的实现类对象
		//我们利用BeanHandler,他实现了ResultsSetHandler
		//利用反射得到类
		Stu stu = qr.query(sql, new BeanHandler<Stu>(Stu.class),params);
		System.out.println(stu);
	}
	/**
	 * BeanListHandler的应用，踏实多行处理器的使用
	 * 每行对象一个Stu对象
	 * @throws SQLException
	 */
	@Test
	public void fun3() throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from t_stu";
		List<Stu> stuList = qr.query(sql, new BeanListHandler<Stu>(Stu.class));
		
		System.out.println(stuList);
	}
	/**
	 * MapHandler的应用，他是一个单行的处理器，单行转换成一个Map对象
	 * @throws SQLException
	 */
	@Test
	public void fun4() throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from t_stu where sid = ?";
		Object[] params = {1001};
		Map map= qr.query(sql, new MapHandler(),params);
		
		System.out.println(map);
	}
}
