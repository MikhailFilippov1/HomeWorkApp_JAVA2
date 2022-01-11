package ru.geekbrains.homework_java2.lessons.lesson1_oop;

public class Wall {

        private int wallHeight;

        public void setWallHeight(int height){
            this.wallHeight = height;
            System.out.printf("The wall %d m height installed\n", wallHeight);
        }


        public int getWallHeight() {
            return wallHeight;
        }
    }
