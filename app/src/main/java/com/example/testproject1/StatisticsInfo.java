package com.example.testproject1;

public class StatisticsInfo {

    String testCount; //검사횟수
   String bradycardia; //서맥 통계값
    String tachycardia; //빈맥 통계값
   String atrial_fibrillation; //심방세동 통계값
   String not_classified; //분류되지 않음 통계값
    String[] datelist; // 총 검사한 날짜
     double[] dayvalue; // 검사 날짜별 값


    public StatisticsInfo(String testCount, String bradycardia, String tachycardia, String atrialFibrillation, String notClassified, String[] datelist, double[] dayvalue) {
        this.testCount = testCount;
        this.bradycardia = bradycardia;
        this.tachycardia = tachycardia;
        this.atrial_fibrillation = atrialFibrillation;
        this.not_classified = notClassified;
        this.datelist = datelist;
        this.dayvalue = dayvalue;
    }
}
