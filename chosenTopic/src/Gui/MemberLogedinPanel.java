package Gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class MemberLogedinPanel extends JPanel implements ActionListener{
	
	private JLabel changeLabel;
	private JLabel prePassLabel;
	private JLabel newPassLabel;
	private JLabel conPassLabel;
	private JPasswordField prePassField;
	private JPasswordField newPassField;
	private JPasswordField conPassField;
	private JButton resetBtn;
	private JButton logoutBtn;
	
	private StringListener textListener;
	
	private MemberLogedinListener memberLogedinListener;

	public MemberLogedinPanel() {
		
		Border innerBorder = BorderFactory.createTitledBorder("會員管理");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		//////Create Elements on Member Panel//////
		changeLabel = new JLabel("修改密碼");
		prePassLabel = new JLabel("原始密碼: ");
		newPassLabel = new JLabel("新的密碼: ");
		conPassLabel = new JLabel("確認密碼: ");
		prePassField = new JPasswordField(10);
		newPassField = new JPasswordField(10);
		conPassField = new JPasswordField(10);
		resetBtn = new JButton("修改密碼");
		logoutBtn = new JButton("登出");
		
		//////Action While "修改密碼" was Clicked//////
		resetBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String prePass = prePassField.getText();
				String newPass = newPassField.getText();
				String conPass = conPassField.getText();
				
				if(prePass.equals("")||newPass.equals("")||conPass.equals("")) {
					JOptionPane.showMessageDialog(null, "所有欄位皆不可為空白",
							" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
				}else {
					if(!(newPass.equals(conPass))) {
						JOptionPane.showMessageDialog(null, "設定密碼及確認密碼必須相同",
								" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
					}else {
						MemberLogedinEvent ev = new MemberLogedinEvent(this, prePass, newPass, conPass);
						
						prePassField.setText("");
						newPassField.setText("");
						conPassField.setText("");
						
						if(memberLogedinListener != null) {
							memberLogedinListener.memberLogedinEventOccurred(ev);
						}
					}
				}
			}
			
		});
		
	    //////Action While "登出" was Clicked//////
		logoutBtn.addActionListener(this);
		
		//////Set the Location of Elements//////
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		//////First Row//////
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(changeLabel, gc);
		
	    //////Second Row//////
		gc.gridx = 0;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(prePassLabel, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(prePassField, gc);
		
	    //////Third Row//////
		gc.gridx = 0;
		gc.gridy = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(newPassLabel, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(newPassField, gc);
		
	    //////Fourth Row//////
		gc.gridx = 0;
		gc.gridy = 3;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(conPassLabel, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(conPassField, gc);
		
	    //////Fifth Row//////
		gc.gridx = 1;
		gc.gridy = 4;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(resetBtn, gc);
		
	    //////Sixth Row//////
		gc.gridx = 1;
		gc.gridy = 5;
		gc.weighty = 30;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(logoutBtn, gc);
	}
	
	public void setMemberLogedinListener(MemberLogedinListener listener) {
		this.memberLogedinListener = listener;
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
			
		if(clicked == logoutBtn) {
			if(textListener != null) {
				textListener.textEmitted("logout");
			}
		}
	}
}
