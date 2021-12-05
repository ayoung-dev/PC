package school;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JMenuItem;

public class S_Home extends JFrame {

	private JPanel contentPane;
	private JTextField Phone;
	private JTextField Email;
	private JTable table;
	private JTable Home_Subject;

	public static String id;
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("홈 화면 가능");
					Class.forName("org.mariadb.jdbc.Driver");
					// sql 접속하기 위한 아이디와 비밀번호 입력
					url = "jdbc:mariadb://localhost:3306/school";

					con = DriverManager.getConnection(url, "root", "1234");

					S_Management mg = new S_Management(url, con);
					P_Home frame = new P_Home(id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public S_Home(String id) throws SQLException, ClassNotFoundException {
		setTitle("학사관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);

		// 교수 함수에 연결
		S_Management mg = new S_Management(url, con);
		Functions func = new Functions(url, con);

		// 해당 교수의 이름 불러오기
		Student s = mg.getStudent(id);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		// TitledBorder tb = new TitledBorder(new LineBorder(Color.BLACK));
		contentPane.setBorder(new LineBorder(Color.BLACK)); //
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setBorder(new LineBorder(Color.BLACK)); //
		menuPanel.setLayout(null);

		JPanel Main = new JPanel();
		Main.setBackground(Color.WHITE);
		Main.setForeground(Color.WHITE);
		Main.setBounds(12, 5, 1169, 553);
		contentPane.add(Main);
		Main.setLayout(null);

		JButton lectureBtn = new JButton("수강 시스템");
		lectureBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(lectureBtn);

		JButton gradeBtn = new JButton("성적 시스템");
		gradeBtn.setBounds(0, 50, 205, 50);
		menuPanel.add(gradeBtn);

		JButton infoBtn = new JButton("학적 시스템");
		infoBtn.setBounds(0, 100, 205, 50);
		menuPanel.add(infoBtn);

		JButton graduateBtn = new JButton("졸업 시스템");
		graduateBtn.setBounds(0, 150, 205, 50);
		menuPanel.add(graduateBtn);

		// ******** 수강 시스템 ********
		lectureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel menuPanel2 = new JPanel();
				menuPanel2.setBounds(0, 0, 205, 563);
				menuPanel.add(menuPanel2);
				menuPanel2.setLayout(null);

				JButton lectureBtn = new JButton("수강 시스템");
				lectureBtn.setBounds(0, 0, 205, 50);
				menuPanel2.add(lectureBtn);

				// 메뉴 선택 화면 이동
				JButton homeBtn = new JButton("뒤로가기");
				menuPanel2.add(homeBtn);
				homeBtn.setBounds(45, 500, 105, 27);
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

				// 버튼 숨김 함수 모음 - 투명화
				lectureBtn.setVisible(false);
				gradeBtn.setVisible(false);
				infoBtn.setVisible(false);
				graduateBtn.setVisible(false);

				JButton showAllClassLabel = new JButton("개설과목조회");
				showAllClassLabel.setBounds(10, 63, 152, 18);
				menuPanel2.add(showAllClassLabel);
				showAllClassLabel.setBorderPainted(false);
				showAllClassLabel.setFocusPainted(false);
				showAllClassLabel.setContentAreaFilled(false);
				showAllClassLabel.setHorizontalAlignment(SwingConstants.LEFT);
				menuPanel2.add(showAllClassLabel);
				showAllClassLabel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						S_ShowAllClasses sac;
						try {
							sac = new S_ShowAllClasses(id);
							sac.setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});

				JButton BasketLabel = new JButton("장바구니 입력");
				BasketLabel.setBounds(10, 94, 152, 18);
				menuPanel2.add(BasketLabel);
				BasketLabel.setBorderPainted(false);
				BasketLabel.setFocusPainted(false);
				BasketLabel.setContentAreaFilled(false);
				BasketLabel.setHorizontalAlignment(SwingConstants.LEFT);
				menuPanel2.add(BasketLabel);
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
				menuPanel2.add(applicationLabel);
				applicationLabel.setBorderPainted(false);
				applicationLabel.setFocusPainted(false);
				applicationLabel.setContentAreaFilled(false);
				applicationLabel.setHorizontalAlignment(SwingConstants.LEFT);
				menuPanel2.add(applicationLabel);
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
				menuPanel2.add(stuClassLabel);
				stuClassLabel.setBorderPainted(false);
				stuClassLabel.setFocusPainted(false);
				stuClassLabel.setContentAreaFilled(false);
				stuClassLabel.setHorizontalAlignment(SwingConstants.LEFT);
				menuPanel2.add(stuClassLabel);
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

			}
		});

		// ******** 성적 시스템 ********
		gradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel menuPanel2 = new JPanel();
				menuPanel2.setBounds(0, 0, 205, 563);
				menuPanel.add(menuPanel2);
				menuPanel2.setLayout(null);

				JButton gradeBtn2 = new JButton("성적 시스템");
				gradeBtn2.setBounds(0, 0, 205, 50);
				menuPanel2.add(gradeBtn2);

				// 버튼 숨김 함수 모음 - 투명화
				lectureBtn.setVisible(false);
				gradeBtn.setVisible(false);
				infoBtn.setVisible(false);
				graduateBtn.setVisible(false);

				JButton recentGradeLabel = new JButton("금학기성적조회");
				recentGradeLabel.setBounds(10, 63, 152, 18);
				menuPanel2.add(recentGradeLabel);
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
							e1.printStackTrace();
						}
					}
				});

				JButton allGradeLabel = new JButton("전체성적조회");
				allGradeLabel.setBounds(10, 94, 152, 18);
				menuPanel2.add(allGradeLabel);
				allGradeLabel.setBorderPainted(false);
				allGradeLabel.setFocusPainted(false);
				allGradeLabel.setContentAreaFilled(false);
				allGradeLabel.setHorizontalAlignment(SwingConstants.LEFT);
				allGradeLabel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						S_ShowAllGrade sag;
						try {
							sag = new S_ShowAllGrade(id);
							sag.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});

				// 메뉴 선택 화면 이동
				JButton homeBtn = new JButton("뒤로가기");
				menuPanel2.add(homeBtn);
				homeBtn.setBounds(45, 500, 105, 27);
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
			}
		});

		// ******** 학적 시스템 ********
		infoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel menuPanel2 = new JPanel();
				menuPanel2.setBounds(0, 0, 205, 563);
				menuPanel.add(menuPanel2);
				menuPanel2.setLayout(null);

				JButton infoBtn2 = new JButton("학적 시스템");
				infoBtn2.setBounds(0, 0, 205, 50);
				menuPanel2.add(infoBtn2);

				// 버튼 숨김 함수 모음 - 투명화
				lectureBtn.setVisible(false);
				gradeBtn.setVisible(false);
				infoBtn.setVisible(false);
				graduateBtn.setVisible(false);

				// 1.학적부 관리
				JButton sManage = new JButton("학적부 관리");
				sManage.setBounds(10, 63, 152, 18);
				menuPanel2.add(sManage);
				sManage.setBorderPainted(false);
				sManage.setFocusPainted(false);
				sManage.setContentAreaFilled(false);
				sManage.setHorizontalAlignment(SwingConstants.LEFT);

				// 밑줄 함수
				Font font = sManage.getFont();
				sManage.setForeground(Color.RED);
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				sManage.setFont(font.deriveFont(attributes));

				sManage.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 여기에 화면전환 함수 코드를 쓰세요.
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

				// 학생정보조회
				JButton showSInfo = new JButton("학생정보조회");
				showSInfo.setBounds(10, 83, 152, 18);
				menuPanel2.add(showSInfo);
				showSInfo.setBorderPainted(false);
				showSInfo.setFocusPainted(false);
				showSInfo.setContentAreaFilled(false);
				showSInfo.setHorizontalAlignment(SwingConstants.LEFT);
				showSInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 여기에 화면전환 함수 코드를 쓰세요.
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

				// 2.학적변동조회
				JButton showRegistChange = new JButton("학적변동조회");
				showRegistChange.setBounds(10, 113, 152, 18);
				menuPanel2.add(showRegistChange);
				showRegistChange.setBorderPainted(false);
				showRegistChange.setFocusPainted(false);
				showRegistChange.setContentAreaFilled(false);
				showRegistChange.setHorizontalAlignment(SwingConstants.LEFT);
				showRegistChange.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		  				S_Huhak info;
						try {
							info = new S_Huhak(id);
		    				info.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	  				dispose();
						}
					});

				showRegistChange.setForeground(Color.RED);
				showRegistChange.setFont(font.deriveFont(attributes));

				// 휴학신청
				JButton huHakGo = new JButton("휴학신청");
				huHakGo.setBounds(10, 133, 152, 18);
				menuPanel2.add(huHakGo);
				huHakGo.setBorderPainted(false);
				huHakGo.setFocusPainted(false);
				huHakGo.setContentAreaFilled(false);
				huHakGo.setHorizontalAlignment(SwingConstants.LEFT);
				huHakGo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		  				S_Huhak nextpage;
						try {
							nextpage = new S_Huhak(id);
			  				nextpage.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	  				dispose();
						}
					});	

				// 복학신청
				JButton bokHakGo = new JButton("복학신청");
				bokHakGo.setBounds(10, 153, 152, 18);
				menuPanel2.add(bokHakGo);
				bokHakGo.setBorderPainted(false);
				bokHakGo.setFocusPainted(false);
				bokHakGo.setContentAreaFilled(false);
				bokHakGo.setHorizontalAlignment(SwingConstants.LEFT);
				bokHakGo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		  				S_Bokhak nextpage;
						try {
							nextpage = new S_Bokhak(id);
			  				nextpage.setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	  				dispose();
						}
					});	

				// 자퇴신청
				JButton zaTaeGo = new JButton("자퇴신청");
				zaTaeGo.setBounds(10, 173, 152, 18);
				menuPanel2.add(zaTaeGo);
				zaTaeGo.setBorderPainted(false);
				zaTaeGo.setFocusPainted(false);
				zaTaeGo.setContentAreaFilled(false);
				zaTaeGo.setHorizontalAlignment(SwingConstants.LEFT);
				zaTaeGo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		  				S_Jatuae nextpage;
						try {
							nextpage = new S_Jatuae(id);
			  				nextpage.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	  				dispose();
						}
					});	

				// 3.학적등록조회
				JButton showMajorChange = new JButton("학적등록조회");
				showMajorChange.setBounds(10, 203, 152, 18);
				menuPanel2.add(showMajorChange);
				showMajorChange.setBorderPainted(false);
				showMajorChange.setFocusPainted(false);
				showMajorChange.setContentAreaFilled(false);
				showMajorChange.setHorizontalAlignment(SwingConstants.LEFT);
				showMajorChange.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						S_BuBokSu nextpage;
						try {
							nextpage = new S_BuBokSu(id);
							nextpage.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
						}
					});	

				showMajorChange.setForeground(Color.RED);
				showMajorChange.setFont(font.deriveFont(attributes));

				// 부/복수신청
				JButton doubleMajorGo = new JButton("부/복수신청");
				doubleMajorGo.setBounds(10, 223, 152, 18);
				menuPanel2.add(doubleMajorGo);
				doubleMajorGo.setBorderPainted(false);
				doubleMajorGo.setFocusPainted(false);
				doubleMajorGo.setContentAreaFilled(false);
				doubleMajorGo.setHorizontalAlignment(SwingConstants.LEFT);
				doubleMajorGo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						S_BuBokSu nextpage;
						try {
							nextpage = new S_BuBokSu(id);
							nextpage.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
						}
					});	

				// 전과신청
				JButton changeMajorGo = new JButton("전과신청");
				changeMajorGo.setBounds(10, 243, 152, 18);
				menuPanel2.add(changeMajorGo);
				changeMajorGo.setBorderPainted(false);
				changeMajorGo.setFocusPainted(false);
				changeMajorGo.setContentAreaFilled(false);
				changeMajorGo.setHorizontalAlignment(SwingConstants.LEFT);
				changeMajorGo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						S_JunKua nextpage;
						try {
							nextpage = new S_JunKua(id);
							nextpage.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
						}
					});

				// 메뉴 선택 화면 이동
				JButton homeBtn = new JButton("뒤로가기");
				menuPanel2.add(homeBtn);
				homeBtn.setBounds(45, 500, 105, 27);
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
			}
		});

		// ******** 졸업 시스템 ********
		graduateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel menuPanel2 = new JPanel();
				menuPanel2.setBounds(0, 0, 205, 563);
				menuPanel.add(menuPanel2);
				menuPanel2.setLayout(null);

				JButton graduateBtn2 = new JButton("졸업 시스템");
				graduateBtn2.setBounds(0, 0, 205, 50);
				menuPanel2.add(graduateBtn2);

				// 버튼 숨김 함수 모음 - 투명화
				lectureBtn.setVisible(false);
				gradeBtn.setVisible(false);
				infoBtn.setVisible(false);
				graduateBtn.setVisible(false);

				JButton gradauateM = new JButton("학점취득현황");
				gradauateM.setBounds(10, 63, 152, 18);
				menuPanel2.add(gradauateM);
				gradauateM.setBorderPainted(false);
				gradauateM.setFocusPainted(false);
				gradauateM.setContentAreaFilled(false);
				gradauateM.setHorizontalAlignment(SwingConstants.LEFT);
				gradauateM.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						S_Graduation frame;
						try {
							frame = new S_Graduation(id);
							frame.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});

				JButton ifGraduate = new JButton("졸업여부조회");
				ifGraduate.setBounds(10, 94, 152, 18);
				menuPanel2.add(ifGraduate);
				ifGraduate.setBorderPainted(false);
				ifGraduate.setFocusPainted(false);
				ifGraduate.setContentAreaFilled(false);
				ifGraduate.setHorizontalAlignment(SwingConstants.LEFT);
				ifGraduate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						S_Graduation_Check shome;
						try {
							shome = new S_Graduation_Check(id);
							shome.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});

				// 메뉴 선택 화면 이동
				JButton homeBtn = new JButton("뒤로가기");
				menuPanel2.add(homeBtn);
				homeBtn.setBounds(45, 500, 105, 27);
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
			}
		});

		JPanel User = new JPanel();
		User.setBackground(Color.WHITE);
		User.setForeground(Color.WHITE);
		User.setBounds(218, 73, 378, 145);
		User.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		Main.add(User);
		User.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		panel.setBounds(12, 10, 141, 125);
		User.add(panel);

		JLabel UserPhoto = new JLabel("UserPhoto");
		panel.add(UserPhoto);

		JLabel StuNum = new JLabel("학번 : ");
		StuNum.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		StuNum.setBounds(165, 38, 125, 15);
		User.add(StuNum);

		JLabel Name = new JLabel("이름 : ");
		Name.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		Name.setBounds(165, 63, 125, 15);
		User.add(Name);

		JLabel College = new JLabel("소속 단대 : ");
		College.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		College.setBounds(165, 88, 200, 15);
		User.add(College);

		JLabel Department = new JLabel("소속 학과 : ");
		Department.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		Department.setBounds(165, 113, 200, 15);
		User.add(Department);

		JLabel Home_Profile = new JLabel("프로필");
		Home_Profile.setFont(new Font("한컴산뜻돋움", Font.BOLD, 23));
		Home_Profile.setBounds(218, 33, 142, 27);
		Main.add(Home_Profile);

		// 수강 과목 확인
		String colNames[] = { "과목명", "학점", "강의 시간", "담당교수" };
		String rows[][] = {};
		DefaultTableModel model = new DefaultTableModel(rows, colNames);

		Home_Subject = new JTable(model);
		Home_Subject.setBackground(Color.WHITE);
		JScrollPane scrollPane_sub = new JScrollPane(Home_Subject);
		scrollPane_sub.setBounds(221, 273, 924, 222);
		Main.add(scrollPane_sub);

		JLabel StudentHome = new JLabel("수업 조회");
		StudentHome.setFont(new Font("한컴산뜻돋움", Font.BOLD, 23));
		StudentHome.setBounds(218, 233, 189, 27);
		Main.add(StudentHome);

		JLabel Home_Graduation = new JLabel("졸업 잔여 학점");
		Home_Graduation.setFont(new Font("한컴산뜻돋움", Font.BOLD, 23));
		Home_Graduation.setBounds(632, 36, 208, 27);
		Main.add(Home_Graduation);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(632, 73, 513, 145);
		Main.add(scrollPane);

		// 프로필 정보 띄우기
		StuNum.setText("학번: "+s.getstNum());
		Name.setText("이름: "+s.getstName());
		College.setText("소속단대: "+s.getCollege());
		Department.setText("소속학과: "+s.getfMajor());

		// 이수 구분별 학점 취득 현황 띄우기
		table = new JTable();
		table.setFont(new Font("굴림", Font.PLAIN, 12));
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setModel(
				new DefaultTableModel(
						new Object[][] { { "총학점", "130", null, null }, { "교양 필수", "10", null, null },
								{ "교양 선택", "27", null, null }, { "전공 필수", "14", null, null },
								{ "전공 선택", "-", null, null }, { "전공 계", "69", null, null }, },
						new String[] { "", "이수 기준 학점", "취득 학점", "잔여 학점" }));
		scrollPane.setViewportView(table);

		// 첫번째 테이블 데이터 설정하기
		// 필요한 데이터들
		// 총 이수 학점
		int TotalCredit = mg.getTotalCredit(id, "총학점");
		// 교양 필수
		int credit1 = mg.getTotalCredit(id, "교양필수");
		// 교양 선택
		int credit2 = mg.getTotalCredit(id, "교양선택");
		// 전공 필수
		int credit3 = mg.getTotalCredit(id, "전공필수");
		// 전공 선택
		int credit4 = mg.getTotalCredit(id, "전공선택");
		// 전공 계(전공 필수 + 전공 선택)
		int MajorCredit = credit3 + credit4;

		// 테이블에 넣기
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
		int MajorRemained = 69 - MajorCredit;

		table.setValueAt(Totalremained, 0, 3);
		table.setValueAt(remained1, 1, 3);
		table.setValueAt(remained2, 2, 3);
		table.setValueAt(remained3, 3, 3);
		table.setValueAt("-", 4, 3);
		table.setValueAt(MajorRemained, 5, 3);

		// 금학기 수강 내역 띄우기
		ResultSet rs = null;
		rs = mg.getStClasses(id, "2021-1");
		if (rs.getRow() > -1) {
			while (rs.next()) {
				// 학수번호만 불러와서
				String leNum = rs.getString(2);
				// 해당 학수번호를 가진 수업 객체 들고 오기
				Lecture lec = mg.getLe(leNum);
				// GUI에 띄우기
				String[] row = { lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(), lec.getprName()};

				model.addRow(row);
			}
		}

	}
}