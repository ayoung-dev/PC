package school;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class S_BuBokSu extends JFrame{

	private JFrame frame;
	private JPanel contentPane;

	private JTextField textFieldPhone;
	private JTextField textFieldEmail;
	private JTable table;
	private JTextField textField_1;
	int sem = 0;
	
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

	/**
	 * Create the frame.
	 * @throws Exception
	 */

	public S_BuBokSu(String id) throws Exception {
		
		setTitle("학사관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 교수,학생 기능 함수 클래스에 연결
		P_Management pm = new P_Management(url, con);
		S_Management sm = new S_Management(url, con);

		// 해당 교수의 이름 불러오기
		Student s = sm.getStudent(id);
		String Username = sm.getstName(id);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		
		//메뉴안내 라벨
		JButton LectureBtn = new JButton("학적 시스템");
		LectureBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(LectureBtn);
		
		JButton Menu1 = new JButton("학적부 관리");
		Menu1.setBounds(4, 63, 152, 18);
		Menu1.setBorderPainted(false);
		Menu1.setFocusPainted(false);
		Menu1.setContentAreaFilled(false);
		Menu1.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(Menu1);
		
		// 밑줄 함수
		 Font font = Menu1.getFont();
		 Menu1.setForeground(Color.RED);
		 Map attributes = font.getAttributes();
		 attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		 Menu1.setFont(font.deriveFont(attributes));
		
		JButton info = new JButton("학생 정보 조회");
		info.setBounds(10, 83, 152, 18);
		info.setBorderPainted(false);
		info.setFocusPainted(false);
		info.setHorizontalAlignment(SwingConstants.LEFT);
		info.setContentAreaFilled(false);
		menuPanel.add(info);
		
		JButton Menu2 = new JButton("학적 변동 조회");
		Menu2.setBounds(4, 113, 152, 18);
		Menu2.setBorderPainted(false);
		Menu2.setFocusPainted(false);
		Menu2.setHorizontalAlignment(SwingConstants.LEFT);
		Menu2.setContentAreaFilled(false);
		menuPanel.add(Menu2);
		
		Menu2.setForeground(Color.RED);
		Menu2.setFont(font.deriveFont(attributes));
		
		
		JButton Huhak = new JButton("휴학 신청");
		Huhak.setBounds(10, 133, 152, 18);
		Huhak.setBorderPainted(false);
		Huhak.setFocusPainted(false);
		Huhak.setHorizontalAlignment(SwingConstants.LEFT);
		Huhak.setContentAreaFilled(false);
		menuPanel.add(Huhak);		
		
		JButton Bokhak = new JButton("복학 신청");
		Bokhak.setBounds(10, 153, 152, 18);
		Bokhak.setBorderPainted(false);
		Bokhak.setFocusPainted(false);
		Bokhak.setHorizontalAlignment(SwingConstants.LEFT);
		Bokhak.setContentAreaFilled(false);
		menuPanel.add(Bokhak);		
		
		JButton Jatuae = new JButton("자퇴 신청");
		Jatuae.setBounds(10, 173, 152, 18);
		Jatuae.setBorderPainted(false);
		Jatuae.setFocusPainted(false);
		Jatuae.setHorizontalAlignment(SwingConstants.LEFT);
		Jatuae.setContentAreaFilled(false);
		menuPanel.add(Jatuae);
		
		
		JButton Menu3 = new JButton("학적 등록 조회");
		Menu3.setBounds(4, 203, 152, 18);
		Menu3.setBorderPainted(false);
		Menu3.setFocusPainted(false);
		Menu3.setHorizontalAlignment(SwingConstants.LEFT);
		Menu3.setContentAreaFilled(false);
		menuPanel.add(Menu3);
		
		Menu3.setForeground(Color.RED);
		Menu3.setFont(font.deriveFont(attributes));
		
		JButton BuBoksu = new JButton("부/복수 신청");
		BuBoksu.setBounds(10, 223, 152, 18);
		BuBoksu.setBorderPainted(false);
		BuBoksu.setFocusPainted(false);
		BuBoksu.setHorizontalAlignment(SwingConstants.LEFT);
		BuBoksu.setContentAreaFilled(false);
		BuBoksu.setEnabled(false);
		menuPanel.add(BuBoksu);		
		
		JButton Junkua = new JButton("전과 신청");
		Junkua.setBounds(10, 243, 152, 18);
		Junkua.setBorderPainted(false);
		Junkua.setFocusPainted(false);
		Junkua.setHorizontalAlignment(SwingConstants.LEFT);
		Junkua.setContentAreaFilled(false);
		menuPanel.add(Junkua);		
				
		JButton homeBtn = new JButton("홈 화면");
		homeBtn.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		dispose();
	        		S_Home shome;
					try {
						shome = new S_Home(id);
		        		shome.setVisible(true);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	}
	     });
		homeBtn.setBounds(45, 500, 105, 27);
		menuPanel.add(homeBtn);
		
		JPanel Main = new JPanel();
		Main.setBackground(Color.WHITE);
		Main.setForeground(Color.WHITE);
		Main.setBounds(211, 5, 970, 553);
		contentPane.add(Main);
		Main.setLayout(null);
		
		JPanel User = new JPanel();
		User.setBackground(Color.WHITE);
		User.setForeground(Color.WHITE);
		User.setBounds(12, 39, 946, 170);
		User.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		Main.add(User);
		User.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		panel.setBounds(12, 10, 141, 150);
		User.add(panel);
		
		JLabel UserPhoto = new JLabel("UserPhoto");
		panel.add(UserPhoto);
		
		JLabel StuNum = new JLabel("학번 : ");
		StuNum.setBounds(165, 29, 140, 15);
		User.add(StuNum);
	
		
		JLabel Name = new JLabel("이름 : ");
		Name.setBounds(165, 54, 120, 15);
		User.add(Name);
		
		JLabel Birth = new JLabel("생년월일 : ");
		Birth.setBounds(354, 54, 140, 15);
		User.add(Birth);
		
		JLabel College = new JLabel("소속 단대 : ");
		College.setBounds(165, 79, 140, 15);
		User.add(College);
		
		JLabel Department = new JLabel("소속 학과 : ");
		Department.setBounds(354, 79, 160, 15);
		User.add(Department);
		
		JLabel Grade = new JLabel("학년 : ");
		Grade.setBounds(165, 108, 120, 15);
		User.add(Grade);
		
		JLabel Semester = new JLabel("학기 : ");
		Semester.setBounds(354, 108, 140, 15);
		User.add(Semester);
		
		JLabel TextFieldPhone = new JLabel("전화번호 : ");
		TextFieldPhone.setBounds(165, 133, 120, 15);
		User.add(TextFieldPhone);
		
		JLabel TextFieldEmail = new JLabel("이메일 : ");
		TextFieldEmail.setBounds(354, 133, 140, 15);
		User.add(TextFieldEmail);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(229, 130, 96, 21);
		User.add(textFieldPhone);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(410, 130, 96, 21);
		User.add(textFieldEmail);
		
		JButton Edit = new JButton("수정");
		Edit.setBounds(843, 137, 91, 23);
		User.add(Edit);
		
  		Edit.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				try {
					sm.editInfo(s.getstNum(), textFieldPhone.getText(), textFieldEmail.getText());
					JOptionPane.showMessageDialog(null,"정보가 수정되었습니다.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  			}
  		});
		
		// 프로필 정보 띄우기
		StuNum.setText("학번: "+s.getstNum());
		Name.setText("이름: "+s.getstName());
		College.setText("소속단대: "+s.getCollege());
		Department.setText("소속학과: "+s.getfMajor());
		Birth.setText("생년월일: "+s.getBirth());
		Grade.setText("학년: "+s.getGrade());
		Semester.setText("학기: 5");
		TextFieldPhone.setText("전화번호: ");
		TextFieldEmail.setText("이메일: ");
		textFieldPhone.setText(s.getPhoneNum());
		textFieldEmail.setText(s.getEmail());
		
		//휴학 정보 띄우기        
        String colNames1[] = {"학년도", "학기", "변동 구분", "신청 전공", "신청일자", "처리 상태"};
        String rows1[][] = {};
        DefaultTableModel model1 = new DefaultTableModel(rows1, colNames1);
		
        JTable table1 = new JTable(model1);
        JScrollPane scrollPane_1 = new JScrollPane(table1);
        scrollPane_1.setBounds(12, 379, 946, 148);
        Main.add(scrollPane_1);
        
		// 해당 학생 신청 여부 띄우기
		ResultSet rs = null;
		rs = sm.getMajorA(id);
		if (rs.getRow() > -1) {
			while (rs.next()) {
				// 신청날짜 받아오기
				String now = rs.getString(6);
				// 구분 받아오기
				String what = rs.getString(3);
				// 해당 학수번호를 가진 수업 객체 들고 오기
				MajorApplication ma = sm.getMa(id, now, what);
				// GUI에 띄우기
				String[] row = { ma.getApSemester().substring(0,4), ma.getApSemester().substring(5), ma.getClassify(), ma.getApMajor(), 
						ma.getApDate(), ma.getApState()};

				model1.addRow(row);
			}
		}
		
        
        String colNames[] = {"학년도", "학기","부/복수 구분", "전공명", "신청 사유"};
        String rows[][] = {};
        DefaultTableModel model = new DefaultTableModel(rows, colNames);
		
        JTable table = new JTable(model);
        
        //신청한 부/복수 현황 나타내는 부분
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
        scrollPane.setBounds(12, 251, 946, 84);
        scrollPane.setViewportView(table);
        Main.add(scrollPane);
        
        // 해당 학생 신청 여부 띄우기
        ResultSet rs2 = null;
     	rs2 = sm.getMinDouble(id);
     	if (rs2.getRow() > -1) {
     		while (rs.next()) {
     			// 신청날짜 받아오기
     			String now = rs.getString(6);
     			// 구분 받아오기
     			String what = rs.getString(3);
     			// 해당 학수번호를 가진 수업 객체 들고 오기
     			MajorApplication ma = sm.getMa(id, now, what);
     			// GUI에 띄우기
     			String[] row1 = {ma.getApSemester().substring(0,4), ma.getApSemester().substring(5), ma.getClassify(), ma.getApMajor(),  ma.getApReason()};

     			model.addRow(row1);
     		}
     	}
        
        
        JButton Submit = new JButton("신청");
        Submit.setBounds(867, 345, 91, 23);
        Main.add(Submit);
        
        textField_1 = new JTextField();
        textField_1.setText("부/복수 신청");
        textField_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
        textField_1.setColumns(10);
        textField_1.setBounds(12, 6, 123, 30);
        Main.add(textField_1);
        
        //부 복수 신청 창 열리게 하기        
        Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new SubmitWindow(id);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
    
      //학적 시스템 액션 리스너 - 메뉴
  		Menu1.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				S_Info info;
				try {
					info = new S_Info(id);
	  				info.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
  			}
  		});
  		
  		Menu2.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				S_Huhak huhak;
				try {
					huhak = new S_Huhak(id);
	  				huhak.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
  			}
  		});
  		
  		
  	//좌측 메뉴 이동하는 기능
  		//학생 정보 조회 액션 리스너
  		info.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				S_Info Info;
				try {
					Info = new S_Info(id);
	  				Info.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
  			}
  		});
  		
    		//휴학 신청 조회 액션 리스너
    		Huhak.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				S_Huhak huhak;
					try {
						huhak = new S_Huhak(id);
	      				huhak.setVisible(true);	
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
    				dispose();	
      			}
      		});
    		
    		//복학 신청 조회 액션 리스너
    		Bokhak.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				S_Bokhak bokhak;
					try {
						bokhak = new S_Bokhak(id);
	      				bokhak.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}		
    				dispose();	
      			}
      		});

    		//자퇴 신청 조회 액션 리스너
    		Jatuae.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				S_Jatuae jatuae;
					try {
						jatuae = new S_Jatuae(id);
	      				jatuae.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}		
    				dispose();	
      			}
      		});

    		
    		//전과 신청 조회 액션 리스너
    		Junkua.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				S_JunKua junkua;
					try {
						junkua = new S_JunKua(id);
	      				junkua.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}		
    				dispose();	
      			}
      		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		frame.setBounds(100, 100, 450, 300);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}

class SubmitWindow extends JFrame {
	private JFrame frame1;
	private JPanel contentPane;

	private JTextField textField_4;
	private JTextField textField;
	private JTable table;
	int sem = 0;
	
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;
	
	SubmitWindow(String id) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Class.forName("org.mariadb.jdbc.Driver");
					// sql 접속하기 위한 아이디와 비밀번호 입력
					url = "jdbc:mariadb://localhost:3306/school";

					con = DriverManager.getConnection(url, "root", "1234");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//창 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 300, 900, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//frame.pack();
		
		// 교수,학생 기능 함수 클래스에 연결
		P_Management mg = new P_Management(url, con);
		S_Management sm = new S_Management(url, con);
		
		JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 900, 400);
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        
        Calendar today = Calendar.getInstance();
        SimpleDateFormat year = new SimpleDateFormat ("yyyy");
        String strYear = year.format(today.getTime());
        SimpleDateFormat month = new SimpleDateFormat ("mm");
        String strMonth = month.format(today.getTime());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = date.format(today.getTime());
        int intMonth =  Integer.parseInt(strMonth);
        int intYear =  Integer.parseInt(strYear);
        
        if(intMonth >= 1 && intMonth < 7)
        {
        	sem = 2;
        } else
        {
        	sem = 1;
        }
        
        
        //신청 정보
        
        JLabel Year = new JLabel("신청 학년도 : " + strYear);
        Year.setBounds(12, 94, 130, 21);
        panel_1.add(Year);
        
        JLabel Semester = new JLabel("신청 학기 : " + sem);
        Semester.setBounds(204, 97, 99, 21);
        panel_1.add(Semester);
        
        JLabel Date = new JLabel("신청 일자 : " + strDate);
        Date.setBounds(401, 97, 130, 21);
        panel_1.add(Date);
        
        /*JLabel State = new JLabel("신청 현황");
        State.setBounds(571, 97, 99, 21);
        panel_1.add(State);*/
        
        JButton Submit = new JButton("신청");
        Submit.setBounds(768, 93, 91, 23);
        panel_1.add(Submit);
        
        //소속 단대
        JComboBox College = new JComboBox();
        College.setModel(new DefaultComboBoxModel(new String[] {"미래산업융합대학", "자연과학대학", "인문사회대학"}));
        College.setBounds(12, 47, 130, 23);
        panel_1.add(College);
        
        //소속 학과
        JComboBox Department = new JComboBox();
        Department.setModel(new DefaultComboBoxModel(new String[] {"정보보호학과","소프트웨어융합학과", "디지털미디어학과"}));
        Department.setBounds(197, 47, 115, 23);
        panel_1.add(Department);
        
        //부전공
        JRadioButton Second = new JRadioButton("부전공");
        Second.setBounds(5, 10, 73, 23);
        panel_1.add(Second);
        
        //복수전공
        JRadioButton Both = new JRadioButton("복수전공");
        Both.setBounds(95, 10, 98, 23);
        panel_1.add(Both);
        
        Submit.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				if(Second.isSelected() == true)
  				try {
					sm.addMinor(sm.getstName(id), id, strYear + "-" + sem, Department.getSelectedItem().toString(), strDate);
					JOptionPane.showMessageDialog(null,"부전공신청이 완료되었습니다.");
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
  				else {
  					try {
  						sm.addDouble(sm.getstName(id), id, strYear + "-" + sem, Department.getSelectedItem().toString(), strDate);
  						JOptionPane.showMessageDialog(null,"복수전공신청이 완료되었습니다.");
  						dispose();
  					} catch (Exception e1) {
  						e1.printStackTrace();
  					}
  				}
  			}
  		});
        
       
		
		setVisible(true);
	}
}
