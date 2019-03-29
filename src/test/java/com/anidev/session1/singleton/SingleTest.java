package com.anidev.session1.singleton;

import org.junit.Test;
import sun.reflect.ConstructorAccessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class SingleTest {

    @Test
    public void testSimpleSingle() {
        //Ok
        SimpleSingle s1 = SimpleSingle.getInstance();
        SimpleSingle s2 = SimpleSingle.getInstance();

        assertSame(s1, s2);
        assertSame(s1.getSingletonId(), s2.getSingletonId());
        assertEquals(s1.getSingletonId(), s2.getSingletonId());

        new Thread(() -> System.out.println("123")).start();


    }

    @Test
    public void breakSimpleWithReflection() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<SimpleSingle> ctor = SimpleSingle.class.getDeclaredConstructor();
        ctor.setAccessible(true);
        SimpleSingle otherS1 = ctor.newInstance();
        assertNotSame(SimpleSingle.getInstance(), otherS1);
    }


    @Test
    public void breakLazyWithMultithreading() throws InterruptedException {
        LazySingle[] sArray = new LazySingle[2];
        Thread t1 = new Thread(() -> {
            sArray[0] = LazySingle.getInstance();
            sArray[0].incrementCounter();
        });

        Thread t2 = new Thread(() -> {
            sArray[1] = LazySingle.getInstance();
            sArray[1].incrementCounter();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertNotSame(sArray[0], sArray[1]);
    }

    @Test
    public void synchronizedLazyWithMultithreading() throws InterruptedException {
        SynchronizedLazySingle[] sArray = new SynchronizedLazySingle[2];
        Thread t1 = new Thread(() -> {
            sArray[0] = SynchronizedLazySingle.getInstance();
            sArray[0].incrementCounter();
        });

        Thread t2 = new Thread(() -> {
            sArray[1] = SynchronizedLazySingle.getInstance();
            sArray[1].incrementCounter();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertSame(sArray[0], sArray[1]);
    }

    @Test
    public void tryToBreakSafeSingle() throws InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        SafeSingle[] sArray = new SafeSingle[2];
        Thread t1 = new Thread(() -> {
            sArray[0] = SafeSingle.INSTANCE;
            sArray[0].incrementCounter();
        });

        Thread t2 = new Thread(() -> {
            sArray[1] = SafeSingle.INSTANCE;
            sArray[1].incrementCounter();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertSame(sArray[0], sArray[1]);


        assertThrows(NoSuchMethodException.class, () -> {
            Constructor<SafeSingle> ctor = SafeSingle.class.getDeclaredConstructor();
        });

        SafeSingle single = SafeSingle.valueOf("INSTANCE");
        assertSame(single, SafeSingle.INSTANCE);

        Constructor<?> constructor = SafeSingle.class.getDeclaredConstructors()[0];
        Field constructorAccessorField = Constructor.class.getDeclaredField("constructorAccessor");
        constructorAccessorField.setAccessible(true);

        ConstructorAccessor ca = (ConstructorAccessor) constructorAccessorField.get(constructor);
        if (ca == null) {
            Method acquireConstructorAccessorMethod = Constructor.class.getDeclaredMethod("acquireConstructorAccessor");
            acquireConstructorAccessorMethod.setAccessible(true);
            ca = (ConstructorAccessor) acquireConstructorAccessorMethod.invoke(constructor);
        }
        SafeSingle enumValue = (SafeSingle) ca.newInstance(new Object[]{"SafeSingle", 2});

        assertNotSame(enumValue, SafeSingle.INSTANCE);
    }

}