package com.summer_school.pojo.dto;

public class ProfessionDto {
    private String profession;
    private Integer num;

    public ProfessionDto() {
    }

    public ProfessionDto(String profession, Integer num) {
        this.profession = profession;
        this.num = num;
    }

    /**
     * 获取
     * @return profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * 设置
     * @param profession
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * 获取
     * @return num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置
     * @param num
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    public String toString() {
        return "ProfessionDto{profession = " + profession + ", num = " + num + "}";
    }
}
