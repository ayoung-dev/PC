package pc;

import java.awt.*;
import java.awt.Container;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.*;
import java.util.*;
import java.awt.event.*;
import java.sql.SQLException;

public class AdminMenuGUI extends JFrame {

	// 컨테이너 객체
	Container container = getContentPane();

	// 조회버튼을 눌렀는지 확인
	private boolean doSearch = false;

	// 디비에서 선택된 검색된 멤버를 저장
	private ArrayList<Menu> list = new ArrayList<Menu>();
	private DefaultTableModel dtm;
	private JTable jt;
	private JTextField typeField, productField, textFieldType, textFieldProduct, textFieldPrice, textFieldCount,
			textFieldDate, textFieldRemarks;
	private JComboBox<String> comboType;



	public static void main(String[] args) {
		new AdminMenuGUI();
	}

	// ManageMember생성자 시작
	@SuppressWarnings("serial")
	public AdminMenuGUI() {
		// 컬럼네임 지정
		String[] culumnName = { "종류", "품명", "가격", "재고", "입고일", "비고" };
		String[] typeName = {"과자", "음료수", "식사류", "라면"};
		
		// 컴포넌트 선언
		JFrame menuFrame = new JFrame("재고 조회");
		dtm = new DefaultTableModel(culumnName, 0) {
			// 셀편집을 못하게 하는 필드
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JLabel labelType = new JLabel("종류 : ", JLabel.RIGHT); // 오른쪽으로 정렬됨
		JLabel labelProduct = new JLabel("제품명 : ", JLabel.RIGHT);
		JLabel labelPrice = new JLabel("가격 : ", JLabel.RIGHT);
		JLabel labelCount = new JLabel("재고량 : ", JLabel.RIGHT);
		JLabel labelDate = new JLabel("입고일 : ", JLabel.RIGHT);
		JLabel labelRemarks = new JLabel("비고 : ", JLabel.RIGHT);

		// 재고 추가 입력 필드
		comboType = new JComboBox<String>(typeName);

		textFieldProduct = new JTextField(14);
		textFieldPrice = new JTextField(14);
		textFieldCount = new JTextField(14);
		textFieldDate = new JTextField(14);
		textFieldRemarks = new JTextField(14);
		// 검색 필드
		typeField = new JTextField(10);
		productField = new JTextField(14);

		JPanel panelLabel = new JPanel();
		JPanel panelTextField = new JPanel();

		jt = new JTable(dtm);
		JScrollPane panel = new JScrollPane(jt);
		JPanel center = new JPanel();
		JPanel north = new JPanel();
		JPanel east = new JPanel();
		JPanel panelL = new JPanel();
		JPanel panelLU = new JPanel();
		JLabel productLabel = new JLabel("제품명");
		JButton searchBtn = new JButton("   전체 조회   ");
		JButton insertBtn = new JButton("추 가");
		JButton deleteBtn = new JButton("삭 제");
		JButton tableSearchBtn2 = new JButton("검 색");

		// 컴포넌트 결합부
		center.add(panel);
		north.add(productLabel);
		north.add(productField);
		north.add(tableSearchBtn2);
		north.add(deleteBtn);
		north.add(searchBtn);
		east.add(panelL);

		/** panelL 구성 */
		panelL.setLayout(new BorderLayout());
		panelL.add("Center", panelLU);

		/* 오른쪽 상단부 (재고 물품 추가 폼) */

		panelLU.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED), "재고 추가")); // 전체를 묶어줌

		panelLU.setLayout(new BorderLayout());
		panelLU.setPreferredSize(new Dimension(200, 290));
		panelLU.add("West", panelLabel);
		panelLU.add("Center", panelTextField);
		panelLU.add("South", insertBtn);

		// panelLabel 구성
		panelLabel.setLayout(new GridLayout(6, 1, 5, 8));
		panelLabel.add(labelType);
		panelLabel.add(labelProduct);
		panelLabel.add(labelPrice);
		panelLabel.add(labelCount);
		panelLabel.add(labelDate);
		panelLabel.add(labelRemarks);

		// panelTextField 구성
		panelTextField.setLayout(new GridLayout(6, 1, 5, 8));
		panelTextField.add(comboType);
		panelTextField.add(textFieldProduct);
		panelTextField.add(textFieldPrice);
		panelTextField.add(textFieldCount);
		panelTextField.add(textFieldDate);
		panelTextField.add(textFieldRemarks);

		menuFrame.add(north, "North");
		menuFrame.add(panel, "Center");
		menuFrame.add(east, "East");

		// 버튼 이벤트 세팅
		tableSearchBtn2.addActionListener(new ProductSearchEvent());
		searchBtn.addActionListener(new SearchEvent());
		insertBtn.addActionListener(new InsertEvent());
		deleteBtn.addActionListener(new DeleteEvent());

		// 프레임 위치 세팅
		int width = Toolkit.getDefaultToolkit().getScreenSize().width / 5;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height / 7;

		// 프레임의 기본적인 위치와 사이즈 세팅
		menuFrame.setResizable(false);
		menuFrame.setBounds(width, height, 700, 370);
		menuFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		menuFrame.setVisible(true);

	}

	// 조회버튼을 누를시에 menuInfo테이블에서 DB를읽어와서 테이블에 보여주는 이벤트처리 클래스
	private class SearchEvent implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			doSearch = true;
			if (list.size() > 0) {
				list.clear();
			}
			try {
				LoginGUI.ac.readMenu(list);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "조회할 데이터가 없습니다.");
				e1.printStackTrace();
			}
			
			// 데이터삭제
			resetRow();

			for (int i = 0; i < list.size(); i++) {
				// String type, String product, String price, String count, String date, String remarks
				String type = list.get(i).getType();
				String product = list.get(i).getProduct();
				String price = list.get(i).getPrice();
				String count = list.get(i).getCount();
				String date = list.get(i).getDate();
				String remarks = list.get(i).getRemarks();
				String[] str = { type, product, price, count, date, remarks };
				dtm.addRow(str);
			}
		}
	}
	// 조회버튼을 누를시에 menuInfo테이블에서 DB를읽어와서 테이블에 보여주는 이벤트처리 클래스종료

	
	// 메뉴 품목삭제 삭제를 위한 이벤트 클래스 시작
	private class DeleteEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = jt.getSelectedRow();
			if (index != -1) {
				// getSelectedRow() - 선택된 행/ 1번째 열
				String product = (String) dtm.getValueAt(jt.getSelectedRow(), 1);
				try {
					LoginGUI.ac.delMenu(product);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getProduct().equals(product)) {
						list.remove(i);
					}
				}
				dtm.removeRow(jt.getSelectedRow());
				JOptionPane.showMessageDialog(null, "품목 " + product + "를 제거했습니다.");

			} else {
				JOptionPane.showMessageDialog(null, "테이블에서 삭제할 값을 먼저 선택 하세요");
			}

		}
	}
	// 메뉴 품목삭제 삭제를 위한 이벤트 클래스 종료
	
	// 수월하게 삭제하기 위해 품목을 검색하고 삭제하기 위한 이벤트 처리 클래스 시작
	private class ProductSearchEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			String searchProduct = productField.getText();
			if (doSearch == true) {
				if (searchProduct.equals("")) {
					JOptionPane.showMessageDialog(null, "검색할 품목을 입력하세요");
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getProduct().equals(searchProduct)) {
							resetRow();
							doSearch = false;
							String type = list.get(i).getType();
							String product = list.get(i).getProduct();
							String price = list.get(i).getPrice();
							String count = list.get(i).getCount();
							String date = list.get(i).getDate();
							String remarks = list.get(i).getRemarks();
							String[] str = { type, product, price, count, date, remarks };
							dtm.addRow(str);
						} 
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "조회 먼저 클릭해주십시오");
			}
		}
	}


	// 추가 버튼을 누르면 발생하는 이벤트
	private class InsertEvent implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int flag = 0;

			if (textFieldProduct.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "품명을 입력하십시오");
			} else if (textFieldPrice.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "가격을 입력하십시오");
			} else if (textFieldCount.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "재고량을 입력하십시오");
			} else {
				// 비밀번호 해쉬암호 생략
				try {
					LoginGUI.ac.insertMenu(comboType.getSelectedItem().toString(), textFieldProduct.getText(), textFieldPrice.getText(),
							textFieldCount.getText(), textFieldDate.getText(), textFieldRemarks.getText());
					JOptionPane.showMessageDialog(null,"추가되었습니다.");
					
					textFieldProduct.setText("");
					textFieldPrice.setText("");
					textFieldCount.setText("");
					textFieldDate.setText("");
					textFieldRemarks.setText("");
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "재고 등록에 실패하셨습니다");
				}
			}

		}
	}

	// 테이블의 모든 데이터를 삭제하는 메소드 시작
	private void resetRow() {
		if (dtm.getRowCount() > 0) {
			for (int i = dtm.getRowCount() - 1; i > -1; i--) {
				dtm.removeRow(i);
			}
		}
	}
	// /테이블의 모든 데이터를 삭제하는 메소드 종료

}
