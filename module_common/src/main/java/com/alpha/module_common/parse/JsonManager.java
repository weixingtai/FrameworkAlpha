package com.alpha.module_common.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;

import java.util.List;

import retrofit2.HttpException;

public class JsonManager {

    static{
        TypeUtils.compatibleWithJavaBean = true;
    }

    /**
     * 将json字符串转换成java对象
     * @param json json 数据
     * @param cls 当前类
     * @return Java对象
     * @throws HttpException 网络异常
     */
    public static <T> T jsonToBean(String json, Class<T> cls) throws HttpException {
        return JSON.parseObject(json, cls);
    }

    /**
     * 将json字符串转换成java List对象
     * @param json json 数据
     * @param cls 当前类
     * @return java List对象
     * @throws HttpException 网络异常
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) throws HttpException {
        return JSON.parseArray(json, cls);
    }

    /**
     * 将bean对象转化成json字符串
     * @param obj bean对象
     * @return json字符串
     * @throws HttpException 网络异常
     */
    public static String beanToJson(Object obj) throws HttpException{
        return JSON.toJSONString(obj);
    }

}
