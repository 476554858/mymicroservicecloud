package com.atguigu.springcloud.common;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SpringABA {
    private static Map<String,Object> cacheMap = new HashMap<String, Object>();

    public static void main(String[] args) {
        //假装扫描出来的对象
        Class[] classes = {A.class, B.class};
        //假装项目初始化实例化所有的bean
        for (Class  aClass : classes){
            getBean(aClass);
        }
        //check
        System.out.println(getBean(B.class).getA() == getBean(A.class));
        System.out.println(getBean(A.class).getB() == getBean(B.class));
    }

    @SneakyThrows
    private static <T> T getBean(Class<T> beanClass){
        //本文用 类名小写 简单代替bean的生命周期
        String beanName = beanClass.getSimpleName().toLowerCase();
        ///如果是一个bean,则直接返回
        if(cacheMap.containsKey(beanName)){
            return (T) cacheMap.get(beanName);
        }
        //将对象本省实例化
        Object object = beanClass.getDeclaredConstructor().newInstance();
        //放入缓存
        cacheMap.put(beanName,object);
        //把所有字段当成需要注入的bean，创建并注入到当前bean中
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            //获取需要注入字段的class
            Class<?> fieldClass = field.getType();
            String fieldBeanName = fieldClass.getSimpleName().toLowerCase();
            field.set(object,cacheMap.containsKey(fieldBeanName)?cacheMap.get(fieldBeanName):getBean(fieldClass));
        }
        return (T)  object;
    }
}
