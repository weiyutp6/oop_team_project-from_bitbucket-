package Gui;

import java.util.EventObject;

public class SearchTourEvent extends EventObject {
	
	private String earlyBeginDate;
	private String lateBeginDate;
	private String destination;
	
	public SearchTourEvent(Object source) {
		super(source);
	}
	
	public SearchTourEvent(Object source, String earlyBeginDate, String lateBeginDate, 
			String destination) {
		super(source);
		
		this.earlyBeginDate = earlyBeginDate;
		this.lateBeginDate = lateBeginDate;
		this.destination = destination;
	}

	public String getEarlyBeginDate() {
		return earlyBeginDate;
	}

	public void setEarlyBeginDate(String earlyBeginDate) {
		this.earlyBeginDate = earlyBeginDate;
	}

	public String getLateBeginDate() {
		return lateBeginDate;
	}

	public void setLateBeginDate(String lateBeginDate) {
		this.lateBeginDate = lateBeginDate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	

}
