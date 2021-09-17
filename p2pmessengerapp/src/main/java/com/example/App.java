package com.example;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        User user = new User("User1", "Password1");

        User user2 = new User("User2", "Password2");

        MainView mainView = new MainView(user, "localhost", 8555, 8666);
        LoginFrame loginFrame = new LoginFrame(mainView);

        MainView mainView2 = new MainView(user2, "localhost", 8666, 8555);
        LoginFrame loginFrame2 = new LoginFrame(mainView2);

    }
}
