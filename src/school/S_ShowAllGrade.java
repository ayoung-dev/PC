package school;

//전체학기 성적조회
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
import java.util.List;
import javax.swing.SwingConstants;

// 전체학기 성적조회
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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

// 전체학기 성적조회
public class S_ShowAllGrade extends JFrame {

	private JPanel contentPane;
	private JTextField totalCreField;
	private JTextField totalMGradeField;
	private JTextField totalAGradeField;
	private JTextField allMField;
	private JTextField allCreField;
	private JTextField allAField;
	
	public static String id;
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;
	
	public S_ShowAllGrade(String id) throws Exception {
		setTitle("학사관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// 학생 함수에 연결
		S_Management sg = new S_Management(url, con);
		
		//학생 유저 이름 받아오기
		String Username = sg.getstName(id);
		//학생 객체 만들기
		Student s = sg.getStudent(id);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		
		JButton gradeBtn = new JButton("성적 시스템");
		gradeBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(gradeBtn);
		
		JButton recentGradeLabel = new JButton("금학기성적조회");
		recentGradeLabel.setBounds(10, 63, 152, 18);
		menuPanel.add(recentGradeLabel);
		recentGradeLabel.setBorderPainted(false);
		recentGradeLabel.setFocusPainted(false);
		recentGradeLabel.setContentAreaFilled(false);
		recentGradeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		recentGradeLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				S_ShowRecentGrade srg;
				try {
					srg = new S_ShowRecentGrade(id);
					srg.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton allGradeLabel = new JButton("전체성적조회");
		allGradeLabel.setBounds(10, 94, 152, 18);
		allGradeLabel.setBorderPainted(false);
		allGradeLabel.setFocusPainted(false);
		allGradeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		allGradeLabel.setEnabled(false);
		menuPanel.add(allGradeLabel);
		
		JButton homeBtn = new JButton("홈 화면");
		homeBtn.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		dispose();
	        		S_Home sHome;
					try {
						sHome = new S_Home(id);
						sHome.setVisible(true);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // 홈 화면 연결(학생)
	        	}
	     });
		homeBtn.setBounds(45, 500, 105, 27);
		menuPanel.add(homeBtn);
		
		
		JLabel titleLabel = new JLabel("전체성적조회");
		titleLabel.setBounds(219, 13, 109, 18);
		contentPane.add(titleLabel);
		
		JPanel semesterPanel = new JPanel();
		semesterPanel.setBounds(910, 56, 245, 28);
		semesterPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		contentPane.add(semesterPanel);
		semesterPanel.setLayout(null);
		
		//학기 콤보박스
		String[] listSemester = { "선택", "19-1","19-2","20-1","20-2", "21-1","21-2"};
		JComboBox semesterBox = new JComboBox(listSemester);
		semesterBox.setModel(new DefaultComboBoxModel(new String[] {"선택", "2019-1", "2019-2", "2020-1", "2020-2", "2021-1", "2021-2"}));
		semesterBox.setBounds(65, 1, 100, 24);
		semesterPanel.add(semesterBox);
		
		//조회 버튼
		JButton showBtn = new JButton("조회");
		showBtn.setBounds(170, 1, 68, 24);
		semesterPanel.add(showBtn);

		//학기 라벨
		JLabel semesterLabel = new JLabel("학기");
		semesterLabel.setBounds(14, 5, 62, 18);
		semesterPanel.add(semesterLabel);
		
		// 표 생성 - 전체 성적 조회
		String colNames[] = {"학수번호", "과목명", "이수구분", "이수학점","강의시간","교수명","점수","학점","등급"};// 행 데이터
		Object data[][] = {};// 열 데이터
		DefaultTableModel model = new DefaultTableModel(data, colNames);		
		
		//조회하기 동작
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//학기 값 받아오기
				String semester = (String) semesterBox.getSelectedItem();
				
				// 선택학기 불러오기
				model.setNumRows(0); // 이전 표 데이터 초기화 
				List<LectureScore> sc;
				try {
					sc = sg.showGrade(s.getstNum(), semester);
					int length = sc.size();
					for (int i=0; i< length; i++) {
						Object[] row;
						row = new Object[] { sc.get(i).getLeNum(), sc.get(i).getLeName(), sc.get(i).getDivision(),
								sc.get(i).getCredit(),sc.get(i).getTime(),sc.get(i).getprName(),
								sc.get(i).getTotalScore(),sc.get(i).getScoreCredit(),sc.get(i).getGrade() };
						model.addRow(row);	
					}
					
					// 전공 총 학점
					int creditCnt = 0;
					for (int i=0; i<length; i++) {
						if(sc.get(i).getDivision().substring(0,1).equals("전"))
						creditCnt = creditCnt + sc.get(i).getCredit();
					}
					totalMGradeField.setText("전공 총 학점:"+creditCnt);
					
					// 총 이수 학점
					int allCreditCnt = 0;
					for (int i=0; i<length; i++) {
						allCreditCnt = allCreditCnt + sc.get(i).getCredit();
					}
					totalCreField.setText("총 이수 학점:"+ allCreditCnt);
				
					// 총 학점
					float allScoreCredit = 0;
					for(int i=0; i<length; i++) {
						allScoreCredit += sc.get(i).getScoreCredit();
					}
					// 소수 둘째 짜리 까지 표시 (세 번째 자리 반올림)
					double recentScoreAvg = allScoreCredit/(length);
					totalAGradeField.setText("총 평점:" + String.format("%.2f", recentScoreAvg));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
						
			}	
		});
				
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		mainPanel.setBounds(219, 85, 936, 455);
		contentPane.add(mainPanel);
		mainPanel.setBackground(null);
		mainPanel.setLayout(null);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 13, 908, 348);
		scrollPane.setBorder(null);
		mainPanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(584, 360, 338, 36);
		mainPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		totalMGradeField = new JTextField();
		totalMGradeField.setText("전공 총 학점:");
		panel.add(totalMGradeField, BorderLayout.WEST);
		totalMGradeField.setColumns(10);
		
		totalCreField = new JTextField();
		totalCreField.setText("총 이수 학점:");
		panel.add(totalCreField);
		totalCreField.setColumns(10);
		
		totalAGradeField = new JTextField();
		totalAGradeField.setText("총 평점:");
		panel.add(totalAGradeField, BorderLayout.EAST);
		totalAGradeField.setColumns(10);
		
		JLabel allTotal = new JLabel("전체누계");
		allTotal.setBounds(414, 374, 62, 36);
		mainPanel.add(allTotal);
		
		JPanel allTotalPanel = new JPanel();
		allTotalPanel.setBounds(14, 409, 908, 36);
		mainPanel.add(allTotalPanel);
		
		JPanel totalPanel = new JPanel();
		allTotalPanel.add(totalPanel);
	
		// 전체 학기동안 수강한 과목 갯수 구하기
		int allSubCnt = sg.getAllSubCnt(id);
		
		allMField = new JTextField();
		allMField.setColumns(10);
		int allMCredit = sg.getAllMCredit(id);
		totalPanel.add(allMField, BorderLayout.WEST);
		allMField.setText("전공 총 학점:" + allMCredit);
	
		allCreField = new JTextField();
		allCreField.setColumns(10);
		int allCreditCnt = sg.getAllCreditCnt(id);
		totalPanel.add(allCreField, BorderLayout.CENTER);
		allCreField.setText("총 이수 학점:"+allCreditCnt);
		
		allAField = new JTextField();
		allAField.setText("총 평점:");
		allAField.setColumns(10);
		double allScoreAvg = sg.getAllScoreAvg(id)/allSubCnt;
		totalPanel.add(allAField, BorderLayout.EAST);
		allAField.setText("총 평점:"+String.format("%.2f", allScoreAvg));
		sg.setstScore(id, allScoreAvg);
	}
}
