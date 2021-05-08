package view;

import map.entity.Settler;
import map.resource.*;

import javax.swing.*;
import java.awt.*;

public class SettlerInventoryView extends JPanel {

    private Settler settler;
    JTextArea informationLabel;
    JLabel titleLabel;

    SettlerInventoryView() {
        titleLabel = new JLabel();
        informationLabel = new JTextArea();
        titleLabel.setFont(titleLabel.getFont().deriveFont(20.0f));

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
        informationLabel.setBackground(new Color(200, 200, 200, 255));
    }

    public void updateView(Settler currentSettler) {
        settler = currentSettler;
        titleLabel.setText(settler.getName());

        Uranium uranium = new Uranium();
        Ice ice = new Ice();
        Coal coal = new Coal();
        Iron iron = new Iron();

        BillOfResources bill = new BillOfResources();
        bill.addResources(uranium);

        informationLabel.append("Uranium: " + bill.count(settler.getResources()) + "\n");
        bill.removeResources(uranium);
        bill.addResources(coal);

        informationLabel.append("Coal: " + bill.count(settler.getResources()) + "\n");
        bill.removeResources(coal);
        bill.addResources(ice);

        informationLabel.append("Ice: " + bill.count(settler.getResources()) + "\n");
        bill.removeResources(ice);
        bill.addResources(iron);

        informationLabel.append("Iron: " + bill.count(settler.getResources()) + "\n\n");

        informationLabel.append("Teleports: " + settler.getTeleportNumber());
    }

}
