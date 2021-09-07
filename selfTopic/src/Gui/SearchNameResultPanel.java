package Gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchNameResultPanel extends JPanel {

	public String companyNum;
	
	private JTable table;
	private JButton detailButton;
	
	private StringListener textListener01;

	public SearchNameResultPanel (Object[] List) {
		
		Border innerBorder = BorderFactory.createTitledBorder("由產業別分類");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		detailButton = new JButton("查看公司資料");
		
		//////表格內容(搜尋結果)//////
        String[] columNames = {"公司代碼", "公司名稱"};
		
        DefaultTableModel model = new DefaultTableModel(columNames, 0);
        
        for(int i=0; i<List.length; i++) {
//        	Stock stock = Stock.searchStockByStockCode((String) List[i][0]);
        	model.addRow(new Object[] {((String[]) List[i])[0], ((String[]) List[i])[1]});
        }
		
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(600, 400));
		table.setFillsViewportHeight(true);
		
		table.setRowHeight(30);
		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(40);
		columnModel.getColumn(1).setPreferredWidth(80);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		table.setAutoCreateRowSorter(true);
		
		 //////按下"查看公司資料"的行為//////
		detailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "請選擇欲查看公司",
							"YeeToro", JOptionPane.OK_OPTION);
				}else {
                    JButton clicked = (JButton)e.getSource();
					if(clicked == detailButton) {
						companyNum = (String) model.getValueAt(table.getSelectedRow(), 0);
						try {
							textListener01.textEmitted("detailButton\n");
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}	
				}
			}
		});
		
		add(scrollPane, BorderLayout.CENTER);
		add(detailButton, BorderLayout.SOUTH);
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener01 = listener;
	}
}
