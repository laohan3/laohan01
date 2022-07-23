package com.laohan.utils;


import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BeanCopyUtils {

    private BeanCopyUtils() {

    }

    //单个对象Bean拷贝
    public static <T> T copyBean(Object source, Class<T> clazz) {
        T target = null;
        try {
            target = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }

    //集合Bean拷贝
    public static <T, E> List<T> copyBeanList(List<E> list, Class<T> clazz) {
        List<T> tList = new ArrayList<>();
        for (E e : list) {
            try {
                T t = clazz.newInstance();
                BeanUtils.copyProperties(e, t);
                tList.add(t);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return tList;
    }



}
