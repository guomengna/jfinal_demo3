package model;

import com.jfinal.plugin.activerecord.Model;

public class Keyword extends Model<Keyword>{
	private String keyword;//�ؼ���
	private String author;//��������
	public static final Keyword getter=new Keyword();
	public void setKeyword(String keyword){
		this.keyword = keyword;
	}
	public void setAuthor(String author){
		this.author = author;
	}
	public String getKeyword(){
		return keyword;
	}
	public String getAuthor(){
		return author;
	}
}
