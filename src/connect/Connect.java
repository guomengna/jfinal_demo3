package connect;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * ���ݿ�������
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
            System.out.println("�������ݿ�ɹ�");
              
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return con;  
    }  
}
