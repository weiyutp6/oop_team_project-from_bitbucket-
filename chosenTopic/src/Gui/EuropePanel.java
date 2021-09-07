package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class EuropePanel extends JPanel implements ActionListener {
	
	private JButton btn040;
	private JButton btn101;
	private JButton btn393;
	private JButton btn394;
	private JButton btn395;
	private JButton btn396;
	private JButton btn397;
	private JButton btn398;
	private JButton btn404;
	private JButton btn413;
	private JButton btn414;
	
	private StringListener textListener;
	
    private JLabel label;
	
	private JPanel btnPanel;
	private JPanel imgPanel;

	public EuropePanel() {
		
		Border innerBorder = BorderFactory.createTitledBorder("歐洲");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		btn040 = new JButton("北歐、丹麥");
		btn101 = new JButton("捷克、波蘭");
		btn393 = new JButton("奧地利");
		btn394 = new JButton("瑞士");
		btn395 = new JButton("波羅的海");
		btn396 = new JButton("冰島");
		btn397 = new JButton("希臘");
		btn398 = new JButton("伊比利");
		btn404 = new JButton("克羅埃西亞");
		btn413 = new JButton("荷、比、盧");
		btn414 = new JButton("法國");
		
        label = new JLabel("");
		
		btnPanel = new JPanel();
		imgPanel = new JPanel();
		
		btn040.addActionListener(this);
		btn101.addActionListener(this);
		btn393.addActionListener(this);
		btn394.addActionListener(this);
		btn395.addActionListener(this);
		btn396.addActionListener(this);
		btn397.addActionListener(this);
		btn398.addActionListener(this);
		btn404.addActionListener(this);
		btn413.addActionListener(this);
		btn414.addActionListener(this);
		
		Image img = new ImageIcon(this.getClass().getResource("/Europe.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		imgPanel.add(label);
		
		Dimension dim = getPreferredSize();
		dim.height = 200;
		
		btnPanel.setLayout(new GridLayout(3,4));
		btnPanel.setPreferredSize(dim);
		btnPanel.setBorder(BorderFactory.createEtchedBorder());
		
		btnPanel.add(btn394);
		btnPanel.add(btn395);
		btnPanel.add(btn413);
		btnPanel.add(btn397);
		btnPanel.add(btn404);
		btnPanel.add(btn393);
		btnPanel.add(btn414);
		btnPanel.add(btn040);
		btnPanel.add(btn398);
		btnPanel.add(btn396);
		btnPanel.add(btn101);
		
		setLayout(new BorderLayout());
		
		add(btnPanel, BorderLayout.SOUTH);
		add(imgPanel, BorderLayout.NORTH);
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}
	
    //////Set the Action While Clicked//////
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
			
		if(clicked == btn394) {
			if(textListener != null) {
				textListener.textEmitted("瑞士");
			}
		}else if(clicked == btn395){
			if(textListener != null) {
				textListener.textEmitted("波羅的海周邊");
			}
		}else if(clicked == btn413){
			if(textListener != null) {
				textListener.textEmitted("比利時 荷蘭 盧森堡");
			}
		}else if(clicked == btn397){
			if(textListener != null) {
				textListener.textEmitted("希臘");
			}
		}else if(clicked == btn404){
			if(textListener != null) {
				textListener.textEmitted("克羅埃西亞");
			}
		}else if(clicked == btn393){
			if(textListener != null) {
				textListener.textEmitted("奧地利");
			}
		}else if(clicked == btn414){
			if(textListener != null) {
				textListener.textEmitted("法國");
			}
		}else if(clicked == btn040){
			if(textListener != null) {
				textListener.textEmitted("丹麥 挪威 瑞典 芬蘭");
			}
		}else if(clicked == btn398){
			if(textListener != null) {
				textListener.textEmitted("葡萄牙 西班牙");
			}
		}else if(clicked == btn396){
			if(textListener != null) {
				textListener.textEmitted("冰島");
			}
		}else if(clicked == btn101){
			if(textListener != null) {
				textListener.textEmitted("捷克 波蘭 匈牙利");
			}
		}
	}
}
