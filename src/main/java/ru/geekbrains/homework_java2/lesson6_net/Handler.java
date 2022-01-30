package ru.geekbrains.homework_java2.lesson6_net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Handler extends Thread{

    private static final ArrayList<Handler> clients = new ArrayList<>();

    private DataInputStream in;
    private OutputStream out;
    private Socket socket;

    public Handler(Socket socket, DataInputStream in, DataOutputStream out) {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    public void handlerMethod(int num) throws IOException {
        System.out.printf("Handler %d with socket %s started\n", num, socket);
            while (true) {
                var message = in.readUTF();
                System.out.println("Server>" + message);
            }
    }
}
