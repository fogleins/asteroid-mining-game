package view;

import javax.swing.*;
import java.awt.*;

/**
 * UI panel showing the data of the game's status
 */
public class GameStatusView extends JPanel {
    /**
     * A JLabel displaying the current round number.
     */
    private final JLabel round;
    /**
     * A JLabel shown if sunflare is coming.
     */
    private final JLabel sunflare;

    /**
     * Constructor, initializes the UI components of this panel.
     */
    public GameStatusView() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 22, 8));
        setFont(getFont().deriveFont(24.0f));
        round = new JLabel();
        round.setFont(getFont().deriveFont(Font.BOLD));
        add(round);
        sunflare = new JLabel();
        sunflare.setFont(getFont().deriveFont(Font.BOLD));
        sunflare.setForeground(Color.RED);
        sunflare.setText("sunflare is coming");
        add(sunflare);
    }

    /**
     * Refreshes the data shown by this panel
     *
     * @param round          The current round of the game.
     * @param sunflareComing Whether a sunflare is coming or not.
     */
    public void updateView(Integer round, boolean sunflareComing) {
        this.round.setText("Round " + round.toString());
        sunflare.setVisible(sunflareComing);
    }
}
