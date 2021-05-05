package view;

import map.entity.Settler;

import javax.swing.*;

public class SettlerActionsView extends JPanel {
    private Settler settler;

    SettlerActionsView(){
        JButton moveBtn = new JButton("Move");
        JButton drillBtn = new JButton("Drill");
        JButton mineBtn = new JButton("Mine");
        JButton placeTeleportBtn = new JButton("Build Teleport");
        JButton buildRobotBtn = new JButton("Build Robot");
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
