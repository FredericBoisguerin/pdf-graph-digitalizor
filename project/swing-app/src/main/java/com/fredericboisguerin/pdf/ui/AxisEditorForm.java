package com.fredericboisguerin.pdf.ui;

import javax.swing.*;

import com.fredericboisguerin.graph.Axis;
import com.fredericboisguerin.graph.Coord;
import com.fredericboisguerin.graph.LinearAxis;
import com.fredericboisguerin.graph.LogAxis;
import com.fredericboisguerin.pdf.ui.exceptions.InvalidInputException;

public class AxisEditorForm {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField minField;
    private JTextField maxField;
    private JCheckBox logarithmicCheckBox;
    private JPanel innerPanel;

    public AxisEditorForm(String axisName) {
        titleLabel.setText("Axis " + axisName);
        logarithmicCheckBox.setSelected(false);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Axis getAxis() {
        float min;
        float max;
        try {
            min = getFloatValue(minField, "min");
            max = getFloatValue(maxField, "max");
        } catch (InvalidInputException e) {
            NotificationUtils.showError(mainPanel.getParent(), e.getMessage());
            throw new RuntimeException(e);
        }
        boolean isLogarithmic = logarithmicCheckBox.isSelected();
        Coord minCoord = Coord.of(min);
        Coord maxCoord = Coord.of(max);
        return getAxisNew(minCoord, maxCoord, isLogarithmic);
    }

    private Axis getAxisNew(Coord minCoord, Coord maxCoord, boolean isLogarithmic) {
        return isLogarithmic ? new LogAxis(minCoord, maxCoord) : new LinearAxis(minCoord, maxCoord);
    }

    private float getFloatValue(JTextField field, String designation) throws InvalidInputException {
        float result;
        try {
            result = new Float(field.getText());
        } catch (Exception e) {
            field.requestFocus();
            throw new InvalidInputException(designation);
        }
        return result;
    }
}