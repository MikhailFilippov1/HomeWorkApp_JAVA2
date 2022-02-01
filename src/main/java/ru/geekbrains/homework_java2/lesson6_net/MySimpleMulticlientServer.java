package ru.geekbrains.homework_java2.lesson6_net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class MySimpleMulticlientServer {

    private static final int PORT = 8181;
    private List<Handler> handlers;

    public MySimpleMulticlientServer(){
        this.handlers = new ArrayList<>();
    }

    public void start() {

        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started.");

            while (true){
                System.out.println("Waiting for connection ...");
                Socket socket = serverSocket.accept();
                System.out.printf("Client connecting.");
                Handler handler = new Handler(socket, this);
                handlers.add(handler);
                handler.handlerMethod();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void broadcast(String message) {
        for (Handler handler: handlers) {
            handler.send(message);
        }
    }
}
