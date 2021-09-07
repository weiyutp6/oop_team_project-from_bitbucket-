package Gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ToolBar extends JPanel implements ActionListener{
	
	private JButton homeButton;
	private JButton searchButton;
	private JButton orderButton;
	private JButton memberButton;
	
	private StringListener textListener;
	
	public ToolBar() {
		
		setBorder(BorderFactory.createEtchedBorder());
		
		homeButton = new JButton("首頁");
		searchButton = new JButton("搜尋行程");
		orderButton = new JButton("查看訂單");
		memberButton = new JButton("會員管理");
		
		homeButton.addActionListener(this);
		searchButton.addActionListener(this);
		orderButton.addActionListener(this);
		memberButton.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(homeButton);
		add(searchButton);
		add(orderButton);
		add(memberButton);
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}

	//////Set the Action While Clicked//////
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == homeButton) {
			if(textListener != null) {
				textListener.textEmitted("Home\n");
			}
		}else if(clicked == searchButton){
			if(textListener != null) {
				textListener.textEmitted("Search\n");
			}
		}else if(clicked == orderButton){
			if(textListener != null) {
				textListener.textEmitted("Order\n");
			}
		}else if(clicked == memberButton){
			if(textListener != null) {
				textListener.textEmitted("Member\n");
			}
		}
	}
}