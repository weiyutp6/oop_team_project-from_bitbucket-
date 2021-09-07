package Gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class FormPanel extends JPanel implements ActionListener{
	
	private JButton SEAsiaButton;
	private JButton JapanButton;
	private JButton KoreaButton;
	private JButton ChinaButton;
	private JButton USAButton;
	private JButton AsiaButton;
	private JButton AmericaButton;
	private JButton EuropeButton;
	private JButton OceaniaButton;
	private JButton AfricaButton;
	
	private StringListener textListener;
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		
		Border innerBorder = BorderFactory.createTitledBorder("想去哪裡?");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		SEAsiaButton = new JButton("東南亞");
		JapanButton = new JButton("日本");
		KoreaButton = new JButton("韓國");
		ChinaButton = new JButton("中國");
		USAButton = new JButton("美國");
		AsiaButton = new JButton("亞洲");
		AmericaButton = new JButton("美洲");
		EuropeButton = new JButton("歐洲");
		OceaniaButton = new JButton("大洋洲");
		AfricaButton = new JButton("非洲");
		
		SEAsiaButton.addActionListener(this);
		JapanButton.addActionListener(this);
		KoreaButton.addActionListener(this);
		ChinaButton.addActionListener(this);
		USAButton.addActionListener(this);
		AsiaButton.addActionListener(this);
		AmericaButton.addActionListener(this);
		EuropeButton.addActionListener(this);
		OceaniaButton.addActionListener(this);
		AfricaButton.addActionListener(this);
		
		setLayout(new GridLayout(5,2));
		
		add(SEAsiaButton);
		add(JapanButton);
		add(KoreaButton);
		add(ChinaButton);
		add(USAButton);
		add(AsiaButton);
		add(AmericaButton);
		add(EuropeButton);
		add(OceaniaButton);
		add(AfricaButton);
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}
	
    //////Set the Action While Clicked//////
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == SEAsiaButton) {
			if(textListener != null) {
				textListener.textEmitted("東南亞");
			}
		}else if(clicked == JapanButton){
			if(textListener != null) {
				textListener.textEmitted("日本");
			}
		}else if(clicked == KoreaButton){
			if(textListener != null) {
				textListener.textEmitted("韓國");
			}
		}else if(clicked == ChinaButton){
			if(textListener != null) {
				textListener.textEmitted("中國");
			}
		}else if(clicked == USAButton){
			if(textListener != null) {
				textListener.textEmitted("美國");
			}
		}else if(clicked == AsiaButton){
			if(textListener != null) {
				textListener.textEmitted("亞洲");
			}
		}else if(clicked == AmericaButton){
			if(textListener != null) {
				textListener.textEmitted("美洲");
			}
		}else if(clicked == EuropeButton){
			if(textListener != null) {
				textListener.textEmitted("歐洲");
			}
		}else if(clicked == OceaniaButton){
			if(textListener != null) {
				textListener.textEmitted("大洋洲");
			}
		}else if(clicked == AfricaButton){
			if(textListener != null) {
				textListener.textEmitted("非洲");
			}
		}
	}
}
