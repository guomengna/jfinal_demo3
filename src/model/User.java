package model;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User>{
	private int id,code;
	private String username,password,email;
	private boolean actived;
	public static final User dao=new User();
	private void setUsername(String username){
		this.username=username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public int getId() {
		return id;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	public boolean getActived() {
		return actived;
	}
}
