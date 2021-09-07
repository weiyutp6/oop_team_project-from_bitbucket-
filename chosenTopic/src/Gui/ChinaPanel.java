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

public class ChinaPanel extends JPanel implements ActionListener {
	
	private JButton btn426;
	private JButton btn427;
	private JButton btn428;
	private JButton btn430;
	private JButton btn431;
	private JButton btn432;
	private JButton btn433;
	private JButton btn435;
	private JButton btn436;
	private JButton btn439;
	private JButton btn440;
	private JButton btn441;
	private JButton btn442;
	
	private StringListener textListener;
	
	private JLabel label;
	
	private JPanel btnPanel;
	private JPanel imgPanel;

	public ChinaPanel() {
		
		Border innerBorder = BorderFactory.createTitledBorder("中國");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		btn426 = new JButton("北京、天津");
		btn427 = new JButton("山西、太原");
		btn428 = new JButton("大連、瀋陽");
		btn430 = new JButton("長三角");
		btn431 = new JButton("安徽、黃山");
		btn432 = new JButton("山東、泰山");
		btn433 = new JButton("長沙、武漢");
		btn435 = new JButton("河南、開封");
		btn436 = new JButton("廈門、三通");
		btn439 = new JButton("珠三角");
		btn440 = new JButton("四川、重慶");
		btn441 = new JButton("雲南、昆明");
		btn442 = new JButton("貴州、貴陽");
		
		label = new JLabel("");
		
		btnPanel = new JPanel();
		imgPanel = new JPanel();
		
		btn426.addActionListener(this);
		btn427.addActionListener(this);
		btn428.addActionListener(this);
		btn430.addActionListener(this);
		btn431.addActionListener(this);
		btn432.addActionListener(this);
		btn433.addActionListener(this);
		btn435.addActionListener(this);
		btn436.addActionListener(this);
		btn439.addActionListener(this);
		btn440.addActionListener(this);
		btn441.addActionListener(this);
		btn442.addActionListener(this);
		
		Image img = new ImageIcon(this.getClass().getResource("/china.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		imgPanel.add(label);
		
		Dimension dim = getPreferredSize();
		dim.height = 200;
		
		btnPanel.setLayout(new GridLayout(3,5));
		btnPanel.setPreferredSize(dim);
		btnPanel.setBorder(BorderFactory.createEtchedBorder());
		
		btnPanel.add(btn426);
		btnPanel.add(btn427);
		btnPanel.add(btn428);
		btnPanel.add(btn431);
		btnPanel.add(btn432);
		btnPanel.add(btn433);
		btnPanel.add(btn435);
		btnPanel.add(btn436);
		btnPanel.add(btn439);
		btnPanel.add(btn440);
		btnPanel.add(btn441);
		btnPanel.add(btn442);
		btnPanel.add(btn430);
		
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
			
		if(clicked == btn426) {
			if(textListener != null) {
				textListener.textEmitted("北京 天津 承德");
			}
		}else if(clicked == btn427){
			if(textListener != null) {
				textListener.textEmitted("山西太原");
			}
		}else if(clicked == btn428){
			if(textListener != null) {
				textListener.textEmitted("大連 瀋陽 哈爾濱 北韓");
			}
		}else if(clicked == btn431){
			if(textListener != null) {
				textListener.textEmitted("黃山 千島湖");
			}
		}else if(clicked == btn432){
			if(textListener != null) {
				textListener.textEmitted("山東 清島 濟南 煙台 泰山");
			}
		}else if(clicked == btn433){
			if(textListener != null) {
				textListener.textEmitted("長沙 武漢 張家界 長江三峽");
			}
		}else if(clicked == btn435){
			if(textListener != null) {
				textListener.textEmitted("河南 開封 洛陽 少林寺 龍門石窟");
			}
		}else if(clicked == btn436){
			if(textListener != null) {
				textListener.textEmitted("廈門 小三通");
			}
		}else if(clicked == btn439){
			if(textListener != null) {
				textListener.textEmitted("港澳珠圳");
			}
		}else if(clicked == btn440){
			if(textListener != null) {
				textListener.textEmitted("四川 重慶 成都 九寨溝");
			}
		}else if(clicked == btn441){
			if(textListener != null) {
				textListener.textEmitted("雲南 昆明 大里 麗江");
			}
		}else if(clicked == btn442){
			if(textListener != null) {
				textListener.textEmitted("貴州 貴陽 黃果樹");
			}
		}else if(clicked == btn430){
			if(textListener != null) {
				textListener.textEmitted("江南 上海 杭州 蘇州");
			}
		}
	}
}
