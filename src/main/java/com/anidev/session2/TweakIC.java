package com.anidev.session2;

import java.lang.reflect.Field;

public class TweakIC {

    public static void main(String[] args) {
        Integer i0 = 7;

        try {
            Class[] classes = Integer.class.getDeclaredClasses();
            for (Class clazz : classes) {
                if (clazz.getName().endsWith("IntegerCache")) {
                    Field cacheField = clazz.getDeclaredField("cache");
                    cacheField.setAccessible(true);
                    Integer[] cache = (Integer[]) cacheField.get(null);
                    for (int i = 0; i < cache.length; i++) {
                        cache[i] = new Integer(0);
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        Integer i1 = 7;

        System.out.println(i1 == i0);
        System.out.println(i1.equals(i0));

        System.out.println(Integer.valueOf(23));
    }

}
