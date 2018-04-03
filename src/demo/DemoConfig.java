package demo;

import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

import model.User;

//import model.User;
public class DemoConfig extends JFinalConfig{
	public void configConstant(Constants me) {
	       me.setDevMode(true);
	       me.setEncoding("utf-8");
	       loadPropertyFile("jdbc.txt");   
	       me.setViewType(ViewType.JSP);

	    }
	    public void configRoute(Routes me) {
	    	me.setBaseViewPath("/view");
//	       me.add("/helloyou", HelloController.class);//���ֲ���Ϊ����ַ��localhost��߼��ϵĲ���//ʹ��helloyou���޷���ת��·��д��
	    	me.add("/hello", HelloController.class);//������Ŀ�ĸ�Ŀ¼Ϊ/WEB-INF/view/hello/***.html
	    }
	    public void configEngine(Engine me) {}
	    public void configPlugin(Plugins me) {
	    	C3p0Plugin c3p0=new C3p0Plugin(getProperty("jdbcUrl"),
	                getProperty("user"),               
	                getProperty("password"));      
	    	me.add(c3p0);
	        ActiveRecordPlugin activeRecord=new ActiveRecordPlugin("otherConfig",c3p0);
	        activeRecord.addMapping("user",User.class);
	        me.add(activeRecord);   
	        /**
	        ActiveReceord �ж����� addMapping(String tableName, Class<? extends Model> modelClass>)�������÷������������ݿ������ Model ��ӳ���ϵ�� ���⣬���ϴ����� arp.addMapping(��user��, User.class)�����������ΪĬ��Ϊ��id���������������Ϊ ��user_id������Ҫ�ֶ�ָ�����磺arp.addMapping(��user��, ��user_id��, User.class)�� 
	�ҵ�user������Ϊid,���ʹ��Ĭ������Ϊid,����������͵��޸ġ�
	          **/
	    }
	    public void configInterceptor(Interceptors me) {}
	    public void configHandler(Handlers me) {}
}
