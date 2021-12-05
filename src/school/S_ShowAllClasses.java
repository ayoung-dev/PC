package school;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

// 개설과목 조회
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.DefaultComboBoxModel;

// 개설과목 조회
public class S_ShowAllClasses extends JFrame {

	private JPanel contentPane;
	private JTextField timeField1;
	private JTextField timeField2;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
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
					P_Management mg = new P_Management(url, con);
					S_ShowAllClasses frame = new S_ShowAllClasses(id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public S_ShowAllClasses(String id) throws SQLException {
		setTitle("학사관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		

		// 교수 함수에 연결
		P_Management mg = new P_Management(url, con);

		JButton LectureBtn = new JButton("수업 시스템");
		LectureBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(LectureBtn);
		
		
		
		JButton showAllClassLabel = new JButton("개설과목조회");
		showAllClassLabel.setBounds(10, 63, 152, 18);
		showAllClassLabel.setBorderPainted(false);
		showAllClassLabel.setFocusPainted(false);
		showAllClassLabel.setHorizontalAlignment(SwingConstants.LEFT);
		//AddLectureLabel.setContentAreaFilled(false);
		showAllClassLabel.setEnabled(false);
		menuPanel.add(showAllClassLabel);
	
		
		
		JButton BasketLabel = new JButton("장바구니 입력");
		BasketLabel.setBounds(10, 94, 152, 18);
		menuPanel.add(BasketLabel);
		BasketLabel.setBorderPainted(false);
		BasketLabel.setFocusPainted(false);
		BasketLabel.setContentAreaFilled(false);
		BasketLabel.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(BasketLabel);
		BasketLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				S_Bucket sb;
				try {
					sb = new S_Bucket(id);
					sb.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		JButton applicationLabel = new JButton("수강신청");
		applicationLabel.setBounds(10, 125, 152, 18);
		menuPanel.add(applicationLabel);
		applicationLabel.setBorderPainted(false);
		applicationLabel.setFocusPainted(false);
		applicationLabel.setContentAreaFilled(false);
		applicationLabel.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(applicationLabel);
		applicationLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				S_ApplicationClass sac;
				try {
					sac = new S_ApplicationClass(id);
					sac.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		JButton stuClassLabel = new JButton("수강신청 내역");
		stuClassLabel.setBounds(10, 156, 152, 18);
		menuPanel.add(stuClassLabel);
		stuClassLabel.setBorderPainted(false);
		stuClassLabel.setFocusPainted(false);
		stuClassLabel.setContentAreaFilled(false);
		stuClassLabel.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(stuClassLabel);
		stuClassLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				S_StuClasses ssc;
				try {
					ssc = new S_StuClasses(id);
					ssc.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		JButton homeBtn = new JButton("홈 화면");
		homeBtn.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		dispose();
	        		S_Home shome;
					try {
						shome = new S_Home(id);
		        		shome.setVisible(true);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	}
	     });
		homeBtn.setBounds(45, 500, 105, 27);
		menuPanel.add(homeBtn);
		
		
		
		
		
		
		JLabel titleLabel = new JLabel("개설과목조회");
		titleLabel.setBounds(219, 13, 109, 18);
		contentPane.add(titleLabel);
		JPanel panelOne = new JPanel();
		panelOne.setBounds(104, 269, 544, 349);
		
		JPanel selectPanel = new JPanel();
		selectPanel.setBounds(219, 44, 362, 28);
		contentPane.add(selectPanel);
		selectPanel.setLayout(null);
		selectPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		
		JComboBox yearBox = new JComboBox();
		yearBox.setModel(new DefaultComboBoxModel(new String[] {"2021", "2020", "2019"}));
		yearBox.setBounds(90, 2, 85, 23);
		selectPanel.add(yearBox);
		
		JComboBox semesterBox = new JComboBox();
		semesterBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		semesterBox.setBounds(274, 2, 85, 23);
		selectPanel.add(semesterBox);
		
		JLabel yearLabel = new JLabel("학년도");
		yearLabel.setBounds(14, 5, 62, 18);
		selectPanel.add(yearLabel);
		
		JLabel semesterLabel = new JLabel("학기");
		semesterLabel.setBounds(196, 5, 62, 18);
		selectPanel.add(semesterLabel);
		
		// 표 생성 - 학부
		String colNames[] = { "학년", "학수번호", "이수구분", "강의유형", "과목명","학점","강의시간","담당교수","제한인원","수강인원" };// 행 데이터
		Object data[][] = {};// 열 데이터
		DefaultTableModel model = new DefaultTableModel(data, colNames);
		
		
		// 표 생성 - 교과목
		String colNames2[] = { "학년", "학수번호", "이수구분", "강의유형", "과목명","학점","강의시간","담당교수","제한인원","수강인원" };// 행 데이터
		Object data2[][] = {};// 열 데이터
		DefaultTableModel model2 = new DefaultTableModel(data2, colNames2);
		
		// 표 생성 - 시간
		String colNames3[] = { "학년", "학수번호", "이수구분", "강의유형", "과목명","학점","강의시간","담당교수","제한인원","수강인원" };// 행 데이터
		Object data3[][] = {};// 열 데이터
		DefaultTableModel model3 = new DefaultTableModel(data3, colNames3);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(219, 85, 936, 455);
		contentPane.add(mainPanel);
		mainPanel.setBackground(null);
		mainPanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 936, 455);
		mainPanel.add(tabbedPane);
		JTable table = new JTable(model);
		table.setBorder(null);
		
		// 학부 패널
		JPanel panelOne1 = new JPanel();
		panelOne1.setBounds(104, 269, 544, 349);
		
		// 스크롤
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 44, 931, 379);
		tabbedPane.addTab("학부", null, panelOne1, null);
		scrollPane.setBorder(null);
		JTable table3 = new JTable(model3);
		panelOne1.setLayout(null);
		panelOne1.add(scrollPane);
		
		JComboBox combo1 = new JComboBox();
		combo1.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uACF5\uD544\uC218", "\uC804\uACF5\uC120\uD0DD", "\uAD50\uC591\uD544\uC218", "\uAD50\uC591\uC120\uD0DD"}));
		combo1.setBounds(73, 8, 85, 28);
		panelOne1.add(combo1);
		
		JLabel orderLabel = new JLabel("구분");
		orderLabel.setBounds(14, 13, 62, 18);
		panelOne1.add(orderLabel);
		
		JLabel majorLabel = new JLabel("학과");
		majorLabel.setBounds(195, 13, 62, 18);
		panelOne1.add(majorLabel);
		
		JComboBox orderCombo = new JComboBox();
		orderCombo.setModel(
				new DefaultComboBoxModel(new String[] { "소프트웨어융합학과", "국어국문학과", "영어영문학과", "경제학과", "화학과", "그 외" }));
		orderCombo.setBounds(245, 8, 144, 28);
		panelOne1.add(orderCombo);
		
		JButton showBtn = new JButton("조회");
		showBtn.setBounds(840,8, 85, 28);
		panelOne1.add(showBtn);
		JScrollPane scrollPane3 = new JScrollPane(table3);
		scrollPane3.setBounds(0, 44, 931, 379);
		
		JPanel panelThree = new JPanel();
		panelThree.setLayout(null);
		tabbedPane.addTab("교과목", null, panelThree, null);
		panelThree.add(scrollPane3);
		
		JLabel searchLabel = new JLabel("교과목검색");
		searchLabel.setBounds(14, 13, 83, 18);
		panelThree.add(searchLabel);
		
		JButton showBtn3 = new JButton("조회");
		showBtn3.setBounds(840,8, 85, 28);
		panelThree.add(showBtn3);
		
		// 교과목 검색 필드
		JTextField searchField = new JTextField();
		searchField.setBounds(92, 8, 145, 24);
		panelThree.add(searchField);
		searchField.setColumns(10);
		
		// 학수번호 라디오
		JRadioButton subNumRdo = new JRadioButton("학수번호");
		subNumRdo.setBounds(249, 8, 85, 27);
		panelThree.add(subNumRdo);
		
		// 과목명 라디오
		JRadioButton subNameRdo = new JRadioButton("과목명");
		subNameRdo.setBounds(339, 8, 139, 27);
		panelThree.add(subNameRdo);
		JTable table2 = new JTable(model2);
		
		// 스크롤
		JScrollPane scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(0, 44, 931, 379);
		
		JPanel panelTwo = new JPanel();
		panelTwo.setLayout(null);
		tabbedPane.addTab("시간", null, panelTwo, null);
		panelTwo.add(scrollPane2);
		
		JComboBox dayComobo = new JComboBox();
		dayComobo.setModel(new DefaultComboBoxModel(new String[] { "월", "화", "수", "목", "금" }));
		dayComobo.setBounds(73, 8, 85, 28);
		panelTwo.add(dayComobo);
		
		// 요일 라벨
		JLabel dayLabel = new JLabel("요일");
		dayLabel.setBounds(14, 13, 62, 18);
		panelTwo.add(dayLabel);
		
		JLabel timeLabel = new JLabel("시간");
		timeLabel.setBounds(195, 13, 62, 18);
		panelTwo.add(timeLabel);
		
		// 조회 버튼
		JButton showBtn2 = new JButton("조회");
		showBtn2.setBounds(840,8, 85, 28);
		panelTwo.add(showBtn2);
		
		// 시간 필드(앞)
		timeField1 = new JTextField();
		timeField1.setBounds(241, 10, 42, 24);
		panelTwo.add(timeField1);
		timeField1.setColumns(10);
		
		// 시간필드(뒤)
		timeField2 = new JTextField();
		timeField2.setColumns(10);
		timeField2.setBounds(321, 10, 42, 24);
		panelTwo.add(timeField2);
		
		JLabel wavePanel = new JLabel("~");
		wavePanel.setBounds(300, 12, 16, 18);
		panelTwo.add(wavePanel);
	
		//라디오 버튼 중 둘 중 하나만 선택할 수 있도록 설정하기(중요함)
		ButtonGroup bg = new ButtonGroup();
		bg.add(subNumRdo);
		bg.add(subNameRdo);
		
		
		// 학부필드에서 검색했을 경우
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				// 테이블 로우 초기화 시키기
				if (table.getRowCount() > 0) {
					model.setNumRows(0);
				}

				// 해당 학기 데이터 받아오기
				String Semester = yearBox.getSelectedItem().toString() + "-" + semesterBox.getSelectedItem().toString();

				// 이수구분 데이터 받아오기
				String Classification = combo1.getSelectedItem().toString();

				// 학과 데이터 받아오기
				String Major;

				// 학과에 따라 들어가는 인자를 다르게 하기
				if (orderCombo.getSelectedItem().toString().equals("소프트웨어융합학과")) {
					Major = "MT";
				} else if (orderCombo.getSelectedItem().toString().equals("국어국문학과")) {
					Major = "KL";
				} else if (orderCombo.getSelectedItem().toString().equals("영어영문학과")) {
					Major = "EL";
				} else if (orderCombo.getSelectedItem().toString().equals("경제학과")) {
					Major = "EC";
				} else if (orderCombo.getSelectedItem().toString().equals("화학과")) {
					Major = "CH";
				} else {
					Major = "AA";
				}

				try {
					rs = mg.searchLecture("학부", Classification, Major, Semester);
					if (rs.getRow() > -1) {
						while (rs.next()) {

							// 각각 메소드 값을 받아와서 할당함.
							String leName = rs.getString(1);
							String leNum = rs.getString(2);
							String semester = rs.getString(3);
							int limGrade = rs.getInt(4);
							String prName = rs.getString(5);
							String type = rs.getString(6);
							String time = rs.getString(7);
							int credit = rs.getInt(8);
							int attMem = rs.getInt(9);
							int limMem = rs.getInt(10);
							String preLecture = rs.getString(11);
							String division = rs.getString(12);
							// 수업 객체 생성
							Lecture lec = new Lecture(leName, leNum, semester, limGrade, prName, type, time,
									credit, attMem, limMem, preLecture, division, "0");

							// GUI에 띄우기
							String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(),
									lec.getType(), lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(),
									lec.getprName(), Integer.toString(lec.getLimMem()),
									Integer.toString(lec.getAttMem()), lec.getPreLecture() };

							model.addRow(rows);
						}
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//시간에 따라 조회하기
		showBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				// 테이블 로우 초기화 시키기
				if (table2.getRowCount() > 0) {
					model2.setNumRows(0);
				}
				// 해당 학기 데이터 받아오기
				String Semester = yearBox.getSelectedItem().toString() + "-" + semesterBox.getSelectedItem().toString();
				// 해당 요일 데이터 받아오기
				String Day = dayComobo.getSelectedItem().toString();
				// 해당 요일에 시간 데이터 받아오기
				String Time = timeField1.getText() + "-" + timeField2.getText();

				System.out.println(Day + Time);

				try {
					rs = mg.searchLecture("시간", Day, Time, Semester);
					if (rs.getRow() > -1) {
						while (rs.next()) {

							// 각각 메소드 값을 받아와서 할당함.
							String leName = rs.getString(1);
							String leNum = rs.getString(2);
							String semester = rs.getString(3);
							int limGrade = rs.getInt(4);
							String prName = rs.getString(5);
							String type = rs.getString(6);
							String time = rs.getString(7);
							int credit = rs.getInt(8);
							int attMem = rs.getInt(9);
							int limMem = rs.getInt(10);
							String preLecture = rs.getString(11);
							String division = rs.getString(12);
							// 수업 객체 생성
							Lecture lec = new Lecture(leName, leNum, semester, limGrade, prName, type, time,
									credit, attMem, limMem, preLecture, division, "0");

							// GUI에 띄우기
							String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(),
									lec.getType(), lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(),
									lec.getprName(), Integer.toString(lec.getLimMem()),
									Integer.toString(lec.getAttMem()), lec.getPreLecture() };

							model2.addRow(rows);
						}
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//시간에 따라 조회하기
				showBtn3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ResultSet rs = null;
						// 테이블 로우 초기화 시키기
						if (table3.getRowCount() > 0) {
							model3.setNumRows(0);
						}
						// 해당 학기 데이터 받아오기
						String Semester = yearBox.getSelectedItem().toString() + "-" + semesterBox.getSelectedItem().toString();
						// 해당 요일 데이터 받아오기
						String Query = searchField.getText();
						
						//교과목과 학수번호 중 어떤 것 선택되었는지 받아오기
						String which = null;
						if(subNameRdo.isSelected())
						{
							which = "과목명";
						}
						else {
							which = "학수번호";
						}
						
						
						try {
							rs = mg.searchLecture("교과목", which, Query, Semester);
							if (rs.getRow() > -1) {
								while (rs.next()) {
									// 각각 메소드 값을 받아와서 할당함.
									String leName = rs.getString(1);
									String leNum = rs.getString(2);
									String semester = rs.getString(3);
									int limGrade = rs.getInt(4);
									String prName = rs.getString(5);
									String type = rs.getString(6);
									String time = rs.getString(7);
									int credit = rs.getInt(8);
									int attMem = rs.getInt(9);
									int limMem = rs.getInt(10);
									String preLecture = rs.getString(11);
									String division = rs.getString(12);
									// 수업 객체 생성
									Lecture lec = new Lecture(leName, leNum, semester, limGrade, prName, type, time,
											credit, attMem, limMem, preLecture, division, "0");

									// GUI에 띄우기
									String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(),
											lec.getType(), lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(),
											lec.getprName(), Integer.toString(lec.getLimMem()),
											Integer.toString(lec.getAttMem()), lec.getPreLecture() };

									model3.addRow(rows);
								}
							}

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});

	}
}
