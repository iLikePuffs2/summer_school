package com.summer_school.pojo.dto;

public class CleanSignUp {
    private String fileURL;
    private Integer summerSchoolId;


    public CleanSignUp() {
    }

    public CleanSignUp(String fileURL, Integer summerSchoolId) {
        this.fileURL = fileURL;
        this.summerSchoolId = summerSchoolId;
    }

    /**
     * 获取
     * @return fileURL
     */
    public String getFileURL() {
        return fileURL;
    }

    /**
     * 设置
     * @param fileURL
     */
    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    /**
     * 获取
     * @return summerSchoolId
     */
    public Integer getSummerSchoolId() {
        return summerSchoolId;
    }

    /**
     * 设置
     * @param summerSchoolId
     */
    public void setSummerSchoolId(Integer summerSchoolId) {
        this.summerSchoolId = summerSchoolId;
    }

    public String toString() {
        return "CleanSignUp{fileURL = " + fileURL + ", summerSchoolId = " + summerSchoolId + "}";
    }
}
