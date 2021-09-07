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

public class SEAsiaPanel extends JPanel implements ActionListener {

	private JButton btn384;
	private JButton btn391;
	private JButton btn392;
	private JButton btn399;
	private JButton btn405;
	private JButton btn406;
	private JButton btn410;
	private JButton btn411;
	private JButton btn416;
	private JButton btn417;
	
	private StringListener textListener;
	
    private JLabel label;
	
	private JPanel btnPanel;
	private JPanel imgPanel;
	
	public SEAsiaPanel() {
		
		Border innerBorder = BorderFactory.createTitledBorder("東南亞");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		btn384 = new JButton("斯里蘭卡");
		btn391 = new JButton("柬埔寨");
		btn392 = new JButton("緬甸、寮國");
		btn399 = new JButton("尼泊爾、不丹");
		btn405 = new JButton("泰國、華欣");
		btn406 = new JButton("泰國、蘇梅");
		btn410 = new JButton("巴拉望");
		btn411 = new JButton("菲律賓、宿霧");
		btn416 = new JButton("馬來西亞");
		btn417 = new JButton("汶萊");
		
		label = new JLabel("");
		
		btnPanel = new JPanel();
		imgPanel = new JPanel();
		
		btn384.addActionListener(this);
		btn391.addActionListener(this);
		btn392.addActionListener(this);
		btn399.addActionListener(this);
		btn405.addActionListener(this);
		btn406.addActionListener(this);
		btn410.addActionListener(this);
		btn411.addActionListener(this);
		btn416.addActionListener(this);
		btn417.addActionListener(this);
		
		Image img = new ImageIcon(this.getClass().getResource("/SEAsia.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		imgPanel.add(label);
		
		Dimension dim = getPreferredSize();
		dim.height = 200;
		
		btnPanel.setLayout(new GridLayout(3,4));
		btnPanel.setPreferredSize(dim);
		btnPanel.setBorder(BorderFactory.createEtchedBorder());
		
		btnPanel.add(btn384);
		btnPanel.add(btn399);
		btnPanel.add(btn405);
		btnPanel.add(btn391);
		btnPanel.add(btn392);
		btnPanel.add(btn416);
		btnPanel.add(btn411);
		btnPanel.add(btn406);
		btnPanel.add(btn410);
		btnPanel.add(btn417);
		
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
			
		if(clicked == btn384) {
			if(textListener != null) {
				textListener.textEmitted("斯里蘭卡");
			}
		}else if(clicked == btn399){
			if(textListener != null) {
				textListener.textEmitted("尼泊爾 不丹");
			}
		}else if(clicked == btn405){
			if(textListener != null) {
				textListener.textEmitted("華欣");
			}
		}else if(clicked == btn391){
			if(textListener != null) {
				textListener.textEmitted("柬埔寨 吳哥窟 金邊");
			}
		}else if(clicked == btn392){
			if(textListener != null) {
				textListener.textEmitted("緬甸 寮國");
			}
		}else if(clicked == btn416){
			if(textListener != null) {
				textListener.textEmitted("檳城 蘭卡威");
			}
		}else if(clicked == btn411){
			if(textListener != null) {
				textListener.textEmitted("宿霧 薄荷島");
			}
		}else if(clicked == btn406){
			if(textListener != null) {
				textListener.textEmitted("蘇梅島");
			}
		}else if(clicked == btn410){
			if(textListener != null) {
				textListener.textEmitted("巴拉望");
			}
		}else if(clicked == btn417){
			if(textListener != null) {
				textListener.textEmitted("汶萊");
			}
		}
	}
}
