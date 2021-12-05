package school;

//수강신청
//수강신청
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
import javax.swing.JOptionPane;
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
import javax.swing.table.TableModel;
import javax.swing.DefaultComboBoxModel;

public class S_ApplicationClass extends JFrame {

	private JPanel contentPane;
	private JTextField searchSubField;

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

					con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/school", "root", "1234");

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
	 * 
	 * @throws Exception
	 */
	public S_ApplicationClass(String id) throws Exception {
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
		S_Management sg = new S_Management(url, con);

		String Username = sg.getstName(id);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		JButton LectureBtn = new JButton("수업 시스템");
		LectureBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(LectureBtn);

		JButton showAllClassLabel = new JButton("개설과목조회");
		showAllClassLabel.setBounds(10, 63, 152, 18);
		menuPanel.add(showAllClassLabel);
		showAllClassLabel.setBorderPainted(false);
		showAllClassLabel.setFocusPainted(false);
		showAllClassLabel.setContentAreaFilled(false);
		showAllClassLabel.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(showAllClassLabel);
		showAllClassLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				S_ShowAllClasses sacs;
				try {
					sacs = new S_ShowAllClasses(id);
					sacs.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

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
		applicationLabel.setBorderPainted(false);
		applicationLabel.setFocusPainted(false);
		applicationLabel.setHorizontalAlignment(SwingConstants.LEFT);
		applicationLabel.setEnabled(false);
		menuPanel.add(applicationLabel);

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

		JLabel titleLabel = new JLabel("수강신청");
		titleLabel.setBounds(219, 13, 109, 18);
		contentPane.add(titleLabel);

		JPanel selectPanel = new JPanel();
		selectPanel.setBounds(219, 42, 936, 36);
		contentPane.add(selectPanel);
		selectPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		selectPanel.setLayout(null);

		JComboBox orderBox = new JComboBox();
		orderBox.setModel(new DefaultComboBoxModel(new String[] { "장바구니", "교양필수", "교양선택" }));
		orderBox.setBounds(70, 3, 111, 28);
		selectPanel.add(orderBox);

		JComboBox majorBox = new JComboBox();
		majorBox.setModel(
				new DefaultComboBoxModel(new String[] { "소프트웨어융합학과", "국어국문학과", "영어영문학과", "경제학과", "화학과", "그 외" }));
		majorBox.setBounds(253, 3, 153, 28);
		selectPanel.add(majorBox);

		// 장바구니를 선택했다면 전공 콤보박스 선택하지 않아도 되도록 하기
		if (orderBox.getSelectedItem().toString().equals("장바구니")) {
			majorBox.setEnabled(false);
		}

		// 장바구니를 선택했다면 전공 콤보박스 선택하지 않아도 되도록 하기
		orderBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource(); // 콤보박스 알아내기

				int index = cb.getSelectedIndex();// 선택된 아이템의 인덱스

				if (index == 0) {
					majorBox.setEnabled(false);
				} else {
					majorBox.setEnabled(true);
				}
			}
		});

		JLabel orderLabel = new JLabel("구분");
		orderLabel.setBounds(14, 5, 62, 18);
		selectPanel.add(orderLabel);

		JLabel majorLabel = new JLabel("학과");
		majorLabel.setBounds(195, 5, 62, 18);
		selectPanel.add(majorLabel);

		JButton showBtn = new JButton("조회");
		showBtn.setBounds(833, 3, 93, 25);
		selectPanel.add(showBtn);

		JLabel searchSubLabel = new JLabel("교과목검색");
		searchSubLabel.setBounds(432, 5, 85, 18);
		selectPanel.add(searchSubLabel);

		// 교과목검색 필드
		searchSubField = new JTextField();
		searchSubField.setBounds(507, 4, 116, 26);
		selectPanel.add(searchSubField);
		searchSubField.setColumns(10);

		JRadioButton subNumRdo = new JRadioButton("학수번호");
		subNumRdo.setBounds(629, 3, 85, 27);
		selectPanel.add(subNumRdo);

		JRadioButton subNameRdo = new JRadioButton("과목명");
		subNameRdo.setBounds(713, 3, 93, 27);
		selectPanel.add(subNameRdo);

		// 라디오 버튼 중 둘 중 하나만 선택할 수 있도록 설정하기(중요함)
		ButtonGroup bg = new ButtonGroup();
		bg.add(subNumRdo);
		bg.add(subNameRdo);

		// 표 생성 - 수강신청 신청란
		String colNames[] = {"학년", "학수번호", "이수구분", "강의유형", "과목명", "학점", "강의시간", "담당교수", "제한인원", "수강인원",
				"선수과목" };// 행 데이터
		Object data[][] = {};// 열 데이터
		DefaultTableModel model = new DefaultTableModel(data, colNames);

		// 표 생성 - 수강신청 취소란
		String colNames2[] = {"학년", "학수번호", "이수구분", "강의유형", "과목명", "학점", "강의시간", "담당교수", "선수과목" };// 행 데이터
		Object data2[][] = {};// 열 데이터
		DefaultTableModel model2 = new DefaultTableModel(data2, colNames2);
		JTable table2 = new JTable(model2);

		// 스크롤
		JScrollPane scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(14, 229, 912, 213);

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		mainPanel.setBounds(219, 85, 936, 455);
		contentPane.add(mainPanel);
		mainPanel.setBackground(null);
		mainPanel.setLayout(null);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 13, 908, 179);
		scrollPane.setBorder(null);
		mainPanel.add(scrollPane);
		mainPanel.add(scrollPane2);

		JLabel titleLabel2 = new JLabel("신청 내역");
		titleLabel2.setBounds(14, 205, 145, 18);
		mainPanel.add(titleLabel2);

		JLabel totalSubLabel = new JLabel("총 신청 강좌 수");
		totalSubLabel.setBounds(197, 205, 99, 18);
		mainPanel.add(totalSubLabel);

		JLabel totalCreLabel = new JLabel("총 신청 학점");
		totalCreLabel.setBounds(376, 205, 99, 18);
		mainPanel.add(totalCreLabel);

		// 수강 신청 - 신청 패널
		JTextField panel = new JTextField();
		panel.setEnabled(false);
		panel.setBounds(300, 205, 62, 18);
		mainPanel.add(panel);

		// 수강 신청 - 취소 패널
		JTextField panel2 = new JTextField();
		panel2.setEnabled(false);
		panel2.setBounds(463, 205, 62, 18);
		mainPanel.add(panel2);

		JButton submit = new JButton("신청");
		submit.setBounds(829, 202, 93, 25);
		mainPanel.add(submit);
		
		//첫 화면 로드했을 때 - 해당 사용자가 수강신청한 수업 내역 model2에 띄우기
		ResultSet rs = null;
		rs = sg.getStClasses(id, "2021-1");
		if (rs.getRow() > -1) {
	      while(rs.next())
	      {
	         //학수번호만 불러와서
	         String leNum = rs.getString(2);
	         //해당 학수번호를 가진 수업 객체 들고 오기
	         Lecture lec = sg.getLe(leNum);
	      // GUI에 띄우기
				String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(), lec.getType(),
						lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(), lec.getprName(),lec.getPreLecture() };

				model2.addRow(rows);
	      }
		}						
		
		//사용자가 신청한 총 강좌 수
		int num = sg.getStudentTotalCount(id, "2021-1");
		panel.setText(Integer.toString(num));
		
		//사용자가 신청한 총 학점
		num = sg.getStudentTotalCredit(id, "2021-1");
		panel2.setText(Integer.toString(num));
		

		// 조회버튼을 눌렀을 때
		// 등록되어 있는 수업 내역 불러오기
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				// 테이블 로우 초기화 시키기
				if (table.getRowCount() > 0) {
					model.setNumRows(0);
				}

				// 해당 학기 데이터 받아오기
				String Semester = "2021-1";

				// 이수구분 데이터 받아오기
				String Classification = orderBox.getSelectedItem().toString();

				if (Classification.contentEquals("장바구니")) {
					try {
						// 맨처음 화면이 시작할 때 화면 구성
						rs = sg.getBucket(id, "2021-1");
						if (rs.getRow() > -1) {
							while (rs.next()) {
								// 학수번호만 불러와서
								String leNum = rs.getString(2);
								// 해당 학수번호를 가진 수업 객체 들고 오기
								Lecture lec = sg.getLe(leNum);
								// GUI에 띄우기
								String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(),
										lec.getDivision(), lec.getType(), lec.getleName(),
										Integer.toString(lec.getCredit()), lec.getTime(), lec.getprName(),
										Integer.toString(lec.getLimMem()), Integer.toString(lec.getAttMem()),
										lec.getPreLecture() };

								model.addRow(rows);
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					// 학과 데이터 받아오기
					String Major;

					// 학과에 따라 들어가는 인자를 다르게 하기
					if (majorBox.getSelectedItem().toString().equals("소프트웨어융합학과")) {
						Major = "MT";
					} else if (majorBox.getSelectedItem().toString().equals("국어국문학과")) {
						Major = "KL";
					} else if (majorBox.getSelectedItem().toString().equals("영어영문학과")) {
						Major = "EL";
					} else if (majorBox.getSelectedItem().toString().equals("경제학과")) {
						Major = "EC";
					} else if (majorBox.getSelectedItem().toString().equals("화학과")) {
						Major = "CH";
					} else {
						Major = "AA";
					}

					String queary = searchSubField.getText();
					// 교과목과 학수번호 중 어떤 것 선택되었는지 받아오기
					String which = null;
					if (subNameRdo.isSelected()) {
						which = "과목명";
					} else {
						which = "학수번호";
					}

					try {
						if (queary.equals("")) {
							rs = sg.bucketLecture(Classification, Major, "", which, Semester);
						} else {
							rs = sg.bucketLecture(Classification, Major, queary, which, Semester);
						}
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
								String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(),
										lec.getDivision(), lec.getType(), lec.getleName(),
										Integer.toString(lec.getCredit()), lec.getTime(), lec.getprName(),
										Integer.toString(lec.getLimMem()), Integer.toString(lec.getAttMem()),
										lec.getPreLecture() };

								model.addRow(rows);

							}
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		// 수강신청 버튼을 눌렀을 때
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;

				// 선택한 로우의 정보 들고오기
				int row = table.getSelectedRow();
				// 해당 로우의 학수번호 정보 들고오기 - colnumNum = 2
				String stNum = table.getValueAt(row, 1).toString();
				try {
					// 해당 학수 번호를 가진 수업 정보 불러오기
					Lecture lec = sg.getLe(stNum);

					//강의 시간 중복 없는지, 해당 강의 수강 인원 초과 안 하는지, 18학점(한 학기 제한 수강 학점) 넘지 않는지 확인하기
					 if(sg.isClass(id, lec) == 0&&lec.getLimMem()>lec.getAttMem()+1&&sg.getStudentTotalCredit(id, "2021-1")<18)
						{
							sg.applicationClass(id, lec, Username);
							// GUI에 띄우기
							String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(),
									lec.getType(), lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(),
									lec.getprName(),lec.getPreLecture() };

							model2.addRow(rows);
						}
					 else if(sg.isClass(id, lec) != 0)
					 {
						 JOptionPane.showMessageDialog(null, "중복 시간대의 수업이 존재합니다.");
					 }else if(lec.getLimMem()<lec.getAttMem()+1)
					 {
						 JOptionPane.showMessageDialog(null, "수강 제한 인원을 초과하였습니다.");
					 }else if(sg.getStudentTotalCredit(id, "2021-1")>=18)
					 {
						 JOptionPane.showMessageDialog(null, "한 학기 수강 제한 학점을 초과하였습니다.");
					 }
					 
						//사용자가 신청한 총 강좌 수
						int num = sg.getStudentTotalCount(id, "2021-1");
						panel.setText(Integer.toString(num));
						
						//사용자가 신청한 총 학점
						num = sg.getStudentTotalCredit(id, "2021-1");
						panel2.setText(Integer.toString(num));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}
}
