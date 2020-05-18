package com.qufu.utils;


/**
 * 返回错误信息
 */
public class E {
    //验证失败
    public static final int YZM_NUMBER_CODE = 1007;
    public static final String YZM_NUMBER_INFO = "失败";
    //数据已存在
    public static final int NO_NUMBER_CODE = 1005;
    public static final String NO_NUMBER_INFO = "不存在";
    //存在
    public static final int NUMBER_CODE = 1006;
    public static final String NUMBER_INFO = "存在";
    //参数不合法
    public static final int INVALID_PARAM_ERROR_CODE = 1000;
    public static final String INVALID_PARAM_INFO = "参数不合法";
    //用户名或密码不正确
    public static final int USER_INFO_ERROR_CODE = 1001;
    public static final String USER_INFO_ERROR_INFO = "用户名或密码不正确";
    //请求路径错误
    public static final int PATH_ERROR_CODE = 1002;
    public static final String PATH_ERROR_INFO = "请求路径错误";
    //非法访问
    public static final int NO_PRIVILEGE_ERROR_CODE = 1004;
    public static final String NO_PRIVILEGE_ERROR_INFO = "没有访问权限";
    //自定义异常
    public static final int SELF_DEFINE_ERROR_CODE = 1003;
}
