package ru.geekbrains.homework_java2.lesson6_net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

public class MySimpleMulticlientServer {

    private static final int PORT = 8181;
    List<Socket> socket = new ArrayList<>();
    private DataOutputStream out;
    private DataInputStream in;
    private Thread serverConsoleThread;
    private String clientName;
    private static int numOfClient = 0;

    public static void main(String[] args) {
        new MySimpleMulticlientServer().start();
    }

    public void start() {


        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started.");
 //           connectionMethod(serverSocket);
 //           startConsoleThread();

            while (true){
                connectionMethod(serverSocket);
                    var message = in.readUTF();
                    if(message.startsWith("/stop")){
                        shutdown();
                        break;
                    }
                    System.out.printf("%s> %s ", clientName, message);
                    System.out.println();
                }
        } catch (SocketException e){
            System.out.println("Connection to server has been interrupt");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                shutdown();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void shutdown() throws IOException{
        if(serverConsoleThread.isAlive()){
            serverConsoleThread.interrupt();
        }
        if(socket != null){
            socket.get(0).close();
        }
        System.out.println("Server shutdown.");
    }

    private void startConsoleThread() {
        serverConsoleThread = new Thread(() -> {
            try{
                while (!Thread.currentThread().isInterrupted()){
                    if(in.readUTF() != null){
                        var currentMessage = in.readUTF();
                        out.writeUTF(currentMessage);
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
    });
        serverConsoleThread.start();
    }

    private void connectionMethod(ServerSocket serverSocket) throws IOException {

        MultipleChatClientsMap client = new MultipleChatClientsMap();

        System.out.println("Waiting for connection ...");
        while (true) {
            Socket tmpSocket = serverSocket.accept();
        socket.add(tmpSocket);
        in = new DataInputStream(tmpSocket.getInputStream());
        out = new DataOutputStream(tmpSocket.getOutputStream());
        Handler handler = new Handler(socket.get(numOfClient), in, out);
            try {
                List<Thread> list = new LinkedList<>();
                for (int i = 0; i <= numOfClient; i++) {
                    Thread thread = new Thread(() -> {
                        try {
                            handler.handlerMethod(numOfClient);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    thread.start();
                    list.add(thread);
                    clientName = in.readUTF();
                    client.add(clientName, "");
                    numOfClient++;
                    System.out.printf("Client %s connecting.\n", clientName);
                    System.out.printf("Quantity of connections are %d.\n", numOfClient);
                }
                for(Thread t : list){
                    t.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
