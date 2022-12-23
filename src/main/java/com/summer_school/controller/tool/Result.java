package com.summer_school.controller.tool;

public class Result {
    private Object data;
    private Integer code;
    private boolean success;


    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, Object data, Integer code) {
        this.data = data;
        this.code = code;
        this.success = success;
    }

    public Result(boolean success, Object data) {
        this.data = data;
        this.success = success;
    }

    public Result(boolean success, Integer code) {
        this.code = code;
        this.success = success;
    }

    /**
     * 获取
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 获取
     * @return code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取
     * @return success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String toString() {
        return "Result{data = " + data + ", code = " + code + ", success = " + success + "}";
    }
}
