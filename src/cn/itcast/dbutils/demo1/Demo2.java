package cn.itcast.dbutils.demo1;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import cn.itcast.jdbc.JdbcUtils;

public class Demo2 {
	@Test
	public void fun1() throws SQLException{
//		Stu s = new Stu(1002,"lisi","999","MALE");
//		addStu(s);
		
		Stu s = load(1001);
		System.out.println(s);
	}
	
	public void addStu(Stu stu) throws SQLException{
		//1、创建给出连接池
		QR qr = new QR(JdbcUtils.getDataSource());
		//2、给出SQL模板
		String sql = "INSERT INTO t_stu values(?,?,?,?)";
		//3、给出参数的赋值
		Object[] params = {stu.getSID(),stu.getSNAME(),stu.getSAGE(),stu.getSEX()};
		//4、调用UODATE执行增删改
		qr.update(sql, params);
	}
	
	public Stu load(int sid) throws SQLException{
		//创建对象时候给出的连接池
		QR qr = new QR(JdbcUtils.getDataSource());
		String sql = "SELECT * FROM t_stu WHERE sid = ?";
		Object[] params = {sid};
		//return (Stu)qr.Query(sql, rh, params);
		RsHandler<Stu> rh = new RsHandler<Stu>() {
			@Override
			public Stu handle(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				if(!rs.next()) return null;
				Stu stu = new Stu();
				stu.setSID(rs.getInt("sid"));
				stu.setSNAME(rs.getString("sname"));
				stu.setSAGE(rs.getString("sage"));
				stu.setSEX(rs.getString("sex"));
				return stu;
			}
			
		};
		return (Stu)qr.query(sql, rh, params);
	}
}
