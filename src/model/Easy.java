package model;

import com.jfinal.plugin.activerecord.Model;

public class Easy extends Model<Easy>{
	private String title;//文章名称
	private String content;//文章内容
	private String createData;//文章创建日期
	private String updateData;//文章更新日期
	private int id;//文章编号
	private String author;//文章作者
	public static final Easy getter=new Easy();
	public void setEasyTitle(String easyTitle){
		this.title=easyTitle;
	}
	public void setEasyContent(String easyContent){
		this.content=easyContent;
	}
	public void setCreateData(String createData){
		this.createData=createData;
	}
	public void setUpdateData(String updateData){
		this.updateData=updateData;
	}
	public void setEasyId(int id){
		this.id=id;
	}
	public void setAuthor(String author){
		this.author=author;
	}
	public String getEasyTitle(){
		return title;
	}
	public String getEasyContent(){
		return content;
	}
	public int getEasyId(){
		return id;
	}
	public String getCreateData(){
		return createData;
	}
	public String getUpdateData(){
		return updateData;
	}
	public String getAuthor(){
		return author;
	}
	
}
