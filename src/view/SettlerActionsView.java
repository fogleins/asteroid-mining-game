package view;

import map.entity.Settler;

import javax.swing.*;

public class SettlerActionsView extends JPanel {
    private Settler settler;

    public void updateView(Settler settler) {
        this.settler = settler;
    }
}
