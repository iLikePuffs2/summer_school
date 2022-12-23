package com.summer_school.pojo.po;

public class SchoolLevelContrast {
    private String schoolName;
    private String level;


    public SchoolLevelContrast() {
    }

    public SchoolLevelContrast(String schoolName, String level) {
        this.schoolName = schoolName;
        this.level = level;
    }

    /**
     * 获取
     * @return schoolName
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * 设置
     * @param schoolName
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * 获取
     * @return level
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    public String toString() {
        return "SchoolLevelContrast{schoolName = " + schoolName + ", level = " + level + "}";
    }
}
