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
	 * �˷����ǿ��Ի�ȡ�����ݿ����ݵ�,�᷵�����е�������Ŀ������Ӧ������޶�������
	 * ��������Ϊ���ֵ�ʱ���ȡ֮���������룬����������ν��
	 * @param name
	 * @param password
	 * @return
	 */
	public List findListUser(String name,String password){
		String input_username=name;
		String input_password=password;
		//����д���Ǵ�ģ���Ϊ���ŵ�ʹ���Ǵ��
//		String sql="select *from user where username=? and password=?";
		//����д���ǲ��޶��κεļ�������
//		String sql="select *from user";
		//�޶������ݿ��еļ���������username=name,password=password
		String sql="select *from user where username='"+name+"' AND password='"+password+"'";
		List users=User.dao.find(sql);
		System.out.printf("sql:"+sql);
		return users;
	}
	
}
