package view;

import map.entity.Settler;
import map.resource.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class SettlerInventoryView extends JPanel {

    private Settler settler;
    private final JTextArea informationLabel;
    private final JLabel titleLabel;

    SettlerInventoryView() {
        titleLabel = new JLabel();
        informationLabel = new JTextArea();
        titleLabel.setFont(titleLabel.getFont().deriveFont(35.0f));

        informationLabel.setFont(informationLabel.getFont().deriveFont(20.0f));
        setPreferredSize(new Dimension(200, 500));
        add(titleLabel);
        add(informationLabel);
        titleLabel.setPreferredSize(new Dimension(180, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.TOP);
        informationLabel.setPreferredSize(new Dimension(180, 170));
        informationLabel.setEditable(false);
        informationLabel.setHighlighter(null);
        informationLabel.setBackground(new Color(238, 238, 238, 255));
    }

    public void updateView(Settler currentSettler) {
        settler = currentSettler;
        titleLabel.setText(settler.getName());
        informationLabel.setText("");

        HashMap<String, Integer> resCount = new HashMap<>();
        resCount.put((new Uranium()).toString(), 0);
        resCount.put((new Coal()).toString(), 0);
        resCount.put((new Ice()).toString(), 0);
        resCount.put((new Iron()).toString(), 0);
        for (Resource r : settler.getResources()) {
            int countSoFar = resCount.get(r.toString());
            resCount.put(r.toString(), countSoFar + 1);
        }
        resCount.forEach((k, v) -> informationLabel.append(k + ": " + v + '\n'));

        informationLabel.append("\nTeleports: " + settler.getTeleportNumber());
    }

}
