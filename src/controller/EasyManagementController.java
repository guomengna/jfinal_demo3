package controller;



import java.util.ArrayList;

import java.util.List;



import com.jfinal.core.Controller;



import model.Easy;
import model.PublicedEasy;
import model.User;

import service.EasyService;

import service.ReadUserService;

/**
 * ���¹��������
 * @author guomn
 * ��ȡ�������£���ȡ��������
 * �������µ����ݿ⡢ɾ������
 * ������ع���ʱ��δʵ��
 */
public class EasyManagementController extends Controller{
	public void jumpToEasymanagement() {
    	//render("/html/easymanagement.html");
    }
	/**
	 * ��ȡ���е����·��������õ���EasyService�еĻ�ȡ�������·���
	 */
	public void getAllEasys(){
		System.out.println("�����ѯȫ�����·���");
		EasyService easyGeter=new EasyService();  
    	List<Easy> easylist=new ArrayList<Easy>();

    	easylist=easyGeter.findAllEasy();
    	if(easylist.size()!=0){
    		System.out.println("��ѯ��"+easylist.size()+"������");
    		setAttr("result",1);
    		setAttr("getEasy_returns",easylist);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("reason","δ��ѯ������");
    		System.out.println("δ��ѯ������");
    	}
	}
	//������Ŀ��ѯ����
	public void getEasysByTitle(){
		String title="";
		title =getPara("title");
		System.out.println("�����ѯ�������·���");
		EasyService easyGeter=new EasyService();  
    	List<Easy> easylist=new ArrayList<Easy>();
    	easylist=easyGeter.findUserByEasyTitle(title);
    	if(easylist.size()!=0){
    		System.out.println("��ѯ��"+easylist.size()+"������");
    		setAttr("result",1);
    		setAttr("getEasy_returns",easylist);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("reason","δ��ѯ������");
    		System.out.println("δ��ѯ������");
    	}
	}
	//�������߲�ѯ����
	public void getEasysByAuthor(){
		String author="";
		author =getPara("author");
		System.out.println("�����ѯ�������·���");
		EasyService easyGeter=new EasyService();  
    	List<Easy> easylist=new ArrayList<Easy>();

    	easylist=easyGeter.findUserByEasyAuthor(author);
    	if(easylist.size()!=0){
    		System.out.println("��ѯ��"+easylist.size()+"������");
    		setAttr("result",1);
    		setAttr("getEasy_returns",easylist);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("reason","δ��ѯ������");
    		System.out.println("δ��ѯ������");
    		renderJson();
    	}
	}
	//����һƪ���µ����ݿ���,����ӿڲ���ͨ��
	public void addEasy(){
		//�������ݣ�����ͨ��
//		String title="���Բ���ӿ�";
//		String author="nana";
//		String content="��������ֵ�����Բ���ӿ��Ƿ����";
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
		//easyTableΪeasy��ı���
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
        	renderText("yes!����ɹ�����");
        	setAttr("result",1);
    		setAttr("status","insert succeed");
    		renderJson();
        }else {  
            renderText("sorry,����ʧ�ܣ���");
            setAttr("result",2);
    		setAttr("status","insert fail");
    		renderJson();
        }  
	}
	//ɾ��һƪ���ݿ��е�����
	//ɾ���ӿڲ��Գɹ�
	public void deleteEasyById(){
		int id = getParaToInt("id");  
        boolean flag = Easy.getter.deleteById(id);  
        if (flag) {  
        	renderText("yes!ɾ���ɹ�����");
        	setAttr("result",1);
        	setAttr("status","delete succeed");
        	renderJson();
        }else {  
            renderText("sorry,ɾ��ʧ�ܣ���"); 
            setAttr("result",2);
        	setAttr("status","delete fail");
        	renderJson();
        }
	}
	//ͨ��id��ѯ���£��ӿڲ��Գɹ�
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
    		setAttr("reason","δ��ѯ������");
    		System.out.println("δ��ѯ������");
    		renderText("δ��ѯ�����ݣ���");
    	}
	}

	/**

	 * ʵ���ϸ�����ع���һ��ì�ܵķ�����������ζ�����ݿ��е����ݱ����ǣ��ͻᵼ���޷�ʵ�ֻع�����

	 * ���ǣ����Բ������ݲֿ��˼�룬��ÿ�����ݶ����µ����ݣ������Ḳ��ǰһ�����ݣ����ǻ����һ��ʱ���

	 * ÿ�β�ѯֻ��ʾ�����ʱ������ع�һ�μ���ʾ��һ��ʱ�����

	 * ���ǣ�Ӧ�����ƻع�ֻ�ܽ���һ�β��Ҳ����棬���ع�֮�󲻿���ȡ����

	 * Ҳ����һ��ִ���˻ع����������Ӧ���������¶�Ӧ�ñ�ɾ����ֻ���������ڶ��ε�ǰ�ع��������¼�¼��

	 * 

	 * ������ÿ�δ򿪱༭һƪ���¶�Ҫ�ȸ��ƴ洢һ��������Ȼ��ֻҪ���±��޸��˾��ٴ洢һ���µİ汾��ͬһƪ������Զͬʱ����ǰ��������¼��

	 * �ɵİ汾ʲôʱ��ɾ���أ��Ķ������ʱ��Ӧ�ñ����ɰ汾�����������ݽ��µİ汾
	 * ����ÿ�����¶�����һ����ǩ����ͬ��ǩ��Ϊһƪ���µĲ�ͬ�汾

	 */

	//����һƪ����

	public void updateEasy(){

	}

	//�ع�����һ���汾

	public void rollBack(){

	}
	/**
	 * ��ȡȫ��������Ŀ
	 */
	public void getCountOfEasy(){
		EasyService easyGeter=new EasyService();  
    	List<Easy> easylist=new ArrayList<Easy>();
    	int count;
    	easylist=easyGeter.findAllEasy();
    	if(easylist.size()!=0){
    		System.out.println("��ѯ��"+easylist.size()+"������");
    		count=easylist.size();
    		setAttr("result",1);
    		setAttr("count",count);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("reason","count=0");
    		System.out.println("count=0");
    	}
	}
	/**
	 * ��������
	 */
	public void publicdEasy(){
		String title="";
		title =getPara("title");
		String content="";
		content =getPara("content");
		String author="";
		author =getPara("author");
		String publiceddata="";
		publiceddata =getPara("publiceddata");
//		int publicedeasyid;
//		publicedeasyid =getParaToInt("publicedeasyid");
		//publicedeasyTableΪpublicedeasy��ı���
		PublicedEasy publicedEasy=getModel(PublicedEasy.class,"publicedeasyTable");
	
		publicedEasy.set("title",title);
		publicedEasy.set("author",author);  
		publicedEasy.set("content",content);  
		publicedEasy.set("publiceddata",publiceddata);  
        boolean flag = publicedEasy.save();  
        if (flag) {  
        	renderText("yes!����ɹ�����");
        	setAttr("result",1);
    		setAttr("status","insert succeed");
    		renderJson();
        }else {  
            renderText("sorry,����ʧ�ܣ���");
            setAttr("result",2);
    		setAttr("status","insert fail");
    		renderJson();
        }
	}
	/**
	 * �����������߻�ȡ����������
	 */
	public void getPublicedEasysByAuthor(){
		String author="";
		author =getPara("author");
		System.out.println("�����ѯ�������·���");
		EasyService easyGeter=new EasyService();  
    	List<PublicedEasy> publicedeasylist=new ArrayList<PublicedEasy>();

    	publicedeasylist=easyGeter.findPublicedEasyByAuthor(author);
    	if(publicedeasylist.size()!=0){
    		System.out.println("��ѯ��"+publicedeasylist.size()+"������");
    		setAttr("result",1);
    		setAttr("publicedeasy_returns",publicedeasylist);
    		renderJson();
    	}else{
    		setAttr("result",2);
    		setAttr("reason","δ��ѯ������");
    		System.out.println("δ��ѯ������");
    		renderJson();
    	}
	}
	
	//ͨ��id��ѯ���������£��ӿڲ��Գɹ�
		public void findByPublicedEasyId(){
			int publicedeasyid = getParaToInt("publicedeasyid");
			PublicedEasy publicedEasy=new PublicedEasy();
			EasyService easyGeter=new EasyService();  
			publicedEasy=easyGeter.findByPublicedId(publicedeasyid);
	    	if(publicedEasy!=null){
	    		setAttr("result",1);
	    		setAttr("getEasy_returns",publicedEasy);
	    		renderJson();
	    	}else{
	    		setAttr("result",2);
	    		setAttr("reason","δ��ѯ������");
	    		System.out.println("δ��ѯ������");
	    		renderText("δ��ѯ�����ݣ���");
	    	}
		}
}