package ru.geekbrains.homework_java2.lesson6_net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Handler {

    private int clientNumber;
    private static int clientCounter = 0;
    private  DataInputStream in;
    private  DataOutputStream out;
    private  Socket socket;
    private Thread handlerThread;
    private MySimpleMulticlientServer server;

    public Handler(Socket socket, MySimpleMulticlientServer server) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            System.out.printf("Handler %d created\n", ++clientCounter);
            this.clientNumber = clientCounter;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handlerMethod() {
        handlerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted() && socket.isConnected()){
                try{
                    String message = in.readUTF();
                    message = "Client# " + this.clientNumber + "> " + message;
                    server.broadcast(message);
 //                   System.out.printf("Client# %d> %s\n", this.clientNumber,message); //Чтобы сервер себе не писал
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        handlerThread.start();
    }

    public void send(String message) {
        try{
            out.writeUTF(message);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Thread getHandlerThread(){ return handlerThread;}
}
