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
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JMenuItem;

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

public class P_Home extends JFrame {

	private JPanel contentPane;
	private JTextField Phone;
	private JTextField Email;
	private JTable table;
	

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
					S_Management sg = new S_Management(url, con);
					P_Home frame= new P_Home(id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param string 
	 * @throws Exception 
	 */
	public P_Home(String id) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);

		
	      contentPane = new JPanel();
	      contentPane.setBackground(Color.WHITE);
	      contentPane.setBorder(new LineBorder(Color.BLACK)); 
	      setContentPane(contentPane);
	      contentPane.setLayout(null);
		
			// 교수,학생 기능 함수 클래스에 연결
			P_Management pm = new P_Management(url, con);
			S_Management sm = new S_Management(url, con);
			Functions func = new Functions(url, con);
							
			// 해당 교수의 이름 불러오기
			Professor p = pm.getProfessor(id);
			String Username = pm.checkNamePr(id);
			
		 JPanel menuPanel = new JPanel();
	     menuPanel.setBounds(0, 0, 205, 563);
	     contentPane.add(menuPanel);
	     menuPanel.setBorder(new LineBorder(Color.BLACK)); //
	     menuPanel.setLayout(null);
		
	     JButton lectureBtn = new JButton("수업 시스템");
	     lectureBtn.setBounds(0, 0, 205,50);
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
	    
	    // ******** 수업 시스템 ********
	      lectureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				JPanel menuPanel2 = new JPanel();
				menuPanel2.setBounds(0, 0, 205, 563);
				menuPanel.add(menuPanel2);
				menuPanel2.setLayout(null);
				
				JButton lectureBtn = new JButton("수업 시스템");
				lectureBtn.setBounds(0, 0, 205, 50);
				menuPanel2.add(lectureBtn);
				
				
				// 메뉴 선택 화면 이동
				JButton homeBtn = new JButton("뒤로가기");
				menuPanel2.add(homeBtn);
				homeBtn.setBounds(45, 500, 105, 27);
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
				
				JButton BasketLabel = new JButton("개설과목입력");
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
						P_AddLecture pal;
						try {
							pal = new P_AddLecture(id);
							pal.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				
				
				JButton applicationLabel = new JButton("개설과목내역");
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
						P_ShowProLecture pshpl;
						try {
							pshpl = new P_ShowProLecture(id);
							pshpl.setVisible(true);
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
				
				
				JButton recentGradeLabel = new JButton("성적관리");
				recentGradeLabel.setBounds(10, 63, 152, 18);
				menuPanel2.add(recentGradeLabel);
				recentGradeLabel.setBorderPainted(false);
				recentGradeLabel.setFocusPainted(false);
				recentGradeLabel.setContentAreaFilled(false);
				recentGradeLabel.setHorizontalAlignment(SwingConstants.LEFT);
				recentGradeLabel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						P_Grade pgrade;
						try {
							pgrade = new P_Grade(id);
							pgrade.setVisible(true);
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
				sManage.setBounds(4, 63, 152, 18);
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
		  				P_Info info;
						try {
							info = new P_Info(id);
		    				info.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	  				dispose();
					}
				});
				
				// 학생정보조회
				JButton showSInfo = new JButton("교수정보 조회");
				showSInfo.setBounds(10, 83, 152, 18);
				menuPanel2.add(showSInfo);
				showSInfo.setBorderPainted(false);
				showSInfo.setFocusPainted(false);
				showSInfo.setContentAreaFilled(false);
				showSInfo.setHorizontalAlignment(SwingConstants.LEFT);
				showSInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 여기에 화면전환 함수 코드를 쓰세요.
		  				P_Info info;
						try {
							info = new P_Info(id);
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
				showRegistChange.setBounds(4, 113, 152, 18);
				menuPanel2.add(showRegistChange);
				showRegistChange.setBorderPainted(false);
				showRegistChange.setFocusPainted(false);
				showRegistChange.setContentAreaFilled(false);
				showRegistChange.setHorizontalAlignment(SwingConstants.LEFT);
				showRegistChange.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	  				P_Huhak info;
					try {
						info = new P_Huhak(id);
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
				JButton huHakGo = new JButton("휴학신청 조회");
				huHakGo.setBounds(10, 133, 152, 18);
				menuPanel2.add(huHakGo);
				huHakGo.setBorderPainted(false);
				huHakGo.setFocusPainted(false);
				huHakGo.setContentAreaFilled(false);
				huHakGo.setHorizontalAlignment(SwingConstants.LEFT);
				huHakGo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	  				P_Huhak nextpage;
					try {
						nextpage = new P_Huhak(id);
		  				nextpage.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  				dispose();
					}
				});	
				
				// 복학신청
				JButton bokHakGo = new JButton("복학신청 조회");
				bokHakGo.setBounds(10, 153, 152, 18);
				menuPanel2.add(bokHakGo);
				bokHakGo.setBorderPainted(false);
				bokHakGo.setFocusPainted(false);
				bokHakGo.setContentAreaFilled(false);
				bokHakGo.setHorizontalAlignment(SwingConstants.LEFT);
				bokHakGo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	  				P_Bokhak nextpage;
					try {
						nextpage = new P_Bokhak(id);
		  				nextpage.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  				dispose();
					}
				});	
				
				// 자퇴신청
				JButton zaTaeGo = new JButton("자퇴신청 조회");
				zaTaeGo.setBounds(10, 173, 152, 18);
				menuPanel2.add(zaTaeGo);
				zaTaeGo.setBorderPainted(false);
				zaTaeGo.setFocusPainted(false);
				zaTaeGo.setContentAreaFilled(false);
				zaTaeGo.setHorizontalAlignment(SwingConstants.LEFT);
				zaTaeGo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	  				P_Jatuae nextpage;
					try {
						nextpage = new P_Jatuae(id);
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
				showMajorChange.setBounds(4, 203, 152, 18);
				menuPanel2.add(showMajorChange);
				showMajorChange.setBorderPainted(false);
				showMajorChange.setFocusPainted(false);
				showMajorChange.setContentAreaFilled(false);
				showMajorChange.setHorizontalAlignment(SwingConstants.LEFT);
				showMajorChange.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					P_BuBoksu nextpage;
					try {
						nextpage = new P_BuBoksu(id);
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
				JButton doubleMajorGo = new JButton("부/복수신청 조회");
				doubleMajorGo.setBounds(10, 223, 152, 18);
				menuPanel2.add(doubleMajorGo);
				doubleMajorGo.setBorderPainted(false);
				doubleMajorGo.setFocusPainted(false);
				doubleMajorGo.setContentAreaFilled(false);
				doubleMajorGo.setHorizontalAlignment(SwingConstants.LEFT);
				doubleMajorGo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					P_BuBoksu nextpage;
					try {
						nextpage = new P_BuBoksu(id);
						nextpage.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
					}
				});	
				
				// 전과신청
				JButton changeMajorGo = new JButton("전과신청 조회");
				changeMajorGo.setBounds(10, 243, 152, 18);
				menuPanel2.add(changeMajorGo);
				changeMajorGo.setBorderPainted(false);
				changeMajorGo.setFocusPainted(false);
				changeMajorGo.setContentAreaFilled(false);
				changeMajorGo.setHorizontalAlignment(SwingConstants.LEFT);
				changeMajorGo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					P_JunKua nextpage;
					try {
						nextpage = new P_JunKua(id);
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
				
				
				JButton gradauateM = new JButton("학점 취득 현황 조회");
				gradauateM.setBounds(10, 63, 152, 18);
				menuPanel2.add(gradauateM);
				gradauateM.setBorderPainted(false);
				gradauateM.setFocusPainted(false);
				gradauateM.setContentAreaFilled(false);
				gradauateM.setHorizontalAlignment(SwingConstants.LEFT);
				gradauateM.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						P_Graduation nextpage;
						try {
							nextpage = new P_Graduation(id);
							nextpage.setVisible(true);
							dispose();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
				
				JButton graduationcheck = new JButton("졸업 여부 조회");
				graduationcheck.setBounds(10, 85, 152, 18);
				menuPanel2.add(graduationcheck);
				graduationcheck.setBorderPainted(false);
				graduationcheck.setFocusPainted(false);
				graduationcheck.setContentAreaFilled(false);
				graduationcheck.setHorizontalAlignment(SwingConstants.LEFT);
				graduationcheck.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						P_GraduationCheck nextpage;
						try {
							nextpage = new P_GraduationCheck(id);
							nextpage.setVisible(true);
							dispose();
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
			}
		});
	      
	    
	    
		
		JPanel Main = new JPanel();
		Main.setBackground(Color.WHITE);
		Main.setForeground(Color.WHITE);
		Main.setBounds(12, 5, 1169, 553);
		contentPane.add(Main);
		Main.setLayout(null);
		
		JPanel User = new JPanel();
		User.setBackground(Color.WHITE);
		User.setForeground(Color.WHITE);
		User.setBounds(216, 53, 403, 145);
		User.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		Main.add(User);
		User.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		panel.setBounds(12, 10, 141, 125);
		User.add(panel);
		
		JLabel UserPhoto = new JLabel("UserPhoto");
		panel.add(UserPhoto);
		
		JLabel proNum = new JLabel("교번 : ");
		proNum.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		proNum.setBounds(165, 38, 125, 15);
		User.add(proNum);
	
		
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
        Home_Profile.setBounds(216, 13, 142, 27);
        Main.add(Home_Profile);
		
        JLabel ProfSubject = new JLabel("개설 과목 조회");
        ProfSubject.setFont(new Font("한컴산뜻돋움", Font.BOLD, 23));
        ProfSubject.setBounds(216, 229, 172, 27);
        Main.add(ProfSubject);
        

        String colNames[] = {"과목명", "학수 번호","수강 인원", "제한 인원"};
        String rows[][] = {};
        DefaultTableModel model = new DefaultTableModel(rows, colNames);
    
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(216, 273, 926, 241);
        Main.add(scrollPane);
		

        //프로필 부분에 정보 띄우기
        //교수 교번을 이용해서 정보 검색하기
		// 프로필 정보 띄우기
		proNum.setText("교번: "+p.getprNum());
		Name.setText("이름: "+p.getprName());
		College.setText("소속단대: "+p.getCollege());
		Department.setText("소속학과: "+p.getMajor());


        
        //교수 수업 내역 띄우기
        if (table.getRowCount() > 0) {
			model.setNumRows(0);
		}
		try {
			ResultSet rs = pm.searchLecture("", "", Username,"");

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
				String[] row1 = { lec.getleName(), lec.getleNum(), Integer.toString(lec.getAttMem()), Integer.toString(lec.getLimMem())};

				model.addRow(row1);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
