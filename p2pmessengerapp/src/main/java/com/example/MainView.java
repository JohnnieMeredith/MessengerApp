package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 * MainView is the main JPanel of this messaging app
 * 
 * @author Johnnie Meredith
 * @version 1.0
 */

public class MainView extends JPanel implements Runnable {
    private User user;
    private JTextArea messageDisplayTextArea;
    private JTextField messageInputTextField;
    private JPanel messageFrame;
    private int port;
    private int hostport;
    private String host;

    /**
     * 
     * @param user     User that is logged in
     * @param host     String which is to be the host
     * @param hostport ObjectReceiver port
     * @param port     ObjectSender port
     */

    public MainView(User user, String host, int hostport, int port) {
        this.user = user;
        this.host = host;
        this.hostport = hostport;
        this.port = port;
        setLayout(new FlowLayout());
        add(createMessageCenter());
        add(createMessageToolbar());
        setPreferredSize(new Dimension(500, 500));
        setSize(getPreferredSize());
        this.run();
    }

    /**
     * 
     * @return JComponent that is a JPanel container with JTextArea for writing
     *         message content
     */
    protected JComponent createMessageCenter() {
        JPanel messageCenterPane = new JPanel();

        JTextArea textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(400, 350));
        textArea.setSize(textArea.getPreferredSize());
        textArea.setLineWrap(true);
        textArea.setEnabled(false);

        messageCenterPane.setLayout(new FlowLayout(FlowLayout.LEADING));
        messageCenterPane.setPreferredSize(new Dimension(400, 350));
        messageCenterPane.add(textArea);
        messageCenterPane.setAutoscrolls(true);
        messageCenterPane.setEnabled(false);
        messageCenterPane.setSize(messageCenterPane.getPreferredSize());
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        messageCenterPane.setBackground(Color.WHITE);

        this.messageDisplayTextArea = textArea;
        this.messageFrame = messageCenterPane;

        return messageCenterPane;
    }

    /**
     * Method which creates a SwingWorker thread which listens for incoming
     * communications in the background.
     */
    public void run() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            ObjectReceiver objectReceiver;

            @Override
            protected Void doInBackground() throws Exception {
                try {
                    objectReceiver = new ObjectReceiver(getMainViewObject(), hostport);
                    objectReceiver.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

        };
        worker.execute();

    }

    /**
     * 
     * @return JPanel with JTextField and JButtons with action listeners for writing
     *         and sending messages
     */
    protected JComponent createMessageToolbar() {
        JPanel messageToolbarPane = new JPanel();
        JTextField messageContent = new JTextField();
        JButton send = new JButton("Send");
        JButton clear = new JButton("Clear");

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    sendMessage();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageInputTextField.setText("");
            }
        });

        messageContent.setPreferredSize(new Dimension(265, 25));
        messageContent.setSize(messageContent.getPreferredSize());
        this.messageInputTextField = messageContent;

        messageToolbarPane.add(messageContent);
        messageToolbarPane.add(send);
        messageToolbarPane.add(clear);

        return messageToolbarPane;
    }

    /**
     * 
     * @param message Message to be written to the GUI
     */
    public void updateMessages(Message message) {
        messageDisplayTextArea.append(message.getUserName() + ": " + message.getText() + "\n");
    }

    /**
     * Method which creates a SwingWorker thread to help send outgoing
     * communications
     */
    public void sendMessage() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                LocalDateTime created = LocalDateTime.now();
                Message message = new Message(created, user.getUserName(), messageInputTextField.getText());
                ObjectSender objectSender = new ObjectSender(getMainViewObject(), message,
                        getMainViewObject().getHost(), getMainViewObject().getPort());
                objectSender.run();
                return null;
            }

        };
        worker.execute();

    }

    /**
     * @return Message Frame for writing messages to GUI
     */
    public JPanel getMessageFrame() {
        return this.messageFrame;
    }

    /**
     * @return port field
     */
    protected int getPort() {
        return this.port;
    }

    /**
     * @return Hostname
     */
    protected String getHost() {
        return this.host;
    }

    /**
     * @return this
     */
    protected MainView getMainViewObject() {
        return this;
    }

    /**
     * @return Logged in User
     */
    protected User getUser() {
        return this.user;
    }

}
