package ru.geekbrains.homework_java2.lesson5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HomeWorkApp5 {

    static final int size = 10000000;
    static final int h = size / 2;
    static public int quantityOfProcessors;


    public static void main(String[] args) throws InterruptedException {

        float[] arr = new float[size];
        float[] array = new float[size];
        quantityOfProcessors = Runtime.getRuntime().availableProcessors();

        initializeArray(arr);

        long a = System.currentTimeMillis();        // Однопоточный метод
        myArrMethod1(arr);
        System.out.printf("Time of singlethread method is %d ms\n", System.currentTimeMillis() - a);

        System.arraycopy(arr, 0, array, 0, size);

        initializeArray(arr);

        a = System.currentTimeMillis();         // Двухпоточный метод
        myArrMethod2(arr);
        System.out.printf("Time of multithread method is %d ms\n", System.currentTimeMillis() - a);

        System.out.println("Arrays are equal:" + Arrays.equals(arr, array));

        initializeArray(arr);

        a = System.currentTimeMillis();         // Количество потоков равно количеству процессоров
        myArrMethod4(arr, quantityOfProcessors);
        System.out.printf("Time of fastest method is %d ms\n", System.currentTimeMillis() - a);

        System.out.println("Arrays are equal:" + Arrays.equals(arr, array));

    }



    public static void myArrMethod1(float[] arr) {  // Однопоточный метод
        for (int i = 0; i < size; i++){
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public static void myArrMethod2(float[] arr) throws InterruptedException {  // Двухпоточный метод
        float[] arr1 = new float[size/2];
        float[] arr2 = new float[size/2];

        System.arraycopy(arr, 0, arr1, 0, size/2);
        System.arraycopy(arr, size/2, arr2, 0, size/2);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < size/2; i++) {
                    arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < size/2; i++){
                    arr2[i] = (float) (arr2[i] * Math.sin(0.2f + (i + size/2) / 5) * Math.cos(0.2f + (i + size/2) / 5) * Math.cos(0.4f + (i + size/2 )/ 2));
                }
            }
        });
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e){
            System.err.println("Something went wrong!!! Process interrupt.");
        }

        System.arraycopy(arr1, 0, arr, 0, size/2);
        System.arraycopy(arr2, 0, arr, size/2, size/2);
    }

    public static void myArrMethod4(float[] arr, int number){       //  Fastest method
        try {
            List<Thread> list = new LinkedList<>();
            for (int i = 0; i < number; i++) {
                int finalI = i;
                Thread thread = new Thread(() -> {
                    for (int k = finalI; k < size; k+=number){
                        arr[k] = (float) (arr[k] * Math.sin(0.2f + k  / 5) * Math.cos(0.2f + k / 5) * Math.cos(0.4f + k / 2));
                    }
                });
                thread.start();
                list.add(thread);
            }
            for(Thread t : list){
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void initializeArray(float[] array) {
        for (int i = 0; i < size; i++) {
            array[i] = 1;
        }
    }
}
