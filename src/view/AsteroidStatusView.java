package view;

import map.asteroid.Asteroid;

import javax.swing.*;
import java.awt.*;

public class AsteroidStatusView extends JPanel {
    private final JLabel titleLabel = new JLabel();
    private final JTextArea detailsTextArea = new JTextArea();

    public AsteroidStatusView() {
        titleLabel.setFont(titleLabel.getFont().deriveFont(35.0f));
        detailsTextArea.setFont(detailsTextArea.getFont().deriveFont(20.0f));
        setPreferredSize(new Dimension(200, 500));
        add(titleLabel);
        add(detailsTextArea);
        titleLabel.setPreferredSize(new Dimension(200, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.TOP);
        detailsTextArea.setPreferredSize(new Dimension(200, 450));
        //detailsTextArea.setVerticalAlignment(SwingConstants.TOP);
        detailsTextArea.setEditable(false);
        detailsTextArea.setHighlighter(null);
        detailsTextArea.setBackground(new Color(200, 200, 200, 255));
        //detailsTextArea.setBackground(UIManager.getColor("Panel.background"));
        this.setPreferredSize(new Dimension(230, 500));
    }

    public static String toMultiline(String orig) {
        return orig.replaceAll("\n", System.lineSeparator()).replaceAll("\t", "      ");
    }

    public void updateView(Asteroid asteroid) {
        if (asteroid == null) {
            return;
        }
        updateNameText(asteroid);
        String details = "";
        details += getAsteroidInfo(asteroid);
        details += getEntityInfo(asteroid);
        details += getTeleportInfo(asteroid);
        detailsTextArea.setText(toMultiline(details));
    }

    private String getTeleportInfo(Asteroid asteroid) {
        String res = "";
        res += "Teleport:\n";
        res += asteroid.getTeleportGate() != null ? (asteroid.getTeleportGate().getOtherSide() != null ? "\t->" +
                asteroid.getTeleportGate().getOtherSide().getName() + "\n" : "\t-\n") : "\t-\n";
        return res;
    }

    private String getEntityInfo(Asteroid asteroid) {
        String res = "";
        res += "Entities:\n";
        if (asteroid.getEntities().size() == 0) {
            res += "\t-\n";
        } else {
            for (int i = 0; i < asteroid.getEntities().size(); i++) {
                res += "\t" + asteroid.getEntities().get(i).getName() + "\n";
            }
        }
        return res;
    }

    private String getAsteroidInfo(Asteroid asteroid) {
        String res = "";
        res += asteroid.getInPerihelion() ? "In Perihelion\n" : "\n";
        res += "Resource: " + (asteroid.getResource() == null ? "-\n" : asteroid.getResource().toString() + "\n");
        return res;
    }

    private void updateNameText(Asteroid asteroid) {
        titleLabel.setText(asteroid.getName());
    }
}
