package demo;
import java.util.List;

import com.jfinal.core.Controller;

import model.User;
import service.UserService;
public class HelloController extends Controller {
	
	UserService userService =new UserService();
	
    public void index() {
//    	 //����ҳ�ϴ�ӡ����仰
//       renderText("Hello JFinal World,I'm guomn.");
//    	 //����ҳ�Ͻ����ݿ�ļ�¼��ӡ����
//       List<User> user1 = User.dao.find("select * from user");
//       renderJson(user1);
       /**
        * ��¼login���棬�����û�����������ת��hello����
        * ��ַΪlocalhost/html/login.html
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