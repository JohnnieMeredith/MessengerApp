package com.example;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for Messenger App.
 */
public class AppTest {
    static User user;
    static MainView mainView;
    static LoginFrame loginFrame;
    static Message message;
    static ObjectSender objectSender;
    static ObjectReceiver objectReceiver;

    /**
     * Initialize objects
     */
    @BeforeClass
    public static void shouldBuildComponents() throws Exception {
        user = new User("User", "Password");
        mainView = new MainView(user, "localhost", 5555, 5556);
        loginFrame = new LoginFrame(mainView);
        LocalDateTime now = LocalDateTime.now();
        message = new Message(now, user.getUserName(), "TEST FOR MESSAGE");
        objectReceiver = new ObjectReceiver(mainView, 5555);
        objectSender = new ObjectSender(mainView, message, "localhost", 5556);

    }

    @Test
    public void shouldBeObjectSenderHost() throws Exception {
        Field hostField = ObjectSender.class.getDeclaredField("host");
        hostField.setAccessible(true);
        assertEquals("localhost", hostField.get(objectSender));
    }

    @Test
    public void shouldBeObjectSenderPort() throws Exception {
        Field portField = ObjectSender.class.getDeclaredField("port");
        portField.setAccessible(true);

        assertEquals(5556, portField.get(objectSender));
    }

    @Test
    public void shouldBeObjectMessageHost() throws Exception {
        Field messageField = ObjectSender.class.getDeclaredField("message");
        messageField.setAccessible(true);

        assertEquals(messageField.get(objectSender), message);
    }

    @Test
    public void shouldBeObjectSenderMainview() throws Exception {
        Field mainViewField = ObjectSender.class.getDeclaredField("mainView");
        mainViewField.setAccessible(true);

        assertEquals(mainView, mainViewField.get(objectSender));
    }

    @Test
    public void shouldBeObjectReceiverMainview() throws Exception {
        Field mainViewField = ObjectReceiver.class.getDeclaredField("mainView");
        mainViewField.setAccessible(true);

        assertEquals(mainView, mainViewField.get(objectReceiver));
    }

    @Test
    public void classesShouldMatch() {
        assertEquals(user.getClass(), User.class);
        assertEquals(loginFrame.getClass(), LoginFrame.class);
        assertEquals(mainView.getClass(), MainView.class);
        assertEquals(objectReceiver.getClass(), ObjectReceiver.class);
        assertEquals(objectSender.getClass(), ObjectSender.class);
    }

    @Test
    public void shouldCreateLoginGUI() {
        assertEquals(loginFrame.createLoginArea().getClass(), JPanel.class);
        assertEquals(loginFrame.createButtons().getClass(), JPanel.class);
    }

    @Test
    public void shouldCreateMainViewGUI() {
        assertEquals(mainView.createMessageCenter().getClass(), JPanel.class);
        assertEquals(mainView.createMessageToolbar().getClass(), JPanel.class);
    }

}
