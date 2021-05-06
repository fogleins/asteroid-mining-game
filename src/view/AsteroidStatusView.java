package view;

import map.asteroid.Asteroid;

import javax.swing.*;
import java.awt.*;

public class AsteroidStatusView extends JPanel {
    JLabel titleLabel = new JLabel();
    JLabel detailsLabel = new JLabel();

    public AsteroidStatusView(){
        titleLabel.setFont(titleLabel.getFont().deriveFont(20.0f));
        JScrollPane panel = new JScrollPane();
        add(panel);
        panel.add(titleLabel);
        panel.add(detailsLabel);
    }

    public void updateView(Asteroid asteroid){
        if(asteroid == null){
            return;
        }
        GetNameText(asteroid);
        String details = "";
        GetAsteroidInfo(asteroid, details);
        GetEntityInfo(asteroid, details);
        GetTeleportInfo(asteroid, details);
        detailsLabel.setText(details);
    }

    private void GetTeleportInfo(Asteroid asteroid, String details) {
        details += "Teleport:\n";
        details += asteroid.getTeleportGate() != null ? (asteroid.getTeleportGate().getOtherSide() == null ? "\t->" + asteroid.getTeleportGate().getOtherSide().getName() + "\n" : "\t-\n") : "\t-\n";
    }

    private void GetEntityInfo(Asteroid asteroid, String details) {
        details += "Entities:\n";
        if(asteroid.getEntities().size() == 0){
            details += "\t-\n";
        } else {
            for (int i = 0; i < asteroid.getEntities().size(); i++) {
                details += "\t" + asteroid.getEntities().get(i).getName() + "\n";
            }
        }
    }

    private void GetAsteroidInfo(Asteroid asteroid, String details) {
        details += asteroid.getInPerihelion() ? "In Perihelion\n" : "\n";
        details += "Resource: " + (asteroid.getResource() == null ? "-\n" : asteroid.getResource().toString() + "\n");
    }

    private void GetNameText(Asteroid asteroid) {
        titleLabel.setText(asteroid.getName());
    }
}
