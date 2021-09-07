package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SearchBar extends JPanel implements ActionListener {
	
	private JButton homeButton;
	private JButton trackButton;
	
	private JLabel coName;
	private JLabel coNum;
	private JTextField comNameField;
	private JButton subBtn01;
	private JTextField comNumField;
	private JButton subBtn02;
	private Gui.SearchBarNameListener SearchBarNameListener;
	private Gui.SearchBarNumListener SearchBarNumListener;
	
	private StringListener textListener;
	
	public SearchBar(){
		
		setBorder(BorderFactory.createEtchedBorder());
		
		homeButton = new JButton("首頁");
		trackButton = new JButton("持有股票");
		coName = new JLabel("股票名稱");
		coNum = new JLabel("股票代碼");
		comNameField = new JTextField(10);
		subBtn01 = new JButton("提交");
		comNumField = new JTextField(10);
		subBtn02 = new JButton("提交");
		
		homeButton.addActionListener(this);
		trackButton.addActionListener(this);
		
        //////Set the Location of Elements//////
		setLayout(new GridBagLayout());
				
		GridBagConstraints gc = new GridBagConstraints();
		
	    //////First Row//////
		gc.gridx = 2;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(coName, gc);
		
		gc.gridx = 4;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(coNum, gc);
		
	    //////Second Row//////
		gc.gridx = 0;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(homeButton, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(trackButton, gc);
		
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(comNameField, gc);
		
		gc.gridx = 3;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(subBtn01, gc);
		
		gc.gridx = 4;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(comNumField, gc);
		
		gc.gridx = 5;
		gc.weightx = 40;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(subBtn02, gc);
		
	    //////Action While "提交(subBtn01)" was Clicked//////
		subBtn01.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String comName = comNameField.getText();
				
				if(comName.equals("")) {
					JOptionPane.showMessageDialog(null, "公司名稱不可為空白",
							"YeeToro", JOptionPane.OK_OPTION);
				}else {
					SearchBarNameEvent ev = new SearchBarNameEvent(this, comName);
					
					comNameField.setText("");
					
					if(SearchBarNameListener != null) {
						SearchBarNameListener.SearchBarNameEventOccurred(ev);
					}
				}
			}
		});
		
	    //////Action While "提交(subBtn02)" was Clicked//////
		subBtn02.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String comNum = comNumField.getText();
				
				if(comNum.equals("")) {
					JOptionPane.showMessageDialog(null, "公司代碼不可為空白",
							"YeeToro", JOptionPane.OK_OPTION);
				}else {
					SearchBarNumEvent ev = new SearchBarNumEvent(this, comNum);
					
					comNumField.setText("");
					
					if(SearchBarNumListener != null) {
						try {
							SearchBarNumListener.SearchBarNumEventOccurred(ev);
						} catch (IOException ex) {
							ex.printStackTrace();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});
	}
	public void setSearchBarNameListener(Gui.SearchBarNameListener listener) {
		this.SearchBarNameListener = listener;
	}
	
	public void setSearchBarNumListener(Gui.SearchBarNumListener listener) {
		this.SearchBarNumListener = listener;
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}

	//////Set the Action While Clicked//////
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == homeButton) {
			if(textListener != null) {
				try {
					textListener.textEmitted("Home\n");
				} catch (IOException ex) {
					ex.printStackTrace();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == trackButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("Track\n");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
