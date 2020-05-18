package com.qufu.utils;

import com.qufu.exception.InvalidParamException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


public class V {
    /**
     * 遍历数组判断是否为空
     *
     * @param req
     * @param keys
     */
    public static void valid(HttpServletRequest req, String[] keys) {
        for (String key : keys) {
            String value = req.getParameter(key);
            if (value == null || value.length() == 0) {
                throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, key + "不能为空");
            }
        }
    }

    /**
     * 判断是否为空
     *
     * @param req
     * @param keys
     */
    public static void val(HttpServletRequest req, String keys) {
        String value = req.getParameter(keys);
        if (value == null || value.length() == 0) {
            throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, keys + "不能为空");

        }
    }

    /**
     * 自动创建实体对象
     *
     * @param req
     * @param cls
     * @param keys
     * @return
     */
    public static <T> T entity(HttpServletRequest req, Class cls, String[] keys) {
        T instance = null;
        try {
            instance = (T) cls.newInstance();//Object instance = new Teacher();
            for (String key : keys) {
                String value = req.getParameter(key);
                String param = key;
                //获取指定字段
                Field field = cls.getDeclaredField(param);
                //获取set方法让首字母大写
                param = NameUtil.firstUpper(param);
                String optName = "set" + param;
                //获取字段的数据类型
                Class<?> dataType = field.getType();
                String typeName = dataType.getTypeName();
                Object setValue = null;
                if (typeName.equals("java.lang.Integer") || typeName.equals("int")) {
                    setValue = Integer.parseInt(value);
                } else if (typeName.equals("java.lang.Long") || typeName.equals("long")) {
                    setValue = Long.parseLong(value);
                } else if (typeName.equals("java.util.Date")) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date tmpDate = format.parse(value);
                    setValue = tmpDate;
                } else {
                    setValue = dataType.cast(value);
                }

                Method method = cls.getMethod(optName, dataType);
                method.invoke(instance, setValue);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 获取指定参数
     *
     * @param req
     * @param param
     * @param defaultValue
     * @return
     */
    public static String getValue(HttpServletRequest req, String param, String defaultValue) {
        if (req.getParameter(param) == null || req.getParameter(param) == "") {
            if (defaultValue == null || defaultValue == "") {
                return "无";
            }
            return defaultValue;
        }
        return req.getParameter(param);
    }
}
