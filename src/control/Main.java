package control;

import view.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        showNameInputWindow();
    }

    // TODO: majd ha lesz rá idő, ezen lehetne szépíteni
    private static void showNameInputWindow() {
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2)); // a JLabeleket tartalmazó panel
        JPanel input = new JPanel(new GridLayout(0, 1, 2, 2));
        ArrayList<JTextField> textFields = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            labels.add(new JLabel("Player name", SwingConstants.RIGHT));
            JTextField textField = new JTextField(20);
            textFields.add(textField);
            input.add(textField);
        }
        panel.add(labels, BorderLayout.WEST);

        JPanel buttons = new JPanel();
//        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setLayout(new BorderLayout());
        JButton startGameButton = new JButton("Start game");
        buttons.add(startGameButton, BorderLayout.EAST);
        startGameButton.addActionListener(actionEvent -> {
            // hide the start screen, so that no new game can be started when the main window is active
            frame.setVisible(false);
            startGame(textFields);
        });
        panel.add(buttons, BorderLayout.SOUTH);
        panel.add(input, BorderLayout.CENTER);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private static void startGame(ArrayList<JTextField> textFields) {
        ArrayList<String> playerNames = new ArrayList<>();
        for (JTextField textField : textFields) {
            if (!textField.getText().equals(""))
                playerNames.add(textField.getText());
        }
        GameWindow.getInstance().setVisible(true);
        GameWindow.init(playerNames);
    }
}
