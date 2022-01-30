package ru.geekbrains.homework_java2.lesson6_net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MySimpleConsoleClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8181;
    private static final String NAME = "Bob";
    private Thread clientConsoleThread;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        new MySimpleConsoleClient().start();
    }

    public void start() {
        try (var socket = new Socket(HOST, PORT)) {
            System.out.printf("Client %s connected to server.\n", NAME);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(NAME);
            startConsoleThread();

            while (true) {
                var message = in.readUTF();
                if(message.startsWith("/stop")){
                    shutdown();
                    System.exit(1);
                }
                System.out.println("Bob" + message);
            }
        }catch (SocketException e){
            System.out.println("Connection to server has been interrupt");
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void shutdown() throws IOException {
        if (clientConsoleThread.isAlive()) {
            clientConsoleThread.interrupt();
        }
        System.out.printf("Client %s disconnect.",NAME);
    }

    private void startConsoleThread() {
        clientConsoleThread = new Thread(() -> {
            try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.print("Enter message to server>>>");
                while (!Thread.currentThread().isInterrupted()) {
                    if (reader.ready()) {
                        var clientMessage = reader.readLine();
                        if (clientMessage.equalsIgnoreCase("/quit")) {
                            out.writeUTF("/stop");
                            shutdown();
                        }
                        out.writeUTF(clientMessage);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        clientConsoleThread.start();
    }
}


