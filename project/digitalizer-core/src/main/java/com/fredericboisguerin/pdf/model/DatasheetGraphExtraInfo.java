package com.fredericboisguerin.pdf.model;

public class DatasheetGraphExtraInfo {
    private final AxisName xAxisName;
    private final AxisName yAxisName;
    private final PhysicalParameter physicalParameter;

    public DatasheetGraphExtraInfo(AxisName xAxisName, AxisName yAxisName,
            PhysicalParameter physicalParameter) {
        this.xAxisName = xAxisName;
        this.yAxisName = yAxisName;
        this.physicalParameter = physicalParameter;
    }

    public AxisName getxAxisName() {
        return xAxisName;
    }

    public AxisName getyAxisName() {
        return yAxisName;
    }

    public PhysicalParameter getPhysicalParameter() {
        return physicalParameter;
    }
}
