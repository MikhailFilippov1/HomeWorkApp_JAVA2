package ru.geekbrains.homework_java2.lessons.lesson1_oop;

public class Bun implements Moving {        // Bun  это Колобок
    protected String name;
    protected int max_length;

    public Bun(String name, int max_length) {
        this.name = name;
        this.max_length = max_length;
       }

    @Override
    public boolean run(Treadmill treadmill) {
        if(max_length >= treadmill.getTreadmillLength()){
            System.out.printf("%s successfully run the %d m distance!!!\n", name, treadmill.getTreadmillLength());
            return true;
        }else {
            System.out.printf("%s can't run the %d m distance :-(\n", name, treadmill.getTreadmillLength());
            return false;
        }
    }

    @Override
    public boolean jump(Wall wall) {
            System.out.printf("Remember, GUY. Bun %s never jump!!!\n", name);
            return false;
        }
}
