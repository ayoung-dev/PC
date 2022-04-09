package pc;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MemberGUI extends JFrame {
	public static int money;
	public static int selectindex;
	public static DefaultTableModel model;

	public MemberGUI() {
		setTitle("회원 조회");
		setBounds(10, 50, 600, 550);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		//이름, 아이디를 선택할 수 있는 콤보박스 생성
		JComboBox comboBox = new JComboBox(); 
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"이름", "아이디"}));
		comboBox.setBackground(Color.white);
		panel_1.add(comboBox);
		
		//검색창 만들기
		JTextField searchtextField = new JTextField("검색할 내용을 입력하세요.");
		panel_1.add(searchtextField);
		searchtextField.setColumns(15); //검색창 가로 사이즈 설정
		searchtextField.addMouseListener(new MouseAdapter(){  //텍스트필드 클릭 시 내용 초기화
			public void mouseClicked(MouseEvent e){ 
				   searchtextField.setText(""); 
			   } 
		});
		
		//"검색"버튼 만들기
		JButton searchbutton = new JButton("검색"); 
		JButton totalbutton = new JButton("전체 검색");
		panel_1.add(searchbutton);
		panel_1.add(totalbutton);
		
		//저장된 회원 리스트가 나타나는 테이블
		String colNames[] = {"이름", "아이디", "전화번호", "사용중/사용X", "요금"}; //테이블 내용 이름들
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model); //주소록 테이블 만들기
		panel.add(new JScrollPane(table));
		
		totalbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//전체 주소록 보이기
				model.setNumRows(0);
				int c = LoginGUI.ac.getCount();
				if(c == 0) {
					JOptionPane.showMessageDialog(null,"등록된 회원이 없습니다.");
				}
				else {
					for(int i=1; i<c; i++) {
						Object[] row;
						try {
							String state;
							int paytime = (int) ((Math.round(LoginGUI.ac.getPerson(i).getTime())) / 60);
							money = 0;
							if(paytime == 0) {
								money = 0;
							} else if(paytime > 0 && paytime <= 60) {
								money = 1000;
							} else {
								money = paytime * 1000;
							}
							
							if(LoginGUI.ac.getPerson(i).getState().equals("사용가능")) {
								state = "사용X";
							} else {
								state = LoginGUI.ac.getPerson(i).getState();
							}
							row = new Object[] {LoginGUI.ac.getPerson(i).getName(), LoginGUI.ac.getPerson(i).getId(), LoginGUI.ac.getPerson(i).getPhoneNum(), state, money};
							model.addRow(row); // 추가
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
			}
		});
		
		//검색 버튼을 누르면 해당 주소록만 테이블에 보이게 한다.
		searchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				String num, name, id, phonenum, state;
				int index = 0;
				Object[] arr;
				try {			
					if(comboBox.getSelectedItem().toString() == "아이디") {
						index=LoginGUI.ac.searchId(searchtextField.getText());
					}
					else if(comboBox.getSelectedItem().toString() == "이름") {
						index=LoginGUI.ac.searchName(searchtextField.getText());	
					}
					num = Integer.toString(index);
					name = LoginGUI.ac.getPerson(index).getName();
					id = LoginGUI.ac.getPerson(index).getId();
					int paytime = (int) ((Math.round(LoginGUI.ac.getPerson(index).getTime())) / 60);
					int money = 0;
					if(LoginGUI.ac.getPerson(index).getTime() == 0.00) {
						money = 0;
					} else if(LoginGUI.ac.getPerson(index).getTime() > 0 && LoginGUI.ac.getPerson(index).getTime() <= 60) {
						money = 1000;
					} else {
						money = paytime * 1000;
					}
					phonenum = LoginGUI.ac.getPerson(index).getPhoneNum();
					if(LoginGUI.ac.getPerson(index).getState().equals("사용가능")) {
						state = "사용X";
					} else {
						state = LoginGUI.ac.getPerson(index).getState();
					}
					arr = new Object[] {name, id, phonenum, state, money};
					model.addRow(arr);
				} catch (Exception e1) {
					if(comboBox.getSelectedItem().toString() == "이름") {
						JOptionPane.showMessageDialog(null,"등록된 이름이 없습니다.");	
					}
					else if(comboBox.getSelectedItem().toString() == "아이디") {
						JOptionPane.showMessageDialog(null,"등록된 아이디가 없습니다.");	
					}		
				}						
			}
		});
		JPanel panel1 = new JPanel();
		getContentPane().add(panel1, BorderLayout.SOUTH);
		JButton paybutton = new JButton("결제하기");
		panel1.add(paybutton);
		
		//버튼 클릭하면 정산되어서 시간을 0으로 초기화
		paybutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int accountOption = JOptionPane.showConfirmDialog(null, "결제하시겠습니까?","결제",JOptionPane.YES_NO_OPTION);
				if(accountOption == JOptionPane.YES_OPTION) {
					try {
						selectindex = table.getSelectedRow();
						LoginGUI.ac.zerotime(selectindex + 1, 0);
						LoginGUI.ac.logoutModify(selectindex + 1, "사용가능");
						if(LoginGUI.ac.getPerson(selectindex + 1).getTime() == 0) {
							JOptionPane.showMessageDialog(null,"결제되었습니다.");
							LoginGUI.ac.salesmodify((int)model.getValueAt(selectindex, 4));
							AdministratorGUI.salesLabel.setText(LoginGUI.ac.getSales());
							model.setValueAt(0, selectindex, 4);
						}
					}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null,"결제에 실패했습니다.");
					}
				}
				else if((accountOption == JOptionPane.NO_OPTION) || (accountOption == JOptionPane.CLOSED_OPTION)) {
					return;
				}
			}
		});
		
		setVisible(true);
		setResizable(false);
	}
}
