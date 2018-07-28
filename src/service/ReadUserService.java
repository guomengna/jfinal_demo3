package service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import connect.Connect;
import model.User;
import tool.CheckEmail;

public class ReadUserService {
	/**
	 * 获取数据库中的第一个用户
	 * @param name
	 * @param password
	 * @return
	 */
	public User findUser(String name,String password){
		User user = new User();
		user=null;
		String sql="select *from user ";
		user=User.dao.findFirst(sql);
		System.out.printf("sql:"+sql);
		return user;
	}
	/**
	 * 获取所有的user
	 * @param name
	 * @param password
	 * @return
	 */
	public List findListUser(){
		String sql="select *from user ";
		List users=User.dao.find(sql);
		//System.out.printf("sql:"+sql);
		return users;
	}
	/**
	 * 根据前台输入的登陆用户名username和密码password获取相应的用户
	 */
	public List findSomeUser(String username){
		String sql="select *from user where username='"+username+"'";
		List users=User.dao.find(sql);
		//System.out.printf("sql:"+sql);
		return users;
	}
	
	public List findAUser(String username,String password){
		String sql="select *from user where username='"+username+"' and password='"+password+"'";
		List users=User.dao.find(sql);
		System.out.printf("sql:"+sql);
		return users;
	}
	
	public List findAUserValidEmail(int id,int code){
		String sql="select *from user where id='"+id+"' and code='"+code+"'";
		List users=User.dao.find(sql);
		System.out.printf("sql:"+sql);
		return users;
	}
	
    
}
