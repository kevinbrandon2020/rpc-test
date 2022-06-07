package com.util;

import java.awt.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflictionUtil {
    public static <T> T newInstance(Class<T> clazz) {
        T instance = null;
        try {
            instance = clazz.newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static Method[] getPublicMethod(Class clazz){
        List<Method> pMethods = new ArrayList<>();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        pMethods = Arrays.stream(declaredMethods)
                .filter(method -> Modifier.isPublic(method.getModifiers())).collect(Collectors.toList());

        return pMethods.toArray(new Method[0]);
    }

    public static Object invoke(Object obj, Method method, Object... args){
        Object object = null;
        try {
            object = method.invoke(obj, args);
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }
}
