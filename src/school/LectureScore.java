package school;

public class LectureScore {
	   private String stNum;// 학생학번
	   private String stName;// 학생이름
	   private String leName;// 과목명
	   private String leNum;// 학수번호
	   private String division;// 이수구분
	   private int credit;// 이수학점
	   private float scoreCredit; //학점(학생점수) (ex.A+ -> 4.5)
	   private int midScore;// 중간고사
	   private int assiScore;// 과제
	   private int finScore;// 기말고사
	   private int attendScore;// 출석
	   private int totalScore;// 총점
	   private String prName;// 교수명
	   private String time;// 강의시간
	   private String grade;// 등급
	   private String semester;// 학기

	   // 생성자 메서드
	   public LectureScore(String stNum, String stName, String leName, String leNum,
	         String division, int credit, int midScore, int assiScore, int finScore,
	         int attendScore, int totalScore, String prName, String time, String grade, 
	         String semester) {
	      this.stNum = stNum;
	      this.stName = stName;
	      this.leName = leName;
	      this.leNum = leNum;
	      this.division = division;
	      this.credit = credit;
	      this.midScore = midScore; 
	      this.assiScore = assiScore;
	      this.finScore = finScore;
	      this.attendScore = attendScore;
	      this.totalScore = totalScore;
	      this.prName = prName;
	      this.time = time;
	      this.grade = grade;
	      this.semester = semester;
	   }
	   
	   // 생성자 메서드 (for pmg.showGrade)
	   public LectureScore(String leName,String stName, String stNum, int midScore, int finScore, int assiScore,
	         int attedScore, int totalScore,String grade) {
	      this.leName = leName;
	      this.stName = stName;
	      this.stNum = stNum;
	      this.midScore = midScore;
	      this.assiScore = assiScore;
	      this.finScore = finScore;
	      this.attendScore = attedScore;
	      this.totalScore = totalScore;
	      this.grade = grade;
	   }
	   
	// 생성자 메서드 (for smg.showRecntGrade)
	   public LectureScore(String leName,String stName, int credit, String stNum, int midScore, int finScore, int assiScore,
	         int attedScore, int totalScore,String grade) {
	      this.leName = leName;
	      this.stName = stName;
	      this.credit = credit;
	      this.stNum = stNum;
	      this.midScore = midScore;
	      this.assiScore = assiScore;
	      this.finScore = finScore;
	      this.attendScore = attedScore;
	      this.totalScore = totalScore;
	      this.grade = grade;
	   }
	   	   
	   // 생성자 메서드(for P_Grade)
	   public LectureScore(int midScore, int finScore, int assiScore, int attendScore, int totalScore,
	         String grade) {
	      this.midScore = midScore;
	      this.finScore = finScore;
	      this.assiScore = assiScore;
	      this.attendScore = attendScore;
	      this.totalScore = totalScore;
	      this.grade = grade;
	   }

	   // 생성자 메서드 (for smg.showGrade)
	   public LectureScore(String leNum, String leName, String division, int credit, String time,
			String prName, int totalScore, float scoreCredit, String grade) {
		this.leNum = leNum;
		this.leName = leName;
		this.division = division;
		this.credit = credit;
		this.time = time;
		this.prName = prName;
		this.totalScore = totalScore;
		this.scoreCredit = scoreCredit;
		this.grade = grade;
	}

	// get 메소드
	   public String getstNum() {
	      return stNum;
	   }

	   public String getstName() {
	      return stName;
	   }

	   public String getLeName() {
	      return leName;
	   }

	   public String getLeNum() {
	      return leNum;
	   }

	   public String getDivision() {
	      return division;
	   }

	   public int getCredit() {
	      return credit;
	   }
	   
	   public float getScoreCredit() {
	      return scoreCredit;
	   }

	   public int getAssiScore() {
	      return assiScore;
	   }

	   public int getMidScore() {
	      return midScore;
	   }

	   public int getFinScore() {
	      return finScore;
	   }

	   public int getAttendScore() {
	      return attendScore;
	   }

	   public int getTotalScore() {
	      return totalScore;
	   }

	   public String getprName() {
	      return prName;
	   }

	   public String getTime() {
	      return time;
	   }

	   // set 메소드
	   public void setstNum(String stNum) {
	      this.stNum = stNum;
	   }

	   public String getGrade() {
	      return grade;
	   }

	   public String getSemester() {
	      return semester;
	   }

	   public void setStName(String stName) {
	      this.stName = stName;
	   }

	   public void setLeName(String leName) {
	      this.leName = leName;
	   }

	   public void setLeNum(String leNum) {
	      this.leNum = leNum;
	   }

	   public void setDivision(String division) {
	      this.division = division;
	   }

	   public void setCredit(int credit) {
	      this.credit = credit;
	   }
	   
	   public void setScoreCredit(float scoreCredit) {
	      this.scoreCredit = scoreCredit;
	   }

	   public void setAssiScore(int assiScore) {
	      this.assiScore = assiScore;
	   }

	   public void setMidScore(int midScore) {
	      this.midScore = midScore;
	   }

	   public void setFinScore(int finScore) {
	      this.finScore = finScore;
	   }

	   public void setAttendScore(int attendScore) {
	      this.attendScore = attendScore;
	   }

	   public void setTotalScore(int totalScore) {
	      this.totalScore = totalScore;
	   }

	   public void setprName(String prName) {
	      this.prName = prName;
	   }

	   public void setTime(String time) {
	      this.time = time;
	   }

	   public void setGrade(String grade) {
	      this.grade = grade;
	   }

	   public void setSemester(String semester) {
	      this.semester = semester;
	   }

	}