package pc;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.event.*;
import java.sql.SQLException;

class UserMenuGUI {

	private int total = 0; // 총 금액에 들어갈 정수

	// 취소버튼을 눌렀는지 확인
	private boolean doDelete = false;
	
	// 과자 클릭 count
	private int[] sCnt = new int[20];
	// 음료수 클릭 count
	private int[] jCnt = new int[20];
	// 라면 클릭 count
	private int[] nCnt = new int[20];
	// 식사 클릭 count
	private int[] mCnt = new int[12];

	// 디비에서 선택된 검색된 멤버를 저장
	private ArrayList<Member> list = new ArrayList<Member>();
	private ArrayList<Menu> typeList = new ArrayList<Menu>();
	private Menu productList = null;
	private DefaultTableModel dtm;
	private JTable jt;
	private JTextField idField;
	private JLabel totalPrice;
	
	public static void main(String[] args) {
		new UserMenuGUI();
	}
	
	public UserMenuGUI() {
		// 컬럼네임 지정
		String[] orderCulumnName = { "제품명", "가격", "수량" };

		// 컴포넌트 선언
		dtm = new DefaultTableModel(orderCulumnName, 0) {
			// 셀편집을 못하게 하는 필드
			public boolean isCellEditable(int row, int column) {
				return true;
			}
		};
		JTabbedPane tap = new JTabbedPane(); // JTabbedPane생성

		// 컴포넌트 선언
		JFrame orderFrame = new JFrame("음식 주문");

		// 스크롤 패널 생성 후 테이블 넣기?
		jt = new JTable(dtm);
		JScrollPane orderPanel = new JScrollPane(jt);
		orderPanel.setPreferredSize(new Dimension(270, 300));

		// 패널 생성
		JPanel pSnack = new JPanel();
		JPanel pJuice = new JPanel();
		JPanel pNoodle = new JPanel();
		JPanel pMeal = new JPanel();
		JPanel east = new JPanel();
		JPanel north = new JPanel();
		JPanel orderU = new JPanel();
		JPanel orderD = new JPanel();
		JPanel sum = new JPanel();

		int orderSum = 0;

		// 버튼
		JButton delBtn = new JButton("취 소");
		JButton buyBtn = new JButton("결 제");

		// 라벨
		totalPrice = new JLabel("  총  금 액  :  0 ");

		// 탭 생성
		tap.add(" 과 자 ", pSnack); // JTabbedPane에 탭추가
		tap.add(" 음 료 수 ", pJuice);
		tap.add(" 라 면 ", pNoodle);
		tap.add(" 식 사 ( 덮 밥 ) ", pMeal);

		// 오른쪽 order창 아래 / 조회+결제 버튼 레이아웃
		orderD.setLayout(new GridLayout(1, 2, 3, 3));
		orderD.setPreferredSize(new Dimension(270, 60));
		orderD.add(delBtn);
		orderD.add(buyBtn);

		orderFrame.add(east, "East");
		orderFrame.add(tap, "Center");

		// 오른쪽 order 창 위 / 선택 내역
		orderU.setLayout(new BorderLayout());
		orderU.add(orderPanel, "Center");
		orderU.add(sum, "South");

		// east 레이아웃(주문 목록과 결제 창) 세팅
		east.setLayout(new BorderLayout());
		east.add(orderU, "Center");
		east.add(orderD, "South");

		// orderlist에 총 금액 나오는 sum Panel
		sum.setLayout(new GridLayout(1, 2, 4, 3));
		sum.setPreferredSize(new Dimension(270, 30));
		sum.add(totalPrice);
		// sum.add();

		// 이벤트 리스너
		buyBtn.addActionListener(new sumEvent());
		delBtn.addActionListener(new delEvent());

		/* 과자 탭 설정 */
		LoginGUI.ac.readType("과자", typeList);
		
		ArrayList<String> snackName = new ArrayList<String>();
		ArrayList<String> snackPrice = new ArrayList<String>();
		
		for (int i = 0; i < typeList.size(); i++) {
			// String type, String product, String price, String count, String date, String remarks
			String product = typeList.get(i).getProduct();
			String price = typeList.get(i).getPrice();
			
			snackName.add(product);
			snackPrice.add(price);			
		}
		
		JButton[] sButtons = new JButton[20];

		for (int i = 0; i < snackName.size(); i++) {
			sButtons[i] = new JButton(snackName.get(i));
		}
		// 스낵코너 레이아웃(음식 보이는 창) 세팅
		pSnack.setLayout(new GridLayout(4, 5, 3, 3));
		for (int i = 0; i < snackName.size(); i++) {
			pSnack.add(sButtons[i]);
		}

		// 과자 클릭 이벤트
		for (int i = 0; i < snackName.size(); i++) {
			final int idx1;
			idx1 = i;

			sButtons[idx1].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					sCnt[idx1]++;
					JOptionPane.showMessageDialog(null, snackName.get(idx1) + "+" + sCnt[idx1] + " 주문 목록에 담습니다.");
					total += Integer.parseInt(snackPrice.get(idx1));
					totalPrice.setText("  총  금 액  :  " + total);
					if (sCnt[idx1] <= 1)
						dtm.addRow(new Object[] { snackName.get(idx1), snackPrice.get(idx1), sCnt[idx1] });
					else {

						for (int j = 0; j < dtm.getRowCount(); j++) {
							for (int k = 0; k < dtm.getColumnCount(); k++) {
								if (dtm.getValueAt(j, k) == snackName.get(idx1)) {
									dtm.setValueAt(sCnt[idx1], j, 2);
									break;
								}
							}
						}
					}
				}
			});
		}

		/* 음료수 탭 설정 */
		typeList.clear();
		LoginGUI.ac.readType("음료수", typeList);
		
		ArrayList<String> juiceName = new ArrayList<String>();
		ArrayList<String> juicePrice = new ArrayList<String>();
		
		for (int i = 0; i < typeList.size(); i++) {
			// String type, String product, String price, String count, String date, String remarks
			String product = typeList.get(i).getProduct();
			String price = typeList.get(i).getPrice();
			
			juiceName.add(product);
			juicePrice.add(price);
		}
		
		JButton[] jButtons = new JButton[20];

		for (int i = 0; i < juiceName.size(); i++) {
			jButtons[i] = new JButton(juiceName.get(i));
		}
		// 음료 코너 레이아웃(음식 보이는 창) 세팅
		pJuice.setLayout(new GridLayout(4, 5, 3, 3));
		for (int i = 0; i < juiceName.size(); i++) {
			pJuice.add(jButtons[i]);
		}

		// 음료 클릭 이벤트
		for (int i = 0; i < juiceName.size(); i++) {
			final int idx2;
			idx2 = i;

			jButtons[idx2].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					jCnt[idx2]++;
					JOptionPane.showMessageDialog(null, juiceName.get(idx2) + "+" + jCnt[idx2] + " 주문 목록에 담습니다.");
					total += Integer.parseInt(juicePrice.get(idx2));
					totalPrice.setText("  총  금 액  :  " + total);
					if (jCnt[idx2] <= 1)
						dtm.addRow(new Object[] { juiceName.get(idx2), juicePrice.get(idx2), jCnt[idx2] });
					else {

						for (int j = 0; j < dtm.getRowCount(); j++) {
							for (int k = 0; k < dtm.getColumnCount(); k++) {
								if (dtm.getValueAt(j, k) == juiceName.get(idx2)) {
									dtm.setValueAt(jCnt[idx2], j, 2);
									break;
								}
							}
						}
					}
				}
			});
		}

		/* 라면 탭 설정 */
		typeList.clear();
		LoginGUI.ac.readType("라면", typeList);
		
		ArrayList<String> NoodleName = new ArrayList<String>();
		ArrayList<String> noodlePrice = new ArrayList<String>();
		
		for (int i = 0; i < typeList.size(); i++) {
			// String type, String product, String price, String count, String date, String remarks
			String product = typeList.get(i).getProduct();
			String price = typeList.get(i).getPrice();
			
			NoodleName.add(product);
			noodlePrice.add(price);
		}
		JButton[] nButtons = new JButton[20];

		for (int i = 0; i < NoodleName.size(); i++) {
			nButtons[i] = new JButton(NoodleName.get(i));
		}
		// 라면 코너 레이아웃(음식 보이는 창) 세팅
		pNoodle.setLayout(new GridLayout(4, 5, 3, 3));
		for (int i = 0; i < NoodleName.size(); i++) {
			pNoodle.add(nButtons[i]);
		}
		// 라면 클릭 이벤트
		for (int i = 0; i < NoodleName.size(); i++) {
			final int idx3;
			idx3 = i;

			nButtons[idx3].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					nCnt[idx3]++;
					JOptionPane.showMessageDialog(null, NoodleName.get(idx3) + "+" + nCnt[idx3] + " 주문 목록에 담습니다.");
					total += Integer.parseInt(noodlePrice.get(idx3));
					totalPrice.setText("  총  금 액  :  " + total);
					if (nCnt[idx3] <= 1) {
						dtm.addRow(new Object[] { NoodleName.get(idx3), noodlePrice.get(idx3), nCnt[idx3] });
					} else {

						for (int j = 0; j < dtm.getRowCount(); j++) {
							for (int k = 0; k < dtm.getColumnCount(); k++) {
								if (dtm.getValueAt(j, k) == NoodleName.get(idx3)) {
									dtm.setValueAt(nCnt[idx3], j, 2);
									break;
								}
							}
						}
					}
				}
			});
		}

		/* 식사 탭 설정 */
		typeList.clear();
		LoginGUI.ac.readType("식사류", typeList);
		
		ArrayList<String> MealName = new ArrayList<String>();
		ArrayList<String> mealePrice = new ArrayList<String>();
		
		for (int i = 0; i < typeList.size(); i++) {
			// String type, String product, String price, String count, String date, String remarks
			String product = typeList.get(i).getProduct();
			String price = typeList.get(i).getPrice();
			
			MealName.add(product);
			mealePrice.add(price);
		}
		
		JButton[] mButtons = new JButton[11];

		for (int i = 0; i < MealName.size(); i++) {
			mButtons[i] = new JButton(MealName.get(i));
		}
		// 식사 코너 레이아웃(음식 보이는 창) 세팅
		pMeal.setLayout(new GridLayout(4, 5, 3, 3));
		for (int i = 0; i < MealName.size(); i++) {
			pMeal.add(mButtons[i]);
		}

		// 식사 클릭 이벤트
		for (int i = 0; i < MealName.size(); i++) {
			final int idx4;
			idx4 = i;
			int j = -1;
			int k = -1;

			mButtons[idx4].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					mCnt[idx4]++;
					JOptionPane.showMessageDialog(null, MealName.get(idx4) + "+" + mCnt[idx4] + " 주문 목록에 담습니다.");
					total += Integer.parseInt(mealePrice.get(idx4));
					totalPrice.setText("  총  금 액  :  " + total);
					if (mCnt[idx4] <= 1)
						dtm.addRow(new Object[] { MealName.get(idx4), mealePrice.get(idx4), mCnt[idx4] });
					else {
						for (int j = 0; j < dtm.getRowCount(); j++) {
							for (int k = 0; k < dtm.getColumnCount(); k++) {
								if (dtm.getValueAt(j, k) == MealName.get(idx4)) {
									dtm.setValueAt(mCnt[idx4], j, 2);
									break;
								}
							}
						}
					}
				}
			});
		}

		// 프레임 위치 세팅
		int width = Toolkit.getDefaultToolkit().getScreenSize().width / 3;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height / 7;

		// 프레임의 기본적인 위치와 사이즈 세팅
		orderFrame.setResizable(false);
		orderFrame.setBounds(width, height, 800, 450);
		orderFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		orderFrame.setVisible(true);
	}

	// 결제 버튼을 누르면 발생하는 이벤트
	private class sumEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int flag = 0;
			
			// 데베에 총금액 내용이 변경되야하는 코드
			if (dtm.getRowCount() > 0) {
				for(int i=0; i<dtm.getRowCount(); i++) {
					
					// getSelectedRow() - 선택된 행/ 1번째 열
					String sellProduct = (String) dtm.getValueAt(i, 0);
					int sellCount = (int) dtm.getValueAt(i, 2);
					System.out.println(sellProduct+sellCount);
					
					productList = LoginGUI.ac.readProduct(sellProduct);
					// 팔고 난 다음 남는 개수
					int afterCount = Integer.parseInt(productList.getCount()) - sellCount;
						
						LoginGUI.ac.sellMenu(sellProduct, Integer.toString(afterCount));
				}
				try {
					LoginGUI.ac.salesmodify(total);
					try {
						AdministratorGUI.salesLabel.setText(LoginGUI.ac.getSales());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					JOptionPane.showMessageDialog(null, "결제 완료되었습니다.");				
					resetRow();
					total = 0;
					totalPrice.setText("  총  금 액  :  " + total);
			}

		}
	}

	// 취소 버튼을 누르면 발생하는 이벤트
	private class delEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			resetRow();
			total = 0;
			totalPrice.setText("  총  금 액  :  " + total);
		}
	}

	// 테이블의 모든 데이터를 삭제하는 메소드 시작
	private void resetRow() {
		if (dtm.getRowCount() > 0) {
			sCnt = new int[20];
			jCnt = new int[20];
			nCnt = new int[20];
			mCnt = new int[12];
			for (int i = dtm.getRowCount() - 1; i > -1; i--) {
				dtm.removeRow(i);
			}
		}
	}
	// /테이블의 모든 데이터를 삭제하는 메소드 종료

}
