package com.fredericboisguerin.pdf.ui.graph.create.model.graphinfo;

public class DatasheetGraphInfoViewModel {

    private final AxisViewModel xAxis = new AxisViewModel();
    private final AxisViewModel yAxis = new AxisViewModel();
    private final ParameterViewModel parameter = new ParameterViewModel();

    public AxisViewModel getxAxis() {
        return xAxis;
    }

    public AxisViewModel getyAxis() {
        return yAxis;
    }

    public ParameterViewModel getParameter() {
        return parameter;
    }
}
