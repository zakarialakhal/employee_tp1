package view;

import javax.swing.*;
import java.awt.*;

public class BtnPanel extends JPanel {

    private JButton addBtn, removeBtn , updateBtn;

    private JButton editBtn;

    public JButton getEditBtn() {
        return editBtn;
    }

    public BtnPanel() {
        setLayout(new FlowLayout());

        addBtn = new JButton("Add");
        removeBtn = new JButton("Remove");
        editBtn =new JButton("edit");
        updateBtn = new JButton("Update");

        add(addBtn);
        add(removeBtn);
        add(editBtn);
        add(updateBtn);
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getRemoveBtn() {
        return removeBtn;
    }

    public JButton getUpdateBtn() {
        return updateBtn;
    }
}
