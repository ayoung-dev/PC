package school;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

// 회원가입 
public class M_Register extends JFrame {

	private JPanel contentPane;
	private JTextField IDField;
	private JTextField passWordField;
	private JTextField yearField;
	private JTextField emailField;
	private JTextField phonNumField;
	private JTextField dayField;
	private JTextField passWordField2;
	static P_Management pm = null;
	static S_Management sm = null;
	

	public static String id;
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Class.forName("org.mariadb.jdbc.Driver");
					// sql 접속하기 위한 아이디와 비밀번호 입력
					url = "jdbc:mariadb://localhost:3306/school";

					con = DriverManager.getConnection(url, "root", "1234");

					S_ApplicationClass frame = new S_ApplicationClass(id);
					P_Management mg = new P_Management(url, con);
					S_Management sg = new S_Management(url, con);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public M_Register(P_Management pmg, S_Management smg) throws SQLException, ClassNotFoundException {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // 창 닫기 옵션 재설정
		setBounds(600, 200, 450, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel TitleLabel = new JLabel("회원가입");
		TitleLabel.setBounds(52, 13, 331, 52);
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("굵게", Font.BOLD, 25));
		contentPane.add(TitleLabel);

		// 아이디 입력란
		IDField = new JTextField();
		IDField.setBounds(82, 80, 281, 36);
		contentPane.add(IDField);
		IDField.setColumns(10);

		// 비밀번호 입력란
		passWordField = new JTextField();
		passWordField.setColumns(10);
		passWordField.setBounds(82, 147, 281, 36);
		contentPane.add(passWordField);

		// 비밀번호 재확인
		passWordField2 = new JTextField();
		passWordField2.setColumns(10);
		passWordField2.setBounds(82, 206, 281, 36);
		contentPane.add(passWordField2);

		// 생년월일 - 년
		yearField = new JTextField();
		yearField.setText("년(4글자)");
		yearField.setFocusable(false);
		yearField.setForeground(Color.GRAY);
		yearField.setColumns(10);
		yearField.setBounds(82, 274, 88, 36);
		contentPane.add(yearField);
		yearField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				yearField.setFocusable(true);
				yearField.setText(null);
				yearField.setForeground(Color.BLACK);
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		// 생년월일 - 월
		String month[] = { "월", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		JComboBox monthCombo = new JComboBox(month);
		monthCombo.setBackground(Color.white);
		monthCombo.setBounds(184, 274, 88, 36);
		contentPane.add(monthCombo);

		// 여기서 선택된 month를 반환한다.
		String selectedMonth = monthCombo.getSelectedItem().toString();

		// 생년월일 - 일 입력란
		dayField = new JTextField();
		dayField.setText("일");
		dayField.setForeground(Color.GRAY);
		dayField.setFocusable(false);
		dayField.setColumns(10);
		dayField.setBounds(286, 274, 77, 36);
		contentPane.add(dayField);
		dayField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				dayField.setFocusable(true);
				dayField.setText(null);
				dayField.setForeground(Color.BLACK);
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		// 이메일 입력란
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(82, 341, 281, 36);
		contentPane.add(emailField);

		// 전화번호 입력란
		phonNumField = new JTextField();
		phonNumField.setColumns(10);
		phonNumField.setBounds(82, 407, 281, 36);
		contentPane.add(phonNumField);

		//////// 라벨 /////////
		JLabel passWordLabel = new JLabel("비밀번호");
		passWordLabel.setBounds(82, 129, 62, 18);
		contentPane.add(passWordLabel);

		JLabel passWordLabel_2 = new JLabel("비밀번호 재확인");
		passWordLabel_2.setBounds(82, 187, 125, 18);
		contentPane.add(passWordLabel_2);

		JLabel IDLabel = new JLabel("학번/교번");
		IDLabel.setBounds(82, 62, 62, 18);
		contentPane.add(IDLabel);

		JLabel birthLabel = new JLabel("생년월일");
		birthLabel.setBounds(82, 256, 62, 18);
		contentPane.add(birthLabel);

		JLabel emailLabel = new JLabel("이메일");
		emailLabel.setBounds(82, 323, 62, 18);
		contentPane.add(emailLabel);

		JLabel numLabel = new JLabel("전화번호");
		numLabel.setBounds(82, 390, 62, 18);
		contentPane.add(numLabel);

		
		// 가입하기 버튼
		JButton registBtn = new JButton("가입하기");
		registBtn.setBounds(82, 460, 281, 38);
		registBtn.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(registBtn);
		//가입하기 버튼을 누르면 객체를 만들고 테이블에 내용을 지워주는 리스너
	
		// if - else 나중에 추가 바람!
		registBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					boolean check = false;
					//학번/교번을 적지 않았을 때 팝업창 듸우기
					if(IDField.getText().isEmpty()) { 
						JOptionPane.showMessageDialog(null,"학번/교번을 입력해주세요.");
					}
					//비밀번호를 적지 않았을 때 팝업창 띄우기
					else if(passWordField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
					}
					//비밀번호 재확인을 적지 않았을 때 팝업창 띄우기
					else if(passWordField2.getText().isEmpty()) { 
						JOptionPane.showMessageDialog(null,"비밀번호를 한 번 더 입력해주세요.");
					}
					//생년원일 - 년도를 적지 않았을 때 팝업창 띄우기
					else if(yearField.getText().isEmpty()) { 
						JOptionPane.showMessageDialog(null,"년도를 입력해주세요.");
					}
					//생년월일 - 월을 적지 않았을 때 팝업창 띄우기
					else if(monthCombo.getSelectedItem().toString() == "월") { 
						JOptionPane.showMessageDialog(null,"월을 선택해주세요.");
					}
					//생년월일 - 일을 적지 않았을 때 팝업창 띄우기
					else if(dayField.getText().isEmpty()) { 
						JOptionPane.showMessageDialog(null,"일을 입력해주세요.");
					}
					//이메일을 적지 않았을 때 팝업창 띄우기
					else if(emailField.getText().isEmpty()) { 
						JOptionPane.showMessageDialog(null,"이메일을 입력해주세요.");
					}
					//전화번호를 적지 않았을 때 팝업창 띄우기
					else if(phonNumField.getText().isEmpty()) { 
						JOptionPane.showMessageDialog(null,"전화번호를 입력해주세요.");
					}
					//모든 내용을 적었을 경우
					else {
						boolean pass = false;
						//비밀번호 텍스트박스와 비밀번호 확인 텍스트 박스가 일치하는지 확인
						if(passWordField.getText().equals(passWordField2.getText()) ) {
							pass = true;
						} else {
						}
						
						//pass가 false이면 팝업창 띄우기
						if(pass == false) {
							JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
							passWordField2.setText("");
						}
						
						//학번/교번이 존재하지 않을 때 팝업창 띄우기
						else if(smg.checkId(IDField.getText()) == false && pmg.checkId(IDField.getText()) == false) {
							JOptionPane.showMessageDialog(null,"해당 학번/교번은 존재하지 않습니다. 학번/교번을 확인해주세요.");
							IDField.setText("");
						}
						
						//전화번호가 중복일 때 팝업창 띄우기
						else if(smg.checkPhoneNum(phonNumField.getText())==true || pmg.checkPhoneNum(phonNumField.getText())==true) { 
							JOptionPane.showMessageDialog(null,"이미 등록된 전화번호 입니다.");
							phonNumField.setText("");	
						} 
						
						else {
							try {
								if (IDField.getText().length() == 10) { // 학번을 입력하고 회원가입을 시도했을 때
									smg.add(IDField.getText(), passWordField.getText(), yearField.getText() + monthCombo.getSelectedItem().toString() + dayField.getText(),
										emailField.getText(), phonNumField.getText());	//학생 등록 메소드	
									JOptionPane.showMessageDialog(null,"가입되었습니다."); //객체가 정상적으로 추가 되었을 때 뜨는 팝업창
									dispose();

								} else if (IDField.getText().length() == 6) { // 교번을 입력하고 회원가입을 시도했을 때
									pmg.add(IDField.getText(), passWordField.getText(), yearField.getText() + monthCombo.getSelectedItem().toString() + dayField.getText(),
										emailField.getText(), phonNumField.getText());	//교수 등록 메소드
									JOptionPane.showMessageDialog(null,"가입되었습니다."); //객체가 정상적으로 추가 되었을 때 뜨는 팝업창
									dispose();
								}
							} catch (Exception e1) { //입력이 정상적으로 되지 않았을 경우
								JOptionPane.showMessageDialog(null,"가입에 실패했습니다."); //객체가 정상적으로 추가되지 않았을 때 뜨는 팝업창
								//입력 후 텍스트 필드 값 제거
								IDField.setText("");
								passWordField.setText("");
								passWordField2.setText("");
								yearField.setText("");
								dayField.setText("");
								emailField.setText("");
								phonNumField.setText("");	
							}
						}
						
					}
			}
		});
		
		JButton cancelbutton = new JButton("취소"); //"취소"버튼 만들기
		//"취소"버튼을 누르면 현재 창이 닫힌다.
		cancelbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
