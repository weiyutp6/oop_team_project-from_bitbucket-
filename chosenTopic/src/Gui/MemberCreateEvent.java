package Gui;

import java.util.EventObject;

public class MemberCreateEvent extends EventObject{
	
	private String name;
	private String citizenID;
	private String ID;
	private String passWordCreate;
	private String passWordConfirm;

	public MemberCreateEvent(Object source) {
		super(source);
	}
	
	public MemberCreateEvent(Object source, String name, String citizenID, String ID,
			String passWordCreate, String passWordConfirm) {
		super(source);
		
		this.name = name;
		this.citizenID = citizenID;
		this.ID = ID;
		this.passWordCreate = passWordCreate;
		this.passWordConfirm = passWordConfirm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCitizenID() {
		return citizenID;
	}

	public void setCitizenID(String citizenID) {
		this.citizenID = citizenID;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPassWordCreate() {
		return passWordCreate;
	}

	public void setPassWordCreate(String passWordCreate) {
		this.passWordCreate = passWordCreate;
	}

	public String getPassWordConfirm() {
		return passWordConfirm;
	}

	public void setPassWordConfirm(String passWordConfirm) {
		this.passWordConfirm = passWordConfirm;
	}
}
