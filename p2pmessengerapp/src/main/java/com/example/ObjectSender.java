package com.example;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * ObjectSender creates a ServerSocket and sends Message objects. It writes the
 * content of the Message to the MainView GUI.
 * 
 * @author Johnnie Meredith
 * @version 1.0
 */

public class ObjectSender implements Runnable {
    private String host;
    private int port;
    private Message message;
    private MainView mainView;

    /**
     * 
     * @param mainView Main screen of the message app
     * @param message  Message object with a time created username and message text
     * @param host     String name of the host
     * @param port     port number
     */
    public ObjectSender(MainView mainView, Message message, String host, int port) {
        this.mainView = mainView;
        this.message = message;
        this.host = host;
        this.port = port;
    }

    /**
     * Runnable allows siultaneous sending and recieving
     */
    @Override
    public void run() {
        Socket socket = null;
        try {
            socket = new Socket(this.host, this.port);
            ObjectOutputStream objOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objOutputStream.writeObject(message);
            mainView.updateMessages(message);
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
