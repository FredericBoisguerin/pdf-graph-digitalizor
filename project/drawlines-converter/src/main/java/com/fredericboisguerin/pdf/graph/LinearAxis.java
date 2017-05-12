package com.fredericboisguerin.pdf.graph;

public class LinearAxis extends Axis {
    public LinearAxis(AxisCoords axisCoords) {
        super(axisCoords);
    }

    @Override
    public void accept(AxisVisitor visitor) {
        visitor.visit(this);
    }
}
