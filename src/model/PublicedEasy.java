package model;

import com.jfinal.plugin.activerecord.Model;

public class PublicedEasy extends Model<PublicedEasy>{
	private String title;//�������µ�����
	private String content;//�������µ�����
	private String author;//�������µ�����
	private String publiceddata;//��������
	private int publicedeasyid;//�������µ�id
	private int readcount;//�Ķ�����
	public static final PublicedEasy getter=new PublicedEasy();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPubliceddata() {
		return publiceddata;
	}
	public void setPubliceddata(String publiceddata) {
		this.publiceddata = publiceddata;
	}
	public int getPublicedeasyid() {
		return publicedeasyid;
	}
	public void setPublicedeasyid(int publicedeasyid) {
		this.publicedeasyid = publicedeasyid;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	
	
}
