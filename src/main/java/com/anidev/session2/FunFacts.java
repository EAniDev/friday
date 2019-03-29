package com.anidev.session2;

public class FunFacts {

    public static void main(String[] args) {
        Integer i0 = Integer.valueOf(1001);
        System.out.printf("i5.equals(1001): %b\n", i0.equals(1001));

        Integer i3 = 1000;
        Integer i4 = 1000;

        System.out.printf("i3.equals(i4): %b\n", i3.equals(i4));
        System.out.printf("i3 == i4: %b\n", i3 == i4);


        Integer i1 = 127;
        Integer i2 = 127;

        System.out.printf("i1.equals(i2): %b\n", i1.equals(i2));
        System.out.printf("i1 == i2: %b\n" , i1 == i2);
    }

}
