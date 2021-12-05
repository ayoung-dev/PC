package school;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class P_Bokhak extends JFrame {

	private JPanel contentPane;
	private JTextField Phone;
	private JTextField Email;
	
	//public int SubNum = 0;
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

					P_Management mg = new P_Management(url, con);
					S_Management sg = new S_Management(url, con);
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

	public P_Bokhak(String id) throws Exception {
		
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

		// 교수,학생 기능 함수 클래스에 연결
		P_Management pm = new P_Management(url, con);
		S_Management sm = new S_Management(url, con);
		Functions func = new Functions(url, con);
						
		// 해당 교수의 이름 불러오기
		Professor p = pm.getProfessor(id);
		String Username = pm.checkNamePr(id);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		
		//메뉴안내 라벨
		JButton LectureBtn = new JButton("학적 시스템");
		LectureBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(LectureBtn);
		
		JButton Menu1 = new JButton("학적부 관리");
		Menu1.setBounds(4, 63, 152, 18);
		Menu1.setBorderPainted(false);
		Menu1.setFocusPainted(false);
		Menu1.setContentAreaFilled(false);
		Menu1.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(Menu1);
		
		// 밑줄 함수
		 Font font = Menu1.getFont();
		 Menu1.setForeground(Color.RED);
		 Map attributes = font.getAttributes();
		 attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		 Menu1.setFont(font.deriveFont(attributes));
		
		JButton info = new JButton("교수 정보 조회");
		info.setBounds(10, 83, 152, 18);
		info.setBorderPainted(false);
		info.setFocusPainted(false);
		info.setHorizontalAlignment(SwingConstants.LEFT);
		info.setContentAreaFilled(false);
		menuPanel.add(info);
		
		JButton Menu2 = new JButton("학적 변동 조회");
		Menu2.setBounds(4, 113, 152, 18);
		Menu2.setBorderPainted(false);
		Menu2.setFocusPainted(false);
		Menu2.setHorizontalAlignment(SwingConstants.LEFT);
		Menu2.setContentAreaFilled(false);
		menuPanel.add(Menu2);
		
		Menu2.setForeground(Color.RED);
		Menu2.setFont(font.deriveFont(attributes));
		
		
		JButton Huhak = new JButton("휴학 신청 조회");
		Huhak.setBounds(10, 133, 152, 18);
		Huhak.setBorderPainted(false);
		Huhak.setFocusPainted(false);
		Huhak.setHorizontalAlignment(SwingConstants.LEFT);
		Huhak.setContentAreaFilled(false);
		menuPanel.add(Huhak);		
		
		JButton Bokhak = new JButton("복학 신청 조회");
		Bokhak.setBounds(10, 153, 152, 18);
		Bokhak.setBorderPainted(false);
		Bokhak.setFocusPainted(false);
		Bokhak.setHorizontalAlignment(SwingConstants.LEFT);
		Bokhak.setContentAreaFilled(false);
		Bokhak.setEnabled(false);
		menuPanel.add(Bokhak);		
		
		JButton Jatuae = new JButton("자퇴 신청 조회");
		Jatuae.setBounds(10, 173, 152, 18);
		Jatuae.setBorderPainted(false);
		Jatuae.setFocusPainted(false);
		Jatuae.setHorizontalAlignment(SwingConstants.LEFT);
		Jatuae.setContentAreaFilled(false);
		menuPanel.add(Jatuae);
		
		
		JButton Menu3 = new JButton("학적 등록 조회");
		Menu3.setBounds(4, 203, 152, 18);
		Menu3.setBorderPainted(false);
		Menu3.setFocusPainted(false);
		Menu3.setHorizontalAlignment(SwingConstants.LEFT);
		Menu3.setContentAreaFilled(false);
		menuPanel.add(Menu3);
		
		Menu3.setForeground(Color.RED);
		Menu3.setFont(font.deriveFont(attributes));
		
		JButton BuBoksu = new JButton("부/복수 신청 조회");
		BuBoksu.setBounds(10, 223, 152, 18);
		BuBoksu.setBorderPainted(false);
		BuBoksu.setFocusPainted(false);
		BuBoksu.setHorizontalAlignment(SwingConstants.LEFT);
		BuBoksu.setContentAreaFilled(false);
		menuPanel.add(BuBoksu);		
		
		JButton Junkua = new JButton("전과 신청 조회");
		Junkua.setBounds(10, 243, 152, 18);
		Junkua.setBorderPainted(false);
		Junkua.setFocusPainted(false);
		Junkua.setHorizontalAlignment(SwingConstants.LEFT);
		Junkua.setContentAreaFilled(false);
		menuPanel.add(Junkua);		
		
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
				
		JPanel Main = new JPanel();
		Main.setBackground(Color.WHITE);
		Main.setForeground(Color.WHITE);
		Main.setBounds(211, 5, 970, 553);
		contentPane.add(Main);
		Main.setLayout(null);
		
		JPanel User = new JPanel();
		User.setBackground(Color.WHITE);
		User.setForeground(Color.WHITE);
		User.setBounds(12, 10, 946, 170);
		User.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		Main.add(User);
		User.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		panel.setBounds(12, 10, 141, 150);
		User.add(panel);
		
		JLabel UserPhoto = new JLabel("UserPhoto");
		panel.add(UserPhoto);
		
		JLabel StuNum = new JLabel("교번 : ");
		StuNum.setBounds(165, 29, 140, 15);
		User.add(StuNum);
	
		
		JLabel Name = new JLabel("이름 : ");
		Name.setBounds(165, 54, 120, 15);
		User.add(Name);
		
		JLabel Birth = new JLabel("생년월일 : ");
		Birth.setBounds(354, 54, 140, 15);
		User.add(Birth);
		
		JLabel College = new JLabel("소속 단대 : ");
		College.setBounds(165, 79, 140, 15);
		User.add(College);
		
		JLabel Department = new JLabel("소속 학과 : ");
		Department.setBounds(354, 79, 160, 15);
		User.add(Department);
		
		
		JLabel TextFieldPhone = new JLabel("전화번호 : ");
		TextFieldPhone.setBounds(165, 104, 140, 15);
		User.add(TextFieldPhone);
		
		JLabel TextFieldEmail = new JLabel("이메일 : ");
		TextFieldEmail.setBounds(354, 104, 140, 15);
		User.add(TextFieldEmail);
		
		JTextField textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(229, 100, 96, 21);
		User.add(textFieldPhone);
		
		JTextField textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(410, 100, 96, 21);
		User.add(textFieldEmail);
		
		JButton Edit = new JButton("수정");
		Edit.setBounds(843, 137, 91, 23);
		User.add(Edit);
		
  		Edit.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				try {
					pm.editInfo(p.getprNum(), textFieldPhone.getText(), textFieldEmail.getText());
					JOptionPane.showMessageDialog(null,"정보가 수정되었습니다.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  			}
  		});
		
		// 프로필 정보 띄우기
		StuNum.setText("교번: "+p.getprNum());
		Name.setText("이름: "+p.getprName());
		College.setText("소속단대: "+p.getCollege());
		Department.setText("소속학과: "+p.getMajor());
		Birth.setText("생년월일: "+p.getBirth());
		TextFieldPhone.setText("전화번호: ");
		TextFieldEmail.setText("이메일: ");
		textFieldPhone.setText(p.getPhoneNum());
		textFieldEmail.setText(p.getEmail());
		
		//휴학 신청 확인
        String colNames[] = {"학번", "이름", "학년도", "학기", "신청일", "처리 상태"};
        String rows[][] = {};
        DefaultTableModel model = new DefaultTableModel(rows, colNames);
		
        JTable table = new JTable(model
        	);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(12, 247, 946, 192);
        Main.add(scrollPane);     
        
        // 해당 학생 신청 여부 띄우기
     	ResultSet rs = null;
     	rs = pm.getGoBack();
     	if (rs.getRow() > -1) {
     		while (rs.next()) {
     			// 신청 학생 이름 받아오기
     			String name = rs.getString(1);
     			// 신청 학생 학번 받아오기
     			String num = rs.getString(2);
     			// 신청날짜 받아오기
     			String now = rs.getString(9);
     			// 구분 받아오기
     			String what = rs.getString(4);
     			// 해당 학수번호를 가진 수업 객체 들고 오기
     			SchoolApplication sa = sm.getSa(num, now, what);
     			// GUI에 띄우기
     			String[] row = { num, name, sa.getApSemester().substring(0,4), sa.getApSemester().substring(5), sa.getApCount(), sa.getApState()};

     			model.addRow(row);
     		}
     	}
        
        JLabel ProfBokhak = new JLabel("복학 신청 조회");
        ProfBokhak.setFont(new Font("한컴산뜻돋움", Font.BOLD, 23));
        ProfBokhak.setBounds(12, 210, 172, 27);
        Main.add(ProfBokhak);
        
        JButton Delete = new JButton("신청 취소");
        Delete.setBounds(771, 446, 91, 23);
        Main.add(Delete);
        
     	Delete.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				try {
  					//선택한 줄(row)
  					int row = table.getSelectedRow();

  					//선택 안하고 누를 경우 
  					if(row == -1) 
  						JOptionPane.showMessageDialog(null,"신청을 취소할 데이터를 선택해주세요.");
  					
  					String stNum = (String) table.getValueAt(row, 0);
  					String now = (String) table.getValueAt(row, 4);
					pm.deleteGoBack(stNum, now);
					model.removeRow(row);
					JOptionPane.showMessageDialog(null,"해당 복학 신청이 취소되었습니다.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  			}
  		});
        
        JButton Confirm = new JButton("승인");
        Confirm.setBounds(867, 446, 91, 23);
        Main.add(Confirm);
        
     	Confirm.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				try {
  					//선택한 줄(row)
  					int row = table.getSelectedRow();

  					//선택 안하고 누를 경우 
  					if(row == -1) 
  						JOptionPane.showMessageDialog(null,"승인할 데이터를 선택해주세요.");
  					
  					String stNum = (String) table.getValueAt(row, 0);
  					String now = (String) table.getValueAt(row, 4);
					pm.acceptGoBack(stNum, now);
					JOptionPane.showMessageDialog(null,"해당 복학 신청이 승인되었습니다.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  			}
  		});
        
        
      		//학적 시스템 액션 리스너 - 메뉴
      		Menu1.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
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
      		Menu2.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				P_Huhak huhak;
					try {
						huhak = new P_Huhak(id);
	      				huhak.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				dispose();
      			}
      		});
      		
      		Menu3.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				P_BuBoksu buboksu;
					try {
						buboksu = new P_BuBoksu(id);
	      				buboksu.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				dispose();
      			}
      		});
      		
      		
      		//좌측 메뉴 이동하는 기능
          	//교수 정보 조회 액션 리스너
        		info.addActionListener(new ActionListener() {
          			public void actionPerformed(ActionEvent e) {
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
        		
        		//휴학 신청 조회 액션 리스너
        		Huhak.addActionListener(new ActionListener() {
          			public void actionPerformed(ActionEvent e) {
          				P_Huhak huhak;
						try {
							huhak = new P_Huhak(id);
	          				huhak.setVisible(true);		
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        				dispose();	
          			}
          		});

        		//자퇴 신청 조회 액션 리스너
        		Jatuae.addActionListener(new ActionListener() {
          			public void actionPerformed(ActionEvent e) {
          				P_Jatuae jatuae;
						try {
							jatuae = new P_Jatuae(id);
	          				jatuae.setVisible(true);	
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
        				dispose();	
          			}
          		});
        		

        		//부/복수 신청 조회 액션 리스너
        		BuBoksu.addActionListener(new ActionListener() {
          			public void actionPerformed(ActionEvent e) {
          				P_BuBoksu buboksu;
						try {
							buboksu = new P_BuBoksu(id);
	          				buboksu.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		
        				dispose();	
          			}
          		});
        		
        		//전과 신청 조회 액션 리스너
        		Junkua.addActionListener(new ActionListener() {
          			public void actionPerformed(ActionEvent e) {
          				P_JunKua junkua;
						try {
							junkua = new P_JunKua(id);
	          				junkua.setVisible(true);	
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
        				dispose();	
          			}
          		});
        		
	}

}
