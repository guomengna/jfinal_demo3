package demo;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;

import model.User;
import service.UserService;
public class HelloController extends Controller {
	
	UserService userService =new UserService();
	
    public void index() {
//    	 //在网页上打印出这句话
//       renderText("Hello JFinal World,I'm guomn.");
//    	 //在网页上将数据库的记录打印出来
//       List<User> user1 = User.dao.find("select * from user");
//       renderJson(user1);
       /**
        * 登录login界面，输入用户名和密码跳转到hello界面
        * 地址为localhost/html/login.html
        */
       String name =getPara("name");
       String password=getPara("password");
       System.out.println("username="+name+" password="+password);
       User user=new User();
       user=null;
       user=userService.findUser(name, password);
       System.out.println("user="+user);
       if(user!=null){
    	   render("/html/hello.html");
       }
       else{
    	   render("/html/login.html");
    	   System.out.println("用户不存在");
       }
       List users=new ArrayList<User>();
       users=userService.findListUser(name, password);
       if(users.size()>0){
    	   System.out.println("users size="+users.size());
    	   for(int i=0;i<users.size();i++){
    		   System.out.println("user="+users.get(i).toString());
    	   }
    	   render("/html/hello.html");
       }else{
    	   render("/html/login.html");
    	   System.out.println("用户不存在");
       }
          	    	
    }
}