package ru.geekbrains.homework_java2.lesson3;

import java.util.*;


public class HomeWork3 {

    public static void main(String[] args) {

        String[] array = {"Polit", "Super", "Qwest", "Publication", "Fish", "Super", "Lambda", "4", "4", "West", "Money",
                "Reservation", "185", "Simulation", "Bun", "4", "Super", "Super"};

        String name;

        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Map<String, Integer> words = new HashMap<>();

        PhoneBook phone = new PhoneBook();

        phone.add("Bob", "89816348822");
        phone.add("Bill", "89142657890");
        phone.add("Bob", "89265431245");
        phone.add("Mary","89286765888");
        phone.add("Mary","456-12-34");
        phone.add("Bob","89018787432");

        list.addAll(List.of(array));
        Collections.addAll(set, array);     // Не знаю, корректно ли так делать?

        System.out.println(set);            // Первое задание

        System.out.println();               // Второе задание
        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            String tempString = list.get(i);

            if (!words.containsKey(tempString)) {
                words.put(tempString, 1);
            } else {
                words.put(tempString, words.get(tempString) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            System.out.println(entry.getKey() + " meets " + entry.getValue()+ " times");
        }

        System.out.println();
        System.out.println("Phone Bob " + phone.getNumber("Bob"));      //Третье задание
        System.out.println("Phone Bil " + phone.getNumber("Bil"));
        System.out.println("Phone Bill " + phone.getNumber("Bill"));
        System.out.println("Phone Mary " + phone.getNumber("Mary"));

    }
}
