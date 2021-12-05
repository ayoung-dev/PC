package school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class S_Management {

	public static String id;
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;
	public Statement st;
	public ResultSet rs;
	String leName, leNum, semester, prName, room, type, time, preLecture, division, state;
	int credit, attMem, limMem, limGrade;

	// ********************************************************수영***************************************************

	public S_Management(String s, Connection con) throws SQLException, ClassNotFoundException {

		con = DriverManager.getConnection(s, "root", "1234");
		st = con.createStatement();
	}

	// 강의
	// 과목 내용 받아오기
	public ResultSet getLecture() throws SQLException {
		// 입력한 index값을 가진 데이터 보이는 명령어 실행
		ResultSet rs = st.executeQuery("select*from lecture;");

		return rs;
	}

	// 모든 클래스 시작하면서 학번을 입력하면 해당 학생의 이름을 반환하도록 함
	
	public String getstName(String id) throws Exception {
		String stName = null;
		ResultSet rs = st.executeQuery("select*from student where stNum = '" + id + "';");
		while (rs.next()) {
			stName = rs.getString(1);
		}

		return stName;
	}

	// 수강 신청 하기
	// 조건 - 강의해서 지정한 학년 맞는지, [선수 과목 들었는지 확인하기]
	// S_ApplicationClass
	public void applicationClass(String stNum, Lecture le, String stName) throws SQLException {
		// 해당 학기에 해당 수업을 장바구니에 넣어놓았는지 확인하기
		// 장바구니에 넣어놓았으면 true, 장바구니에 넣어놓지 않았다면 false
		if (isBucket(stNum, le)) {
			// 해당 내역을 찾아서 state만 수정하기
			String sql = "update student" + stNum + " set State = 1 where lecNum = '" + le.getleNum() + "';";
			// 명령어 실행
			st.executeUpdate(sql);
		} else {
			// 최종적으로 해당 학생의 정보를 테이블에 입력하기
			// 수강 신청한 내역은 State = 1로 설정
			String sql = "insert into student" + stNum + " values ('" + le.getleName() + "','" + le.getleNum() + "',"
					+ "'" + le.getDivision() + "','" + le.getCredit() + "', '" + le.getTime() + "'," + "'"
					+ le.getprName() + "',NULL,NULL,'" + le.getSemester() + "', 1);";
			// 명령어 실행
			st.executeUpdate(sql);
		}

		// 교수가 table에 입력
		String sql1 = "insert into lenum values ('" + stNum + "', '" + stName + "', '" + le.getleName() + "', '"
				+ le.getleNum() + "', '" + le.getDivision() + "', " + "" + le.getCredit()
				+ ", NULL, NULL, NULL, NULL, NULL, NULL, '" + le.getprName() + "', '" + le.getTime() + "', '"
				+ le.getLimGrade() + "', '" + le.getSemester() + "');";
		// values ('"+stName+"', '"+stNum+"',
		// 명령어 실행
		st.executeUpdate(sql1);

		// 전체 수업 내역에 있는 신청 학생 수 한명 늘이기
		String sql2 = "update lecture set AttMem = " + (le.getAttMem() + 1) + " where Number = '" + le.getleNum()
				+ "';";

		// 명령어 실행
		st.executeUpdate(sql2);

	}

	// 수강 신청 하는 과목이 이미 장바구니 내역에 있는지 확인하기
	public boolean isBucket(String stNum, Lecture le) throws SQLException {
		int result = 0;
		// 해당 학기에 해당 시간에 강의를 장바구니에 넣었는지 확인
		ResultSet rs = st.executeQuery("select EXISTS (select * from student" + stNum + " WHERE semester = '"
				+ le.getSemester() + "' AND State = 0 AND lecNum = '" + le.getleNum() + "') as success;");

		while (rs.next()) {
			result = rs.getInt(1);
		}

		// 해당 내역이 존재한다면!
		if (result == 1) {
			return true;
		} else {
			// 해당 내역이 존재하지 않는다면!
			return false;
		}
	}

	// 수강신청 내역에 중복 내용
	// S_ApplicationClass
	// S_StuClasses
	public int isClass(String stNum, Lecture le) throws SQLException {
		int result = 0;
		// 해당 학기에 수강 신청한 내역 보이기
		// 해당 학기에 해당 시간에 강의를 듣는 수업이 있는지 확인
		ResultSet rs = st.executeQuery("select EXISTS (select * from student" + stNum + " WHERE semester = '"
				+ le.getSemester() + "' AND time = '" + le.getTime() + "' AND State = 1) as success;");

		while (rs.next()) {
			result = rs.getInt(1);
		}

		return result;
	}

	// 학수 번호 입력하면 해당 데이터 들고오기
	// S_ShowAllClasses
	// S_ShowAllGrade
	public Lecture getLe(String leNum) throws SQLException {
		// 입력한 학수번호를 가진 수업 내역 불러오기
		ResultSet rs = st.executeQuery("select*from lecture where Number = '" + leNum + "';");
		while (rs.next()) {
			// 각각 메소드 값을 받아와서 할당함.
			leName = rs.getString(1);
			leNum = rs.getString(2);
			semester = rs.getString(3);
			limGrade = rs.getInt(4);
			prName = rs.getString(5);
			type = rs.getString(6);
			time = rs.getString(7);
			credit = rs.getInt(8);
			attMem = rs.getInt(9);
			limMem = rs.getInt(10);
			preLecture = rs.getString(11);
			division = rs.getString(12);
		}
		// 수업 객체 생성
		Lecture le = new Lecture(leName, leNum, semester, limGrade, prName, type, time, credit, attMem, limMem,
				preLecture, division, state);

		return le;
	}

	// 학번 입력하면 해당 학기 총 이수 학점 들고오기
	// S_ShowAllGrade
	// S_StuClasses
	// S_ApplicationClass
	public int getStudentTotalCredit(String stNum, String semester) throws SQLException {
		int TotalCredit = 0;
		// 학생 개인 테이블에서 입력한 학수번호를 가진 수업 내역 불러오기
		ResultSet rs = st
				.executeQuery("select*from student" + stNum + " WHERE semester = '" + semester + "' AND State = 1;");
		while (rs.next()) {
			credit = rs.getInt(4);
			TotalCredit += credit;
		}
		return TotalCredit;
	}

	// 해당 학기에 수강신청한 과목갯수 들고오기
	// S_ApplicationClass
	public int getStudentTotalCount(String stNum, String semester) throws SQLException {
		// 학생 개인 테이블에서 입력한 학수번호를 가진 수업 내역 불러오기
		ResultSet rs = st.executeQuery(
				"select count(*)from student" + stNum + " WHERE Semester = '" + semester + "' AND State = 1;");
		rs.next();
		// 행 개수 읽어서 num 변수에 저장
		int num = rs.getInt(1);
		return num;
	}

	// 장바구니에서 과목 내용 받아오기
	// S_ApplicationClass
	// S_Bucket
	public ResultSet bucketLecture(String classification, String major, String queary, String which, String semester)
			throws SQLException {
		if (queary.equals("")) {
			rs = st.executeQuery("select*from lecture where Division = '" + classification + "' and Number like '"
					+ major + "%' and Semester = '" + semester + "';");
		}
		// 첫번째 인자 - 검색하는 내용이 교과목인지 학수번호인지 알려줌, 두번쨰 인자 - 검색하는 내용
		else if (which.equals("과목명")) {
			// 교과목명일 경우
			rs = st.executeQuery("select*from lecture where Name = '" + queary + "' and Division = '" + classification
					+ "' and Number like '" + major + "%' and Semester = '" + semester + "';");
		} else {
			// 학수번호일 경우
			rs = st.executeQuery("select*from lecture where Division = '" + classification + "' and Number = '" + queary
					+ "' and Semester = '" + semester + "';");
		}
		return rs;
	}

	// 장바구니에 담은 학점 들고오기
	// S_Bucket
	public int getStudentBucketCredit(String stNum, String semester) throws SQLException {
		int BucketCredit = 0;
		// 학생 개인 테이블에서 입력한 학수번호를 가진 수업 내역 불러오기
		ResultSet rs = st
				.executeQuery("select*from student" + stNum + " WHERE Semester = '" + semester + "' AND State = 0;");
		while (rs.next()) {
			credit = rs.getInt(4);
			BucketCredit += credit;
		}
		return BucketCredit;
	}

	// 장바구니에 담은 과목갯수 들고오기
	// S_Bucket
	public int getStudentBucketCount(String stNum, String semester) throws SQLException {
		// 학생 개인 테이블에서 입력한 학수번호를 가진 수업 내역 불러오기
		ResultSet rs = st.executeQuery(
				"select count(*)from student" + stNum + " WHERE Semester = '" + semester + "' AND State = 0;");
		rs.next();
		// 행 개수 읽어서 num 변수에 저장
		int num = rs.getInt(1);
		return num;
	}

	// 수업 조회하는 기능
	// 전체 수업에서 해당 학기에 해당 되는 내용 불러오기
	// S_ShowAllClasses
	public ResultSet getClasses(String stNum, String semester) throws SQLException {
		// 전체 과목 테이블에서 입력한 학기 정보를 가진 수업 내역 불러오기
		ResultSet rs = st.executeQuery("select*from Lecture WHERE semester = '" + semester + "';");
		return rs;
	}

	// 해당 학생의 해당 학기에 수강한 내역 불러오기
	// S_ShowAllClasses
	// S_ShowAllGrade
	// S_StuClasses
	public ResultSet getStClasses(String stNum, String semester) throws SQLException {
		// 학생 개인 테이블에서 입력한 학기 정보를 가진 수업 내역 불러오기
		ResultSet rs = st
				.executeQuery("select*from student" + stNum + " WHERE semester = '" + semester + "' AND state = 1;");
		return rs;
	}

	// 장바구니 담기 - 장바구니에 최대한 넣을 수 있는 학점 : 25학점 인 걸로 하기
	// 학생 개인 table에 입력하기 -> State = 0인 상태로 입력하기.
	// S_Bucket
	public void putBucket(String stNum, String semester, Lecture le) throws SQLException {
		// 학생 개인 테이블에 필드값 넣기
		// 수강 신청한 내역은 State = 1로 설정
		String sql = "insert into student" + stNum + " values ('" + le.getleName() + "','" + le.getleNum() + "'," + "'"
				+ le.getType() + "','" + le.getCredit() + "', '" + le.getTime() + "'," + "'" + le.getprName()
				+ "',NULL,NULL,'" + le.getSemester() + "', 0);";
		// 명령어 실행
		st.executeUpdate(sql);
	}

	// 장바구니 내역 불러오기
	// S_Bucket
	public ResultSet getBucket(String stNum, String semester) throws SQLException {
		// 학생 개인 테이블에 학생이 해당 학기를 담은 장바구니 내역 다 들고 오기
		// 1. 해당 학생 장바구니 내역에서 학수번호만 들고 오기
		ResultSet rs = st
				.executeQuery("select*from student" + stNum + " where Semester = '" + semester + "' AND state = 0;");

		return rs;
	}

	// 수강신청 취소 하기
	// S_ApplicationClass
	public void removeLe(String stNum, Lecture le) throws SQLException {
		// 취소하기 실행
		// 학생 개인 테이블에 해당 학수 번호를 가진 과목을 찾아 삭제
		String sql = "delete from student" + stNum + " where leNum = '" + le.getleNum() + "' AND state = 1;";
		// 명령어 실행
		st.executeUpdate(sql);

		// 전체 수업 내역에 있는 신청 학생 수 한명 늘이기
		String sql2 = "update lecture set attMem = " + (le.getAttMem() - 1) + " where leNum = '" + le.getleNum() + "';";

		// 명령어 실행
		st.executeUpdate(sql2);
	}

	// 장바구니 취소 하기
	// S_Bucket
	public void removeBucket(String stNum, String leNum) throws SQLException {
		// 취소하기 실행
		// 학생 개인 테이블에서 해당 학수 번호를 가진 과목을 찾아 삭제
		String sql = "delete from student" + stNum + " where leNum = '" + leNum + "' AND state = 0;";
		// 명령어 실행
		st.executeUpdate(sql);
	}

	// S_Graduation
	// 학생이 들어온 누계 학점 반환하는 함수
	public int getTotalCredit(String StuNum, String which) throws SQLException {
		int credit = 0;
		int totalcredit = 0;
		if (which.contentEquals("총학점")) {
			ResultSet rs = st.executeQuery("select*from lenum WHERE stNum = '" + StuNum + "';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		} else if (which.contentEquals("교양필수")) {
			ResultSet rs = st
					.executeQuery("select*from lenum WHERE stNum = '" + StuNum + "' AND division = '" + which + "';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		} else if (which.contentEquals("교양선택")) {
			ResultSet rs = st
					.executeQuery("select*from lenum WHERE stNum = '" + StuNum + "' AND division = '" + which + "';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		} else if (which.contentEquals("전공필수")) {
			ResultSet rs = st
					.executeQuery("select*from lenum WHERE stNum = '" + StuNum + "' AND division = '" + which + "';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		} else if (which.contentEquals("전공선택")) {
			ResultSet rs = st
					.executeQuery("select*from lenum WHERE stNum = '" + StuNum + "' AND division = '" + which + "';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		}
		return totalcredit;
	}

	// S_Graduation
	// 학생의 전공 총학점과 총 평점을 계산하는 함수
	public float getTotalGrade(String StuNum, String which) throws SQLException {
		float grade = 0;
		if (which.contentEquals("전공")) {
			ResultSet rs = st.executeQuery("select*from student WHERE stNum = '" + StuNum + "';");
			while (rs.next()) {
				grade = rs.getFloat(13);
			}
		} else {
			ResultSet rs = st.executeQuery("select*from student WHERE stNum = '" + StuNum + "';");
			while (rs.next()) {
				grade = rs.getFloat(15);
			}
		}
		return grade;
	}

	// S_GraduationCheck
	// S_showAllGrade
	public Student getStudent(String StuNum) throws SQLException {
		Student s = new Student();

		ResultSet rs = st.executeQuery("select*from student WHERE stNum = '" + StuNum + "' ;");
		while (rs.next()) {
			s.setstName(rs.getString(1));
			s.setstNum(rs.getString(2));
			s.setGrade(rs.getInt(3));
			s.setBirth(rs.getString(5));
			s.setfMajor(rs.getString(6));
			s.setCollege(rs.getString("college"));
			s.setfMajor(rs.getString("fMajor"));
			s.setPhoneNum(rs.getString(10));
			s.setEmail(rs.getString(11));
		}

		return s;
	}

	// S_showAllGrade
	public void setstScore(String stNum,  double allScoreAvg) throws SQLException {
		int cnt = getAllSubCnt(stNum);
		float totalMajor = getAllSoreMajor(stNum)/cnt;
		st.executeQuery("update student set totalMajor = "+totalMajor+", totalFinish = "+allScoreAvg+" WHERE stNum = '" + stNum + "' ;");
	}


	// S_showAllGrade
	// 전공 총 학점 구하기
	public float getAllSoreMajor(String stNum) throws SQLException {
		rs = st.executeQuery("select sum(scoreCredit) from leNum where stNum ='" + stNum + "' AND division = '전공필수';");
		rs.next();

		float allScoreSum1 = rs.getFloat(1);
		
		rs = st.executeQuery("select sum(scoreCredit) from leNum where stNum ='" + stNum + "' AND division = '전공선택';");
		rs.next();
		
		float allScoreSum2 = rs.getFloat(1);
		
		return allScoreSum1 + allScoreSum2;
	}
   
   //********************************************************아영***************************************************
   
   //**회원가입
   //학번 중복 확인
	public boolean checkId(String id) {
 			// 파라미터로 받아온 id을 검색한다.
 		try {
 			rs = st.executeQuery("select * from student where stNum ='" + id + "';");

 			while (rs.next()) {
 				if (rs.getString("stNum").equals(id))// 일치하는 이름이 있다면
 					return true;// true 반환
 			}
 			
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
		return false;
 	}
 	
	
 	//전화번호 중복 확인 메소드
 	public boolean checkPhoneNum(String phoneNum) {
 		try {
 			// 파라미터로 받아온 id을 검색한다.
 			rs = st.executeQuery("select * from student where phoneNum= '" + phoneNum + "';");
 			while (rs.next()) {
 				if (rs.getString("phoneNum").equals(phoneNum))// 일치하는 이름이 있다면
 					return true;// true 반환
 			}
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		return false;
 	}
 	
 	//계정 등록 메소드
 	public void add(String stNum, String pw, String birth, String email, String phoneNum)throws Exception{ 
 		try {
 			// 받아온 Account 객체 P를 db에 추가해 준다.
 			rs = st.executeQuery("update student set pw = '" + pw + "', birth = '" + birth + "', email = '" + email + 
 					"', phoneNum = '" + phoneNum + "' where stNum= '" + stNum +"';");

 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 	}
 	
 	
 	//**ID,PW 찾기
 	//ID 찾기
	public String findId(String email, String phoneNum) throws Exception {
		
		rs = st.executeQuery("select *from student where email ='" + email + "'and phoneNum='" + phoneNum + "';");
		while (rs.next()) {
			if (rs.getString("email").equals(email) && rs.getString("phoneNum").equals(phoneNum))// 일치하는 아이디(학번)이 있다면
				return rs.getString(2);// id 반환
		}
		return null;// 일치하는 계정 없음
	}
	
 	//PW 찾기
	public String findPw(String id, String email, String phoneNum) throws Exception {
		
		rs = st.executeQuery("select *from student where stNum = '" + id + "' and email ='" + email + "'and phoneNum='" + phoneNum + "';");
		while (rs.next()) {
			if (rs.getString("stNum").equals(id) && rs.getString("email").equals(email) && rs.getString("phoneNum").equals(phoneNum))// 일치하는 아이디(학번)이 있다면
				return rs.getString(4);// pw 반환
		}
		return null;// 일치하는 계정 없음
	}
 	
 	
 	//**학생 정보
    //정보 수정
    public void editInfo(String stNum, String phoneNum, String email) throws Exception {
 	   try {
 		   rs = st.executeQuery("update student set phoneNum = '" + phoneNum + "', email = '" + email + "' where stNum= '" + stNum + "';");
 	   } catch (SQLException e) {
           e.printStackTrace();
        }
 	   
    }
 	
 	
 	//*******신청
 	// 휴학 등록 메소드
    public void addLeave(String stName, String stNum, String apSemester, String startDate, String endDate, 
 		   String apReason, int abSemester, String apCount)throws Exception{ 
       try {
          // 받아온 부복수신청 내역을 DB에 저장
          rs = st.executeQuery("insert into schoolApplication values('"
                + stName + "','" + stNum + "','" + apSemester + "', '" + "휴학" + "','" + startDate + "','" 
                + endDate + "','" + apReason + "','" + abSemester + "','" + apCount + "', '" + "미승인" + "');");
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    
    // 복학 등록 메소드
    //S_Bokhak
    public void addGoBack(String stName, String stNum, String apSemester, String startDate, String endDate, String apReason, int abSemester, String apCount)throws Exception{ 
       try {
          // 받아온 부복수신청 내역을 DB에 저장
          rs = st.executeQuery("insert into schoolApplication values('"
                  + stName + "','" + stNum + "','" + apSemester + "', '" + "복학" + "','" + startDate + "','" 
                  + endDate + "','" + apReason + "','" + abSemester + "','" + apCount + "', '" + "미승인" + "');");
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    
    // 자퇴 등록 메소드
    //S_Jatuae
    public void addDrop(String stName, String stNum, String apSemester, String startDate, String endDate, int abSemester, String apReason, String apCount)throws Exception{ 
       try {
          // 받아온 부복수신청 내역을 DB에 저장
          rs = st.executeQuery("insert into schoolApplication values('"
                  + stName + "','" + stNum + "','" + apSemester + "', '" + "자퇴" + "','" + startDate + "','" 
                  + endDate + "','" + apReason + "','" + abSemester + "','" + apCount + "', '" + "미승인" + "');");
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
     
    
    // 부전공신청 등록 메소드
    //S_BuBokSu
    public void addMinor(String stName, String stNum, String apSemester, String apMajor, String apDate)throws Exception{ 
       try {
          // 받아온 부전공신청 내역을 DB에 저장
          rs = st.executeQuery("insert into majorApplication values('"
                + stName + "','" + stNum + "', '" + "부전공" + "','" + apSemester + "','"
                + apMajor + "','" + apDate + "','" + ""  + "', '" + "미승인" + "');");
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    
    // 복수전공신청 등록 메소드
    //S_BuBokSu
    public void addDouble(String stName, String stNum, String apSemester, String apMajor, String apDate)throws Exception{ 
        try {
           // 받아온 부전공신청 내역을 DB에 저장
           rs = st.executeQuery("insert into majorApplication values('"
                 + stName + "','" + stNum + "', '" + "복수전공" + "','" + apSemester + "','"
                 + apMajor + "','" + apDate + "','" + ""  + "', '" + "미승인" + "');");
        } catch (SQLException e) {
           e.printStackTrace();
        }
     }
       
    // 전과신청 등록 메소드
    //S_JunKua
    public void addChange(String stName, String stNum, String apSemester, String apMajor, String apReason, String apDate)throws Exception{ 
        try {
           // 받아온 부전공신청 내역을 DB에 저장
           rs = st.executeQuery("insert into majorApplication values('"
                 + stName + "','" + stNum + "', '" + "전과" + "','" + apSemester + "','"
                 + apMajor + "','" + apDate + "','" + apReason  + "', '" + "미승인" + "');");
        } catch (SQLException e) {
           e.printStackTrace();
        }
     } 
    
   
 	
   
 	//*********신청 조회
 	String stName, stNum, apSemester, classify, startDate, endDate, apReason, apCount, apState, apDate, apMajor;
 	int abSemester;
 	
 	// 학번으로 SchoolApplication 가져와 객체 생성
	public SchoolApplication getSa(String id, String now, String what) throws SQLException {
		// 입력한 학수번호를 가진 수업 내역 불러오기
		ResultSet rs = st.executeQuery("select * from schoolApplication where stNum = '" + id + "'and apCount = '" + now + "'and classify = '" + what + "';");
		while (rs.next()) {
			// 각각 메소드 값을 받아와서 할당함.
			stName = rs.getString(1);
			stNum = rs.getString(2);
			apSemester = rs.getString(3);
			classify = rs.getString(4);
			startDate = rs.getString(5);
			endDate = rs.getString(6);
			apReason = rs.getString(7);
			abSemester = rs.getInt(8);
			apCount = rs.getString(9);
			apState = rs.getString(10);
		}
		
		// SchoolApplication 객체 생성
		SchoolApplication sa = new SchoolApplication(stName, stNum, apSemester, classify, startDate, endDate, apReason, abSemester, apCount, apState);
		
		return sa;
	}
   
   //휴,복학, 자퇴 받아오기
   public ResultSet getSchoolA(String id) throws SQLException
   {
      //입력한 index값을 가진 데이터 보이는 명령어 실행
      ResultSet rs = st.executeQuery("select * from schoolApplication where stNum = '" + id + "';");
            
      return rs;
   }
   
   //휴학신청 받아오기
   public ResultSet getLeave(String id) throws SQLException
   {
      //입력한 index값을 가진 데이터 보이는 명령어 실행
      ResultSet rs = st.executeQuery("select * from schoolApplication where stNum = '" + id + "'and classify = '" + "휴학" + "';");
            
      return rs;
   }
   
   //복학신청 받아오기
   public ResultSet getGoBack(String id) throws SQLException
   {
      //입력한 index값을 가진 데이터 보이는 명령어 실행
      ResultSet rs = st.executeQuery("select * from schoolApplication where stNum = '" + id + "'and classify = '" + "복학" + "';");
            
      return rs;
   }
   
   //자퇴신청 받아오기
   public ResultSet getDrop(String id) throws SQLException
   {
      //입력한 index값을 가진 데이터 보이는 명령어 실행
      ResultSet rs = st.executeQuery("select * from schoolApplication where stNum = '" + id + "'and classify = '" + "자퇴" + "';");
            
      return rs;
   }
   
	// 학번으로 MajorApplication 가져와 객체 생성
	public MajorApplication getMa(String id, String now, String what) throws SQLException {
		// 입력한 학수번호를 가진 수업 내역 불러오기
		ResultSet rs = st.executeQuery("select * from majorApplication where stNum = '" + id + "'and apDate = '" + now + "'and classify = '" + what + "';");
		while (rs.next()) {
			// 각각 메소드 값을 받아와서 할당함.
			stName = rs.getString(1);
			stNum = rs.getString(2);
			classify = rs.getString(3);
			apSemester = rs.getString(4);
			apMajor = rs.getString(5);
			apDate = rs.getString(6);
			apReason = rs.getString(7);
			apState = rs.getString(8);
		}
		
		// MajorApplication 객체 생성
		MajorApplication ma = new MajorApplication(stName, stNum, classify, apSemester, apMajor, apDate, apReason, apState);
		
		return ma;
	}
	
	//부, 복수, 전과 받아오기
	public ResultSet getMajorA(String id) throws SQLException
	{
		//입력한 index값을 가진 데이터 보이는 명령어 실행
	    ResultSet rs = st.executeQuery("select * from majorApplication where stNum = '" + id + "';");
	            
	    return rs;
	}
   
	//부, 복수전공 신청 받아오기
	public ResultSet getMinDouble(String id) throws SQLException
	{
		//입력한 index값을 가진 데이터 보이는 명령어 실행
	    ResultSet rs = st.executeQuery("select * from majorApplication where stNum = '" + id + "'and classify = '" + "부전공" + "'or classify = '" + "복수전공" + "';");
	            
	    return rs;
	}
	
	//전과 신청 받아오기
	public ResultSet getChange(String id) throws SQLException
	{
		//입력한 index값을 가진 데이터 보이는 명령어 실행
		ResultSet rs = st.executeQuery("select * from majorApplication where stNum = '" + id + "'and classify = '" + "전과" + "';");
		            
		return rs;
	}
   
   //********************************************************지연***************************************************
// 로그인하기(학생.ver) -> 교수와 동일
	public boolean loginSt(String id, String pw) throws Exception {
		String checkIdMsg = "select *from student where stNum ='" + id + "'and pw='" + pw + "'"; // 아이디와 비번 입력하기

		rs = st.executeQuery(checkIdMsg);
		while (rs.next()) {
			if (rs.getString("stNum").equals(id))// 일치하는 아이디(학번)이 있다면
				return true;// true 반환
		}

		return false;// 일치하는 계정 없음
	}

	// ******************************** S_ShowRecentGrade()
	// *******************************
	/* 수강학기 중에 막학기(max값을) 열(col)을 찾아서 막학기 데이터 해당 행(row) 다 출력 */
	public String getMaxYear(String stNum) throws SQLException {
		String searchMaxYear = "select max(semester) from leNum where stNum = '" + stNum + "';";
		rs = st.executeQuery(searchMaxYear);
		rs.last();
		String maxYear = rs.getString(1);
		return maxYear;
	}

	// 금학기 성적조회
	public List<LectureScore> showRecentGrade(String stNum, String maxYear) throws Exception {
		String searchWithMsg = "select * from leNum where semester = '" + maxYear + "'AND stNum = '" + stNum + "';";

		rs = st.executeQuery(searchWithMsg);
		List<LectureScore> score = new ArrayList<LectureScore>();

		while (rs.next()) {
			String getLeName = rs.getString("leName"); // 과목명
			String getStName = rs.getString("stName");
			int getCredit = rs.getInt("credit"); // 이수학점
			String getStNum = rs.getString("stNum"); // 학번
			int getMidScore = rs.getInt("midScore"); // 중간고사
			int getFinScore = rs.getInt("finScore"); // 기말고사
			int getAssiScore = rs.getInt("assiScore"); // 과제
			int getAttendScore = rs.getInt("attendScore"); // 출석
			int getTotalScore = rs.getInt("totalScore"); // 총점
			String getGrade = rs.getString("grade"); // 등급

			// Person 객체 p를 생성해주어 문자 포함 데이터들의 행 값들을 넣어줌
			LectureScore s = new LectureScore(getLeName, getStName, getCredit, getStNum, getMidScore, getAssiScore,
					getFinScore, getAttendScore, getTotalScore, getGrade);
			score.add(s);
		}
		return score;
	}

	// ******************************** S_ShowAllGrade()
	// *******************************
	// 해당 학기 성적조회
	public List<LectureScore> showGrade(String stNum, String semester) throws Exception {
		String searchWithMsg = "select *from leNum where stNum ='" + stNum + "' AND semester ='" + semester + "';";
		rs = st.executeQuery(searchWithMsg);
		List<LectureScore> score = new ArrayList<LectureScore>();

		while (rs.next()) {
			String getLeNum = rs.getString("leNum"); // 학수번호
			String getLeName = rs.getString("leName"); // 과목명
			String getDivision = rs.getString("division"); // 이수구분
			int getCredit = rs.getInt("credit"); // 이수학점
			String getTime = rs.getString("time");// 강의시간
			String getprName = rs.getString("professorName");// 교수명
			int getTotalScore = rs.getInt("totalScore"); // 총점
			float getScoreCredit = rs.getFloat("scoreCredit");
			String getGrade = rs.getString("grade"); // 등급

			// Person 객체 p를 생성해주어 문자 포함 데이터들의 행 값들을 넣어줌
			LectureScore s = new LectureScore(getLeNum, getLeName, getDivision, getCredit, getTime, getprName,
					getTotalScore, getScoreCredit, getGrade);
			score.add(s);
		}
		return score;
	}

	// 전체 수강과목 갯수 구하기
	public int getAllSubCnt(String stNum) throws SQLException {
		String getSubCnt = "select count(*) from leNum where stNum='" + stNum + "';";
		rs = st.executeQuery(getSubCnt);

		rs.next();
		int allSubCnt = rs.getInt(1);

		return allSubCnt;
	}

	// 총 이수 학점
	public int getAllCreditCnt(String stNum) throws SQLException {
		String getCreditSum = "select sum(credit) from leNum where stNum ='" + stNum + "';";

		rs = st.executeQuery(getCreditSum);

		rs.next();// 커서 이동
		int allCreditCnt = rs.getInt(1); // 갯수 읽기

		return allCreditCnt;// 갯수 반환
	}

	// 총 전공 학점
	public int getAllMCredit(String stNum) throws SQLException {
		String getMCreditSum = "select sum(credit) from leNum where (division = '전공필수' or division = '전공선택') and stNum ='"
				+ stNum + "';";

		rs = st.executeQuery(getMCreditSum);
		rs.next();

		int allMCredit = rs.getInt(1);

		return allMCredit;
	}

	// 학기 총 평점 구하기
	public double getAllScoreAvg(String stNum) throws SQLException {
		String getAllSum = "select sum(scoreCredit) from leNum where stNum ='" + stNum + "';";
		rs = st.executeQuery(getAllSum);
		rs.next();

		double allScoreSum = rs.getDouble(1);
		return allScoreSum;
	}

	// 데베 닫기
	public void dbClose() throws Exception {
		rs.close();
		st.close();
		con.close();
	}

}