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
import javax.swing.SwingConstants;
import javax.swing.JButton;

// 로그인 화면
public class M_Login extends JFrame {

	private JPanel contentPane;
	private JTextField IdField;
	private JTextField passWordField;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 드라이버 로드
		Class.forName("org.mariadb.jdbc.Driver");

		// sql 접속하기 위한 아이디와 비밀번호 입력
		String url = "jdbc:mariadb://localhost:3306/school";
		Connection con = DriverManager.getConnection(url, "root", "1234");
		P_Management pmg = new P_Management(url, con); // 교수DB 연결
		S_Management smg = new S_Management(url, con); // 학생DB 연결

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M_Login frame = new M_Login(pmg, smg); // 교수 or 학생 로그인 시도
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 로그인 시도(교수 or 학생)
	public M_Login(P_Management pmg, S_Management smg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel TitleLabel = new JLabel("학사관리 프로그램");
		TitleLabel.setBounds(52, 13, 331, 52);
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("굵게", Font.BOLD, 25));
		contentPane.add(TitleLabel);

		// 아이디 입력란(학번/교번)
		IdField = new JTextField();
		IdField.setBounds(82, 68, 281, 36);
		contentPane.add(IdField);
		IdField.setColumns(10);
		IdField.setText("학번/교번");
		IdField.setFocusable(false); // 커서 작동 x
		IdField.setForeground(Color.GRAY); // 색상 회색
		IdField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				IdField.setFocusable(true); // 커서 작동 o
				IdField.setText(null); // 텍스트 필드 초기화
				IdField.setForeground(Color.BLACK); // 색상 검정
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

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

		// 비밀번호 입력란
		passWordField = new JTextField();
		passWordField.setColumns(10);
		passWordField.setBounds(82, 117, 281, 36);
		contentPane.add(passWordField);
		passWordField.setText("비밀번호");
		passWordField.setFocusable(false);
		passWordField.setForeground(Color.GRAY);
		passWordField.addMouseListener(new MouseListener() {

			// 마우스로 클릭했을 때
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				passWordField.setFocusable(true); // 커서 작동 o
				passWordField.setText(null); // 텍스트 필드 초기화
				passWordField.setForeground(Color.BLACK); // 색상 검정
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

		// 로그인 버튼
		JButton loginBtn = new JButton("로그인");
		loginBtn.setBounds(82, 166, 281, 27);
		loginBtn.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(loginBtn);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = IdField.getText();
				String pw = passWordField.getText();
				
				//학번/교번을 적지 않았을 때 팝업창 띄우기
				if(id.isEmpty()) { 
					JOptionPane.showMessageDialog(null,"학번/교번을 입력해주세요.");
				}
				//비밀번호를 적지 않았을 때 팝업창 띄우기
				else if(pw.isEmpty()) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
				} 
				else {
					try {
						if (pmg.loginPr(id, pw)) { // 교번을 입력하고 로그인을 시도했을 때

							String prName = pmg.checkNamePr(id); // 로그인한 교수 이름 받아오기
							P_Home pHome = new P_Home(id); // 홈 화면 연결(교수)
							pHome.setVisible(true);
							dispose();

						} else if (smg.loginSt(id, pw)) { // 학번을 입력하고 로그인을 시도했을 때

							String stName = smg.getstName(id); // 로그인한 학생 이름 받아오기
							S_Home sHome = new S_Home(id); // 홈 화면 연결(학생)
							sHome.setVisible(true);
							dispose();
						}
						JOptionPane.showMessageDialog(null, "아이디가 존재하지 않거나 비밀번호가 잘못되었습니다. 다시 확인해주세요.");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				

			}
		});

		// 아이디 찾기 버튼
		JButton findIdBtn = new JButton("아이디 찾기");
		findIdBtn.setBounds(90, 206, 107, 27);
		contentPane.add(findIdBtn);
		findIdBtn.setBorderPainted(false);
		findIdBtn.setFocusPainted(false);
		findIdBtn.setContentAreaFilled(false);
		findIdBtn.setHorizontalAlignment(SwingConstants.LEFT);
		findIdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_FindID findID = new M_FindID(pmg, smg);
				findID.setVisible(true); // 아이디 찾기 창 띄우기
			}
		});

		// 비밀번호 찾기 버튼
		JButton findPassWordBtn = new JButton("비밀번호 찾기");
		findPassWordBtn.setBounds(173, 206, 121, 27);
		contentPane.add(findPassWordBtn);
		findPassWordBtn.setBorderPainted(false);
		findPassWordBtn.setFocusPainted(false);
		findPassWordBtn.setContentAreaFilled(false);
		findPassWordBtn.setHorizontalAlignment(SwingConstants.LEFT);
		findPassWordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_FindPassWord findPassword = new M_FindPassWord(pmg, smg);
				findPassword.setVisible(true); // 비밀번호 찾기 창 띄우기
			}
		});

		// 회원가입 버튼
		JButton RegisterBtn = new JButton("회원가입");
		RegisterBtn.setBounds(270, 206, 88, 27);
		contentPane.add(RegisterBtn);
		RegisterBtn.setBorderPainted(false);
		RegisterBtn.setFocusPainted(false);
		RegisterBtn.setContentAreaFilled(false);
		RegisterBtn.setHorizontalAlignment(SwingConstants.LEFT);
		RegisterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_Register register = null;
				try {
					register = new M_Register(pmg, smg);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				register.setVisible(true);
			}
		});
	}
}
