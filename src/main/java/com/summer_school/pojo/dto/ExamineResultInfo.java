package com.summer_school.pojo.dto;

import com.summer_school.pojo.po.SignUpInfo;

public class ExamineResultInfo {
    /**
     * 审核者的身份
     * 被审核者的身份
     * 被审核者的账号
     * 所属暑期学校id
     * 审核结果
     */
    private String operatorIdentity;
    private String candidateIdentity;
    private String account;
    private int id;
    private boolean result;


    public ExamineResultInfo() {
    }

    public ExamineResultInfo(String operatorIdentity, String candidateIdentity, String account, int id, boolean result) {
        this.operatorIdentity = operatorIdentity;
        this.candidateIdentity = candidateIdentity;
        this.account = account;
        this.id = id;
        this.result = result;
    }

    /**
     * 获取
     * @return operatorIdentity
     */
    public String getOperatorIdentity() {
        return operatorIdentity;
    }

    /**
     * 设置
     * @param operatorIdentity
     */
    public void setOperatorIdentity(String operatorIdentity) {
        this.operatorIdentity = operatorIdentity;
    }

    /**
     * 获取
     * @return candidateIdentity
     */
    public String getCandidateIdentity() {
        return candidateIdentity;
    }

    /**
     * 设置
     * @param candidateIdentity
     */
    public void setCandidateIdentity(String candidateIdentity) {
        this.candidateIdentity = candidateIdentity;
    }

    /**
     * 获取
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return result
     */
    public boolean isResult() {
        return result;
    }

    /**
     * 设置
     * @param result
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    public String toString() {
        return "ExamineResultInfo{operatorIdentity = " + operatorIdentity + ", candidateIdentity = " + candidateIdentity + ", account = " + account + ", id = " + id + ", result = " + result + "}";
    }
}
