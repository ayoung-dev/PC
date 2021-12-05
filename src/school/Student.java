package school;

import java.io.Serializable;

public class Student implements Serializable{
   private String stName;       // 이름 필드
   private String stNum;          // 학번 필드
   private int grade;          // 학년 필드
   private String pw;          // 비밀번호 필드
   private String birth;         // 생년월일 필드
   private String college;      // 단과대학 필드
   private String fMajor;      // 제 1전공 필드
   private String sMajor;      // 부전공 필드
   private String dMajor;      // 복수전공 필드
   private String phoneNum;   // 전화번호 필드
   private String email;      // 이메일 필드
   private String changed;      // 학적변동사항 필드
   private String conditions;   // 졸업 조건 필드
   private int totalMajor;       // 전공총학점 필드
   private int totalFinish;   // 총이수학점 필드
   
   //기본 생성자
   public Student(String stName, String stNum, int grade, String pw, String birth, String college, String fMajor, String sMajor, String dMajor, String phoneNum, 
         String email, String changed, String conditions, int totalMajor, int totalFinish){ 
      this.stName = stName;
      //this.year = year;
      this.stNum = stNum;
      this.grade = grade;
      this.pw = pw;
      this.birth = birth;
      this.college = college;
      this.fMajor = fMajor;
      this.sMajor = sMajor;
      this.dMajor = dMajor;
      this.phoneNum = phoneNum;
      this.email = email;
      this.changed = changed;
      this.conditions = conditions;
      this.totalMajor = totalMajor;
      this.totalFinish = totalFinish;
   }
   
   public Student() {
	// TODO Auto-generated constructor stub
}

public void setstName(String stName){       // 이름 설정자
      this.stName = stName;
   }      
   /*public void setYear(int year) {   // 입학년도 설정자
      this.year = year;
   }*/
   public void setstNum(String stNum) {   // 학번 설정자
      this.stNum = stNum;
   }
   public void setGrade(int grade) {   // 학년 설정자
      this.grade = grade;
   }
   public void setPw(String pw) {   // 비밀번호 설정자
      this.pw = pw;
   }
   public void setBirth(String string) {   // 생년월일 설정자
      this.birth = string;
   }
   public void setCollege(String college) {   // 단과대학 설정자
      this.college = college;
   }
   public void setfMajor(String fMajor) {   // 제1전공 설정자
      this.fMajor = fMajor;
   }
   public void setsMajor(String sMajor) {   // 부전공 설정자
      this.sMajor = sMajor;
   }
   public void setdMajor(String dMajor) {   // 복수전공 설정자
      this.dMajor = dMajor;
   }
   public void setPhoneNum(String phoneNum){    // 전화번호 설정자
      this.phoneNum = phoneNum;
   }
   
   public void setEmail(String email) {   // 이메일 설정자
      this.email = email;
   }
   
   public void setfChanged(String changed) {   // 학적변동사항 설정자
      this.changed = changed;
   }
   
   public void setConditions(String conditions) {   // 졸업조건 설정자
      this.conditions = conditions;
   }
   
   public void setTotalMajor(int totalMajor) {   // 전공총학점 설정자
      this.totalMajor = totalMajor;
   }
   
   public void setTotalFinish(int totalFinish) {   // 총이수학점 설정자
      this.totalFinish = totalFinish;
   }
   
   

   public String getstName(){          // 이름 접근자
      return stName;
   }
   public String getstNum(){       // 학번 접근자
      return stNum;
   }
   public int getGrade() {   // 학년 접근자
      return grade;
   }
   public String getPw() {   // 비밀번호 접근자
      return pw;
   }
   public String getBirth() {   // 생년월일 접근자
      return birth;
   }
   public String getCollege() {   // 단과대학 접근자
      return college;
   }
   public String getfMajor() {   // 제1전공 접근자
      return fMajor;
   }
   public String getsMajor()  {   // 부전공 접근자
      return sMajor;
   }
   public String getdMajor()  {   // 복수전공 접근자
      return dMajor;
   }
   public String getPhoneNum() {    // 전화번호 접근자
      return phoneNum;
   }
   
   public String getEmail()  {   // 이메일 접근자
      return email;
   }
   
   public String getChanged()  {   // 학적변동사항 접근자
      return changed;
   }
   
   public String getConditions()  {   // 졸업조건 접근자
      return conditions;
   }
   
   public int getTotalMajor()  {   // 전공총학점 접근자
      return totalMajor;
   }
   
   public int getTotalFinish() {   // 총이수학점 접근자
      return totalFinish;
   }
}
