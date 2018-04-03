package service;

import java.util.List;

import model.User;

public class UserService {
	public User findUser(String name,String password){
		User user = new User();
		user=null;
		String sql="select *from user ";
		user=User.dao.findFirst(sql);
		System.out.printf("sql:"+sql);
		return user;
	}
	/**
	 * 此方法是可以获取到数据库数据的,会返回所有的数据条目，但是应该如何限定条件？
	 * 输入数据为汉字的时候获取之后会出现乱码，中文乱码如何解决
	 * @param name
	 * @param password
	 * @return
	 */
	public List findListUser(String name,String password){
		String input_username=name;
		String input_password=password;
		//这种写法是错的，因为引号的使用是错的
//		String sql="select *from user where username=? and password=?";
		//这种写法是不限定任何的检索条件
//		String sql="select *from user";
		//限定在数据库中的检索条件是username=name,password=password
		String sql="select *from user where username='"+name+"' AND password='"+password+"'";
		List users=User.dao.find(sql);
		System.out.printf("sql:"+sql);
		return users;
	}
	
}
