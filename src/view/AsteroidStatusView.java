package view;

import map.asteroid.Asteroid;

import javax.swing.*;
import java.awt.*;

public class AsteroidStatusView extends JPanel {
    JLabel titleLabel = new JLabel();
    //JLabel detailsLabel = new JLabel();
    JTextArea detailsLabel = new JTextArea();

    public AsteroidStatusView(){
        titleLabel.setFont(titleLabel.getFont().deriveFont(35.0f));
        detailsLabel.setFont(detailsLabel.getFont().deriveFont(20.0f));
        setPreferredSize(new Dimension(200, 500));
        add(titleLabel);
        add(detailsLabel);
        titleLabel.setPreferredSize(new Dimension(180, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.TOP);
        detailsLabel.setPreferredSize(new Dimension(180, 450));
        //detailsLabel.setVerticalAlignment(SwingConstants.TOP);
        detailsLabel.setEditable(false);
        detailsLabel.setHighlighter(null);
        detailsLabel.setBackground(new Color(200, 200, 200, 255));
        //detailsLabel.setBackground(UIManager.getColor("Panel.background"));
    }

    public void updateView(Asteroid asteroid){
        if(asteroid == null){
            return;
        }
        GetNameText(asteroid);
        String details = "";
        details += GetAsteroidInfo(asteroid);
        details += GetEntityInfo(asteroid);
        details += GetTeleportInfo(asteroid);
        detailsLabel.setText(ToMultiline(details));
    }

    public static String ToMultiline(String orig)
    {
        return orig.replaceAll("\n", System.lineSeparator()).replaceAll("\t", "      ");
    }

    private String GetTeleportInfo(Asteroid asteroid) {
        String res = "";
        res += "Teleport:\n";
        res += asteroid.getTeleportGate() != null ? (asteroid.getTeleportGate().getOtherSide() == null ? "\t->" + asteroid.getTeleportGate().getOtherSide().getName() + "\n" : "\t-\n") : "\t-\n";
        return res;
    }

    private String GetEntityInfo(Asteroid asteroid) {
        String res = "";
        res += "Entities:\n";
        if(asteroid.getEntities().size() == 0){
            res += "\t-\n";
        } else {
            for (int i = 0; i < asteroid.getEntities().size(); i++) {
                res += "\t" + asteroid.getEntities().get(i).getName() + "\n";
            }
        }
        return res;
    }

    private String GetAsteroidInfo(Asteroid asteroid) {
        String res = "";
        res += asteroid.getInPerihelion() ? "In Perihelion\n" : "\n";
        res += "Resource: " + (asteroid.getResource() == null ? "-\n" : asteroid.getResource().toString() + "\n");
        return res;
    }

    private void GetNameText(Asteroid asteroid) {
        titleLabel.setText(asteroid.getName());
    }
}
