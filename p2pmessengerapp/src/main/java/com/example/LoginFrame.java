package com.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * LoginFrame is the login screen for the messenger app. It has two fields one
 * for Username and one for a password. It verifies the Username and password
 * against a User object. It then changes itself to contain a MainView panel
 * upon succesful login.
 * 
 * @author Johnnie Meredith
 * @version 1.0
 */

public class LoginFrame extends JFrame {
    // THESE CREDENTIALS ARE NOT SECURE ONLY FOR DEMO PURPOSES
    private final String userNameSecret;
    private char[] userPasswordSecret;
    private Container contentPane = getContentPane();
    private JTextField userNameTextField;
    private JPasswordField passwordTextField;
    private MainView mainView;

    /**
     * 
     * @param mainView The mainview of the messenging app where you can send and
     *                 recieve messages
     */
    public LoginFrame(MainView mainView) {

        this.userNameSecret = mainView.getUser().getUserName();
        this.userPasswordSecret = mainView.getUser().getPassWord().toCharArray();
        this.mainView = mainView;
        setTitle("Messenger app: " + (mainView.getUser()).getUserName());

        setPreferredSize(new Dimension(250, 150));

        contentPane.setLayout(new BorderLayout());

        contentPane.setPreferredSize(new Dimension(250, 250));

        contentPane.add(createLoginArea(), BorderLayout.CENTER);

        JPanel buttoPanel = (JPanel) createButtons();
        contentPane.add(buttoPanel, BorderLayout.SOUTH);

        pack();
        setResizable(false);
        setVisible(true);

        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 
     * @return A JComponent with required fields and labels
     */
    protected JComponent createLoginArea() {
        JPanel loginArea = new JPanel();
        Dimension loginAreaSize = new Dimension(350, 80);
        Dimension textFieldSize = new Dimension(125, 20);
        Dimension labelSize = new Dimension(75, 20);

        JLabel userNameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        this.userNameTextField = new JTextField();
        this.passwordTextField = new JPasswordField();
        loginArea.setPreferredSize(loginAreaSize);
        userNameLabel.setPreferredSize(labelSize);
        passwordLabel.setPreferredSize(labelSize);
        userNameTextField.setPreferredSize(textFieldSize);
        passwordTextField.setPreferredSize(textFieldSize);

        JPanel userNamePanel = new JPanel();
        JPanel passWordPanel = new JPanel();

        userNamePanel.add(userNameLabel, BorderLayout.EAST);
        userNamePanel.add(this.userNameTextField, BorderLayout.WEST);

        passWordPanel.add(passwordLabel, BorderLayout.EAST);
        passWordPanel.add(this.passwordTextField, BorderLayout.WEST);

        loginArea.setBorder(BorderFactory.createLineBorder(Color.black));
        BoxLayout bl = new BoxLayout(loginArea, BoxLayout.Y_AXIS);

        loginArea.setLayout(bl);

        loginArea.add(userNamePanel);
        loginArea.add(passWordPanel);

        return loginArea;

    }

    /**
     * 
     * @return JComponent that is a JPanel with buttons and actionlisteners
     */
    protected JComponent createButtons() {
        JPanel buttonPanel = new JPanel();

        Dimension buttonPanelDim = new Dimension(350, 45);
        Dimension buttonDim = new Dimension(80, 30);

        JButton login = new JButton("Login");
        JButton cancel = new JButton("Cancel");

        buttonPanel.setPreferredSize(buttonPanelDim);
        login.setPreferredSize(buttonDim);
        cancel.setPreferredSize(buttonDim);

        login.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String userNameText = userNameTextField.getText();
                char[] passwordCharArray = passwordTextField.getPassword();

                if (userNameText.equals(userNameSecret) && Arrays.equals(userPasswordSecret, passwordCharArray)) {
                    Arrays.fill(passwordCharArray, '0');
                    Arrays.fill(userPasswordSecret, '0');
                    getRootPane().removeAll();
                    setResizable(true);
                    setPreferredSize(new Dimension(515, 450));
                    setSize(getPreferredSize());
                    getRootPane().add(mainView);
                }

            }

        });
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userNameTextField.setText("");
                passwordTextField.setText("");
            }

        });

        buttonPanel.add(login, JComponent.CENTER_ALIGNMENT);
        buttonPanel.add(cancel, JComponent.CENTER_ALIGNMENT);

        login.setAlignmentX(CENTER_ALIGNMENT);
        login.setAlignmentY(CENTER_ALIGNMENT);
        cancel.setAlignmentX(CENTER_ALIGNMENT);
        cancel.setAlignmentY(CENTER_ALIGNMENT);
        buttonPanel.setAlignmentY(CENTER_ALIGNMENT);
        buttonPanel.setAlignmentX(CENTER_ALIGNMENT);

        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        return buttonPanel;
    }

}