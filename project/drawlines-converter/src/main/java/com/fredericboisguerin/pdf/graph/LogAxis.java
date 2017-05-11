package com.fredericboisguerin.pdf.graph;

public class LogAxis extends Axis {
    public LogAxis(Coord min, Coord max) {
        super(min, max);
    }

    @Override
    public void accept(AxisVisitor visitor) {
        visitor.visit(this);
    }
}
