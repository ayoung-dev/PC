package school;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class P_Management {

   private Connection conn = null;
   public Statement st;
   public ResultSet rs;
   String leName,leNum,semester, prName, room, type, time, preLecture, division, state;
   int credit, attMem, limMem, limGrade;
   
   //********************************************************수영***************************************************
   public P_Management(String s, Connection con) throws SQLException{

		con = DriverManager.getConnection(s, "root", "1234");
		st = con.createStatement();
	  }
   //강의 개설 - 해당 데이터 테이블에 생성하기 및 조회
   //테이블에 생성하기
   //P_AddLecture
   public void InsertLecture(Lecture le) throws SQLException
   {
      
      //해당 테이블에 값을 삽입하는 sql문 작성
      String sql = "insert into lecture values ('" + le.getleName() +"', '"+  le.getleNum()+"', '"+ le.getSemester()+"', "+ le.getLimGrade() + ","
            + "'"+le.getprName()+"', '"+le.getType()+"', '"+le.getTime()+"', "+le.getCredit()+", "+le.getAttMem()+""
                  + ", "+le.getLimMem()+", '"+le.getPreLecture()+"', '"+le.getDivision()+"', '"+le.getState()+"');";
         
         
      //명령어 실행
      st.executeUpdate(sql);
   }
   
	//P_Home
	public Professor getProfessor(String id) throws SQLException {
		Professor p = new Professor();

		ResultSet rs = st.executeQuery("select*from professor where Number = '"+id+"';");
		while (rs.next()) {
			p.setprName(rs.getString(1));
			p.setprNum(rs.getString(2));
			p.setBirth(rs.getInt(4));
			p.setCollege(rs.getString("college"));
			p.setMajor(rs.getString("department"));
			p.setPhoneNum(rs.getString(7));
			p.setEmail(rs.getString(8));
		}

		return p;
	}
	
	
   
   //과목 내용 받아오기
   //P_AddLecture
   public ResultSet getLecture(String name) throws SQLException
   {
	   //이름이 공백이 아니라면
	   if(!name.equals(""))
	   {
		   //해당 교수의 이름을 가진 사람 받아오기
		   //입력한 index값을 가진 데이터 보이는 명령어 실행
		      ResultSet rs = st.executeQuery("select*from lecture where Prof = '"+name+"';");

		      return rs;

	   }
	   //공백이라면 모든 정보 받아오기
	   else {
		   ResultSet rs = st.executeQuery("select*from lecture;");
		      return rs;
	   }
   }
   
   //과목 내용 받아오기
   //P_ShowLecture
   public ResultSet searchLecture(String name, String a, String b, String semester) throws SQLException
   {
	   //name에서 받는 정보에 따라 교과목, 학부, 시간대별로 검색하는 내용이 달라짐
	   //이름이 공백이 아니라면
	   if(name.equals("학부"))
	   {
		   //해당 교수의 이름을 가진 사람 받아오기
		   //입력한 index값을 가진 데이터 보이는 명령어 실행
		   //첫번째 인자 - 이수구분, 두번째 인자 - 전공
		   rs = st.executeQuery("select*from lecture where Division = '"+a+"' and Semester = '"+semester+"' and Number like '"+b+"%';");

	   }
	   else if(name.equals("시간")){
		   //첫번째 인자 - 요일, 두번쨰 인자 - 자세한 시간 ex)1-2 형식
		   rs = st.executeQuery("select*from lecture where Time = '"+a+b+"' and Semester = '"+semester+"';");
		   System.out.println("select*from lecture where Time = '"+a+b+"' and Semester = '"+semester+"';");
	   }
	   else if(name.equals("교과목")){
		   //첫번째 인자 - 검색하는 내용이 교과목인지 학수번호인지 알려줌, 두번쨰 인자 - 검색하는 내용
		   if(a.equals("과목명"))
		   {
			   //교과목명일 경우
			   rs = st.executeQuery("select*from lecture where Name = '"+b+"' and Semester = '"+semester+"';");
		   }
		   else {
			   //학수번호일 경우
			   rs = st.executeQuery("select*from lecture where Number = '"+b+"' and Semester = '"+semester+"';");
		   }
		  
	   }
	   else if(!semester.contentEquals("")){
		   //학부, 시간, 과목명 없는 경우 -> P_ShowProLecture에서 해당 교수가 개설한 과목 조회하기
		   rs = st.executeQuery("select*from lecture where Prof = '"+b+"' and Semester = '"+semester+"';");
		   
	   }else {
		   //학부, 시간, 과목명 없는 경우 -> P_ShowProLecture에서 해당 교수가 개설한 과목 조회하기
		   rs = st.executeQuery("select*from lecture where Prof = '"+b+"';");
	   }

	  return rs;
   }
   

   //P_AddLecture
   public int getLectureCount(String s, String semester) throws SQLException
   {
	   String leNum = null;   
       if (s.equals("소프트웨어융합학과")) {
           leNum = "MT";
        } else if (s.equals("국어국문학과")) {
           leNum = "KL";
        } else if (s.equals("영어영문학과")) {
           leNum = "EL";
        }  else if (s.equals("경제학과")) {
           leNum = "EC";
        }  else if (s.equals("화학과")) {
           leNum = "CH";
        } else {
           leNum = "AA";
        }
		   rs = st.executeQuery("select count(*)from lecture where Semester = '"+semester+"' and Number like '"+leNum+"%';");
			//행 개수 읽어서 num 변수에 저장
		   /*
			int num = 0;
		   while(rs.next())
		      {
			   	num++;
		      }
		    */

		      
		      rs.next();// 커서 이동
		      int perCnt = rs.getInt(1); // 갯수 읽기


			rs.close();
			
			return perCnt;
   }
   
   //학수 번호 입력하면 해당 데이터 들고오기
   //P_ShowLecture
   public Lecture getLe(String leNum) throws SQLException
   {
      //입력한 학수번호를 가진 수업 내역 불러오기
      ResultSet rs = st.executeQuery("select*from lecture where Number = '"+leNum+"';");
         
      while(rs.next())
      {
         //각각 메소드 값을 받아와서 할당함.
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
      //수업 객체 생성
      Lecture le = new Lecture(leName, leNum, semester, limGrade, prName, type, time, credit, attMem, limMem, preLecture, division, state);
      return le;
   }
   
   //해당 학생의 해당 학기에 수강한 내역 불러오기
   //P_Graduation
   public ResultSet getStClasses(String stNum, String semester) throws SQLException
   {
      //학생 개인 테이블에서 입력한 학기 정보를 가진 수업 내역 불러오기
      ResultSet rs = st.executeQuery("select*from student"+stNum+" WHERE semester = '"+semester+"' AND state = 1;");
      return rs;
   }
   
   //수업 조회하는 기능
   //전체 수업에서 해당 학기에 해당 되는 내용 불러오기
   public ResultSet getClasses(String semester, String username) throws SQLException
   {
	   //교수가 해당 학기에 열었던 수업을 조회한다면?
	   if(!username.equals(""))
	   {
		   //입력한 index값을 가진 데이터 보이는 명령어 실행
		      ResultSet rs = st.executeQuery("select*from Lecture WHERE semester = '"+semester+"' AND Prof = '"+username+"';");

		      return rs;

	   }
	   else {
		      //전체 과목 테이블에서 입력한 학기 정보를 가진 수업 내역 불러오기
		      ResultSet rs = st.executeQuery("select*from Lecture WHERE semester = '"+semester+"';");
		      return rs;
	   }

   }
   
	//P_GraduationCheck
	public ResultSet getStudent(String major) throws SQLException
	{	
		ResultSet rs = st.executeQuery(
				"select*from student WHERE fMajor = '" + major + "' ;");
		
		return rs;
	}
   
	//P_GraduationCheck
	//학생이 들어온 누계 학점 반환하는 함수
	public int getTotalCredit(String StuNum, String which) throws SQLException
	{
		int credit = 0;
		int totalcredit = 0;
		if(which.contentEquals("총학점"))
		{
			ResultSet rs = st.executeQuery(
					"select*from lenum WHERE stNum = '" + StuNum + "';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		}else if(which.contentEquals("교양필수"))
		{
			ResultSet rs = st.executeQuery(
					"select*from lenum WHERE stNum = '" + StuNum + "' AND division = '"+which+"';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		}else if(which.contentEquals("교양선택"))
		{
			ResultSet rs = st.executeQuery(
					"select*from lenum WHERE stNum = '" + StuNum + "' AND division = '"+which+"';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		}else if(which.contentEquals("전공필수"))
		{
			ResultSet rs = st.executeQuery(
					"select*from lenum WHERE stNum = '" + StuNum + "' AND division = '"+which+"';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		}else if(which.contentEquals("전공선택"))
		{
			ResultSet rs = st.executeQuery(
					"select*from lenum WHERE stNum = '" + StuNum + "' AND division = '"+which+"';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		}
		return totalcredit;
	}
	
	//P_Graduation
	//학생의 전공 총학점과 총 평점을 계산하는 함수
	public float getTotalGrade(String StuNum, String which) throws SQLException
	{
		float grade = 0;
		if(which.contentEquals("전공"))
		{
			ResultSet rs = st.executeQuery(
					"select*from student WHERE stNum = '" + StuNum + "';");
			while (rs.next()) {
				grade = rs.getFloat(13);
			}
		}else
		{
			ResultSet rs = st.executeQuery(
					"select*from student WHERE stNum = '" + StuNum + "';");
			while (rs.next()) {
				grade = rs.getFloat(14);
			}
		}
		return grade;
	}
	   
   //********************************************************아영***************************************************
   

   //**회원가입
   //교번 중복 확인
	public boolean checkId(String id) {
			// 파라미터로 받아온 id을 검색한다.
		try {
			rs = st.executeQuery("select * from professor where Number ='" + id + "';");

			while (rs.next()) {
				if (rs.getString("Number").equals(id))// 일치하는 이름이 있다면
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
 			rs = st.executeQuery("select * from professor where Phone = '" + phoneNum + "';");
 			while (rs.next()) {
 				if (rs.getString("Phone").equals(phoneNum))// 일치하는 이름이 있다면
 					return true;// true 반환
 			}
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		return false;
 	}
 	
 	//계정 등록 메소드
 	 	public void add(String prNum, String pw, String birth, String email, String phoneNum)throws Exception{ 
 	 		try {
 	 			// 받아온 Account 객체 P를 db에 추가해 준다.
 	 			rs = st.executeQuery("update professor set pw = '" + pw + "', birth = '" + birth + "', email = '" + email + "', phone = '" + phoneNum + "' where Number= '" + prNum +"';");

 	 		} catch (SQLException e) {
 	 			e.printStackTrace();
 	 		}
 	 	}
 	
 	 	
 	//**ID,PW 찾기
 	//ID 찾기
 	public String findId(String email, String phoneNum) throws Exception {
 			
 		rs = st.executeQuery("select *from professor where email ='" + email + "'and phone='" + phoneNum + "';");
 		while (rs.next()) {
 			if (rs.getString("email").equals(email) && rs.getString("phone").equals(phoneNum))// 일치하는 아이디(학번)이 있다면
 				return rs.getString(2);// id 반환
 		}
 		return null;// 일치하는 계정 없음
 	}
 	
 	//PW 찾기
	public String findPw(String id, String email, String phoneNum) throws Exception {
		
		rs = st.executeQuery("select *from professor where number = '" + id + "' and email ='" + email + "'and phone='" + phoneNum + "';");
		while (rs.next()) {
			if (rs.getString("number").equals(id) && rs.getString("email").equals(email) && rs.getString("phone").equals(phoneNum))// 일치하는 아이디(학번)이 있다면
				return rs.getString(3);// pw 반환
		}
		return null;// 일치하는 계정 없음
	}
	
	
	
 	//**교수 정보
    //정보 수정
    public void editInfo(String prNum, String phoneNum, String email) throws Exception {
 	   try {
 		   rs = st.executeQuery("update professor set phoneNum = '" + phoneNum + "', email = '" + email + "' where Number= '" + prNum + "';");
 	   } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    
    //수업 전체 수업
    public ResultSet getAllLe(String name) throws SQLException
    {
    	ResultSet rs = st.executeQuery("select * from Lecture where Prof = '" + name + "';");
 		return rs;
 	 
    }
   
	
	//*********신청 조회
	//휴,복학, 자퇴 받아오기
	public ResultSet getSchoolA() throws SQLException
	{
	   //입력한 index값을 가진 데이터 보이는 명령어 실행
	   ResultSet rs = st.executeQuery("select * from schoolApplication;");
	            
	   return rs;
	}
	   
	//휴학신청 받아오기
	public ResultSet getLeave() throws SQLException
	{
	   //입력한 index값을 가진 데이터 보이는 명령어 실행
	   ResultSet rs = st.executeQuery("select * from schoolApplication where classify = '" + "휴학" + "';");
	            
	   return rs;
	}
	
	
	//복학신청 받아오기
	public ResultSet getGoBack() throws SQLException
	{
	   //입력한 index값을 가진 데이터 보이는 명령어 실행
	   ResultSet rs = st.executeQuery("select * from schoolApplication where classify = '" + "복학" + "';");
	            
	   return rs;
	}
	
	
	//자퇴신청 받아오기
	public ResultSet getDrop() throws SQLException
	{
	   //입력한 index값을 가진 데이터 보이는 명령어 실행
	   ResultSet rs = st.executeQuery("select * from schoolApplication where classify = '" + "자퇴" + "';");
	            
	   return rs;
	}
	
	//부, 복수, 전과 받아오기
	public ResultSet getMajorA() throws SQLException
	{
		//입력한 index값을 가진 데이터 보이는 명령어 실행
		ResultSet rs = st.executeQuery("select * from majorApplication;");
		            
		return rs;
	}
	   
	//부, 복수전공 신청 받아오기
	public ResultSet getMinDouble() throws SQLException
	{
		//입력한 index값을 가진 데이터 보이는 명령어 실행
		ResultSet rs = st.executeQuery("select * from majorApplication where classify = '" + "부전공" + "'or classify = '" + "복수전공" + "';");
		            
		return rs;
	}
	
	//전과 신청 받아오기
	public ResultSet getChange() throws SQLException
	{
		//입력한 index값을 가진 데이터 보이는 명령어 실행
		ResultSet rs = st.executeQuery("select * from majorApplication where classify = '" + "전과"  + "';");
			            
		return rs;
	}
	

   
   //**승인
   // 휴학 승인 메소드
   //P_Huhak
   public void acceptLeave(String id, String now) throws Exception {
  	 try {
  		 rs = st.executeQuery("update schoolapplication set apState = '" + "승인" + "' where stNum= '" + id
 			   + "'and apCount = '" + now + "'and classify = '" + "휴학" + "';");
  	 } catch (SQLException e) {
          e.printStackTrace();
     }
   }
   
   // 복학 승인 메소드
   //P_Bokhak
   public void acceptGoBack(String id, String now) throws Exception {
	 try {
		 rs = st.executeQuery("update schoolapplication set apState = '" + "승인" + "' where stNum= '" + id
	 			  + "'and apCount = '" + now + "'and classify = '" + "복학" + "';");
	 } catch (SQLException e) {
	        e.printStackTrace();
	 }
   }
   
   // 자퇴 승인 메소드
   //P_Jatuae
   public void acceptDrop(String id, String now) throws Exception {
	 try {
		 rs = st.executeQuery("update schoolapplication set apState = '" + "승인" + "' where stNum= '" + id
	 			   + "'and apCount = '" + now + "'and classify = '" + "자퇴" + "';");
	  } catch (SQLException e) {
	        e.printStackTrace();
	  }
   }
    
   // 부복수전공신청 승인 메소드
   //P_BuBoksu
   public void acceptMinDouble(String id, String now) throws Exception {
	 try {
	  	rs = st.executeQuery("update majorapplication set apState = '" + "승인" + "' where stNum= '" + id
	 		   + "'and apDate = '" + now + "'and (classify = '" + "부전공" + "'or classify = '" + "복수전공" + "');");
	 } catch (SQLException e) {
	       e.printStackTrace();
	 }
   }
   

   // 전과신청 승인 메소드
   //P_JunKua
   public void acceptChange(String id, String now) throws Exception {
	 try {
		 rs = st.executeQuery("update majorapplication set apState = '" + "승인" + "' where stNum= '" + id
	 	   + "'and apDate = '" + now + "'and classify = '" + "전과" + "';");
	 } catch (SQLException e) {
	     e.printStackTrace();
	 }
   }
   
   
   //**********신청 삭제/취소
   // 휴학 삭제/취소 메소드
   public void deleteLeave(String id, String now) throws Exception {
      st.executeUpdate("delete from schoolApplication where stNum = '" + id  + "'and apCount = '" + now + "'and classify = '" + "휴학" + "';");
   }
   
   // 복학 삭제/취소 메소드
   public void deleteGoBack(String id, String now) throws Exception {
      st.executeUpdate("delete from schoolApplication where stNum = '" + id  + "'and apCount = '" + now + "'and classify = '" + "복학" + "';");
   }
      
      
   // 자퇴 삭제/취소 메소드
   public void deleteDrop(String id, String now) throws Exception {
      st.executeUpdate("delete from schoolApplication where stNum = '" + id  + "'and apCount = '" + now + "'and classify = '" + "자퇴" + "';");
   }
  
   // 부복수전공 신청 삭제/취소 메소드
   public void deleteMinDouble(String id, String now) throws Exception {
      st.executeUpdate("delete from majorApplication where stNum = '" + id  + "'and apDate = '" + now + "'and (classify = '" + "부전공" + "'or classify = '" + "복수전공" + "');");
   }
   
   // 전과신청 삭제/취소 메소드
   public void deleteChange(String id, String now) throws Exception {
      st.executeUpdate("delete from majorApplication where stNum = '" + id  + "'and apDate = '" + now + "'and classify = '" + "전과" + "';");
   }
   
   
      
// ********************************************************지연***************************************************

// 로그인하기(교수.ver)
	/*
	 * 교번과 비밀번호를 UI에서 동시에 받아온 후(=아이디 비밀번호 일치여부확인) 교번(id)의 존재가 db에서 확인되면 true를
	 * 반환한다.(=로그인 성공!)
	 */
	public boolean loginPr(String id, String pw) throws Exception {
		String checkIdMsg = "select *from professor where Number ='" + id + "'and pw='" + pw + "'"; // 아이디와 비번 입력하기

		rs = st.executeQuery(checkIdMsg);
		while (rs.next()) {
			if (rs.getString("Number").equals(id))// 일치하는 아이디(교번)이 있다면
				return true;// true 반환
		}

		return false;// 일치하는 계정 없음
	}

	// 로그인한 교수의 이름 데이터 받아오기
	public String checkNamePr(String id) throws Exception {
		String checkNameMsg = "select *from professor"; // table 전체 출력 SQL문

		rs = st.executeQuery(checkNameMsg);
		while (rs.next()) {
			if (rs.getString("Number").equals(id))// 일치하는 이름이 있다면
				return rs.getString("Name");// true 반환
		}

		return null;// 일치하는 이름 없음
	}

	 //******************************** P_Grade() *******************************	
	// 담당교수(prName)의 수업학기(semester)에서 '과목명' 받아오기
	public List<String> getLeInSem(String prName, String semester) throws Exception {
		String getTable = "select *from lecture where semester ='" + semester + "';";
		rs = st.executeQuery(getTable);

		// 과목의 중복 출력을 방지하기 위해 '집합'에 넣었다가 다시 '리스트'로 변환!
		List<String> lectures = new ArrayList<String>();
		HashSet<String> lectures2 = new HashSet<String>();
		while (rs.next()) {
			if (rs.getString("Prof").equals(prName)) {
				String getLeName = rs.getString("Name"); // 과목명
				lectures.add(getLeName);
			}
		}

		for (String item : lectures) {
			lectures2.add(item);
		}

		List<String> lectures3 = new ArrayList<String>(lectures2);
		return lectures3;
	}

	// 성적 데이터 불러오기 -> 학기로 받아오기 
	/* 학기를 매개변수로 받아와서 해당학기의 성적데이터를 출력한다. */
	public List<LectureScore> showGrade(String semester, String leNum) throws Exception {
		String searchWithMsg = "select *from leNum where leNum ='" +leNum + "' AND semester ='" + semester + "';";
		rs = st.executeQuery(searchWithMsg);
		List<LectureScore> score = new ArrayList<LectureScore>();

		while (rs.next()) {
			String getLeName = rs.getString("leName"); // 과목명
			String getStName = rs.getString("stName"); // 이름
			String getStNum = rs.getString("stNum"); // 학번
			int getMidScore = rs.getInt("midScore"); // 중간고사
			int getFinScore = rs.getInt("finScore"); // 기말고사
			int getAssiScore = rs.getInt("assiScore"); // 과제
			int getAttendScore = rs.getInt("attendScore"); // 출석
			int getTotalScore = rs.getInt("totalScore"); // 총점
			String getGrade = rs.getString("grade"); // 등급

			// Person 객체 p를 생성해주어 문자 포함 데이터들의 행 값들을 넣어줌
			LectureScore s = new LectureScore(getLeName, getStName, getStNum, getMidScore, getAssiScore, getFinScore,
					getAttendScore, getTotalScore, getGrade);
			score.add(s);
		}
		return score;
	}

	// 성적 수정
	public void modifyScore(String stNum, String leNum, LectureScore S) throws Exception {
		// table 정보 수정
		if(S.getGrade().contentEquals("A+"))
		{
			S.setScoreCredit(4.5f);
		}else if(S.getGrade().contentEquals("A0")){
			S.setScoreCredit(4.0f);
		}else if(S.getGrade().contentEquals("B+")){
			S.setScoreCredit(3.5f);
		}else if(S.getGrade().contentEquals("B0")){
			S.setScoreCredit(3.0f);
		}else if(S.getGrade().contentEquals("C+")){
			S.setScoreCredit(2.5f);
		}else if(S.getGrade().contentEquals("C0")){
			S.setScoreCredit(2.0f);
		}
		
		st.executeUpdate("update leNum set midScore='" + S.getMidScore() + "', " + "assiScore='" + S.getAssiScore()
				+ "', " + "finScore='" + S.getFinScore() + "', " + "totalScore='" + S.getTotalScore() + "'," + "grade='"
				+ S.getGrade() + "'," + "attendScore='" + S.getAttendScore() + "', scoreCredit = '"+S.getScoreCredit()+"'" + " where stNum='" + stNum
				+ "' AND leNum = '" + leNum + "';");
	}

	// 수강인원 구하기
	/* 과목이름이 OO인 행의 값이 몇개인지 갯수 세기 */
	public int getCount(String leNum) throws SQLException {
		String getCountMsg = "select count(*) from leNum where leNum ='" + leNum + "';";

		rs = st.executeQuery(getCountMsg);

		rs.next();// 커서 이동
		int perCnt = rs.getInt(1); // 갯수 읽기

		return perCnt;// 갯수 반환
	}

	// 학생 평균 구하기 메소드(합)
	/* 과목이름이 OO인 점수 행의 값 다 더하기 */
	public int stLecture(String semester, String leNum) throws Exception {
		String getCountMsg = "select sum(totalScore) from leNum where leNum ='" +leNum  + "';";
		rs = st.executeQuery(getCountMsg);
		rs.last();
		int allTotal = rs.getInt(1);

		return allTotal;
	}

	   
	// 과목명 넣기
	public void setLeName(String leName) {
		this.leName = leName;
	}

	// 과목명 빼기
	public String getLeName() {
		return leName;
	}

	// 데베 닫기
	public void dbClose() throws Exception {
		rs.close();
		st.close();
		conn.close();
	}
}