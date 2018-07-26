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
    //查询全部用户
    public void ReadUserAjax(){
//    	String id=getPara("id");  
//    	String username=getPara("username");
//    	String password=getPara("password");
//    	String email=getPara("email");
    	System.out.println("进入查询全部用户方法");
    	ReadUserService reader=new ReadUserService();  
    	List<User> userlist=new ArrayList<User>();
    	//查询全部用户，调用的是ReadUserService中的方法findListUser
    	userlist=reader.findListUser();
    	if(userlist.size()!=0){
    		System.out.println("查询到"+userlist.size()+"条数据");
    		setAttr("result",1);
    		setAttr("readerUser_returns",userlist);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("reason","未查询到数据");
    		System.out.println("未查询到数据");
    	}
//    	String reason=reader.trackReadUserResult(id,username,password,email);  
//    	   if(reason.equalsIgnoreCase("成功借阅")){  
//    	    List<User> userlist=new ArrayList<User>();  
//    	    userlist=reader.searchInfo(id,username,password,email);  
//    	                  setAttr("result",1);//如果是1代表借成功  
//	    	setAttr("borrow_returns",userlist);  
//	    	renderJson();   
//    	}else{  
//	    	setAttr("result",2);//如果是2代表没有借成功  
//	    	setAttr("reason",reason);  
//	    	renderJson();  
//    	}   	 
    }
    //查询名称限定的用户
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
    	//查询全部用户，调用的是ReadUserService中的方法findListUser
    	if(userlist.size()!=0){
    		System.out.println("查询到"+userlist.size()+"条数据");
    		setAttr("result",1);
    		setAttr("total",userlist.size());//没有赋值进去？？
    		setAttr("readerUser_returns",userlist);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("total",0);
    		setAttr("reason","未查询到数据");
    		System.out.println("未查询到数据");
    	}
    }
    //登录
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
	     	  setAttr("status","登录成功");
	     	  renderJson();
     	   
        }else{ 
        	setAttr("result",2);
       	  	setAttr("status","登录失败");
       	  	renderJson();
       	  	System.out.println("用户不存在"); 
        }
    }
  //查询登录的用户
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
    	//查询全部用户，调用的是ReadUserService中的方法findListUser
    	if(userlist.size()!=0){
    		System.out.println("查询到"+userlist.size()+"条数据");
    		currentUser=userlist.get(0);
    		setAttr("result",1);
    		setAttr("readerUser_returns",currentUser);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("reason","未查询到数据");
    		System.out.println("未查询到数据");
    	}
    }
}
