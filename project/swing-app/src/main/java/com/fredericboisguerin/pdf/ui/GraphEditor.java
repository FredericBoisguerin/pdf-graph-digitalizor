package com.fredericboisguerin.pdf.ui;

import com.fredericboisguerin.graph.XYPointSeries;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class GraphEditor {
    private AxisEditorForm xAxisEditorForm;
    private AxisEditorForm yAxisEditorForm;
    private JPanel mainPanel;
    private JButton goButton;
    private ToggleList<XYPointSeries> seriesToggleList;

    private final List<GoButtonListener> goButtonListeners = new ArrayList<>();

    public GraphEditor() {
        goButton.addActionListener(e -> GraphEditor.this.fireGoButtonClicked());
    }

    private void fireGoButtonClicked() {
        for (GoButtonListener goButtonListener : goButtonListeners) {
            goButtonListener.onGoButtonClicked();
        }
    }

    public boolean addGoButtonListener(GoButtonListener goButtonListener) {
        return goButtonListeners.add(goButtonListener);
    }

    public void setElements(List<XYPointSeries> elements) {
        seriesToggleList.setElements(elements);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        xAxisEditorForm = new AxisEditorForm("X");
        yAxisEditorForm = new AxisEditorForm("Y");
        seriesToggleList = new ToggleList<>();
    }

    public AxisEditorForm getxAxisEditorForm() {
        return xAxisEditorForm;
    }

    public AxisEditorForm getyAxisEditorForm() {
        return yAxisEditorForm;
    }

    public Enumeration<XYPointSeries> getSelectedElements() {
        return seriesToggleList.getSelectedElements();
    }
}
