//로그인 GUI
package pc;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class LoginGUI extends JFrame{
	static Management ac = null;
	static int index1;
	public LoginGUI() {
		setTitle("Login");
		setBounds(10, 50, 400, 300);
		//x버튼 클릭시 종료
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		JTextField id = new JTextField();	//아이디를 쓰는 텍스트 박스
		id.setColumns(10);
		JPasswordField pw = new JPasswordField(); //비밀번호를 쓰는 텍스트 박스
		pw.setColumns(10);
		panel.add(new JLabel("ID")); //아이디 라벨 넣기
		panel.add(id);
		panel.add(new JLabel("Password")); //비밀번호 라벨 넣기
		panel.add(pw);
		JButton signupbtn = new JButton("회원가입"); //"회원가입"버튼 만들기
		signupbtn.setOpaque(false);	//버튼을 투명하게 true->불투명
		signupbtn.setContentAreaFilled(false);	//버튼 배경 없애기
		signupbtn.setBorderPainted(false);	//버튼 테두리 없애기
		panel.add(signupbtn);
		signupbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignUpGUI();
			}
		});
		JButton findbtn = new JButton("아이디/비밀번호 찾기");	//"아이디/비밀번호 찾기"버튼 만들기
		findbtn.setOpaque(false);	//버튼을 투명하게 true->불투명
		findbtn.setContentAreaFilled(false);	//버튼 배경 없애기
		findbtn.setBorderPainted(false);	//버튼 테두리 없애기
		panel.add(findbtn);
		findbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindGUI();
			}
		});
		getContentPane().add(panel, BorderLayout.CENTER);
		
		
		//확인, 취소 버튼 만들기
		JPanel panel_1 = new JPanel();
		JButton okbtn = new JButton("Login");
		panel_1.add(okbtn);
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		//Login버튼
		okbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"아이디를 입력해주세요.");
				}
				else if(pw.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"비밀번호를 입력해주세요.");
				}
				else {
					try {
						index1 = ac.searchId(id.getText());	//id 텍스트필드에 있는 값을 검색해서 인덱스 값 저장
						if(pw.getText().equals(ac.getPerson(index1).getPw())) {	//인덱스 값을 이용해 pw값 불러와서 pw 텍스트필드 값과 비교하여 같으면 로그인 됨
							//사용자이면 사용자화면 띄우기
							if(ac.getPerson(index1).getUser().equals("관리자")) {
								id.setText("");
								pw.setText("");
								new AdministratorGUI();
							}
							else {
								id.setText("");
								pw.setText("");
								new MainGUI();
							}
						}	
						else {
							JOptionPane.showMessageDialog(null,"비밀번호가 잘못되었습니다.");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"아이디가 잘못되었습니다.");
					}	
					
				}
			}
		});
		
		setVisible(true);
		setResizable(false);
	}
	public static void main(String[] args) throws Exception {
		ac = new Management("org.mariadb.jdbc.Driver" ,"jdbc:mariadb://localhost:3306/PC", "1234");
		new LoginGUI();
	}
}
