package view;

import map.entity.Settler;

import javax.swing.*;

public class SettlerInventoryView extends JPanel {

    private Settler settler;

    public void updateView(Settler currentSettler){
        settler = currentSettler;
    }

}
