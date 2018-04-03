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
//	       me.add("/helloyou", HelloController.class);//蓝字部分为在网址中localhost后边加上的部分//使用helloyou是无法跳转的路径写法
	    	me.add("/hello", HelloController.class);//配置项目的根目录为/WEB-INF/view/hello/***.html
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
	        ActiveReceord 中定义了 addMapping(String tableName, Class<? extends Model> modelClass>)方法，该方法建立了数据库表名到 Model 的映射关系。 另外，以上代码中 arp.addMapping(“user”, User.class)，表的主键名为默认为“id”，如果主键名称为 “user_id”则需要手动指定，如：arp.addMapping(“user”, “user_id”, User.class)。 
	我的user表主键为id,因此使用默认主键为id,如果是其他就得修改。
	          **/
	    }
	    public void configInterceptor(Interceptors me) {}
	    public void configHandler(Handlers me) {}
}
