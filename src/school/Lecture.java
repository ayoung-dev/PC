package school;

import java.io.Serializable;

public class Lecture implements Serializable{
   private String leName;       // 과목명 필드
   private String leNum;       // 학수번호 필드
   private String semester;   // 학기 필드
   private int limGrade;      // 제한학년 필드
   private String prName;   // 담당교수 필드
   private String type;      // 강의유형 필드
   private String time;      // 강의시간 필드
   private int credit;         // 학점 필드
   private int attMem;         // 수강인원 필드
   private int limMem;         // 제한인원 필드
   private String preLecture;   // 선수과목 필드
   private String division;   // 이수구분 필드
   private String state;      // 장바구니 필드
   
   //기본 생성자
   public Lecture(String leName, String leNum, String semester, int limGrade, String prName, String type, String time, int credit,
         int attMem, int limMem, String preLecture, String division, String state){ 
      this.leName = leName;
      this.leNum = leNum;
      this.semester = semester;
      this.limGrade = limGrade;
      this.prName = prName;
      this.type = type;
      this.time = time;
      this.credit = credit;
      this.attMem = attMem;
      this.limMem = limMem;
      this.preLecture = preLecture;
      this.division = division;
      this.state = state;
   }
   
   public void setleName(String leName){       // 과목명 설정자
      this.leName = leName;
   }      
   public void setleNum(String leNum) {   // 학수번호 설정자
      this.leNum = leNum;
   }
   public void setSemester(String semester) {   // 학기 설정자
      this.semester = semester;
   }
   public void setLimGrade(int limGrade) {   // 제한학년 설정자
      this.limGrade = limGrade;
   }
   public void setprName(String prName) {   // 담당교수 설정자
      this.prName = prName;
   }
   public void setType(String type){    // 강의유형 설정자
      this.type = type;
   }
   public void setTime(String time) {   // 강의시간 설정자
      this.time = time;
   }
   public void setCredit(int credit) {   // 학점 설정자
      this.credit = credit;
   }
   public void setattMem(int attMem) {   // 수강인원 설정자
      this.attMem = attMem;
   }
   public void setLimMem(int limMem){    // 제한인원 설정자
      this.limMem = limMem;
   }
   public void setPreLecture(String preLecture) {   // 선수과목 설정자
      this.preLecture = preLecture;
   }
   public void setDivision(String division){    // 이수구분 설정자
      this.division = division;
   }
   public void setState(String state) {   // 장바구니 설정자
      this.state = state;
   }
   

   public String getleName(){          // 과목명 접근자
      return leName;
   }
   public String getleNum() {   // 학수번호 설정자
      return leNum;
   }
   public String getSemester() {   // 학기 설정자
      return semester;
   }
   public int getLimGrade()  {   // 제한학년 설정자
      return limGrade;
   }
   public String getprName() {   // 담당교수 설정자
      return prName;
   }
   public String getType(){    // 강의유형 설정자
      return type;
   }
   public String getTime() {   // 강의시간 설정자
      return time;
   }
   public int getCredit() {   // 학점 설정자
      return credit;
   }
   public int getAttMem() {   // 수강인원 설정자
      return attMem;
   }
   public int getLimMem(){    // 제한인원 설정자
      return limMem;
   }
   public String getPreLecture() {   // 선수과목 설정자
      return preLecture;
   }
   public String getDivision(){    // 이수구분 설정자
      return division;
   }
   public String getState() {   // 장바구니 설정자
      return state;
   }
}