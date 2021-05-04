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
        JFrame frame = new JFrame("New game");
        frame.setLayout(new BorderLayout());
        Dimension windowSize = new Dimension(1280, 720);
        frame.setPreferredSize(windowSize);
        frame.setMinimumSize(windowSize);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(25, 25));
        JLabel centerLabel = new JLabel("Input players' names");
        centerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerLabel.setFont(centerLabel.getFont().deriveFont(36.0f));
        JPanel northPanel = new JPanel();
        northPanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
        northPanel.add(centerLabel, BorderLayout.NORTH);
        panel.setBorder(BorderFactory.createEmptyBorder(80, 200, 100, 200));
        JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
        JPanel input = new JPanel(new GridLayout(0, 1, 2, 2));
        ArrayList<JTextField> textFields = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            JLabel label = new JLabel(String.format("Player %d:", i + 1));
            label.setFont(label.getFont().deriveFont(20.0f));
            labels.add(label);
            JTextField textField = new JTextField(15);
            textField.setFont(textField.getFont().deriveFont(20.0f));
            textFields.add(textField);
            input.add(textField);
        }
        panel.add(labels, BorderLayout.WEST);

        JPanel buttons = new JPanel();
        buttons.setLayout(new BorderLayout());
        JButton startGameButton = new JButton("Start game");
        buttons.add(startGameButton, BorderLayout.EAST);
        startGameButton.addActionListener(actionEvent -> {
            ArrayList<String> playerNames = new ArrayList<>();
            for (JTextField textField : textFields) {
                String text = textField.getText().trim();
                if (!text.equals(""))
                    playerNames.add(text);
            }
            // if not enough player names have been entered, the game shouldn't start
            if (playerNames.size() < 2) {
                JOptionPane.showMessageDialog(frame, "Please input at least two valid names.");
                return;
            }
            // hide the start screen, so that no new game can be started when the main window is active
            frame.setVisible(false);
            startGame(playerNames);
        });
        panel.add(buttons, BorderLayout.SOUTH);
        panel.add(input, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }

    private static void startGame(ArrayList<String> playerNames) {
        GameWindow.getInstance().setVisible(true);
        GameWindow.init(playerNames);
    }
}
