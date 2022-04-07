package pc;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Management {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	int num; // 개수를 받는데 쓰임
	double time = 0;

	public Management(String url, String con, String rootpw) throws Exception {// UI에서 생성할 사람의 수 가져와서 객체 생성
		Class.forName(url);
		conn = DriverManager.getConnection(con, "root", rootpw);
		stmt = conn.createStatement();
	}

	public int getCount() { // 등록된 사람 수 접근자
		try {
			rs = stmt.executeQuery("select * from member;");
			rs.last(); // 커서가 맨 마지막 행으로 이동
			num = rs.getRow(); // 행의 개수
		} catch (Exception se) {
			// System.out.println(se.getMessage());
		} // throw exception
		return num;
	}

	// 아이디 중복 확인 메소드
	public boolean checkId(String id) {
		try {
			// 파라미터로 받아온 id을 검색한다.
			rs = stmt.executeQuery("select * from member where id='" + id + "'");

			if (rs.next()) {
				rs.close();
				return true; // true 반환
			} else
				rs.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return false;
	}

	// 전화번호 중복 확인 메소드
	public boolean checkPhoneNum(String phoneNum) {
		try {
			// 파라미터로 받아온 phoneNum을 검색한다.
			rs = stmt.executeQuery("select * from member where phoneNum='" + phoneNum + "'");

			if (rs.next()) {
				rs.close();
				return true;// true 반환
			} else
				rs.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

		return false;
	}

	// 계정 등록 메소드
	public void add(Member P) throws Exception {
		try {
			num = getCount();
			// 받아온 Account 객체 P를 db에 추가해 준다.
			rs = stmt.executeQuery("insert into member(num, user, name, phoneNum, id, pw, time, state) values(" + num
					+ ",'" + P.getUser() + "','" + P.getName() + "','" + P.getPhoneNum() + "','" + P.getId() + "','"
					+ P.getPw() + "','" + P.getTime() + "','" + P.getState() + "');");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 사용중인지 확인
	public int useCheck(String PCNum) throws Exception {
		int i = -1;
		// 받아온 id를 db에서 찾는다.
		rs = stmt.executeQuery("select * from member where state = '" + PCNum + "'");
		while (rs.next()) {
			i = rs.getInt("num"); // 읽어온 num 데이터를 i에 int형으로 복사
			return i;
		}
		throw new Exception("등록된 이름 없음");
	}

	// 이름으로 회원 정보 찾기
	public int searchName(String name) throws Exception {
		int i = -1;
		// 받아온 id를 db에서 찾는다.
		rs = stmt.executeQuery("select * from member where name = '" + name + "'");
		while (rs.next()) {
			i = rs.getInt("num"); // 읽어온 num 데이터를 i에 int형으로 복사
			return i;
		}
		throw new Exception("등록된 이름 없음");
	}

	// 아이디로 가입회원인지 검색, 등록된 아이디가 없을 경우 익셉션
	public int searchId(String id) throws Exception {
		int i = -1;
		// 받아온 id를 db에서 찾는다.
		rs = stmt.executeQuery("select * from member where id = '" + id + "'");
		while (rs.next()) {
			i = rs.getInt("num"); // 읽어온 num 데이터를 i에 int형으로 복사
			return i;
		}
		throw new Exception("등록된 아이디 없음");
	}

	// 전화번호로 가입회원인지 검색, 등록된 전화번호가 없을 경우 익셉션
	public int searchPhone(String phoneNum) throws Exception {
		int i = -1;
		// 받아온 phoneNum를 db에서 찾는다.
		rs = stmt.executeQuery("select * from member where phoneNum = '" + phoneNum + "'");
		while (rs.next()) {
			i = rs.getInt("num"); // 읽어온 num 데이터를 i에 int형으로 복사
			return i;
		}
		throw new Exception("등록된 전화번호 없음");
	}

	// 시간 수정 메소드
	public void modifytime(int index, double time) throws SQLException {
		try {
			double newtime = 0;
			rs = stmt.executeQuery("select *from member where num='" + index + "'");
			while (rs.next()) {
				newtime = time + rs.getDouble("time");
			}
			rs = stmt.executeQuery("update member set time='" + newtime + "'where num='" + index + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 시간을 0으로 수정하는 메소드
	public void zerotime(int index, double time) throws SQLException {
		try {
			rs = stmt.executeQuery("update member set time='" + time + "'where num='" + index + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// state를 사용가능으로 수정하는 메소드
	public void logoutModify(int index, String state) throws SQLException {
		try {
			rs = stmt.executeQuery("update member set state='" + state + "'where num='" + index + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 해당 서버의 ip를 가져오는 메소드
	public void getIp(int index, String ip) throws SQLException {
		try {
			rs = stmt.executeQuery("update member set ip='" + ip + "'where num='" + index + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 개인정보 수정 메소드
	public void modify(int index, String name, String phonenum, String id, String pw) throws SQLException {
		try {
			rs = stmt.executeQuery("update member set name='" + name + "'where num='" + index + "'");
			rs = stmt.executeQuery("update member set phoneNum='" + phonenum + "'where num='" + index + "'");
			rs = stmt.executeQuery("update member set id='" + id + "'where num='" + index + "'");
			rs = stmt.executeQuery("update member set pw='" + pw + "'where num='" + index + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// state를 사용중으로 바꾸는 메소드
	public void stateModify(int index, String PCNum) throws SQLException {
		try {
			rs = stmt.executeQuery("update member set state='" + PCNum + "'where num='" + index + "'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Person 객체 넘겨주는 메소드
	public Member getPerson(int index) {
		String user = "", name = "", phoneNum = "", id = "", pw = "", state = "", ip="";
		try {
			rs = stmt.executeQuery("select * from member where num='" + index + "'");
			while (rs.next()) {
				user = rs.getString("user");
				name = rs.getString("name");
				phoneNum = rs.getString("phoneNum");
				id = rs.getString("id");
				pw = rs.getString("pw");
				time = rs.getDouble("time");
				state = rs.getString("state");
				ip = rs.getString("ip");
			}
			Member person = new Member(user, name, phoneNum, id, pw, time, state, ip);
			return person;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 메뉴 등록시켜주는 메소드
	public void insertMenu(String type, String product, String price, String count, String date, String remarks)
			throws Exception {
		try {
			// "종류", "품명", "가격", "재고량", "입고일", "비고"
			rs = stmt.executeQuery("insert into menu(type, product, price, count, date, remarks) "
					+ "values('" + type + "','" + product + "','" + price + "','" + count + "','"
					+ date + "','" + remarks + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 메뉴 삭제시켜주는 메소드
	public void delMenu(String product) throws SQLException {
		try {
			rs = stmt.executeQuery("delete from menu where product='" + product + "'");

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "메뉴 삭제에 실패했습니다.");
		}
	}

	// 메뉴 판매 메소드
	public void sellMenu(String product, String count) {
		try {
			rs = stmt.executeQuery("update menu set count='" + count + "' where product='" + product + "'");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "결제에 실패했습니다.");
		}
	}

	// 메뉴 DB로 부터 값을 읽어온다
	public void readMenu(ArrayList<Menu> list) throws Exception {
		int num = 0;
		try {
			// type, product, price, count, date, remarks
			rs = stmt.executeQuery("select * from menu");
			while (rs.next()) {
				String type = rs.getString("type");
				String product = rs.getString("product");
				String price = rs.getString("price");
				String count = rs.getString("count");
				String date = rs.getString("date");
				String remarks = rs.getString("remarks");

				list.add(new Menu(type, product, price, count, date, remarks));
				num++;
			}
			/*
			if (num == 0) {
				JOptionPane.showMessageDialog(null, "조회할 데이터가 존재하지 않습니다.");
			}
			*/

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// 메뉴DB에서 값읽어 오는 메소드 종료

	// 특정 상품 정보 불러온다.
	public Menu readProduct(String product) {

		Menu menuInfo = null;
		try {
			int num = 0;
			rs = stmt.executeQuery("select * from menu where product='" + product + "'");

			while (rs.next()) {

				String type = rs.getString("type");
				String price = rs.getString("price");
				String count = rs.getString("count");
				String date = rs.getString("date");
				String remarks = rs.getString("remarks");

				menuInfo = new Menu(type, product, price, count, date, remarks);
				num++;
			}
			/*
			if (num == 0) {
				JOptionPane.showMessageDialog(null, "조회할 데이터가 존재 하지 않습니다.");
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuInfo;
	}
	// 메뉴DB에서 값읽어 오는 메소드 종료

	// 특정 종류 정보를 불러온다.
	public Menu[] readType(String type, ArrayList<Menu> typeList) {

		Menu menuInfo[] = null;

		try {

			int num = 0;
			rs = stmt.executeQuery("select * from menu where type='" + type + "'");
			while (rs.next()) {
				String product = rs.getString("product");
				String price = rs.getString("price");
				String count = rs.getString("count");
				String date = rs.getString("date");
				String remarks = rs.getString("remarks");

				typeList.add(new Menu(type, product, price, count, date, remarks));
				num++;
			}
			/*
			if (num == 0) {
				JOptionPane.showMessageDialog(null, "조회할 데이터가 존재 하지 않습니다.");
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuInfo;

	}
	// 메뉴DB에서 값읽어 오는 메소드 종료
	
	// 매출 수정 메소드
	public void salesmodify(int sales) throws SQLException {
		int totalsales = 0;
		try {
			rs = stmt.executeQuery("select sales from sales;");
			while (rs.next()) {
				totalsales = rs.getInt("sales");
			}
			totalsales += sales;
			rs = stmt.executeQuery("update sales set sales='" + totalsales + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 매출 가져오기
	public String getSales() throws Exception {
		int i = -1;
		String salesText = null;
		
		rs = stmt.executeQuery("select sales from sales;");
		while (rs.next()) {
			salesText = String.valueOf(rs.getInt("sales"));
		}
		return salesText;
	}
}
