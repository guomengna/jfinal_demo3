package connect;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * 数据库连接类
 * @author guomn
 *
 */
public class Connect {
	Connection con;   
    public static final String url="jdbc:mysql://localhost:3306/testdatauser";  
    public static final String name="com.mysql.jdbc.Driver";  
    public static final String user="root";  
    public static final String password="root";  
      
    public Connection getConnection(){  
        try{  
            Class.forName(name);  
            con=DriverManager.getConnection(url,user,password);  
            System.out.println("连接数据库成功");
              
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return con;  
    }  
}
