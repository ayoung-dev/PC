package school;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.SwingConstants;

// 성적관리
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

// 성적관리
public class P_Grade extends JFrame {
	public static String id;
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;
	private JPanel contentPane;
	private JTextField totalNumField;
	private JTextField avgField;
	public Lecture lec = null;

	public String semester;
	
	public P_Grade(String id) throws Exception {
		setTitle("학사관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Class.forName("org.mariadb.jdbc.Driver");
		// sql 접속하기 위한 아이디와 비밀번호 입력

		con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/school", "root", "1234");

		// 교수 함수에 연결
		P_Management pmg = new P_Management(url, con);
		Functions func = new Functions(url, con);

		// 해당 교수의 이름 불러오기
		String prName = pmg.checkNamePr(id);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		JButton GradeAdBtn = new JButton("성적 시스템");
		GradeAdBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(GradeAdBtn);

		JButton gradeMenuLabel = new JButton("성적관리");
		gradeMenuLabel.setBounds(10, 63, 152, 18);
		gradeMenuLabel.setBorderPainted(false);
		gradeMenuLabel.setFocusPainted(false);
		gradeMenuLabel.setContentAreaFilled(false);
		gradeMenuLabel.setHorizontalAlignment(SwingConstants.LEFT);
		gradeMenuLabel.setEnabled(false);
		menuPanel.add(gradeMenuLabel);

		JLabel titleLabel = new JLabel("성적관리");
		titleLabel.setBounds(219, 13, 109, 18);
		contentPane.add(titleLabel);

		// 홈 화면
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

		JPanel selectPanel = new JPanel();
		selectPanel.setBounds(219, 38, 936, 35);
		contentPane.add(selectPanel);
		selectPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		selectPanel.setLayout(null);

		// 학년도 콤보박스
		String[] listYear = { "선택", "2019", "2020", "2021" };
		JComboBox yearBox = new JComboBox(listYear);
		yearBox.setModel(new DefaultComboBoxModel(new String[] { "\uC120\uD0DD", "2019", "2020", "2021" }));
		yearBox.setBounds(70, 3, 111, 28);
		selectPanel.add(yearBox);

		// 학기 콤보박스
		String[] listSemester = { "선택", "1", "2" };
		JComboBox semesterBox = new JComboBox(listSemester);
		semesterBox.setBounds(253, 3, 111, 28);
		selectPanel.add(semesterBox);

		// 학년도 라벨
		JLabel yearLabel = new JLabel("학년도");
		yearLabel.setBounds(14, 6, 62, 18);
		selectPanel.add(yearLabel);

		// 학기 라벨
		JLabel semesterLabel = new JLabel("학기");
		semesterLabel.setBounds(206, 6, 45, 18);
		selectPanel.add(semesterLabel);

		// 과목명 라벨
		JLabel nameLabel = new JLabel("과목명");
		nameLabel.setBounds(617, 6, 45, 18);
		selectPanel.add(nameLabel);

		// 표 생성 (성적조회)
		String colNames[] = { "과목명", "이름", "학번", "중간고사", "기말고사", "과제", "출석", "총점", "학점" };// 행 데이터
		Object data[][] = {};// 열 데이터
		DefaultTableModel model = new DefaultTableModel(data, colNames);
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		mainPanel.setBounds(219, 85, 936, 455);
		contentPane.add(mainPanel);
		mainPanel.setBackground(null);
		mainPanel.setLayout(null);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 37, 908, 371);
		scrollPane.setBorder(null);
		mainPanel.add(scrollPane);

		// 수강인원, 평균 panel
		JPanel panel = new JPanel();
		panel.setBounds(682, 407, 240, 35);
		mainPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		// 수강인원 필드 - 데이터 입력받아야함
		totalNumField = new JTextField();
		totalNumField.setText("수강인원:");
		panel.add(totalNumField, BorderLayout.WEST);
		totalNumField.setColumns(10);

		// 평균 라벨 - 데이터 입력 받아야함
		avgField = new JTextField();
		avgField.setText("평균:");
		panel.add(avgField, BorderLayout.CENTER);
		avgField.setColumns(10);

		// 과목 조회
		// 학기,학년도 검색 버튼
		JLabel subNameLabel = new JLabel();
		JButton findBtn = new JButton("과목조회");
		findBtn.setBounds(385, 3, 95, 27);
		selectPanel.add(findBtn);

		
		//과목 조회버튼
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String YY = (String) yearBox.getSelectedItem();
				String SS = (String) semesterBox.getSelectedItem();
				semester = YY + "-" + SS;

				// 조회 버튼
				JButton showBtn = new JButton("조회");
				selectPanel.add(showBtn);
				showBtn.setBounds(838, 3, 93, 27);

				try {
					List<String> leName = pmg.getLeInSem(prName, semester);

					leName.add(0, "과목선택");
					// 과목명 콤보박스
					JComboBox nameBox = new JComboBox(leName.toArray());
					selectPanel.add(nameBox);
					nameBox.setBounds(676, 3, 150, 28);

					// 조회 버튼 - 과목명 이용하기
					showBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// 선택된 과목 받아오기
							String selectName = (String) nameBox.getSelectedItem();
							System.out.println(selectName);
							pmg.setLeName(selectName);

							// 과목명 라벨 - 데이터 입력받아야 함
							subNameLabel.setText(selectName);
							mainPanel.add(subNameLabel);
							subNameLabel.setBounds(414, 13, 89, 18);

							// 학년도, 학기 받아오기
							String YY = (String) yearBox.getSelectedItem();
							String SS = (String) semesterBox.getSelectedItem();
							String semester = YY + "-" + SS;

							// 사용자가 선택한 수업 객체를 받아오기
							try {
								ResultSet rs = pmg.searchLecture("교과목", "과목명", selectName, semester);

								while (rs.next()) {

									// 각각 메소드 값을 받아와서 할당함.
									String leName1 = rs.getString(1);
									String leNum = rs.getString(2);
									String semester1 = rs.getString(3);
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
									lec = new Lecture(leName1, leNum, semester1, limGrade, prName, type, time, credit,
											attMem, limMem, preLecture, division, "0");
								}

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							// 조회 클릭시 테이블 내용 초기화
							model.setNumRows(0);
							List<LectureScore> sc;
							try {
								sc = pmg.showGrade(semester, lec.getleNum());

								int length = sc.size();

								for (int i = 0; i < length; i++) {
									Object[] row;
									try {
										row = new Object[] { sc.get(i).getLeName(), sc.get(i).getstName(),
												sc.get(i).getstNum(), sc.get(i).getMidScore(), sc.get(i).getFinScore(),
												sc.get(i).getAssiScore(), sc.get(i).getAttendScore(),
												sc.get(i).getTotalScore(), sc.get(i).getGrade()};
										model.addRow(row);
										showBtn.hide();

									} catch (Exception e1) { // getPerson() 익셉션
										e1.printStackTrace();
									}
								}

							} catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}

							// 수강인원 체크
							try {
								int cnt;
								cnt = pmg.getCount(lec.getleNum());
								String toInt = Integer.toString(cnt);
								totalNumField.setText("수강인원: " + toInt);

								// 평균 구하기
								double avg;
								try {
									avg = pmg.stLecture(semester, lec.getleNum());
									avgField.setText("평균: " + avg / cnt);
								} catch (Exception e1) {
									e1.printStackTrace();
								}

							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							nameBox.hide();
						}
					});
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		// 성적입력 버튼
		JButton putGradeBtn = new JButton("성적입력");
		putGradeBtn.setBounds(829, 6, 93, 27);
		mainPanel.add(putGradeBtn);
		
		JLabel ExplainLabel = new JLabel("");
		ExplainLabel.setBounds(430, 380, 50, 15);
		mainPanel.add(ExplainLabel);
		
		
		// 밑줄 함수
		 Font font = ExplainLabel.getFont();
		 ExplainLabel.setForeground(Color.GRAY);
		 Map attributes = font.getAttributes();
		 attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		 ExplainLabel.setFont(font.deriveFont(attributes));
		 

		// 입력튼이 눌렸을 때 -> 성적을 입력하게 한다
		putGradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExplainLabel.setText("성적 변경 실행중입니다.");
				// 성적입력 버튼 숨기기
				if (e.getSource() == putGradeBtn) {
					putGradeBtn.hide();
				}

				// 입력 버튼 생성
				JButton inputGradeBtn = new JButton("확인");
				inputGradeBtn.setBounds(829, 6, 93, 27);
				mainPanel.add(inputGradeBtn);

				// 입력 버튼을 눌렀을 때 -> 성적들이 들어가게 만들고 다시 성적입력 버튼 뜨게 함
				inputGradeBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ExplainLabel.setText("성적 변경 완료되었습니다.");
						// 현재 표에 있는 과목 이름 받아오기
						String leName = model.getValueAt(0, 0).toString();

						// 수정한(upadate된) 표들을 받아온 후 DB에 업데이트 하기
						int rCnt = model.getRowCount();
						for (int j = 0; j < rCnt; j++) {
							try {
								int midScore = Integer.parseInt(model.getValueAt(j, 3).toString()); // 중간고사
								int finalScore = Integer.parseInt(model.getValueAt(j, 4).toString());// 기말고사
								int assiScore = Integer.parseInt(model.getValueAt(j, 5).toString());// 과제
								int attendScore = Integer.parseInt(model.getValueAt(j, 6).toString());// 출석
								int totalScore = Integer.parseInt(model.getValueAt(j, 7).toString()); // 총점
								String grade = model.getValueAt(j, 8).toString();// 학점

								// 수정된 내용 객체에 저장
								LectureScore S = new LectureScore(midScore, finalScore, assiScore, attendScore,
										totalScore, grade);

								// 데베 내용 변경하기
								pmg.modifyScore(model.getValueAt(j, 2).toString(), lec.getleNum(), S);
								// 평균 구하기
								double avg;
								
								try {
									int cnt;
									cnt = pmg.getCount(lec.getleNum());
									avg = pmg.stLecture(semester, lec.getleNum());
									avgField.setText("평균: " + avg / cnt);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}

						}
						putGradeBtn.show(); // 성적 입력 버튼 나타내기
						inputGradeBtn.hide();
					}
				});
			}

		});

	}
}
