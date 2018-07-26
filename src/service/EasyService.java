package service;

import java.util.ArrayList;
import java.util.List;

import model.Easy;

/**
 * 读取数据库中文章操作，所有，按照文章名称，按照文章最近更新日期，按照文章id
 * @author guomn
 * 2018.5.27 8:23
 */
public class EasyService {
	/**
	 * 读取所有的文章
	 * @return
	 */
	public List<Easy> findAllEasy(){
		String sql="select *from easy ";
		System.out.printf("sql:"+sql);	
		List<Easy> easyAllList=new ArrayList<Easy>(); 
		easyAllList=Easy.getter.find(sql);
		return easyAllList;
	}
	/**
	 * 按照传入的参数-文章名称获取响应的文章
	 * @param easyTitle
	 * @return
	 */
	public List<Easy> findUserByEasyTitle(String title){
		String sql="select *from user where title='"+title+"'";
		List<Easy> easyList=Easy.getter.find(sql);
		return easyList;
	}
	/**
	 * 按照传入的参数-文章更新时间获取响应的文章
	 * @param updateData
	 * @return
	 */
	public List<Easy> findUserByEasyUpdateData(String updateData){
		String sql="select *from easy where easyTitle='"+updateData+"'";
		List<Easy> easyList=Easy.getter.find(sql);
		return easyList;
	}
	/**
	 * 按照传入的文章的作者查询数据
	 * @param author
	 * @return
	 */
	public List<Easy> findUserByEasyAuthor(String author){
		String sql="select *from easy where author='"+author+"'";
		List<Easy> easyList=Easy.getter.find(sql);
		return easyList;
	}
	/**
	 * 通过id查询文章,不用sql语句，直接调用Model中提供的findById()方法
	 * @param id
	 * @return
	 */
	public Easy findById(int id){
		//String sql="select *from easy where id='"+id+"'";
		Easy easy=Easy.getter.findById(id);
		return easy;
	}
}
