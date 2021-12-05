package school;

import java.io.Serializable;


public class Professor implements Serializable{
	   private String prName;       // 이름 필드
	   //private int year;         // 입사?년도 필드
	   private String prNum;          // 교번 필드
	   private String pw;          // 비밀번호 필드
	   private int birth;         // 생년월일 필드
	   private String college;      // 단과대학 필드
	   private String major;      // 소속과 필드
	   private String phoneNum;   // 전화번호 필드
	   private String email;      // 이메일 필드
	   
	   //기본 생성자
	   public Professor(String prName, String prNum, String pw, int birth, String college, String major, String phoneNum, String email){ 
	      this.prName = prName;
	      //this.year = year;
	      this.prNum = prNum;
	      this.pw = pw;
	      this.birth = birth;
	      this.college = college;
	      this.major = major;
	      this.phoneNum = phoneNum;
	      this.email = email;
	   }
	   public Professor() {
		   
	   }
	   
	   public void setprName(String prName){       // 이름 설정자
	      this.prName = prName;
	   }      
	   /*public void setYear(int year) {   // 입사년도 설정자
	      this.year = year;
	   }*/
	   public void setprNum(String prNum) {   // 교번 설정자
	      this.prNum = prNum;
	   }
	   public void setPw(String pw) {   // 비밀번호 설정자
	      this.pw = pw;
	   }
	   public void setBirth(int birth) {   // 생년월일 설정자
	      this.birth = birth;
	   }
	   public void setCollege(String college) {   // 단과대학 설정자
	      this.college = college;
	   }
	   public void setMajor(String major) {   // 소속과 설정자
	      this.major = major;
	   }
	   public void setPhoneNum(String phoneNum){    // 전화번호 설정자
	      this.phoneNum = phoneNum;
	   }
	   public void setEmail(String email) {   // 이메일 설정자
	      this.email = email;
	   }

	   public String getprName(){          // 이름 접근자
	      return prName;
	   }
	   /*public int getYear(){       // 입사년도 접근자
	      return year;
	   }*/
	   public String getprNum(){       // 교번 접근자
	      return prNum;
	   }
	   public String getPw() {   // 비밀번호 접근자
	      return pw;
	   }
	   public int getBirth() {   // 생년월일 접근자
	      return birth;
	   }
	   public String getCollege() {   // 단과대학 접근자
	      return college;
	   }
	   public String getMajor() {   // 소속과 접근자
	      return major;
	   }
	   public String getPhoneNum() {    // 전화번호 접근자
	      return phoneNum;
	   }
	   
	   public String getEmail()  {   // 이메일 접근자
	      return email;
	   }
	}