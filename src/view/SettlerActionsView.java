package view;

import Exceptions.ActionFailedException;
import control.Game;
import map.asteroid.Asteroid;
import map.entity.Settler;

import javax.swing.*;

public class SettlerActionsView extends JPanel {
    private Settler settler;

    SettlerActionsView() {
        JButton moveBtn = new JButton("Move");
        moveBtn.addActionListener(actionEvent -> {
            NeighbourChooser chooser = new NeighbourChooser();
            Asteroid chosen = chooser.ChooseAsteroid(settler.getAsteroid().getNeighbours());
            settler.move(chosen);

        });

        JButton drillBtn = new JButton("Drill");
        drillBtn.addActionListener(actionEvent -> {
            try {
                settler.drill();
            } catch (ActionFailedException e) {
                JOptionPane.showMessageDialog(this, "You can't drill!");
            }
        });

        JButton mineBtn = new JButton("Mine");
        mineBtn.addActionListener(actionEvent -> {
            try {
                settler.mine();
            } catch (ActionFailedException e) {
                JOptionPane.showMessageDialog(this, "You can't mine!");
            }
        });

        JButton placeTeleportBtn = new JButton("Build Teleport");
        placeTeleportBtn.addActionListener(actionListener -> settler.placeTeleport());

        JButton buildRobotBtn = new JButton("Build Robot");
        buildRobotBtn.addActionListener(actionListener -> {
            try {
                settler.buildRobot();
            } catch (ActionFailedException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        });

        JButton buildTeleportBtn = new JButton("Place Teleport");

        add(moveBtn);
        add(drillBtn);
        add(mineBtn);
        add(placeTeleportBtn);
        add(buildRobotBtn);
        add(buildTeleportBtn);
    }


    public void updateView(Settler settler) {
        this.settler = settler;
    }
}
