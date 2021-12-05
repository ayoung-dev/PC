package school;

import java.io.Serializable;

public class SchoolApplication implements Serializable{
   private String stName;       // 이름 필드
   private String stNum;          // 학번 필드
   private String apSemester;   // 신청학기 필드
   private String classify;   // 변동사항구분 필드
   private String startDate;   // 시작일자 필드
   private String endDate;      // 종료일자 필드
   private String apReason;   // 변동사유 필드
   private int abSemester;      // 휴학학기수 필드
   private String apCount;      // 신청일
   private String apState;      // 신청현황 필드
   
   //기본 생성자
   public SchoolApplication(String stName, String stNum, String apSemester, String classify, String startDate, String endDate, String apReason, int abSemester,
         String apCount, String apState){ 
      this.stName = stName;
      this.stNum = stNum;
      this.apSemester = apSemester;
      this.classify = classify;
      this.startDate = startDate;
      this.endDate = endDate;
      this.apReason = apReason;
      this.abSemester = abSemester;
      this.apCount = apCount;
      this.apState = apState;
   }
   
   public void setstName(String stName){       // 이름 설정자
      this.stName = stName;
   }      
   public void setstNum(String stNum) {   // 학번 설정자
      this.stNum = stNum;
   }
   public void setApSemester(String apSemester) {   // 신청학기 설정자
      this.apSemester = apSemester;
   }
   public void setClassify(String classify) {   // 변동사항구분 설정자
      this.classify = classify;
   }
   
   public void setfStartDate(String startDate) {   // 시작일자 설정자
      this.startDate = startDate;
   }
   public void setEndDate(String endDate) {   // 종료일자 설정자
      this.endDate = endDate;
   }
   public void setApReason(String apReason) {   // 변동사유 설정자
      this.apReason = apReason;
   }
   public void setAbSemester(int abSemester) {   // 휴학학기수 설정자
      this.abSemester = abSemester;
   }
   public void setApCount(String apCount) {   // 신청일 설정자
      this.apCount = apCount;
   }
   public void setApState(String apState){    // 신청상태 설정자
      this.apState = apState;
   }
   
   

   public String getstName(){          // 이름 접근자
      return stName;
   }
   public String getstNum(){       // 학번 접근자
      return stNum;
   }
   public String getApSemester() {   // 신청학기 접근자
      return apSemester;
   }
   public String getClassify() {   // 변동사항구분 접근자
      return classify;
   }
   public String getStartDate() {   // 시작일자 접근자
      return startDate;
   }
   public String getEndDate() {   // 종료일자 접근자
      return endDate;
   }
   public String getApReason() {   // 변동사유 접근자
      return apReason;
   }
   public int getAbSemester() {   // 휴학학기수 접근자
      return abSemester;
   }
   public String getApCount() {   // 신청일 접근자
      return apCount;
   }
   public String getApState()  {   // 신청상태 접근자
      return apState;
   }
}