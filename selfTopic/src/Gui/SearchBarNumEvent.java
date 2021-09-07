package Gui;

import java.util.EventObject;

public class SearchBarNumEvent extends EventObject {

	private String companyNum;

	public SearchBarNumEvent(Object source) {
		super(source);
	}
	
	public SearchBarNumEvent(Object source, String num) {
		super(source);
		
		this.companyNum = num;
	}

	public String getNum() {
		return companyNum;
	}

	public void setNum(String comNum) {
		this.companyNum = comNum;
	}
}
