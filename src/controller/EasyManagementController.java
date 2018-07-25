package controller;



import java.util.ArrayList;

import java.util.List;



import com.jfinal.core.Controller;



import model.Easy;

import model.User;

import service.EasyService;

import service.ReadUserService;

/**
 * 文章管理控制器
 * @author guomn
 * 获取所有文章，获取部分文章
 * 插入文章到数据库、删除文章
 * 更新与回滚暂时还未实现
 */
public class EasyManagementController extends Controller{
	public void jumpToEasymanagement() {
    	//render("/html/easymanagement.html");
    }
	/**
	 * 获取所有的文章方法，调用的是EasyService中的获取所有文章方法
	 */
	public void getAllEasys(){
		System.out.println("进入查询全部文章方法");
		EasyService easyGeter=new EasyService();  
    	List<Easy> easylist=new ArrayList<Easy>();

    	easylist=easyGeter.findAllEasy();
    	if(easylist.size()!=0){
    		System.out.println("查询到"+easylist.size()+"条数据");
    		setAttr("result",1);
    		setAttr("getEasy_returns",easylist);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("reason","未查询到数据");
    		System.out.println("未查询到数据");
    	}
	}
	//根据题目查询文章
	public void getEasysByTitle(){
		String title="";
		title =getPara("title");
		System.out.println("进入查询部分文章方法");
		EasyService easyGeter=new EasyService();  
    	List<Easy> easylist=new ArrayList<Easy>();
    	easylist=easyGeter.findUserByEasyTitle(title);
    	if(easylist.size()!=0){
    		System.out.println("查询到"+easylist.size()+"条数据");
    		setAttr("result",1);
    		setAttr("getEasy_returns",easylist);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("reason","未查询到数据");
    		System.out.println("未查询到数据");
    	}
	}
	//根据作者查询文章
	public void getEasysByAuthor(){
		String author="";
		author =getPara("author");
		System.out.println("进入查询部分文章方法");
		EasyService easyGeter=new EasyService();  
    	List<Easy> easylist=new ArrayList<Easy>();

    	easylist=easyGeter.findUserByEasyAuthor(author);
    	if(easylist.size()!=0){
    		System.out.println("查询到"+easylist.size()+"条数据");
    		setAttr("result",1);
    		setAttr("getEasy_returns",easylist);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("reason","未查询到数据");
    		System.out.println("未查询到数据");
    		renderJson();
    	}
	}
	//插入一篇文章到数据库中,插入接口测试通过
	public void addEasy(){
		//测试数据，测试通过
//		String title="测试插入接口";
//		String author="nana";
//		String content="给定参数值，测试插入接口是否好用";
//		String createData="2018-5-30 14:45:00";
//		String updateData="2018-5-30 14:46:00";
		String title="";
		String author="";
		String content="";
		String createData="";
		String updateData="";
		title =getPara("title");
		author =getPara("author");
		content =getPara("content");
		createData =getPara("createData");
		updateData =getPara("updateData");
		//easyTable为easy表的别名
		Easy easy=getModel(Easy.class,"easyTable");
		System.out.println("title="+title);
		System.out.println("author="+author);
		System.out.println("content="+content);
		System.out.println("createData="+createData);
		System.out.println("updateData="+updateData);
		easy.set("title",title);
		easy.set("author",author);  
		easy.set("content",content);  
		easy.set("createData",createData);  
		easy.set("updateData",updateData); 
        boolean flag = easy.save();  
        if (flag) {  
        	renderText("yes!插入成功！！");
        	setAttr("result",1);
    		setAttr("status","insert succeed");
    		renderJson();
        }else {  
            renderText("sorry,插入失败！！");
            setAttr("result",2);
    		setAttr("status","insert fail");
    		renderJson();
        }  
	}
	//删除一篇数据库中的文章
	//删除接口测试成功
	public void deleteEasyById(){
		int id = getParaToInt("id");  
        boolean flag = Easy.getter.deleteById(id);  
        if (flag) {  
        	renderText("yes!删除成功！！");
        	setAttr("result",1);
        	setAttr("status","delete succeed");
        	renderJson();
        }else {  
            renderText("sorry,删除失败！！"); 
            setAttr("result",2);
        	setAttr("status","delete fail");
        	renderJson();
        }
	}
	//通过id查询文章，接口测试成功
	public void findByEasyId(){
		int id = getParaToInt("id");
		Easy easy=new Easy();
		EasyService easyGeter=new EasyService();  
		easy=easyGeter.findById(id);
    	if(easy!=null){
    		setAttr("result",1);
    		setAttr("getEasy_returns",easy);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("reason","未查询到数据");
    		System.out.println("未查询到数据");
    		renderText("未查询到数据！！");
    	}
	}

	/**

	 * 实际上更新与回滚是一对矛盾的方法，更新意味着数据库中的数据被覆盖，就会导致无法实现回滚操作

	 * 但是，可以采用数据仓库的思想，即每条数据都是新的数据，都不会覆盖前一条数据，但是会加上一个时间戳

	 * 每次查询只显示最近的时间戳，回滚一次即显示上一个时间戳。

	 * 但是，应该限制回滚只能进行一次并且不可逆，即回滚之后不可再取消。

	 * 也就是一旦执行了回滚操作，相对应的所有文章都应该被删除，只保留倒数第二次当前回滚到的文章记录。

	 * 

	 * 或者是每次打开编辑一篇文章都要先复制存储一个副本，然后只要文章被修改了就再存储一个新的版本，同一篇文章永远同时存在前后两条记录。

	 * 旧的版本什么时候删除呢，改动不大的时候应该保留旧版本，而不是两份较新的版本

	 */

	//更新一篇文章

	public void updateEasy(){

	}

	//回滚到上一个版本

	public void rollBack(){

	}

}