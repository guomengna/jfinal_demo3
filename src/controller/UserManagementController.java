package controller;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;

import connect.Connect;
import model.User;
import service.ReadUserService;
import service.UserService;

public class UserManagementController extends Controller{
	UserService userService =new UserService();
    public void jumpToUsermanagement() {
    	render("/html/usermanagement.html");
    }
    //��ѯȫ���û�
    public void ReadUserAjax(){
//    	String id=getPara("id");  
//    	String username=getPara("username");
//    	String password=getPara("password");
//    	String email=getPara("email");
    	System.out.println("�����ѯȫ���û�����");
    	ReadUserService reader=new ReadUserService();  
    	List<User> userlist=new ArrayList<User>();
    	//��ѯȫ���û������õ���ReadUserService�еķ���findListUser
    	userlist=reader.findListUser();
    	if(userlist.size()!=0){
    		System.out.println("��ѯ��"+userlist.size()+"������");
    		setAttr("result",1);
    		setAttr("readerUser_returns",userlist);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("reason","δ��ѯ������");
    		System.out.println("δ��ѯ������");
    	}
//    	String reason=reader.trackReadUserResult(id,username,password,email);  
//    	   if(reason.equalsIgnoreCase("�ɹ�����")){  
//    	    List<User> userlist=new ArrayList<User>();  
//    	    userlist=reader.searchInfo(id,username,password,email);  
//    	                  setAttr("result",1);//�����1�����ɹ�  
//	    	setAttr("borrow_returns",userlist);  
//	    	renderJson();   
//    	}else{  
//	    	setAttr("result",2);//�����2����û�н�ɹ�  
//	    	setAttr("reason",reason);  
//	    	renderJson();  
//    	}   	 
    }
    //��ѯ�����޶����û�
    public void ReadUserByUsername(){
    	String username="";
    	 
    	username =getPara("username");
    	//System.out.println("username is "+username);
    	List<User> userlist=new ArrayList<User>(); 
        ReadUserService reader=new ReadUserService(); 
    	if(username!=""){
    		userlist=reader.findSomeUser(username); 
    	}else if(username==""){
    		userlist=reader.findListUser();
    	}
    	//��ѯȫ���û������õ���ReadUserService�еķ���findListUser
    	if(userlist.size()!=0){
    		System.out.println("��ѯ��"+userlist.size()+"������");
    		setAttr("result",1);
    		setAttr("total",userlist.size());//û�и�ֵ��ȥ����
    		setAttr("readerUser_returns",userlist);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("total",0);
    		setAttr("reason","δ��ѯ������");
    		System.out.println("δ��ѯ������");
    	}
    }
    //��¼
    public void login(){
    	String username;
    	username =getPara("username");
        String password=getPara("password");
        List users=new ArrayList<User>(); 
        users=userService.findListUser(username, password); 
        if(users.size()>0){ 
     	   System.out.println("users size="+users.size()); 
     	   for(int i=0;i<users.size();i++){ 
     		   System.out.println("user="+users.get(i).toString()); 
     	   }
	     	  setAttr("result",1);
	     	  setAttr("status","��¼�ɹ�");
	     	  renderJson();
     	   
        }else{ 
        	setAttr("result",2);
       	  	setAttr("status","��¼ʧ��");
       	  	renderJson();
       	  	System.out.println("�û�������"); 
        }
    }
  //��ѯ��¼���û�
    public void getLoginUser(){
    	String username;
    	String password;
    	 
    	username =getPara("username");
    	password =getPara("password");
    	//System.out.println("username is "+username);
    	List<User> userlist=new ArrayList<User>();
    	User currentUser=new User(); 
        ReadUserService reader=new ReadUserService();
        userlist=reader.findAUser(username, password);
    	//��ѯȫ���û������õ���ReadUserService�еķ���findListUser
    	if(userlist.size()!=0){
    		System.out.println("��ѯ��"+userlist.size()+"������");
    		currentUser=userlist.get(0);
    		setAttr("result",1);
    		setAttr("readerUser_returns",currentUser);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("reason","δ��ѯ������");
    		System.out.println("δ��ѯ������");
    	}
    }
}
