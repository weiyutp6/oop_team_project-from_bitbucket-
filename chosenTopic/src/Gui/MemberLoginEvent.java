package Gui;

import java.util.EventObject;

public class MemberLoginEvent extends EventObject{
	
	private String account;
	private String passWord;

	public MemberLoginEvent(Object source) {
		super(source);
	}
	
	public MemberLoginEvent(Object source, String account, String passWord) {
		super(source);
		
		this.account = account;
		this.passWord = passWord;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
