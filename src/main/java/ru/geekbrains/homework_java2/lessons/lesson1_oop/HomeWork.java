package ru.geekbrains.homework_java2.lessons.lesson1_oop;

public class HomeWork {

    public static void main(String[] args) {
        boolean run_result = true;
        boolean jump_result = true;

        Moving[] movings = {
            new Man("Jon", 100, 1),     // Длины и высоты можно было сделать float, но я не
            new Man("Dick",200,2),      // стал заморачиваться
            new Cat("Murzik", 10, 3),
            new Bun("Fedya", 1000)
        };


        Wall[] walls = {
                new Wall(),         // Как можно задать значения массива, если не передается никакой параметр?
                new Wall()
        };
        walls[0].setWallHeight(1); //Зачем я сеттером устанавливаю высоту стенки я и сам не очень понял.
        walls[1].setWallHeight(2); // Можно было инициировать передаваемым параметром

        Treadmill[] treadmills = {
                new Treadmill(),
                new Treadmill()
        };
        treadmills[0].setTreadmillLength(100);
        treadmills[1].setTreadmillLength(50);


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if(!run_result || !jump_result) {
                    break;
                }else run_result = movings[i].run(treadmills[j]);
                if(!run_result || !jump_result) {
                    break;
                }else jump_result = movings[i].jump(walls[j]);
            }
            jump_result = true;
            run_result = true;
        }
    }
}
