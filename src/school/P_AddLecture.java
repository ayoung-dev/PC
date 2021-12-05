package school;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;


//개설과목입력
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

//개설과목입력
public class P_AddLecture extends JFrame {
	private JPanel contentPane;
	private JTextField belongTxt;
	private JTextField allLecTxt;
	private JTextField allCreTxt;
	private JTextField gradeField;
	private JTextField creditField;
	private JTextField gradeTxt;
	private JTextField creditTxt;
	private JTextField nameField;
	private JTextField nameTxt;
	private JTextField timeField;
	private JTextField timeTxt;
	private JTextField classificationField;
	private JTextField classificationTxt;
	private JTextField belongTxt3;
	private JTextField belongTxt4;
	private JTextField courseField;
	private JTextField courseTxt;
	private JTextField limitNumField;
	private JTextField limitTxt;

	public int SubNum = 0;
	public static String id;
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;
	static P_Management mg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// 드라이버 로드
				try {
					Class.forName("org.mariadb.jdbc.Driver");
					// sql 접속하기 위한 아이디와 비밀번호 입력

					con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/school", "root", "1234");

					mg = new P_Management(url, con);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Scanner scan = new Scanner(System.in);
				try {
					P_AddLecture frame = new P_AddLecture(id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param con2
	 * @param url2
	 * @throws Exception
	 */

	public P_AddLecture(String id) throws Exception {

		// 기본 패널 생성
		setTitle("학사관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 수업 객체 생성하기
		// 교번을 가진 DB를 찾아서 거기에 저장된 수업 내역 다 불러오기

		// 교수 함수에 연결
		P_Management mg = new P_Management(url, con);
		Functions func = new Functions(url, con);

		// 해당 교수의 이름 불러오기
		String Username = mg.checkNamePr(id);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		JButton LectureBtn = new JButton("수업 시스템");
		LectureBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(LectureBtn);

		// 메뉴안내 라벨
		JButton showLectureLabel = new JButton("개설과목조회");
		showLectureLabel.setBounds(10, 63, 152, 18);
		showLectureLabel.setBorderPainted(false);
		showLectureLabel.setFocusPainted(false);
		showLectureLabel.setContentAreaFilled(false);
		showLectureLabel.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(showLectureLabel);
		showLectureLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				P_ShowLecture psl;
				try {
					psl = new P_ShowLecture(id);
					psl.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton AddLectureLabel = new JButton("개설과목입력");
		AddLectureLabel.setBounds(10, 94, 182, 18);
		AddLectureLabel.setBorderPainted(false);
		AddLectureLabel.setFocusPainted(false);
		AddLectureLabel.setHorizontalAlignment(SwingConstants.LEFT);
		// AddLectureLabel.setContentAreaFilled(false);
		AddLectureLabel.setEnabled(false);
		menuPanel.add(AddLectureLabel);

		JButton showProLectureLabel = new JButton("개설과목내역");
		showProLectureLabel.setBounds(10, 125, 152, 18);
		menuPanel.add(showProLectureLabel);
		showProLectureLabel.setBorderPainted(false);
		showProLectureLabel.setFocusPainted(false);
		showProLectureLabel.setContentAreaFilled(false);
		showProLectureLabel.setHorizontalAlignment(SwingConstants.LEFT);
		showProLectureLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					P_ShowProLecture pspl = new P_ShowProLecture(id);
					pspl.setVisible(true);
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
	        		P_Home phome;
					try {
						phome = new P_Home(id);
						phome.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		
	        	}
	     });
		homeBtn.setBounds(45, 500, 105, 27);
		menuPanel.add(homeBtn);

		JLabel titleLabel = new JLabel("개설과목입력");
		titleLabel.setBounds(219, 13, 109, 18);
		contentPane.add(titleLabel);

		JPanel selectPanel = new JPanel();
		selectPanel.setBounds(219, 38, 936, 35);
		contentPane.add(selectPanel);
		selectPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		selectPanel.setLayout(null);

		// 콤보박스
		JComboBox yearBox = new JComboBox();
		yearBox.setModel(new DefaultComboBoxModel(new String[] { "", "2021", "2020", "2019" }));
		yearBox.setBounds(90, 5, 146, 24);
		selectPanel.add(yearBox);

		JComboBox semesterBox = new JComboBox();
		semesterBox.setModel(new DefaultComboBoxModel(new String[] { "", "1", "2" }));
		semesterBox.setBounds(375, 5, 146, 24);
		selectPanel.add(semesterBox);

		// 안내 라벨
		JLabel yearLabel = new JLabel("학년도");
		yearLabel.setBounds(14, 7, 62, 18);
		selectPanel.add(yearLabel);

		JLabel semesterLabel = new JLabel("학기");
		semesterLabel.setBounds(287, 7, 62, 18);
		selectPanel.add(semesterLabel);

		JButton showBtn = new JButton("조회");
		showBtn.setBounds(834, 3, 93, 27);
		selectPanel.add(showBtn);

		/*
		 * JLabel belongLabel = new JLabel("소속"); belongLabel.setBounds(559, 7, 62, 18);
		 * selectPanel.add(belongLabel);
		 * 
		 * //소속 텍스트 필드(여기서 소속 데이터 입력받을 예정) belongTxt = new JTextField();
		 * belongTxt.setBounds(622, 5, 169, 24); selectPanel.add(belongTxt);
		 * belongTxt.setColumns(10);
		 */

		// 표 생성 - 입력목록
		String colNames[] = { "학년", "학수번호", "이수구분", "강의유형", "과목명", "학점", "강의시간", "담당교수", "제한인원", "수강인원", "선수과목",
				"개설취소" };// 행 데이터
		Object data[][] = {};// 열 데이터
		DefaultTableModel model = new DefaultTableModel(data, colNames);

		// 메인 패널 : 이 위에 입력 목록과 과목 입력 화면을 구현함
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		mainPanel.setBounds(219, 85, 936, 455);
		contentPane.add(mainPanel);
		mainPanel.setBackground(null);
		mainPanel.setLayout(null);

		// 입력 목록 라벨
		JLabel addListLabel = new JLabel("입력 목록");
		addListLabel.setBounds(14, 13, 62, 18);
		mainPanel.add(addListLabel);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 44, 908, 181);
		scrollPane.setBorder(null);
		mainPanel.add(scrollPane);

		// 총 개설 강좌 라벨
		JLabel allLecLabel = new JLabel("총 개설 강좌 수");
		allLecLabel.setBounds(617, 239, 110, 18);
		mainPanel.add(allLecLabel);

		// 총 개설 학점 라벨
		JLabel allCreLabel = new JLabel("총 개설 학점");
		allCreLabel.setBounds(780, 239, 110, 18);
		mainPanel.add(allCreLabel);

		// 총 개설 강좌 텍스트필드(데이터입력)
		allLecTxt = new JTextField();
		allLecTxt.setEditable(false);
		allLecTxt.setBounds(705, 238, 62, 24);
		mainPanel.add(allLecTxt);
		allLecTxt.setColumns(10);

		// 총 개설 학점 텍스트필드(데이터입력)
		allCreTxt = new JTextField();
		allCreTxt.setEditable(false);
		allCreTxt.setColumns(10);
		allCreTxt.setBounds(855, 238, 62, 24);
		mainPanel.add(allCreTxt);

		// 과목 입력 라벨
		JLabel addSubLabel = new JLabel("과목 입력");
		addSubLabel.setBounds(14, 244, 62, 18);
		mainPanel.add(addSubLabel);

		// 첫 번째 줄 패널 - 과목입력
		JPanel panel = new JPanel();
		panel.setBounds(14, 275, 908, 31);
		mainPanel.add(panel);
		panel.setLayout(null);

		// 학년 필드
		gradeField = new JTextField();
		gradeField.setHorizontalAlignment(SwingConstants.CENTER);
		gradeField.setText("학년");
		gradeField.setBounds(0, 0, 116, 31);
		panel.add(gradeField);
		gradeField.setColumns(10);

		// 학년 입력란
		gradeTxt = new JTextField();
		gradeTxt.setBounds(115, 0, 283, 31);
		panel.add(gradeTxt);
		gradeTxt.setColumns(10);

		// 학점 필드
		creditField = new JTextField();
		creditField.setText("학점");
		creditField.setHorizontalAlignment(SwingConstants.CENTER);
		creditField.setBounds(398, 0, 116, 31);
		panel.add(creditField);
		creditField.setColumns(10);

		// 학점 입력란
		creditTxt = new JTextField();
		creditTxt.setBounds(514, 0, 394, 31);
		panel.add(creditTxt);
		creditTxt.setColumns(10);

		// 두 번째 줄 패널 - 과목입력
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(14, 307, 908, 31);
		mainPanel.add(panel2);

		// 과목명 필드
		nameField = new JTextField();
		nameField.setText("과목명");
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setColumns(10);
		nameField.setBounds(0, 0, 116, 31);
		panel2.add(nameField);

		// 과목명 입력란
		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		nameTxt.setBounds(115, 0, 283, 31);
		panel2.add(nameTxt);

		// 강의시간 필드
		timeField = new JTextField();
		timeField.setText("강의시간");
		timeField.setHorizontalAlignment(SwingConstants.CENTER);
		timeField.setColumns(10);
		timeField.setBounds(398, 0, 116, 31);
		panel2.add(timeField);

		// 강의시간 입력란
		timeTxt = new JTextField();
		timeTxt.setColumns(10);
		timeTxt.setBounds(514, 0, 394, 31);
		panel2.add(timeTxt);

		// 세 번째 패널 - 과목입력
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(14, 339, 908, 31);
		mainPanel.add(panel3);

		// 이수구분 필드
		classificationField = new JTextField();
		classificationField.setText("이수구분");
		classificationField.setHorizontalAlignment(SwingConstants.CENTER);
		classificationField.setColumns(10);
		classificationField.setBounds(0, 0, 116, 31);
		panel3.add(classificationField);

		// 이수구분 입력란
		classificationTxt = new JTextField();
		classificationTxt.setColumns(10);
		classificationTxt.setBounds(115, 0, 283, 31);
		panel3.add(classificationTxt);

		// 강의유형 필드
		belongTxt3 = new JTextField();
		belongTxt3.setText("강의유형");
		belongTxt3.setHorizontalAlignment(SwingConstants.CENTER);
		belongTxt3.setColumns(10);
		belongTxt3.setBounds(398, 0, 116, 31);
		panel3.add(belongTxt3);

		// 강의유형 입력란
		belongTxt4 = new JTextField();
		belongTxt4.setColumns(10);
		belongTxt4.setBounds(514, 0, 394, 31);
		panel3.add(belongTxt4);

		// 네 번째 패널 - 과목입력
		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBounds(14, 372, 908, 31);
		mainPanel.add(panel4);

		// 선수과목 필드
		courseField = new JTextField();
		courseField.setText("선수과목");
		courseField.setHorizontalAlignment(SwingConstants.CENTER);
		courseField.setColumns(10);
		courseField.setBounds(0, 0, 116, 31);
		panel4.add(courseField);

		// 선수과목 입력란
		courseTxt = new JTextField();
		courseTxt.setColumns(10);
		courseTxt.setBounds(115, 0, 283, 31);
		panel4.add(courseTxt);

		// 제한인원 필드
		limitNumField = new JTextField();
		limitNumField.setText("제한인원");
		limitNumField.setHorizontalAlignment(SwingConstants.CENTER);
		limitNumField.setColumns(10);
		limitNumField.setBounds(398, 0, 116, 31);
		panel4.add(limitNumField);

		// 제한인원 입력란
		limitTxt = new JTextField();
		limitTxt.setColumns(10);
		limitTxt.setBounds(514, 0, 394, 31);
		panel4.add(limitTxt);

		// 입력 버튼
		JButton inputBtn = new JButton("입력");
		inputBtn.setBounds(817, 416, 105, 27);
		mainPanel.add(inputBtn);

		// 조회하기 기능
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 드랍박스에 있는 데이터 불러오기
				String InputSemester = null;
				InputSemester = yearBox.getSelectedItem().toString() + "-" + semesterBox.getSelectedItem().toString();
				System.out.println(InputSemester);

				// 테이블에 모든 줄 초기화 하기 - 아예 새로 띄울것이기 때문
				if (table.getRowCount() > 0) {
					model.setNumRows(0);
				}

				// 선택한 곳의 해당 크레딧 계산해줄 변수
				int sumcre = 0;

				try {
					ResultSet rs = null;
					if (!InputSemester.equals("-")) {
						// 사용자가 등록한 과목 불러오기
						rs = mg.getClasses(InputSemester, Username);
					} else {
						// 학기를 선택하지 않았을 경우 해당 사용자가 지금까지 개설한 과목 전체목록 받아오기
						rs = mg.getLecture(Username);
					}

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
						Lecture lec = new Lecture(leName, leNum, semester, limGrade, prName, type, time, credit,
								attMem, limMem, preLecture, division, "0");

						// GUI에 띄우기
						String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(),
								lec.getType(), lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(),
								lec.getprName(), Integer.toString(lec.getLimMem()), Integer.toString(lec.getAttMem()),
								lec.getPreLecture() };

						model.addRow(rows);

						sumcre += lec.getCredit();

					}
					allLecTxt.setText(Integer.toString(table.getRowCount()));
					allCreTxt.setText(Integer.toString(sumcre));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// 입력 버튼 누르면?
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String grade = gradeTxt.getText();
				String Credit = creditTxt.getText();
				String Name = nameTxt.getText();
				String Time = timeTxt.getText();
				String Classification = classificationTxt.getText();
				String Belong = belongTxt4.getText();
				String Course = courseTxt.getText();
				String Limitmem = limitTxt.getText();

				if (!Name.equals("") && !grade.equals("") && !Credit.equals("") && !Time.equals("")
						&& !Classification.equals("") && !Belong.equals("") && !Limitmem.equals("")) {
					Lecture lec = new Lecture(Name, "", "2021-1", Integer.parseInt(grade), Username, Belong, Time,
							Integer.parseInt(Credit), 0, Integer.parseInt(Limitmem), Course, Classification, "0");
					try {
						// SQL에 입력하기
						// 수업 객체를 받아서 학수번호 만들기(이때 학수 번호는 NULL 값임)
						// 만들어낸 학수번호를 포함해 table에 추가하기
						String Number = func.makeLeNum(lec, Username);
						lec.setleNum(Number);
						mg.InsertLecture(lec);

						// GUI에 띄우기
						String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(),
								lec.getType(), lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(),
								lec.getprName(), Integer.toString(lec.getLimMem()), Integer.toString(lec.getAttMem()),
								lec.getPreLecture() };

						model.addRow(rows);

						// 띄운 후 EditText내용 비우기
						gradeTxt.setText("");
						creditTxt.setText("");
						nameTxt.setText("");
						timeTxt.setText("");
						classificationTxt.setText("");
						belongTxt4.setText("");
						courseTxt.setText("");
						limitTxt.setText("");
						SubNum++;

						int sumcre = 0;
						if (!allCreTxt.getText().contentEquals("")) {
							sumcre = Integer.parseInt(allCreTxt.getText());
						}

						allLecTxt.setText(Integer.toString(table.getRowCount()));
						allCreTxt.setText(Integer.toString(sumcre + lec.getCredit()));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

	}
}
