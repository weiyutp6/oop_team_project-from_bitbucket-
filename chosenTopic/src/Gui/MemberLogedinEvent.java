package Gui;

import java.util.EventObject;

public class MemberLogedinEvent extends EventObject{
	
	private String prePass;
	private String newPass;
	private String conPass;

	public MemberLogedinEvent(Object source) {
		super(source);
	}
	
	public MemberLogedinEvent(Object source, String prePass, String newPass, String conPass) {
		super(source);
		
		this.prePass = prePass;
		this.newPass = newPass;
		this.conPass = conPass;
	}

	public String getPrePass() {
		return prePass;
	}

	public void setPrePass(String prePass) {
		this.prePass = prePass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	
	public String getConPass() {
		return conPass;
	}

	public void setConPass(String conPass) {
		this.conPass = conPass;
	}
	
}
