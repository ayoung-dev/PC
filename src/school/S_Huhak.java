package school;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Label;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.awt.ScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class S_Huhak extends JFrame {

	private JPanel contentPane;
	private JTextField textField_4;
	private JTextField textField;
	private JTable table_1;
	private JTextField textField_1;
	
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

	public S_Huhak(String id) throws Exception {
		
		
		setTitle("학사관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 교수,학생 기능 함수 클래스에 연결
		P_Management mg = new P_Management(url, con);
		S_Management sm = new S_Management(url, con);

		// 해당 교수의 이름 불러오기
		Student s = sm.getStudent(id);
		String Username = sm.getstName(id);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
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
		Huhak.setEnabled(false);
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
		User.setBounds(12, 44, 946, 170);
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
		StuNum.setBounds(165, 29, 120, 15);
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
		TextFieldEmail.setBounds(354, 133, 120, 15);
		User.add(TextFieldEmail);
		
		JTextField textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(229, 130, 96, 21);
		User.add(textFieldPhone);
		
		JTextField textFieldEmail = new JTextField();
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
		
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(12, 224, 946, 130);
        panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
        Main.add(panel_1);
        panel_1.setLayout(null);
        
        
        
        Calendar today = Calendar.getInstance();
        SimpleDateFormat year = new SimpleDateFormat ("yyyy");
        SimpleDateFormat month = new SimpleDateFormat ("MM");
        SimpleDateFormat date = new SimpleDateFormat("dd");
        SimpleDateFormat stdate = new SimpleDateFormat("yyyyMMdd");
        String strYear = year.format(today.getTime());
        String strMonth = month.format(today.getTime());
        String strDate = date.format(today.getTime());
        String stDate = stdate.format(today.getTime());
        String allDate = strYear +"-" + strMonth + "-" + strDate;
        int intDate =  Integer.parseInt(stDate);
        
        //휴학 신청 정보
        JTextField Huhak_Year = new JTextField("휴학 학년도");
        Huhak_Year.setBounds(12, 10, 77, 21);
        panel_1.add(Huhak_Year);
        
        JTextField Huhak_Semester = new JTextField("휴학 학기");
        Huhak_Semester.setBounds(204, 10, 77, 21);
        panel_1.add(Huhak_Semester);
        
        JComboBox SemesterNum = new JComboBox();
        SemesterNum.setModel(new DefaultComboBoxModel(new String[] {"휴학 학기 수", "1", "2"}));
        SemesterNum.setBounds(402, 10, 98, 23);
        panel_1.add(SemesterNum);
        
        JTextField Bokhak_Date = new JTextField(allDate);
        Bokhak_Date.setBounds(12, 50, 98, 23);
        panel_1.add(Bokhak_Date);
        
        JComboBox Summary2 = new JComboBox();
        Summary2.setModel(new DefaultComboBoxModel(new String[] {"휴학 사유", "어학 연수", "경제 사정", "건강 문제", "진로 문제"}));
        Summary2.setBounds(204, 50, 116, 23);
        panel_1.add(Summary2);
        
    
        
        JTextField StartDate = new JTextField("휴학 시작 일자");
        StartDate.setBounds(401, 51, 99, 21);
        panel_1.add(StartDate);
        
        JTextField LastDate = new JTextField("휴학 종료 일자");
        LastDate.setBounds(572, 50, 98, 21);
        panel_1.add(LastDate);
        
        JButton Submit = new JButton("신청");
        Submit.setBounds(843, 93, 91, 23);
        panel_1.add(Submit);
        
        
        Submit.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				if (SemesterNum.getSelectedIndex() == 0 || Summary2.getSelectedIndex() == 0) {
  					JOptionPane.showMessageDialog(null,"박스 안의 내용을 제대로 선택해주세요.");
  					
  				} else {
  	  				try {
  						sm.addLeave(sm.getstName(id), id, strYear + "-" + Huhak_Semester.getText(), StartDate.getText(), LastDate.getText(), 
  								Summary2.getSelectedItem().toString(), Integer.parseInt(SemesterNum.getSelectedItem().toString()), allDate);
  						JOptionPane.showMessageDialog(null,"휴학신청이 완료되었습니다.");
  					} catch (Exception e1) {
  						JOptionPane.showMessageDialog(null,"모든 칸에 해당 내용을 제대로 입력해주세요.");
  					}
  				}

  			}
  		});
        
        
		//휴학 정보 띄우기        
        String colNames1[] = {"학년도", "학기", "시작 일자", "종료 일자","변동 사유"
        		, "휴학 학기 수", "신청일", "처리상태"};
        String rows1[][] = {};
        DefaultTableModel model1 = new DefaultTableModel(rows1, colNames1);
		
        JTable table1 = new JTable(model1    	
        );
        JScrollPane scrollPane_1 = new JScrollPane(table1);
        scrollPane_1.setBounds(12, 364, 946, 142);
        Main.add(scrollPane_1);
        
		// 해당 학생 신청 여부 띄우기
		ResultSet rs = null;
		rs = sm.getLeave(id);
		if (rs.getRow() > -1) {
			while (rs.next()) {
				// 신청날짜 받아오기
				String now = rs.getString(9);
				// 구분 받아오기
				String what = rs.getString(4);
				// 해당 학수번호를 가진 수업 객체 들고 오기
				SchoolApplication sa = sm.getSa(id, now, what);
				// GUI에 띄우기
				String[] row = { sa.getApSemester().substring(0,4), sa.getApSemester().substring(5), sa.getClassify(), sa.getStartDate(), sa.getEndDate(),
						sa.getApReason(), Integer.toString(sa.getAbSemester()), sa.getApCount(), sa.getApState()};

				model1.addRow(row);
			}
		}
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
        textField_1.setText("휴학신청");
        textField_1.setBounds(12, 10, 105, 32);
        Main.add(textField_1);
        textField_1.setColumns(10);
       
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
  		
  		Menu3.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				S_BuBokSu buboksu;
				try {
					buboksu = new S_BuBokSu(id);
	  				buboksu.setVisible(true);
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
    		
    		//부/복수 신청 조회
    		BuBoksu.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				S_BuBokSu buboksu;
					try {
						buboksu = new S_BuBokSu(id);
	      				buboksu.setVisible(true);	
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
}
