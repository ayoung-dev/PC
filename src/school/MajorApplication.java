package school;

import java.io.Serializable;

public class MajorApplication implements Serializable{
   private String stName;       // 이름 필드
   private String stNum;          // 학번 필드
   private String classify;   // 신청구분 필드
   private String apSemester;   // 신청학기 필드
   private String apMajor;      // 신청전공 필드
   private String apDate;      // 신청일자 필드
   private String apReason;   // 신청사유 필드
   private String apState;      // 신청상태 필드
   
   //기본 생성자
   public MajorApplication(String stName, String stNum, String classify, String apSemester, String apMajor, String apDate, String apReason, String apState){ 
      this.stName = stName;
      this.stNum = stNum;
      this.classify = classify;
      this.apSemester = apSemester;
      this.apMajor = apMajor;
      this.apDate = apDate;
      this.apReason = apReason;
      this.apState = apState;
   }
   
   public void setstName(String stName){       // 이름 설정자
      this.stName = stName;
   }      
   public void setstNum(String stNum) {   // 학번 설정자
      this.stNum = stNum;
   }
   public void setClassify(String classify) {   // 신청구분 설정자
      this.classify = classify;
   }
   public void setApSemester(String apSemester) {   // 신청학기 설정자
      this.apSemester = apSemester;
   }
   public void setfApMajor(String apMajor) {   // 신청전공 설정자
      this.apMajor = apMajor;
   }
   public void setApDate(String apDate) {   // 신청일자 설정자
      this.apDate = apDate;
   }
   public void setApReason(String apReason) {   // 신청사유 설정자
      this.apReason = apReason;
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
   public String getClassify() {   // 신청구분 접근자
      return classify;
   }
   public String getApSemester() {   // 신청학기 접근자
      return apSemester;
   }
   public String getApMajor() {   // 신청전공 접근자
      return apMajor;
   }
   public String getApDate() {   // 신청일자 접근자
      return apDate;
   }
   public String getApReason() {   // 신청사유 접근자
      return apReason;
   }
   public String getApState()  {   // 신청상태 접근자
      return apState;
   }
}