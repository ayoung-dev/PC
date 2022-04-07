//회원가입 GUI
package pc;
import pc.LoginGUI;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SignUpGUI extends JFrame {
	public SignUpGUI() {
		setTitle("Sign Up");
		setBounds(10, 50, 400, 300);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(5,2)); //가로2, 세로4로 나누어 쓰는 칸 만들기
		JTextField name = new JTextField(); //"이름"쓰는 텍스트 박스
		name.setColumns(10); //텍스트필드 가로 사이즈 설정
		JTextField phonenum = new JTextField(); //"전화번호"쓰는 텍스트 박스
		phonenum.setColumns(10); //텍스트필드 가로 사이즈 설정
		JTextField id = new JTextField(); //"아이디"쓰는 텍스트 박스
		id.setColumns(10); //텍스트필드 가로 사이즈 설정
		JPasswordField pw = new JPasswordField(); //"비밀번호"쓰는 텍스트 박스
		pw.setColumns(10); //텍스트필드 가로 사이즈 설정
		JPasswordField pwcheck = new JPasswordField(); //"비밀번호 확인"쓰는 텍스트 박스
		pwcheck.setColumns(10); //텍스트필드 가로 사이즈 설정
		panel_1.add(new JLabel("이름")); //"이름"라벨 넣기
		panel_1.add(name);
		panel_1.add(new JLabel("전화번호")); //"전화번호"라벨 넣기
		panel_1.add(phonenum);
		panel_1.add(new JLabel("아이디")); //"아이디"라벨 넣기
		panel_1.add(id);
		panel_1.add(new JLabel("바밀번호")); //"비밀번호"라벨 넣기
		panel_1.add(pw);
		panel_1.add(new JLabel("바밀번호 확인")); //"비밀번호 확인"라벨 넣기
		panel_1.add(pwcheck);
		getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		JButton savebutton = new JButton("저장"); //"저장"버튼 만들기
		//저장 버튼을 누르면 객체를 만들고 테이블에 내용을 지워주는 리스너
		savebutton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				//이름을 적지 않았을 때 팝업창 듸우기
				if(name.getText().isEmpty()) { 
					JOptionPane.showMessageDialog(null,"이름을 입력해주세요.");
				}
				//전화번호를 적지 않았을 때 팝업창 띄우기
				else if(phonenum.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.");
				}
				//아이디를 적지 않았을 때 팝업창 띄우기
				else if(id.getText().isEmpty()) { 
					JOptionPane.showMessageDialog(null,"아이디를 입력해주세요.");
				}
				//비밀번호를 적지 않았을 때 팝업창 띄우기
				else if(pw.getText().isEmpty()) { 
					JOptionPane.showMessageDialog(null,"비밀번호를 입력해주세요.");
				}
				//비밀번호 확인란을 적지 않았을 때 팝업창 띄우기
				else if(pwcheck.getText().isEmpty()) { 
					JOptionPane.showMessageDialog(null,"비밀번호를 확인해주세요.");
				}
				//모든 내용을 적었을 경우
				else {
					boolean check = false;
					//비밀번호 텍스트박스와 비밀번호 확인 텍스트 박스가 일치하는지 확인
					if(pw.getText().equals(pwcheck.getText()) ) {
						check = true;
					} else {
					}
					
					//check가 false이면 팝업창 띄우기
					if(check == false) {
						JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
					}
					
					//아이디가 중복일 때 팝업창 띄우기
					else if(LoginGUI.ac.checkId(id.getText())==true) {
						JOptionPane.showMessageDialog(null,"이미 등록된 아이디입니다. 다른 아이디를 입력해주세요");
					}
					
					//전화번호가 중복일 때 팝업창 띄우기
					else if(LoginGUI.ac.checkPhoneNum(phonenum.getText())==true) { 
						JOptionPane.showMessageDialog(null,"이미 등록된 전화번호 입니다.");
					}
					
					else {
						//회원정보 등록하기
						try {
							LoginGUI.ac.add(new Member("사용자", name.getText(), phonenum.getText(), id.getText(), pw.getText(), 0, "사용가능", "0"));	//주소록 등록 메소드
							JOptionPane.showMessageDialog(null,"가입되었습니다."); //객체가 정상적으로 추가 되었을 때 뜨는 팝업창
							dispose();
						} catch (Exception e1) { //입력이 정상적으로 되지 않았을 경우
							JOptionPane.showMessageDialog(null,"가입에 실패했습니다."); //객체가 정상적으로 추가되지 않았을 때 뜨는 팝업창
						}
					}
				}
				//입력 후 텍스트 필드 값 제거
				name.setText("");
				phonenum.setText("");
				id.setText("");
				pw.setText("");
				pwcheck.setText("");
			}
			
		});
		JButton cancelbutton = new JButton("취소"); //"취소"버튼 만들기
		//"취소"버튼을 누르면 현재 창이 닫힌다.
		cancelbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		panel_2.add(savebutton);
		panel_2.add(cancelbutton);
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		setVisible(true);
		setResizable(false);
	}
}
