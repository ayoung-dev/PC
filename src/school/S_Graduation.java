package school;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class S_Graduation extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

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

					S_Graduation frame = new S_Graduation(id);

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

	// 졸업 시스템
	public S_Graduation(String id) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 교수 함수에 연결
		P_Management mg = new P_Management(url, con);
		S_Management sg = new S_Management(url, con);

		// 해당 교수의 이름 불러오기
		String Username = sg.getstName(id);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		// 메뉴안내 라벨
		JButton LectureBtn = new JButton("졸업 시스템");
		LectureBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(LectureBtn);

		JButton Menu1 = new JButton("졸업 관리");
		Menu1.setBounds(4, 63, 152, 18);
		Menu1.setBorderPainted(false);
		Menu1.setFocusPainted(false);
		Menu1.setContentAreaFilled(false);
		Menu1.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(Menu1);

		JButton graduation = new JButton("학점 취득 현황");
		graduation.setBounds(10, 83, 152, 18);
		graduation.setBorderPainted(false);
		graduation.setFocusPainted(false);
		graduation.setContentAreaFilled(false);
		graduation.setHorizontalAlignment(SwingConstants.LEFT);
		graduation.setEnabled(false);
		menuPanel.add(graduation);

		JButton graduationcheck = new JButton("졸업여부 조회");
		graduationcheck.setBounds(10, 103, 152, 18);
		graduationcheck.setBorderPainted(false);
		graduationcheck.setFocusPainted(false);
		graduationcheck.setHorizontalAlignment(SwingConstants.LEFT);
		graduationcheck.setContentAreaFilled(false);
		menuPanel.add(graduationcheck);

		JButton homeBtn = new JButton("홈 화면");
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				S_Home phome;
				try {
					phome = new S_Home(id);
					phome.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		homeBtn.setBounds(45, 500, 105, 27);
		menuPanel.add(homeBtn);

		JPanel Main = new JPanel();
		Main.setBackground(Color.WHITE);
		Main.setBounds(211, 5, 970, 553);
		Main.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		contentPane.add(Main);
		Main.setLayout(null);

		JLabel Title = new JLabel("학점 취득 현황");
		Title.setFont(new Font("한컴산뜻돋움", Font.BOLD, 26));
		Title.setBounds(12, 10, 200, 44);
		Main.add(Title);

		JLabel CategoryScore = new JLabel("이수 구분별 학점 현황");
		CategoryScore.setFont(new Font("한컴산뜻돋움", Font.BOLD, 14));
		CategoryScore.setBounds(415, 86, 180, 30);
		Main.add(CategoryScore);

		// 학적 정보 입력
		String colNames[] = { "학년도", "학기", "신청 학점", "이수 학점", "해당 학기 평점 평균" };
		String rows[][] = {};
		DefaultTableModel model = new DefaultTableModel(rows, colNames);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 138, 946, 147);
		Main.add(scrollPane);

		// 이수 구분별 학점 취득 현황
		table = new JTable();
		table.setFont(new Font("굴림", Font.PLAIN, 12));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(
				new DefaultTableModel(
						new Object[][] { { "총학점", "130", null, null }, { "교양 필수", "10", null, null },
								{ "교양 선택", "27", null, null }, { "전공 필수", "14", null, null },
								{ "전공 선택", "-", null, null }, { "전공 계", "69", null, null }, },
						new String[] { "", "이수 기준 학점", "취득 학점", "잔여 학점" }));
		scrollPane.setViewportView(table);

		JLabel TotalScore_Title = new JLabel("전체 누계 학점");
		TotalScore_Title.setFont(new Font("한컴산뜻돋움", Font.BOLD, 14));
		TotalScore_Title.setBounds(442, 354, 95, 44);
		Main.add(TotalScore_Title);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 419, 946, 44);
		Main.add(scrollPane_1);

		String colNames1[] = { "전공 총 학점", "총 이수 학점", "총 평점" };
		String rows1[][] = {{"", "", ""}};
		DefaultTableModel model1 = new DefaultTableModel(rows1, colNames1);
		table_1 = new JTable(model1);
		table_1.setFont(new Font("굴림", Font.PLAIN, 12));
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_1.setViewportView(table_1);

		// 첫번째 테이블 데이터 설정하기
		// 필요한 데이터들
		// 총 이수 학점
		int TotalCredit = sg.getTotalCredit(id, "총학점");
		// 교양 필수
		int credit1 = sg.getTotalCredit(id, "교양필수");
		// 교양 선택
		int credit2 = sg.getTotalCredit(id, "교양선택");
		// 전공 필수
		int credit3 = sg.getTotalCredit(id, "전공필수");
		// 전공 선택
		int credit4 = sg.getTotalCredit(id, "전공선택");
		// 전공 계(전공 필수 + 전공 선택)
		int MajorCredit = credit3 + credit4;

		//테이블에 넣기
		table.setValueAt(TotalCredit, 0, 2);
		table.setValueAt(credit1, 1, 2);
		table.setValueAt(credit2, 2, 2);
		table.setValueAt(credit3, 3, 2);
		table.setValueAt(credit4, 4, 2);
		table.setValueAt(MajorCredit, 5, 2);
		
		int Totalremained = 130 - TotalCredit;
		int remained1 = 10 - credit1;
		int remained2 = 27 - credit2;
		int remained3 = 14 - credit3;
		int MajorRemained = 69-MajorCredit;
		
		table.setValueAt(Totalremained, 0, 3);
		table.setValueAt(remained1, 1, 3);
		table.setValueAt(remained2, 2, 3);
		table.setValueAt(remained3, 3, 3);
		table.setValueAt("-", 4, 3);
		table.setValueAt(MajorRemained, 5, 3);
		
		//두번째 테이블 띄우기
		//전공 총학점과 총 평점을 불러오기
		//총점 = 성적
		float Totalgrade = sg.getTotalGrade(id, "총점");
		
		table_1.setValueAt(MajorCredit, 0, 0);
		table_1.setValueAt(TotalCredit, 0, 1);
		table_1.setValueAt(Totalgrade, 0, 2);
		
		// 졸업 가능 여부 조회로 이동하는 액션 리스너
		graduationcheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				S_Graduation_Check nextpage;
				try {
					nextpage = new S_Graduation_Check(id);
					nextpage.setVisible(true);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

}
