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
        Dimension windowSize = new Dimension(860, 620);
        frame.setPreferredSize(windowSize);
        frame.setMinimumSize(windowSize);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(GameWindow.GameMenuBar.getInstance());

        JPanel panel = new JPanel(new BorderLayout(25, 25));
        JLabel centerTitleLabel = new JLabel("Asteroid mining game");
        centerTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerTitleLabel.setFont(centerTitleLabel.getFont().deriveFont(36.0f));
        JLabel centerSubtitleLabel = new JLabel("Input players' names");
        centerSubtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerSubtitleLabel.setFont(centerSubtitleLabel.getFont().deriveFont(24.0f));
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        JPanel north1 = new JPanel();
        JPanel north2 = new JPanel();
        north2.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        north1.add(centerTitleLabel);
        north2.add(centerSubtitleLabel);
        northPanel.add(north1);
        northPanel.add(north2);
        panel.setBorder(BorderFactory.createEmptyBorder(60, 100, 80, 100));
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
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttons.setLayout(new BorderLayout());
        JButton startGameButton = new JButton("Start game");
        startGameButton.setFont(startGameButton.getFont().deriveFont(20.0f));
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
            Game.start(playerNames);
        });
        panel.add(buttons, BorderLayout.SOUTH);
        panel.add(input, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }
}
