package com.summer_school.controller.tool;

public class Code {

    /**
     * Code里的静态常量不应该是成功/失败，而应该是更细致的内容，比如：
     * 1.INVALID_PARAM = "无效参数"
     */


    public static final Integer INSERT_OK = 20011;
    public static final Integer DELETE_OK = 20021;
    public static final Integer UPDATE_OK = 20031;
    public static final Integer GET_OK = 20041;

    public static final Integer INSERT_ERR = 20010;
    public static final Integer DELETE_ERR = 20020;
    public static final Integer UPDATE_ERR = 20030;
    public static final Integer GET_ERR = 20040;

    public static final Integer SYSTEM_ERR = 50001;
    public static final Integer SYSTEM_TIMEOUT_ERR = 50002;
    public static final Integer SYSTEM_UNKNOWN_ERR = 59999;

    public static final Integer BUSINESS_ERR = 60002;

}
