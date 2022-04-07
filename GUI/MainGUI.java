//사용자 GUI
//자리 선택을 할 수 있음
package pc;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.*;

public class MainGUI extends JFrame {
	public MainGUI() {
		setTitle("PC방");
		setBounds(10, 50, 650, 510);
		//메인 패널
		JPanel panel = new JPanel();
		
		//첫째줄(서브패널)
		JPanel panel_1 = new JPanel();
		JButton btn_1 = new JButton();
		btn_1.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_1.setBorderPainted(false);	//버튼 테두리 없애기
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_1, "1번", 1);
			}
		});
		usePC(btn_1, "1번");
		JButton btn_2 = new JButton();
		btn_2.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_2.setBorderPainted(false);	//버튼 테두리 없애기
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_2, "2번", 2);
			}
		});
		usePC(btn_2, "2번");
		JButton btn_3 = new JButton();
		btn_3.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_3.setBorderPainted(false);	//버튼 테두리 없애기
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_3, "3번", 3);
			}
		});
		usePC(btn_3, "3번");
		JButton btn_4 = new JButton();
		btn_4.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_4.setBorderPainted(false);	//버튼 테두리 없애기
		btn_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_4, "4번", 4);
			}
		});
		usePC(btn_4, "4번");
		panel_1.add(btn_1);
		panel_1.add(btn_2);
		panel_1.add(btn_3);
		panel_1.add(btn_4);
		
		//둘째줄(서브패널)
		JPanel panel_2 = new JPanel();
		JButton btn_5 = new JButton();
		btn_5.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_5.setBorderPainted(false);	//버튼 테두리 없애기
		btn_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_5, "5번", 5);
			}
		});
		usePC(btn_5, "5번");
		JButton btn_6 = new JButton();
		btn_6.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_6.setBorderPainted(false);	//버튼 테두리 없애기
		btn_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_6, "6번", 6);
			}
		});
		usePC(btn_6, "6번");
		JButton btn_7 = new JButton();
		btn_7.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_7.setBorderPainted(false);	//버튼 테두리 없애기
		btn_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_7, "7번", 7);
			}
		});
		usePC(btn_7, "7번");
		JButton btn_8 = new JButton();
		btn_8.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_8.setBorderPainted(false);	//버튼 테두리 없애기
		btn_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_8, "8번", 8);
			}
		});
		usePC(btn_8, "8번");
		panel_2.add(btn_5);
		panel_2.add(btn_6);
		panel_2.add(btn_7);
		panel_2.add(btn_8);
				
		//셋째줄(서브패널)
		JPanel panel_3 = new JPanel();
		JButton btn_9 = new JButton();
		btn_9.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_9.setBorderPainted(false);	//버튼 테두리 없애기
		btn_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_9, "9번", 9);
			}
		});
		usePC(btn_9, "9번");
		JButton btn_10 = new JButton();
		btn_10.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_10.setBorderPainted(false);	//버튼 테두리 없애기
		btn_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_10, "10번", 10);
			}
		});
		usePC(btn_10, "10번");
		JButton btn_11 = new JButton();
		btn_11.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_11.setBorderPainted(false);	//버튼 테두리 없애기
		btn_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_11, "11번", 11);
			}
		});
		usePC(btn_11, "11번");
		JButton btn_12 = new JButton();
		btn_12.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_12.setBorderPainted(false);	//버튼 테두리 없애기
		btn_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_12, "12번", 12);
			}
		});
		usePC(btn_12, "12번");
		panel_3.add(btn_9);
		panel_3.add(btn_10);
		panel_3.add(btn_11);
		panel_3.add(btn_12);
		
		//넷째줄(서브패널)
		JPanel panel_4 = new JPanel();
		JButton btn_13 = new JButton();
		btn_13.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_13.setBorderPainted(false);	//버튼 테두리 없애기
		btn_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_13, "13번", 13);
			}
		});
		usePC(btn_13, "13번");
		JButton btn_14 = new JButton();
		btn_14.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_14.setBorderPainted(false);	//버튼 테두리 없애기
		btn_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_14, "14번", 14);
			}
		});
		usePC(btn_14, "14번");
		JButton btn_15 = new JButton();
		btn_15.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_15.setBorderPainted(false);	//버튼 테두리 없애기
		btn_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_15, "15번", 15);
			}
		});
		usePC(btn_15, "15번");
		JButton btn_16 = new JButton();
		btn_16.setPreferredSize(new Dimension(100, 100));	//버튼 사이즈
		btn_16.setBorderPainted(false);	//버튼 테두리 없애기
		btn_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_16, "16번", 16);
			}
		});
		usePC(btn_16, "16번");
		panel_4.add(btn_13);
		panel_4.add(btn_14);
		panel_4.add(btn_15);
		panel_4.add(btn_16);
		
		//메인 패널에 서브 패널들을 넣어서 CENTER에 배치하기
		panel.add(panel_1);
		panel.add(panel_2);
		panel.add(panel_3);
		panel.add(panel_4);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		
		setVisible(true);
		setResizable(false);
	}
	
	//자리가 이용가능한지 확인하는 함수
	void checkPC(Object btn, String PCNum, int num) {
		if(((AbstractButton) btn).getText() == "사용 가능") {
			int accountOption = JOptionPane.showConfirmDialog(null, "자리를 선택하시겠습니까?","선택",JOptionPane.YES_NO_OPTION);
			if(accountOption == JOptionPane.YES_OPTION) {
				if(LoginGUI.ac.getPerson(LoginGUI.index1).getState().equals("사용가능")) {
					try {
						((Management) LoginGUI.ac).stateModify(LoginGUI.index1, PCNum);
						String ipAd = getServerIp();
						LoginGUI.ac.getIp(LoginGUI.index1, ipAd);
						new UserGUI();
						dispose();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "에러.", "주의",JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "현재 로그인되어 있어 자리를 선택할 수 없습니다.", "주의",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		}
		else 
			JOptionPane.showMessageDialog(null, "이미 사용중인 좌석입니다.", "주의",JOptionPane.WARNING_MESSAGE);
	}
	
	void usePC(Object btn, String PCNum) {
		try {
			LoginGUI.ac.useCheck(PCNum);
			((AbstractButton) btn).setText("사용중");
			((Component) btn).setBackground(Color.lightGray);	//배경색 지정
			((AbstractButton) btn).setBorderPainted(false);	//버튼 테두리 없애기
			((Component) btn).setForeground(Color.red);	//글자색 지정
		} catch (Exception e) {
			((AbstractButton) btn).setText("사용 가능");
			((Component) btn).setBackground(Color.white);	//배경색 지정
			((Component) btn).setForeground(Color.black);	//글자색 지정
		}
	}
	
	private String getServerIp() {
		
		InetAddress local = null;
		try {
			local = InetAddress.getLocalHost();
		}
		catch ( UnknownHostException e ) {
			e.printStackTrace();
		}
			
		if( local == null ) {
			return "";
		}
		else {
			String ip = local.getHostAddress();
			return ip;
		}
			
	}
}
