package service;

import java.util.ArrayList;
import java.util.List;

import model.Easy;

/**
 * ��ȡ���ݿ������²��������У������������ƣ�������������������ڣ���������id
 * @author guomn
 * 2018.5.27 8:23
 */
public class EasyService {
	/**
	 * ��ȡ���е�����
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
	 * ���մ���Ĳ���-�������ƻ�ȡ��Ӧ������
	 * @param easyTitle
	 * @return
	 */
	public List<Easy> findUserByEasyTitle(String title){
		String sql="select *from user where title='"+title+"'";
		List<Easy> easyList=Easy.getter.find(sql);
		return easyList;
	}
	/**
	 * ���մ���Ĳ���-���¸���ʱ���ȡ��Ӧ������
	 * @param updateData
	 * @return
	 */
	public List<Easy> findUserByEasyUpdateData(String updateData){
		String sql="select *from easy where easyTitle='"+updateData+"'";
		List<Easy> easyList=Easy.getter.find(sql);
		return easyList;
	}
	/**
	 * ���մ�������µ����߲�ѯ����
	 * @param author
	 * @return
	 */
	public List<Easy> findUserByEasyAuthor(String author){
		String sql="select *from easy where author='"+author+"'";
		List<Easy> easyList=Easy.getter.find(sql);
		return easyList;
	}
	/**
	 * ͨ��id��ѯ����,����sql��䣬ֱ�ӵ���Model���ṩ��findById()����
	 * @param id
	 * @return
	 */
	public Easy findById(int id){
		//String sql="select *from easy where id='"+id+"'";
		Easy easy=Easy.getter.findById(id);
		return easy;
	}
}
