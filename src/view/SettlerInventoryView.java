package view;

import map.entity.Settler;

import javax.swing.*;
import java.awt.*;

public class SettlerInventoryView extends JPanel {

    private Settler settler;

    SettlerInventoryView(){

        setLayout(new GridLayout(0,2));
        JLabel titleLabel = new JLabel("Inventory");
        titleLabel.setFont(titleLabel.getFont().deriveFont(20.0f));
        add(titleLabel);
        add(new JPanel());
        add(new JLabel("Name: "));
    }

    public void updateView(Settler currentSettler){
        settler = currentSettler;

    }

}
