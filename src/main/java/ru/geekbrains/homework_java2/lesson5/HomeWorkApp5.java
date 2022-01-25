package ru.geekbrains.homework_java2.lesson5;

import java.util.Arrays;

public class HomeWorkApp5 {

    static final int size = 10000000;
    static final int h = size / 2;


    public static void main(String[] args) {

        float[] arr = new float[size];
        float[] array = new float[size];

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();
        myArrMethod1(arr);
        System.out.printf("Time1 is %d ms\n", System.currentTimeMillis() - a);
        System.arraycopy(arr, 0, array, 0, size);

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        a = System.currentTimeMillis();
        myArrMethod2(arr);
        System.out.printf("Time2 is %d ms\n", System.currentTimeMillis() - a);

        System.out.println("Arrays are equal:" + Arrays.equals(arr, array));

    }



    public static void myArrMethod1(float[] arr) {
        for (int i = 0; i < size; i++){
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public static void myArrMethod2(float[] arr) {
        float[] arr1 = new float[size/2];
        float[] arr2 = new float[size/2];

        System.arraycopy(arr, 0, arr1, 0, size/2);
        System.arraycopy(arr, size/2, arr2, 0, size/2);
        for (int i = 0; i < size/2; i++){
            arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        for (int i = 0; i < size/2; i++){
            arr2[i] = (float) (arr2[i] * Math.sin(0.2f + (i + size/2) / 5) * Math.cos(0.2f + (i + size/2) / 5) * Math.cos(0.4f + (i + size/2 )/ 2));
        }
        System.arraycopy(arr1, 0, arr, 0, size/2);
        System.arraycopy(arr2, 0, arr, size/2, size/2);
    }
}
