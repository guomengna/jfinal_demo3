package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;

import connect.Connect;
import model.Easy;
import model.User;
import service.ReadUserService;
import service.UserService;
import tool.CheckEmail;

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
    		setAttr("reason","未查询到数据，登录失败");
    		System.out.println("未查询到数据");
    		renderJson();
    	}
    }
    
    /**
     * 注册
     */
    public void register() {
    	String username="";
  		String password="";
  		String email="";
  		int code=0;
  		boolean actived;
  		username =getPara("username");
  		password =getPara("password");
  		email =getPara("email");
  		code =Integer.parseInt(getPara("code"));
  		actived =false;
  		//easyTable为easy表的别名
  		User newuser=getModel(User.class,"userTable");
  		System.out.println("username="+username);
  		System.out.println("password="+password);
  		System.out.println("email="+email);
  		
  		newuser.set("username",username);
  		newuser.set("password",password);  
  		newuser.set("email",email);  
  		newuser.set("code",code);  
  		newuser.set("actived",actived);
  		
          boolean flag = newuser.save();  
          if (flag) {
        	  List<User> userlist=new ArrayList<User>();
          	  User currentUser=new User(); 
              ReadUserService reader=new ReadUserService();
              userlist=reader.findAUser(username, password);
              currentUser=userlist.get(0);
          	renderText("yes!注册成功！！");
          	setAttr("result",1);
      		setAttr("status","register succeed");
      		setAttr("readerUser_returns",currentUser);
      		renderJson();
          }else {  
              renderText("sorry,注册失败！！");
              setAttr("result",2);
      		setAttr("status","register fail");
      		renderJson();
          }
 	
    }
    /**
     * 验证邮箱激活是否成功
     * 传入的是验证码和用户的id
     * 根据这两个参数寻找数据库中的数据，若是有则将这个对象的状态设置为激活
     * 未激活的用户相当于注册失败，无法登录
     */
    public void validEmail(){
    	int id=0;
  		int code=0; 	
  		id =Integer.parseInt(getPara("id"));
  		code =Integer.parseInt(getPara("code"));
  		User registerUser=new User();
  		List<User> userlist=new ArrayList<User>();
  		ReadUserService reader=new ReadUserService();
        userlist=reader.findAUserValidEmail(id, code);
    	//查询全部用户，调用的是ReadUserService中的方法findListUser
    	if(userlist.size()!=0){
    		System.out.println("查询到"+userlist.size()+"条数据");
    		registerUser=userlist.get(0);
    		registerUser.setActived(true);
    		System.out.println("registerUser.getActived()"+registerUser.getActived());
//    		boolean flag = registerUser.update();
    		boolean flag =registerUser.dao.findById(id).set("actived",true).update();
            if (flag) {  
            	renderText("yes!更改成功！！");
            	setAttr("result",1);
        		setAttr("status","change actived succeed 激活成功！"+registerUser.getActived());
        		renderJson();
            }else {  
                renderText("sorry,更改失败！！");
                setAttr("result",2);
        		setAttr("status","change actived fail");
        		renderJson();
            }
    		setAttr("result",flag);
    		setAttr("readerUser_returns",registerUser);
    		renderJson();
    		
    	}else{
    		setAttr("result",false);
    		setAttr("reason","未查询到数据，登录失败");
    		System.out.println("未查询到数据");
    		renderJson();
    	}
    }
    
}
