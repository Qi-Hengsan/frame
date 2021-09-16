package com.wwj.common.utils;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * PO、JO转换工具类
 * @author sukaiyi
 * @date 2019/3/26
 */
public class PojoUtils {


    /**
     * {@link PojoUtils#convert(Object, Class, ignoreProperties)} 的批量版本
     *
     * @param vs    被复制的对象
     * @param clazz 要复制成的类型
     * @param <T>   复制成的对象的类型
     * @param <V>   被复制的对象的类型
     * @return 包含了复制的结果的list
     */
    public static <T, V> List<T> convert(List<V> vs, Class<T> clazz, String... ignoreProperties) {
        if (CollUtil.isNotEmpty(vs)) {
            return vs.stream()
                    .map(v -> convertOne(v, clazz, ignoreProperties))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public static <T, V> List<T> convert(List<V> vs, Class<T> clazz, BiConsumer<V, T> func, String... ignoreProperties) {
        if (CollUtil.isNotEmpty(vs)) {
            return vs.stream()
                    .map(v -> convertOne(v, clazz, func, ignoreProperties))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * 复制对象的属性
     *
     * @param v     被复制的对象
     * @param clazz 要将该对象复制成什么类型
     * @param <T>   复制成的对象的类型
     * @param <V>   被复制的对象的类型
     * @return 一个新的 T 类型的对象，这个对象和 v 有相同的属性值
     */
    public static <T, V> T convertOne(V v, Class<T> clazz, BiConsumer<V, T> func, String... ignoreProperties) {
        try {
            T t = clazz.newInstance();
            BeanUtils.copyProperties(v, t, ignoreProperties);
            func.accept(v, t);
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    public static <T, V> T convertOne(V v, Class<T> clazz, String... ignoreProperties) {
        try {
            T entity = clazz.newInstance();
            BeanUtils.copyProperties(v, entity, ignoreProperties);
            return entity;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    /**
     * 复制Page对象属性
     * @param page
     * @param clazz
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> Page<T> convert(Page<V> page, Class<T> clazz, String... ignoreProperties) {
        Page<T> result = new Page<>();
        if (page != null) {
            result.setTotal(page.getTotal());
            result.setCurrent(page.getCurrent());
            result.setSize(page.getSize());
            result.setRecords(page.getRecords().stream()
                    .map(r -> convertOne(r, clazz, ignoreProperties))
                    .collect(Collectors.toList())
            );
        }
        return result;
    }

    public static <T, V> Page<T> convert(Page<V> page, Class<T> clazz, BiConsumer<V, T> func, String... ignoreProperties) {
        Page<T> result = new Page<>();
        if (page != null) {
            result.setTotal(page.getTotal());
            result.setCurrent(page.getCurrent());
            result.setSize(page.getSize());
            result.setRecords(page.getRecords().stream()
                    .map(r -> convertOne(r, clazz, func, ignoreProperties))
                    .collect(Collectors.toList())
            );
        }
        return result;
    }

}
