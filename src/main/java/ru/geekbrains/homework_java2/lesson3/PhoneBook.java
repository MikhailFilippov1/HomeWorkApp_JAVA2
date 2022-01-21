package ru.geekbrains.homework_java2.lesson3;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PhoneBook {

    private  final Map<String, Set<String>> persons = new TreeMap<>();


    public void add(String name, String phone) {
        Set<String> phones = getNumber(name);
        phones.add(phone);
    }

    public  Set<String> get(String name){
        return getNumber(name);
    }

    public Set<String> getNumber(String name) {
        return persons.computeIfAbsent(name, key -> new HashSet<>());
    }


    /*@Override
    public String toString() {
        return "PhoneBook{" +
                "Name='" + name + '\'' +
                " PhoneNumber=" + number +
                '}';
    }*/
}
