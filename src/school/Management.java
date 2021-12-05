package school;

import java.io.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Management {
   Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null;
   public Statement st;
   int num; //개수를 받는데 쓰임
   
   public Management(String url, Connection con) throws Exception {//UI에서 생성할 사람의 수 가져와서 객체 생성
	      st = con.createStatement();
   }
   
   // 교수이름으로 전공 찾기
   public String searchpMajor(Lecture LE) throws Exception{
      rs = stmt.executeQuery("select * from professor where prName = '" + LE.getprName() + "'");
      while (rs.next()) {
         String major = rs.getString("major"); 
         return major;
      }
      throw new Exception("등록된 교수 없음");
   }
   
   // 학생이름으로 전공 찾기
      public String searchsMajor(Student S) throws Exception{
         rs = stmt.executeQuery("select * from student where stName = '" + S.getstName() + "'");
         while (rs.next()) {
            String major = rs.getString("fMajor"); 
            return major;
         }
         throw new Exception("등록된 학생 없음");
      }
   
   // 수업 이름으로 수업 이수구분 찾기
   public String searchDivision(Lecture LE) throws Exception{
      rs = stmt.executeQuery("select * from lecture where leName = '" + LE.getleName() + "'");
      while (rs.next()) {
         String division = rs.getString("division"); 
         return division;
      }
      throw new Exception("등록된 강의 없음");
   }
      
   // 학수번호 함수
   public void makeLeNum(Lecture L)throws Exception{ 
      String leNum = "";
      int leNum1 = 0000;
      int leNumM = 0001;
      int leNumC = 0001;
      try {
         if (searchpMajor(L) == "소프트웨어융합학과") {
            leNum = "MT";
         } else if (searchpMajor(L) == "국어국문학과") {
            leNum = "KL";
         } else if (searchpMajor(L) == "영어영문학과") {
            leNum = "EL";
         }  else if (searchpMajor(L) == "경제학과") {
            leNum = "EC";
         }  else if (searchpMajor(L) == "화학과") {
            leNum = "CH";
         } else {
            leNum = "AA";
         }
         
         if (searchDivision(L) == "교양필수" || searchDivision(L) == "교양선택") {
            leNum1 = leNumC;
            leNumC++;
         } else {
            leNum1 = leNumM;
            leNumM++;
         } 
         
         String rleNum = leNum + leNum1;
         
         // 학수번호 DB 저장 
         stmt.executeUpdate("update lecture set leNum:='" + rleNum + "' where leName = '" + L.getleName() + "';");
      } catch (SQLException e) {
         e.printStackTrace();
      }
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
         stmt.executeUpdate("update student set stNum ='" + num + "' where stName = '" + S.getstName() + "';");
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}