package Gui;

import java.util.EventListener;

public interface MemberCreateListener extends EventListener{
	public void memberCreateEventOccurred(MemberCreateEvent ev);
}
