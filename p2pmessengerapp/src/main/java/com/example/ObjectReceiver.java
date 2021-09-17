package com.example;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ObjectReceiver creates a ServerSocket connection which listens for Message
 * Objects sent to it. It transfers these to the MainView UI.
 */
public class ObjectReceiver implements Runnable {

    private ServerSocket serverSocket;
    private MainView mainView;

    public ObjectReceiver(MainView mainView, int port) {
        this.mainView = mainView;
        try {
            this.serverSocket = new ServerSocket(port);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Socket clientEnd;
        try {
            while ((clientEnd = serverSocket.accept()) != null) {

                ObjectInputStream inputStream = new ObjectInputStream(clientEnd.getInputStream());
                Message message = (Message) inputStream.readObject();
                mainView.updateMessages(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}