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
    		setAttr("reason","δ��ѯ�����ݣ���¼ʧ��");
    		System.out.println("δ��ѯ������");
    		renderJson();
    	}
    }
    
    /**
     * ע��
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
  		//easyTableΪeasy��ı���
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
          	renderText("yes!ע��ɹ�����");
          	setAttr("result",1);
      		setAttr("status","register succeed");
      		setAttr("readerUser_returns",currentUser);
      		renderJson();
          }else {  
              renderText("sorry,ע��ʧ�ܣ���");
              setAttr("result",2);
      		setAttr("status","register fail");
      		renderJson();
          }
 	
    }
    /**
     * ��֤���伤���Ƿ�ɹ�
     * ���������֤����û���id
     * ��������������Ѱ�����ݿ��е����ݣ�����������������״̬����Ϊ����
     * δ������û��൱��ע��ʧ�ܣ��޷���¼
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
    	//��ѯȫ���û������õ���ReadUserService�еķ���findListUser
    	if(userlist.size()!=0){
    		System.out.println("��ѯ��"+userlist.size()+"������");
    		registerUser=userlist.get(0);
    		registerUser.setActived(true);
    		System.out.println("registerUser.getActived()"+registerUser.getActived());
//    		boolean flag = registerUser.update();
    		boolean flag =registerUser.dao.findById(id).set("actived",true).update();
            if (flag) {  
            	renderText("yes!���ĳɹ�����");
            	setAttr("result",1);
        		setAttr("status","change actived succeed ����ɹ���"+registerUser.getActived());
        		renderJson();
            }else {  
                renderText("sorry,����ʧ�ܣ���");
                setAttr("result",2);
        		setAttr("status","change actived fail");
        		renderJson();
            }
    		setAttr("result",flag);
    		setAttr("readerUser_returns",registerUser);
    		renderJson();
    		
    	}else{
    		setAttr("result",false);
    		setAttr("reason","δ��ѯ�����ݣ���¼ʧ��");
    		System.out.println("δ��ѯ������");
    		renderJson();
    	}
    }
    
}
