package Gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class HomePanel extends JPanel{
	
	private JPanel btnPanel;
	private JButton preBtn;
	private JButton nextBtn;
	
	private JPanel CardLayOutForToolBar;
	private CardLayout layout;
	
	//////Add Photo//////
	private JLabel img01;
	private JLabel img02;
	private JLabel img03;
	private JLabel img04;
	private JLabel img05;
	private JLabel img06;

	public HomePanel() {
		
		Border innerBorder = BorderFactory.createTitledBorder("首頁");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		btnPanel = new JPanel();
		preBtn = new JButton("<<<<");
		nextBtn = new JButton(">>>>");
		
		CardLayOutForToolBar = new JPanel();
        layout = new CardLayout();
		
		CardLayOutForToolBar.setLayout(layout);
		
		img01 = new JLabel();
		img02 = new JLabel();
		img03 = new JLabel();
		img04 = new JLabel();
		img05 = new JLabel();
		img06 = new JLabel();
		
		Image img1 = new ImageIcon(this.getClass().getResource("/img01.jpg")).getImage();
		img01.setIcon(new ImageIcon(img1));
		CardLayOutForToolBar.add(img01, "0001");
		
		Image img2 = new ImageIcon(this.getClass().getResource("/img02.jpg")).getImage();
		img02.setIcon(new ImageIcon(img2));
		CardLayOutForToolBar.add(img02, "0002");
		
		Image img3 = new ImageIcon(this.getClass().getResource("/img03.jpg")).getImage();
		img03.setIcon(new ImageIcon(img3));
		CardLayOutForToolBar.add(img03, "0003");
		
		Image img4 = new ImageIcon(this.getClass().getResource("/img04.jpg")).getImage();
		img04.setIcon(new ImageIcon(img4));
		CardLayOutForToolBar.add(img04, "0004");
		
		Image img5 = new ImageIcon(this.getClass().getResource("/img05.jpg")).getImage();
		img05.setIcon(new ImageIcon(img5));
		CardLayOutForToolBar.add(img05, "0005");
		
		Image img6 = new ImageIcon(this.getClass().getResource("/img06.jpg")).getImage();
		img06.setIcon(new ImageIcon(img6));
		CardLayOutForToolBar.add(img06, "0006");
		
		preBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.previous(CardLayOutForToolBar);
			}
		});
		
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.next(CardLayOutForToolBar);
			}
		});
		
		Dimension dim = getPreferredSize();
		dim.height = 50;
		
		btnPanel.setLayout(new GridLayout(1,2));
		btnPanel.setPreferredSize(dim);
		btnPanel.setBorder(BorderFactory.createEtchedBorder());
		
		btnPanel.add(preBtn);
		btnPanel.add(nextBtn);
		
		setLayout(new BorderLayout());
		
		add(btnPanel, BorderLayout.SOUTH);
		add(CardLayOutForToolBar, BorderLayout.NORTH);
	}
}
