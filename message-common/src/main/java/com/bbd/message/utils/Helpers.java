/*
 * Copyright (c) 2016.
 * BrandBigData.com Inc
 */

package com.bbd.message.utils;

import com.bbd.message.exception.MessageException;

import java.util.Collection;
import java.util.Map;

/**
 * 基础工具类
 * 
 * @author zhao yuan
 * @version $Id: Helpers.java, v 0.1 2016/8/1 zhao yuan Exp $
 */
public class Helpers {

    /**
     * 断言指定的对象不为空
     * @param o 待检测对象
     * @param <T> 待检测对象类型
     * @return 如果对象不为空则返回
     */
    public static <T> T assertNotNull(T o) {
        if (o == null) {
            throw new MessageException("必要对象为空");
        }
        return o;
    }

    /**
     * 断言集合不为空
     * @param o
     * @param <T>
     * @return
     */
    public static <T extends Collection<?>> T assertNotNullOrEmpty(T o) {
        if (o == null || o.isEmpty()) {
            throw new MessageException("必要对象为空");
        }
        return o;
    }

    /**
     * 断言Map不为空
     * @param o
     * @param <T>
     * @return
     */
    public static <T extends Map<?, ?>> T assertNotNullOrEmpty(T o) {
        if (o == null || o.isEmpty()) {
            throw new MessageException("必要对象为空");
        }
        return o;
    }

    /**
     * 断言字符串不为空
     * @param o
     * @return
     */
    public static String assertNotNullOrEmpty(String o) {
        if (o == null || o.length() == 0) {
            throw new MessageException("必要对象为空");
        }
        return o;
    }

    /**
     * 如果目标值为空， 则使用默认值
     * @param value
     * @param defaultValue 默认值
     * @param <T> 类型
     * @return 目标值 或者 默认值
     */
    public static <T> T getDefaultValueIfNull(T value, T defaultValue) {
        assertNotNull(defaultValue);
        return value == null ? defaultValue : value;
    }

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isNullOrEmpty(String o) {
        return o == null || o.length() == 0;
    }

    public static boolean isNullOrEmpty(Collection<?> o) {
        return o == null || o.isEmpty();
    }

    public static boolean isNullOrEmpty(Map<?, ?> o) {
        return o == null || o.isEmpty();
    }

}
