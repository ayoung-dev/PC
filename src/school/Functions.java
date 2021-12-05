package school;

import java.io.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Functions {
   Connection conn = null;
   Statement st = null;
   ResultSet rs = null;
   int num; //개수를 받는데 쓰임
   P_Management mg;
   
   public Functions(String s, Connection con) throws SQLException {
	// TODO Auto-generated constructor stub
		con = DriverManager.getConnection(s, "root", "1234");
		st = con.createStatement();
		mg = new P_Management(s, con);
}

// 교수이름으로 전공 찾기
   public String searchpMajor(String Name) throws Exception{
      rs = st.executeQuery("select * from professor where Name = '" + Name + "'");
      while (rs.next()) {
         String major = rs.getString("Department"); 
         return major;
      }
      throw new Exception("등록된 교수 없음");
   }
   
   // 학생이름으로 전공 찾기
      public String searchsMajor(Student S) throws Exception{
         rs = st.executeQuery("select * from student where stName = '" + S.getstName() + "'");
         while (rs.next()) {
            String major = rs.getString("fMajor"); 
            return major;
         }
         throw new Exception("등록된 학생 없음");
      }
   
   // 수업 이름으로 수업 이수구분 찾기
   public String searchDivision(Lecture LE) throws Exception{
      rs = st.executeQuery("select * from lecture where leName = '" + LE.getleName() + "'");
      while (rs.next()) {
         String division = rs.getString("division"); 
         return division;
      }
      throw new Exception("등록된 강의 없음");
   }
      
   // 학수번호 함수
   //P_AddLecture
   public String makeLeNum(Lecture L, String name)throws Exception{ 
	   String semester = "2021-1";
      String leNum = "";
      int leNum1 = 0000;
      int leNumMT = mg.getLectureCount("소프트웨어융합학과", semester);
      int leNumKL = mg.getLectureCount("국어국문학과", semester);
      int leNumEL = mg.getLectureCount("영어영문학과", semester);
      int leNumEC = mg.getLectureCount("경제학과", semester);
      int leNumCH = mg.getLectureCount("화학과", semester);
      int leNumAA = mg.getLectureCount("그 외", semester);
      
      //최종적으로 우리가 만들 학수번호
      String rleNum;
      
         if (searchpMajor(name).equals("소프트웨어융합학과")) {
            leNum = "MT";
            rleNum = leNum + leNumMT;
         } else if (searchpMajor(name).equals("국어국문학과")) {
            leNum = "KL";
            rleNum = leNum + leNumKL;
         } else if (searchpMajor(name).equals("영어영문학과")) {
            leNum = "EL";
            rleNum = leNum + leNumEL;
         }  else if (searchpMajor(name).equals("경제학과")) {
            leNum = "EC";
            rleNum = leNum + leNumEC;
         }  else if (searchpMajor(name).equals("화학과")) {
            leNum = "CH";
            rleNum = leNum + leNumCH;
         } else {
            leNum = "AA";
            rleNum = leNum + leNumAA;
         }
         


         // 학수번호 DB 저장 
         //st.executeUpdate("update lecture set leNum:='" + rleNum + "' where leName = '" + L.getleName() + "';");

     	return rleNum;
   }
   
   // 학번 생성하는 함수
   public void makeStNum(Student S)throws Exception{
      int mtNum = 0001, klNum = 1111, elNum = 2222, ecNum = 3333, chNum = 4444, aaNum = 5555;
      int stNum = 0;
      
      SimpleDateFormat sdf = new SimpleDateFormat("yy");
      Calendar cal = Calendar.getInstance();
      String yearstr = sdf.format(cal.getTime());
      int year = Integer.parseInt(yearstr);
      System.out.println(year);
      try {
         if (searchsMajor(S) == "소프트웨어융합학과") {
            stNum = mtNum;
            mtNum++;
         } else if (searchsMajor(S) == "국어국문학과") {
            stNum = klNum;
            mtNum++;
         } else if (searchsMajor(S) == "영어영문학과") {
            stNum = elNum;
            mtNum++;
         } else if (searchsMajor(S) == "경제학과") {
            stNum = ecNum;
            mtNum++;
         } else if (searchsMajor(S) == "화학과") {
            stNum = chNum;
            mtNum++;
         } else {
            stNum = aaNum;
            mtNum++;
         }
         
         int num = year + stNum;
         
         // 학번 DB 저장 
         st.executeUpdate("update student set stNum:='" + num + "' where stName = '" + S.getstName() + "';");
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}