package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class SearchTourPanel extends JPanel {
	
	private JLabel earlyBeginDateLabel;
	private JLabel destinationLabel;
	private JTextField destinationField;
	private JButton chooseBtn;
	private JButton submitBtn;
	
	private DateChooser dateChooser1;
	private DateChooser dateChooser2;
	
	private SearchTourListener searchTourListener;
	
	public SearchTourPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		
		Border innerBorder = BorderFactory.createTitledBorder("搜尋行程");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
	    //////Create Elements on Member Panel//////
		earlyBeginDateLabel = new JLabel("選擇出發日期: ");
		destinationLabel = new JLabel("目的地: ");
		destinationField = new JTextField(10);
		chooseBtn = new JButton("選擇日期");
		submitBtn = new JButton("提交");
		
		////////
		chooseBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dateChooser1 = DateChooser.getInstance("yyyyMMdd");
		        dateChooser2 = DateChooser.getInstance("yyyyMMdd");
		        JTextField showDate1 = new JTextField("選擇最早出發日期");
		        JTextField showDate2 = new JTextField("選擇最晚出發日期");

		        dateChooser1.register(showDate1);
		        dateChooser2.register(showDate2);

		        JFrame jf = new JFrame("選擇出發日期區間");
		        jf.add(showDate1, BorderLayout.NORTH);
		        jf.add(showDate2, BorderLayout.CENTER);
		        jf.pack();
		        jf.setLocationRelativeTo(null);
		        jf.setVisible(true);
			}
		});
		
	    //////Action While "提交" was Clicked//////
		submitBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String earlyBeginDate = dateChooser1.getChosenDate();
				String lateBeginDate = dateChooser2.getChosenDate();
				String destination = destinationField.getText();
				
				if(earlyBeginDate.equals("")||lateBeginDate.equals("")||destination.equals("")) {
					JOptionPane.showMessageDialog(null, "所有欄位皆不可為空白",
							" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
				}else {
					if(isDate2NotBeforeDate1(earlyBeginDate, lateBeginDate)) {
						SearchTourEvent ev = new SearchTourEvent(this, earlyBeginDate, 
								lateBeginDate, destination);
						
						destinationField.setText("");
						
						if(searchTourListener != null) {
							searchTourListener.searchTourEventOccurred(ev);
						}
					}else {
						JOptionPane.showMessageDialog(null, "最晚出發日期須晚於最早出發日期",
								" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
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
		gc.anchor = GridBagConstraints.LINE_END;
		add(earlyBeginDateLabel, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(chooseBtn, gc);
			
		//////Second Row//////
		gc.gridx = 0;
		gc.gridy = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(destinationLabel, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(destinationField, gc);
			
	    //////Third Row//////
		gc.gridx = 1;
		gc.gridy = 3;
		gc.weighty = 15;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(submitBtn, gc);//SearchTrip
	}
	public void setSearchTourListener(SearchTourListener listener) {
		this.searchTourListener = listener;
	}
	
	public boolean isDate2NotBeforeDate1(String date1, String date2) {
		int y1 = Integer.parseInt(date1.substring(0, 4));
	    int m1 = Integer.parseInt(date1.substring(4, 6));
	    int d1 = Integer.parseInt(date1.substring(6, 8));
	    int y2 = Integer.parseInt(date2.substring(0, 4));
	    int m2 = Integer.parseInt(date2.substring(4, 6));
	    int d2 = Integer.parseInt(date2.substring(6, 8));
	
	    if(y1 > y2) {
	        return false;
	    }
	    else if(y1 == y2) {
	        if(m1 > m2) {
	            return false;
	        }
	        else if(m1 == m2) {
	            return d1 <= d2;
	        }
	        else {
	            return true;
	        }
	    }
	    else {
	        return true;
	    }
	}
}
