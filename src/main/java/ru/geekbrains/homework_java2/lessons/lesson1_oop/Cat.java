package ru.geekbrains.homework_java2.lessons.lesson1_oop;

public class Cat implements Moving {

    protected String name;
    protected int max_length;
    protected  int max_height;

        public Cat(String name, int max_length, int max_height) {
        this.name = name;
        this.max_length = max_length;
        this.max_height = max_height;
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
        if(wall.getWallHeight() <= max_height){
            System.out.printf("%s successfully jump over the %d m wall!!!\n", name, wall.getWallHeight());
            return true;
        }else {
            System.out.printf("%s can't jump over the %d m wall :-(\n", name, wall.getWallHeight());
            return false;
        }
    }

}
