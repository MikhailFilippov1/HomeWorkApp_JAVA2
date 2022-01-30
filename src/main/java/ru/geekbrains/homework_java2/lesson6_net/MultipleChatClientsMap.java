package ru.geekbrains.homework_java2.lesson6_net;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MultipleChatClientsMap {

    private  final Map<String, Set<String>> clients = new TreeMap<>();


    public void add(String name, String message) {
        Set<String> contact = getNumber(name);
        contact.add(message);
    }

    public  Set<String> get(String name){
        return getNumber(name);
    }

    public Set<String> getNumber(String name) {
        return clients.computeIfAbsent(name, key -> new HashSet<>());
    }
}
