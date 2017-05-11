package com.fredericboisguerin.pdf.graph;

public class LinearAxis extends Axis {
    public LinearAxis(Coord min, Coord max) {
        super(min, max);
    }

    @Override
    public void accept(AxisVisitor visitor) {
        visitor.visit(this);
    }
}
