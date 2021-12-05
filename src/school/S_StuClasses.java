package school;

//수강신청내역
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

public class S_StuClasses extends JFrame {

	private JPanel contentPane;
	private JTextField totalNumField;
	private JTextField totalCreField;
	public static String id;
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Class.forName("org.mariadb.jdbc.Driver");
				// sql 접속하기 위한 아이디와 비밀번호 입력
				url = "jdbc:mariadb://localhost:3306/school";

				con = DriverManager.getConnection(url, "root", "1234");

					S_StuClasses frame = new S_StuClasses(id);
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
	public S_StuClasses(String id) throws Exception {
		setTitle("학사관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// 교수 함수에 연결
		P_Management mg = new P_Management(url, con);
		S_Management sg = new S_Management(url, con);

		// 해당 학생의 이름 불러오기
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
		stuClassLabel.setBorderPainted(false);
		stuClassLabel.setFocusPainted(false);
		stuClassLabel.setHorizontalAlignment(SwingConstants.LEFT);
		stuClassLabel.setEnabled(false);
		menuPanel.add(stuClassLabel);
		
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
		
		
		JLabel titleLabel = new JLabel("수강신청 내역");
		titleLabel.setBounds(219, 13, 109, 18);
		contentPane.add(titleLabel);
		JPanel panelOne = new JPanel();
		panelOne.setBounds(104, 269, 544, 349);
		
		JPanel selectPanel = new JPanel();
		selectPanel.setBounds(219, 44, 936, 28);
		selectPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		contentPane.add(selectPanel);
		selectPanel.setLayout(null);
		
		JComboBox yearBox = new JComboBox();
		yearBox.setModel(new DefaultComboBoxModel(new String[] {"2021", "2020", "2019"}));
		yearBox.setBounds(90, 2, 146, 23);
		selectPanel.add(yearBox);
		
		JComboBox semesterBox = new JComboBox();
		semesterBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		semesterBox.setBounds(350, 2, 146, 23);
		selectPanel.add(semesterBox);
		
		JLabel yearLabel = new JLabel("학년도");
		yearLabel.setBounds(14, 5, 62, 18);
		selectPanel.add(yearLabel);
		
		JLabel semesterLabel = new JLabel("학기");
		semesterLabel.setBounds(287, 5, 62, 18);
		selectPanel.add(semesterLabel);
		
		JButton showBtn = new JButton("조회");
		showBtn.setBounds(838, 2, 95, 23);
		selectPanel.add(showBtn);
		
		// 표 생성 - 수강신청내역조회
		String colNames[] = {"학년", "학수번호", "이수구분", "강의유형", "과목명","학점","강의시간","담당교수"};// 행 데이터
		Object data[][] = {};// 열 데이터
		DefaultTableModel model = new DefaultTableModel(data, colNames);
		LineBorder JBorder = new LineBorder(Color.gray);
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		mainPanel.setBounds(219, 85, 936, 455);
		contentPane.add(mainPanel);
		mainPanel.setBackground(null);
		mainPanel.setLayout(null);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 13, 908, 395);
		scrollPane.setBorder(null);
		mainPanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(682, 407, 240, 35);
		mainPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		totalNumField = new JTextField();
		totalNumField.setEditable(false);
		totalNumField.setText("총 과목 수:");
		panel.add(totalNumField, BorderLayout.WEST);
		totalNumField.setColumns(10);
		
		totalCreField = new JTextField();
		totalCreField.setEditable(false);
		totalCreField.setText("총 신청학점:");
		panel.add(totalCreField, BorderLayout.CENTER);
		totalCreField.setColumns(10);
		

			// 조회버튼을 눌렀을 때
				// 등록되어 있는 수업 내역 불러오기
				showBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							try {
								// 테이블 로우 초기화 시키기
								if (table.getRowCount() > 0) {
									model.setNumRows(0);
								}
								
								String year = yearBox.getSelectedItem().toString();
								year +="-" + semesterBox.getSelectedItem().toString();
								//첫 화면 로드했을 때 - 해당 사용자가 수강신청한 수업 내역 model2에 띄우기
								ResultSet rs = null;
								rs = sg.getStClasses(id, year);
								if (rs.getRow() > -1) {
							      while(rs.next())
							      {
							         //학수번호만 불러와서
							         String leNum = rs.getString(2);
							         //해당 학수번호를 가진 수업 객체 들고 오기
							         Lecture lec = sg.getLe(leNum);
							      // GUI에 띄우기
										String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(), lec.getType(),
												lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(), lec.getprName(),
												Integer.toString(lec.getLimMem()), Integer.toString(lec.getAttMem()), lec.getPreLecture() };

										model.addRow(rows);
							      }
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
									//사용자가 신청한 총 강좌 수
									int num = sg.getStudentTotalCount(id, year);
									totalNumField.setText("총 과목 수:"+Integer.toString(num));
									
									//사용자가 신청한 총 학점
									num = sg.getStudentTotalCredit(id, year);
									totalCreField.setText("총 신청학점:" + Integer.toString(num));
								}

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
				});

}}
