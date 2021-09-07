package Gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class MemberLoginPanel extends JPanel{
	
	public String MemberID;
	
	private JLabel loginLabel;
	private JLabel accountLabel;
	private JLabel passWordLabel;
	private JTextField accountField;
	private JPasswordField passWordField;
	private JButton submitBtn;
	private JLabel createAccountLabel;
	private JLabel nameLabel;
	private JLabel citizenIDLabel;
	private JLabel IDLabel;
	private JLabel passWordCreateLabel;
	private JLabel passWordConfirmLabel;
	private JTextField nameField;
	private JTextField citizenIDField;
	private JTextField IDField;
	private JPasswordField passWordCreateField;
	private JPasswordField passWordConfirmField;
	private JButton createAccountBtn;
	
	private MemberLoginListener memberLoginListener;
	private MemberCreateListener memberCreateListener;

	public MemberLoginPanel() {
		
		Border innerBorder = BorderFactory.createTitledBorder("會員管理");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		//////Create Elements on Member Panel//////
		loginLabel = new JLabel("會員登入");
		accountLabel = new JLabel("帳號: ");
		passWordLabel = new JLabel("密碼: ");
		accountField = new JTextField(10);
		passWordField = new JPasswordField(10);
		submitBtn = new JButton("登入");
		createAccountLabel = new JLabel("建立帳號");
		nameLabel = new JLabel("姓名:");
		citizenIDLabel = new JLabel("身分證字號:");
		IDLabel = new JLabel("帳號名稱:");
		passWordCreateLabel = new JLabel("設定密碼:");
		passWordConfirmLabel = new JLabel("確認密碼:");
		nameField = new JTextField(10);
		citizenIDField = new JTextField(10);
		IDField = new JTextField(10);
		passWordCreateField = new JPasswordField(10);
		passWordConfirmField = new JPasswordField(10);
		createAccountBtn = new JButton("提交");
		
		//////Action While "登入" was Clicked//////
		submitBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String account = accountField.getText();
				String passWord = passWordField.getText();
				
				if(account.equals("")||passWord.equals("")) {
					JOptionPane.showMessageDialog(null, "所有欄位皆不可為空白",
							" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
				}else {
					MemberLoginEvent ev = new MemberLoginEvent(this, account, passWord);
					
					accountField.setText("");
					passWordField.setText("");
					
					if(memberLoginListener != null) {
						memberLoginListener.memberLoginEventOccurred(ev);
					}
				}
			}
			
		});
		
	    //////Action While "提交" was Clicked//////
		createAccountBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String citizenID = citizenIDField.getText();
				String ID = IDField.getText();
				String passWordCreate = passWordCreateField.getText();
				String passWordConfirm = passWordConfirmField.getText();
				
				if(name.equals("")||citizenID.equals("")||ID.equals("")||
						passWordCreate.equals("")||passWordConfirm.equals("")) {
					JOptionPane.showMessageDialog(null, "所有欄位皆不可為空白",
							" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
				}else {
					if(!(passWordCreate.equals(passWordConfirm))) {
						JOptionPane.showMessageDialog(null, "設定密碼及確認密碼必須相同",
								" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
					}else {
						MemberCreateEvent ev = new MemberCreateEvent(this, name, citizenID,
								ID, passWordCreate, passWordConfirm);
						
						nameField.setText("");
						citizenIDField.setText("");
						IDField.setText("");
						passWordCreateField.setText("");
						passWordConfirmField.setText("");
						
						if(memberCreateListener != null) {
							memberCreateListener.memberCreateEventOccurred(ev);
					    }
					}
				}
			}		
		});
		
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
		add(loginLabel, gc);
		
	    //////Second Row//////
		gc.gridx = 0;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(accountLabel, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(accountField, gc);
		
	    //////Third Row//////
		gc.gridx = 0;
		gc.gridy = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(passWordLabel, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(passWordField, gc);
		
	    //////Fourth Row//////
		gc.gridx = 1;
		gc.gridy = 3;
		gc.weighty = 10;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(submitBtn, gc);//LogIn
		
	    //////Fifth Row//////
		gc.gridx = 0;
		gc.gridy = 4;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(createAccountLabel, gc);
		
	    //////Sixth Row//////
		gc.gridx = 0;
		gc.gridy = 5;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);
		
	    //////Seventh Row//////
		gc.gridx = 0;
		gc.gridy = 6;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(citizenIDLabel, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(citizenIDField, gc);
		
	    //////Eighth Row//////
		gc.gridx = 0;
		gc.gridy = 7;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(IDLabel, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(IDField, gc);
		
	    //////Ninth Row//////
		gc.gridx = 0;
		gc.gridy = 8;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(passWordCreateLabel, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(passWordCreateField, gc);
		
	    //////Tenth Row//////
		gc.gridx = 0;
		gc.gridy = 9;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(passWordConfirmLabel, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(passWordConfirmField, gc);
		
	    //////Eleventh Row//////
		gc.gridx = 1;
		gc.gridy = 10;
		gc.weighty = 10;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(createAccountBtn, gc);//Create Account
	}
	
	public void setMemberLoginListener(MemberLoginListener listener) {
		this.memberLoginListener = listener;
	}
	
	public void setMemberCreateListener(MemberCreateListener listener) {
		this.memberCreateListener = listener;
	}
}
