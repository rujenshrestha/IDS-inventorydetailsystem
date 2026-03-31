package com.inventory.detail.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PojoTestUtil {

    public static <T> void testPojo(Class<T> pojoClass) {
        try {
            T instance = pojoClass.getDeclaredConstructor().newInstance();

            Map<Class<?>, Object> dummyValues = getDummyValues();

            for (Field field : pojoClass.getDeclaredFields()) {
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();

                Object value = dummyValues.get(fieldType);

                if (value == null) {
                    continue;
                }

                String setterName = "set" + capitalize(fieldName);
                Method setter = pojoClass.getMethod(setterName, fieldType);

                String getterName = "get" + capitalize(fieldName);
                Method getter = pojoClass.getMethod(getterName);

                setter.invoke(instance, value);

                Object result = getter.invoke(instance);
                assertEquals(value, result, "Mismatch on field: " + fieldName);
            }

        } catch (Exception e) {
            fail("POJO test failed for class: " + pojoClass.getSimpleName(), e);
        }
    }

    private static Map<Class<?>, Object> getDummyValues() {
        Map<Class<?>, Object> map = new HashMap<>();
        map.put(String.class, "test");
        map.put(int.class, 1);
        map.put(Integer.class, 1);
        map.put(long.class, 1L);
        map.put(Long.class, 1L);
        map.put(boolean.class, true);
        map.put(Boolean.class, true);
        map.put(double.class, 1.0);
        map.put(Double.class, 1.0);
        return map;
    }

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}