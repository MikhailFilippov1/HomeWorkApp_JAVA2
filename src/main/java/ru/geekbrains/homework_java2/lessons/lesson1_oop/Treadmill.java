package ru.geekbrains.homework_java2.lessons.lesson1_oop;

public class Treadmill {
    private int treadmillLength;

    public void setTreadmillLength(int length){
        this.treadmillLength = length;
        System.out.printf("The treadmill %d m length has installed\n", treadmillLength);
    }


    public int getTreadmillLength() {
        return treadmillLength;
    }
}
