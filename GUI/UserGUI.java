//자리 선택하면 사용자에게 보이는 GUI
package pc;

import javax.swing.JFrame;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class UserGUI extends JFrame {
	public static String name;

	public UserGUI() {
		long start = System.currentTimeMillis();
		setTitle("User");
		setBounds(10, 50, 400, 1000);
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH시 mm분 ss초 ");

		// x버튼 클릭 시 종료
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		int index = LoginGUI.index1;
		name = LoginGUI.ac.getPerson(LoginGUI.index1).getName(); // 로그인 한 계정의 이름을 불러와 변수에 저장
		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();
		JLabel label = new JLabel(name); // 로그인 한 계정의 이름을 넣어서 글씨 쓰기
		label.setFont(new Font("나눔바른고딕", Font.BOLD, 14)); // 폰트 속성 지정
		JLabel label_1 = new JLabel("님 안녕하세요"); // 로그인 한 계정의 이름을 넣어서 글씨 쓰기
		label_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 12)); // 폰트 속성 지정
		panel_2.add(label);
		panel_2.add(label_1);

		// 로그아웃 버튼 만들기
		JPanel panel_3 = new JPanel();
		JButton logoutbutton = new JButton("Log out"); // "Log out"버튼 만들기
		logoutbutton.setOpaque(false); // 버튼을 투명하게 true->불투명
		logoutbutton.setContentAreaFilled(false); // 버튼 배경 없애기
		logoutbutton.setBorderPainted(false); // 버튼 테두리 없애기
		panel_3.add(logoutbutton);
		// 로그아웃 버튼을 누르면 "로그아웃 되었습니다"알림창과 동시에 창이 사라진다.
		logoutbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long logout = System.currentTimeMillis ();
				long usertime = (long) ((logout - start)/1000.0); // / 60000;
				try {
					LoginGUI.ac.modifytime(index,usertime);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					LoginGUI.ac.logoutModify(index, "사용가능");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다. 카운터에서 결제해주세요.");
				dispose();
			}
		});
		panel_1.add(panel_2);
		panel_1.add(panel_3);
		getContentPane().add(panel_1, BorderLayout.NORTH);

		JPanel panel_4 = new JPanel();
		JLabel starttimelabel = new JLabel("시작 시간 : ");
		panel_4.add(starttimelabel);
		String startt = timeFormat.format(new Date(start));
		JLabel starttime = new JLabel(startt);
		panel_4.add(starttime);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(new GridLayout(1, 2));
		JButton goodsbtn = new JButton("매점");
		JButton chatbtn = new JButton("관리자와 채팅");
		panel_5.add(goodsbtn);
		panel_5.add(chatbtn);
		panel_4.add(panel_5);
		getContentPane().add(panel_4, BorderLayout.CENTER);

		// 버튼 이벤트
		goodsbtn.addActionListener(new MenuMgrEvent());
		chatbtn.addActionListener(new ChatMgrEvent());

		// 우측 상단에 프레임 창 띄우기
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width), (screenSize.height - screenSize.height));

		setVisible(true);
		setResizable(false);
	}

	// 매뉴 버튼을 누르면 발생하는 이벤트
	private class MenuMgrEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new UserMenuGUI();

		}
	}

	// 채팅 버튼을 누르면 발생하는 이벤트
	private class ChatMgrEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String ipAdress = LoginGUI.ac.getPerson(LoginGUI.index1).getIp();
			new ClientFrame(ipAdress, "8888");
		}
	}


}
