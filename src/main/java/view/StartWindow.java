package view;

import control.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StartWindow extends JFrame {

    /**
     * Constructor, initializes the Frame
     */
    public StartWindow() {
        super("New game");
        setLayout(new BorderLayout());
        Dimension windowSize = new Dimension(860, 620);
        setPreferredSize(windowSize);
        setMinimumSize(windowSize);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // title labels
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
        // name text fields and labels
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

        /* buttons on the bottom (south panel) */
        JPanel southPanel = new JPanel();
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        southPanel.setLayout(new BorderLayout());
        JPanel startPanel = new JPanel();
        // load button
        JButton loadGameBtn = new JButton("Load game");
        Font buttonFont = loadGameBtn.getFont().deriveFont(16.0f);
        loadGameBtn.setFont(buttonFont);
        loadGameBtn.addActionListener(e -> {
            Game.readDataFromFile();
            setVisible(false);
        });
        startPanel.add(loadGameBtn);
        // start button
        JButton startGameButton = new JButton("Start game");
        startGameButton.setFont(buttonFont);
        startPanel.add(startGameButton);
        startGameButton.addActionListener(actionEvent -> {
            ArrayList<String> playerNames = new ArrayList<>();
            for (JTextField textField : textFields) {
                String text = textField.getText().trim();
                if (!text.equals(""))
                    playerNames.add(text);
            }
            // if not enough player names have been entered, the game shouldn't start
            if (playerNames.size() < 2) {
                JOptionPane.showMessageDialog(this, "Please input at least two valid names.");
                return;
            }
            // hide the start screen, so that no new game can be started when the main window is active
            setVisible(false);
            Game.start(playerNames);
        });
        southPanel.add(startPanel, BorderLayout.EAST);
        // about button
        JButton about = new JButton("About");
        java.net.URL imgURL = getClass().getResource("/team_logo.png");
        String message = "Asteroid mining game\n\n" +
                "Created by:\n" +
                "Foglein Simon,\n" +
                "Godor Marton,\n" +
                "Szabo Viktor Akos,\n" +
                "Telek Benjamin Mark,\n" +
                "Wang Tingli Alexandra\n" +
                "\n(C) rapid_sloths, 2021\n\n";
        if (imgURL != null)
            about.addActionListener(e -> JOptionPane.showMessageDialog(null, message, "About this game",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(imgURL))
            );
        else
            about.addActionListener(e -> JOptionPane.showMessageDialog(null, message, "About this game",
                    JOptionPane.PLAIN_MESSAGE)
            );
        southPanel.add(about, BorderLayout.WEST);

        // add everything and show
        panel.add(southPanel, BorderLayout.SOUTH);
        panel.add(input, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
        pack();
    }

    /**
     * Shows the start window
     */
    public void showFrame() {
        setVisible(true);
    }
}
