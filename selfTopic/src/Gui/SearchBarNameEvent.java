package Gui;

import java.util.EventObject;

public class SearchBarNameEvent extends EventObject {

	private String companyName;

	public SearchBarNameEvent(Object source) {
		super(source);
	}
	
	public SearchBarNameEvent(Object source, String name) {
		super(source);
		
		this.companyName = name;
	}

	public String getName() {
		return companyName;
	}

	public void setName(String comName) {
		this.companyName = comName;
	}
}
