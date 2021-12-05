package school;

//개설과목내역
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
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
import javax.swing.DefaultComboBoxModel;

public class P_ShowProLecture extends JFrame {

	private JPanel contentPane;

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

					P_Management mg = new P_Management(url, con);
					P_ShowProLecture frame = new P_ShowProLecture(id);
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
	public P_ShowProLecture(String id) throws Exception {
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

		JButton AddMyLectureLabel = new JButton("개설과목입력");
		AddMyLectureLabel.setBounds(10, 94, 152, 18);
		AddMyLectureLabel.setBorderPainted(false);
		AddMyLectureLabel.setFocusPainted(false);
		AddMyLectureLabel.setContentAreaFilled(false);
		AddMyLectureLabel.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(AddMyLectureLabel);

		AddMyLectureLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					P_AddLecture pal = new P_AddLecture(id);
					pal.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JButton showMyLectureLabel = new JButton("개설과목내역");
		showMyLectureLabel.setBounds(10, 125, 152, 18);
		showMyLectureLabel.setBorderPainted(false);
		showMyLectureLabel.setFocusPainted(false);
		showMyLectureLabel.setHorizontalAlignment(SwingConstants.LEFT);
		showMyLectureLabel.setEnabled(false);
		menuPanel.add(showMyLectureLabel);

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

		JLabel titleLabel = new JLabel("개설과목내역");
		titleLabel.setBounds(219, 13, 109, 18);
		contentPane.add(titleLabel);

		JPanel selectPanel = new JPanel();
		selectPanel.setBounds(219, 44, 936, 28);
		selectPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		contentPane.add(selectPanel);
		selectPanel.setLayout(null);

		JComboBox yearBox = new JComboBox();
		yearBox.setModel(new DefaultComboBoxModel(new String[] { "2021", "2020", "2019" }));
		yearBox.setBounds(90, 2, 146, 23);
		selectPanel.add(yearBox);

		JComboBox semesterBox = new JComboBox();
		semesterBox.setModel(new DefaultComboBoxModel(new String[] { "1", "2" }));
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

		JLabel belongLabel = new JLabel("담당교수");
		belongLabel.setBounds(559, 5, 62, 18);
		selectPanel.add(belongLabel);

		JPanel proPanel = new JPanel();
		proPanel.setBounds(635, 0, 138, 27);
		proPanel.setBackground(Color.lightGray);
		proPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		selectPanel.add(proPanel);

		// 담당교수 이름 - 데이터 받아오기
		JLabel proName = new JLabel(Username);
		proPanel.add(proName);

		// 표 생성 - 개설과목
		String colNames[] = { "학년", "학수번호", "이수구분", "강의유형", "과목명", "학점", "강의시간", "담당교수", "제한인원", "수강인원" };// 행 데이터
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
		scrollPane.setBounds(14, 13, 908, 429);
		scrollPane.setBorder(null);
		mainPanel.add(scrollPane);

		//조회버튼을 눌렀을 때
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getRowCount() > 0) {
					model.setNumRows(0);
				}
				String Semester = yearBox.getSelectedItem().toString() + "-" + semesterBox.getSelectedItem().toString();
				try {
					ResultSet rs = mg.searchLecture("", "", Username,Semester);

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
								lec.getprName(), Integer.toString(lec.getLimMem()), Integer.toString(lec.getAttMem()),
								lec.getPreLecture() };

						model.addRow(rows);
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}
}
