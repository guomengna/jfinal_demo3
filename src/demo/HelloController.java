package demo;
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
       User user=new User();
       user=null;
       
       user=userService.findUser(name, password);
       
       if(user!=null){
    	   render("/html/hello.html");
       }
    }
}