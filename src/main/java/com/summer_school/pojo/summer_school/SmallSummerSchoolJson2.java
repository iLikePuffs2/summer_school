package com.summer_school.pojo.summer_school;

public class SmallSummerSchoolJson2 {
    private String sponsorNum;


    public SmallSummerSchoolJson2() {
    }

    public SmallSummerSchoolJson2(String sponsorNum) {
        this.sponsorNum = sponsorNum;
    }

    /**
     * 获取
     * @return sponsorNum
     */
    public String getSponsorNum() {
        return sponsorNum;
    }

    /**
     * 设置
     * @param sponsorNum
     */
    public void setSponsorNum(String sponsorNum) {
        this.sponsorNum = sponsorNum;
    }

    public String toString() {
        return "SmallSummerSchoolJson2{sponsorNum = " + sponsorNum + "}";
    }
}
