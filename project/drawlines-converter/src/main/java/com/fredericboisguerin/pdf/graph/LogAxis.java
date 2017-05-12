package com.fredericboisguerin.pdf.graph;

public class LogAxis extends Axis {
    public LogAxis(AxisCoords axisCoords) {
        super(axisCoords);
    }

    @Override
    public void accept(AxisVisitor visitor) {
        visitor.visit(this);
    }
}
