package model;

import com.jfinal.plugin.activerecord.Model;

public class Keyword extends Model<Keyword>{
	private String keyword;//关键字
	private String author;//作者名称
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
