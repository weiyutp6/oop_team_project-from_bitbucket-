package Gui;

import java.awt.*;
import javax.swing.*;
import chosenTopic.*;

public class MainFrame extends JFrame{
	
	private boolean login = false;
	
	private ToolBar toolBar;
	private FormPanel formPanel;
	
	//////上方按鈕控制//////
	private HomePanel homePanel;
	private OrderPanel orderPanel;
	private SearchTourPanel searchTourPanel;
	private MemberLoginPanel memberLoginPanel;
	private MemberLogedinPanel memberLogedinPanel;
	private TripPanel tripPanel;
	private RTripPanel rTripPanel;
	private STripPanel sTripPanel;
	private SingleTripPanel singleTripPanel;
	
	//////左側目的地選單，某些選項範圍太大，提供更細選項//////
	private ChinaPanel chinaPanel;
	private EuropePanel europePanel;
	private SEAsiaPanel seasiaPanel;
	
	private JPanel CLayOutForSouthEast;
	private CardLayout layout;
	
	public MainFrame() {
		super("歡迎使用 yee遊網 旅遊系統");
		
		setLayout(new BorderLayout());
		
		//////Create Elements//////
		formPanel = new FormPanel();
		
		homePanel = new HomePanel();
		searchTourPanel = new SearchTourPanel();
		memberLoginPanel = new MemberLoginPanel();
		memberLogedinPanel = new MemberLogedinPanel();
		
		chinaPanel = new ChinaPanel();
		europePanel = new EuropePanel();
		seasiaPanel = new SEAsiaPanel();
		
		CLayOutForSouthEast = new JPanel();
		
		toolBar = new ToolBar();
		
		//////CardLayout Including Most Functions on the South East of the Window//////
		CLayOutForSouthEast = new JPanel();
		layout = new CardLayout();
		
		CLayOutForSouthEast.setLayout(layout);
		
		CLayOutForSouthEast.add("Home", homePanel);
		CLayOutForSouthEast.add("Search", searchTourPanel);
		CLayOutForSouthEast.add("MemberLogin", memberLoginPanel);
		CLayOutForSouthEast.add("MemberLogedin", memberLogedinPanel);
		
		CLayOutForSouthEast.add("China", chinaPanel);
		CLayOutForSouthEast.add("Europe", europePanel);
		CLayOutForSouthEast.add("SEAsia", seasiaPanel);
		
		//////Four Functions on the Top of the Window (ToolBar)//////
		toolBar.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				if(text.equals("Home\n")) {
					layout.show(CLayOutForSouthEast, "Home");
				}else if(text.equals("Search\n")) {
					layout.show(CLayOutForSouthEast, "Search");
				}else if(text.equals("Order\n")) {
					if(login==false) {
						JOptionPane.showMessageDialog(null, "欲查看訂單請先登入",
								" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
						layout.show(CLayOutForSouthEast, "MemberLogin");
					}else if(login==true) {
						OrdersList ordersList = new OrdersList(memberLoginPanel.MemberID);
						Object[] list = ordersList.getList();
						
						orderPanel = new OrderPanel(list, memberLoginPanel.MemberID);
						CLayOutForSouthEast.add("Order", orderPanel);
						layout.show(CLayOutForSouthEast, "Order");
						
					    //////Function "修改訂單" in "查看訂單"//////
						orderPanel.setStringListener(new StringListener() {
							public void textEmitted(String text) {
								if(text.equals("updateBtn\n")) {
									layout.show(CLayOutForSouthEast, "Home");
								}
							}
						});
					}
				}else if(text.equals("Member\n")){
					if(login == false) {
						layout.show(CLayOutForSouthEast, "MemberLogin");
					}else if(login == true) {
						layout.show(CLayOutForSouthEast, "MemberLogedin");
					}
				}
			}
		});
		
		//////Ten Destinations on the Left Hand Side of the Window (FormPanel)//////
		formPanel.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				if(text.equals("中國")) {
					layout.show(CLayOutForSouthEast, "China");
				}else if(text.equals("歐洲")) {
					layout.show(CLayOutForSouthEast, "Europe");
				}else if(text.equals("東南亞")) {
					layout.show(CLayOutForSouthEast, "SEAsia");
				}else {
					AvailableTripsList tripList = new AvailableTripsList();
					Object[] tripOfList = tripList.searchByArea(text, "20200623", "20201231");
					
					sTripPanel = new STripPanel(tripOfList);//送去查看可報名行程
					CLayOutForSouthEast.add("STrip", sTripPanel);
					layout.show(CLayOutForSouthEast, "STrip");
					
					if(tripOfList.length == 0) {
			        	JOptionPane.showMessageDialog(null, "選擇的日期地點無開團資訊",
								" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
			        }
				    //////Function ("我要報名") in "查看可報名行程"//////
					sTripPanel.setStringListener(new StringListener() {
						public void textEmitted(String text) {
							if(text.equals("orderBtnInSTrip\n")) {
								if(login==false) {
									JOptionPane.showMessageDialog(null, "欲報名請先登入",
											" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
									layout.show(CLayOutForSouthEast, "MemberLogin");
								}else if(login==true) {
									Object[] tripOfList = tripList.detailedList(sTripPanel.tripName, "20200623", "20201231");
									
									singleTripPanel = new SingleTripPanel(tripOfList, memberLoginPanel.MemberID, sTripPanel.tripName);//送去查看可報名行程
									CLayOutForSouthEast.add("SingleTrip", singleTripPanel);
									layout.show(CLayOutForSouthEast, "SingleTrip");
									
									//////Function "我要報名" in "查看(單一)行程"//////
									singleTripPanel.setStringListener(new StringListener() {
										public void textEmitted(String text) {
											if(text.equals("orderBtnInSTrip\n")) {
												layout.show(CLayOutForSouthEast, "STrip");
											}
										}
									});
								}
							}
						}
					});
				}
			}
		});
		
	    //////Buttons on ChinaPanel//////
		chinaPanel.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				AvailableTripsList tripList = new AvailableTripsList();
				Object[] tripOfList = tripList.searchByArea(text, "20200623", "20201231");
				
				sTripPanel = new STripPanel(tripOfList);//送去查看可報名行程
				CLayOutForSouthEast.add("STrip", sTripPanel);
				layout.show(CLayOutForSouthEast, "STrip");
				
				if(tripOfList.length == 0) {
		        	JOptionPane.showMessageDialog(null, "選擇的日期地點無開團資訊",
							" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
		        }
			    //////Function ("我要報名") in "查看可報名行程"//////
				sTripPanel.setStringListener(new StringListener() {
					public void textEmitted(String text) {
						if(text.equals("orderBtnInSTrip\n")) {
							if(login==false) {
								JOptionPane.showMessageDialog(null, "欲報名請先登入",
										" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
								layout.show(CLayOutForSouthEast, "MemberLogin");
							}else if(login==true) {
								Object[] tripOfList = tripList.detailedList(sTripPanel.tripName, "20200623", "20201231");
								
								singleTripPanel = new SingleTripPanel(tripOfList, memberLoginPanel.MemberID, sTripPanel.tripName);//送去查看可報名行程
								CLayOutForSouthEast.add("SingleTrip", singleTripPanel);
								layout.show(CLayOutForSouthEast, "SingleTrip");
								
							    //////Function "我要報名" in "查看(單一)行程"//////
								singleTripPanel.setStringListener(new StringListener() {
									public void textEmitted(String text) {
										if(text.equals("orderBtnInSTrip\n")) {
											layout.show(CLayOutForSouthEast, "STrip");
										}
									}
								});
							}
						}
					}
				});
			}
		});
		
	    //////Buttons on SEAsiaPanel//////
		seasiaPanel.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				AvailableTripsList tripList = new AvailableTripsList();
				Object[] tripOfList = tripList.searchByArea(text, "20200623", "20201231");
				
				sTripPanel = new STripPanel(tripOfList);//送去查看可報名行程
				CLayOutForSouthEast.add("STrip", sTripPanel);
				layout.show(CLayOutForSouthEast, "STrip");
				
				if(tripOfList.length == 0) {
		        	JOptionPane.showMessageDialog(null, "選擇的日期地點無開團資訊",
							" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
		        }
			    //////Function ("我要報名") in "查看可報名行程"//////
				sTripPanel.setStringListener(new StringListener() {
					public void textEmitted(String text) {
						if(text.equals("orderBtnInSTrip\n")) {
							if(login==false) {
								JOptionPane.showMessageDialog(null, "欲報名請先登入",
										" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
								layout.show(CLayOutForSouthEast, "MemberLogin");
							}else if(login==true) {
								Object[] tripOfList = tripList.detailedList(sTripPanel.tripName, "20200623", "20201231");
								
								singleTripPanel = new SingleTripPanel(tripOfList, memberLoginPanel.MemberID, sTripPanel.tripName);//送去查看可報名行程
								CLayOutForSouthEast.add("SingleTrip", singleTripPanel);
								layout.show(CLayOutForSouthEast, "SingleTrip");
								
							    //////Function "我要報名" in "查看(單一)行程"//////
								singleTripPanel.setStringListener(new StringListener() {
									public void textEmitted(String text) {
										if(text.equals("orderBtnInSTrip\n")) {
											layout.show(CLayOutForSouthEast, "STrip");
										}
									}
								});
							}
						}
					}
				});
			}
		});
		
	   //////Buttons on EuropePanel//////
		europePanel.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				AvailableTripsList tripList = new AvailableTripsList();
				Object[] tripOfList = tripList.searchByArea(text, "20200623", "20201231");
				
				sTripPanel = new STripPanel(tripOfList);//送去查看可報名行程
				CLayOutForSouthEast.add("STrip", sTripPanel);
				layout.show(CLayOutForSouthEast, "STrip");
				
				if(tripOfList.length == 0) {
		        	JOptionPane.showMessageDialog(null, "選擇的日期地點無開團資訊",
							" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
		        }
			    //////Function ("我要報名") in "查看可報名行程"//////
				sTripPanel.setStringListener(new StringListener() {
					public void textEmitted(String text) {
						if(text.equals("orderBtnInSTrip\n")) {
							if(login==false) {
								JOptionPane.showMessageDialog(null, "欲報名請先登入",
										" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
								layout.show(CLayOutForSouthEast, "MemberLogin");
							}else if(login==true) {
								Object[] tripOfList = tripList.detailedList(sTripPanel.tripName, "20200623", "20201231");
								
								singleTripPanel = new SingleTripPanel(tripOfList, memberLoginPanel.MemberID, sTripPanel.tripName);//送去查看可報名行程
								CLayOutForSouthEast.add("SingleTrip", singleTripPanel);
								layout.show(CLayOutForSouthEast, "SingleTrip");
								
							    //////Function "我要報名" in "查看(單一)行程"//////
								singleTripPanel.setStringListener(new StringListener() {
									public void textEmitted(String text) {
										if(text.equals("orderBtnInSTrip\n")) {
											layout.show(CLayOutForSouthEast, "STrip");
										}
									}
								});
							}
						}
					}
				});
			}
		});
		
	    //////Function "登入" in "會員管理"//////
		memberLoginPanel.setMemberLoginListener(new MemberLoginListener() {
			public void memberLoginEventOccurred(MemberLoginEvent e) {
				String account = e.getAccount();
				String passWord = e.getPassWord();
				
				if(LogIn.logIn(account,  passWord).equals("登入成功!")) {
					login = true;
					memberLoginPanel.MemberID = account;
					layout.show(CLayOutForSouthEast, "Home");
					JOptionPane.showMessageDialog(null, "登入成功!",
							" yee遊網 旅遊系統", JOptionPane.INFORMATION_MESSAGE);
				}else if(LogIn.logIn(account,  passWord).equals("密碼錯誤!")){
					JOptionPane.showMessageDialog(null, "密碼錯誤!",
							" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
				}else if(LogIn.logIn(account,  passWord).equals("無此帳號ID!")){
					JOptionPane.showMessageDialog(null, "無此帳號ID!",
							" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
				}
			}
		});
		
	    //////Function "提交" in "會員管理"//////
		memberLoginPanel.setMemberCreateListener(new MemberCreateListener() {
			public void memberCreateEventOccurred(MemberCreateEvent e) {
				String name = e.getName();
				String citizenID = e.getCitizenID();
				String ID = e.getID();
				String passWordCreate = e.getPassWordCreate();
				String passWordConfirm = e.getPassWordConfirm();
				
				Register newMember = new Register(ID, name, passWordCreate, citizenID, passWordConfirm);
				if(newMember.newMember().equals("註冊成功")) {
					JOptionPane.showMessageDialog(null, "會員註冊成功",
							" yee遊網 旅遊系統", JOptionPane.INFORMATION_MESSAGE);
					layout.show(CLayOutForSouthEast, "Home");
				}else {
					JOptionPane.showMessageDialog(null, "會員註冊失敗",
							" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
				}
			}
		});
		
	    //////Function "登出" in "會員管理"//////
		memberLogedinPanel.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				login = false;
				layout.show(CLayOutForSouthEast, "Home");
			}
		});
		
	    //////Function "提交" in "會員管理"//////
		memberLogedinPanel.setMemberLogedinListener(new MemberLogedinListener() {
			public void memberLogedinEventOccurred(MemberLogedinEvent e) {
				String prePass = e.getPrePass();
				String newPass = e.getNewPass();
				String conPass = e.getConPass();
				
				UpdatePwd NewPass = new UpdatePwd(memberLoginPanel.MemberID);
				if(NewPass.updatePwd(prePass, newPass, conPass).equals("更改成功")){
					JOptionPane.showMessageDialog(null, "密碼修改成功",
							" yee遊網 旅遊系統", JOptionPane.INFORMATION_MESSAGE);
					layout.show(CLayOutForSouthEast, "Home");
				}else {
					JOptionPane.showMessageDialog(null, "密碼修改失敗",
							" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
				}
			}
		});
		
	    //////Function "提交" in "搜尋行程"//////
		searchTourPanel.setSearchTourListener(new SearchTourListener() {
			public void searchTourEventOccurred(SearchTourEvent e) {
				String earlyBeginDate = e.getEarlyBeginDate();
				String lateBeginDate = e.getLateBeginDate();
				String destination = e.getDestination();
				
				AvailableTripsList tripList01 = new AvailableTripsList();
				Object[] tripOfList01 = tripList01.searchByKeyWord(destination, earlyBeginDate, lateBeginDate);
				
				AvailableTripsList tripList02 = new AvailableTripsList();
				Object[] tripOfList02 = tripList02.searchByArea(destination, earlyBeginDate, lateBeginDate);
				
				tripPanel = new TripPanel(tripOfList01);//送去查看可報名行程
				rTripPanel = new RTripPanel(tripOfList02);//送去查看相關行程
				CLayOutForSouthEast.add("Trip", tripPanel);
				CLayOutForSouthEast.add("RTrip", rTripPanel);
				layout.show(CLayOutForSouthEast, "Trip");
				
				if(tripOfList01.length == 0) {
		        	JOptionPane.showMessageDialog(null, "選擇的日期地點無開團資訊",
							" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
		        }
			    //////Function ("我要報名"、"查看相關行程") in "查看可報名行程"//////
				tripPanel.setStringListener(new StringListener() {
					public void textEmitted(String text) {
						if(text.equals("orderBtnInTrip\n")) {
							if(login==false) {
								JOptionPane.showMessageDialog(null, "欲報名請先登入",
										" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
								layout.show(CLayOutForSouthEast, "MemberLogin");
							}else if(login==true) {
								Object[] tripOfList = tripList01.detailedList(tripPanel.tripName, earlyBeginDate, lateBeginDate);
								
								singleTripPanel = new SingleTripPanel(tripOfList, memberLoginPanel.MemberID, tripPanel.tripName);//送去查看可報名行程
								CLayOutForSouthEast.add("SingleTrip", singleTripPanel);
								layout.show(CLayOutForSouthEast, "SingleTrip");
								
							    //////Function "我要報名" in "查看(單一)行程"//////
								singleTripPanel.setStringListener(new StringListener() {
									public void textEmitted(String text) {
										if(text.equals("orderBtnInSTrip\n")) {
											layout.show(CLayOutForSouthEast, "Trip");
										}
									}
								});
							}
						}else if(text.equals("relateBtnInTrip\n")) {
							layout.show(CLayOutForSouthEast, "RTrip");
							
							if(tripOfList02.length == 0) {
					        	JOptionPane.showMessageDialog(null, "選擇的日期地點無開團資訊",
										" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
					        }
						}
					}
				});
			    //////Function ("我要報名"、"查看可報名行程") in "查看相關行程"//////
				rTripPanel.setStringListener(new StringListener() {
					public void textEmitted(String text) {
						if(text.equals("orderBtnInRTrip\n")) {
							if(login==false) {
								JOptionPane.showMessageDialog(null, "欲報名請先登入",
											" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
								layout.show(CLayOutForSouthEast, "MemberLogin");
							}else if(login==true) {
								Object[] tripOfList = tripList02.detailedList(rTripPanel.tripName, earlyBeginDate, lateBeginDate);//
								
								singleTripPanel = new SingleTripPanel(tripOfList, memberLoginPanel.MemberID, rTripPanel.tripName);//送去查看可報名行程
								CLayOutForSouthEast.add("SingleTrip", singleTripPanel);
								layout.show(CLayOutForSouthEast, "SingleTrip");
								
							    //////Function "我要報名" in "查看(單一)行程"//////
								singleTripPanel.setStringListener(new StringListener() {
									public void textEmitted(String text) {
										if(text.equals("orderBtnInSTrip\n")) {
											layout.show(CLayOutForSouthEast, "RTrip");
										}
									}
								});
							}
						}else if(text.equals("tripBtnInRTrip\n")) {
							layout.show(CLayOutForSouthEast, "Trip");
							
							if(tripOfList01.length == 0) {
					        	JOptionPane.showMessageDialog(null, "選擇的日期地點無開團資訊",
										" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
					        }
						}
					}
				});
			}
		});
		
		//////Add Panels to the Window//////
		add(toolBar, BorderLayout.NORTH);
		add(formPanel, BorderLayout.WEST);
		add(CLayOutForSouthEast, BorderLayout.CENTER);
		
		setSize(1000, 600);//Size of Window//
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
